DESCRIPTION = "Search the internet bases themoviedb.org/kinopoisk.ru"
HOMEPAGE = "https://github.com/Dima73/enigma2-plugin-extensions-tmbd"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=a1f8725511fa113a2b2a282860d4fc19"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/enigma2-plugin-extensions-tmbd;branch=master;protocol=https \
	file://YouTube.key \
"

S = "${WORKDIR}/git"

inherit gitpkgv distutils-openplugins

PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

RDEPENDS_${PN} = " \
	python-twisted-web \
	python-xml \
	python-shell \
	python-misc \
	python-html \
	python-subprocess \
	python-unixadmin \
	python-lxml \
	"

CONFFILES = "${sysconfdir}/enigma2/YouTube.key"

do_install_append() {
	install -d ${D}${sysconfdir}/enigma2
	install -m 0644 ${WORKDIR}/YouTube.key ${D}${sysconfdir}/enigma2/YouTube.key
}
