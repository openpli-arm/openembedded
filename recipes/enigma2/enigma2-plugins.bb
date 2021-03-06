DESCRIPTION = "Additional plugins for Enigma2"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"

PACKAGES_DYNAMIC = "enigma2-plugin-*"

inherit gitpkgv

PV = "experimental-git${SRCPV}"
PKGV = "experimental-git${GITPKGV}"
PR = "r5"

SRCREV_pn-${PN} = "748e04b7efa9cec11ce6283039649e8bb77001bd"

SRC_URI="git://openpli.git.sourceforge.net/gitroot/openpli/plugins-enigma2;protocol=git"
SRC_URI_append_arm = " file://plugins-enigma2-webinterface.patch \
			file://plugins-enigma2-webinterface-changehardwerainfo.patch"

EXTRA_OECONF = " \
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
"
EXTRA_OECONF_arm = " \
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${BASEPKG_HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
"

CONFFILES_${PN} += "${sysconfdir}/enigma2/movietags"
FILES_${PN} += " /usr/share/enigma2 /usr/share/fonts "
FILES_${PN}-meta = "${datadir}/meta"
PACKAGES += "${PN}-meta"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools

S = "${WORKDIR}/git"

DEPENDS = "enigma2 python-pyopenssl python-gdata streamripper python-mutagen python-twisted python-daap"

python populate_packages_prepend () {
	enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)

	do_split_packages(d, enigma2_plugindir, '(.*?/.*?)/.*', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)

	def getControlLines(mydir, d, package):
		import os
		try:
			#ac3lipsync is renamed since 20091121 to audiosync.. but rename in cvs is not possible without lost of revision history..
			#so the foldername is still ac3lipsync
			if package == 'audiosync':
				package = 'ac3lipsync'
			src = open(mydir + package + "/CONTROL/control").read()
		except IOError:
			return
		for line in src.split("\n"):
			if line.startswith('Package: '):
				full_package = line[9:]
			if line.startswith('Depends: '):
				# some plugins still reference twisted-* dependencies, these packages are now called python-twisted-*
				depends = line[9:].replace(',', '').split(' ')
				rdepends = ''
				for depend in depends:
					if depend.startswith('twisted-'):
						rdepends += ' ' + depend.replace('twisted-', 'python-twisted-')
					elif depend in ('enigma2', 'enigma2-plugins'):
						pass # ignore silly depends
					else:
						rdepends += ' ' + depend
				bb.data.setVar('RDEPENDS_' + full_package, rdepends, d)
			if line.startswith('Description: '):
				bb.data.setVar('DESCRIPTION_' + full_package, line[13:], d)
			if line.startswith('Replaces: '):
				bb.data.setVar('RREPLACES_' + full_package, ' '.join(line[10:].split(', ')), d)
			if line.startswith('Conflicts: '):
				bb.data.setVar('RCONFLICTS_' + full_package, ' '.join(line[11:].split(', ')), d)
			if line.startswith('Maintainer: '):
				bb.data.setVar('MAINTAINER_' + full_package, line[12:], d)


	mydir = bb.data.getVar('D', d, 1) + "/../git/"
	for package in bb.data.getVar('PACKAGES', d, 1).split():
		getControlLines(mydir, d, package.split('-')[-1])
}

# remove unused .pyc files
do_install_append() {
        find ${D}/usr/lib/enigma2/python/ -name '*.pyc' -exec rm {} \;
}
