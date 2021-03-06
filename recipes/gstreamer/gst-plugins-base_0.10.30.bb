require gst-plugins.inc

SRC_URI_append_openpli = " file://samihack.patch"

PR = "${INC_PR}.0"

PROVIDES += "gst-plugins"

# gst-plugins-base only builds the alsa plugin
# if alsa has been built and is present.  You will
# not get an error if this is not present, just 
# a missing alsa plugin
DEPENDS += "cdparanoia pango libtheora alsa-lib libsm virtual/libx11 freetype gnome-vfs libxv"
DEPENDS_openpli += "alsa-lib"
EXTRA_OECONF_openpli += "--disable-theora --disable-pango --with-audioresample-format=int"

