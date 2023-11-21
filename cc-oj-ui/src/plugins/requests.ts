import  axios, { AxiosInstance, AxiosResponse } from "axios";
const instance:AxiosInstance = axios.create({
    baseURL: 'http://localhost:8888/api',
    timeout: 3000,
    headers: {
        'Content-Type': 'application/json', 
    },
})
instance.defaults.withCredentials = true // 允许携带cookie
instance.interceptors.request.use((config: any) => {
    return config;
},err => {
    return Promise.reject(err);
})


instance.interceptors.response.use((res: AxiosResponse) => {
    const response = res.data;
    console.log(response);
    
    return response
}, err => {
    return Promise.reject(err);
})

export default instance;