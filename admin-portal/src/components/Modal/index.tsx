import React from "react";
import { Modal as AModal } from "antd";
import type { ModalProps } from "antd";

const Modal: React.FC<ModalProps> = (props) => {
  return <AModal {...props} />;
};

export { Modal };
