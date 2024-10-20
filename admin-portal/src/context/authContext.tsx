import { createContext, ReactNode, useEffect, useState } from "react";
import { AuthContextType } from "../interfaces";

const AuthContext = createContext<AuthContextType | null>(null);


export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<any>(null);

  useEffect(() => {
    const fetchUser = async () => {
    //   const userData = JSON.parse(localStorage.getItem("user")); 
    //   if (userData) {
    //     setUser(userData); 
    //   } else {
    //   }
    };
    fetchUser();
  }, []);

  return (
    <AuthContext.Provider value={{ user, setUser }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
