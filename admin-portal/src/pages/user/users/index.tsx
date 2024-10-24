import React, { useEffect, useState } from "react";
import { Table } from "../../../components/ProTable";
import { ProColumns } from "@ant-design/pro-table";
import { getUserPaging } from "../../../services/user/api";
import { useTranslate } from "../../../utils";
import moment from "moment";

const User = () => {
  const translate = useTranslate();
  const [users, setUsers] = useState<any>();
  const [loading, setLoading] = useState<boolean>(true);

  const getUsers = async () => {
    const resp = await getUserPaging({});
    if (resp.success) {
      setLoading(false);
      setUsers(resp.data);
    } else {
      setLoading(false);
    }
    console.log(resp);
  };

  useEffect(() => {
    getUsers();
  }, []);

  const onAdd = () => {
    console.log("oke");
  };
  const columns: ProColumns<Record<string, any>, "text">[] = [
    {
      key: "id",
      title: "STT",
      dataIndex: "id",
      search: false,
      renderText: (text, record, index) => {
        return index + 1;
      },
    },
    {
      key: "fullName",
      title: translate("form.title.fullName"),
      dataIndex: "fullName",
      onFilter: (value, record) => record.name.includes(value),
    },
    {
      key: "phone",
      title: translate("form.title.phone"),
      dataIndex: "phone",
      onFilter: (value, record) => record.name.includes(value),
    },
    {
      key: "dob",
      title: translate("form.title.dob"),
      dataIndex: "dob",
      search: false,
      onFilter: (value, record) => record.name.includes(value),
      renderText: (text) => {
        return moment(text).format("DD/MM/YYYY");
      },
    },
    {
      key: "email",
      title: translate("form.title.email"),
      dataIndex: "email",
      search: true,
      onFilter: (value, record) => record.name.includes(value),
    },
  ];
  return (
    <Table
      exportExcel
      columns={columns}
      dataSourse={users}
      addButton
      editButton
      deleteButton
      buttonAddFunc={onAdd}
      key={"user"}
      loading={loading}
    />
  );
};

export default User;
