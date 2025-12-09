# API文档

## 概述
本文档提供了医疗问诊平台的API文档。当前项目处于开发阶段，后端API正在建设中，前端使用本地store模拟API调用。

## 项目架构

### 后端服务
- **qa-service-user**: Spring Boot用户服务，运行在端口8080
- **qa-service-question**: Spring Boot问题服务（建设中）
- **qa-service-statistic**: Spring Boot统计服务（建设中）

### 前端应用
- **qa-web**: Vue 3 + TypeScript + Ant Design Vue前端应用
- 使用本地store模拟API调用，数据存储在JSON文件中

## API基础信息

### 基础URL
- **开发环境（用户服务）**: `http://localhost:8080/api`
- **开发环境（前端应用）**: `http://localhost:5173` (Vite开发服务器)

### 当前状态
⚠️ **注意**: 当前后端API处于开发初期阶段，仅提供CORS测试端点。完整API正在开发中。

### 测试端点
```bash
# CORS测试
GET http://localhost:8080/api/test/cors
POST http://localhost:8080/api/test/cors
```

### 前端模拟API
前端使用本地store提供以下功能：
- 医生登录/登出
- 患者身份验证
- 问题管理（提交、回答、查询）
- 医生和患者数据管理

### 通用头部
```http
Content-Type: application/json
Accept: application/json
```

## 认证端点（前端Store实现）

### 医生登录
**前端Store方法**: `store.loginDoctor(username, password)`

**参数:**
- `username`: 医生用户名（如: dr-zhang-wei）
- `password`: 密码（如: 123456）

**返回值:**
- 成功: 返回医生对象
- 失败: 返回null

**示例:**
```typescript
const doctor = store.loginDoctor('dr-zhang-wei', '123456');
if (doctor) {
  console.log('登录成功:', doctor.name);
  // 医生信息存储在 store.state.currentDoctor
}
```

**测试账号:**
```json
[
  {
    "username": "dr-zhang-wei",
    "password": "123456",
    "name": "张伟医生",
    "department": "心内科"
  },
  {
    "username": "dr-li-na", 
    "password": "123456",
    "name": "李娜医生",
    "department": "儿科"
  },
  {
    "username": "dr-wang-qiang",
    "password": "123456",
    "name": "王强医生",
    "department": "骨科"
  }
]
```

### 患者身份验证
**前端Store方法**: `store.verifyPatient(name, birthday)`

**参数:**
- `name`: 患者姓名
- `birthday`: 生日（格式: YYYY-MM-DD）

**返回值:** 患者对象

**行为:**
- 如果患者存在，返回现有患者
- 如果患者不存在，创建新患者并返回

**示例:**
```typescript
const patient = store.verifyPatient('赵明', '1980-05-15');
console.log('患者信息:', patient);
// 患者信息存储在 store.state.currentPatient
```

### 登出功能
**医生登出:**
```typescript
store.logoutDoctor();
```

**患者登出:**
```typescript
store.logoutPatient();
```

## 医生端点（前端Store实现）

### 获取当前医生
**数据位置**: `store.state.currentDoctor`

**示例:**
```typescript
const currentDoctor = store.state.currentDoctor;
if (currentDoctor) {
  console.log('当前医生:', currentDoctor.name);
}
```

### 获取医生列表
**数据位置**: `store.state.doctors`

**获取所有医生:**
```typescript
const allDoctors = store.state.doctors;
```

**获取活跃医生:**
```typescript
const activeDoctors = store.state.doctors.filter(doctor => doctor.isActive);
```

**按用户名获取医生:**
```typescript
const doctor = store.getDoctorByUsername('dr-zhang-wei');
```

### 医生数据结构
```typescript
interface Doctor {
  id: string;
  username: string;
  password: string;  // 仅用于前端验证
  name: string;
  title: string;
  department: string;
  avatar: string;
  experience: string;
  specialties: string[];
  isActive: boolean;
}
```

