DESCRIPTION = "User space daemon for extended IEEE 802.11 management"
HOMEPAGE = "http://hostap.epitest.fi"
SECTION = "kernel/userland"
PRIORITY = "optional"
LICENSE = "GPL"
RDEPENDS_${PN} = "hostap-utils (${PV})"
DEPENDS = "openssl"
DEPENDS_append_mtx-1 = " madwifi-modules"
DEPENDS_append_mtx-2 = " madwifi-modules"
CPPFLAGS_append_mtx-1 = " -I${STAGING_INCDIR}/madwifi/"
CPPFLAGS_append_mtx-2 = " -I${STAGING_INCDIR}/madwifi/"

SRC_URI = "http://hostap.epitest.fi/releases/hostapd-${PV}.tar.gz \
	file://makefile-cross.diff;striplevel=0 \
	file://madwifi-bsd-fix.diff;striplevel=0 \
	file://defconfig \
	file://init"

S = "${WORKDIR}/hostapd-${PV}"

do_configure() {
       install -m 0644 ${WORKDIR}/defconfig ${S}/.config
}

do_compile() {
	CFLAGS='${CFLAGS}' CC='${CC}' make
}

do_install() {
	install -d ${D}${sbindir} ${D}${sysconfdir}/init.d
	make TARGET_PREFIX=${D}${sbindir} install
	install -m 0644 hostapd.conf ${D}${sysconfdir}
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/hostapd
}

