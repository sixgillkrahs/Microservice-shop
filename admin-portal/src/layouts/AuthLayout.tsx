import { Card, ConfigProvider, Layout, Select } from "antd";
import { useState } from "react";
import { locales, useTranslate } from "../utils";
import { Outlet } from "react-router-dom";

const { Header, Content, Footer } = Layout;
const { Option } = Select;

const AuthLayout = () => {
  const storedLocale = localStorage.getItem("locale") || "vi-VN";
  const [locale, setLocale] = useState<string>(storedLocale);
  const translate = useTranslate();
  const currentLocale = locales[locale];

  const handleLanguageChange = (value: string) => {
    setLocale(value);
    localStorage.setItem("locale", value);
    window.location.reload();
  };

  return (
    <ConfigProvider locale={currentLocale}>
      <Layout>
        <Header
          style={{
            background: "#fff",
            padding: 0,
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          {" "}
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
        <Content style={{ margin: "24px 16px 0" }}>
          <Card style={{ padding: 24, background: "#fff", minHeight: 360 }}>
            <Outlet />
          </Card>
        </Content>
        <Footer style={{ textAlign: "center" }}>
          ©{new Date().getFullYear()} Bản Quyền Thuộc Về Cửa Hàng
        </Footer>
      </Layout>
    </ConfigProvider>
  );
};

export default AuthLayout;
