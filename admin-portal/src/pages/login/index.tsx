import {
  AlipayOutlined,
  LockOutlined,
  MobileOutlined,
  TaobaoOutlined,
  UserOutlined,
} from "@ant-design/icons";
import "./index.css";
import {
  LoginFormPage,
  ProFormText,
  ProFormCaptcha,
  ProFormCheckbox,
  ProConfigProvider,
} from "@ant-design/pro-components";
import { Tabs, message, theme } from "antd";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useTranslate } from "../../utils";
import { login } from "../../services/auth/api";

type LoginType = "phone" | "account";

const Login = () => {
  const [loginType, setLoginType] = useState<LoginType>("account");
  const { token } = theme.useToken();
  const navigate = useNavigate();
  const translate = useTranslate();
  const [messagelog, setMessageLog] = useState<string>();
  const onFinish = async (formData: any) => {
    const resp = await login(formData);
    console.log(resp);
    if (resp.success) {
      localStorage.setItem("token", resp.token);
      navigate("/");
    } else {
      setMessageLog(translate("form.notice.login"));
    }
  };

  return (
    <div
      style={{
        backgroundColor: "white",
        height: "100vh",
      }}
    >
      <LoginFormPage
        backgroundImageUrl="https://mdn.alipayobjects.com/huamei_gcee1x/afts/img/A*y0ZTS6WLwvgAAAAAAAAAAAAADml6AQ/fmt.webp"
        logo="https://github.githubassets.com/favicons/favicon.png"
        backgroundVideoUrl="https://gw.alipayobjects.com/v/huamei_gcee1x/afts/video/jXRBRK_VAwoAAAAAAAAAAAAAK4eUAQBr"
        title="Krahs"
        containerStyle={{
          backgroundColor: "rgba(0, 0, 0,0.65)",
          backdropFilter: "blur(4px)",
        }}
        subTitle={translate("form.title.welcome")}
        submitter={{
          searchConfig: {
            submitText: translate("form.title.submit"),
          },
        }}
        message={messagelog}
        onFinish={onFinish}
      >
        <Tabs
          centered
          activeKey={loginType}
          onChange={(activeKey) => setLoginType(activeKey as LoginType)}
        >
          <Tabs.TabPane key={"account"} tab={translate("form.title.account")} />
          <Tabs.TabPane key={"phone"} tab={translate("form.title.phone")} />
        </Tabs>
        {loginType === "account" && (
          <>
            <ProFormText
              name="email"
              fieldProps={{
                size: "large",
                prefix: (
                  <UserOutlined
                    style={{
                      color: token.colorText,
                    }}
                    className={"prefixIcon"}
                  />
                ),
              }}
              placeholder={translate("form.placeholder.username")}
              rules={[
                {
                  required: true,
                  message: translate("form.required"),
                },
              ]}
            />
            <ProFormText.Password
              name="password"
              fieldProps={{
                size: "large",
                prefix: (
                  <LockOutlined
                    style={{
                      color: token.colorText,
                    }}
                    className={"prefixIcon"}
                  />
                ),
              }}
              placeholder={translate("form.placeholder.password")}
              rules={[
                {
                  required: true,
                  message: translate("form.required"),
                },
              ]}
            />
          </>
        )}
        {loginType === "phone" && (
          <>
            <ProFormText
              fieldProps={{
                size: "large",
                prefix: (
                  <MobileOutlined
                    style={{
                      color: token.colorText,
                    }}
                    className={"prefixIcon"}
                  />
                ),
              }}
              name="mobile"
              placeholder={translate("form.placeholder.phone")}
              rules={[
                {
                  required: true,
                  message: translate("form.required"),
                },
                {
                  pattern: /^1\d{10}$/,
                  message: translate("form.validate.phone"),
                },
              ]}
            />
            <ProFormCaptcha
              fieldProps={{
                size: "large",
                prefix: (
                  <LockOutlined
                    style={{
                      color: token.colorText,
                    }}
                    className={"prefixIcon"}
                  />
                ),
              }}
              captchaProps={{
                size: "large",
              }}
              placeholder={translate("form.placeholder.capcha")}
              captchaTextRender={(timing, count) => {
                if (timing) {
                  return `${count} ${translate("form.title.resend")}`;
                }
                return translate("form.title.otp");
              }}
              name="captcha"
              rules={[
                {
                  required: true,
                  message: translate("form.required"),
                },
              ]}
              onGetCaptcha={async () => {
                message.success(translate("form.message.sendotp"));
              }}
            />
          </>
        )}
        <div
          style={{
            marginBlockEnd: 24,
          }}
        >
          <ProFormCheckbox noStyle name="autoLogin">
            {translate("form.rememberPassword")}
          </ProFormCheckbox>
          <a
            style={{
              float: "right",
            }}
          >
            {translate("form.forgotPassword")}
          </a>
        </div>
        {/* <Button type="primary" htmlType="submit">
          Đăng nhập
        </Button> */}
      </LoginFormPage>
    </div>
  );
};

export default () => {
  return (
    <ProConfigProvider dark>
      <Login />
    </ProConfigProvider>
  );
};
