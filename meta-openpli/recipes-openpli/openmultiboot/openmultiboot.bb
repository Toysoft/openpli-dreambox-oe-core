SUMMARY = "Multi boot loader for enigma2"
MAINTAINER = "oe-alliance"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "freetype"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/openmultiboot;protocol=https;branch=master"

inherit autotools-brokensep pkgconfig

S = "${WORKDIR}/git"

#dreambox /dev/mtd boot partition with kernel
MTD_KERNEL_dm500hd = "mtd2"
MTD_KERNEL_dm500hdv2 = "mtd2"
MTD_KERNEL_dm520 = "mtd2"
MTD_KERNEL_dm800se = "mtd2"
MTD_KERNEL_dm800sev2 = "mtd2"
MTD_KERNEL_dm820 = "mtd2"
MTD_KERNEL_dm7020hd = "mtd2"
MTD_KERNEL_dm7020hdv2 = "mtd2"
MTD_KERNEL_dm7080 = "mtd2"
MTD_KERNEL_dm8000 = "mtd2"
MTD_KERNEL_dm900 = "mmcblk0p1"
MTD_KERNEL_dm920 = "mmcblk0p1"

EXTRA_OEMAKE = " \
    'CFLAGS=${CFLAGS} \
    -I=${includedir}/freetype2 \
    -DOMB_DEFAULT_TIMER=10 \
    ${@bb.utils.contains("MACHINE_FEATURES", "textlcd", "-DOMB_HAVE_TEXTLCD" , "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "ombv1", "-DOMB_DREAMBOX", "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "ombv2", "-DOMB_MMCBLK", "", d)} \
    -DOMB_KERNEL_MTD=\"/dev/${MTD_KERNEL}\"' \
    'LDFLAGS= -lfreetype ${LDFLAGS}' \
    "

do_install() {
    install -d ${D}${base_sbindir}
    install -m 755 ${S}/src/open_multiboot ${D}${base_sbindir}
}

pkg_preinst_${PN}() {
#!/bin/sh
if mountpoint -q ${libdir}/enigma2/python/Plugins/Extensions/OpenMultiboot; then
    echo "openMultiBoot will only install on main image."
    echo "Child image is running - canceling installation!"
    sleep 3
    exit 1
else
    echo "Main image is running - proceeding installation..."
    exit 0
fi
}

pkg_postrm_${PN}() {
#!/bin/sh
rm -rf /sbin/init
ln -s /sbin/init.sysvinit /sbin/init
rm -rf /sbin/open-multiboot-branding-helper.py
exit 0
}

pkg_postinst_${PN}_openbh() {
}
