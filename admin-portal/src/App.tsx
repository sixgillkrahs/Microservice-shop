import "./App.css";
import { Routes } from "./utils";
import { IntlProvider } from "react-intl";
import messages from "./locales";
import { ConfigProvider } from "antd";
import { ProConfigProvider } from "@ant-design/pro-components";
import viVN from "antd/locale/vi_VN";

function App() {
  const locale = localStorage.getItem("locale") || "vi-VN";

  return (
    <IntlProvider
      locale={locale}
      messages={messages[locale]}
      defaultLocale={locale}
    >
      <ProConfigProvider dark={false}>
        <ConfigProvider >
          <Routes />
        </ConfigProvider>
      </ProConfigProvider>
    </IntlProvider>
  );
}

export default App;
