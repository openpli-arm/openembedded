require openpli-image.bb

#enigma2-plugin-pli-ppanel 
ENIGMA2_PLUGINS = " \
				enigma2-plugin-pli-softcamsetup \
				enigma2-plugin-extensions-audiosync \
				enigma2-plugin-systemplugins-softwaremanager \
				enigma2-plugin-systemplugins-positionersetup \
				enigma2-plugin-extensions-cutlisteditor \
				enigma2-plugin-systemplugins-satfinder \
				enigma2-plugin-systemplugins-videotune \
				enigma2-plugin-extensions-mediascanner \
				enigma2-plugin-extensions-webinterface \
				enigma2-plugin-extensions-graphmultiepg \
				enigma2-plugin-systemplugins-skinselector \
				enigma2-plugin-extensions-pictureplayer \
#				enigma2-plugin-extensions-mediaplayer \
#				enigma2-plugin-systemplugins-networkbrowser \
				enigma2-plugin-systemplugins-fastscan \
				enigma2-plugin-systemplugins-osdpositionsetup \
				enigma2-plugin-extensions-oscamstatus \
				enigma2-plugin-extensions-factorytest \
				${@base_contains("MACHINE_FEATURES", "nohotplug", "", "enigma2-plugin-systemplugins-hotplug", d)} \
				${@base_contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "frontprocessor", "enigma2-plugin-systemplugins-frontprocessorupgrade" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "hdtv", "enigma2-plugin-systemplugins-videomode" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "dvbapi5", "usbtunerhelper" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "3dtv", "enigma2-plugin-systemplugins-osd3dsetup" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "wifi", "enigma2-plugin-systemplugins-wirelesslan", "", d)} \
				"
#Archer:Add oscam plugin in openpli image 
ENIGMA2_PLUGINS_append_arm = "enigma2-plugin-softcams-oscam-stable su980-uboot-tools"
DEPENDS += "enigma2 enigma2-plugins enigma2-pliplugins"

ENIGMA2_OPTIONAL = " \
				enigma2-skins \
				enigma2-plugins \
				enigma2-pliplugins \
				enigma2-plugin-extensions-tuxcom \
				enigma2-plugin-others-nano \
				enigma2-plugin-extensions-tuxterm \
				${@base_contains("MACHINE_FEATURES", "usbhost", "enigma2-plugin-drivers-usbserial" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "dvbapi5", "enigma2-plugin-drivers-dvb-usb-dib0700" , "", d)} \
				enigma2-plugin-security-firewall \
				enigma2-plugin-extensions-xmltvimport \
				enigma2-plugin-extensions-ambx \
				enigma2-plugin-extensions-project-valerie \
				channelsettings-enigma2-meta \
				picons-enigma2-meta \
#				softcams-enigma2-meta \
				dvbsnoop \
				"
#Archer:add oscam-stable in openpli iamge
IMAGE_INSTALL += " \
				enigma2 \
				${ENIGMA2_PLUGINS} \
				enigma2-streamproxy \
				aio-grab \
				tuxbox-common \
				libavahi-client \
				settings-autorestore \
				libstdc++\
				oscam-stable\
				dvb-apps\
				ntfs-3g\
				"

OPTIONAL_PACKAGES += " \
			${ENIGMA2_OPTIONAL} \
			"

export IMAGE_BASENAME = "openpli-enigma2"

LOADER_VER := "20121017"
LOADER_NAME := "uldr_${LOADER_VER}"
BOOT_VER := "20121104"
BOOT_NAME := "u-boot_${BOOT_VER}"
E2_KERNEL_VER := "20121113"
E2_KERNEL_NAME := "vmlinux_${E2_KERNEL_VER}"
ANDROID_VER := "20121103"
ANDROID_FILE_NAME := "software_package_${ANDROID_VER}_Sen5"
UPGRADE_SCRIPT_VER := "20121010"
UPGRADE_SCRIPT_NAME := "upgrade_${UPGRADE_SCRIPT_VER}"

