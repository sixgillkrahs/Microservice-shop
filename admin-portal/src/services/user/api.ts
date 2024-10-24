import { apiClient } from "../../utils";

const getUserPaging = async (params : API.GetUserPaging) => {
    const resp: any = await apiClient.get("users/list-users-paging",{params})
    return {
        data: resp.data?.data?.content,
        success: resp?.data?.code === 200,
        message: resp?.data?.message,
        pageSize: resp?.data?.data?.pageable?.pageSize,
        pageIndex: resp?.data?.data?.pageable?.pageNumber,
    }
}


export {getUserPaging}