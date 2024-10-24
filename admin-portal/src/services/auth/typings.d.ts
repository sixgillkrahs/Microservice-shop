declare namespace API {
  type RequestLogin = {
    email: string;
    password: string;
  }
  type GetUserPaging = {
     sortType?: number,
     sortBy?: string,
     pageIndex?: number,
     pageSize?: number, 
  }
}
