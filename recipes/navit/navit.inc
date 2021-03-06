DESCRIPTION = "Navit is a car navigation system with routing engine."
LICENSE = "GPL"
SECTION = "x11/applications"
DEPENDS = "glib-2.0 gtk+ qt4-x11-free gd gypsy librsvg-native"
RDEPENDS_${PN} = " navit-icons ${PN}-config "
RDEPENDS_${PN}_append_shr = " fsoraw"
RDEPENDS_${PN}-gui-qml = "qt4-plugin-imageformat-svg"
RRECOMMENDS_${PN} = "gpsd ${PN}-dbus ${PN}-speech-cmdline ${PN}-gui-internal ${PN}-graphics-gtk"
RSUGGESTS_${PN} = "flite espeak ${PN}-speech-dbus ${PN}-gui-gtk ${PN}-gui-qml ${PN}-graphics-sdl ${PN}-graphics-qt ${PN}-maptool"

PE = "1"
INC_PR = "r8"

inherit autotools

EXTRA_OECONF = " \
  --disable-binding-python \
  --disable-samplemap \
  --enable-avoid-unaligned \
  --enable-graphics-qt-qpainter \
  --enable-gui-qml \
  --enable-svg2png-scaling-flag=32 \
  --disable-speech-speech-dispatcher \
  --enable-cache-size=20971520 \
"

#  --enable-avoid-float \
#check for TARGET_FPU=soft and inform configure of the result so it can disable some floating points
require navit-fpu.inc
EXTRA_OECONF += "${@get_navit_fpu_setting(bb, d)}"

PACKAGES =+ "${PN}-maptool ${PN}-config ${PN}-dbus ${PN}-speech-cmdline ${PN}-speech-dbus ${PN}-gui-gtk ${PN}-gui-internal ${PN}-gui-qml ${PN}-graphics-sdl ${PN}-graphics-gtk ${PN}-graphics-qt"

EXTRA_AUTORECONF = " -I m4"

CONFFILES_${PN}-config += "${datadir}/navit/navit.default.xml \
                    ${datadir}/navit/navit.xml \
                    ${datadir}/navit/maps.xml \
                    ${datadir}/navit/osd.xml \
                    ${datadir}/navit/speech.xml \
                    ${datadir}/navit/plugins.xml \
                   "

SRC_URI += "file://navit.xml \
            file://maps.xml \
            file://osd.xml \
            file://speech.xml \
            file://plugins.xml \
            file://navit.desktop \
            file://navit.png \
           "

#Second launcher for shr
SRC_URI_append_shr = "file://navitD.desktop \
                      file://navitD.png \
                     "

FILES_${PN} += "${libdir}/${PN}/*/*.so"
FILES_${PN}-dbg += "${libdir}/${PN}/*/.debug"
FILES_${PN}-maptool = " ${bindir}/maptool "
FILES_${PN}-config = " ${datadir}/navit/*.xml "
FILES_${PN}-dbus = " ${datadir}/dbus-1/services/ ${libdir}/${PN}/binding/libbinding_dbus.so "
FILES_${PN}-speech-cmdline = " ${libdir}/${PN}/speech/libspeech_cmdline.so "
FILES_${PN}-speech-dbus = " ${libdir}/${PN}/speech/libspeech_dbus.so "
FILES_${PN}-gui-gtk = " ${libdir}/${PN}/gui/libgui_gtk.so "
FILES_${PN}-gui-qml = " ${libdir}/${PN}/gui/libgui_qml.so ${datadir}/navit/skins/ "
FILES_${PN}-gui-internal = " ${libdir}/${PN}/gui/libgui_internal.so "
FILES_${PN}-graphics-sdl = " ${libdir}/${PN}/graphics/libgraphics_sdl.so "
FILES_${PN}-graphics-qt = " ${libdir}/${PN}/graphics/libgraphics_qt_qpainter.so "
FILES_${PN}-graphics-gtk = " ${libdir}/${PN}/graphics/libgraphics_gtk_drawing_area.so "

do_configure_prepend() {
  #Remove xpm building, replaced by icons in own package
  sed -i 's/\(.*SUBDIRS.*\) xpm\( \|$\)\(.*\)/\1\2\3/g' ${S}/navit/Makefile.am
  # MOC=`$PKG_CONFIG QtGui --variable=moc_location` returns only /usr/bin/moc4 which is not available on hosts without Qt
  sed -i "s#MOC=#MOC=${STAGING_DIR_NATIVE}#g" ${S}/configure.in
}

do_install_append() {
        install -d ${D}${datadir}/applications/
        install -m 0644 ${WORKDIR}/navit.desktop ${D}${datadir}/applications/
        install -d ${D}${datadir}/pixmaps/
        install -m 0644 ${WORKDIR}/navit.png ${D}${datadir}/pixmaps/

	rm ${D}${libdir}/${PN}/*/*.la

        #Use split config
        mv ${D}${datadir}/navit/navit.xml ${D}${datadir}/navit/navit.default.xml
        install -m 0644 ${WORKDIR}/navit.xml ${D}${datadir}/navit/navit.xml
        install -m 0644 ${WORKDIR}/maps.xml ${D}${datadir}/navit/maps.xml
        install -m 0644 ${WORKDIR}/osd.xml ${D}${datadir}/navit/osd.xml
        install -m 0644 ${WORKDIR}/speech.xml ${D}${datadir}/navit/speech.xml
        install -m 0644 ${WORKDIR}/plugins.xml ${D}${datadir}/navit/plugins.xml
}

do_install_append_shr() {
        #Install second launcher for shr
        install -m 0644 ${WORKDIR}/navitD.desktop ${D}${datadir}/applications/
        install -m 0644 ${WORKDIR}/navitD.png ${D}${datadir}/pixmaps/
}
