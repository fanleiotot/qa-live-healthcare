import re
import subprocess
import sys
from pathlib import Path

def parse_http_file(file_path):
    """解析 .http 文件，提取 HTTP 请求"""
    with open(file_path, 'r', encoding='utf-8') as file:
        content = file.read()

    # 匹配 HTTP 请求（如 GET、POST 等）
    requests = re.findall(r'^(GET|POST|PUT|DELETE|PATCH)\s+(http[^\s]+)', content, re.MULTILINE)
    return requests

def run_httpie_command(method, url):
    """使用 HTTPie 运行请求"""
    command = ['http', method.upper(), url]
    try:
        result = subprocess.run(command, check=True, capture_output=True, text=True)
        print(f"请求成功: {method} {url}")
        print(result.stdout)
    except subprocess.CalledProcessError as e:
        print(f"请求失败: {method} {url}")
        print(e.stderr)

def main():
    if len(sys.argv) != 2:
        print("用法: python run_http_requests.py <file.http>")
        sys.exit(1)

    file_path = Path(sys.argv[1])
    if not file_path.exists():
        print(f"文件不存在: {file_path}")
        sys.exit(1)

    requests = parse_http_file(file_path)
    if not requests:
        print(f"未找到有效的 HTTP 请求: {file_path}")
        sys.exit(1)

    for method, url in requests:
        run_httpie_command(method, url)

if __name__ == "__main__":
    main()