import React from "react";
import { Switch as ASwitch } from "antd";
import type { SwitchProps } from "antd";

const Switch: React.FC<SwitchProps> = (props) => {
  return <ASwitch {...props} />;
};

export { Switch };
