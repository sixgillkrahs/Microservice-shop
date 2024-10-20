import React from "react";
import { isObject } from "lodash";
import { Table as ATable } from "antd";
import type { TableProps, TableColumnProps } from "antd";
import type { ColumnGroupProps } from "antd/lib/table/ColumnGroup";
// import { translate } from "@/utils";

const Table: React.FC<TableProps<any>> = ({
  scroll,
  pagination = true,
  ...props
}) => {
  return (
    <ATable
      {...props}
      scroll={scroll || { x: "max-content" }}
      pagination={
        pagination && {
          size: "small",
          defaultPageSize: 20,
          showTotal: (total: number, range: number[]) => (
            <div>
              {/* {translate(
                "protable.showTotal.text",
                "{from}-{to} trên {total} kết quả",
                {
                  from: String(range[0] || 0),
                  to: String(range[1] || 0),
                  total: String(total || 0),
                }
              )} */}
            </div>
          ),
          ...(isObject(pagination) ? { ...pagination } : {}),
        }
      }
    />
  );
};

const TableColumn: React.FC<TableColumnProps<any>> = (props) => {
  return <ATable.Column {...props} />;
};

const TableColumnGroup: React.FC<ColumnGroupProps<any>> = ({
  children,
  ...props
}) => {
  return <ATable.ColumnGroup {...props}>{children}</ATable.ColumnGroup>;
};

export { Table, TableColumn, TableColumnGroup };
