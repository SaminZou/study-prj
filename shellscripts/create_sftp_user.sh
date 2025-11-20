#!/bin/bash
set -euo pipefail

############################################################
# 使用方法：
# sudo bash create_sftp_user.sh <真实日志目录> <sftp用户> <用户密码>
# 例如：
# sudo bash create_sftp_user.sh /data/nfs/test testlog testpasswd
############################################################

if [ "$(id -u)" -ne 0 ]; then
    echo "请以 root 或 sudo 运行此脚本"
    exit 1
fi

if [ "$#" -ne 3 ]; then
    echo "Usage: $0 <REAL_DIR> <SFTP_USER> <SFTP_PASS>"
    exit 1
fi

REAL_DIR="$1"
SFTP_USER="$2"
SFTP_PASS="$3"
CHROOT_DIR="$(dirname "$REAL_DIR")/sftp-chroot"
SHADOW_DIR="$CHROOT_DIR/logs"

# 检查真实目录
if [ ! -d "$REAL_DIR" ]; then
    echo "❌ 真实日志目录不存在: $REAL_DIR"
    exit 1
fi

echo "➡ 创建 chroot 根目录和 logs 子目录"
mkdir -p "$SHADOW_DIR"

# chroot 根必须 root:root 755
chown root:root "$CHROOT_DIR"
chmod 755 "$CHROOT_DIR"

# logs 子目录 root:root 755
chown root:root "$SHADOW_DIR"
chmod 755 "$SHADOW_DIR"

echo "➡ 安装 bindfs（优先）"
USE_BINDFS=0
if ! command -v bindfs >/dev/null 2>&1; then
    yum -y install epel-release || true
    yum -y install bindfs && USE_BINDFS=1
else
    USE_BINDFS=1
fi

echo "➡ 挂载 bindfs 只读映射日志目录"
umount "$SHADOW_DIR" 2>/dev/null || true

if [ "$USE_BINDFS" -eq 1 ]; then
    bindfs -o ro "$REAL_DIR" "$SHADOW_DIR"
    # 写入 /etc/fstab
    grep -v -F "bindfs#${REAL_DIR} ${SHADOW_DIR}" /etc/fstab >/tmp/fstab.$$ || true
    echo "bindfs#${REAL_DIR} ${SHADOW_DIR} fuse ro,nonempty,defaults 0 0" >> /tmp/fstab.$$
    mv /tmp/fstab.$$ /etc/fstab
else
    mount --bind "$REAL_DIR" "$SHADOW_DIR"
    mount -o remount,bind,ro "$SHADOW_DIR"
    # 写入 fstab
    grep -v -F "${REAL_DIR} ${SHADOW_DIR}" /etc/fstab >/tmp/fstab.$$ || true
    echo "${REAL_DIR} ${SHADOW_DIR} none bind 0 0" >> /tmp/fstab.$$
    echo "${SHADOW_DIR} none remount,bind,ro 0 0" >> /tmp/fstab.$$
    mv /tmp/fstab.$$ /etc/fstab
fi

echo "➡ 创建 SFTP 用户"
if ! id "$SFTP_USER" >/dev/null 2>&1; then
    useradd -M -s /sbin/nologin "$SFTP_USER"
fi
echo "${SFTP_USER}:${SFTP_PASS}" | chpasswd

echo "➡ 写入 SSHD 配置"
mkdir -p /etc/ssh/sshd_config.d
CONF_FILE="/etc/ssh/sshd_config.d/sftp_${SFTP_USER}.conf"

cat > "$CONF_FILE" <<EOF
Match User ${SFTP_USER}
    ForceCommand internal-sftp
    ChrootDirectory ${CHROOT_DIR}
    AllowTcpForwarding no
    X11Forwarding no
EOF

echo "➡ 重启 SSHD"
systemctl restart sshd

echo ""
echo "==================================================="
echo "   🎉 SFTP 用户创建成功"
echo "---------------------------------------------------"
echo " 用户:        $SFTP_USER"
echo " 密码:        $SFTP_PASS"
echo " 真实日志目录: $REAL_DIR"
echo " chroot 根:   $CHROOT_DIR"
echo " logs 映射:   $SHADOW_DIR (只读)"
echo " bindfs 使用: $( [ $USE_BINDFS -eq 1 ] && echo '是' || echo '否，fallback bind+ro')"
echo "==================================================="
echo ""
echo "测试登录:"
echo " sftp $SFTP_USER@<服务器IP>"
echo "登录后，cd logs 即可看到日志文件（只读）"
