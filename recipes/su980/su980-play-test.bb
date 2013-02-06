DESCRIPTION = "play test for su980 factory test"
SECTION = "base"
LICENSE = "proprietary"
MAINTAINER = "Archer Zhao <archer.zhao@sen5.com>"

PV = '20130124'
PR = '2'

SRC_URI = "https://www.dropbox.com/s/3vs09usu1kge919/su980-play-test-${PV}.tar.gz"

FILES_${PN} = "${bindir}/*"

do_compile() {
}

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/play_test ${D}/${bindir}
}
