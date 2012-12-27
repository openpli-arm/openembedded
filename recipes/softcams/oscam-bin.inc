DESCRIPTION = "Oscam binary, freshly compiled"
PV = "svn${SRCPV}"
PR = "r0"
DEPENDS = "${OSCAMDEPENS}"
RREPLACES_${PN} = "oscam-bin"
RCONFLICTS_${PN} = "oscam-bin"
RPROVIDES_${PN} = "${OSCAMBIN}"
RDEPENDS = "${OSCAMRDEPENDS}"
#the three options below are not needed but makes it easier to trace the cmake generated cross compile files
#All generated files by cmake will be in a subdir called build_dir
OECMAKE_BUILDPATH = "${WORKDIR}/${MODULE}/build_dir"
OECMAKE_SOURCEPATH = "${WORKDIR}/${MODULE}"
EXTRA_OEMAKE = "-c ${OECMAKE_BUILDPATH} "

PV_arm = "1.0+gitr${SRCPV}"
OECMAKE_BUILDPATH_arm = "${S}/build"
OECMAKE_SOURCEPATH_arm = "${S}"
DEPENDS_append_arm = " su980-user-libs"

SRC_URI = "${URI}" 
# SRC_URI = "svn://streamboard.gmc.to/svn/oscam;module=trunk;proto=http;scmdata=keep;rev=${SRCREV}"
# SRC_URI = "svn://oscam.ump2002.net/svn/oscam;module=trunk;proto=http;rev=${SRCREV}"
#SRC_URI = "svn://www.oscam.to/svn/oscam;module=trunk;proto=http;scmdata=keep;rev=${SRCREV}"

S = "${WORKDIR}/${MODULE}"
S_arm = "${WORKDIR}/git"

#added byc cvr to add wright libusb location
#For dreambox it does compile but doesn't  work, cause kernel needs to be above 2.6.30. by boxes with such high kernel it should work
#You still can use smartreader with mouse protocol and set speed with ttys0 utility on the smargo it works up to 600 mhz
#with libusb it works up to 1000 mhz for seca 3 and viaccess.


do_generate_toolchain_file_append() {
	echo "set( OPTIONAL_INCLUDE_DIR \"${STAGING_DIR_HOST}/usr/include/PCSC\" CACHE STRING \"Some optional include directories\" )" >> ${WORKDIR}/toolchain.cmake
	echo "set( LIBUSBDIR ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/usr ) " >> ${WORKDIR}/toolchain.cmake \
}

do_generate_toolchain_file_arm() {
	echo "set( OSCAM_SYSTEM_NAME senbox ) " > ${WORKDIR}/toolchain.cmake 
	echo "set( CMAKE_SYSTEM_NAME Linux ) " >> ${WORKDIR}/toolchain.cmake 
	echo "set( CMAKE_C_COMPILER ${HOST_PREFIX}gcc ) " >> ${WORKDIR}/toolchain.cmake
	echo "set( CMAKE_SYSTEM_PROCESSOR arm ) " >> ${WORKDIR}/toolchain.cmake

	echo "#The following line is added by do_generate_toolchain_file_arm in oscam-bin.inc" > ${S}/CMakeLists_tmp.txt
	echo "link_directories( ${STAGING_LIBDIR}) " >> ${S}/CMakeLists_tmp.txt
	cat ${S}/CMakeLists.txt >> ${S}/CMakeLists_tmp.txt
	rm ${S}/CMakeLists.txt
	mv ${S}/CMakeLists_tmp.txt ${S}/CMakeLists.txt
}

EXTRA_OECMAKE += "\
	-DOSCAM_SYSTEM_NAME=Tuxbox \
	-DWEBIF=1 \
	-DWITH_STAPI=0 \
	-DHAVE_LIBUSB=0 \
	-${SSL} \
	-${PCSC} \
	"

inherit cmake update-alternatives

do_install_append() {
	mv "${D}${bindir}/oscam" "${D}${bindir}/${PN}"
}

ALTERNATIVE_NAME = "${OSCAMBIN}"
ALTERNATIVE_PATH = "${bindir}/${PN}"

