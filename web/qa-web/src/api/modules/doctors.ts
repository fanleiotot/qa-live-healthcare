import request from '../request';
import type { 
  DoctorUserResponse, 
  DoctorUserCreateRequest, 
  DoctorUserUpdateRequest,
  DoctorUserLoginRequest,
  DoctorUserLoginResponse
} from '../types';

/**
 * 获取所有医生用户
 */
export const getAllDoctors = (): Promise<DoctorUserResponse[]> => {
  return request.get('/doctors');
};

/**
 * 获取所有激活的医生用户
 */
export const getActiveDoctors = (): Promise<DoctorUserResponse[]> => {
  return request.get('/doctors/active');
};

/**
 * 根据ID获取医生用户
 */
export const getDoctorById = (id: string): Promise<DoctorUserResponse> => {
  return request.get(`/doctors/${id}`);
};

/**
 * 根据用户名获取医生用户
 */
export const getDoctorByUsername = (username: string): Promise<DoctorUserResponse> => {
  return request.get(`/doctors/username/${username}`);
};

/**
 * 根据科室获取医生用户
 */
export const getDoctorsByDepartment = (department: string): Promise<DoctorUserResponse[]> => {
  return request.get(`/doctors/department/${department}`);
};

/**
 * 搜索医生用户
 */
export const searchDoctors = (keyword: string): Promise<DoctorUserResponse[]> => {
  return request.get(`/doctors/search`, {
    params: { keyword }
  });
};

/**
 * 获取所有科室列表
 */
export const getDepartments = (): Promise<string[]> => {
  return request.get('/doctors/departments');
};

/**
 * 统计激活的医生用户数量
 */
export const getActiveDoctorCount = (): Promise<Record<string, number>> => {
  return request.get('/doctors/stats/active-count');
};

/**
 * 检查用户名是否存在
 */
export const checkUsernameExists = (username: string): Promise<Record<string, boolean>> => {
  return request.get(`/doctors/exists/${username}`);
};

/**
 * 创建医生用户
 */
export const createDoctor = (data: DoctorUserCreateRequest): Promise<DoctorUserResponse> => {
  return request.post('/doctors', data);
};

/**
 * 医生用户登录
 */
export const loginDoctor = (data: DoctorUserLoginRequest): Promise<DoctorUserLoginResponse> => {
  return request.post('/doctors/login', data);
};

/**
 * 更新医生用户信息
 */
export const updateDoctor = (id: string, data: DoctorUserUpdateRequest): Promise<DoctorUserResponse> => {
  return request.put(`/doctors/${id}`, data);
};

/**
 * 删除医生用户
 */
export const deleteDoctor = (id: string): Promise<void> => {
  return request.delete(`/doctors/${id}`);
};

// 医生相关 API 统一导出
export const doctorsApi = {
  getAllDoctors,
  getActiveDoctors,
  getDoctorById,
  getDoctorByUsername,
  getDoctorsByDepartment,
  searchDoctors,
  getDepartments,
  getActiveDoctorCount,
  checkUsernameExists,
  createDoctor,
  loginDoctor,
  updateDoctor,
  deleteDoctor
};

export default doctorsApi;