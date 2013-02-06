DESCRIPTION = "ca test for su980 factory test"
SECTION = "base"
LICENSE = "proprietary"
MAINTAINER = "Archer Zhao <archer.zhao@sen5.com>"

PV = '20121023'
PR = '1'

SRC_URI = "https://www.dropbox.com/s/fuetif84w0pqez9/su980-ca-test-${PV}.tar.gz"

FILES_${PN} = "${bindir}/*"

do_compile() {
}

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/ca_test ${D}/${bindir}
}
