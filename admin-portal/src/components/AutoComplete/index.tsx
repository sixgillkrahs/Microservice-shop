import React from "react";
import { AutoComplete as AAutoComplete } from "antd";
import { AutoCompleteProps } from "antd";

const AutoComplete: React.FC<AutoCompleteProps> = (props) => {
  return <AAutoComplete {...props} />;
};

export { AutoComplete };
