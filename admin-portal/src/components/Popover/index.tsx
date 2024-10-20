import React from "react";
import { Popover as APopover } from "antd";
import type { PopoverProps } from "antd";

const Popover: React.FC<PopoverProps> = (props) => {
  return <APopover {...props} />;
};

export { Popover };
