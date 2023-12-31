SUMMARY = "PLi-HD skin"
MAINTAINER = "littlesat"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://usr/share/enigma2/PLi-HD/skin.xml;beginline=3;endline=8;md5=1d560d35b9194281a488eb3a32d9c8bf"
require skin-url.inc

inherit gitpkgv allarch

PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"

FILES_${PN} = "${datadir}/enigma2/"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
	install -d ${D}${datadir}/enigma2/PLi-HD
	cp -r ${S}${datadir}/enigma2/PLi-HD/* ${D}${datadir}/enigma2/PLi-HD
	chmod -R a+rX ${D}${datadir}/enigma2/
}
