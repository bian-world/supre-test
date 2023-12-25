drop table ui_page ;

create table ui_page
(
    id varchar(50) not null comment 'UI page id'
        primary key,
    num int null comment 'UI 页面num',
    parent_id varchar(50) not null comment 'UI 父页面ID',
    module_id varchar(50) not null comment '所属模块ID',
    project_id varchar(50) null comment '所属项目ID',
    name varchar(50) not null comment '名称',
    pageLevel  varchar(100) not null comment '页面路径',
    create_user varchar(50) not null comment '创建人',
    description varchar(1000) null comment '备注',
    create_time bigint(13) null comment '创建时间戳',
    update_time bigint(13) null comment '更新时间戳',
    sub_project_id varchar(50)   null comment 'zendao_project的id字段'
);


drop table ui_page_module;

create table ui_page_module
(
    id varchar(50) not null comment 'Test case node ID'
        primary key,
    project_id varchar(50) not null comment 'Project ID this node belongs to',
    name varchar(64) not null comment 'Node name',
    protocol varchar(64) not null comment 'Node protocol',
    parent_id varchar(50) null comment 'Parent node ID',
    level int(10) default 1 null comment 'Node level',
    pos double null comment 'Node order',
    create_time bigint(13) not null comment 'Create timestamp',
    update_time bigint(13) not null comment 'Update timestamp',
    create_user varchar(100) null,
    zentao_tree_id varchar(10) null comment '禅道产品模块id',
    zentao_branch_id varchar(10) null comment '禅道产品分支、平台id',
    zentao_branch_name varchar(50) null comment '禅道产品分支、平台名'
);


drop table ui_element;

create table ui_element
(
    id varchar(50) not null comment 'Ui element ID'
        primary key,
    num int null comment 'Ui element ID',
    page_id varchar(50) not null comment 'page ID this case belongs to',
    module_id varchar(50) not null comment 'Node ID this case belongs to',
    project_id varchar(50) not null comment 'Project ID this test belongs to',
    name varchar(255) not null comment 'Ui element name',
    location_type varchar(30) not null comment 'Location type',
    location varchar(300) not null comment 'Location',
    create_user varchar(100) null,
    version_id varchar(50) not null comment '版本ID',
    ref_id varchar(50) not null comment '指向初始版本ID',
    `order` bigint not null comment '自定义排序，间隔5000',
    latest tinyint(1) default 0 null comment '是否为最新版本 0:否，1:是',
    description varchar(1000) default '' null comment '元素描述',
    create_time bigint(13) not null comment 'Create timestamp',
    update_time bigint(13) not null comment 'Update timestamp'
);


drop table ui_element_location_type ;




create table ui_element_location_type
(
    id varchar(50) not null comment 'ID'
        primary key,
    location_type varchar(30) not null comment 'Location type',
    create_time bigint(13) not null comment 'Create timestamp',
    update_time bigint(13) not null comment 'Update timestamp'
);


drop table ui_element_operation;

create table ui_element_operation
(
    id varchar(50) not null comment 'ID'
        primary key,
    type varchar(30) not null comment '标注种类（鼠标，键盘',
    operation varchar(30) not null comment '操作方式（鼠标点击，鼠标拖动，键盘输入）',
    create_time bigint(13) not null comment 'Create timestamp',
    update_time bigint(13) not null comment 'Update timestamp'
);

drop table ui_scenario;

