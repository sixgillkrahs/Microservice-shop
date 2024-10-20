import React from "react";
import { Form as AForm } from "antd";
import type { FormProps } from "antd";
import type {
  FormItemProps,
  FormListProps,
  ErrorListProps,
} from "antd/lib/form";
import { FormProviderProps } from "antd/lib/form/context";


const Form: React.FC<FormProps> = (props) => {
  return <AForm {...props} />;
};


const FormItem: React.FC<FormItemProps> = (props) => {
  return <AForm.Item {...props} />;
};

const FormList: React.FC<FormListProps> = ({ name, children }) => {
  return <AForm.List name={name}>{children}</AForm.List>;
};

const FormErrorList: React.FC<ErrorListProps> = (props) => {
  return <AForm.ErrorList {...props} />;
};

const FormProvider: React.FC<FormProviderProps> = (props) => {
  return <AForm.Provider {...props} />;
};


export { Form, FormItem, FormList, FormErrorList, FormProvider };