SRCREV = "${AUTOREV}"
#SRCREV = "6145"
SRCREV_arm = "${AUTOREV}"
MODULE = "trunk"
OSCAMBIN = "oscam-experimental"
OSCAMDEPENS = "openssl"
OSCAMRDEPENDS ="openssl"
URI = "svn://oscam.to/svn/oscam;module=trunk;proto=http;scmdata=keep"
#URI_arm = "git://192.168.0.218:3121/opt/git/openpli/oscam.git;protocol=ssh;user=git;branch=master"
SSL = "DWITH_SSL=1"
PCSC = "DHAVE_PCSC=0"
ALTERNATIVE_PRIORITY = "20"
require oscam-bin.inc

