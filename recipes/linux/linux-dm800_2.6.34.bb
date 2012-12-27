require linux-opendreambox-2.6.18.inc

#Don't use PR here, use MACHINE_KERNEL_PR in machine.conf instead
#PR="${PR_INC}.0"

SRC_URI += "\
	file://linux-2.6.18-fix-serial.patch \
"
do_patch() {
	:
}

do_install_prepend() {
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

do_package_write_ipk() {
	:
}

do_populate_sysroot() {
	:
}

do_package_stage() {
	:
}

do_deploy() {
	:
}
