DESCRIPTION="AiO screenshot grabber"
MAINTAINER = "PLi team"
LICENSE = "GPL"

DEPENDS = "jpeg libpng zlib"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r1"

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/aio-grab;protocol=git "

SRC_URI_append_arm = "file://aio-grab-su980-osd.patch "

S = "${WORKDIR}/git"

inherit autotools pkgconfig
