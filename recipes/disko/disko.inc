DESCRIPTION = "Disko is an application framework, that can be used to develop GUI applications for embedded devices."
LICENSE = "GPL"

DEPENDS = "sqlite3 libpng jpeg curl alsa-lib freetype libxml2 libsigc++-2.0 openssl"

inherit scons pkgconfig

ARM_INSTRUCTION_SET = "arm"
LD = ${CXX}

#workaround for disko which creates .pc inside install
do_install_append () {

for i in `find ${S}/ ${D}/${libdir}/pkgconfig -name "*.pc" -type f` ; do \
            sed -i -e 's:${D}::g' $i
        done
}

scons_do_stage_append () {
        STAGE_PKG_SEARCHPATH="${S}/ ${@['','${WORKDIR}/staging-pkg/'][bb.data.inherits_class('packaged-staging',d)]}"
        for i in `find ${STAGE_PKG_SEARCHPATH} -name "*.pc" -type f` ; do \
            sed -i -e 's:${STAGING_DIR_HOST}::g' $i
        done
}
