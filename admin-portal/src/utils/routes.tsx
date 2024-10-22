import {
  createBrowserRouter,
  RouteObject,
  RouterProvider,
} from "react-router-dom";
import { AuthLayout, BasicLayout } from "../layouts";
import Home from "../pages/home";
import Test from "../pages/test";
import NoFoundPage from "../pages/404";
import Login from "../pages/login";
import { App } from "antd";
import DashBoard from "../pages/dashboard";
import User from "../pages/user/users";
import Employee from "../pages/user/employees";

const childRoutes: RouteObject[] = [
  {
    path: "",
    element: <DashBoard />,
  },
  {
    path: "product",
    element: <Home />,
  },
  {
    path: "test",
    element: <Test />,
  },
];

const authRoutes: RouteObject[] = [
  {
    path: "login",
    element: <Login />,
  },
];

const userRoutes: RouteObject[] = [
  {
    path: "users",
    element: <User />,
  },
  {
    path: "employees",
    element: <Employee />,
  },
];

const mainRoutes: RouteObject[] = [
  {
    path: "/",
    element: <BasicLayout />,
    children: childRoutes,
  },
  {
    path: "/user/",
    element: <BasicLayout />,
    children: userRoutes,
  },
  {
    path: "/auth/",
    element: <AuthLayout />,
    children: authRoutes,
  },
  {
    path: "*",
    element: <NoFoundPage />,
  },
];

const rootRoute = createBrowserRouter(mainRoutes);

const Routes = () => {
  return (
    <App>
      <RouterProvider router={rootRoute} />
    </App>
  );
};

export default Routes;
