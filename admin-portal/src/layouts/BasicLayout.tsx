import React, { useState, useEffect } from "react";
import { Layout, ConfigProvider, Select, Card } from "antd";
import ProLayout, { MenuDataItem } from "@ant-design/pro-layout";
import { createIntl, useIntl } from "react-intl";
import { createBrowserHistory } from "history";
import * as allIcons from "@ant-design/icons";
import { Link, Outlet, useLocation } from "react-router-dom";
import { locales, menuList, useTranslate } from "../utils";
import { Preloader } from "../components";
import "./BasicLayout.css";

const { Header, Content, Footer } = Layout;
const { Option } = Select;
const history = createBrowserHistory();

interface CustomIconProps {
  value?: string;
}

const CustomIcon: React.FC<CustomIconProps> = ({ value }) => {
  return <span>{value || "Custom"}</span>;
};

const menuDataRender = (menuList: MenuDataItem[]): MenuDataItem[] =>
  menuList.map((item: MenuDataItem) => {
    const MenuIcon = allIcons[item.icon as keyof typeof allIcons] ||
      allIcons.CrownOutlined || <CustomIcon value="Tùy Chỉnh" />;

    return {
      ...item,
      icon: <MenuIcon />,
      children: item.children ? menuDataRender(item.children) : undefined,
    } as MenuDataItem;
  });

const BasicLayout: React.FC = () => {
  const { formatMessage } = createIntl({ ...useIntl(), onError: () => {} });
  const translate = useTranslate();
  const location = useLocation();
  const [collapsed, setCollapsed] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(true);

  const storedLocale = localStorage.getItem("locale") || "vi-VN";
  const [locale, setLocale] = useState<string>(storedLocale);

  const currentLocale = locales[locale];

  const handleCollapse = (collapsing: boolean) => {
    setCollapsed(collapsing);
    console.log("Menu is collapsed:", collapsing);
  };

  useEffect(() => {
    const loadData = async () => {
      setLoading(true);
      await new Promise((resolve) => setTimeout(resolve, 2000));
      setLoading(false);
    };

    loadData();
  }, []);

  const handleLanguageChange = (value: string) => {
    setLocale(value);
    localStorage.setItem("locale", value);
    window.location.reload();
  };

  if (loading) {
    return <Preloader />;
  }

  return (
    <ConfigProvider locale={currentLocale}>
      <ProLayout
        title={"Krahs"}
        formatMessage={formatMessage}
        onCollapse={handleCollapse}
        onMenuHeaderClick={() => history.push("/")}
        breadcrumbRender={(routers: any[] = []) => [
          {
            path: "/",
            breadcrumbName: formatMessage({ id: "menu.home" }),
          },
          ...routers.map((route) => ({
            ...route,
            path: route?.component ? route?.path : "",
          })),
        ]}
        menuDataRender={() => menuDataRender(menuList)}
        menuItemRender={(menuItemProps: any, defaultDom: any) => {
          if (
            menuItemProps.isUrl ||
            !menuItemProps.path ||
            location.pathname === menuItemProps.path
          ) {
            return defaultDom;
          }
          return <Link to={menuItemProps.path}>{defaultDom}</Link>;
        }}
        itemRender={(route: any, params: any, routes: any[], paths: any) => {
          if (
            routes.indexOf(route) < routes.length - 1 &&
            route?.path.length > 0
          ) {
            return <Link to={`/${paths}`}>{route.breadcrumbName}</Link>;
          }
          return <span>{route.breadcrumbName}</span>;
        }}
      >
        <Layout>
          <Header className="headerBasicLayout">
            <div className="logo" />
            <Select
              value={locale}
              style={{ width: 120 }}
              onChange={handleLanguageChange}
            >
              <Option value="vi-VN">{translate("language.vn")}</Option>
              <Option value="en-US">{translate("language.en")}</Option>
            </Select>
          </Header>
          <Content className="contextBasicLayout">
            <Card className="outletBasicLayout">
              <Outlet />
            </Card>
          </Content>
          <Footer style={{ textAlign: "center" }}>
            ©{new Date().getFullYear()} Bản Quyền Thuộc Về Cửa Hàng
          </Footer>
        </Layout>
      </ProLayout>
    </ConfigProvider>
  );
};

export default BasicLayout;
