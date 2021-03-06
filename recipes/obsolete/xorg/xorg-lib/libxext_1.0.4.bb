require xorg-lib-common.inc
DESCRIPTION = "X11 miscellaneous extension library"
DEPENDS += "xproto virtual/libx11 xextproto libxau"
PE = "1"
PR = "${INC_PR}.0"

SRC_URI[archive.md5sum] = "a91f1f722ac80c597cf0b75dcb8b48c0"
SRC_URI[archive.sha256sum] = "2dfd8eace1cafacc87b4055c57efeb771a740e24141d3f113de58c2a9eebd21f"

BBCLASSEXTEND = "native nativesdk"

XORG_PN = "libXext"
