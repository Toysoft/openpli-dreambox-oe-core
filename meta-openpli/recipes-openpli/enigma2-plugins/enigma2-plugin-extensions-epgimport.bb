SUMMARY = "Imports XMLTV and epg.dat files into the EPG cache of enigma2"
DESCRIPTION = "Imports XMLTV and epg.dat files into the EPG cache of enigma2"
MAINTAINER = "OpenPLi team"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../etc/epgimport/readme.txt;startline=1;endline=4;md5=c162054328d930d453543efef81be1d8"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/enigma2-plugin-extensions-epgimport;branch=master;protocol=https"

S = "${WORKDIR}/git/src"

inherit distutils-openplugins

DEPENDS = "python"
RDEPENDS_${PN} = "python-compression python-shell python-lzma python-pkgutil"
RRECOMMENDS_${PN} = "${PN}-rytec"
PACKAGES = "${PN}-dbg ${PN}"

RREPLACES_${PN} = "enigma2-plugin-extensions-xmltvimport"
RCONFLICTS_${PN} = "enigma2-plugin-extensions-xmltvimport"

PLUGIN = "EPGImport"

FILES_${PN} = "${libdir}/enigma2/python"
FILES_${PN}-dbg = "${libdir}/enigma2/python/Plugins/Extensions/${PLUGIN}/.debug /usr/src/debug"

pkg_postinst_${PN}() {

	if [ ! -f $D${sysconfdir}/image-version ]
	then
		# when slipstreaming, don't patch enigma
		exit 0
	fi
	if grep -q PLi $D${sysconfdir}/image-version
	then
		# PLi needs no patch...
		true
	else
		[ -f $D${bindir}/enigma2.sh.xmltvbak ] || {
			cp $D${bindir}/enigma2.sh $D${bindir}/enigma2.sh.xmltvbak
			sed '3ipython $D${libdir}/enigma2/python/Plugins/Extensions/EPGImport/boot.py' $D${bindir}/enigma2.sh.xmltvbak > $D${bindir}/enigma2.sh
		}
	fi
}

pkg_prerm_${PN}() {
	if [ -f ${bindir}/enigma2.sh.xmltvbak ] ; then
		mv -f ${bindir}/enigma2.sh.xmltvbak ${bindir}/enigma2.sh
	fi
}
