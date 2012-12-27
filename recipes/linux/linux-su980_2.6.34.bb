
DESCRIPTION = "Dummy kernel for su980"
SECTION = "kernel"
LICENSE = "GPL"

inherit kernel

#Don't use PR here, use MACHINE_KERNEL_PR in machine.conf instead
#PR="${PR_INC}.0"

S = "${WORKDIR}/linux-${PV}"
PKG_kernel-image = "kernel-image"
PKG_kernel-base = "kernel-base"
RPROVIDES_kernel = "kernel-${KERNEL_VERSION}"
RPROVIDES_kernel-image = "kernel-image-${KERNEL_VERSION}"
RPROVIDES_kernel-base = "kernel-base-${KERNEL_VERSION}"
PROVIDES += "kernel-${KERNEL_VERSION} kernel-image-${KERNEL_VERSION} kernel-base-${KERNEL_VERSION}"

SRC_URI += "\
	file://linux-2.6.18-fix-serial.patch \
"
#PACKAGES = "kernel kernel-base kernel-image"
ALLOW_EMPTY = "1"

do_fetch() {
	mkdir -p ${S}/include/generated/
	echo "#define UTS_RELEASE \"2.6.34\"" > ${S}/include/generated/utsrelease.h
}

do_unpack() {
	:
}

do_patch() {
	:
}

do_configure() {
	:
}

do_install() {
	# dummy vmlinux 
	echo "It's a dummy kernel image" > vmlinux	
}

do_compile() {
	:
}

do_install() {
	:
}

do_package() {
	:
}

#do_package_write_ipk() {
#:
#}

do_populate_sysroot() {
	:
}

do_package_stage() {
	:
}

do_deploy() {
	:
}
