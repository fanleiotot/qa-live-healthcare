# 标准编码风格

## 概述
本文档定义了此工作空间的编码标准和风格指南。一致的编码风格提高了代码的可读性、可维护性和协作效率。

## 通用原则

### 1. 可读性优先
- 代码应易于阅读和理解
- 为变量、函数和类使用有意义的名称
- 编写意图清晰的自文档化代码

### 2. 一致性
- 在整个代码库中遵循相同的模式
- 使用语言和框架的既定约定
- 保持团队成员间的一致性

### 3. 可维护性
- 编写易于修改和扩展的代码
- 保持函数和类的单一职责
- 避免不必要的复杂性

## 语言特定指南

### TypeScript/JavaScript

#### 命名约定
```typescript
// 变量和函数 - camelCase
const userName = '张三';
function calculateTotal() { }

// 类和接口 - PascalCase
class UserService { }
interface UserData { }

// 常量 - UPPER_SNAKE_CASE
const MAX_RETRY_COUNT = 3;
const API_BASE_URL = 'http://localhost:8080';

// 私有成员 - 使用下划线前缀（可选）
private _internalMethod() { }
```

#### 代码格式化
```typescript
// 使用2空格缩进
function example() {
  if (condition) {
    // ...
  }
}

// 一致使用分号
const name = '张三';

// 最大行长度：80-100字符
// 为可读性换行长行

// 除非需要插值，否则使用单引号
const message = '你好';
const template = `你好 ${name}`;
```

#### 类型注解
```typescript
// 始终指定返回类型
function add(a: number, b: number): number {
  return a + b;
}

// 使用显式类型而非'any'
// 不好
function process(data: any) { }

// 好
function process(data: UserData) { }

// 为对象形状使用接口
interface User {
  id: number;
  name: string;
  email: string;
}

// 当前项目中的示例
export interface Doctor {
  id: string;
  username: string;
  password: string;
  name: string;
  title: string;
  department: string;
  avatar: string;
  experience: string;
  specialties: string[];
  isActive: boolean;
}
```

### Java

#### 命名约定
```java
// 类和接口 - PascalCase
public class UserService { }
public interface UserRepository { }

// 方法和变量 - camelCase
public void calculateTotal() { }
private String userName;

// 常量 - UPPER_SNAKE_CASE
public static final int MAX_RETRY_COUNT = 3;
private static final String API_BASE_URL = "http://localhost:8080";
```

#### 代码格式化
```java
// 使用4空格缩进
public class Example {
    public void method() {
        if (condition) {
            // ...
        }
    }
}

// 开括号在同一行
public void example() {
    // ...
}

// 每行一个语句
// 不好
int a = 1; int b = 2;

// 好
int a = 1;
int b = 2;
```

#### 注解和修饰符
```java
// 标准修饰符顺序
public static final String CONSTANT = "value";

// 使用@Override注解
@Override
public String toString() {
    return "User";
}

// 当前项目中的示例
@SpringBootApplication
public class QaServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(QaServiceUserApplication.class, args);
    }
}
```

### Vue组件风格

#### 组件结构
```vue
<template>
  <!-- 模板部分 -->
  <div class="component-name">
    <!-- 使用语义化HTML -->
  </div>
</template>

<script setup lang="ts">
// 使用Composition API with <script setup>
import { ref, computed } from 'vue';
import { store } from '../store';

// 响应式数据
const count = ref(0);

// 计算属性
const doubleCount = computed(() => count.value * 2);

// 方法
function increment() {
  count.value++;
}
</script>

<style scoped>
/* 使用scoped样式 */
.component-name {
  /* 样式定义 */
}
</style>
```

#### 组件命名
- 组件文件使用PascalCase：`AppHeader.vue`, `Consultation.vue`
- 组件名与文件名匹配
- 使用描述性名称反映组件用途

## 当前项目编码模式

### Vue组件模式
```typescript
// 使用Composition API
<script setup lang="ts">
import { ref, reactive, computed, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { message } from 'ant-design-vue';
import { store, Doctor } from '../store';

// 响应式状态
const currentPatient = computed(() => store.state.currentPatient);
const myQuestions = computed(() =>
  currentPatient.value
    ? store.getQuestionsByPatient(currentPatient.value.id)
    : []
);

// 生命周期钩子
onMounted(() => {
  // 初始化逻辑
});

// 方法定义
const verifyPatient = () => {
  // 业务逻辑
};
</script>
```

### 状态管理模式
```typescript
// store/index.ts 中的模式
export interface Doctor {
  id: string;
  username: string;
  password: string;
  name: string;
  title: string;
  department: string;
  avatar: string;
  experience: string;
  specialties: string[];
  isActive: boolean;
}

interface State {
  doctors: Doctor[];
  patients: Patient[];
  questions: Question[];
  currentDoctor: Doctor | null;
  currentPatient: Patient | null;
}

const state = reactive<State>({
  doctors: doctorData as Doctor[],
  patients: patientData as Patient[],
  questions: questionData as Question[],
  currentDoctor: null,
  currentPatient: null,
});

export const store = {
  state,

  // 业务方法
  loginDoctor(username: string, password: string): Doctor | null {
    const doctor = state.doctors.find(
      d => d.username === username && d.password === password
    );
    if (doctor) {
      state.currentDoctor = doctor;
      return doctor;
    }
    return null;
  },
};
```

