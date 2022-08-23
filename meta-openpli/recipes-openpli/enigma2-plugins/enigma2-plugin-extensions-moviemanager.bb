SUMMARY = "Plugin for copy or move more files at once"
DESCRIPTION = "Plugin for copy or move more files at once"
MAINTAINER = "ims"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9432c1f3d564948269193fd19a0ad0fd"

inherit gitpkgv
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

SRC_URI = "git://gitlab.com/jack2015/MovieManager.git;protocol=https"

S="${WORKDIR}/git"

inherit distutils-openplugins
