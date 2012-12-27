# dreambox-mipsel.inc

MACHINE_FEATURES += "alsa"
MACHINE_KERNEL_PR = "r6"

#PREFERRED_VERSION_linux = "2.6.34"
PREFERRED_VERSION_linux-libc-headers = "2.6.34"

PREFERRED_VERSION_madwifi-ng = "r3314-20080131"

PREFERRED_PROVIDER_virtual/showiframe = "su980-dvb-tools"
PREFERRED_PROVIDER_virtual/blindscanutils = "dreambox-blindscan-utils"

TARGET_ARCH = "arm"
TARGET_FPU ?= "soft"

#Archer:We use external toolchain to provide linux-libc-headers
#PREFERRED_PROVIDER_linux-libc-headers 	= "linux-libc-headers"

require conf/machine/include/dreambox.inc
require conf/machine/include/tune-cortexa9.inc
