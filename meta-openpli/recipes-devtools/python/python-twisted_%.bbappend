RDEPENDS_${PN}-core += "${PYTHON_PN}-service-identity"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
           file://fix-writing-after-channel-is-closed.patch \
           file://twisted-17.9.0-python-27-utf-8-fix.patch \
           file://0001-Revert-Prevent-CRLF-injections-described-in-CVE-2019.patch \
"

FILES_${PN}-src += " \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*.py \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*.py \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*/*.py \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*/*/*.py \
"

FILES_${PN}-dbg += " \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*.egg-info \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*/test \
"
