SRCREV = "6573"
SRCREV_arm = "${AUTOREV}"
MODULE = "trunk"
OSCAMBIN = "oscam-stable"
OSCAMDEPENS = "openssl"
OSCAMRDEPENDS ="openssl"
URI = "svn://streamboard.gmc.to/svn/oscam;module=trunk;proto=http;scmdata=keep;rev=${SRCREV}"
URI_arm = "git://192.168.0.218:3121/opt/git/openpli/oscam.git;protocol=ssh;user=git;branch=master"
SSL="DWITH_SSL=0"
PCSC = "DHAVE_PCSC=0"
ALTERNATIVE_PRIORITY = "10"
require oscam-bin.inc

