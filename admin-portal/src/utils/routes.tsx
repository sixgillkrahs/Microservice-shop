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

const childRoutes: RouteObject[] = [
  {
    path: "",
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

const mainRoutes: RouteObject[] = [
  {
    path: "/",
    element: <BasicLayout />,
    children: childRoutes,
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
