#Angstrom bootstrap image

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

ANGSTROM_EXTRA_INSTALL ?= ""

ZZAPSPLASH = ' ${@base_contains("MACHINE_FEATURES", "screen", "psplash-zap", "",d)}'

# task-base \
DEPENDS = " \
           ${SPLASH} \
           ${ZZAPSPLASH} \
	   "

# task-base \
IMAGE_INSTALL = " \
	    ${ANGSTROM_EXTRA_INSTALL} \
	    ${SPLASH} \
	    ${ZZAPSPLASH} \
	    "

IMAGE_LINGUAS = ""

inherit image
