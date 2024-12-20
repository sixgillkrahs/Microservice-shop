import React, { useState } from "react";
import { Input } from "antd";
import { InputNumber } from "antd";
import "./index.css";

interface Props {
  label?: string | number | React.ReactNode;
  placeholder?: string | number | React.ReactNode;
  value?: any;
  type?: string;
  required?: boolean;
  maxlength?: number;
  onChange?: (val: any) => void;
}

const FloatInput: React.FC<Props> = ({
  label,
  placeholder,
  value,
  type,
  required,
  maxlength,
  onChange,
}) => {
  const [focus, setFocus] = useState(false);
  const replaceHolder = placeholder || label;
  const isOccupied = focus || (value && value.length !== 0);
  const labelClass = isOccupied ? "label as-label" : "label as-placeholder";
  const requiredMark = required ? <span className="text-danger">*</span> : null;


  const handleChange = (val: any) => {
    if (onChange) {
      onChange(val);
    }
  };

  return (
    <div
      className="float-label"
      onBlur={() => setFocus(false)}
      onFocus={() => setFocus(true)}
    >
      {type === "number" ? (
        <InputNumber min={1} max={100} onChange={onChange} value={value} />
      ) : (
        <Input
          onChange={(e) => handleChange(e.target.value)}
          type={type}
          value={value}
          maxLength={maxlength}
          size="large"
        />
      )}
      <label className={labelClass}>
        {isOccupied ? label : replaceHolder} {requiredMark}
      </label>
    </div>
  );
};

export { FloatInput };
