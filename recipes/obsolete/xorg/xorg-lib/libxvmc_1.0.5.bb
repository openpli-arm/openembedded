require xorg-lib-common.inc
DESCRIPTION = "X Video Motion Compensation extension library"
DEPENDS += "libxext libxv videoproto"
PE = "1"
PR = "${INC_PR}.0"

SRC_URI[archive.md5sum] = "16c3a11add14979beb7510e44623cac6"
SRC_URI[archive.sha256sum] = "0644c768d28343af0efe0cb09fc9e0220b8ad755642bb498a3038cee328fde7f"

XORG_PN = "libXvMC"