### 错误处理模式
```typescript
// 使用try-catch处理预期错误
try {
  const result = await apiCall();
} catch (error) {
  // 处理特定错误类型
  if (error instanceof NetworkError) {
    // 重试逻辑
  } else if (error instanceof ValidationError) {
    // 显示用户消息
    message.error('验证失败，请检查输入');
  }
}

// 不要吞掉异常
// 不好
try {
  riskyOperation();
} catch (e) {
  // 空的catch块
}

// 好
try {
  riskyOperation();
} catch (e) {
  console.error('操作失败', e);
  message.error('操作失败，请重试');
}
```

## 注释和文档

### 何时注释
- 解释"为什么"而不是"什么"（代码应自解释）
- 记录复杂算法或业务逻辑
- 注明变通方法或临时解决方案
- 记录公共API和接口

### 注释风格
```typescript
/**
 * 计算包含税费的总价格。
 * 
 * @param items - 包含价格和数量的物品数组
 * @param taxRate - 税率作为小数（例如，0.08表示8%）
 * @returns 应用税费后的总价格
 */
function calculateTotalWithTax(items: Item[], taxRate: number): number {
  // 计算小计
  const subtotal = items.reduce((sum, item) => sum + (item.price * item.quantity), 0);
  
  // 应用税费
  return subtotal * (1 + taxRate);
}

// 复杂逻辑的行内注释
// 使用Dijkstra算法查找最短路径
const shortestPath = findShortestPath(graph, start, end);
```

### 当前项目中的文档示例
```typescript
// store/index.ts 中的接口文档
export interface Question {
  id: string;
  patientId: string;
  patientName: string;
  doctorId: string;
  doctorName: string;
  question: string;
  submitTime: string;
  status: 'pending' | 'answered';  // 问题状态：待解答/已解答
  answer: string | null;
  answerTime: string | null;
}

// 路由配置中的文档
const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'Home',
    component: Home,  // 首页
  },
  {
    path: '/consultation',
    name: 'Consultation',
    component: Consultation,  // 患者问诊页面
  },
];
```

## 测试标准

### 测试结构（当前项目待完善）
```typescript
describe('UserService', () => {
  let userService: UserService;
  let mockRepository: jest.Mocked<UserRepository>;

  beforeEach(() => {
    mockRepository = {
      findById: jest.fn(),
      save: jest.fn(),
    };
    userService = new UserService(mockRepository);
  });

  describe('createUser', () => {
    it('使用有效数据创建用户', async () => {
      // 准备
      const userData = { name: '张三', email: 'zhangsan@example.com' };
      mockRepository.save.mockResolvedValue({ id: 1, ...userData });

      // 执行
      const result = await userService.createUser(userData);

      // 断言
      expect(result.id).toBe(1);
      expect(result.name).toBe('张三');
      expect(mockRepository.save).toHaveBeenCalledWith(userData);
    });
  });
});
```

### 测试命名
- 使用描述性测试名称
- 遵循模式：`当[条件]时应该[预期行为]`
- 示例：`当提供有效ID时应返回用户`

## 代码审查指南

### 审查内容
1. **功能性**: 代码是否按预期工作？
2. **可读性**: 代码是否易于理解？
3. **测试**: 是否有足够的测试？
4. **性能**: 是否有性能问题？
5. **安全性**: 是否有安全漏洞？
6. **可维护性**: 此代码是否易于维护？

### 审查评论
- 建设性且具体
- 建议替代方案，不仅仅是批评
- 关注代码，而不是人
- 使用"我们"语言："我们应该考虑..."

## 工具配置

### 当前项目配置
```json
// package.json 中的脚本
{
  "scripts": {
    "dev": "vite",                    // 开发服务器
    "build": "vue-tsc -b && vite build",  // 生产构建
    "preview": "vite preview"         // 预览构建
  }
}
```

```typescript
// tsconfig.json 配置
{
  "compilerOptions": {
    "target": "ES2020",
    "useDefineForClassFields": true,
    "module": "ESNext",
    "lib": ["ES2020", "DOM", "DOM.Iterable"],
    "skipLibCheck": true,
    "moduleResolution": "bundler",
    "allowImportingTsExtensions": true,
    "resolveJsonModule": true,
    "isolatedModules": true,
    "noEmit": true,
    "jsx": "preserve",
    "strict": true,                    // 启用严格模式
    "noUnusedLocals": true,           // 未使用局部变量报错
    "noUnusedParameters": true,       // 未使用参数报错
    "noFallthroughCasesInSwitch": true
  },
  "include": ["src/**/*.ts", "src/**/*.tsx", "src/**/*.vue"],
  "references": [{ "path": "./tsconfig.node.json" }]
}
```

## 持续集成

### 预提交钩子（建议）
- 运行linter和formatter
- 运行单元测试
- 检查安全漏洞
- 验证提交消息

### CI流水线（建议）
1. 代码检查和格式化
2. 单元测试
3. 集成测试
4. 构建验证
5. 安全扫描
6. 部署到测试环境

---

*这些编码标准应根据特定项目需求和团队偏好进行调整。鼓励定期审查和更新本文档。*