create table ui_scenario
(
    id varchar(255) not null
        primary key,
    project_id varchar(50) not null comment 'Project ID this test belongs to',
    tags varchar(2000) null comment 'tag list',
    user_id varchar(64) null comment 'User ID',
    module_id varchar(64) null comment 'Module ID',
    module_path varchar(1000) null,
    name varchar(255) not null comment 'ui scenario name',
    level varchar(100) null comment 'ui scenario level ',
    status varchar(100) not null comment 'ui scenario status ',
    principal varchar(100) not null comment 'ui scenario principal ',
    step_total int default 0 null comment 'Step total ',
    schedule varchar(255) null comment 'Test schedule (cron list)',
    scenario_definition longtext null comment 'Test scenario_definition json',
    description longtext null comment 'ui scenario description',
    create_time bigint(13) not null comment 'Create timestamp',
    update_time bigint(13) not null comment 'Update timestamp',
    pass_rate varchar(100) null,
    last_result varchar(100) null,
    report_id varchar(50) null,
    num int null comment 'ui scenario ID',
    original_state varchar(64) null,
    custom_num varchar(64) null comment 'custom num',
    create_user varchar(100) null,
    use_url longtext null comment '步骤中用到的url',
    version int(10) default 0 null comment '版本号',
    delete_time bigint(13) null comment 'Delete timestamp',
    delete_user_id varchar(64) null comment 'Delete user id',
    execute_times int null,
    `order` bigint not null comment '自定义排序，间隔5000',
    environment_type varchar(20) null,
    environment_json longtext null,
    environment_group_id varchar(50) null,
    version_id varchar(50) not null,
    ref_id varchar(255) not null,
    latest tinyint(1) default 0 null comment '是否为最新版本 0:否，1:是',
    sub_project_id varchar(50)   null comment 'zendao_project的id字段'
);


drop table ui_scenario_module;

create table ui_scenario_module
(
    id varchar(50) not null comment 'Test case node ID'
        primary key,
    project_id varchar(50) not null comment 'Project ID this node belongs to',
    name varchar(64) not null comment 'Node name',
    parent_id varchar(50) null comment 'Parent node ID',
    level int(10) default 1 null comment 'Node level',
    pos double null comment 'Node order',
    create_time bigint(13) not null comment 'Create timestamp',
    update_time bigint(13) not null comment 'Update timestamp',
    create_user varchar(100) null,
    zentao_tree_id varchar(10) null comment '禅道产品模块id',
    zentao_branch_id varchar(10) null comment '禅道产品分支、平台id',
    zentao_branch_name varchar(50) null comment '禅道产品分支、平台名'
);


alter table ui_page add column module_path varchar(1000) comment 'page所属模块路径';


alter table ui_page_module drop column protocol;

alter table ui_page modify column create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';
alter table ui_page modify column update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间';
alter table ui_page modify column pageLevel varchar(1000) DEFAULT null;
alter table ui_page MODIFY column parent_id varchar(50) DEFAULT null;



alter table ui_element MODIFY column version_id varchar(50) DEFAULT null;
alter table ui_element MODIFY column ref_id varchar(50) DEFAULT null;
alter table ui_element MODIFY column `order`  bigint(20) DEFAULT 0 ;


INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('041d2929-05d8-4b0d-b041-19651f600639', 'browser', 'to', '2022-09-13 17:31:02', '2022-10-09 11:26:29');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('4a2ebfc2-3e3c-11ed-921d-d13180f13639', 'WebElement', 'clear', '2022-09-13 17:31:02', '2022-09-28 14:47:03');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('4a323ea4-3e3c-11ed-921d-d13180f13639', 'WebElement', 'getAttribute', '2022-09-13 17:31:02', '2022-09-28 14:47:03');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('4a3357b2-3e3c-11ed-921d-d13180f13639', 'WebElement', 'click', '2022-09-13 17:31:02', '2022-09-28 14:47:03');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('4a341936-3e3c-11ed-921d-d13180f13639', 'WebElement', 'getCssValue', '2022-09-13 17:31:02', '2022-09-28 14:47:03');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('4a351e08-3e3c-11ed-921d-d13180f13639', 'WebElement', 'getDomAttribute', '2022-09-13 17:31:02', '2022-09-28 14:47:03');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('4a35d6e0-3e3c-11ed-921d-d13180f13639', 'WebElement', 'sendKeys', '2022-09-13 17:31:02', '2022-09-28 14:47:03');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('4a36af52-3e3c-11ed-921d-d13180f13639', 'WebElement', 'submit', '2022-09-13 17:31:02', '2022-09-28 14:47:03');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('b04327b6-354c-4b0f-8420-7ecf7f5e5fea', 'browser', 'back', '2022-09-13 17:31:02', '2022-10-09 11:26:29');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('cc982059-77d9-4bcc-8226-d7f7e9423d07', 'browser', 'forward', '2022-09-13 17:31:02', '2022-10-09 11:26:29');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('f6bcff41-d984-4db7-9e69-ffdb9b62ede2', 'browser', 'refresh', '2022-09-13 17:31:02', '2022-10-09 11:26:29');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('f6fe8830-3e42-11ed-921d-d13180f13639', 'WebElement', 'isSelected', '2022-09-13 17:31:02', '2022-09-28 14:47:03');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('f7019e9e-3e42-11ed-921d-d13180f13639', 'WebElement', 'isEnabled', '2022-09-13 17:31:02', '2022-09-28 14:47:03');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('f70276fc-3e42-11ed-921d-d13180f13639', 'WebElement', 'getText', '2022-09-13 17:31:02', '2022-09-28 14:47:03');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('f7033038-3e42-11ed-921d-d13180f13639', 'WebElement', 'getTagName', '2022-09-13 17:31:02', '2022-09-28 14:47:03');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('f815b738-d0d1-4f1a-ab55-4c0aa838958b', 'browser', 'open', '2022-09-13 17:31:02', '2022-10-09 11:26:29');
INSERT INTO supretest.ui_element_operation (id, type, operation, create_time, update_time) VALUES ('b73a289e-b44c-4fbb-bb19-48a0d8f9ea5d', 'JavascriptExecutor', 'executeScript', '2022-09-13 17:31:02', '2022-10-09 11:26:29');


