# Originally brought to you by the Angstrom Distribution

# Branding
TARGET_VENDOR = ""

#mess with compiler flags to use -Os instead of -O2
#Please see http://free-electrons.com/doc/embedded_linux_optimizations/img47.html for some more info
# perl has some problems, see http://bugs.openembedded.net/show_bug.cgi?id=1616
# [23:00] oxo: glibc (any version) on sparc does not like (repeat me) "-Os"

FULL_OPTIMIZATION = "-fexpensive-optimizations -frename-registers -fomit-frame-pointer -O2"

FULL_OPTIMIZATION_pn-perl = "-fexpensive-optimizations -fomit-frame-pointer -frename-registers -O1"
FULL_OPTIMIZATION_pn-glibc = "-fexpensive-optimizations -fomit-frame-pointer -O2"
FULL_OPTIMIZATION_sparc = "-fexpensive-optimizations -fomit-frame-pointer -frename-registers -O2"

BUILD_OPTIMIZATION_pn-perl = "-O1"
BUILD_OPTIMIZATION_pn-glibc = "-O2"
BUILD_OPTIMIZATION_sparc = "-O2"

TARGET_LINK_HASH_STYLE = ""

CXXFLAGS += "-fvisibility-inlines-hidden"

#########################################################################
def detect_arm_abi (d):
	import bb
	if bb.data.getVar('DISTRO_FEATURES',d) is None:
		if bb.data.getVar('TARGET_ARCH', d, 1) in [ 'arm', 'armeb' ]:
			return "oabi"
		else:
			return ""
	if bb.data.getVar('TARGET_ARCH', d, 1) in [ 'arm', 'armeb' ]:
		if 'eabi' in bb.data.getVar('DISTRO_FEATURES',d).split():
			return "eabi"
		return "oabi"
	return ""

def compute_os_portion_of_target_triplet (d):
	import bb
	arm_eabi_unsupported_arches = "armv1 armv2 armv3"
	ppc_spe_supporting_arches = "ppce500v2 ppce500"
	gnu_suffix = ""
	if bb.data.getVar('LIBC', d, 1) == "uclibc":
		libc_suffix = "uclibc"
	else:
		libc_suffix = ""
	
	if bb.data.getVar('TARGET_ARCH',d,1) in ['bfin']:
		if libc_suffix is not "uclibc":
			bb.fatal("bfin is not supported on glibc/eglibc. Please choose uclibc")
		else:
			os_suffix = "uclinux"
	else:
		os_suffix = "linux"
	bparch = bb.data.getVar('BASE_PACKAGE_ARCH', d,1)

	if bb.data.getVar('DISTRO_FEATURES',d,1) is not None and \
	bparch is not None:
		if 'eabi' in bb.data.getVar('DISTRO_FEATURES',d,1).split() and \
		bb.data.getVar('TARGET_ARCH', d, 1) in [ 'arm', 'armeb' ]:
			if bparch in arm_eabi_unsupported_arches.split():
				bb.fatal("DISTRO requested EABI but selected machine does not support EABI")
				abi_suffix = ""
			else:
				if libc_suffix is not "uclibc":
					gnu_suffix = "gnu"
				abi_suffix = "eabi"
		elif bparch in ppc_spe_supporting_arches.split():
			if libc_suffix is not "uclibc":
				gnu_suffix = "gnu"
			abi_suffix = "spe"
		else:
			abi_suffix = ""
	else:
		bb.note("DISTRO_FEATURES is not set abi suffix not set")
		abi_suffix = ""
	if libc_suffix is not "" or abi_suffix is not "":
		return os_suffix + "-" + libc_suffix + gnu_suffix + abi_suffix
	else:
		return os_suffix

# This is needed to get a correct PACKAGE_ARCH for packages that have PACKAGE_ARCH = ${MACHINE_ARCH}
ARM_ABI = "${@detect_arm_abi(d)}"
TARGET_OS = "${@compute_os_portion_of_target_triplet(d)}"

