MODULE = "ChangeRootPassword"
SUMMARY = "Set/Change your box password"
DESCRIPTION = "Set/Change your box password"

inherit gitpkgv
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r1"

require conf/license/license-gplv2.inc

require openplugins-distutils.inc