### 示例数据
```json
[
  {
    "id": "doc001",
    "username": "dr-zhang-wei",
    "name": "张伟医生",
    "title": "主任医师",
    "department": "心内科",
    "avatar": "https://images.pexels.com/photos/5215024/pexels-photo-5215024.jpeg",
    "experience": "15年临床经验",
    "specialties": ["高血压", "冠心病", "心律失常"],
    "isActive": true
  },
  {
    "id": "doc002",
    "username": "dr-li-na",
    "name": "李娜医生",
    "title": "副主任医师",
    "department": "儿科",
    "avatar": "https://images.pexels.com/photos/5327585/pexels-photo-5327585.jpeg",
    "experience": "10年临床经验",
    "specialties": ["儿童感冒", "儿童发育", "疫苗接种"],
    "isActive": true
  },
  {
    "id": "doc003",
    "username": "dr-wang-qiang",
    "name": "王强医生",
    "title": "主治医师",
    "department": "骨科",
    "avatar": "https://images.pexels.com/photos/5452293/pexels-photo-5452293.jpeg",
    "experience": "8年临床经验",
    "specialties": ["骨折", "关节炎", "运动损伤"],
    "isActive": true
  }
]
```

## 问题端点（前端Store实现）

### 获取问题列表
**数据位置**: `store.state.questions`

**获取所有问题:**
```typescript
const allQuestions = store.state.questions;
```

**按医生ID获取问题:**
```typescript
const doctorQuestions = store.getQuestionsByDoctor('doc001');
```

**按患者ID获取问题:**
```typescript
const patientQuestions = store.getQuestionsByPatient('patient001');
```

**按状态过滤:**
```typescript
const pendingQuestions = store.state.questions.filter(q => q.status === 'pending');
const answeredQuestions = store.state.questions.filter(q => q.status === 'answered');
```

### 提交新问题
**Store方法**: `store.addQuestion(questionData)`

**参数:**
```typescript
interface QuestionInput {
  patientId: string;
  patientName: string;
  doctorId: string;
  doctorName: string;
  question: string;
}
```

**示例:**
```typescript
const newQuestion = store.addQuestion({
  patientId: 'patient001',
  patientName: '赵明',
  doctorId: 'doc001',
  doctorName: '张伟医生',
  question: '血压最近有点高,早上测量是145/95,需要吃降压药吗?'
});
```

### 回答问题
**Store方法**: `store.answerQuestion(questionId, answer)`

**示例:**
```typescript
store.answerQuestion('q001', '根据您的血压值,属于1级高血压。建议先进行生活方式干预...');
```

### 标记问题为已解答（口述）
**Store方法**: `store.markQuestionAsAnswered(questionId)`

**示例:**
```typescript
store.markQuestionAsAnswered('q002');
// 会将问题状态改为answered，答案设为"已口述解答"
```

### 问题数据结构
```typescript
interface Question {
  id: string;
  patientId: string;
  patientName: string;
  doctorId: string;
  doctorName: string;
  question: string;
  submitTime: string;  // ISO格式
  status: 'pending' | 'answered';
  answer: string | null;
  answerTime: string | null;  // ISO格式
}
```

### 示例数据
```json
[
  {
    "id": "q001",
    "patientId": "patient001",
    "patientName": "赵明",
    "doctorId": "doc001",
    "doctorName": "张伟医生",
    "question": "最近总是感觉胸闷气短,特别是爬楼梯的时候,这是什么原因?",
    "submitTime": "2025-11-02T09:30:00",
    "status": "answered",
    "answer": "根据您的描述,可能是心脏功能问题。建议您做个心电图和心脏彩超检查,同时注意休息,避免剧烈运动。",
    "answerTime": "2025-11-02T09:45:00"
  },
  {
    "id": "q002",
    "patientId": "patient002",
    "patientName": "孙丽",
    "doctorId": "doc002",
    "doctorName": "李娜医生",
    "question": "孩子5岁,最近总是咳嗽,晚上更严重,需要吃什么药?",
    "submitTime": "2025-11-02T10:15:00",
    "status": "pending",
    "answer": null,
    "answerTime": null
  }
]
```

## 患者端点（前端Store实现）

### 获取当前患者
**数据位置**: `store.state.currentPatient`

