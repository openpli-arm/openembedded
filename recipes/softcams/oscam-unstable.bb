SRCREV = "${AUTOREV}"
SRCREV_arm = "${AUTOREV}"
MODULE = "1.10"
OSCAMBIN = "oscam-unstable"
OSCAMDEPENS = "openssl"
OSCAMRDEPENDS ="openssl"
URI = "svn://oscam.to/svn/oscam/tags;module=1.10;proto=http;scmdata=keep"
#URI_arm = "git://192.168.0.218:3121/opt/git/openpli/oscam.git;protocol=ssh;user=git;branch=master"
SSL = "DWITH_SSL=1"
PCSC = "DHAVE_PCSC=0"
ALTERNATIVE_PRIORITY = "10"
require oscam-bin.inc

