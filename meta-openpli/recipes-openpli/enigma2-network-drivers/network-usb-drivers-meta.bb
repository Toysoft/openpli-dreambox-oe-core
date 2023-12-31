SUMMARY = "meta file for USB Network drivers"
inherit packagegroup

require conf/license/license-gplv2.inc

DEPENDS = "\
    enigma2-plugin-drivers-network-usb-asix \
    enigma2-plugin-drivers-network-usb-ath9k-htc \
    enigma2-plugin-drivers-network-usb-smsc75xx \
    enigma2-plugin-drivers-network-usb-carl9170 \
    enigma2-plugin-drivers-network-usb-rt2500 \
    enigma2-plugin-drivers-network-usb-rt2800 \
    enigma2-plugin-drivers-network-usb-zd1211rw \
    enigma2-plugin-drivers-network-usb-rtl8814au \
    enigma2-plugin-drivers-network-usb-r8723a \
    enigma2-plugin-drivers-network-usb-rt8723bs \
    enigma2-plugin-drivers-network-usb-mt7601u \
    enigma2-plugin-drivers-network-usb-mt7610u \
    enigma2-plugin-drivers-network-usb-rt73 \
    enigma2-plugin-drivers-network-usb-rt3573 \
    enigma2-plugin-drivers-network-usb-rt5572 \
    enigma2-plugin-drivers-network-usb-r8188eu \
    enigma2-plugin-drivers-network-usb-r8712u \
    ${@bb.utils.contains_any("MACHINE", "dm520 dm820 dm7080", "", "enigma2-plugin-drivers-network-usb-rtl8189es", d)} \
    enigma2-plugin-drivers-network-usb-rtl8192eu \
    enigma2-plugin-drivers-network-usb-rtl8192cu \
    enigma2-plugin-drivers-network-usb-rtl8192fu \
    enigma2-plugin-drivers-network-usb-rtl8822bu \
    enigma2-plugin-drivers-network-usb-rtl8187 \
    enigma2-plugin-drivers-network-usb-rtl8821cu \
    enigma2-plugin-drivers-network-usb-rt3070 \
    ${@bb.utils.contains_any("MACHINE", "dm900 dm920", "enigma2-plugin-drivers-network-usb-rtl8812au", "", d)} \
    "
