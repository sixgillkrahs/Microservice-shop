import axios from "axios";

const API_BASE_URL = "http://localhost:4567/api/";

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    timeout: 5000 
});

apiClient.interceptors.request.use(
    (requestConfig) => {
        const authToken = localStorage.getItem("token");
        if (authToken) {
            requestConfig.headers["Authorization"] = `Bearer ${authToken}`;
        }
        return requestConfig;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export { apiClient };