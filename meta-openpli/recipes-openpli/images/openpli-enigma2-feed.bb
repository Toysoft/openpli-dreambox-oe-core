# Creates the "feed", packages not required for the image
# but that should be built for the feed so that other
# components may use them and install on demand.

# We have a GPLv2 license for this recipe...
require conf/license/openpli-gplv2.inc

# Depend on the image, so that it gets build
DEPENDS = "openpli-enigma2-image"

OPTIONAL_PACKAGES_BROKEN = ""
OPTIONAL_PACKAGES ?= ""
OPTIONAL_BSP_PACKAGES ?= ""
OPTIONAL_PACKAGES += " \
	autofs \
	astra-sm \
	autossh \
	bitratecalc \
	ccid \
	curl \
	dvblast \
	dvbsnoop \
	dvblast \
	djmount \
	edid-decode \
	evtest \
	exfat-utils \
	exteplayer3 \
	ffmpeg \
	gstplayer \
	hddtemp \
	hdparm \
	htop \
	hlsdl \
	inadyn-mt \
	inetutils \
	inotify-tools \
	iperf3 \
	iproute2 \
	iputils \
	libbluray \
	libsdl2 \
	libudfread \
	libcrypto-compat \
	mc \
	minisatip \
	mtd-utils \
	mtools \
	nfs-utils \
	nano \
	net-snmp \
	net-tools \
	openssh \
	openvpn \
	openresolv \
	openssh \
	pv \
	p7zip \
	parted \
	procps \
	pyload \
	samba \
	ushare \
	wget \
	yafc \
	zeroconf \
	python-attr \
	python-attrs \
	python-beautifulsoup4 \
	python-future \
	python-futures \
	python-isodate \
	python-js2py \
	python-lxml \
	python-mechanize \
	python-ntplib \
	python-pexpect \
	python-pip \
	python-pyasn1-modules \
	python-requests \
	python-singledispatch \
	python-ujson \
	python-websocket-client \
	${OPTIONAL_BSP_PACKAGES} \
	"

OPTIONAL_BSP_ENIGMA2_PACKAGES ?= ""
ENIGMA2_OPTIONAL = " \
	channelsettings-enigma2-meta \
	dvb-usb-drivers-meta \
	network-usb-drivers-meta \
	enigma2-plugin-drivers-usbserial \
	enigma2-plugin-drivers-exfat \
	enigma2-plugin-drivers-ntfs-3g \
	${@bb.utils.contains_any("MACHINE", "dm800se dm800sev2", " \
	enigma2-plugin-softcams-cccam-v209 \
	enigma2-plugin-softcams-cccam-v221 \
	enigma2-plugin-softcams-cccam-v230 \
	enigma2-plugin-softcams-cccam-v232 \
	enigma2-plugin-softcams-cccam-v238 \
	enigma2-plugin-softcams-mgcamd-v135a \
	enigma2-plugin-softcams-mgcamd-v145c \
	", "", d)} \
	${@bb.utils.contains_any("MACHINE", "dm900 dm920", " \
	enigma2-plugin-softcams-cccam-v232-arm \
	enigma2-plugin-softcams-cccam-v238-arm \
	enigma2-plugin-softcams-mgcamd-v135a-arm \
	", "", d)} \
	enigma2-plugin-extensions-keyadder \
	enigma2-plugin-extensions-weathermsn \
	enigma2-plugin-extensions-weatherplugin \
	enigma2-plugin-extensions-backupsuite \
	enigma2-plugin-extensions-blurayplayer \
	enigma2-plugin-extensions-vpnmanager \
	enigma2-plugin-extensions-e2iplayer \
	enigma2-plugin-extensions-epgimport \
	enigma2-plugin-extensions-filecommander \
	enigma2-plugin-extensions-fontinfo \
	enigma2-plugin-extensions-flashexpander \
	enigma2-plugin-extensions-historyzapselector \
	enigma2-plugin-extensions-managerautofs \
	enigma2-plugin-extensions-modifyplifullhd \
	enigma2-plugin-extensions-moviemanager \
	enigma2-plugin-systemplugins-mountmanager \
	enigma2-plugin-extensions-pluginskinmover \
	enigma2-plugin-extensions-openmultiboot \
	enigma2-plugin-extensions-refreshbouquet \
	enigma2-plugin-extensions-subssupport \
	enigma2-plugin-extensions-sdgradio \
	enigma2-plugin-systemplugins-signalfinder \
	enigma2-plugin-extensions-managerautofs \
	enigma2-plugin-extensions-hdmitest \
	enigma2-plugin-extensions-xstreamity \
	enigma2-plugin-extensions-tmbd \
	enigma2-plugin-extensions-vcs \
	enigma2-plugin-extensions-xmodem \
	enigma2-plugin-extensions-youtube \
	enigma2-plugin-extensions-cacheflush \
	enigma2-plugin-extensions-openwebif \
	enigma2-plugin-extensions-oscamstatus \
	${@bb.utils.contains("MACHINE", "dm800se", "enigma2-plugin-softcams-oscam-dm800se", "", d)} \
	${@bb.utils.contains("MACHINE", "dm800sev2", "enigma2-plugin-softcams-oscam-dm800sev2", "", d)} \
	${@bb.utils.contains("MACHINE", "dm900", "enigma2-plugin-softcams-oscam-dm900", "", d)} \
	${@bb.utils.contains("MACHINE", "dm920", "enigma2-plugin-softcams-oscam-dm920", "", d)} \
	${@bb.utils.contains_any("MACHINE", "dm900 dm920", "enigma2-plugin-softcams-wicardd", "", d)} \
	enigma2-plugin-skins-pli-hd \
	enigma2-plugin-skins-pli-hd1 \
	enigma2-plugin-skins-pli-hd2 \
	enigma2-plugin-skins-pli-fullnighthd \
	enigma2-plugin-skins-pli-fullhd \
	enigma2-plugin-skins-mx-hq7 \
	enigma2-plugin-skins-mx-hq9w \
	enigma2-plugin-skins-mx-hq10 \
	enigma2-plugin-skins-mx-titaniumc \
	enigma2-plugin-skins-mx-black \
	enigma2-plugin-skins-metropolishd \
	enigma2-plugin-skins-simple-gray-hd \
	enigma2-plugin-security-firewall \
	enigma2-plugin-systemplugins-crossepg \
	enigma2-plugin-systemplugins-extnumberzap \
	enigma2-plugin-systemplugins-hrtunerproxy \
	enigma2-plugin-systemplugins-newvirtualkeyboard \
	enigma2-plugin-systemplugins-signalfinder \
	enigma2-plugin-systemplugins-mountmanager \
	enigma2-plugin-systemplugins-terrestrialscan \
	enigma2-plugin-systemplugins-misplslcnscan \
	enigma2-plugin-systemplugins-serviceapp \
	enigma2-plugin-systemplugins-exteplayer3 \
	enigma2-plugin-systemplugins-servicemp3 \
	enigma2-plugins \
	packagegroup-openplugins \
	${OPTIONAL_BSP_ENIGMA2_PACKAGES} \
	" 

DEPENDS += "${OPTIONAL_PACKAGES} ${ENIGMA2_OPTIONAL}"
