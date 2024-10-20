import "./App.css";
import { Routes } from "./utils";
import { IntlProvider } from "react-intl";
import messages from "./locales";
import { ConfigProvider } from "antd";

function App() {
  const locale = localStorage.getItem("locale") || "vi-VN";
  return (
    <IntlProvider locale="locale" messages={messages[locale]}>
      <ConfigProvider
        theme={{
          token: {
            colorPrimary: "#ff0339",
          },
        }}
      >
        <Routes />
      </ConfigProvider>
    </IntlProvider>
  );
}

export default App;
