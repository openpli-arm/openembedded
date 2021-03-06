DESCRIPTION = "CCcam ${PV} softcam"

PN = "enigma2-plugin-softcams-cccam"
PV = "2.3.0"
PR = "r0"

SRC_URI = "http://downloads.pli-images.org/softcams/CCcam-${PV}.zip \
	file://CCcam.xml"

CAMNAME = "CCcam"

S = "${WORKDIR}/CCcam-${PV}"

require softcam.inc

INHIBIT_PACKAGE_STRIP = "1"

CONFFILES = "/etc/CCcam.cfg /etc/ppanels/CCcam.xml"


do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/CCcam.${TARGET_ARCH} ${D}/usr/bin/${CAMNAME}
	install -d ${D}/etc
	install -m 0644 ${S}/CCcam.cfg ${D}/etc/CCcam.cfg
	install -d ${D}/etc/ppanels
	install -m 0644 ${WORKDIR}/CCcam.xml ${D}/etc/ppanels/CCcam.xml
}
