DESCRIPTION = "Open Vision branding lib"
MAINTAINER = "Open Vision Developers"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "python"

require conf/license/openpli-gplv2.inc

inherit autotools-brokensep gitpkgv pythonnative

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI="${GIT_SITE}/branding-module;protocol=https;branch=master"

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    --with-visionversion="9.3" \
    --with-visionrevision="${VISIONREVISION}" \
    --with-boxbrand="${BOX_BRAND}" \
    --with-oever="${VISIONVERSION}" \
    --with-distro="${DISTRO_NAME}" \
    --with-boxtype="${MACHINE}" \
    --with-brandoem="${BOX_BRAND}" \
    --with-machinebrand="${BOX_BRAND}" \
    --with-machinename="${MACHINE}" \
    --with-machinebuild="${MACHINE}" \
    --with-machinemake="${MACHINE}" \
    --with-imageversion="${DISTRO_VERSION}" \
    --with-imagebuild="${BUILD_VERSION}" \
    --with-imagedevbuild="${DEVELOPER_BUILD_VERSION}" \
    --with-imagetype="${DISTRO_TYPE}" \
    --with-feedsurl="${DISTRO_FEED_URI}" \
    --with-imagedir="${IMAGEDIR}" \
    --with-imagefs="${IMAGE_FSTYPES}" \
    --with-mtdbootfs="${MTD_BOOTFS}" \
    --with-mtdrootfs="${MTD_ROOTFS}" \
    --with-mtdkernel="${MTD_KERNEL}" \
    --with-rootfile="${ROOTFS_FILE}" \
    --with-kernelfile="${KERNEL_FILE}" \
    --with-mkubifs="${MKUBIFS_ARGS}" \
    --with-ubinize="${UBINIZE_ARGS}" \
    --with-arch="${DEFAULTTUNE}" \
    --with-display-type="${DISPLAY_TYPE}" \
    --with-transcoding="${TRANSCODING}" \
    --with-multitranscoding="${MULTITRANSCODING}" \
    "

FILES_${PN} = "${libdir}/enigma2/python/*.so"
FILES_${PN}-dev += "${libdir}/enigma2/python/*.la"
FILES_${PN}-staticdev += "${libdir}/enigma2/python/*.a"
FILES_${PN}-dbg += "${libdir}/enigma2/python/.debug"
