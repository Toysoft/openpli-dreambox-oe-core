SUMMARY = "Collection of enigma2 subtitles plugins"
HOMEPAGE = "https://github.com/mx3L/subssupport"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

RDEPENDS_${PN} = "python-requests python-xmlrpc python-compression python-codecs python-zlib python-difflib unrar"

inherit gitpkgv autotools-brokensep gettext

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/subssupport;protocol=https;branch=master"

S = "${WORKDIR}/git"

PV = "1.5.8+git${SRCPV}"
PKGV = "1.5.8+git${GITPKGV}"

FILES_${PN} = "${libdir}/enigma2/python/Plugins/Extensions/SubsSupport \
${localstatedir}/lib/subssupport"

do_install_append() {
    install -d ${D}${localstatedir}/lib/subssupport
}

python populate_packages_prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
}

