import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios';

// API 响应包装类型
interface ApiResponse<T = any> {
  data: T;
  message?: string;
  success?: boolean;
}

// 创建 axios 实例
const service: AxiosInstance = axios.create({
  baseURL: '/api', // 使用相对路径，通过 Vite proxy 转发到后端
  timeout: 10000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json;charset=UTF-8'
  }
});

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 可以在这里添加 token 等认证信息
    // const token = localStorage.getItem('token');
    // if (token) {
    //   config.headers.Authorization = `Bearer ${token}`;
    // }
    return config;
  },
  (error) => {
    console.error('Request error:', error);
    return Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (response: AxiosResponse<ApiResponse>) => {
    const { data } = response;
    // 这里可以根据后端返回的数据结构进行统一处理
    return data;
  },
  (error) => {
    console.error('Response error:', error);
    
    // 统一错误处理
    if (error.response) {
      // 服务器返回错误状态码
      const { status, data } = error.response;
      
      switch (status) {
        case 400:
          console.error('Bad Request:', data.message || '请求参数错误');
          break;
        case 401:
          console.error('Unauthorized:', data.message || '未授权访问');
          // 可以跳转到登录页面
          break;
        case 403:
          console.error('Forbidden:', data.message || '无权限访问');
          break;
        case 404:
          console.error('Not Found:', data.message || '请求资源不存在');
          break;
        case 500:
          console.error('Server Error:', data.message || '服务器内部错误');
          break;
        default:
          console.error(`Error ${status}:`, data.message || '未知错误');
      }
    } else if (error.request) {
      // 请求发送失败
      console.error('Network Error:', '网络连接失败，请检查网络设置');
    } else {
      // 其他错误
      console.error('Error:', error.message);
    }
    
    return Promise.reject(error);
  }
);

// 封装请求方法
const request = {
  get<T = any>(url: string, config?: AxiosRequestConfig): Promise<T> {
    return service.get(url, config);
  },
  
  post<T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<T> {
    return service.post(url, data, config);
  },
  
  put<T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<T> {
    return service.put(url, data, config);
  },
  
  delete<T = any>(url: string, config?: AxiosRequestConfig): Promise<T> {
    return service.delete(url, config);
  }
};

export default request;