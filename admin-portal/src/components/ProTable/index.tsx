import React, { useEffect, useRef } from "react";
import type { ActionType, ProColumns } from "@ant-design/pro-table";
import { ProTable } from "@ant-design/pro-table";
import { ProFormSelect } from "@ant-design/pro-form";
import { Button, Popconfirm, Space } from "antd";
import {
  DeleteOutlined,
  EditOutlined,
  FileExcelOutlined,
  PlusOutlined,
} from "@ant-design/icons";
import { useTranslate } from "../../utils";

interface TableRequest {
  columns: any;
  dataSourse: any;
  loading?: boolean;
  reloadTable?: boolean;
  addButton?: boolean;
  editButton?: boolean;
  deleteButton?: boolean;
  exportExcel?: boolean;
  buttonAddFunc?: () => void;
  exportExcelFunc?: () => void;
  extraButtons?: (
    row?: any,
    reloadFunc?: () => void,
    formModal?: any
  ) => void | React.ReactNode | React.ReactNode[];
  tableStyle?: React.CSSProperties;
  rowKey?: string;
  scroll?: { x?: any; y?: any };
}

export const Table = ({
  columns,
  dataSourse,
  loading,
  reloadTable,
  addButton,
  editButton,
  deleteButton,
  exportExcel,
  extraButtons,
  buttonAddFunc,
  exportExcelFunc,
  tableStyle = {
    width: "100%",
    overflow: "hidden",
    overflowX: "auto",
  },
  rowKey,
  scroll,
}: TableRequest) => {
  const translate = useTranslate();
  const ref = useRef<ActionType>();
  const reloadGrid = () => {
    ref.current?.reload?.(false);
  };

  useEffect(() => {
    reloadGrid();
  }, [reloadTable]);
  const columnPlus: ProColumns[] = [
    ...columns,
    {
      search: false,
      title: "Action",
      render: (row: any, record: any) => {
        return (
          <Space size={"middle"}>
            {editButton && (
              <EditOutlined
                title={translate("form.button.edit")}
                onClick={() => {
                  console.log(record);
                  // handleModalVisible(true);
                  // setCurrentRow(record);
                  // onModalLoad?.(record, formModal);
                }}
              />
            )}
            {deleteButton && (
              <Popconfirm
                title={
                  <div style={{ whiteSpace: "pre" }}>
                    {translate("form.message.delete.confirmText")}
                  </div>
                }
                okText={translate("form.button.delete")}
                cancelText={translate("form.button.cancel")}
                okButtonProps={{ danger: true }}
                onConfirm={async () => {
                  // if (!removeFunc) return;
                  // const resp: any = await removeFunc?.(record);
                  // if (resp?.success)
                  //   message.success(translate("form.delete.success.message"));
                  // else
                  //   notification.error(
                  //     resp?.message || translate("form.delete.failed.message")
                  //   );
                  reloadGrid();
                }}
              >
                <DeleteOutlined
                  title={translate("form.button.delete")}
                  style={{ color: "red" }}
                />
              </Popconfirm>
            )}
          </Space>
        );
      },
    },
  ];
  return (
    <ProTable
      columns={columnPlus}
      dataSource={dataSourse}
      loading={loading}
      rowKey={rowKey || "id"}
      scroll={scroll || { x: 1000 }}
      //   cardProps={{
      //     title: "quan",

      //   }}
      //   searchFormRender={(props) => <ProFormSelect />}
      search={{
        filterType: "light",
      }}
      toolBarRender={() => [
        addButton && (
          <Button
            // hidden={hideAddButton}
            type="primary"
            ghost
            key="toolbarAddButton"
            onClick={() => {
              if (buttonAddFunc) {
                buttonAddFunc();
              }
            }}
          >
            {
              <>
                <PlusOutlined /> {translate("form.button.addNew")}
              </>
            }
          </Button>
        ),
        exportExcel && (
          <Button
            type="primary"
            ghost
            key="toolbarExportButton"
            // loading={loading}
            onClick={async () => {
              if (exportExcelFunc) {
                await exportExcelFunc();
              }
            }}
          >
            <FileExcelOutlined /> {translate("form.button.export")}
          </Button>
        ),
      ]}
      locale={{
        filterTitle: "Filter",
        filterConfirm: "OK",
        filterReset: "Reset",
        emptyText: "No Data",
        selectAll: "Select All",
        selectInvert: "Invert Selection",
        // selectionAriaLabel: "Select Rows",
      }}
      pagination={{
        // current: currentPageNumber,
        // pageSize: currentPageSize,
        // total: meta?.total,
        showTotal: (total, range) => (
          <div>{`Showing ${range[0]}-${range[1]} of ${total} total items`}</div>
        ),
      }}
      tableStyle={tableStyle}
      toolbar={{
        title: "",
        style: {
          width: "100%",
          overflow: "hidden",
          overflowX: "auto",
        },
      }}
    ></ProTable>
  );
};