**示例:**
```typescript
const currentPatient = store.state.currentPatient;
if (currentPatient) {
  console.log('当前患者:', currentPatient.name);
}
```

### 获取患者列表
**数据位置**: `store.state.patients`

**示例:**
```typescript
const allPatients = store.state.patients;
```

### 患者数据结构
```typescript
interface Patient {
  id: string;
  name: string;
  birthday: string;  // 格式: YYYY-MM-DD
  phone: string;
  gender: string;    // 'male' | 'female' | ''
}
```

### 示例数据
```json
[
  {
    "id": "patient001",
    "name": "赵明",
    "birthday": "1980-05-15",
    "phone": "",
    "gender": ""
  },
  {
    "id": "patient002",
    "name": "孙丽",
    "birthday": "1990-08-20",
    "phone": "",
    "gender": ""
  },
  {
    "id": "patient003",
    "name": "周杰",
    "birthday": "1985-03-10",
    "phone": "",
    "gender": ""
  }
]
```

### 患者资料更新
**注意**: 当前版本的患者资料更新功能尚未实现。患者验证时会创建新患者或返回现有患者，但更新功能需要在后续版本中添加。

## 统计端点（前端Store实现）

### 获取平台统计信息
**Store方法**: `store.getStatistics()`

**返回值:**
```typescript
interface Statistics {
  totalDoctors: number;      // 医生总数
  totalQuestions: number;    // 问题总数
  activeSessions: number;    // 待响应问题数
  totalSessions: number;     // 在线诊室数（活跃医生数）
}
```

**示例:**
```typescript
const stats = store.getStatistics();
console.log('平台统计:', stats);
```

**示例输出:**
```json
{
  "totalDoctors": 5,
  "totalQuestions": 7,
  "activeSessions": 3,
  "totalSessions": 4
}
```

### 医生个人统计
**当前版本**: 医生个人统计功能尚未实现，需要在后续版本中添加。

**计划功能:**
- 医生回答的问题数量
- 平均响应时间
- 患者满意度评分
- 月度趋势分析

### 数据来源
统计信息基于以下数据计算：
- **医生数据**: `src/data/doctor-user-list.json`
- **问题数据**: `src/data/question-list.json`
- **患者数据**: `src/data/patient-user.json`

所有统计数据实时更新，反映当前store中的数据状态。

## 后端健康检查端点

### Spring Boot Actuator
后端服务启用了Spring Boot Actuator，提供以下监控端点：

**基础URL**: `http://localhost:8080/actuator`

**可用端点:**
- `GET /actuator/health` - 服务健康状态
- `GET /actuator/info` - 应用信息
- `GET /actuator/metrics` - 应用指标
- `GET /actuator/env` - 环境变量
- `GET /actuator/beans` - Spring Beans
- `GET /actuator/loggers` - 日志配置

**健康检查示例:**
```bash
curl http://localhost:8080/actuator/health
```

**响应示例:**
```json
{
  "status": "UP",
  "components": {
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 500000000000,
        "free": 300000000000,
        "threshold": 10485760
      }
    },
    "ping": {
      "status": "UP"
    }
  }
}
```

## 前端Store使用示例

### TypeScript/JavaScript
```typescript
// 导入store
import { store } from './store';

// 1. 医生登录
const doctor = store.loginDoctor('dr-zhang-wei', '123456');
if (doctor) {
  console.log('登录成功:', doctor.name);
}

// 2. 患者验证
const patient = store.verifyPatient('赵明', '1980-05-15');
console.log('患者信息:', patient);

// 3. 获取活跃医生列表
const activeDoctors = store.state.doctors.filter(d => d.isActive);

// 4. 提交问题
const newQuestion = store.addQuestion({
  patientId: patient.id,
  patientName: patient.name,
  doctorId: 'doc001',
  doctorName: '张伟医生',
  question: '最近总是感觉胸闷气短,这是什么原因?'
});

// 5. 获取医生的问题
const doctorQuestions = store.getQuestionsByDoctor('doc001');
const pendingQuestions = doctorQuestions.filter(q => q.status === 'pending');

// 6. 回答问题
store.answerQuestion(newQuestion.id, '根据您的描述,可能是心脏功能问题...');

// 7. 获取统计信息
const stats = store.getStatistics();
console.log('平台统计:', stats);
```

