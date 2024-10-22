import { apiClient } from "../../utils";

const login = async (data : API.RequestLogin) => {
    const resp: any = await apiClient.post("auth/login",data)
    return {
        token: resp.data?.data,
        success: resp?.data?.code === 200,
        message: resp?.data?.message
    }
}

const validate = async (token : string) => {
    const resp: any = await apiClient.get(`auth/validate?token=${token}`)
     return {
        data: resp.data?.data,
        success: resp?.data?.code === 200,
        message: resp?.data?.message
    }
}

export {login ,validate}