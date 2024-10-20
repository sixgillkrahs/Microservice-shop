import React from "react";
import { Radio as ARadio } from "antd";
import type { RadioProps, RadioGroupProps } from "antd";
import type { RadioButtonProps } from "antd/lib/radio/radioButton";

const Radio: React.FC<RadioProps> = (props) => {
  return <ARadio {...props} />;
};

const RadioGroup: React.FC<RadioGroupProps> = (props) => {
  return <ARadio.Group {...props} />;
};

const RadioButton: React.FC<RadioButtonProps> = (props) => {
  return <ARadio.Button {...props} />;
};

export { Radio, RadioGroup, RadioButton };
