# 集成测试说明

## 使用 `run_http_requests.py` 运行 `.http` 文件

### 功能
`run_http_requests.py` 是一个 Python 脚本，用于解析 `.http` 文件中的 HTTP 请求，并通过 HTTPie 工具执行这些请求。

### 依赖
- Python 3.x
- HTTPie 工具（通过 `pip install httpie` 安装）

### 使用方法
1. **确保 HTTPie 已安装**：
   ```bash
   pip install httpie
   ```

2. **运行脚本**：
   ```bash
   cd server/qa-service-user/src/test/integrationTests/
   python run_http_requests.py <file.http>
   ```
   示例：
   ```bash
   python run_http_requests.py doctors_api_test.http
   ```

3. **输出结果**：
   - 脚本会解析 `.http` 文件中的请求（如 `GET`、`POST` 等）。
   - 执行请求并打印 HTTPie 的返回结果。

### 示例文件格式
`.http` 文件示例：
```http
### 测试 /api/doctors GET 接口
GET http://localhost:8080/api/doctors

### 可选：带参数的请求
# GET http://localhost:8080/api/doctors?name=John
```

### 注意事项
- 脚本仅支持解析简单的 HTTP 请求（如 `GET`、`POST` 等）。
- 注释行（以 `#` 开头）会被忽略。
- 确保目标服务（如 `http://localhost:8080`）已启动。

### 扩展功能
如需支持更复杂的请求（如请求头、请求体），可以修改脚本以增强解析逻辑。