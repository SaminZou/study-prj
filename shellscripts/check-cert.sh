#!/bin/bash

# 证书信息检查脚本
# 使用方法: ./cert_check.sh <证书文件>

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
PURPLE='\033[0;35m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# 帮助信息
show_help() {
    echo "证书信息检查脚本"
    echo "========================"
    echo "用法: $0 <证书文件>"
    echo ""
    echo "示例:"
    echo "  $0 test.pem"
    echo "  $0 /path/to/certificate.crt"
    echo "  $0 domain.crt"
    echo ""
    echo "支持的证书格式: .pem, .crt, .cer"
}

# 检查证书文件是否存在且有效
check_cert_file() {
    local cert_file=$1

    if [[ ! -f "$cert_file" ]]; then
        echo -e "${RED}错误: 证书文件 '$cert_file' 不存在${NC}"
        return 1
    fi

    # 验证证书文件格式
    if ! openssl x509 -in "$cert_file" -noout >/dev/null 2>&1; then
        echo -e "${RED}错误: '$cert_file' 不是有效的证书文件${NC}"
        return 1
    fi

    return 0
}

# 获取证书信息
get_cert_info() {
    local cert_file=$1

    echo -e "${CYAN}� 证书详细信息检查${NC}"
    echo -e "${CYAN}========================${NC}"

    # 基本证书信息
    echo -e "${BLUE}� 证书文件:${NC} $cert_file"
    echo -e "${BLUE}� 序列号:${NC} $(openssl x509 -in "$cert_file" -noout -serial | cut -d= -f2)"
    echo -e "${BLUE}� 颁发者:${NC} $(openssl x509 -in "$cert_file" -noout -issuer | sed 's/.*= //')"
    echo -e "${BLUE}� 使用者:${NC} $(openssl x509 -in "$cert_file" -noout -subject | sed 's/.*= //')"
    echo ""

    # 有效期信息
    local not_before=$(openssl x509 -in "$cert_file" -noout -startdate | cut -d= -f2)
    local not_after=$(openssl x509 -in "$cert_file" -noout -enddate | cut -d= -f2)

    echo -e "${GREEN}⏰ 生效时间:${NC} $not_before"
    echo -e "${GREEN}⏰ 过期时间:${NC} $not_after"

    # 检查证书是否过期
    local days_remaining=$(openssl x509 -in "$cert_file" -noout -checkend 0 2>&1 | grep -c "will expire")
    if [[ $days_remaining -eq 1 ]]; then
        echo -e "${RED}❌ 证书已过期${NC}"
    else
        echo -e "${GREEN}✅ 证书有效${NC}"
    fi

    # 检查30天内是否过期
    openssl x509 -in "$cert_file" -noout -checkend 2592000 >/dev/null 2>&1
    if [[ $? -ne 0 ]]; then
        echo -e "${YELLOW}⚠️  警告: 证书将在30天内过期${NC}"
    fi
    echo ""

    # 域名信息
    local common_name=$(openssl x509 -in "$cert_file" -noout -subject | grep -o 'CN = [^,]*' | cut -d= -f2 | sed 's/^ *//')
    echo -e "${PURPLE}� 主域名 (CN):${NC} $common_name"

    # SAN扩展信息
    echo -e "${PURPLE}� 扩展域名 (SAN):${NC}"
    local san_info=$(openssl x509 -in "$cert_file" -noout -ext subjectAltName 2>/dev/null)
    if [[ -n "$san_info" ]]; then
        echo "$san_info" | grep "DNS:" | sed 's/ *DNS:/   • /g'
    else
        echo "   • 无扩展域名"
    fi
}

# 主函数
main() {
    # 检查参数
    if [[ $# -ne 1 ]]; then
        show_help
        exit 1
    fi

    # 检查帮助参数
    if [[ "$1" == "-h" || "$1" == "--help" ]]; then
        show_help
        exit 0
    fi

    local cert_file=$1

    # 检查证书文件
    if ! check_cert_file "$cert_file"; then
        exit 1
    fi

    # 获取并显示证书信息
    get_cert_info "$cert_file"
}

# 执行主函数
main "$@"