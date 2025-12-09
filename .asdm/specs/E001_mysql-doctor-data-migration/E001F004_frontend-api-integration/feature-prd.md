# Feature PRD: E001F004_frontend-api-integration

**Date**: 2025-12-09  
**Author**: AI Assistant  
**Status**: Draft  
**Parent Epic**: [E001_mysql-doctor-data-migration](./../epic-prd.md)

## 1. Feature Overview

### 1.1 Feature Description
修改前端Vue应用，将医生相关操作从内存存储转向调用后端API，实现真实的数据库操作，包括医生登录、医生列表查询、状态管理等功能，确保前后端数据一致性。

### 1.2 Business Value
此功能完成前后端集成的最后一步，实现端到端的数据流，使系统从原型演示转变为可用的真实应用，为用户提供一致可靠的数据体验，支持多用户并发访问。

### 1.3 User Value
为用户提供真正的数据持久化体验，页面刷新后数据不会丢失，医生登录信息更加安全，系统响应更加稳定，为后续功能扩展奠定基础。

## 2. Relationship to Parent Epic

### 2.1 Epic Objectives Supported
- **前端应用能正常显示和操作医生数据**: 通过API调用替换内存存储
- **后端医生相关API实现**: 完成前端对后端API的集成
- **数据迁移过程不影响现有功能的正常使用**: 确保用户体验平滑过渡

### 2.2 Dependencies on Other Features
- **E001F003_backend-doctor-api-integration**: 依赖此功能提供的后端API接口
- **E001F002_doctor-table-migration**: 间接依赖此功能提供的表结构

## 3. User Stories

### 3.1 Target User Personas
- **前端开发人员**: 负责修改前端代码以调用后端API
- **测试人员**: 需要验证前后端集成功能
- **最终用户**: 使用医生登录和问诊功能
- **产品经理**: 确保用户体验一致

### 3.2 Detailed User Stories
| ID | User Story | Priority | Acceptance Criteria |
|----|------------|----------|-------------------|
| F-US-001 | 作为前端开发人员，我希望修改医生登录功能以调用后端API，以便实现真实的身份验证 | High | 1. 登录表单调用后端API<br>2. 登录成功后保存用户信息<br>3. 登录失败显示正确错误信息<br>4. 保持原有UI/UX体验 |
| F-US-002 | 作为前端开发人员，我希望修改医生列表获取逻辑以调用后端API，以便显示数据库中的医生信息 | High | 1. 医生列表从API获取<br>2. 支持按科室和专长筛选<br>3. 分页加载医生列表<br>4. 加载状态和错误处理 |
| F-US-003 | 作为前端开发人员，我希望实现医生状态更新API调用，以便医生可以修改自己的在线状态 | Medium | 1. 医生可更新在线状态<br>2. 状态更新实时反映<br>3. 更新失败有错误提示<br>4. 保持状态同步 |
| F-US-004 | 作为最终用户，我希望医生登录后页面刷新能保持登录状态，以便提供更好的用户体验 | High | 1. 登录状态持久化存储<br>2. 页面刷新后自动恢复状态<br>3. 令牌过期自动处理<br>4. 安全退出机制 |
| F-US-005 | 作为前端开发人员，我希望实现API调用的错误处理机制，以便网络错误或服务异常时有友好提示 | Medium | 1. 网络错误提示清晰<br>2. 服务异常有重试机制<br>3. 加载状态反馈及时<br>4. 错误日志记录完整 |

## 4. Functional Requirements

### 4.1 Core Functionality
- **API客户端封装**: 实现统一的HTTP客户端，处理请求和响应
- **认证管理**: 实现JWT令牌存储、刷新和验证
- **状态管理**: 修改现有store以支持API调用
- **错误处理**: 实现全局错误处理和用户反馈
- **加载状态**: 实现API调用过程中的加载状态管理
- **数据同步**: 确保前后端数据一致性