### Vue组件中使用
```vue
<template>
  <div>
    <h1>医生诊室</h1>
    <div v-if="currentDoctor">
      <p>欢迎, {{ currentDoctor.name }}</p>
      <button @click="logout">退出登录</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { store } from '../store';

const currentDoctor = computed(() => store.state.currentDoctor);

const logout = () => {
  store.logoutDoctor();
};
</script>
```

## 测试示例

### cURL测试后端CORS端点
```bash
# 测试GET请求
curl -X GET http://localhost:8080/api/test/cors

# 测试POST请求
curl -X POST http://localhost:8080/api/test/cors \
  -H "Content-Type: application/json" \
  -d '{"test": "data"}'

# 测试OPTIONS请求（CORS预检）
curl -X OPTIONS http://localhost:8080/api/test/cors \
  -H "Access-Control-Request-Method: POST" \
  -H "Access-Control-Request-Headers: content-type" \
  -H "Origin: http://localhost:5173"
```

### 响应示例
```json
{
  "message": "CORS configuration is working!",
  "timestamp": 1733731200000,
  "service": "qa-service-user"
}
```

### 前端开发服务器
```bash
# 启动前端开发服务器
cd web/qa-web
npm run dev

# 访问地址: http://localhost:5173
```

### 后端开发服务器
```bash
# 启动后端服务
cd server/qa-service-user
./mvnw spring-boot:run

# 访问地址: http://localhost:8080
```

## 开发注意事项

### 前端Store限制
1. **数据持久性**: 当前store数据仅在内存中，页面刷新后会重置
2. **并发访问**: 不支持多用户并发访问，所有用户共享同一store
3. **数据验证**: 前端验证较简单，需要后端补充完整验证逻辑
4. **安全性**: 密码明文存储，仅用于演示目的

### 后端API开发计划
1. **用户服务** (`qa-service-user`):
   - 医生认证和授权
   - 患者管理
   - 医生资料管理

2. **问题服务** (`qa-service-question`):
   - 问题提交和查询
   - 问题回答和状态管理
   - 问题分类和标签

3. **统计服务** (`qa-service-statistic`):
   - 平台统计
   - 医生个人统计
   - 数据分析和报表

### 数据文件位置
- **医生数据**: `web/qa-web/src/data/doctor-user-list.json`
- **患者数据**: `web/qa-web/src/data/patient-user.json`
- **问题数据**: `web/qa-web/src/data/question-list.json`

## 版本控制

### 当前版本: v0.1.0 (开发中)
- 前端: Vue 3 + TypeScript + Ant Design Vue
- 后端: Spring Boot 3.5.7 (基础框架)
- 数据: 前端store模拟 + JSON文件

### 版本路线图
| 版本 | 状态 | 主要功能 |
|------|------|----------|
| v0.1.0 | 当前 | 前端原型 + 基础后端框架 |
| v0.2.0 | 计划 | 完整后端API + 数据库集成 |
| v0.3.0 | 计划 | 用户认证 + 权限管理 |
| v1.0.0 | 计划 | 生产就绪版本 |

## 变更日志
| 版本 | 日期 | 变更 |
|------|------|------|
| v0.1.0 | 2025-12-09 | 初始版本：前端原型 + 基础后端框架 |
| - | 2025-12-09 | 更新API文档以反映实际项目状态 |

---

## 快速开始指南

### 1. 启动后端服务
```bash
cd server/qa-service-user
./mvnw spring-boot:run
```

### 2. 启动前端应用
```bash
cd web/qa-web
npm install
npm run dev
```

### 3. 访问应用
- 前端: http://localhost:5173
- 后端健康检查: http://localhost:8080/actuator/health
- 后端CORS测试: http://localhost:8080/api/test/cors

### 4. 测试账号
- **医生**: dr-zhang-wei / 123456
- **患者**: 任意姓名 + 生日

---

*此API文档应在项目发生变化时更新。当前文档反映了v0.1.0开发版本的实际状态。*