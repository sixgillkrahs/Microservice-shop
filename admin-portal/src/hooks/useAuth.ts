import { useContext } from "react";
import AuthContext from "../context/authContext";

// Custom Hook để sử dụng context
export const useAuth = () => {
  return useContext(AuthContext);
};
