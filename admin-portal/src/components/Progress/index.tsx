import React from "react";
import { Progress as AProgress } from "antd";
import type { ProgressProps } from "antd";

const Progress: React.FC<ProgressProps> = (props) => {
  return <AProgress {...props} />;
};

export { Progress };
