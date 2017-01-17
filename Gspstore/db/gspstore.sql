/*
MySQL Data Transfer
Source Host: localhost
Source Database: gspstore
Target Host: localhost
Target Database: gspstore
Date: 2015-05-21 16:51:46
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for jeecg_dict_param
-- ----------------------------
CREATE TABLE `jeecg_dict_param` (
  `OBID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `DELFLAG` int(11) default NULL,
  `PARAM_LEVEL` varchar(255) collate utf8_unicode_ci default NULL,
  `PARAM_LEVEL_DEC` varchar(255) collate utf8_unicode_ci default NULL,
  `PARAM_NAME` varchar(255) collate utf8_unicode_ci default NULL,
  `PARAM_VALUE` varchar(255) collate utf8_unicode_ci default NULL,
  `PARAM_VIEW_ORDER` int(11) default NULL,
  `REMARK` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`OBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_group
-- ----------------------------
CREATE TABLE `jeecg_group` (
  `obid` varchar(36) collate utf8_unicode_ci NOT NULL,
  `CDESC` varchar(255) collate utf8_unicode_ci default NULL,
  `CNAME` varchar(255) collate utf8_unicode_ci default NULL,
  `CREATE_DT` datetime default NULL,
  `CRTUSER` varchar(255) collate utf8_unicode_ci default NULL,
  `CRTUSER_NAME` varchar(255) collate utf8_unicode_ci default NULL,
  `DEL_DT` datetime default NULL,
  `DELFLAG` int(11) default NULL,
  `MODIFIER` varchar(255) collate utf8_unicode_ci default NULL,
  `MODIFIER_NAME` varchar(255) collate utf8_unicode_ci default NULL,
  `MODIFY_DT` datetime default NULL,
  `CPID` varchar(36) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`obid`),
  KEY `FKA35EFB6EB19DB05C` (`CPID`),
  CONSTRAINT `FKA35EFB6EB19DB05C` FOREIGN KEY (`CPID`) REFERENCES `jeecg_group` (`obid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_note
-- ----------------------------
CREATE TABLE `jeecg_note` (
  `OBID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `content` varchar(255) collate utf8_unicode_ci default NULL,
  `create_dt` datetime default NULL,
  `crtuser` varchar(255) collate utf8_unicode_ci default NULL,
  `crtuser_name` varchar(255) collate utf8_unicode_ci default NULL,
  `title` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`OBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_one_demo
-- ----------------------------
CREATE TABLE `jeecg_one_demo` (
  `obid` varchar(255) collate utf8_unicode_ci NOT NULL,
  `age` int(11) default NULL,
  `birthday` datetime default NULL,
  `create_dt` datetime default NULL,
  `crtuser` varchar(255) collate utf8_unicode_ci default NULL,
  `crtuser_name` varchar(255) collate utf8_unicode_ci default NULL,
  `name` varchar(255) collate utf8_unicode_ci default NULL,
  `register_dt` datetime default NULL,
  `salary` decimal(19,2) default NULL,
  `content` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`obid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_order_custom
-- ----------------------------
CREATE TABLE `jeecg_order_custom` (
  `OBID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `create_dt` datetime default NULL,
  `crtuser` varchar(255) collate utf8_unicode_ci default NULL,
  `crtuser_name` varchar(255) collate utf8_unicode_ci default NULL,
  `del_dt` datetime default NULL,
  `delflag` int(11) default NULL,
  `go_order_code` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_birthday` datetime default NULL,
  `goc_buss_content` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_content` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_cus_name` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_cus_name_en` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_cus_type` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_idcard` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_passport_code` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_passport_end_date` datetime default NULL,
  `goc_room_num` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_sex` varchar(255) collate utf8_unicode_ci default NULL,
  `gorder_obid` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier_name` varchar(255) collate utf8_unicode_ci default NULL,
  `modify_dt` datetime default NULL,
  PRIMARY KEY  (`OBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_order_main
-- ----------------------------
CREATE TABLE `jeecg_order_main` (
  `OBID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `agency_id` varchar(255) collate utf8_unicode_ci default NULL,
  `agency_name` varchar(255) collate utf8_unicode_ci default NULL,
  `auditor_name` varchar(255) collate utf8_unicode_ci default NULL,
  `auditor_obid` varchar(255) collate utf8_unicode_ci default NULL,
  `auditor_status` varchar(255) collate utf8_unicode_ci default NULL,
  `create_dt` datetime default NULL,
  `crtuser` varchar(255) collate utf8_unicode_ci default NULL,
  `crtuser_name` varchar(255) collate utf8_unicode_ci default NULL,
  `del_dt` datetime default NULL,
  `delflag` int(11) default NULL,
  `go_all_price` decimal(19,2) default NULL,
  `go_contact_name` varchar(255) collate utf8_unicode_ci default NULL,
  `go_content` varchar(255) collate utf8_unicode_ci default NULL,
  `go_cus_name` varchar(255) collate utf8_unicode_ci default NULL,
  `go_facsimile` varchar(255) collate utf8_unicode_ci default NULL,
  `go_mail` varchar(255) collate utf8_unicode_ci default NULL,
  `go_order_code` varchar(255) collate utf8_unicode_ci default NULL,
  `go_order_count` int(11) default NULL,
  `go_phone` varchar(255) collate utf8_unicode_ci default NULL,
  `go_return_price` decimal(19,2) default NULL,
  `go_sale_id` varchar(255) collate utf8_unicode_ci default NULL,
  `go_sale_name` varchar(255) collate utf8_unicode_ci default NULL,
  `go_status` varchar(255) collate utf8_unicode_ci default NULL,
  `go_telphone` varchar(255) collate utf8_unicode_ci default NULL,
  `go_zone_no` varchar(255) collate utf8_unicode_ci default NULL,
  `goder_type` varchar(255) collate utf8_unicode_ci default NULL,
  `linkmenid` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier_name` varchar(255) collate utf8_unicode_ci default NULL,
  `modify_dt` datetime default NULL,
  `touristid` varchar(255) collate utf8_unicode_ci default NULL,
  `usertype` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`OBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_order_product
-- ----------------------------
CREATE TABLE `jeecg_order_product` (
  `OBID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `create_dt` datetime default NULL,
  `crtuser` varchar(255) collate utf8_unicode_ci default NULL,
  `crtuser_name` varchar(255) collate utf8_unicode_ci default NULL,
  `del_dt` datetime default NULL,
  `delflag` int(11) default NULL,
  `go_order_code` varchar(255) collate utf8_unicode_ci default NULL,
  `gop_content` varchar(255) collate utf8_unicode_ci default NULL,
  `gop_count` int(11) default NULL,
  `gop_one_price` decimal(19,2) default NULL,
  `gop_product_name` varchar(255) collate utf8_unicode_ci default NULL,
  `gop_product_type` varchar(255) collate utf8_unicode_ci default NULL,
  `gop_sum_price` decimal(19,2) default NULL,
  `gop_unit` varchar(255) collate utf8_unicode_ci default NULL,
  `gorder_obid` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier_name` varchar(255) collate utf8_unicode_ci default NULL,
  `modify_dt` datetime default NULL,
  PRIMARY KEY  (`OBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_tauth
-- ----------------------------
CREATE TABLE `jeecg_tauth` (
  `CID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `CDESC` varchar(200) collate utf8_unicode_ci default NULL,
  `CICONCLS` varchar(20) collate utf8_unicode_ci default NULL,
  `CNAME` varchar(100) collate utf8_unicode_ci NOT NULL,
  `CSEQ` decimal(22,0) default NULL,
  `ctype` varchar(255) collate utf8_unicode_ci default NULL,
  `CURL` varchar(200) collate utf8_unicode_ci default NULL,
  `CPID` varchar(36) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`CID`),
  KEY `FK5AFD30CB985C7649` (`CPID`),
  KEY `FK5AFD30CB98573E92` (`CPID`),
  CONSTRAINT `FK5AFD30CB98573E92` FOREIGN KEY (`CPID`) REFERENCES `jeecg_tauth` (`CID`),
  CONSTRAINT `FK5AFD30CB985C7649` FOREIGN KEY (`CPID`) REFERENCES `jeecg_tauth` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_tbug
-- ----------------------------
CREATE TABLE `jeecg_tbug` (
  `CID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `CCREATEDATETIME` datetime default NULL,
  `CDESC` longtext collate utf8_unicode_ci,
  `CNAME` varchar(100) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_tonline
-- ----------------------------
CREATE TABLE `jeecg_tonline` (
  `CID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `CDATETIME` datetime NOT NULL,
  `CIP` varchar(50) collate utf8_unicode_ci NOT NULL,
  `CNAME` varchar(100) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_trole
-- ----------------------------
CREATE TABLE `jeecg_trole` (
  `CID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `CDESC` varchar(200) collate utf8_unicode_ci default NULL,
  `CNAME` varchar(100) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_troletauth
-- ----------------------------
CREATE TABLE `jeecg_troletauth` (
  `CID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `CAUTHID` varchar(36) collate utf8_unicode_ci default NULL,
  `CROLEID` varchar(36) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`CID`),
  KEY `FKFFDB1003B95E9730` (`CAUTHID`),
  KEY `FKFFDB1003D610614C` (`CROLEID`),
  CONSTRAINT `FKFFDB1003B95E9730` FOREIGN KEY (`CAUTHID`) REFERENCES `jeecg_tauth` (`CID`),
  CONSTRAINT `FKFFDB1003D610614C` FOREIGN KEY (`CROLEID`) REFERENCES `jeecg_trole` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_tuser
-- ----------------------------
CREATE TABLE `jeecg_tuser` (
  `CID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `CCREATEDATETIME` datetime default NULL,
  `CMODIFYDATETIME` datetime default NULL,
  `CNAME` varchar(100) collate utf8_unicode_ci NOT NULL,
  `CPWD` varchar(100) collate utf8_unicode_ci NOT NULL,
  `mail` varchar(255) collate utf8_unicode_ci default NULL,
  `mobile` varchar(255) collate utf8_unicode_ci default NULL,
  `org` varchar(255) collate utf8_unicode_ci default NULL,
  `realname` varchar(255) collate utf8_unicode_ci default NULL,
  `status` varchar(255) collate utf8_unicode_ci default NULL,
  `usertype` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`CID`),
  UNIQUE KEY `CNAME` (`CNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for jeecg_tusertrole
-- ----------------------------
CREATE TABLE `jeecg_tusertrole` (
  `CID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `CROLEID` varchar(36) collate utf8_unicode_ci default NULL,
  `CUSERID` varchar(36) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`CID`),
  KEY `FK18149CDB65B6B6` (`CUSERID`),
  KEY `FK18149CD610614C` (`CROLEID`),
  CONSTRAINT `FK18149CD610614C` FOREIGN KEY (`CROLEID`) REFERENCES `jeecg_trole` (`CID`),
  CONSTRAINT `FK18149CDB65B6B6` FOREIGN KEY (`CUSERID`) REFERENCES `jeecg_tuser` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for person
-- ----------------------------
CREATE TABLE `person` (
  `OBID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `age` int(11) default NULL,
  `createdt` datetime default NULL,
  `name` varchar(255) collate utf8_unicode_ci default NULL,
  `salary` decimal(19,2) default NULL,
  PRIMARY KEY  (`OBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_cw_fee
-- ----------------------------
CREATE TABLE `t_cw_fee` (
  `feeid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '费用记录ID',
  `subjectid` varchar(5) collate utf8_unicode_ci NOT NULL COMMENT '费用科目',
  `amount` decimal(8,2) NOT NULL COMMENT '费用金额',
  `feetype` varchar(5) collate utf8_unicode_ci NOT NULL COMMENT '支付形式',
  `feedate` date NOT NULL COMMENT '费用日期',
  `feeeman` varchar(30) collate utf8_unicode_ci NOT NULL COMMENT '经手人',
  `meno` varchar(100) collate utf8_unicode_ci default NULL COMMENT '备注',
  `modifier` varchar(10) collate utf8_unicode_ci default NULL COMMENT '修改人ID',
  `modifiername` varchar(40) collate utf8_unicode_ci default NULL COMMENT '修改人姓名',
  `modifydate` datetime default NULL COMMENT '修改日期',
  `crtuser` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '创建ID',
  `crtusername` varchar(40) collate utf8_unicode_ci NOT NULL COMMENT '创建人姓名',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `ordernum` smallint(6) NOT NULL COMMENT '序号',
  PRIMARY KEY  (`feeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='费用信息表';

-- ----------------------------
-- Table structure for t_kc_stock
-- ----------------------------
CREATE TABLE `t_kc_stock` (
  `stockkey` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '库存表主键',
  `depotid` varchar(5) collate utf8_unicode_ci NOT NULL COMMENT '仓库ID',
  `articleid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '品种ID',
  `serial` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '批号',
  `num` decimal(5,0) NOT NULL COMMENT '数量',
  `costprice` decimal(8,2) NOT NULL COMMENT '成本价',
  `expiredate` date NOT NULL COMMENT '有效期',
  PRIMARY KEY  (`stockkey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='库存表';

-- ----------------------------
-- Table structure for t_kc_stock_check
-- ----------------------------
CREATE TABLE `t_kc_stock_check` (
  `stockcheckcode` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '盘点单号',
  `depotid` varchar(5) collate utf8_unicode_ci NOT NULL COMMENT '仓库ID',
  `articleid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '品种ID',
  `serial` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '批号',
  `computernum` decimal(5,0) NOT NULL COMMENT '电脑数量',
  `realnum` decimal(5,0) NOT NULL COMMENT '实际数量',
  `costprice` decimal(8,2) NOT NULL COMMENT '成本价',
  `expiredate` date NOT NULL COMMENT '有效期',
  `crtuser` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '盘点人',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `crtusername` varchar(30) collate utf8_unicode_ci NOT NULL COMMENT '盘点人姓名',
  `ordernum` smallint(6) NOT NULL COMMENT '序号',
  `stockkey` varchar(50) collate utf8_unicode_ci default NULL COMMENT '库存序号',
  PRIMARY KEY  (`stockcheckcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='盘点表';

-- ----------------------------
-- Table structure for t_rk_pay
-- ----------------------------
CREATE TABLE `t_rk_pay` (
  `paybillno` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '付款单号',
  `payman` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '付款人',
  `付款日期` datetime NOT NULL COMMENT '付款日期',
  `购进单列表` varchar(200) collate utf8_unicode_ci NOT NULL COMMENT '购进单列表',
  `付款金额` decimal(8,2) NOT NULL COMMENT '付款金额',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  PRIMARY KEY  (`paybillno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='购进付款表';

-- ----------------------------
-- Table structure for t_rk_stockin
-- ----------------------------
CREATE TABLE `t_rk_stockin` (
  `stockinbillno` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '入库单号',
  `stockindate` datetime NOT NULL COMMENT '入库日期',
  `depotid` varchar(5) collate utf8_unicode_ci NOT NULL COMMENT '仓库',
  `supplierid` varchar(5) collate utf8_unicode_ci NOT NULL COMMENT '供应商',
  `ispayed` tinyint(1) NOT NULL COMMENT '是否付款',
  `paybillno` varchar(20) collate utf8_unicode_ci default NULL COMMENT '付款单号',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `amount` decimal(8,2) NOT NULL COMMENT '总金额',
  `ordernum` smallint(6) NOT NULL COMMENT '序号',
  `crtuser` varchar(50) collate utf8_unicode_ci default NULL COMMENT '创建人ID',
  `crtusername` varchar(40) collate utf8_unicode_ci default NULL COMMENT '创建人姓名',
  `modifier` varchar(10) collate utf8_unicode_ci default NULL COMMENT '修改人ID',
  `modifiername` varchar(40) collate utf8_unicode_ci default NULL COMMENT '修改人姓名',
  `meno` varchar(100) collate utf8_unicode_ci default NULL COMMENT '备注',
  `stockbacknoitems` varchar(100) collate utf8_unicode_ci default NULL COMMENT '购进退货单列表',
  PRIMARY KEY  (`stockinbillno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='入库主表';

-- ----------------------------
-- Table structure for t_rk_stockin_detail
-- ----------------------------
CREATE TABLE `t_rk_stockin_detail` (
  `stockindetailno` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '入库明细单ID',
  `stockinbillno` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '入库单号',
  `articleid` varchar(10) collate utf8_unicode_ci default NULL COMMENT '品种ID号',
  `serial` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '批号',
  `expiredate` date NOT NULL COMMENT '有效期',
  `num` decimal(5,0) NOT NULL COMMENT '数量',
  `price` decimal(8,2) NOT NULL COMMENT '进价',
  `amount` decimal(8,2) NOT NULL COMMENT '金额',
  `backnum` decimal(5,0) default NULL COMMENT '已退货数',
  PRIMARY KEY  (`stockindetailno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='入库表明细';

-- ----------------------------
-- Table structure for t_th_stockback
-- ----------------------------
CREATE TABLE `t_th_stockback` (
  `stockbackno` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '购进退货单号',
  `depotid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '仓库ID',
  `supplierid` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '供应商',
  `crtuser` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '退货人',
  `crtusername` varchar(30) collate utf8_unicode_ci NOT NULL COMMENT '退货人姓名',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `ordernum` smallint(6) default NULL COMMENT '序号',
  `meno` varchar(100) collate utf8_unicode_ci default NULL COMMENT '备注',
  `stockinno` varchar(50) collate utf8_unicode_ci default NULL COMMENT '入库单号',
  `amount` decimal(8,2) default NULL COMMENT '退货金额',
  PRIMARY KEY  (`stockbackno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='购进退货单';

-- ----------------------------
-- Table structure for t_th_stockback_detail
-- ----------------------------
CREATE TABLE `t_th_stockback_detail` (
  `stockbackdetailno` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '购进退货明细主键',
  `stockbackno` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '购进退货单号',
  `articleid` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '品种ID号',
  `serial` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '批号',
  `expiredate` date NOT NULL COMMENT '有效期',
  `num` decimal(5,0) NOT NULL COMMENT '数量',
  `price` decimal(8,2) NOT NULL COMMENT '进价',
  `amount` decimal(8,2) NOT NULL COMMENT '金额',
  `stockindetailno` varchar(50) collate utf8_unicode_ci default NULL COMMENT '入库单明细主键',
  PRIMARY KEY  (`stockbackdetailno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='购进退货单明细';

-- ----------------------------
-- Table structure for t_xs_customer_price
-- ----------------------------
CREATE TABLE `t_xs_customer_price` (
  `articleid` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '品种名称',
  `customerid` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '客户名称',
  `saleprice` decimal(8,2) NOT NULL COMMENT '销售价',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `flowid` varchar(30) collate utf8_unicode_ci NOT NULL COMMENT '流水号',
  PRIMARY KEY  (`flowid`),
  KEY `AK_Identifier_1` (`flowid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_xs_receive
-- ----------------------------
CREATE TABLE `t_xs_receive` (
  `receiveno` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '收款单号',
  `receiveman` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '收款人',
  `receivedate` datetime NOT NULL COMMENT '收款日期',
  `amount` decimal(8,2) NOT NULL COMMENT '收款金额',
  `salebillnolist` varchar(200) collate utf8_unicode_ci NOT NULL COMMENT '销售单列表',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  PRIMARY KEY  (`receiveno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='销售回款表';

-- ----------------------------
-- Table structure for t_xs_sale
-- ----------------------------
CREATE TABLE `t_xs_sale` (
  `salebillno` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '销售单号',
  `customerid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '销售客户',
  `saledate` datetime NOT NULL COMMENT '销售日期',
  `accountid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '业务员',
  `salerate` decimal(5,2) NOT NULL COMMENT '提成比例',
  `meno` varchar(100) collate utf8_unicode_ci default NULL COMMENT '备注',
  `isreceive` tinyint(1) NOT NULL COMMENT '是否收款',
  `crtusername` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '操作人姓名',
  `crtuser` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '操作人',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `salebackbillitems` varchar(100) collate utf8_unicode_ci default NULL COMMENT '销售退货单列表',
  `amount` decimal(8,2) default NULL COMMENT '总金额',
  `ordernum` smallint(6) NOT NULL COMMENT '序号',
  PRIMARY KEY  (`salebillno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='销售主表';

-- ----------------------------
-- Table structure for t_xs_sale_back
-- ----------------------------
CREATE TABLE `t_xs_sale_back` (
  `salebackno` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '销售退货单号',
  `salebillno` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '原销售单号',
  `customerid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '退货客户',
  `backdate` datetime NOT NULL COMMENT '退货日期',
  `accountid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '业务员',
  `salerate` decimal(5,2) NOT NULL COMMENT '原提成比例',
  `meno` varchar(100) collate utf8_unicode_ci default NULL COMMENT '备注',
  `isreceive` tinyint(1) NOT NULL COMMENT '是否收款',
  `crtusername` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '操作人姓名',
  `crtuser` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '操作人',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `amount` decimal(8,2) default NULL COMMENT '总金额',
  `ordernum` smallint(6) default NULL COMMENT '序号',
  PRIMARY KEY  (`salebackno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='销售退货单';

-- ----------------------------
-- Table structure for t_xs_sale_back_detail
-- ----------------------------
CREATE TABLE `t_xs_sale_back_detail` (
  `salebackbilldetailno` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '退货明细主键',
  `salebackno` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '退货单号',
  `articleid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '品种',
  `serial` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '批号',
  `num` decimal(5,0) NOT NULL COMMENT '数量',
  `saleprice` decimal(8,2) NOT NULL COMMENT '退货价',
  `price` decimal(8,2) NOT NULL COMMENT '成本价',
  `amount` decimal(8,2) NOT NULL COMMENT '金额',
  `depotid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '仓库',
  `secretno` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '防伪号',
  `ordernum` smallint(6) default NULL COMMENT '序号',
  `stockkey` varchar(50) collate utf8_unicode_ci default NULL COMMENT '库存序号',
  `expiredate` date NOT NULL COMMENT '有效期',
  `salebilldetailno` varchar(50) collate utf8_unicode_ci default NULL COMMENT '销售明细主键',
  PRIMARY KEY  (`salebackbilldetailno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='销售退货明细表';

-- ----------------------------
-- Table structure for t_xs_sale_detail
-- ----------------------------
CREATE TABLE `t_xs_sale_detail` (
  `salebilldetailno` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '销售明细主键',
  `salebillno` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '销售单号',
  `articleid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '品种',
  `serial` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '批号',
  `num` decimal(5,0) NOT NULL COMMENT '数量',
  `saleprice` decimal(8,2) NOT NULL COMMENT '销售价',
  `price` decimal(8,2) NOT NULL COMMENT '成本价',
  `amount` decimal(8,2) NOT NULL COMMENT '金额',
  `depotid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '仓库',
  `secretno` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '防伪号',
  `ordernum` smallint(6) default NULL COMMENT '序号',
  `stockkey` varchar(50) collate utf8_unicode_ci default NULL COMMENT '库存序号',
  `expiredate` date NOT NULL COMMENT '有效期',
  `backnum` decimal(5,0) default NULL COMMENT '已退货数',
  PRIMARY KEY  (`salebilldetailno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='销售明细表';

-- ----------------------------
-- Table structure for t_xx_accounter
-- ----------------------------
CREATE TABLE `t_xx_accounter` (
  `accountid` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '业务员ID',
  `accountname` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '业务员姓名',
  `sex` varchar(2) collate utf8_unicode_ci NOT NULL COMMENT '性别',
  `age` decimal(2,0) default NULL COMMENT '年龄',
  `birthday` date default NULL COMMENT '出生日期',
  `telephone` varchar(15) collate utf8_unicode_ci default NULL COMMENT '联系电话',
  `area` varchar(5) collate utf8_unicode_ci NOT NULL COMMENT '所属片区',
  `enterdate` date NOT NULL COMMENT '入职日期',
  `address` varchar(80) collate utf8_unicode_ci NOT NULL COMMENT '家庭地址',
  `rate` decimal(5,2) NOT NULL COMMENT '提成比例',
  `isactive` tinyint(1) NOT NULL COMMENT '是否在职',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `ordernum` smallint(6) default NULL COMMENT '序号',
  PRIMARY KEY  (`accountid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='业务员信息表';

-- ----------------------------
-- Table structure for t_xx_area
-- ----------------------------
CREATE TABLE `t_xx_area` (
  `areacode` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '片区ID',
  `areaname` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '片区名称',
  `manage` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '片区经理',
  `rate` decimal(5,2) NOT NULL COMMENT '提成比例',
  `meno` varchar(100) collate utf8_unicode_ci default NULL COMMENT '备注',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `ordernum` smallint(6) NOT NULL COMMENT '序号',
  PRIMARY KEY  (`areacode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='片区信息';

-- ----------------------------
-- Table structure for t_xx_article
-- ----------------------------
CREATE TABLE `t_xx_article` (
  `articleid` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '品种ID号',
  `articlename` varchar(100) collate utf8_unicode_ci NOT NULL COMMENT '品种名称',
  `articlealias` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '品种别名',
  `articlespec` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '品种规格',
  `factory` varchar(80) collate utf8_unicode_ci NOT NULL COMMENT '生产厂家',
  `barcode` varchar(40) collate utf8_unicode_ci default NULL COMMENT '条码',
  `fileno` varchar(80) collate utf8_unicode_ci default NULL COMMENT '批准文号',
  `pfprice` decimal(8,2) default NULL COMMENT '批发价',
  `lastinprice` decimal(8,2) default NULL COMMENT '最新进价',
  `unit` varchar(5) collate utf8_unicode_ci NOT NULL COMMENT '单位',
  `catelog` varchar(5) collate utf8_unicode_ci default NULL,
  `israte` tinyint(1) default NULL COMMENT '是否提成',
  `rate` decimal(5,3) default NULL COMMENT '提成比例',
  `meno` varchar(100) collate utf8_unicode_ci default NULL COMMENT '备注',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `ordernum` smallint(6) default NULL COMMENT '序号',
  `retailprice` decimal(8,2) NOT NULL,
  PRIMARY KEY  (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='品种信息表';

-- ----------------------------
-- Table structure for t_xx_catelog
-- ----------------------------
CREATE TABLE `t_xx_catelog` (
  `catelogcode` varchar(36) collate utf8_unicode_ci NOT NULL,
  `cname` varchar(255) collate utf8_unicode_ci default NULL,
  `catelogmeno` varchar(255) collate utf8_unicode_ci default NULL,
  `Ciconcls` varchar(50) collate utf8_unicode_ci default NULL,
  `CPID` varchar(36) collate utf8_unicode_ci default NULL,
  `Cseq` int(11) default NULL,
  PRIMARY KEY  (`catelogcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_xx_company
-- ----------------------------
CREATE TABLE `t_xx_company` (
  `companyno` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '公司序号',
  `companyname` varchar(50) collate utf8_unicode_ci default NULL COMMENT '公司名称',
  `telephone` varchar(30) collate utf8_unicode_ci default NULL COMMENT '电话',
  `fax` varchar(20) collate utf8_unicode_ci default NULL COMMENT '传真',
  `email` varchar(40) collate utf8_unicode_ci default NULL COMMENT '邮箱',
  `address` varchar(150) collate utf8_unicode_ci default NULL COMMENT '地址',
  `contactman` varchar(50) collate utf8_unicode_ci default NULL COMMENT '联系人',
  `billmeno` varchar(100) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`companyno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='公司信息';

-- ----------------------------
-- Table structure for t_xx_customer
-- ----------------------------
CREATE TABLE `t_xx_customer` (
  `customerid` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '客户ID',
  `customername` varchar(100) collate utf8_unicode_ci NOT NULL COMMENT '客户名称',
  `accountid` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '业务员',
  `connectman` varchar(15) collate utf8_unicode_ci NOT NULL COMMENT '联系人',
  `phone` varchar(15) collate utf8_unicode_ci NOT NULL COMMENT '联系电话',
  `email` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '邮箱',
  `address` varchar(100) collate utf8_unicode_ci NOT NULL COMMENT '地址',
  `meno` varchar(100) collate utf8_unicode_ci default NULL COMMENT '备注',
  `ordernum` smallint(6) NOT NULL COMMENT '序号',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  PRIMARY KEY  (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='客户信息表';

-- ----------------------------
-- Table structure for t_xx_depot
-- ----------------------------
CREATE TABLE `t_xx_depot` (
  `depotid` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '仓库ID',
  `depotname` varchar(100) collate utf8_unicode_ci NOT NULL COMMENT '仓库名称',
  `depotman` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '仓管人',
  `depotaddress` varchar(80) collate utf8_unicode_ci NOT NULL COMMENT '仓库地址',
  `phone` varchar(15) collate utf8_unicode_ci NOT NULL COMMENT '联系电话',
  `isactive` tinyint(1) NOT NULL COMMENT '是否启用',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `ordernum` smallint(6) NOT NULL COMMENT '序号',
  PRIMARY KEY  (`depotid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='仓库信息表';

-- ----------------------------
-- Table structure for t_xx_subject
-- ----------------------------
CREATE TABLE `t_xx_subject` (
  `subjectid` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '科目ID',
  `subjectname` varchar(100) collate utf8_unicode_ci NOT NULL COMMENT '科目名称',
  `subjecttype` varchar(5) collate utf8_unicode_ci NOT NULL COMMENT '科目类别',
  `meno` varchar(100) collate utf8_unicode_ci default NULL COMMENT '备注',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `crtuser` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '创建ID',
  `crtusername` varchar(40) collate utf8_unicode_ci default NULL COMMENT '创建人姓名',
  `ordernum` smallint(6) NOT NULL,
  PRIMARY KEY  (`subjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='科目信息';

-- ----------------------------
-- Table structure for t_xx_supplier
-- ----------------------------
CREATE TABLE `t_xx_supplier` (
  `supplierid` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '供应商ID',
  `suppliername` varchar(80) collate utf8_unicode_ci NOT NULL COMMENT '供货商名称',
  `connectman` varchar(15) collate utf8_unicode_ci NOT NULL COMMENT '联系人',
  `phone` varchar(15) collate utf8_unicode_ci NOT NULL COMMENT '联系电话',
  `address` varchar(100) collate utf8_unicode_ci NOT NULL COMMENT '地址',
  `fax` varchar(15) collate utf8_unicode_ci NOT NULL COMMENT '传真',
  `email` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '邮箱',
  `meno` varchar(100) collate utf8_unicode_ci default NULL COMMENT '备注',
  `createdate` datetime NOT NULL COMMENT '创建日期',
  `modifydate` datetime NOT NULL COMMENT '修改日期',
  `ordernum` smallint(6) default NULL COMMENT '序号',
  PRIMARY KEY  (`supplierid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='供货商信息表';

-- ----------------------------
-- Table structure for t60_gbuy_order
-- ----------------------------
CREATE TABLE `t60_gbuy_order` (
  `OBID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `auditor_name` varchar(255) collate utf8_unicode_ci default NULL,
  `auditor_obid` varchar(255) collate utf8_unicode_ci default NULL,
  `auditor_status` varchar(255) collate utf8_unicode_ci default NULL,
  `create_dt` datetime default NULL,
  `crtuser` varchar(255) collate utf8_unicode_ci default NULL,
  `crtuser_name` varchar(255) collate utf8_unicode_ci default NULL,
  `del_dt` datetime default NULL,
  `delflag` int(11) default NULL,
  `go_all_price` decimal(19,2) default NULL,
  `go_contact_name` varchar(255) collate utf8_unicode_ci default NULL,
  `go_content` varchar(255) collate utf8_unicode_ci default NULL,
  `go_cus_name` varchar(255) collate utf8_unicode_ci default NULL,
  `go_facsimile` varchar(255) collate utf8_unicode_ci default NULL,
  `go_mail` varchar(255) collate utf8_unicode_ci default NULL,
  `go_order_code` varchar(255) collate utf8_unicode_ci default NULL,
  `go_order_count` int(11) default NULL,
  `go_phone` varchar(255) collate utf8_unicode_ci default NULL,
  `go_return_price` decimal(19,2) default NULL,
  `go_telphone` varchar(255) collate utf8_unicode_ci default NULL,
  `go_zone_no` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier_name` varchar(255) collate utf8_unicode_ci default NULL,
  `modify_dt` datetime default NULL,
  PRIMARY KEY  (`OBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t60_gbuy_order_custom
-- ----------------------------
CREATE TABLE `t60_gbuy_order_custom` (
  `OBID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `create_dt` datetime default NULL,
  `crtuser` varchar(255) collate utf8_unicode_ci default NULL,
  `crtuser_name` varchar(255) collate utf8_unicode_ci default NULL,
  `del_dt` datetime default NULL,
  `delflag` int(11) default NULL,
  `go_order_code` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_birthday` datetime default NULL,
  `goc_buss_content` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_content` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_cus_name` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_cus_name_en` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_cus_type` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_idcard` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_passport_code` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_passport_end_date` datetime default NULL,
  `goc_room_num` varchar(255) collate utf8_unicode_ci default NULL,
  `goc_sex` varchar(255) collate utf8_unicode_ci default NULL,
  `gorder_obid` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier_name` varchar(255) collate utf8_unicode_ci default NULL,
  `modify_dt` datetime default NULL,
  PRIMARY KEY  (`OBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t60_gbuy_order_product
-- ----------------------------
CREATE TABLE `t60_gbuy_order_product` (
  `OBID` varchar(36) collate utf8_unicode_ci NOT NULL,
  `create_dt` datetime default NULL,
  `crtuser` varchar(255) collate utf8_unicode_ci default NULL,
  `crtuser_name` varchar(255) collate utf8_unicode_ci default NULL,
  `del_dt` datetime default NULL,
  `delflag` int(11) default NULL,
  `go_order_code` varchar(255) collate utf8_unicode_ci default NULL,
  `gop_content` varchar(255) collate utf8_unicode_ci default NULL,
  `gop_count` int(11) default NULL,
  `gop_one_price` decimal(19,2) default NULL,
  `gop_product_name` varchar(255) collate utf8_unicode_ci default NULL,
  `gop_product_obid` varchar(255) collate utf8_unicode_ci default NULL,
  `gop_product_type` varchar(255) collate utf8_unicode_ci default NULL,
  `gop_sum_price` decimal(19,2) default NULL,
  `gop_unit` varchar(255) collate utf8_unicode_ci default NULL,
  `gorder_obid` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier` varchar(255) collate utf8_unicode_ci default NULL,
  `modifier_name` varchar(255) collate utf8_unicode_ci default NULL,
  `modify_dt` datetime default NULL,
  PRIMARY KEY  (`OBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `jeecg_dict_param` VALUES ('01dbeada-22b6-4e02-8f6a-af88b72ebabe', null, '006', '科目类型', '收入', '1', '1', '');
INSERT INTO `jeecg_dict_param` VALUES ('1', '0', '001', '性别', '男', '01', null, null);
INSERT INTO `jeecg_dict_param` VALUES ('2', '0', '001', '性别', '女', '02', null, null);
INSERT INTO `jeecg_dict_param` VALUES ('2a71f4b3-6c36-4cae-a87c-22659bc529e9', null, '007', '支付方式', '承兑', '03', '3', '');
INSERT INTO `jeecg_dict_param` VALUES ('3', '0', '002', '系统用户', '系统用户', '01', null, null);
INSERT INTO `jeecg_dict_param` VALUES ('4', '0', '002', '公司员工', '公司员工', '02', null, null);
INSERT INTO `jeecg_dict_param` VALUES ('488e6171-925a-4bdb-942d-21af8fd12422', null, '003', '单位', '瓶', '03', null, '');
INSERT INTO `jeecg_dict_param` VALUES ('4c72ba09-4338-4883-a1bc-a7e6e6be0b84', null, '003', '单位', '袋', '02', null, '');
INSERT INTO `jeecg_dict_param` VALUES ('634aec9e-50c1-4544-8cd2-ec0fd6453ccc', null, '007', '支付方式', '现金', '01', '1', '');
INSERT INTO `jeecg_dict_param` VALUES ('7a4a99cb-5a48-4057-8846-07b1c99e1b07', null, '004', '是否标志', '是', '1', null, '');
INSERT INTO `jeecg_dict_param` VALUES ('961f1578-0f6e-41bd-9562-92aa7db6c0cb', null, '006', '科目类型', '支出', '2', null, '');
INSERT INTO `jeecg_dict_param` VALUES ('a5e6133c-c32d-4799-8428-e87f959d4dd6', null, '004', '是否标志', '否', '2', null, '');
INSERT INTO `jeecg_dict_param` VALUES ('a9e94474-5580-4b97-abcb-a10675d99683', null, '005', '产品类别', '西药', '01', '1', '');
INSERT INTO `jeecg_dict_param` VALUES ('af89f51e-706c-41e0-82ea-f0956a3b9b66', null, '005', '产品类别', '其他', '04', '4', '');
INSERT INTO `jeecg_dict_param` VALUES ('b5c8d45b-0816-4b8d-aa8f-c61dc7cf00ab', null, '003', '单位', '包', '04', null, '');
INSERT INTO `jeecg_dict_param` VALUES ('c3f9710e-b274-4a02-acaa-786999dcd9f2', null, '005', '产品类别', '保健品', '03', '3', '');
INSERT INTO `jeecg_dict_param` VALUES ('cd59dfc2-e25d-424b-824c-6467a4ea1445', null, '003', '单位', '盒', '01', null, '');
INSERT INTO `jeecg_dict_param` VALUES ('d4f063f0-b567-4525-a286-acfcd7ad308f', null, '005', '产品类别', '中成药', '02', '2', '');
INSERT INTO `jeecg_dict_param` VALUES ('ddb3d77e-68ea-407b-8f94-4b13e09918c4', null, '007', '支付方式', '转账', '02', '2', '');
INSERT INTO `jeecg_group` VALUES ('01', null, 'SCOTT 工作室', '2013-05-11 23:42:18', null, null, null, '0', null, null, null, null);
INSERT INTO `jeecg_group` VALUES ('0101', null, '财务室', '2013-05-11 23:42:18', null, null, null, '0', null, null, null, '01');
INSERT INTO `jeecg_group` VALUES ('0102', null, '软件开发室', '2013-05-11 23:42:18', null, null, null, '0', null, null, null, '01');
INSERT INTO `jeecg_group` VALUES ('010201', null, '开发一部', '2013-05-11 23:42:18', null, null, null, '0', null, null, null, '0102');
INSERT INTO `jeecg_group` VALUES ('010202', null, '开发二部', '2013-05-11 23:42:18', null, null, null, '0', null, null, null, '0102');
INSERT INTO `jeecg_tauth` VALUES ('0', null, null, '首页', '1', '01', '', null);
INSERT INTO `jeecg_tauth` VALUES ('0217959f-ef41-5d2d-3b83-eac4b7d10f84', null, 'icon-hoho', '库存汇总', '12', '01', 'stockAction!goStockTotal.action', '7924309f-f1c8-4618-39ff-463df09a3a18');
INSERT INTO `jeecg_tauth` VALUES ('0876d610-1adb-6bcd-57a4-08d2cc09d0cd', null, 'icon-blue', '片区信息', '11', '01', 'areaAction!goArea.action', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8');
INSERT INTO `jeecg_tauth` VALUES ('091c9088-c4fa-ab2d-7f80-d1310b03ed01', null, '', '销售管理', '3', '01', '', '0');
INSERT INTO `jeecg_tauth` VALUES ('263cc6f1-0b28-bde9-3063-f985f7d4818d', null, 'icon-hihi', '客户信息', '14', '01', 'customerAction!goCustomer.action', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8');
INSERT INTO `jeecg_tauth` VALUES ('282a65c6-8b24-48d1-3ee5-3b6b26389c14', null, 'icon-newd', '销售明细统计', '11', '01', 'saleAction!goViewSaleDetail.action', '94cc37b7-aef5-4333-049f-8adef2b0ccf3');
INSERT INTO `jeecg_tauth` VALUES ('3266c267-1fe5-cfb6-8352-dab1a6b95173', '新增入库单', null, '新增入库单', '1', '02', 'stockinAction!toStockinMainAdd.action', 'qxgl_sub');
INSERT INTO `jeecg_tauth` VALUES ('365937d4-c337-3614-0696-a20e7ee295c0', null, 'icon-annex', '销售退货单', '11', '01', 'saleBackAction!goSaleBack.action', '091c9088-c4fa-ab2d-7f80-d1310b03ed01');
INSERT INTO `jeecg_tauth` VALUES ('3cff5606-deba-d75b-9ee1-c918ca529441', null, 'icon-conf', '费用出入统计', '12', '01', 'feeAction!goViewFeeTotal.action', 'c32570e8-9bc0-0887-4f69-7b5128e2a331');
INSERT INTO `jeecg_tauth` VALUES ('3ee172f2-f414-0d61-c335-16628f429fd1', null, 'icon-bars', '业务员销售统计', '13', '01', 'saleAction!goViewSaleByAccounter.action', '94cc37b7-aef5-4333-049f-8adef2b0ccf3');
INSERT INTO `jeecg_tauth` VALUES ('41e52963-8ec1-6525-ba4e-b7a1054090fe', null, 'icon-cale', '费用出入管理', '11', '01', 'feeAction!goFee.action', 'c32570e8-9bc0-0887-4f69-7b5128e2a331');
INSERT INTO `jeecg_tauth` VALUES ('5a7119bf-7900-ca78-27bd-3a431426f07e', null, 'icon-bing', '品种信息', '10', '01', 'articleAction!goArticle.action', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8');
INSERT INTO `jeecg_tauth` VALUES ('61795e92-8ed3-f8e8-dd15-c85a1bc8b4c2', null, 'icon-talk', '商品进出跟踪', '11', '01', 'articleAction!goViewArticleTrack.action', '94cc37b7-aef5-4333-049f-8adef2b0ccf3');
INSERT INTO `jeecg_tauth` VALUES ('65dab05b-166b-552b-718b-a1b48f635022', '新增品种', null, '新增品种', '3', '02', 'articleAction!add.action', 'qxgl_sub');
INSERT INTO `jeecg_tauth` VALUES ('68545c2c-7408-19b5-9296-b3d4fa345222', null, 'icon-rmbs', '客户价格查询', '12', '01', 'customerPriceAction!goCustomerPrice.action', '091c9088-c4fa-ab2d-7f80-d1310b03ed01');
INSERT INTO `jeecg_tauth` VALUES ('78c1030f-9d8d-a22e-4c05-624f1f8f89aa', null, 'icon-love', '类别信息', '15', '01', 'catelogAction!goCatelog.action', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8');
INSERT INTO `jeecg_tauth` VALUES ('7924309f-f1c8-4618-39ff-463df09a3a18', null, '', '库存管理', '4', '01', '', '0');
INSERT INTO `jeecg_tauth` VALUES ('7bb7268b-9a24-40e0-50b9-b177d87227c1', null, '', '采购管理', '2', '01', '', '0');
INSERT INTO `jeecg_tauth` VALUES ('7df74098-57b3-aa00-9826-a1f894783f9b', null, 'icon-offe', '销售出库单', '10', '01', 'saleAction!goSale.action', '091c9088-c4fa-ab2d-7f80-d1310b03ed01');
INSERT INTO `jeecg_tauth` VALUES ('8cc046f7-f2d1-197f-24fd-af41cb777ded', null, 'icon-bing', '采购退货单', '11', '01', 'stockBackAction!goStockBack.action', '7bb7268b-9a24-40e0-50b9-b177d87227c1');
INSERT INTO `jeecg_tauth` VALUES ('94cc37b7-aef5-4333-049f-8adef2b0ccf3', null, '', '报表管理', '6', '01', '', '0');
INSERT INTO `jeecg_tauth` VALUES ('a0b68a51-d4b7-4ba0-31b0-de3341bdf240', null, 'icon-blue', '采购汇总统计', '8', '01', 'stockinAction!goViewStockinTotal.action', '94cc37b7-aef5-4333-049f-8adef2b0ccf3');
INSERT INTO `jeecg_tauth` VALUES ('a2dab35a-d183-4b46-6791-d32716985a58', null, 'icon-blue', '库存明细', '10', '01', 'stockAction!goStockDetail.action', '7924309f-f1c8-4618-39ff-463df09a3a18');
INSERT INTO `jeecg_tauth` VALUES ('a4703155-0ac4-8d9b-1fb4-53c6d5840009', null, 'icon-cale', '公司信息', '9', '01', 'companyAction!goCompany.action', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8');
INSERT INTO `jeecg_tauth` VALUES ('ad22fd96-71dc-3849-0cf4-184c87d34474', null, 'icon-gree', '业务员信息', '12', '01', 'accounterAction!goAccounter.action', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8');
INSERT INTO `jeecg_tauth` VALUES ('adae52a9-4f93-d619-5dd7-a60be9184529', null, 'icon-bing', '库存盘点', '11', '01', 'stockCheckAction!goStockCheck.action', '7924309f-f1c8-4618-39ff-463df09a3a18');
INSERT INTO `jeecg_tauth` VALUES ('adf80bac-fb13-9c69-bc62-feb64804e72f', null, 'icon-ding', '仓库信息', '16', '01', 'depotAction!goDepot.action', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8');
INSERT INTO `jeecg_tauth` VALUES ('b03b26b6-ed01-dbb4-cc0b-c8bc37e6c127', null, 'icon-limi', '供应商信息', '13', '01', 'supplierAction!goSupplier.action', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8');
INSERT INTO `jeecg_tauth` VALUES ('b888eb48-3437-6eb7-8051-f3072c3d0204', '查看入库单', null, '查看入库单', '2', '02', 'stockinAction!toStockinMainDetail.action', 'qxgl_sub');
INSERT INTO `jeecg_tauth` VALUES ('c219487c-66ec-07a0-cd70-5308876ce9a3', null, 'icon-cale', '销售汇总统计', '10', '01', 'saleAction!goViewSaleTotal.action', '94cc37b7-aef5-4333-049f-8adef2b0ccf3');
INSERT INTO `jeecg_tauth` VALUES ('c2ea2112-a7e3-c607-89cb-199312c4edc5', null, 'icon-hihi', '库存盘点统计', '12', '01', 'stockCheckAction!goViewStockCheck.action', '94cc37b7-aef5-4333-049f-8adef2b0ccf3');
INSERT INTO `jeecg_tauth` VALUES ('c32570e8-9bc0-0887-4f69-7b5128e2a331', null, '', '财务管理', '5', '01', '', '0');
INSERT INTO `jeecg_tauth` VALUES ('c5fe2b18-b760-9de7-de5a-7123fc8f45d4', null, 'icon-cale', '采购入库单', '10', '01', 'stockinAction!goStockin.action', '7bb7268b-9a24-40e0-50b9-b177d87227c1');
INSERT INTO `jeecg_tauth` VALUES ('cdgl', null, 'icon-face', '菜单管理', '3', '01', 'menuAction!goMenu.action', 'xtgl');
INSERT INTO `jeecg_tauth` VALUES ('ce4ac90e-2ca1-ac65-2e39-bcef61ad54a4', null, 'icon-calc', '采购明细统计', '9', '01', 'stockinAction!goViewStockinDetail.action', '94cc37b7-aef5-4333-049f-8adef2b0ccf3');
INSERT INTO `jeecg_tauth` VALUES ('dict', null, 'icon-annex', '字典管理', '5', '01', 'dictParamAction!goDictParam.action', 'xtgl');
INSERT INTO `jeecg_tauth` VALUES ('druidIndex', null, 'icon-calc', 'druid监控', '3', '01', 'datasourceAction!goDruid.action', 'sjkgl');
INSERT INTO `jeecg_tauth` VALUES ('f4402a53-e929-f4fc-771d-09dd474a0aea', null, 'icon-hibo', '科目信息', '17', '01', 'subjectAction!goSubject.action', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8');
INSERT INTO `jeecg_tauth` VALUES ('f6507ed3-0fba-4c30-0955-0cdbf1045df8', null, '', '基础信息', '1', '01', '', '0');
INSERT INTO `jeecg_tauth` VALUES ('jsgl', null, 'icon-talk', '角色管理', '2', '01', 'roleAction!goRole.action', 'xtgl');
INSERT INTO `jeecg_tauth` VALUES ('note', null, 'icon-orde', '公告管理', '6', '01', 'noteAction!goNote.action', 'xtgl');
INSERT INTO `jeecg_tauth` VALUES ('orgmanager', null, 'icon-hote', '组织机构管理', '7', '01', 'jeecgGroupAction!goJeecgGroup.action', 'xtgl');
INSERT INTO `jeecg_tauth` VALUES ('qxgl', null, 'icon-penc', '按鈕权限', '4', '01', 'authAction!goAuth.action', 'xtgl');
INSERT INTO `jeecg_tauth` VALUES ('qxgl_sub', '', 'icon-calc', '按鈕权限', '4', '02', '', null);
INSERT INTO `jeecg_tauth` VALUES ('sjkgl', null, '', '数据库管理', '9', '01', '', '0');
INSERT INTO `jeecg_tauth` VALUES ('xtgl', null, '', '系统管理', '8', '01', '', '0');
INSERT INTO `jeecg_tauth` VALUES ('yhgl', null, 'icon-limi', '用户管理', '1', '01', 'userAction!goUser.action', 'xtgl');
INSERT INTO `jeecg_tonline` VALUES ('9a59a011-0cda-4d5b-a048-23dbec581904', '2013-06-16 00:20:21', '127.0.0.1', 'zhangsan');
INSERT INTO `jeecg_tonline` VALUES ('e4d59c97-8667-4c8f-8d27-71cfa9caef39', '2015-05-21 16:38:49', '127.0.0.1', 'admin');
INSERT INTO `jeecg_trole` VALUES ('0', '拥有系统所有权限', '超级管理员');
INSERT INTO `jeecg_trole` VALUES ('1', '有医药权限', '总经理');
INSERT INTO `jeecg_trole` VALUES ('2dadab80-68aa-d8c6-8525-3c3384821b40', '开票员', '开票员');
INSERT INTO `jeecg_trole` VALUES ('efbf348a-d914-324a-e420-82a11b114fdf', '统计员', '统计员');
INSERT INTO `jeecg_troletauth` VALUES ('048c5474-ac7b-4a16-8e29-77ff86f12fda', 'ad22fd96-71dc-3849-0cf4-184c87d34474', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('050671de-590c-47a7-85b4-5b62fb018c07', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('094a3b48-6637-419c-a20b-1e352c114526', '41e52963-8ec1-6525-ba4e-b7a1054090fe', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('0a2904d0-baaa-4c35-a50a-3055e514e6ed', '3ee172f2-f414-0d61-c335-16628f429fd1', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('0e5cac27-defb-464e-b61c-fd06ee6b8d14', 'qxgl_sub', '0');
INSERT INTO `jeecg_troletauth` VALUES ('10079ab0-be47-4dfe-b411-968a04b5d607', '365937d4-c337-3614-0696-a20e7ee295c0', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('1060cf74-2dcb-48fc-977c-75ddc13107cd', '94cc37b7-aef5-4333-049f-8adef2b0ccf3', '1');
INSERT INTO `jeecg_troletauth` VALUES ('14260c52-55ca-434f-bde3-c7d87e7b98db', '0876d610-1adb-6bcd-57a4-08d2cc09d0cd', '1');
INSERT INTO `jeecg_troletauth` VALUES ('145bd420-acd1-4998-8e2f-14d8204375d8', 'ad22fd96-71dc-3849-0cf4-184c87d34474', '1');
INSERT INTO `jeecg_troletauth` VALUES ('15f9ecff-1b6a-4d7e-b70a-3cd661e6a2bf', 'adae52a9-4f93-d619-5dd7-a60be9184529', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('1734b5a2-2bf5-432e-a7aa-9a7922b26c80', 'b03b26b6-ed01-dbb4-cc0b-c8bc37e6c127', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('18062bae-8c57-46bb-997c-3e16c43ff59f', '5a7119bf-7900-ca78-27bd-3a431426f07e', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('18a06cb2-f8bd-4b15-97b4-5f5f7f2738e6', '68545c2c-7408-19b5-9296-b3d4fa345222', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('1c7705f3-277f-4600-9165-8c6d8cccf9ad', '5a7119bf-7900-ca78-27bd-3a431426f07e', '1');
INSERT INTO `jeecg_troletauth` VALUES ('23bb5022-8090-4c68-9d29-6925c16ce8a1', 'c5fe2b18-b760-9de7-de5a-7123fc8f45d4', '1');
INSERT INTO `jeecg_troletauth` VALUES ('255c8734-f044-4dfa-9c5a-809104b1e153', '7df74098-57b3-aa00-9826-a1f894783f9b', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('2613fb9f-2d67-4110-8d0d-c80148594155', '3cff5606-deba-d75b-9ee1-c918ca529441', '1');
INSERT INTO `jeecg_troletauth` VALUES ('285a3dfc-30b9-41e1-b0e9-6f647f5f51e2', 'c32570e8-9bc0-0887-4f69-7b5128e2a331', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('2ac1fe31-d02c-46e9-80ab-851140c62d33', 'b03b26b6-ed01-dbb4-cc0b-c8bc37e6c127', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('3758e4f7-5c02-4b78-b2c7-39c79f33381c', '61795e92-8ed3-f8e8-dd15-c85a1bc8b4c2', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('38758f81-671c-4687-b9fc-740c9339b0c0', 'ad22fd96-71dc-3849-0cf4-184c87d34474', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('38c1fe6a-8de2-45ca-8430-2ae306c3d4eb', 'c32570e8-9bc0-0887-4f69-7b5128e2a331', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('3e89c821-e9ce-4c5a-86bc-d097084055d6', '282a65c6-8b24-48d1-3ee5-3b6b26389c14', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('43213785-564b-45e1-a825-50df8d667717', 'b03b26b6-ed01-dbb4-cc0b-c8bc37e6c127', '1');
INSERT INTO `jeecg_troletauth` VALUES ('4da1f438-6917-4451-991b-74636bd9d1b7', 'adf80bac-fb13-9c69-bc62-feb64804e72f', '1');
INSERT INTO `jeecg_troletauth` VALUES ('4df29663-8a9a-4fc9-8e3d-cae3429adfe0', 'note', '0');
INSERT INTO `jeecg_troletauth` VALUES ('4e946da8-fc63-49c1-8058-1a78aaa63c5c', '8cc046f7-f2d1-197f-24fd-af41cb777ded', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('508ccef4-8ea2-42d6-b372-2089a9438771', '7df74098-57b3-aa00-9826-a1f894783f9b', '1');
INSERT INTO `jeecg_troletauth` VALUES ('57c358f9-fc5c-427d-ad12-1e0450153572', '091c9088-c4fa-ab2d-7f80-d1310b03ed01', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('58e560ef-e6fa-44ab-abd9-4cd3f1328240', '0', '0');
INSERT INTO `jeecg_troletauth` VALUES ('5bef1fa8-a52f-415e-aea4-be8d8168220d', '263cc6f1-0b28-bde9-3063-f985f7d4818d', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('615ce17c-e912-422f-944f-41e2cf8c577b', '7bb7268b-9a24-40e0-50b9-b177d87227c1', '1');
INSERT INTO `jeecg_troletauth` VALUES ('68967837-bc1e-4ef5-a212-34ba11cd98fd', '94cc37b7-aef5-4333-049f-8adef2b0ccf3', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('68acbc4e-0952-4eb5-a96e-c37ef2e49a8b', '8cc046f7-f2d1-197f-24fd-af41cb777ded', '1');
INSERT INTO `jeecg_troletauth` VALUES ('6929954f-2005-4ab7-8088-7fe9150fe066', '7bb7268b-9a24-40e0-50b9-b177d87227c1', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('69ad21af-51ec-4a43-a9c5-3229de476d4a', '7924309f-f1c8-4618-39ff-463df09a3a18', '1');
INSERT INTO `jeecg_troletauth` VALUES ('6b54fcec-1672-4488-9b0b-415eb659f047', 'jsgl', '0');
INSERT INTO `jeecg_troletauth` VALUES ('6d77040b-f91e-4487-ba38-21f6d6f4be9b', '41e52963-8ec1-6525-ba4e-b7a1054090fe', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('6f4fb758-f6bb-4dc0-aa04-6b8ea44862fe', '091c9088-c4fa-ab2d-7f80-d1310b03ed01', '1');
INSERT INTO `jeecg_troletauth` VALUES ('6f937d8b-e718-4069-b9d4-30c49f025054', 'adf80bac-fb13-9c69-bc62-feb64804e72f', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('71d09221-f516-4cf5-8dd5-747bc066a3ab', 'cdgl', '0');
INSERT INTO `jeecg_troletauth` VALUES ('763eaec9-2b15-44c7-a020-571462a644a5', '263cc6f1-0b28-bde9-3063-f985f7d4818d', '1');
INSERT INTO `jeecg_troletauth` VALUES ('795f9324-bd05-4183-a69c-2236611adde7', 'ce4ac90e-2ca1-ac65-2e39-bcef61ad54a4', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('7c833f17-3c27-4ccb-9af2-ff8ce9c56f3f', '41e52963-8ec1-6525-ba4e-b7a1054090fe', '1');
INSERT INTO `jeecg_troletauth` VALUES ('800ebd5f-48e5-4f9f-bafb-026aaa566140', 'qxgl', '0');
INSERT INTO `jeecg_troletauth` VALUES ('806f1dd1-752c-4752-89b2-bf61aa2c5839', '3cff5606-deba-d75b-9ee1-c918ca529441', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('852c0604-f133-4e5c-a07f-83aa86d87975', '7924309f-f1c8-4618-39ff-463df09a3a18', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('89d22dd8-e24f-49eb-98c7-afef641076c2', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('89f13d8e-3b17-4f51-ba2c-5d0ecda3c8bc', 'c5fe2b18-b760-9de7-de5a-7123fc8f45d4', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('8a4b93c4-5f95-44cb-b432-1dd0f7561c63', 'f6507ed3-0fba-4c30-0955-0cdbf1045df8', '1');
INSERT INTO `jeecg_troletauth` VALUES ('8cb7e13f-dadf-4001-baa6-83f0500c2cd4', 'c2ea2112-a7e3-c607-89cb-199312c4edc5', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('8e424c48-6106-4e7c-ad70-aa4141c94198', 'a2dab35a-d183-4b46-6791-d32716985a58', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('914bb504-b633-42ac-9772-0aabbe431948', '091c9088-c4fa-ab2d-7f80-d1310b03ed01', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('93d30648-6ced-4d30-af8a-a19dba890828', '5a7119bf-7900-ca78-27bd-3a431426f07e', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('93edb5b9-2b7c-4649-b9bb-566bb2f68c0c', '0876d610-1adb-6bcd-57a4-08d2cc09d0cd', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('98bb85d9-2cc9-47c9-ac82-9879741f90fb', 'orgmanager', '0');
INSERT INTO `jeecg_troletauth` VALUES ('99da0ee2-3f8d-41dd-91d3-612e015d4191', 'c5fe2b18-b760-9de7-de5a-7123fc8f45d4', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('a2d9cf17-d9df-4d4d-b3e5-9b301c7ea616', 'c219487c-66ec-07a0-cd70-5308876ce9a3', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('a5879016-30c2-4dc8-a315-0ec5e9e2eeae', '8cc046f7-f2d1-197f-24fd-af41cb777ded', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('a70238e5-ad79-407b-bb2b-80f22a87f63d', 'druidIndex', '0');
INSERT INTO `jeecg_troletauth` VALUES ('aaf13806-ec53-4bee-b9aa-4718b656f4e0', '78c1030f-9d8d-a22e-4c05-624f1f8f89aa', '1');
INSERT INTO `jeecg_troletauth` VALUES ('acafa6c6-7869-4d6a-9e87-3ced7524dbe6', 'a2dab35a-d183-4b46-6791-d32716985a58', '1');
INSERT INTO `jeecg_troletauth` VALUES ('aff83087-ff99-4f74-b4c3-4cc0fb0a2ea7', '7bb7268b-9a24-40e0-50b9-b177d87227c1', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('b0d3d476-1788-46b6-8b10-edfa57d454f0', 'xtgl', '0');
INSERT INTO `jeecg_troletauth` VALUES ('b448b437-336c-44c9-be4d-90c4c8f91303', '68545c2c-7408-19b5-9296-b3d4fa345222', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('ba9bf160-2f7c-44ac-99d5-13d407620372', 'c219487c-66ec-07a0-cd70-5308876ce9a3', '1');
INSERT INTO `jeecg_troletauth` VALUES ('bbf75d6f-97d3-4284-a522-b18c04383b49', 'adf80bac-fb13-9c69-bc62-feb64804e72f', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('bc86f66f-518b-4d32-b555-c565847d4d5f', 'adae52a9-4f93-d619-5dd7-a60be9184529', '1');
INSERT INTO `jeecg_troletauth` VALUES ('c78ed7b1-c5e6-4db6-9198-3ddd30554f7a', '0217959f-ef41-5d2d-3b83-eac4b7d10f84', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('c7a2cc56-f3c4-4641-9b77-f07876c783ea', 'sjkgl', '0');
INSERT INTO `jeecg_troletauth` VALUES ('c9c6f098-7449-4511-baae-80d205c6d5a3', '365937d4-c337-3614-0696-a20e7ee295c0', '1');
INSERT INTO `jeecg_troletauth` VALUES ('ce1e6c3d-be14-4e4c-81b1-0020a6aac5b8', 'adae52a9-4f93-d619-5dd7-a60be9184529', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('ce3a23f3-c6b8-4392-a283-71d2242ca33f', 'a2dab35a-d183-4b46-6791-d32716985a58', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('d2080578-5a71-4e22-98f5-360d0c287aea', '7df74098-57b3-aa00-9826-a1f894783f9b', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('d438aa77-c780-4669-be93-be55a95ec296', '263cc6f1-0b28-bde9-3063-f985f7d4818d', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('d761c6a8-a879-4df8-b9f0-02a254e4dc9a', '7924309f-f1c8-4618-39ff-463df09a3a18', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('dc57a224-33af-4d21-8159-f82ee3f933ff', '78c1030f-9d8d-a22e-4c05-624f1f8f89aa', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('de7fde34-37eb-4bad-ad41-7241837847a0', 'f4402a53-e929-f4fc-771d-09dd474a0aea', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('deafcc28-5cc1-431b-921a-8902bcb1ff21', '3cff5606-deba-d75b-9ee1-c918ca529441', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('e02f017a-add6-4417-a232-10397070e977', '0876d610-1adb-6bcd-57a4-08d2cc09d0cd', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('e0edf11e-a321-426f-8797-5cfe01ed76bd', '0217959f-ef41-5d2d-3b83-eac4b7d10f84', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('e322565f-e2e7-43f1-a366-b77af1664797', 'f4402a53-e929-f4fc-771d-09dd474a0aea', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('e8bbe950-7456-4f4b-8773-6602f36d9bae', 'c32570e8-9bc0-0887-4f69-7b5128e2a331', '1');
INSERT INTO `jeecg_troletauth` VALUES ('f3e82363-5031-460a-a664-bfc680581a6f', 'a0b68a51-d4b7-4ba0-31b0-de3341bdf240', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_troletauth` VALUES ('f59b526c-0dfc-4132-a2b3-fb9cd183fd35', 'dict', '0');
INSERT INTO `jeecg_troletauth` VALUES ('f73a4d32-2a1f-472f-add2-7329a5521b2d', '78c1030f-9d8d-a22e-4c05-624f1f8f89aa', '2dadab80-68aa-d8c6-8525-3c3384821b40');
INSERT INTO `jeecg_troletauth` VALUES ('f85cd573-f27b-46db-995a-f17ce9d0a899', 'yhgl', '0');
INSERT INTO `jeecg_troletauth` VALUES ('fab0de8d-a352-4266-8b10-e09ff561e91c', 'a0b68a51-d4b7-4ba0-31b0-de3341bdf240', '1');
INSERT INTO `jeecg_troletauth` VALUES ('fadbca23-368c-4953-bad4-8344b2ab8a44', '365937d4-c337-3614-0696-a20e7ee295c0', 'efbf348a-d914-324a-e420-82a11b114fdf');
INSERT INTO `jeecg_tuser` VALUES ('0', '2013-05-14 21:11:17', '2013-05-14 21:11:17', 'admin', '21232f297a57a5a743894a0e4a801fc3', '', '', '01', '超级用户', '1', '01');
INSERT INTO `jeecg_tuser` VALUES ('bf4b784f-2bed-4d25-dcde-8855e5b15660', '2013-05-14 21:36:49', '2013-06-13 16:38:27', 'zhangsan', 'c8837b23ff8aaa8a2dde915473ce0991', '', '', '01', '张开票', '1', '01');
INSERT INTO `jeecg_tuser` VALUES ('ca65c62f-380c-fc16-94ef-2157d3d42cc5', '2013-06-13 16:32:16', '2013-06-13 16:37:36', 'lijie', 'c8837b23ff8aaa8a2dde915473ce0991', '', '', '01', '李姐', '1', '01');
INSERT INTO `jeecg_tusertrole` VALUES ('94dff378-a7d2-43cf-b016-6c4878177176', '1', 'ca65c62f-380c-fc16-94ef-2157d3d42cc5');
INSERT INTO `jeecg_tusertrole` VALUES ('e68152a1-bafe-4b1a-9d3c-df4b41438b68', '0', '0');
INSERT INTO `jeecg_tusertrole` VALUES ('ed9a30da-5792-47ba-9c78-e6b1be85ccfc', '2dadab80-68aa-d8c6-8525-3c3384821b40', 'bf4b784f-2bed-4d25-dcde-8855e5b15660');
INSERT INTO `t_kc_stock` VALUES ('ff8080813f4c1623013f4d0c08780003', '001', '00002', '20130202', '140', '6.00', '2015-06-30');
INSERT INTO `t_kc_stock` VALUES ('ff8080813f4c1623013f4d0c08970005', '001', '00021', '20130909', '300', '8.00', '2015-08-26');
INSERT INTO `t_rk_stockin` VALUES ('201306160001', '2013-06-16 20:52:05', '001', '0001', '2', null, '2013-06-16 20:53:21', '2013-06-16 20:54:00', '3600.00', '1', '0', '超级用户', '0', '超级用户', '测试', '20130616001');
INSERT INTO `t_rk_stockin_detail` VALUES ('ff8080813f4c1623013f4d0c08680002', '201306160001', '00002', '20130202', '2015-06-30', '200', '6.00', '1200.00', '50');
INSERT INTO `t_rk_stockin_detail` VALUES ('ff8080813f4c1623013f4d0c08780004', '201306160001', '00021', '20130909', '2015-08-26', '300', '8.00', '2400.00', '0');
INSERT INTO `t_th_stockback` VALUES ('20130616001', '001', '0001', '0', '超级用户', '2013-06-16 20:54:00', '2013-06-16 20:54:00', '1', '', '201306160001', '300.00');
INSERT INTO `t_th_stockback_detail` VALUES ('ff8080813f4c1623013f4d0c9f880006', '20130616001', '00002', '20130202', '2015-06-30', '50', '6.00', '300.00', 'ff8080813f4c1623013f4d0c08680002');
INSERT INTO `t_xs_customer_price` VALUES ('00002', '0001', '12.00', '2013-06-16 20:57:11', '2013-06-16 20:57:11', '0001-00002');
INSERT INTO `t_xs_sale` VALUES ('201306160001', '0001', '2013-06-16 20:56:43', '0001', '20.00', '', '2', '超级用户', '0', '2013-06-16 21:01:09', '2013-06-16 20:57:11', '201306160001', '240.00', '1');
INSERT INTO `t_xs_sale_back` VALUES ('201306160001', '201306160001', '0001', '2013-06-16 21:01:03', '0001', '20.00', '', '2', '超级用户', '0', '2013-06-16 21:01:09', '2013-06-16 21:01:09', '120.00', '1');
INSERT INTO `t_xs_sale_back_detail` VALUES ('ff8080813f4c1623013f4d132d530008', '201306160001', '00002', '20130202', '10', '12.00', '6.00', '120.00', '001', '12333', null, 'ff8080813f4c1623013f4d0c08780003', '2015-06-30', 'ff8080813f4c1623013f4d0f8b840007');
INSERT INTO `t_xs_sale_detail` VALUES ('ff8080813f4c1623013f4d0f8b840007', '201306160001', '00002', '20130202', '20', '12.00', '6.00', '240.00', '001', '12333', null, 'ff8080813f4c1623013f4d0c08780003', '2015-06-30', '10');
INSERT INTO `t_xx_accounter` VALUES ('0001', '李金波', '01', '34', '1984-06-18', '1', '001', '2013-06-12', '稍微', '20.00', '1', '2013-06-13 17:55:37', '2013-06-13 17:55:37', '1');
INSERT INTO `t_xx_accounter` VALUES ('0002', '张万福', '01', '36', '1983-10-11', '15', '003', '2009-07-16', '收发室', '23.00', '1', '2013-06-13 17:56:31', '2013-06-13 17:56:31', '2');
INSERT INTO `t_xx_area` VALUES ('001', '华北地区', '章子怡', '3.89', '额', '2013-05-13 09:09:45', '2013-05-13 09:09:45', '1');
INSERT INTO `t_xx_area` VALUES ('002', '华中地区', '张小会', '1.29', '测试', '2013-05-13 11:01:30', '2013-05-13 11:01:30', '2');
INSERT INTO `t_xx_area` VALUES ('003', '华南地区', '赵子龙', '2.90', '何必', '2013-05-13 11:01:54', '2013-05-13 11:01:54', '3');
INSERT INTO `t_xx_area` VALUES ('004', '西北地区', '周末', '99.00', '测试', '2013-05-13 11:02:19', '2013-05-13 15:50:52', '4');
INSERT INTO `t_xx_area` VALUES ('005', '东北地区', '赵和名', '3.59', '圣斗士1', '2013-05-13 15:50:25', '2014-07-09 10:07:52', '5');
INSERT INTO `t_xx_article` VALUES ('00001', '胃肠灵胶囊', 'wcljn', '24粒', '昆明群芳药业', null, null, '7.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '1', '28.00');
INSERT INTO `t_xx_article` VALUES ('00002', '百贝益肺胶囊', 'bbyfjn', '24粒', '昆明群芳药业', null, null, '7.00', '6.00', '01', '02', null, null, null, '2013-06-16 20:53:21', '2013-06-06 22:47:56', '2', '32.00');
INSERT INTO `t_xx_article` VALUES ('00003', '参七心疏胶囊', 'cqxsjn', '24粒', '昆明群芳药业', null, null, '9.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '3', '30.00');
INSERT INTO `t_xx_article` VALUES ('00004', '复方胃痛胶囊', 'ffwtjn', '24粒', '贵州万胜药业', null, null, '11.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '4', '32.00');
INSERT INTO `t_xx_article` VALUES ('00005', '九味痔疮胶囊', 'jwzcjn', '30粒', '贵州万胜药业', null, null, '12.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '5', '38.00');
INSERT INTO `t_xx_article` VALUES ('00006', '伤痛克酊', 'stkd', '30ml', '贵州万胜药业', '', '', '10.00', '0.00', '03', '02', null, null, '', '2013-06-06 23:39:47', '2013-06-06 22:47:56', '6', '36.00');
INSERT INTO `t_xx_article` VALUES ('00007', '妇科止带片', 'fkzdp', '36粒', '贵州万胜药业', null, null, '6.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '7', '25.00');
INSERT INTO `t_xx_article` VALUES ('00008', '止咳宁嗽胶囊', 'zknsjn', '24粒', '贵州万胜药业', null, null, '4.00', '0.00', '01', '02', null, null, null, '2013-06-07 14:45:30', '2013-06-06 22:47:56', '8', '15.00');
INSERT INTO `t_xx_article` VALUES ('00009', '肝乐欣胶囊', 'glxjn', '30粒', '贵州家诚药业', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '9', '65.00');
INSERT INTO `t_xx_article` VALUES ('00010', '附桂骨痛胶囊', 'fggtjn', '48粒', '贵州家诚药业', null, null, '9.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '10', '30.00');
INSERT INTO `t_xx_article` VALUES ('00011', '附桂骨痛胶囊', 'fggtjn', '60粒', '贵州家诚药业', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '11', '39.00');
INSERT INTO `t_xx_article` VALUES ('00012', '肉蔻五味丸', 'rkwww', '90丸', '内蒙古大唐药业', '', '', '13.00', '0.00', '01', '02', null, null, '', '2013-06-06 23:40:13', '2013-06-06 22:47:56', '12', '36.00');
INSERT INTO `t_xx_article` VALUES ('00013', '清肺十八味丸', 'qfsbww', '135丸', '内蒙古大唐药业', null, null, '43.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '13', '138.00');
INSERT INTO `t_xx_article` VALUES ('00014', '红花清肝十三味丸', 'hhqgssww', '60丸', '内蒙古大唐药业', null, null, '15.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '14', '48.00');
INSERT INTO `t_xx_article` VALUES ('00015', '十五味龙胆花丸', 'swwldhw', '60丸', '宁夏多维药业', null, null, '12.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '15', '38.00');
INSERT INTO `t_xx_article` VALUES ('00016', '洁白胶囊', 'jbjn', '24粒', '宁夏多维药业', null, null, '8.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:56', '2013-06-06 22:47:56', '16', '28.00');
INSERT INTO `t_xx_article` VALUES ('00017', '五味麝香丸', 'wwsxw', '25丸', '宁夏多维药业', '', '', '9.00', '0.00', '01', '02', null, null, '', '2013-06-06 23:40:24', '2013-06-06 22:47:56', '17', '30.00');
INSERT INTO `t_xx_article` VALUES ('00018', '十三味菥蓂丸', 'sswxmw', '36丸', '宁夏多维药业', '', '', '14.00', '0.00', '01', '02', null, null, '', '2013-06-06 23:40:38', '2013-06-06 22:47:56', '18', '46.00');
INSERT INTO `t_xx_article` VALUES ('00019', '十八味杜鹃丸', 'sbwdjw', '15丸', '宁夏多维药业', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '19', '48.00');
INSERT INTO `t_xx_article` VALUES ('00020', '二十五味鬼臼丸', 'eswwgjw', '16丸', '宁夏多维药业', null, null, '12.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '20', '38.00');
INSERT INTO `t_xx_article` VALUES ('00021', '八味獐牙菜丸', 'bwzycw', '45丸', '宁夏多维药业', '', '', '8.00', '8.00', '01', '02', null, null, '', '2013-06-16 20:53:21', '2013-06-06 22:47:57', '21', '28.00');
INSERT INTO `t_xx_article` VALUES ('00022', '三臣散', 'scs', '10袋', '宁夏多维药业', null, null, '0.00', '0.00', '04', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '22', '27.00');
INSERT INTO `t_xx_article` VALUES ('00023', '抗感胶囊', 'kgjn', '12粒', '宁夏多维药业', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '23', '17.00');
INSERT INTO `t_xx_article` VALUES ('00024', '玄麦甘桔颗粒', 'xmgjkl', '10袋', '宁夏多维药业', null, null, '4.00', '0.00', '04', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '24', '15.00');
INSERT INTO `t_xx_article` VALUES ('00025', '六味消痔胶囊', 'lwxzjn', '36粒', '陕西东泰制药', null, null, '11.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '25', '36.00');
INSERT INTO `t_xx_article` VALUES ('00026', '胃舒宁胶囊', 'wsnjn', '24粒', '陕西东泰制药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-16 14:27:09', '2013-06-06 22:47:57', '26', '30.00');
INSERT INTO `t_xx_article` VALUES ('00027', '丹桂香胶囊', 'dgxjn', '24粒', '陕西东泰制药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '27', '32.00');
INSERT INTO `t_xx_article` VALUES ('00028', '复方蛤青胶囊', 'ffgqjn', '24粒', '陕西东泰制药', '', '', '0.00', '0.00', '01', '02', null, null, '', '2013-06-06 23:48:40', '2013-06-06 22:47:57', '28', '32.00');
INSERT INTO `t_xx_article` VALUES ('00029', '复方蛤青胶囊', 'ffgqjn', '30粒', '陕西东泰制药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '29', '39.00');
INSERT INTO `t_xx_article` VALUES ('00030', '灵芝北芪片', 'lzbqp', '24片', '陕西东泰制药', '', '', '0.00', '0.00', '01', '02', null, null, '', '2013-06-06 23:40:54', '2013-06-06 22:47:57', '30', '32.00');
INSERT INTO `t_xx_article` VALUES ('00031', '根痛平胶囊', 'gtpjn', '30粒', '陕西东泰制药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '31', '36.00');
INSERT INTO `t_xx_article` VALUES ('00032', '复方珍珠暗疮胶囊', 'ffzzacjn', '36粒', '陕西东泰制药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '32', '33.00');
INSERT INTO `t_xx_article` VALUES ('00033', '心脑康胶囊', 'xnkjn', '40粒', '陕西东泰制药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '33', '25.00');
INSERT INTO `t_xx_article` VALUES ('00034', '妇炎平栓', 'fyps', '7粒', '陕西东泰制药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '34', '38.00');
INSERT INTO `t_xx_article` VALUES ('00035', '复方石韦胶囊', 'ffswjn', '36粒', '陕西东泰制药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '35', '32.00');
INSERT INTO `t_xx_article` VALUES ('00036', '顺气化痰片', 'sqhtp', '24片', '江苏中兴药业', null, null, '6.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '36', '23.00');
INSERT INTO `t_xx_article` VALUES ('00037', '顺气化痰颗粒', 'sqhtkl', '6袋', '江苏中兴药业', null, null, '0.00', '0.00', '04', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '37', '28.00');
INSERT INTO `t_xx_article` VALUES ('00038', '水飞蓟宾葡甲胺片', 'sfjbpjap', '60片', '江苏中兴药业', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '38', '33.00');
INSERT INTO `t_xx_article` VALUES ('00039', '龙生蛭胶囊', 'lszjn', '45粒', '陕西步长制药', '', '', '33.00', '0.00', '01', '02', null, null, '', '2013-06-06 23:41:19', '2013-06-06 22:47:57', '39', '48.00');
INSERT INTO `t_xx_article` VALUES ('00040', '参倍固肠胶囊', 'cbgcjn', '36粒', '贵州联盛药业', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-07 14:45:30', '2013-06-06 22:47:57', '40', '48.00');
INSERT INTO `t_xx_article` VALUES ('00041', '参倍固肠胶囊', 'cbgcjn', '12粒', '贵州联盛药业', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '41', '18.00');
INSERT INTO `t_xx_article` VALUES ('00042', '苯磺酸左旋氨氯地平片', 'bhszxaldpp', '14片', '贵州联盛药业', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '42', '28.00');
INSERT INTO `t_xx_article` VALUES ('00043', '苯磺酸左旋氨氯地平片', 'bhszxaldpp', '21片', '贵州联盛药业', null, null, '14.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '43', '45.00');
INSERT INTO `t_xx_article` VALUES ('00044', '苯磺酸左旋氨氯地平片', 'bhszxaldpp', '28片', '南昌弘益药业', null, null, '19.00', '0.00', '01', '02', null, null, null, '2013-06-07 14:45:30', '2013-06-06 22:47:57', '44', '59.00');
INSERT INTO `t_xx_article` VALUES ('00045', '盐酸氨基葡萄糖胶囊', 'ysajpttjn', '20粒', '浙江诚意药业', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '45', '26.00');
INSERT INTO `t_xx_article` VALUES ('00046', '洁肤净洗剂', 'jfjxj', '100ml(带冲洗剂)', '西安仁仁药业', null, null, '0.00', '0.00', '03', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '46', '28.00');
INSERT INTO `t_xx_article` VALUES ('00047', '洁肤净洗剂', 'jfjxj', '200ml', '西安仁仁药业', null, null, '0.00', '0.00', '03', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '47', '28.00');
INSERT INTO `t_xx_article` VALUES ('00048', '醋氯芬酸片', 'clfsp', '12片', '西安海欣制药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '48', '28.00');
INSERT INTO `t_xx_article` VALUES ('00049', '厄多司坦胶囊', 'edstjn', '12粒', '西安海欣制药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '49', '32.00');
INSERT INTO `t_xx_article` VALUES ('00050', '苯磺酸氨氯地平片', 'bhsaldpp', '30片', '浙江为康制药', null, null, '7.00', '0.00', '01', '02', null, null, null, '2013-06-16 14:27:09', '2013-06-06 22:47:57', '50', '35.00');
INSERT INTO `t_xx_article` VALUES ('00051', '双金胃疡胶囊', 'sjwyjn', '36粒', '贵州三仁堂药业', null, null, '9.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '51', '30.00');
INSERT INTO `t_xx_article` VALUES ('00052', '复肾宁胶囊', 'fsnjn', '36粒', '贵州三仁堂药业', null, null, '6.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '52', '25.00');
INSERT INTO `t_xx_article` VALUES ('00053', '杜仲降压片', 'dzjyp', '75片', '贵州百花医药', null, null, '10.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '53', '32.00');
INSERT INTO `t_xx_article` VALUES ('00054', '山楂内消丸', 'sznxwscnxw', '6袋', '贵州百花医药', '', '', '4.00', '0.00', '04', '02', null, null, '', '2013-06-06 23:41:51', '2013-06-06 22:47:57', '54', '14.00');
INSERT INTO `t_xx_article` VALUES ('00055', '痤疮软膏', 'ccrg', '20克', '贵州百花医药', '', '', '7.00', '0.00', '01', '02', null, null, '', '2013-06-06 23:42:01', '2013-06-06 22:47:57', '55', '22.00');
INSERT INTO `t_xx_article` VALUES ('00056', '复方五倍子水杨酸搽剂', 'ffwbzsyscj', '30毫升', '贵州百花医药', null, null, '7.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '56', '22.00');
INSERT INTO `t_xx_article` VALUES ('00057', '藿香正气水', 'hxzqs', '10支', '贵州百花医药', '', '', '3.00', '0.00', '01', '02', null, null, '', '2013-06-06 23:39:03', '2013-06-06 22:47:57', '57', '7.00');
INSERT INTO `t_xx_article` VALUES ('00058', '大山楂颗粒', 'dszkldsckl', '6袋', '贵州百花医药', '', '', '0.00', '0.00', '04', '02', null, null, '', '2013-06-06 23:42:16', '2013-06-06 22:47:57', '58', '9.00');
INSERT INTO `t_xx_article` VALUES ('00059', '银翘解毒片', 'yqjdp', '24片', '贵州百花医药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '59', '7.00');
INSERT INTO `t_xx_article` VALUES ('00060', '首乌延寿片', 'swysp', '50片', '贵州百花医药', null, null, '6.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:57', '2013-06-06 22:47:57', '60', '19.00');
INSERT INTO `t_xx_article` VALUES ('00061', '牛黄解毒片', 'nhjdp', '24片', '贵州百花医药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '61', '5.00');
INSERT INTO `t_xx_article` VALUES ('00062', '桑菊感冒片', 'sjgmp', '24片', '贵州百花医药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '62', '8.00');
INSERT INTO `t_xx_article` VALUES ('00063', '复方黄连素片', 'ffhlsp', '24片', '贵州百花医药', null, null, '1.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '63', '5.00');
INSERT INTO `t_xx_article` VALUES ('00064', '陈香露白露片', 'cxlblp', '24片', '贵州百花医药', null, null, '2.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '64', '8.00');
INSERT INTO `t_xx_article` VALUES ('00065', '参梅养胃颗粒', 'cmywkl', '6袋', '江苏中兴药业', null, null, '6.00', '0.00', '04', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '65', '19.00');
INSERT INTO `t_xx_article` VALUES ('00066', '参芪健胃颗粒', 'sqjwklcqjwkl', '6袋', '江苏中兴药业', '', '', '6.00', '0.00', '04', '02', null, null, '', '2013-06-06 23:42:36', '2013-06-06 22:47:58', '66', '19.00');
INSERT INTO `t_xx_article` VALUES ('00067', '蒲地蓝消炎片', 'pdlxyp', '60片', '甘肃岷海制药', null, null, '3.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '67', '15.00');
INSERT INTO `t_xx_article` VALUES ('00068', '金莲花胶囊', 'jlhjn', '48粒', '牙克石市森健药业', null, null, '12.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '68', '39.00');
INSERT INTO `t_xx_article` VALUES ('00069', '银黄胶囊', 'yhjn', '36粒', '贵州安华药业', null, null, '3.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '69', '12.00');
INSERT INTO `t_xx_article` VALUES ('00070', '银杏叶片', 'yxyp', '48片', '广东省博罗先锋药业', null, null, '4.00', '0.00', '01', '02', null, null, null, '2013-06-16 14:27:09', '2013-06-06 22:47:58', '70', '15.00');
INSERT INTO `t_xx_article` VALUES ('00071', '辛伐他汀片', 'xfttp', '14片', '山东凤凰制药', null, null, '3.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '71', '13.00');
INSERT INTO `t_xx_article` VALUES ('00072', '冠脉宁片', 'gmnp', '45片', '通化斯威药业', null, null, '5.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '72', '17.00');
INSERT INTO `t_xx_article` VALUES ('00073', '盐酸二甲双胍缓释片', 'ysejsghsp', '18片', '山东凤凰制药', '', '', '4.00', '0.00', '01', '02', null, null, '', '2013-06-06 23:42:52', '2013-06-06 22:47:58', '73', '14.00');
INSERT INTO `t_xx_article` VALUES ('00074', '复方桔梗止咳片', 'ffjgzkp', '36片', '贵州三仁堂药业', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '74', '13.00');
INSERT INTO `t_xx_article` VALUES ('00075', '脑得生片', 'ndsp', '48片', '景忠山国药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '75', '18.00');
INSERT INTO `t_xx_article` VALUES ('00076', '妇科调经片', 'fkdjp', '36片', '景忠山国药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '76', '16.00');
INSERT INTO `t_xx_article` VALUES ('00077', '妇科调经片', 'fkdjp', '48粒', '景忠山国药', null, null, '0.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '77', '22.00');
INSERT INTO `t_xx_article` VALUES ('00078', '感冒清热颗粒', 'gmqrkl', '10袋', '北京龙泰基药业', null, null, '0.00', '0.00', '04', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '78', '15.00');
INSERT INTO `t_xx_article` VALUES ('00079', '橘红颗粒', 'jhkljgkl', '10袋', '北京龙泰基药业', '', '', '0.00', '0.00', '04', '02', null, null, '', '2013-06-07 14:45:30', '2013-06-06 22:47:58', '79', '18.00');
INSERT INTO `t_xx_article` VALUES ('00080', '蒲公英颗粒', 'pgykl', '10袋', '北京龙泰基药业', null, null, '0.00', '0.00', '04', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '80', '19.00');
INSERT INTO `t_xx_article` VALUES ('00081', '百咳静糖浆', 'bkjtj', '100ml', '北京龙泰基药业', null, null, '0.00', '0.00', '03', '02', null, null, null, '2013-06-07 14:45:30', '2013-06-06 22:47:58', '81', '19.00');
INSERT INTO `t_xx_article` VALUES ('00082', '九味羌活颗粒', 'jwqhkl', '10袋', '北京龙泰基药业', null, null, '0.00', '0.00', '04', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '82', '25.00');
INSERT INTO `t_xx_article` VALUES ('00083', '感冒解毒灵颗粒', 'gmjdlkl', '9袋', '北京龙泰基药业', null, null, '0.00', '0.00', '04', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '83', '18.00');
INSERT INTO `t_xx_article` VALUES ('00084', '川贝清肺糖浆', 'cbqftj', '100ml', '北京龙泰基药业', null, null, '0.00', '0.00', '03', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '84', '14.00');
INSERT INTO `t_xx_article` VALUES ('00085', '氨基酸片口服液', 'ajspkfy', '90片*两瓶250ml', '樟树市齐灵药业', '', '', '37.00', '0.00', '02', '02', null, null, '', '2013-06-16 01:23:44', '2013-06-06 22:47:58', '85', '138.00');
INSERT INTO `t_xx_article` VALUES ('00086', '威威钙片樟树市齐灵药业', 'wwgpzssqlyy', '90片', '樟树市齐灵药业', null, null, '8.00', '0.00', '01', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '86', '32.00');
INSERT INTO `t_xx_article` VALUES ('00087', '维铁素冲剂', 'wtscj', '4g*20包', '樟树市齐灵药业', null, null, '8.00', '0.00', '04', '02', null, null, null, '2013-06-06 22:47:58', '2013-06-06 22:47:58', '87', '32.00');
INSERT INTO `t_xx_article` VALUES ('00088', '安第斯山玛珈', 'adssmj', '90片*2瓶(礼盒装)', '昆明元正生物科技有限公司', '89898989898', '', '63.00', '0.00', '03', '02', null, null, '22222', '2014-07-09 10:06:46', '2013-06-06 22:47:58', '88', '1680.00');
INSERT INTO `t_xx_article` VALUES ('00089', '安第斯山玛珈', 'adssmj', '90片*1瓶(简装)', '昆明元正生物科技有限公司', '', '', '63.00', '0.00', '03', '02', null, null, '', '2013-06-17 09:13:20', '2013-06-06 22:47:58', '89', '800.00');
INSERT INTO `t_xx_article` VALUES ('00090', '安第斯山玛珈', 'adssmj', '32片/盒', '昆明元正生物科技有限公司', '', '', '63.00', '0.00', '01', '02', null, null, '', '2013-06-10 20:29:38', '2013-06-06 22:47:58', '90', '198.00');
INSERT INTO `t_xx_article` VALUES ('00091', '测试', 'cs', '2', '2', '22', '', null, '0.00', '02', '02', null, null, '', '2013-06-16 00:35:47', '2013-06-16 00:35:47', '91', '12.00');
INSERT INTO `t_xx_catelog` VALUES ('01', '药品类别', 'SCOTT 工作室', '', null, '1');
INSERT INTO `t_xx_catelog` VALUES ('0101', '西药', '财务室', 'icon-annex', '01', '2');
INSERT INTO `t_xx_catelog` VALUES ('6dbeeb1f-3c67-2441-d4b1-28e7caf9529e', '中成药', null, 'icon-bing', '01', '3');
INSERT INTO `t_xx_catelog` VALUES ('739a552e-a701-ae4a-37b9-a40fecf653ba', '保健品', null, 'icon-cale', '01', '4');
INSERT INTO `t_xx_company` VALUES ('10000', '长沙XXXXXX科技有限公司', '13618495810', '4500089895', 'zhaopei2773@126.com', '长沙XX区汽车南站', '李姐', '商品非质量原因，一个月后概不退货');
INSERT INTO `t_xx_customer` VALUES ('0001', '永安大药房', '0001', '赵红', '15189585232', 'zhaopei2773@126.com', '1313', '', '1', '2013-06-13 17:58:43', '2013-06-13 17:58:43');
INSERT INTO `t_xx_customer` VALUES ('0002', '百姓大药房', '0002', '张红', '1313', 'zhaopei2773@126.com', '为二位', '', '2', '2013-06-13 17:59:10', '2013-06-13 17:59:10');
INSERT INTO `t_xx_customer` VALUES ('0003', '康鑫大药房', '0001', '何小萌', '15189585232', 'zhaopei2773@126.com', '是否', '是', '3', '2013-06-13 17:59:39', '2013-06-13 17:59:39');
INSERT INTO `t_xx_depot` VALUES ('001', '正泰药品库', '李姐', '长沙', '15189585232', '1', '2013-06-13 17:57:59', '2013-06-13 17:57:19', '1');
INSERT INTO `t_xx_subject` VALUES ('001', '销售出库收款', '1', '销售出库收款', '2013-05-21 22:46:08', '2013-05-21 21:26:58', '0', '超级用户', '1');
INSERT INTO `t_xx_subject` VALUES ('002', '销售退货付款', '2', '销售退货付款', '2013-05-21 22:46:33', '2013-05-21 22:46:33', '0', '超级用户', '2');
INSERT INTO `t_xx_subject` VALUES ('003', '采购入库付款', '2', '采购入库付款', '2013-05-21 22:46:56', '2013-05-21 22:46:56', '0', '超级用户', '3');
INSERT INTO `t_xx_subject` VALUES ('004', '采购退货收款', '1', '采购退货收款', '2013-05-21 22:47:13', '2013-05-21 22:47:13', '0', '超级用户', '4');
INSERT INTO `t_xx_subject` VALUES ('005', '员工报销费用', '2', '员工报销费用', '2013-05-21 22:49:45', '2013-05-21 22:49:45', '0', '超级用户', '5');
INSERT INTO `t_xx_subject` VALUES ('006', '公司日常用品费用', '2', '公司日常用品费用', '2013-05-21 22:50:14', '2013-05-21 22:50:14', '0', '超级用户', '6');
INSERT INTO `t_xx_subject` VALUES ('007', '缴纳税费', '2', '缴纳税费', '2013-05-21 22:50:57', '2013-05-21 22:50:57', '0', '超级用户', '7');
INSERT INTO `t_xx_subject` VALUES ('008', '水电费', '2', '水电费', '2013-05-21 22:52:59', '2013-05-21 22:52:59', '0', '超级用户', '8');
INSERT INTO `t_xx_subject` VALUES ('009', '商品报损费用', '2', '商品报损费用', '2013-05-21 22:53:44', '2013-05-21 22:53:44', '0', '超级用户', '9');
INSERT INTO `t_xx_supplier` VALUES ('0001', '湖南九华医药厂', '张华', '1331', '长沙', '1231', 'zhaopei2773@126.com', '', '2013-06-13 17:52:17', '2013-06-13 17:52:17', '1');
INSERT INTO `t_xx_supplier` VALUES ('0002', '湖南时代阳光', '赵小龙', '15189585232', '长沙', '12', 'zhaopei2773@126.com', '', '2013-06-13 17:52:52', '2013-06-13 17:52:52', '2');
INSERT INTO `t_xx_supplier` VALUES ('0003', '贵州天麟医药公司', '赵子风', '15189585232', '贵州', '131', 'zhaopei2773@126.com', '131', '2013-06-13 17:54:06', '2013-06-13 17:54:06', '3');
