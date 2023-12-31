DESCRIPTION = "DPF access extension module"
MAINTAINER = "https://sourceforge.net/projects/pydpf/"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=6a256fd20875b5cf06888bbcbe1a21aa"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/pydpflib;protocol=https;branch=master"

DEPENDS = "libusb"

S = "${WORKDIR}/git"

inherit gitpkgv setuptools

PV = "0.14+git${SRCPV}"
PKGV = "0.14+git${GITPKGV}"
PR = "r0"

do_compile_prepend() {
    $MAKE -C ./dpf-ax/dpflib all
}
