SUMMARY = "Tools for managing memory technology devices"
HOMEPAGE = "http://www.linux-mtd.infradead.org/"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://include/common.h;beginline=1;endline=17;md5=ba05b07912a44ea2bf81ce409380049c"

inherit autotools pkgconfig update-alternatives

DEPENDS = "zlib lzo e2fsprogs util-linux"

PV = "2.0.0"

SRCREV = "1bfee8660131fca7a18f68e9548a18ca6b3378a0"
SRC_URI = "git://git.infradead.org/mtd-utils.git;branch=master \
           file://add-exclusion-to-mkfs-jffs2-git-2.patch \
           file://fix-armv7-neon-alignment.patch \
           file://mtd-utils-fix-corrupt-cleanmarker-with-flash_erase--j-command.patch \
           file://0001-Fix-build-with-musl.patch \
           file://010-fix-rpmatch.patch \
           file://mkfs.ubifs-allow-output-file-creation-on-different-device.patch \
           file://no_deatach_check.patch \
           file://0001-nanddump-Truncate-empty-blocks-FF-from-dump.patch \
"

S = "${WORKDIR}/git"

# xattr support creates an additional compile-time dependency on acl because
# the sys/acl.h header is needed. libacl is not needed and thus enabling xattr
# regardless whether acl is enabled or disabled in the distro should be okay.
PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'xattr', d)}"
PACKAGECONFIG[xattr] = ",,acl,"

EXTRA_OEMAKE = "'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' 'CFLAGS=${CFLAGS} ${@bb.utils.contains('PACKAGECONFIG', 'xattr', '', '-DWITHOUT_XATTR', d)} -I${S}/include' 'BUILDDIR=${S}'"

# Use higher priority than corresponding BusyBox-provided applets
ALTERNATIVE_PRIORITY = "100"

ALTERNATIVE_${PN} = "flashcp flash_eraseall flash_lock flash_unlock nanddump nandwrite"
ALTERNATIVE_${PN}-ubifs = "ubiattach ubidetach ubimkvol ubirename ubirmvol ubirsvol ubiupdatevol"

ALTERNATIVE_LINK_NAME[flash_eraseall] = "${sbindir}/flash_eraseall"
ALTERNATIVE_LINK_NAME[nandwrite] = "${sbindir}/nandwrite"
ALTERNATIVE_LINK_NAME[nanddump] = "${sbindir}/nanddump"
ALTERNATIVE_LINK_NAME[ubiattach] = "${sbindir}/ubiattach"
ALTERNATIVE_LINK_NAME[ubiattach] = "${sbindir}/ubiattach"
ALTERNATIVE_LINK_NAME[ubidetach] = "${sbindir}/ubidetach"
ALTERNATIVE_LINK_NAME[ubimkvol] = "${sbindir}/ubimkvol"
ALTERNATIVE_LINK_NAME[ubirename] = "${sbindir}/ubirename"
ALTERNATIVE_LINK_NAME[ubirmvol] = "${sbindir}/ubirmvol"
ALTERNATIVE_LINK_NAME[ubirsvol] = "${sbindir}/ubirsvol"
ALTERNATIVE_LINK_NAME[ubiupdatevol] = "${sbindir}/ubiupdatevol"
ALTERNATIVE_LINK_NAME[flash_eraseall] = "${sbindir}/flash_eraseall"
ALTERNATIVE_LINK_NAME[flash_lock] = "${sbindir}/flash_lock"
ALTERNATIVE_LINK_NAME[flash_unlock] = "${sbindir}/flash_unlock"
ALTERNATIVE_LINK_NAME[flashcp] = "${sbindir}/flashcp"

do_install () {
	oe_runmake install DESTDIR=${D} SBINDIR=${sbindir} MANDIR=${mandir} INCLUDEDIR=${includedir}
}

PACKAGES =+ "mtd-utils-jffs2 mtd-utils-ubifs mtd-utils-misc"

FILES_mtd-utils-jffs2 = "${sbindir}/mkfs.jffs2 ${sbindir}/jffs2dump ${sbindir}/jffs2reader ${sbindir}/sumtool"
FILES_mtd-utils-ubifs = "${sbindir}/mkfs.ubifs ${sbindir}/ubi*"
FILES_mtd-utils-misc = "${sbindir}/nftl* ${sbindir}/ftl* ${sbindir}/rfd* ${sbindir}/doc* ${sbindir}/serve_image ${sbindir}/recv_image"

BBCLASSEXTEND = "native nativesdk"

# git/.compr.c.dep:46: warning: NUL character seen; rest of line ignored
# git/.compr.c.dep:47: *** missing separator.  Stop.
PARALLEL_MAKE = ""
