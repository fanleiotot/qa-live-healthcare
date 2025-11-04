import axios from 'axios';

export const getDoctors = async () => {
  const response = await axios.get('/api/doctors');
  return response.data;
};

export const getDoctorById = async (id: string) => {
  const response = await axios.get(`/api/doctors/${id}`);
  return response.data;
};
