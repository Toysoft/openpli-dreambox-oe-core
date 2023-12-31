SUMMARY = "VU+ DLNA Browser plugin"
DESCRIPTION = "VU+ DLNA Browser plugin"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"

S = "${WORKDIR}/git"
GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/dvbapp;branch=vuplus_experimental;protocol=https \
	file://enigma2-plugin-systemplugins-dlnabrowser_20130723.patch \
"

inherit gitpkgv
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

DEPENDS = "djmount fuse libupnp"
PROVIDES = "enigma2-plugin-systemplugins-dlnabrowser"
RDEPENDS_${PN} = "djmount fuse-utils fuse libupnp"
RRECOMMENDS_${PN} = "kernel-module-fuse"
FILES_${PN} = "${libdir}/enigma2/python/Plugins/Extensions/DLNABrowser/*"
PACKAGES = "${PN}"

do_install() {
	install -d ${D}${libdir}/enigma2/python/Plugins/Extensions/DLNABrowser
	install -m 0644 ${S}/lib/python/Plugins/Extensions/DLNABrowser/*.py ${D}${libdir}/enigma2/python/Plugins/Extensions/DLNABrowser
	python2 -O -m compileall ${D}${libdir}/enigma2/python/Plugins/
}