### 4.2 User Interactions
- **无缝过渡**: 保持原有UI/UX体验不变
- **加载反馈**: API调用过程中显示加载状态
- **错误提示**: 友好的错误信息提示
- **重试机制**: 网络错误时提供重试选项

### 4.3 Data Requirements
- **数据转换**: 处理前后端数据格式差异
- **缓存策略**: 合理的数据缓存机制
- **离线支持**: 网络不可用时的降级处理
- **数据验证**: 前端数据验证和后端验证协同

## 5. Non-Functional Requirements

### 5.1 Performance Requirements
| Requirement | Target | Measurement |
|-------------|--------|-------------|
| API响应处理 | < 300ms | 95%的请求 |
| 页面加载时间 | < 2秒 | 首次加载 |
| 数据更新延迟 | < 500ms | 状态更新 |
| 错误恢复时间 | < 5秒 | 自动重试 |

### 5.2 Security Requirements
- **令牌安全**: JWT令牌安全存储
- **HTTPS通信**: 生产环境使用HTTPS
- **CSRF防护**: 实现CSRF令牌验证
- **输入验证**: 前端输入数据验证

### 5.3 Compatibility Requirements
- **浏览器兼容**: 支持主流现代浏览器
- **向后兼容**: 保持API版本兼容性
- **移动端适配**: 响应式设计支持

## 6. UI/UX Specifications

### 6.1 User Interface Requirements
- **加载状态**: API调用时显示加载指示器
- **错误提示**: 统一的错误信息显示组件
- **状态反馈**: 操作成功/失败的明确反馈
- **进度指示**: 长时间操作的进度显示

### 6.2 User Experience Requirements
- **体验一致性**: 保持与原有内存存储体验一致
- **响应速度**: API调用不影响用户操作流畅度
- **错误恢复**: 网络错误时自动恢复或提供重试
- **状态同步**: 多窗口状态同步

### 6.3 Design Assets
- **加载组件**: 统一的加载状态组件
- **错误组件**: 标准化的错误提示组件
- **API文档**: 前端API调用文档

## 7. Technical Specifications

### 7.1 Architecture Impact
- **状态管理**: 从本地内存存储转向API调用
- **组件结构**: 保持现有组件结构，修改数据访问逻辑
- **路由管理**: 添加认证路由守卫
- **错误处理**: 实现全局错误处理机制

### 7.2 API Specifications
前端API客户端设计：
```typescript
// API客户端配置
class ApiClient {
  private baseURL: string;
  private token: string | null = null;
  
  constructor(baseURL: string) {
    this.baseURL = baseURL;
    // 从本地存储恢复token
    this.token = localStorage.getItem('auth_token');
  }
  
  // 设置认证令牌
  setToken(token: string) {
    this.token = token;
    localStorage.setItem('auth_token', token);
  }
  
  // 清除认证令牌
  clearToken() {
    this.token = null;
    localStorage.removeItem('auth_token');
  }
  
  // 通用请求方法
  async request<T>(endpoint: string, options: RequestOptions = {}): Promise<ApiResponse<T>> {
    const url = `${this.baseURL}${endpoint}`;
    const headers = {
      'Content-Type': 'application/json',
      ...(this.token && { 'Authorization': `Bearer ${this.token}` }),
      ...options.headers,
    };
    
    try {
      const response = await fetch(url, {
        ...options,
        headers,
      });
      
      if (!response.ok) {
        throw new ApiError(response.status, response.statusText);
      }
      
      return await response.json();
    } catch (error) {
      throw new ApiError(0, error.message);
    }
  }
  
  // 医生登录API
  async loginDoctor(username: string, password: string): Promise<LoginResponse> {
    return this.request('/api/auth/doctor/login', {
      method: 'POST',
      body: JSON.stringify({ username, password }),
    });
  }
  
  // 获取医生列表API
  async getDoctors(params: DoctorQueryParams = {}): Promise<DoctorsResponse> {
    const queryParams = new URLSearchParams(params as any).toString();
    return this.request(`/api/doctors?${queryParams}`);
  }
  
  // 更新医生状态API
  async updateDoctorStatus(doctorId: string, isActive: boolean): Promise<Doctor> {
    return this.request(`/api/doctors/${doctorId}/status`, {
      method: 'PUT',
      body: JSON.stringify({ isActive }),
    });
  }
}

// 全局API客户端实例
export const apiClient = new ApiClient('http://localhost:8080');
```

