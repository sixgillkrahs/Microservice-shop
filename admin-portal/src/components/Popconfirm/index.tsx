import React from "react";
import { Popconfirm as APopconfirm } from "antd";
import type { PopconfirmProps } from "antd";

const Popconfirm: React.FC<PopconfirmProps> = (props) => {
  return <APopconfirm {...props} />;
};

export { Popconfirm };
