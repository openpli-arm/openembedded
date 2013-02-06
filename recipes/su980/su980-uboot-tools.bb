DESCRIPTION = "uboot tools setenv and getenv for su980"
SECTION = "base"
LICENSE = "proprietary"
MAINTAINER = "Archer Zhao <archer.zhao@sen5.com>"

PV = '20120615'

SRC_URI = "https://www.dropbox.com/s/a36f3nczbrr6xqy/su980-uboot-tools-${PV}.tar.gz"

FILES_${PN} = "${bindir}/*"

do_compile() {
}

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/getenv ${D}/${bindir}
	install -m 0755 ${S}/setenv ${D}/${bindir}
}
