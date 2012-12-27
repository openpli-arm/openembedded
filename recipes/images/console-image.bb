#Angstrom bootstrap image
require console-base-image.bb
# task-base \
DEPENDS += "ffmpeg \
			libsdl "

# task-base \
IMAGE_INSTALL += "ffmpeg \
				libsdl "

EXTRA_IMAGECMD_jffs2 = " hello world"
IMAGE_CMD_jffs2 = "echo"


export IMAGE_BASENAME = "console-image"
