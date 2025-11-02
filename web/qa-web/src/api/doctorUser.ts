import axios from 'axios';

interface Doctor {
  id: string;
  name: string;
  specialty: string;
  isActive: boolean;
}

const API_BASE_URL = '/api';

export const fetchAllDoctors = async (): Promise<Doctor[]> => {
  const response = await axios.get(`${API_BASE_URL}/doctors`);
  return response.data;
};

export const fetchDoctorById = async (id: string): Promise<Doctor> => {
  const response = await axios.get(`${API_BASE_URL}/doctors/${id}`);
  return response.data;
};

export const fetchActiveDoctors = async (): Promise<Doctor[]> => {
  const response = await axios.get(`${API_BASE_URL}/doctors/active`);
  return response.data;
};

export const createDoctor = async (doctor: Omit<Doctor, 'id'>): Promise<Doctor> => {
  const response = await axios.post(`${API_BASE_URL}/doctors`, doctor);
  return response.data;
};

export const updateDoctor = async (id: string, doctor: Partial<Doctor>): Promise<Doctor> => {
  const response = await axios.put(`${API_BASE_URL}/doctors/${id}`, doctor);
  return response.data;
};

export const deleteDoctor = async (id: string): Promise<void> => {
  await axios.delete(`${API_BASE_URL}/doctors/${id}`);
};
