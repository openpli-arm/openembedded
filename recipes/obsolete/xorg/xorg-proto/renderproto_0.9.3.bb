require xorg-proto-common.inc
PE = "1"
PR = "${INC_PR}.0"

SRC_URI[archive.md5sum] = "598bc9a493deee2e48e4434e94467189"
SRC_URI[archive.sha256sum] = "c98a08b877e98a8fa7daa04be472de7e0cb38a99593faca8b987effcea2dd45b"

BBCLASSEXTEND = "native nativesdk sdk"

CONFLICTS = "renderext"