POPULATE_UPGRADE_SCRIPT = "\
	mkdir -p su980;\
	cp ${DL_DIR}/${UPGRADE_SCRIPT_NAME}.scr su980/upgrade.scr;\
"
POPULATE_BOOTLOADER = "\
	mkdir -p su980;\
	cp ${DL_DIR}/${BOOT_NAME}.bin su980/boot.bin;\
	cp ${DL_DIR}/${LOADER_NAME}.bin su980/loader.bin;\
"
POPULATE_E2 = "\
	mkdir -p su980/e2;\
	cp ${DL_DIR}/${E2_KERNEL_NAME}.bin su980/e2/e2-kernel.bin;\
	cp ${IMAGE_NAME}.rootfs.jffs2 su980/e2/e2-rootfs.jffs2;\
"
POPULATE_ANDROID = "\
	mkdir -p su980/android;\
	cp ${ANDROID_FILE_NAME}/vmlinux.bin su980/android/android-kernel.bin;\
    cp ${ANDROID_FILE_NAME}/rootfs.arm.ubifs.nand su980/android/android-rootfs.ubifs;\
	cp ${ANDROID_FILE_NAME}/appfs.ubifs.nand su980/android/android-appfs.ubifs;\
    cp ${ANDROID_FILE_NAME}/Readme.txt su980/android/readme.txt;\
"
MACHINE_POSTPROCESS_COMMAND = "\
	cd ${DL_DIR};\
	if [ ! -e ${UPGRADE_SCRIPT_NAME}.scr ];then\
		wget http://192.168.0.218/download/images/bootloader/${UPGRADE_SCRIPT_NAME}.scr;\
	fi;\
	if [ ! -e ${BOOT_NAME}.bin ];then\
		wget http://192.168.0.218/download/images/bootloader/${BOOT_NAME}.bin;\
	fi;\
	if [ ! -e ${LOADER_NAME}.bin ];then\
		wget http://192.168.0.218/download/images/bootloader/${LOADER_NAME}.bin;\
	fi;\
	if [ ! -e ${E2_KERNEL_NAME}.bin ];then\
		wget http://192.168.0.218/download/images/e2/${E2_KERNEL_NAME}.bin;\
	fi;\
	if [ ! -e ${ANDROID_FILE_NAME}.tar.gz ];then\
		wget http://192.168.0.218/download/images/android/${ANDROID_FILE_NAME}.tar.gz;\
	fi;\
	cd ${DEPLOY_DIR_IMAGE};\
	rm -rf release;\
	mkdir release;\
	rm -rf su980;\
	rm -rf ${ANDROID_FILE_NAME};\
	export VERSION=`date +%Y%m%d`;\
	cp openpli-enigma2-2.0-dm800.rootfs.tar.gz release/openpli-enigma2-2.0-su980-${VERSION}.rootfs.tar.gz;\
	tar -xzvf ${DL_DIR}/${ANDROID_FILE_NAME}.tar.gz;\
#Generate images contains e2 and android \
	${POPULATE_UPGRADE_SCRIPT}\
	${POPULATE_BOOTLOADER}\
	${POPULATE_E2}\
	${POPULATE_ANDROID}\
	tar -czvf release/su980-e2-android-${VERSION}.tar.gz su980;\
	rm -rf su980;\
#Generate e2 only image \
	${POPULATE_UPGRADE_SCRIPT}\
	${POPULATE_BOOTLOADER}\
	${POPULATE_E2}\
	tar -czvf release/su980-e2-${VERSION}.tar.gz su980;\
	rm -rf su980;\
#Generate android only image\
	${POPULATE_UPGRADE_SCRIPT}\
	${POPULATE_BOOTLOADER}\
	${POPULATE_ANDROID}\
	tar -czvf release/su980-android-${VERSION}.tar.gz su980;\
	rm -rf su980;\
#remove temp files\
	rm -rf ${ANDROID_FILE_NAME}\
"
