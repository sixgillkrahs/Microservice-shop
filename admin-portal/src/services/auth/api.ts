import { apiClient } from "../../utils";

const login = async (data : API.RequestLogin) => {
    const resp: any = await apiClient.post("auth/login",data)
    return {
        token: resp.data,
        success: resp?.code === 1  ,
        message: resp.message
    }
}

export {login}