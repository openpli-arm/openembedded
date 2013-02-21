DESCRIPTION = "User space libs for su980"
SECTION = "base"
LICENSE = "proprietary"
MAINTAINER = "Archer Zhao <archer.zhao@sen5.com>"

PV = '20130221'

SRC_URI = "https://www.dropbox.com/s/quuixa3yw1943wt/su980-user-libs-${PV}.tar.gz"
PROVIDES += "libciplus.so"

FILES_${PN} = "${libdir}/*.so"

do_compile() {
}

do_install() {
	install -d ${D}/${libdir}
	for f in *.a  *.so; do
		install -m 0644 ${S}/$f ${D}/${libdir}
	done
}
