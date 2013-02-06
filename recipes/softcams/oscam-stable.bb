SRCREV = "6573"
SRCREV_arm = "${AUTOREV}"
MODULE = "trunk"
OSCAMBIN = "oscam-stable"
OSCAMDEPENS = "openssl"
OSCAMRDEPENDS ="openssl"
URI = "svn://streamboard.gmc.to/svn/oscam;module=trunk;proto=http;scmdata=keep;rev=${SRCREV}"
URI_arm = "https://github.com/openpli-arm/oscam-stable.git;protocol=git;branch=master"
SSL="DWITH_SSL=0"
PCSC = "DHAVE_PCSC=0"
ALTERNATIVE_PRIORITY = "10"
require oscam-bin.inc

