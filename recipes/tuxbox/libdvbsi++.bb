DESCRIPTION = "libdvbsi++ by obi@saftware.de"
DEPENDS = "dreambox-dvbincludes"
MAINTAINER = "Felix Domke <tmbinc@elitdvb.net>"

#inherit gitpkgv

SRCREV = "bf0c2bb7b326b93713629dbf74f63a0a54ffe59d"
PV = "1.0+gitr${SRCREV}"
#PKGV = "1.0+git${GITPKGV}"
PR = "r0"
SRC_URI = "git://git.opendreambox.org/git/obi/libdvbsi++.git;protocol=git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_stage() {
	oe_runmake install prefix=${STAGING_DIR} \
		bindir=${STAGING_BINDIR} \
		includedir=${STAGING_INCDIR} \
		libdir=${STAGING_LIBDIR} \
		datadir=${STAGING_DATADIR}
}

EXTRA_OECONF = "--with-target=native"
