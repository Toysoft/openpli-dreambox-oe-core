SUMMARY = "mediatek 7603u v1.12"
HOMEPAGE = "www.mediatek.com"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
require conf/license/license-gplv2.inc

inherit module

SRCREV = "${AUTOREV}"
GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/mt7603u.git;branch=master;protocol=https"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR} KDIR=${STAGING_KERNEL_DIR}"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
    install -m 0644 ${S}/os/linux/mt7603u_sta.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
    install -d ${D}${sysconfdir}/Wireless/RT2870STA
    install -m 0644 ${S}/conf/MT7603USTA.dat ${D}${sysconfdir}/Wireless/RT2870STA/MT7603USTA.dat
}

FILES_${PN}_append = "${sysconfdir}/Wireless"