修改后的Store设计：
```typescript
// 修改后的store，集成API调用
import { apiClient } from './api-client';
import { reactive, computed } from 'vue';

interface State {
  doctors: Doctor[];
  currentDoctor: Doctor | null;
  loading: boolean;
  error: string | null;
}

const state = reactive<State>({
  doctors: [],
  currentDoctor: null,
  loading: false,
  error: null,
});

export const store = {
  state,
  
  // 计算属性
  activeDoctors: computed(() => 
    state.doctors.filter(doctor => doctor.isActive)
  ),
  
  // 医生登录
  async loginDoctor(username: string, password: string): Promise<Doctor | null> {
    state.loading = true;
    state.error = null;
    
    try {
      const response = await apiClient.loginDoctor(username, password);
      
      if (response.success) {
        // 保存token
        apiClient.setToken(response.token);
        
        // 保存当前医生
        state.currentDoctor = response.data;
        
        // 返回医生信息
        return response.data;
      } else {
        state.error = '登录失败';
        return null;
      }
    } catch (error) {
      state.error = error.message || '登录失败';
      return null;
    } finally {
      state.loading = false;
    }
  },
  
  // 医生登出
  logoutDoctor() {
    apiClient.clearToken();
    state.currentDoctor = null;
  },
  
  // 获取医生列表
  async fetchDoctors(params: DoctorQueryParams = {}): Promise<void> {
    state.loading = true;
    state.error = null;
    
    try {
      const response = await apiClient.getDoctors(params);
      
      if (response.success) {
        state.doctors = response.data.content;
      } else {
        state.error = '获取医生列表失败';
      }
    } catch (error) {
      state.error = error.message || '获取医生列表失败';
    } finally {
      state.loading = false;
    }
  },
  
  // 更新医生状态
  async updateDoctorStatus(doctorId: string, isActive: boolean): Promise<void> {
    state.loading = true;
    state.error = null;
    
    try {
      const updatedDoctor = await apiClient.updateDoctorStatus(doctorId, isActive);
      
      // 更新本地状态
      const index = state.doctors.findIndex(d => d.id === doctorId);
      if (index !== -1) {
        state.doctors[index] = updatedDoctor;
      }
      
      // 更新当前医生状态
      if (state.currentDoctor && state.currentDoctor.id === doctorId) {
        state.currentDoctor = updatedDoctor;
      }
    } catch (error) {
      state.error = error.message || '更新医生状态失败';
    } finally {
      state.loading = false;
    }
  },
  
  // 初始化：尝试恢复登录状态
  async initializeAuth() {
    const token = localStorage.getItem('auth_token');
    if (token) {
      apiClient.setToken(token);
      
      try {
        // 这里可以调用一个验证token的API
        // 如果没有这样的API，可以在页面加载时直接清除token
        // 并要求用户重新登录
        this.logoutDoctor();
      } catch (error) {
        this.logoutDoctor();
      }
    }
  }
};

// 初始化认证状态
store.initializeAuth();
```

## 8. Testing Requirements

### 8.1 Test Scenarios
- **单元测试**: API客户端和Store方法的单元测试
- **集成测试**: 前端与后端API的集成测试
- **UI测试**: 用户交互流程的端到端测试
- **错误测试**: 网络错误和异常情况处理测试
- **性能测试**: 页面加载和API响应性能测试