INSERT INTO supretest.ui_element_location_type (id, location_type, create_time, update_time) VALUES ('17e5e543-bd11-4657-b190-a838b226e2d7', 'partlink', '2022-09-13 17:31:02', '2022-10-09 17:16:41');
INSERT INTO supretest.ui_element_location_type (id, location_type, create_time, update_time) VALUES ('18f52960-69a5-48af-9fff-867c2f7b0a4f', 'function', '2022-09-13 17:31:02', '2022-10-09 17:16:41');
INSERT INTO supretest.ui_element_location_type (id, location_type, create_time, update_time) VALUES ('5df920eb-f31b-4181-9a95-661be03a2f18', 'css', '2022-09-13 17:31:02', '2022-10-09 17:16:41');
INSERT INTO supretest.ui_element_location_type (id, location_type, create_time, update_time) VALUES ('6f7f0637-1ce8-40f6-bffc-5a6b7afe5232', 'xpath', '2022-09-13 17:31:02', '2022-09-13 17:31:02');
INSERT INTO supretest.ui_element_location_type (id, location_type, create_time, update_time) VALUES ('851e5529-0f90-4600-9361-4944ec3ab930', 'className', '2022-09-13 17:31:02', '2022-10-09 17:16:41');
INSERT INTO supretest.ui_element_location_type (id, location_type, create_time, update_time) VALUES ('8babd012-9749-4fa1-a07f-527ce8e13213', 'name', '2022-09-13 17:31:02', '2022-10-09 17:16:41');
INSERT INTO supretest.ui_element_location_type (id, location_type, create_time, update_time) VALUES ('be468ae9-1a2b-4689-903b-b7f9efb65d75', 'id', '2022-09-13 17:31:02', '2022-10-09 17:16:41');
INSERT INTO supretest.ui_element_location_type (id, location_type, create_time, update_time) VALUES ('bef63946-0bdd-4024-88b9-eaa0eafdf037', 'tag', '2022-09-13 17:31:02', '2022-10-09 17:16:41');
INSERT INTO supretest.ui_element_location_type (id, location_type, create_time, update_time) VALUES ('f815b738-d0d1-4f1a-ab55-4c0aa838958b', 'browser', '2022-09-13 17:31:02', '2022-10-09 17:16:41');
INSERT INTO supretest.ui_element_location_type (id, location_type, create_time, update_time) VALUES ('f815b738-d0d1-4f1a-ab55-4c0aa838958c', 'link', '2022-09-13 17:31:02', '2022-10-09 17:16:41');


INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('042eabdd-7a52-40f7-a27c-388ce0638c5e', 'partlink', 'sendKeys', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).sendKeys([''%s'']);', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('0745defd-5a2b-d01c-c141-2dd10375eee8', 'partlink', 'clear', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).clear();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('08184a22-e666-456c-b855-92dd92fd5c81', 'browser', 'to', 'js', 'WDS.browser.navigate().to("%s");', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('0cc35600-c9d4-b7df-1074-99959bd3e339', 'className', 'clear', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).clear();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('1f9c102e-3e42-11ed-921d-d13180f13639', 'partlink', 'isEnabled', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).isEnabled();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('1f9e3ec6-3e42-11ed-921d-d13180f13639', 'className', 'isEnabled', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).isEnabled();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('1fa03960-3e42-11ed-921d-d13180f13639', 'tag', 'isEnabled', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).isEnabled();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('1fa1b63c-3e42-11ed-921d-d13180f13639', 'id', 'isEnabled', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).isEnabled();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('1fa2ec1e-3e42-11ed-921d-d13180f13639', 'link', 'isEnabled', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).isEnabled();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('1fa3f852-3e42-11ed-921d-d13180f13639', 'xpath', 'isEnabled', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).isEnabled();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('1fb100ec-3e42-11ed-921d-d13180f13639', 'css', 'isEnabled', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).isEnabled();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('1fb25d98-3e42-11ed-921d-d13180f13639', 'name', 'isEnabled', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).isEnabled();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('221dc1d0-3e38-11ed-921d-d13180f13639', 'partlink', 'submit', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).submit();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('221f3844-3e38-11ed-921d-d13180f13639', 'className', 'submit', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).submit();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('2220d44c-3e38-11ed-921d-d13180f13639', 'tag', 'submit', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).submit();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('222220e0-3e38-11ed-921d-d13180f13639', 'id', 'submit', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).submit();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('22246314-3e38-11ed-921d-d13180f13639', 'link', 'submit', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).submit();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('222555d0-3e38-11ed-921d-d13180f13639', 'xpath', 'submit', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).submit();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('22266a1a-3e38-11ed-921d-d13180f13639', 'css', 'submit', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).submit();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('22275a06-3e38-11ed-921d-d13180f13639', 'name', 'submit', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).submit();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('2acfee08-afa6-42ed-8fd1-dd5d8f993c12', 'css', 'click', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).click();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('2c0f519c-2dae-4e34-9070-362a2dbc5cbd', 'name', 'sendKeys', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).sendKeys([''%s'']);', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('2fa8220e-1fca-af48-0276-dd042adee0ce', 'tag', 'clear', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).clear();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('3099fda1-be5e-4fe3-8c5f-43eb11eff268', 'partlink', 'click', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).click();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('37e83b78-3e3b-11ed-921d-d13180f13639', 'partlink', 'getDomAttribute', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).getDomAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('37e951c0-3e3b-11ed-921d-d13180f13639', 'className', 'getDomAttribute', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).getDomAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('37ea2b36-3e3b-11ed-921d-d13180f13639', 'tag', 'getDomAttribute', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).getDomAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('37eb1230-3e3b-11ed-921d-d13180f13639', 'id', 'getDomAttribute', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).getDomAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('37ebca36-3e3b-11ed-921d-d13180f13639', 'link', 'getDomAttribute', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).getDomAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('37ecba2c-3e3b-11ed-921d-d13180f13639', 'xpath', 'getDomAttribute', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).getDomAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('37f4ea3a-3e3b-11ed-921d-d13180f13639', 'css', 'getDomAttribute', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).getDomAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('37f65b5e-3e3b-11ed-921d-d13180f13639', 'name', 'getDomAttribute', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).getDomAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('3cf69571-0df2-4494-86da-bfde4015e01b', 'tag', 'sendKeys', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).sendKeys([''%s'']);', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('481d5fd6-3c6f-463a-8171-e4564ceb71a1', 'className', 'click', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).click();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('4e6a5469-98c9-41a5-86ef-835f4e5791e1', 'xpath', 'sendKeys', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).sendKeys([''%s'']);', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('4eeac7ce-4a89-45a6-8825-ea8382536bff', 'name', 'click', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).click();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('51b88b57-912c-40b2-b061-cb87a83070ce', 'className', 'sendKeys', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).sendKeys([''%s'']);', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('61d7517c-c5b0-bf7e-4de8-265a42291001', 'id', 'clear', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).clear();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('66777ff6-3e42-11ed-921d-d13180f13639', 'partlink', 'isSelected', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).isSelected();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('6678da5e-3e42-11ed-921d-d13180f13639', 'className', 'isSelected', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).isSelected();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('667a4790-3e42-11ed-921d-d13180f13639', 'tag', 'isSelected', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).isSelected();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('667b5f04-3e42-11ed-921d-d13180f13639', 'id', 'isSelected', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).isSelected();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('667cb408-3e42-11ed-921d-d13180f13639', 'link', 'isSelected', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).isSelected();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('667e34b8-3e42-11ed-921d-d13180f13639', 'xpath', 'isSelected', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).isSelected();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('667ee656-3e42-11ed-921d-d13180f13639', 'css', 'isSelected', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).isSelected();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('667f9448-3e42-11ed-921d-d13180f13639', 'name', 'isSelected', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).isSelected();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('67fe8d93-4cec-42c2-8a15-3aaaa6fb5f31', 'link', 'sendKeys', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).sendKeys([''%s'']);', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('6c57404c-a818-f9cc-a372-ec45eadfe4f3', 'link', 'clear', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).clear();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('7a1747ae-3e41-11ed-921d-d13180f13639', 'partlink', 'getText', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).getText();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('7a1c2224-3e41-11ed-921d-d13180f13639', 'className', 'getText', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).getText();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('7a1d60bc-3e41-11ed-921d-d13180f13639', 'tag', 'getText', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).getText();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('7a1e8988-3e41-11ed-921d-d13180f13639', 'id', 'getText', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).getText();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('7a1fe256-3e41-11ed-921d-d13180f13639', 'link', 'getText', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).getText();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('7a20bbae-3e41-11ed-921d-d13180f13639', 'xpath', 'getText', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).getText();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('7a2185d4-3e41-11ed-921d-d13180f13639', 'css', 'getText', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).getText();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('7a226d96-3e41-11ed-921d-d13180f13639', 'name', 'getText', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).getText();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('8857cffc-3e3a-11ed-921d-d13180f13639', 'partlink', 'getAttribute', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).getAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('885accac-3e3a-11ed-921d-d13180f13639', 'className', 'getAttribute', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).getAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('885b90ce-3e3a-11ed-921d-d13180f13639', 'tag', 'getAttribute', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).getAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('885c7dcc-3e3a-11ed-921d-d13180f13639', 'id', 'getAttribute', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).getAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('885d5292-3e3a-11ed-921d-d13180f13639', 'link', 'getAttribute', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).getAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('885f0f4c-3e3a-11ed-921d-d13180f13639', 'xpath', 'getAttribute', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).getAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('88610702-3e3a-11ed-921d-d13180f13639', 'css', 'getAttribute', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).getAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('8861c2dc-3e3a-11ed-921d-d13180f13639', 'name', 'getAttribute', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).getAttribute(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('8bef1aeb-daa0-4b32-800d-64f8eb4f20e4', 'browser', 'forward', 'js', 'WDS.browser.navigate().forward();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('93531287-da3c-4391-a285-211e42b34f65', 'id', 'sendKeys', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).sendKeys([''%s'']);', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('93531287-da3c-4391-a285-211e42b34f66', 'xpath', 'click', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).click();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('93531287-da3c-4391-a285-211e42b34f67', 'browser', 'open', 'js', 'WDS.browser.get("%s");', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('93531287-da3c-4391-a285-211e42b34f68', 'id', 'click', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).click();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('93d45b74-9831-4d06-b93e-b1480bcc5ae3', 'browser', 'refresh', 'js', 'WDS.browser.navigate().refresh();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('995ba70a-3faf-4500-a3b2-9cdb3b38e3de', 'browser', 'back', 'js', 'WDS.browser.navigate().back();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('a3ba8c92-3e41-11ed-921d-d13180f13639', 'partlink', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).getTagName();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('a3bcb0f8-3e41-11ed-921d-d13180f13639', 'className', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).getTagName();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('a3bdc5b0-3e41-11ed-921d-d13180f13639', 'tag', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).getTagName();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('a3beb254-3e41-11ed-921d-d13180f13639', 'id', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).getTagName();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('a3bfbb40-3e41-11ed-921d-d13180f13639', 'link', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).getTagName();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('a3c0808e-3e41-11ed-921d-d13180f13639', 'xpath', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).getTagName();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('a3c17598-3e41-11ed-921d-d13180f13639', 'css', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).getTagName();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('a3c2ccd6-3e41-11ed-921d-d13180f13639', 'name', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).getTagName();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('b4e371d1-d482-4aed-bc36-e93d450ac127', 'css', 'sendKeys', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).sendKeys([''%s'']);', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('cb1f10c5-231a-ca7f-6067-6de69b359206', 'xpath', 'clear', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).clear();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('d0f982a6-89a8-4962-9a91-dc0218774b3a', 'tag', 'click', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).click();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('e2ee2cf4-3e3a-11ed-921d-d13180f13639', 'partlink', 'getCssValue', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).getCssValue(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('e2f03aa8-3e3a-11ed-921d-d13180f13639', 'className', 'getCssValue', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).getCssValue(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('e2f116e4-3e3a-11ed-921d-d13180f13639', 'tag', 'getCssValue', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).getCssValue(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('e2f1ddc2-3e3a-11ed-921d-d13180f13639', 'id', 'getCssValue', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).getCssValue(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('e2f3de60-3e3a-11ed-921d-d13180f13639', 'link', 'getCssValue', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).getCssValue(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('e2f49eea-3e3a-11ed-921d-d13180f13639', 'xpath', 'getCssValue', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).getCssValue(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('e2f5cc34-3e3a-11ed-921d-d13180f13639', 'css', 'getCssValue', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).getCssValue(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('e2f6cbb6-3e3a-11ed-921d-d13180f13639', 'name', 'getCssValue', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).getCssValue(''%s'');', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('ed09a8e2-3e41-11ed-921d-d13180f13639', 'partlink', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.partialLinkText(''%s'')).isDisplayed();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('ed0bc67c-3e41-11ed-921d-d13180f13639', 'className', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.className(''%s'')).isDisplayed();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('ed0cb064-3e41-11ed-921d-d13180f13639', 'tag', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.tagName(''%s'')).isDisplayed();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('ed0da60e-3e41-11ed-921d-d13180f13639', 'id', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.id(''%s'')).isDisplayed();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('ed0f66e2-3e41-11ed-921d-d13180f13639', 'link', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).isDisplayed();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('ed1069b6-3e41-11ed-921d-d13180f13639', 'xpath', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.xpath(''%s'')).isDisplayed();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('ed113594-3e41-11ed-921d-d13180f13639', 'css', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).isDisplayed();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('ed11fd76-3e41-11ed-921d-d13180f13639', 'name', 'getTagName', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).isDisplayed();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('ed5c1745-9b7b-6532-2d21-0d8603435f42', 'css', 'clear', 'js', 'WDS.browser.findElement(pkg.By.cssSelector(''%s'')).clear();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('f6ec2544-666e-405e-9cbf-9f5ea0a99262', 'link', 'click', 'js', 'WDS.browser.findElement(pkg.By.linkText(''%s'')).click();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('fda8b7d8-7aa0-c101-3b54-fa25df9558b3', 'name', 'clear', 'js', 'WDS.browser.findElement(pkg.By.name(''%s'')).clear();', 1660647034996, 1660647034996);
INSERT INTO supretest.ui_script_mapping (id, location_type, operation, language_type, script, create_time, update_time) VALUES ('0bb27046-defe-4203-b046-29982889dec8', 'JavascriptExecutor', 'executeScript', 'js', 'WDS.browser.executeScript(''%s'');', 1660647034996, 1660647034996);

