SUMMARY = "Flasher for NRF52 based BLE Transceiver"
require conf/license/openpli-gplv2.inc

SRC_URI = "http://dreamboxupdate.com/download/opendreambox/2.6.0/${BPN}/${PV}/${PACKAGE_ARCH}/3649233eb10eaef04838f5021e0e4ca7/${BPN}_${PV}_${PACKAGE_ARCH}.tar.xz"

SRC_URI[sha256sum] = "e9f119af9bcadf4a74d91ed8ebccbf86bc423a2f38a32a46d7310cbea8fd8720"
SRC_URI[cortexa15hf-neon-vfpv4.md5sum] = "3649233eb10eaef04838f5021e0e4ca7"
SRC_URI[cortexa15hf-neon-vfpv4.sha256sum] = "e9f119af9bcadf4a74d91ed8ebccbf86bc423a2f38a32a46d7310cbea8fd8720"

COMPATIBLE_MACHINE = "^(dm900|dm920)$"

inherit opendreambox-precompiled-binary-new

INSANE_SKIP_${PN} += " installed-vs-shipped already-stripped"
