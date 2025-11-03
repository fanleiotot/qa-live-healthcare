/**
 * 医生用户响应数据类型
 */
export interface DoctorUserResponse {
  id: string;
  username: string;
  name: string;
  title: string;
  department: string;
  avatar: string;
  experience: string;
  specialties: string[];
  isActive: boolean;
  createdAt?: string;
  updatedAt?: string;
}

/**
 * 医生用户创建请求数据类型
 */
export interface DoctorUserCreateRequest {
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

/**
 * 医生用户更新请求数据类型
 */
export interface DoctorUserUpdateRequest {
  name: string;
  title: string;
  department: string;
  avatar: string;
  experience: string;
  specialties: string[];
  isActive: boolean;
}

/**
 * 医生用户登录请求数据类型
 */
export interface DoctorUserLoginRequest {
  username: string;
  password: string;
}

/**
 * 医生用户登录响应数据类型
 */
export interface DoctorUserLoginResponse {
  token: string;
  id: string;
  username: string;
  name: string;
  title: string;
  department: string;
  avatar: string;
  experience: string;
  specialties: string[];
  isActive: boolean;
  loginTime: string;
}

/**
 * 测试响应数据类型
 */
export interface TestResponse {
  message: string;
  timestamp: number;
  service: string;
  receivedData?: Record<string, any>;
}

/**
 * 健康检查响应数据类型
 */
export interface HealthResponse {
  status: string;
  components: Record<string, {
    status: string;
    details?: Record<string, any>;
  }>;
}

/**
 * 应用信息响应数据类型
 */
export interface InfoResponse {
  app: {
    name: string;
    description: string;
    version: string;
    encoding: string;
    java: {
      version: string;
    };
  };
  team: string;
  environment: string;
  build: {
    timestamp: string;
  };
  features: string;
  java: {
    version: string;
    vendor: string;
  };
  os: {
    name: string;
    version: string;
    arch: string;
  };
}

/**
 * API 统一响应包装类型
 */
export interface ApiResponse<T = any> {
  data: T;
  message?: string;
  success?: boolean;
  timestamp?: string;
  status?: number;
}

/**
 * 分页响应数据类型
 */
export interface PageResponse<T = any> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
  numberOfElements: number;
  empty: boolean;
}

/**
 * 分页请求参数类型
 */
export interface PageRequest {
  page?: number;
  size?: number;
  sort?: string;
  direction?: 'ASC' | 'DESC';
}