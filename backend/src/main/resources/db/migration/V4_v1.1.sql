create table supretest.zendao_project
(
    id                      varchar(50)             not null primary key,
    product_id              varchar(50)             not null comment 'supretest project表的id',
    zendao_product_id       varchar(20)             not null comment '禅道产品id',
    zendao_project_id       varchar(50)             not null comment '禅道项目id',
    zendao_project_name      varchar(100)            null comment '禅道项目名',
    zendao_project_status    varchar(10)             null comment '项目状态',
    create_time             bigint(13)              not null comment 'Create timestamp',
    update_time             bigint(13)              not null comment 'Update timestamp'
);

alter table supretest.api_definition add column sub_project_id varchar(50) comment 'zendao_project的id字段';
alter table supretest.api_scenario add column sub_project_id varchar(50) comment 'zendao_project的id字段';
alter table supretest.api_scenario_report add column sub_project_id varchar(50) comment 'zendao_project的id字段';
alter table supretest.api_test_case add column sub_project_id varchar(50) comment 'zendao_project的id字段';


alter table supretest.project_test_plan_progress add column sub_project_id varchar(50) comment 'zendao_project的id字段';
alter table supretest.test_plan add column sub_project_id varchar(50) comment 'zendao_project的id字段';
alter table supretest.test_case_review add column sub_project_id varchar(50) comment 'zendao_project的id字段';
alter table supretest.test_case add column sub_project_id varchar(50) comment 'zendao_project的id字段';
alter table supretest.swagger_url_project add column sub_project_id varchar(50) comment 'zendao_project的id字段';
alter table supretest.api_definition_exec_result add column sub_project_id varchar(50) comment 'zendao_project的id字段';
update supretest.system_header set props = '[{"id":"name","label":"评审名称"},{"id":"reviewer","label":"评审人"},{"id":"projectName","label":"所属产品"},{"id":"subProjectName","label":"所属项目"},{"id":"creatorName","label":"发起人"},{"id":"status","label":"当前状态"},{"id":"createTime","label":"创建时间"},{"id":"endTime","label":"截止时间"},{"id":"tags","label":"标签"}]'  where `type`='test_case_review_list';
