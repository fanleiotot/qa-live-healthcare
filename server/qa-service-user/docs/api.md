# API 文档

## 医生用户 API

**Controller 文件位置**: [DoctorUserController.java](../src/main/java/com/leansoftx/qaserviceuser/controller/DoctorUserController.java)

| 方法 | 端点 | 描述 | 参数 | 请求体 | 响应 |
|--------|----------|-------------|------------|--------------|----------|
| GET | `/api/doctors` | 获取所有医生的列表。 | - | - | `Doctor` 对象列表。 |
| GET | `/api/doctors/{id}` | 根据 ID 获取医生信息。 | `id`: 医生的 ID。 | - | 一个 `Doctor` 对象。 |
| GET | `/api/doctors/active` | 获取所有活跃医生的列表。 | - | - | `Doctor` 对象列表。 |
| POST | `/api/doctors` | 添加一个新医生。 | - | 一个 `Doctor` 对象。 | 创建的 `Doctor` 对象。 |
| PUT | `/api/doctors/{id}` | 根据 ID 更新医生信息。 | `id`: 要更新的医生的 ID。 | 包含更新字段的 `Doctor` 对象。 | 更新后的 `Doctor` 对象。 |
| DELETE | `/api/doctors/{id}` | 根据 ID 删除医生。 | `id`: 要删除的医生的 ID。 | - | 无内容。 |

### JSON 示例数据

#### `Doctor` 对象结构
```json
{
  "id": "doc123",
  "name": "Dr. John Doe",
  "specialization": "Cardiology",
  "active": true,
  "contact": {
    "email": "john.doe@example.com",
    "phone": "+1234567890"
  }
}
```

#### GET `/api/doctors` 响应示例
```json
[
  {
    "id": "doc123",
    "name": "Dr. John Doe",
    "specialization": "Cardiology",
    "active": true
  },
  {
    "id": "doc456",
    "name": "Dr. Jane Smith",
    "specialization": "Neurology",
    "active": true
  }
]
```

#### POST `/api/doctors` 请求示例
```json
{
  "name": "Dr. New Doctor",
  "specialization": "Pediatrics",
  "active": true,
  "contact": {
    "email": "new.doctor@example.com",
    "phone": "+9876543210"
  }
}
```

#### PUT `/api/doctors/{id}` 请求示例
```json
{
  "name": "Dr. Updated Name",
  "specialization": "Cardiology",
  "active": false
}
```