### 8.2 Acceptance Test Criteria
- **功能测试**: 所有医生相关功能正常工作
- **UI测试**: 用户界面保持原有体验
- **错误处理**: 各种错误情况处理正确
- **性能测试**: 响应速度满足要求

### 8.3 Quality Gates
- **代码覆盖率**: 单元测试覆盖率 > 80%
- **E2E测试**: 核心用户流程端到端测试通过
- **性能测试**: 页面加载和API响应时间达标
- **兼容性测试**: 主流浏览器兼容性验证

## 9. Deployment Requirements

### 9.1 Deployment Strategy
- **渐进式部署**: 先内部测试，再逐步推广
- **A/B测试**: 部分用户使用新版本，验证稳定性
- **监控指标**: 部署后监控关键指标
- **快速回滚**: 问题快速回滚机制

### 9.2 Rollback Plan
- **版本控制**: Git版本快速回退
- **配置回退**: 环境配置快速恢复
- **数据隔离**: 确保回滚不影响数据

## 10. Success Metrics

### 10.1 Feature-specific Metrics
| Metric | Target | Measurement Frequency |
|--------|--------|---------------------|
| API调用成功率 | > 99.5% | 实时监控 |
| 页面加载时间 | < 2秒 | 每日统计 |
| 用户操作响应时间 | < 300ms | 实时监控 |
| 错误率 | < 0.5% | 每日统计 |

### 10.2 Business Impact Metrics
- **用户体验**: 页面刷新不丢失登录状态
- **数据一致性**: 前后端数据100%一致
- **系统稳定性**: 99.9% uptime
- **用户满意度**: 用户反馈评分 > 4.0/5.0

## 11. Risks and Mitigations

### 11.1 Technical Risks
| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| API兼容性问题 | 中 | 高 | 严格的API版本管理 |
| 网络延迟影响体验 | 中 | 中 | 实现加载状态和错误处理 |
| 状态同步问题 | 中 | 中 | 实现状态同步机制 |
| 认证令牌管理 | 低 | 高 | 安全的令牌存储和刷新 |

### 11.2 Timeline Risks
| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| 集成复杂度高 | 中 | 中 | 分阶段实现，先核心功能 |
| 测试不充分 | 中 | 高 | 自动化测试和手动测试结合 |

## 12. Timeline

### 12.1 Development Timeline
| Phase | Duration | Start Date | End Date | Deliverables |
|-------|----------|------------|----------|--------------|
| API客户端开发 | 1天 | 2025-12-18 | 2025-12-18 | HTTP客户端封装 |
| 状态管理改造 | 2天 | 2025-12-18 | 2025-12-20 | Store API集成 |
| 组件改造 | 2天 | 2025-12-20 | 2025-12-22 | 组件API调用 |
| 错误处理 | 1天 | 2025-12-22 | 2025-12-23 | 全局错误处理 |
| 测试和优化 | 2天 | 2025-12-23 | 2025-12-25 | 测试报告和优化 |

### 12.2 Dependencies Timeline
- **E001F003_backend-doctor-api-integration**: 需要先完成后端API开发
- **前端测试**: 需要后端API可用才能进行端到端测试

## 13. Appendix

### 13.1 References
- [Parent Epic PRD: E001_mysql-doctor-data-migration](./../epic-prd.md)
- [ASDM API文档](../../../contexts/asdm.api.md)
- [ASDM架构文档](../../../contexts/asdm.architecture.md)
- [Vue 3官方文档](https://v3.vuejs.org/)
- [Fetch API文档](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API)

### 13.2 Glossary
| Term | Definition |
|------|------------|
| JWT | JSON Web Token，用于身份验证的令牌 |
| API客户端 | 封装HTTP请求和响应的客户端模块 |
| 状态管理 | 管理应用状态的模式和机制 |
| 错误处理 | 处理和响应应用错误的机制 |

### 13.3 Revision History
| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2025-12-09 | AI Assistant | 初始草稿 |