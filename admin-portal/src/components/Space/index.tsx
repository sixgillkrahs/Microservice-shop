import React from "react";
import { Space as ASpace } from "antd";
import type { SpaceProps } from "antd";

const Space: React.FC<SpaceProps> = (props) => {
  return <ASpace {...props} />;
};

export { Space };
