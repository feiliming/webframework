drop table if exists sys_user_role;
drop table if exists sys_role_resource;
drop table if exists sys_user;
drop table if exists sys_role;
drop table if exists sys_resource;
drop table if exists sys_organization;
drop table if exists sys_dictionarytype;
drop table if exists sys_dictionary;

drop table if exists sale_category;
drop table if exists sale_goods;
drop table if exists sale_log;
drop table if exists sale_purchase;
drop table if exists sale_sales;
drop table if exists mb_mbgroup;
drop table if exists mb_member;

--
-- 表的结构 用户角色表
--

create table sys_user_role (
    user_id smallint(5) not null,
    role_id smallint(5) not null,
    primary key (user_id, role_id)
)  ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='用户角色';

--
-- 数据初始化
--
insert into sys_user_role(user_id,role_id) values(1,1);

--
-- 表的结构 角色资源表
--

create table sys_role_resource (
    role_id smallint(5) not null,
    resource_id smallint(5) not null,
    primary key (role_id, resource_id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='角色资源';

--
-- 数据初始化
--
insert into sys_role_resource(role_id,resource_id) values(1,1);
insert into sys_role_resource(role_id,resource_id) values(1,2);
insert into sys_role_resource(role_id,resource_id) values(1,3);
insert into sys_role_resource(role_id,resource_id) values(1,4);
insert into sys_role_resource(role_id,resource_id) values(1,5);
insert into sys_role_resource(role_id,resource_id) values(1,6);
insert into sys_role_resource(role_id,resource_id) values(1,7);
insert into sys_role_resource(role_id,resource_id) values(1,8);
insert into sys_role_resource(role_id,resource_id) values(1,9);
insert into sys_role_resource(role_id,resource_id) values(1,10);
insert into sys_role_resource(role_id,resource_id) values(1,11);
insert into sys_role_resource(role_id,resource_id) values(1,12);
insert into sys_role_resource(role_id,resource_id) values(1,13);
insert into sys_role_resource(role_id,resource_id) values(1,14);
insert into sys_role_resource(role_id,resource_id) values(1,15);
insert into sys_role_resource(role_id,resource_id) values(1,16);
insert into sys_role_resource(role_id,resource_id) values(1,17);
insert into sys_role_resource(role_id,resource_id) values(1,18);
insert into sys_role_resource(role_id,resource_id) values(1,19);
insert into sys_role_resource(role_id,resource_id) values(1,20);
insert into sys_role_resource(role_id,resource_id) values(1,21);
insert into sys_role_resource(role_id,resource_id) values(1,22);
insert into sys_role_resource(role_id,resource_id) values(1,23);
insert into sys_role_resource(role_id,resource_id) values(1,24);
insert into sys_role_resource(role_id,resource_id) values(1,25);
insert into sys_role_resource(role_id,resource_id) values(1,26);
insert into sys_role_resource(role_id,resource_id) values(1,27);
insert into sys_role_resource(role_id,resource_id) values(1,28);
insert into sys_role_resource(role_id,resource_id) values(1,29);
insert into sys_role_resource(role_id,resource_id) values(1,30);
insert into sys_role_resource(role_id,resource_id) values(1,31);
insert into sys_role_resource(role_id,resource_id) values(1,32);
insert into sys_role_resource(role_id,resource_id) values(1,33);
insert into sys_role_resource(role_id,resource_id) values(1,34);
insert into sys_role_resource(role_id,resource_id) values(1,35);
insert into sys_role_resource(role_id,resource_id) values(1,36);
insert into sys_role_resource(role_id,resource_id) values(1,37);
insert into sys_role_resource(role_id,resource_id) values(1,38);
insert into sys_role_resource(role_id,resource_id) values(1,39);
insert into sys_role_resource(role_id,resource_id) values(1,40);
insert into sys_role_resource(role_id,resource_id) values(1,41);
insert into sys_role_resource(role_id,resource_id) values(1,42);
insert into sys_role_resource(role_id,resource_id) values(1,43);
insert into sys_role_resource(role_id,resource_id) values(1,44);
insert into sys_role_resource(role_id,resource_id) values(1,45);
insert into sys_role_resource(role_id,resource_id) values(1,46);
insert into sys_role_resource(role_id,resource_id) values(1,47);
insert into sys_role_resource(role_id,resource_id) values(1,48);
insert into sys_role_resource(role_id,resource_id) values(1,49);
insert into sys_role_resource(role_id,resource_id) values(1,50);
insert into sys_role_resource(role_id,resource_id) values(1,51);
insert into sys_role_resource(role_id,resource_id) values(1,52);
insert into sys_role_resource(role_id,resource_id) values(1,53);


--
-- 表的结构 资源表
--

create table sys_resource (
	id smallint(5) auto_increment,
	name varchar(64) not null,
	url varchar(100),
	description varchar(255),
	icon varchar(32),
	pid int,
	seq tinyint(1) not null default 0,
	state tinyint(1) not null default 0,
	resourcetype tinyint(1) not null default 0,
	createdatetime timestamp not null default 0,
    primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='资源';

--
-- 数据初始化
--
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(1,'系统管理','','系统管理','icon_sys',null,7,0,0,'2014-02-19 01:00:00');

insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(2,'资源管理','/resource/manager','资源管理','icon_resource',1,1,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(5,'资源列表','/resource/treeGrid','资源列表','icon_resource',2,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(6,'添加','/resource/add','资源添加','icon_resource',2,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(7,'编辑','/resource/edit','资源编辑','icon_resource',2,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(8,'删除','/resource/delete','资源删除','icon_resource',2,0,0,1,'2014-02-19 01:00:00');

insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(3,'角色管理','/role/manager','角色管理','icon_role',1,2,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(10,'角色列表','/role/dataGrid','角色列表','icon_role',3,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(11,'添加','/role/add','角色添加','icon_role',3,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(12,'编辑','/role/edit','角色编辑','icon_role',3,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(13,'删除','/role/delete','角色删除','icon_role',3,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(14,'授权','/role/grant','角色授权','icon_role',3,0,0,1,'2014-02-19 01:00:00');

insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(4,'用户管理','/user/manager','用户管理','icon_user',1,3,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(15,'用户列表','/user/dataGrid','用户列表','icon_user',4,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(16,'添加','/user/add','用户添加','icon_user',4,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(17,'编辑','/user/edit','用户编辑','icon_user',4,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(18,'删除','/user/delete','用户删除','icon_user',4,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(19,'查看','/user/view','用户查看','icon_user',4,0,0,1,'2014-02-19 01:00:00');

insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(20,'部门管理','/organization/manager','部门管理','icon_org',1,4,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(21,'部门列表','/organization/treeGrid','用户列表','icon_org',20,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(22,'添加','/organization/add','部门添加','icon_org',20,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(23,'编辑','/organization/edit','部门编辑','icon_org',20,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(24,'删除','/organization/delete','部门删除','icon_org',20,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(25,'查看','/organization/view','部门查看','icon_org',20,0,0,1,'2014-02-19 01:00:00');

insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(26,'数据字典','/dictionary/manager','数据字典','icon_dic',1,5,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(27,'字典列表','/dictionary/dataGrid','字典列表','icon_dic',26,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(28,'字典类别列表','/dictionarytype/dataGrid','字典类别列表','icon_dic',26,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(29,'添加','/dictionary/add','字典添加','icon_dic',26,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(30,'编辑','/dictionary/edit','字典编辑','icon_dic',26,0,0,1,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(31,'删除','/dictionary/delete','字典删除','icon_dic',26,0,0,1,'2014-02-19 01:00:00');

insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(32,'商品管理','','商品管理','icon_sys',null,1,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(38,'类别管理','/category/manager','类别管理','icon_dic',32,1,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(39,'商品管理','/goods/manager','商品管理','icon_dic',32,1,0,0,'2014-02-19 01:00:00');

insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(33,'进货管理','','进货管理','icon_sys',null,2,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(40,'供应商管理','/supplier/manager','供应商管理','icon_dic',33,1,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(41,'采购入库单','/purchase/warehousing','采购入库单','icon_dic',33,1,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(42,'采购退货单','/purchase/return','采购退货单','icon_dic',33,1,0,0,'2014-02-19 01:00:00');

insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(34,'销售管理','','销售管理','icon_sys',null,3,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(43,'客户管理','/customer/manager','客户管理','icon_dic',34,1,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(44,'销售单','/sales/sale','销售单','icon_dic',34,1,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(45,'销售退货单','/sales/return','销售退货单','icon_dic',34,1,0,0,'2014-02-19 01:00:00');

insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(35,'仓库管理','','仓库管理','icon_sys',null,4,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(46,'仓库设置','','仓库设置','icon_dic',35,1,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(47,'库存明细','','库存明细','icon_dic',35,1,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(48,'库存盘点','','库存盘点','icon_dic',35,1,0,0,'2014-02-19 01:00:00');

insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(36,'会员管理','','会员管理','icon_sys',null,5,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(49,'分组管理','','会员分组','icon_dic',36,1,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(50,'会员管理','','会员管理','icon_dic',36,2,0,0,'2014-02-19 01:00:00');

insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(37,'统计管理','','统计管理','icon_sys',null,6,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(51,'销售统计','','销售统计','icon_dic',37,2,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(52,'退货统计','','退货统计','icon_dic',37,2,0,0,'2014-02-19 01:00:00');
insert into sys_resource(id,name,url,description,icon,pid,seq,state,resourcetype,createdatetime) values(53,'进货统计','','进货统计','icon_dic',37,2,0,0,'2014-02-19 01:00:00');


--
-- 表的结构 角色表
--

create table sys_role(
	id smallint(5) auto_increment,
	name varchar(64) not null,
	seq tinyint(1) not null default 0,
	description varchar(255),
	isdefault tinyint(1) not null default 0,
 	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='角色';

--
-- 数据初始化
--
insert into sys_role(id,name,seq,isdefault,description) values(1,'超级管理员',0,0,'超级管理员，拥有全部权限');

--
-- 表的结构 用户表
--

create table sys_user (
	id smallint(5) auto_increment,
	loginname varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(64) not null,
	sex tinyint(1) not null default 0,
	age tinyint(1) not null default 0,
	usertype tinyint(1) not null default 0,
	isdefault tinyint(1) not null default 0,
	state tinyint(1) not null default 0,
	organization_id int not null default 0,
	createdatetime timestamp not null default 0,
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='用户' ;

--
-- 数据初始化
--
insert into sys_user (id, loginname, name, password, sex, age, usertype,isdefault,state,createdatetime,organization_id) values(1,'admin','超级管理员','21232f297a57a5a743894a0e4a801fc3',0,18,1,0,0,'2012-06-04 01:00:00',1);

--
-- 表的结构 组织机构表
--

create table sys_organization (
	id int auto_increment,
	name varchar(64) not null,
	address varchar(100),
	code varchar(64) not null,
	icon varchar(32),
	pid int,
	seq tinyint(1) not null default 0,
	createdatetime timestamp not null default 0,
    primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='组织机构';

--
-- 数据初始化
--
insert into sys_organization(id,name,address,code,icon,pid,seq,createdatetime) values(1,'XXX有限公司','地址','01','icon_company',null,0,'2014-02-19 01:00:00');

--
-- 表的结构 字典类别表
--

create table sys_dictionarytype (
	id int auto_increment,
	code varchar(64) not null,
	name varchar(64) not null,
	seq tinyint(1) not null default 0,
	description varchar(255),
	pid int,
    primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='字典分类';

--
-- 数据初始化
--
insert into sys_dictionarytype(id,code,name,seq,description,pid) values(1,'base','基础设置',0,'基础设置',null);
insert into sys_dictionarytype(id,code,name,seq,description,pid) values(2,'usertype','用户类型',0,'用户类型',1);

--
-- 表的结构 字典表
--

create table sys_dictionary (
	id int auto_increment,
	code varchar(64) not null,
	text varchar(64) not null,
	dictionarytype_id int not null,
	seq tinyint(1) not null default 0,
	state tinyint(1) not null default 0,
	isdefault tinyint(1) not null default 0,
    primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='数据字典';

--
-- 数据初始化
--
insert into sys_dictionary(id,code,text,dictionarytype_id,seq,state,isdefault) values(1,'0','管理员',2,0,0,0);
insert into sys_dictionary(id,code,text,dictionarytype_id,seq,state,isdefault) values(2,'1','用户',2,0,0,1);


--
-- 表的结构 商品分类表
--

create table  sale_category (
  	id int(10) auto_increment,
  	name varchar(100) not null,
	pid int(10) not null default 0,
   	seq tinyint(1) not null,
   	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='商品分类';

--
-- 表的结构 商品表
--

create table sale_goods (
  id int auto_increment,
  name varchar(100) not null,
  code varchar(32) not null unique,
  cat_id int not null,
  stock float not null  default 0 ,
  warn_stock tinyint(3) not null default 1,
  weight varchar(32) not null,
  unit varchar(32) not null,
  out_price decimal(8,2) not null,
  in_price decimal(8,2) not null,
  market_price decimal(8,2) not null,
  promote_price decimal(8,2) not null,
  ispromote tinyint(1) not null  default 0,
  promote_start_date date not null,
  promote_end_date date not null,
  ismemberprice tinyint(1) not null  default 1,
  creatymd date not null,
  lastinymd date not null,
  goods_desc varchar(200) not null,
  countamount float(10,2) unsigned not null,
  salesamount float(10,2) unsigned not null,
  primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='商品表';


--
-- 表的结构 商品管理日志表
--

create table  sale_log (
  id int auto_increment,
  type tinyint(1) not null,
  goods_id int not null,
  content text not null,
  user_id int(10) not null,
  username varchar(32) not null,
  dateymd date not null,
  primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='商品管理日志表' ;


--
-- 表的结构 进货表
--

create table  sale_purchase (
  id int auto_increment,
  goods_id int(10) not null,
  goods_sn varchar(32) not null,
  goods_name varchar(100) not null,
  cat_id int(10) not null,
  in_num float not null,
  out_num float not null  default 0,
  in_price decimal(8,2) not null,
  dateymd date not null,
  dateline int(10) not null,
  isdel tinyint(1) not null default 0,
   primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='进货表';

--
-- 表的结构 销售记录表
--

create table  sale_sales (
  id int not null auto_increment,
  goods_id int(10) not null,
  goods_sn varchar(16) not null,
  goods_name varchar(100) not null,
  cat_id int(10) not null,
  order_id varchar(14) not null,
  mid int(10) not null,
  membercardid varchar(16) not null,
  realname varchar(32) not null,
  num float not null,
  price decimal(8,2) not null,
  out_price decimal(8,2) not null,
  in_price decimal(8,2) not null,
  dateymd date not null,
  dateline int(10) not null,
  m_discount float(10,2) unsigned not null default 0.00,
  p_discount float(10,2) unsigned not null default 0.00,
  refund_type tinyint(1) not null default 0,
  refund_num float not null default 0,
  refund_amount decimal(8,2) not null default 0.00,
  sales_type tinyint(4) not null  default 0,
  primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='销售记录表';

--
-- 表的结构 会员用户组
--

create table  mb_mbgroup (
 	id int auto_increment,
  	name varchar(32) not null,
  	credit int(10) not null,
  	discount int(3) not null,
   	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='会员用户组';

--
-- 表的结构 会员表
--

create table  mb_member (
  id int auto_increment,
  membercardid varchar(16) not null unique,
  realname varchar(32) not null,
  phone varchar(16) not null,
  mobile varchar(16) not null,
  email varchar(32) not null,
  prov_id int(10) not null,
  prov_name varchar(32) not null,
  city_id int(10) not null,
  city_name varchar(32) not null,
  address varchar(200) not null,
  zipcode int(7) not null,
  cardid varchar(18) not null,
  state tinyint(1) not null default 1,
  grade tinyint(1) not null default 1,
  credit int(10) not null  default 0,
  regdateymd date not null,
  lastdateline date not null,
  mbgroup_id int not null,
  primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='会员信息表';

