DESCRIPTION = "Frequency lists"
MAINTAINER = "PLi team"

require conf/license/openpli-gplv2.inc
inherit allarch gitpkgv

PV = "3+git${SRCPV}"
PKGV = "3+git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/tuxbox-xml;protocol=https;branch=master"

S = "${WORKDIR}/git/xml"
FILES_${PN} = "${sysconfdir}/tuxbox/* ${prefix}/* ${localstatedir}/*"

do_compile[noexec] = "1"

do_install() {
	install -m 0755 -d "${D}${sysconfdir}"
	install -m 0755 -d "${D}${sysconfdir}/tuxbox"
	install -m 0755 -d "${D}${sysconfdir}/tuxbox/scce"
	install -m 0755 -d "${D}${prefix}/keys"
	install -m 0755 -d "${D}${bindir}"
	install -m 0755 -d "${D}${localstatedir}"

	install -m 0644 "${S}"/*.xml "${D}${sysconfdir}/tuxbox"

	ln -s "${sysconfdir}/tuxbox/scce"	"${D}${localstatedir}/"
	ln -s "${prefix}/keys"			"${D}${localstatedir}/"
	ln -s "${bindir}"			"${D}${localstatedir}/"
	ln -s "${sysconfdir}"			"${D}${localstatedir}/"
}
