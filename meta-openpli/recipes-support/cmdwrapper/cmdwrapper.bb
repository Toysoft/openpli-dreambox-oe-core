DESCRIPTION = "cmd wrapper"
MAINTAINER = "Open Vision Developers"

require conf/license/openpli-gplv2.inc

inherit gitpkgv

PV = "2.1+git${SRCPV}"
PKGV = "2.1+git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/cmdwrapper;protocol=https;branch=master"

S = "${WORKDIR}/git/"

SOURCE_FILES = "src/main.c src/cmdwrapper.h src/cmdwrapper.c"

do_compile() {
    ${CC} ${SOURCE_FILES} -D_FILE_OFFSET_BITS=64 -D_LARGEFILE64_SOURCE=1 -D_LARGEFILE_SOURCE -I${S}/src -I${D}/${libdir} -I${D}/${includedir} -o cmdwrapper ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/cmdwrapper ${D}${bindir}
}
