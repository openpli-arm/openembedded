require xorg-app-common.inc
DESCRIPTION = "a program to create an index of scalable font files for X"
DEPENDS += " zlib libfontenc freetype "
PR = "${INC_PR}.1"

SRC_URI[archive.md5sum] = "1d608771aca9695b828cec1e34178fd1"
SRC_URI[archive.sha256sum] = "0458a3a5525b4ab458a018648ef8575afc191f904364c00e27876a7bd53af020"

BBCLASSEXTEND = "native"
