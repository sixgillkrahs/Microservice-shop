import { MenuDataItem } from "@ant-design/pro-layout";

const getMenuList = (translate: Function): MenuDataItem[] => [
  {
    path: "/",
    name: translate("menu.dashboard"), 
    icon: "HomeOutlined", 
  },
  // {
  //   path: "/a",
  //   name: translate("menu.products"), 
  //   icon: "AppstoreOutlined",
  //   children: [
  //     {
  //       path: "/products/new",
  //       name: translate("menu.addProduct"),
  //       icon: "PlusOutlined",
  //     },
  //     {
  //       path: "/products/list",
  //       name: translate("menu.productList"), 
  //       icon: "OrderedListOutlined",
  //     },
  //   ],
  // },
  {
    path: "/user",
    name: translate("menu.usermanagement"),
    icon: "HomeOutlined", 
    children : [
       {
        path: "/user/users",
        name: translate("menu.usermanagement.user"), 
        icon: "UserOutlined",
      },
      {
        path: "/user/employees",
        name: translate("menu.usermanagement.employees"), 
        icon: "OrderedListOutlined",
      },
    ]
  },
];

export default getMenuList;