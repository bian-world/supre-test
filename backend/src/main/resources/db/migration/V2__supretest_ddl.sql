create table IF NOT EXISTS  supretest.QRTZ_CALENDARS
(
    SCHED_NAME    varchar(120) not null,
    CALENDAR_NAME varchar(200) not null,
    CALENDAR      blob         not null,
    primary key (SCHED_NAME, CALENDAR_NAME)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.QRTZ_FIRED_TRIGGERS
(
    SCHED_NAME        varchar(120) not null,
    ENTRY_ID          varchar(95)  not null,
    TRIGGER_NAME      varchar(200) not null,
    TRIGGER_GROUP     varchar(200) not null,
    INSTANCE_NAME     varchar(200) not null,
    FIRED_TIME        bigint(13)   not null,
    SCHED_TIME        bigint(13)   not null,
    PRIORITY          int          not null,
    STATE             varchar(16)  not null,
    JOB_NAME          varchar(200) null,
    JOB_GROUP         varchar(200) null,
    IS_NONCONCURRENT  varchar(1)   null,
    REQUESTS_RECOVERY varchar(1)   null,
    primary key (SCHED_NAME, ENTRY_ID)
)
    charset = utf8mb4;

-- create index  IDX_QRTZ_FT_INST_JOB_REQ_RCVRY
--     on supretest.QRTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME, REQUESTS_RECOVERY);

-- create index IDX_QRTZ_FT_JG
--     on supretest.QRTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_GROUP);
--
-- create index IDX_QRTZ_FT_J_G
--     on supretest.QRTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
--
-- create index IDX_QRTZ_FT_TG
--     on supretest.QRTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_GROUP);
--
-- create index IDX_QRTZ_FT_TRIG_INST_NAME
--     on supretest.QRTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME);
--
-- create index IDX_QRTZ_FT_T_G
--     on supretest.QRTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

create table IF NOT EXISTS  supretest.QRTZ_JOB_DETAILS
(
    SCHED_NAME        varchar(120) not null,
    JOB_NAME          varchar(200) not null,
    JOB_GROUP         varchar(200) not null,
    DESCRIPTION       varchar(250) null,
    JOB_CLASS_NAME    varchar(250) not null,
    IS_DURABLE        varchar(1)   not null,
    IS_NONCONCURRENT  varchar(1)   not null,
    IS_UPDATE_DATA    varchar(1)   not null,
    REQUESTS_RECOVERY varchar(1)   not null,
    JOB_DATA          blob         null,
    primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
    charset = utf8mb4;
--
-- create index IDX_QRTZ_J_GRP
--     on supretest.QRTZ_JOB_DETAILS (SCHED_NAME, JOB_GROUP);
--
-- create index IDX_QRTZ_J_REQ_RECOVERY
--     on supretest.QRTZ_JOB_DETAILS (SCHED_NAME, REQUESTS_RECOVERY);

create table IF NOT EXISTS  supretest.QRTZ_LOCKS
(
    SCHED_NAME varchar(120) not null,
    LOCK_NAME  varchar(40)  not null,
    primary key (SCHED_NAME, LOCK_NAME)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.QRTZ_PAUSED_TRIGGER_GRPS
(
    SCHED_NAME    varchar(120) not null,
    TRIGGER_GROUP varchar(200) not null,
    primary key (SCHED_NAME, TRIGGER_GROUP)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.QRTZ_SCHEDULER_STATE
(
    SCHED_NAME        varchar(120) not null,
    INSTANCE_NAME     varchar(200) not null,
    LAST_CHECKIN_TIME bigint(13)   not null,
    CHECKIN_INTERVAL  bigint(13)   not null,
    primary key (SCHED_NAME, INSTANCE_NAME)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.api_data_view
(
    id            varchar(50)                           not null
        primary key,
    report_id     varchar(255)                          not null,
    api_name      varchar(200)                          null,
    url           varchar(255)                          null,
    response_code varchar(100)                          null,
    start_time    varchar(20)                           null,
    response_time varchar(20) default '0'               null,
    create_time   timestamp   default CURRENT_TIMESTAMP not null,
    update_time   timestamp   default CURRENT_TIMESTAMP not null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.api_definition
(
    id                varchar(50)          not null comment 'Test ID'
        primary key,
    project_id        varchar(50)          not null comment 'Project ID this test belongs to',
    name              varchar(255)         not null comment 'Test name',
    method            varchar(64)          not null comment 'method',
    protocol          varchar(255)         not null comment 'request protocol',
    path              varchar(1000)        null,
    module_path       varchar(1000)        null comment 'module path',
    description       longtext             null comment 'Test description',
    environment_id    varchar(50)          null comment 'environment id',
    request           longtext             null comment 'request (JSON format)',
    response          longtext             null comment 'request (JSON format)',
    schedule          varchar(255)         null comment 'Test schedule (cron list)',
    status            varchar(64)          null comment 'Status of this test',
    module_id         varchar(50)          null comment 'module_id of this module',
    user_id           varchar(64)          null comment 'User ID',
    create_time       bigint(13)           not null comment 'Create timestamp',
    update_time       bigint(13)           not null comment 'Update timestamp',
    num               int                  null comment 'api definition ID',
    tags              varchar(1000)        null,
    original_state    varchar(64)          null,
    create_user       varchar(100)         null,
    case_total        varchar(100)         null,
    case_status       varchar(100)         null,
    case_passing_rate varchar(100)         null,
    delete_time       bigint(13)           null comment 'Delete timestamp',
    delete_user_id    varchar(64)          null comment 'Delete user id',
    `order`           bigint               not null comment '自定义排序，间隔5000',
    remark            text                 null,
    version_id        varchar(50)          null,
    ref_id            varchar(50)          null,
    latest            tinyint(1) default 0 null comment '是否为最新版本 0:否，1:是'
)
    charset = utf8mb4;

-- create index api_definition_ref_id_index
--     on supretest.api_definition (ref_id);
--
-- create index api_definition_version_id_index
--     on supretest.api_definition (version_id);
--
-- create index methodIndex
--     on supretest.api_definition (method);
--
-- create index project_id
--     on supretest.api_definition (project_id);
--
-- create index protocolIndex
--     on supretest.api_definition (protocol);
--
-- create index user_id
--     on supretest.api_definition (user_id);

create table IF NOT EXISTS  supretest.api_definition_env
(
    id          varchar(50) not null comment 'ID'
        primary key,
    user_id     varchar(50) not null comment 'user id',
    env_id      varchar(50) null comment 'Api Environment id',
    create_time bigint(13)  null,
    update_time bigint(13)  null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.api_definition_exec_result
(
    id                   varchar(50)                            not null comment 'Test ID'
        primary key,
    name                 varchar(255)                           null,
    resource_id          varchar(50)                            not null comment 'api id or testcase id ',
    content              longtext                               null comment 'request (JSON format)',
    status               varchar(50)                            null comment 'execute status',
    user_id              varchar(64)                            null comment 'User ID',
    start_time           bigint(13)                             not null comment 'Create timestamp',
    end_time             bigint(13)                             not null comment 'Update timestamp',
    create_time          bigint(13)                             null comment 'Create time',
    type                 varchar(20)                            null,
    actuator             varchar(100)                           null,
    trigger_mode         varchar(50)                            null,
    error_code           varchar(255)                           null,
    version_id           varchar(50)                            null,
    project_id           varchar(50)                            null,
    integrated_report_id varchar(50)                            null,
    report_type          varchar(100) default 'API_INDEPENDENT' not null comment '报告类型'
)
    charset = utf8mb4;

-- create index api_definition_exec_result_integrated_report_id_IDX
--     on supretest.api_definition_exec_result (integrated_report_id);
--
-- create index api_definition_exec_result_version_id_index
--     on supretest.api_definition_exec_result (version_id);
--
-- create index project_id_index
--     on supretest.api_definition_exec_result (project_id);
--
-- create index resource_id
--     on supretest.api_definition_exec_result (resource_id);

create table IF NOT EXISTS  supretest.api_definition_follow
(
    definition_id varchar(50) null,
    follow_id     varchar(50) null,
    constraint api_definition_follow_scenario_id_follow_id_pk
        unique (definition_id, follow_id)
)
    charset = utf8mb4;
--
-- create index api_definition_follow_id_index
--     on supretest.api_definition_follow (follow_id);

create table IF NOT EXISTS  supretest.api_execution_queue
(
    id          varchar(50)  not null comment 'ID'
        primary key,
    report_id   varchar(100) null comment '集合报告/测试计划报告',
    report_type varchar(100) null comment '报告类型/计划报告/单独报告',
    run_mode    varchar(100) null comment '执行模式/scenario/api/test_paln_api/test_pan_scenario',
    pool_id     varchar(100) null comment '执行资源池',
    create_time bigint(13)   null comment '创建时间',
    failure     tinyint(1)   null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.api_execution_queue_detail
(
    id          varchar(50)  not null comment 'ID'
        primary key,
    queue_id    varchar(100) null comment '队列id',
    sort        int          null comment '排序',
    report_id   varchar(100) null comment '报告id',
    test_id     varchar(100) null comment '资源id',
    evn_map     longtext     null comment '环境',
    type        varchar(100) null comment '资源类型',
    create_time bigint(13)   null comment '创建时间'
)
    charset = utf8mb4;

-- create index queue_id_test_id_index
--     on supretest.api_execution_queue_detail (queue_id, test_id);

create table IF NOT EXISTS  supretest.api_load_test
(
    id           varchar(50)       not null comment 'ID'
        primary key,
    api_id       varchar(255)      not null comment 'Relate resource id',
    load_test_id varchar(50)       not null comment 'Load Test id',
    env_id       varchar(50)       null comment 'Api case env id',
    type         varchar(20)       not null comment 'Api Type',
    api_version  int(10) default 0 null comment 'Relate Scenario Version'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.api_module
(
    id          varchar(50)       not null comment 'Test case node ID'
        primary key,
    project_id  varchar(50)       not null comment 'Project ID this node belongs to',
    name        varchar(64)       not null comment 'Node name',
    protocol    varchar(64)       not null comment 'Node protocol',
    parent_id   varchar(50)       null comment 'Parent node ID',
    level       int(10) default 1 null comment 'Node level',
    pos         double            null comment 'Node order',
    create_time bigint(13)        not null comment 'Create timestamp',
    update_time bigint(13)        not null comment 'Update timestamp',
    create_user varchar(100)      null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.api_scenario
(
    id                     varchar(255)         not null
        primary key,
    project_id             varchar(50)          not null comment 'Project ID this test belongs to',
    tags                   varchar(2000)        null comment 'tag list',
    user_id                varchar(64)          null comment 'User ID',
    api_scenario_module_id varchar(64)          null comment 'User ID',
    module_path            varchar(1000)        null,
    name                   varchar(255)         not null comment 'api scenario name',
    level                  varchar(100)         null comment 'api scenario level ',
    status                 varchar(100)         not null comment 'api scenario status ',
    principal              varchar(100)         not null comment 'api scenario principal ',
    step_total             int        default 0 null comment 'Step total ',
    schedule               varchar(255)         null comment 'Test schedule (cron list)',
    scenario_definition    longtext             null comment 'Test scenario_definition json',
    description            longtext             null comment 'api scenario description',
    create_time            bigint(13)           not null comment 'Create timestamp',
    update_time            bigint(13)           not null comment 'Update timestamp',
    pass_rate              varchar(100)         null,
    last_result            varchar(100)         null,
    report_id              varchar(50)          null,
    num                    int                  null comment 'api scenario ID',
    original_state         varchar(64)          null,
    custom_num             varchar(64)          null comment 'custom num',
    create_user            varchar(100)         null,
    version                int(10)    default 0 null comment '版本号',
    delete_time            bigint(13)           null comment 'Delete timestamp',
    delete_user_id         varchar(64)          null comment 'Delete user id',
    execute_times          int                  null,
    `order`                bigint               not null comment '自定义排序，间隔5000',
    environment_type       varchar(20)          null,
    environment_json       longtext             null,
    environment_group_id   varchar(50)          null,
    version_id             varchar(50)          null,
    ref_id                 varchar(255)         null,
    latest                 tinyint(1) default 0 null comment '是否为最新版本 0:否，1:是',
    use_url                varchar(255)         null
)
    charset = utf8mb4;
--
-- create index api_scenario_ref_id_index
--     on supretest.api_scenario (ref_id);
--
-- create index api_scenario_version_id_index
--     on supretest.api_scenario (version_id);
--
-- create index index_project_id
--     on supretest.api_scenario (project_id);

create table IF NOT EXISTS  supretest.api_scenario_follow
(
    scenario_id varchar(50) null,
    follow_id   varchar(50) null,
    constraint api_scenario_follow_scenario_id_follow_id_pk
        unique (scenario_id, follow_id)
)
    charset = utf8mb4;

-- create index api_scenario_follow_id_index
--     on supretest.api_scenario_follow (follow_id);

create table IF NOT EXISTS  supretest.api_scenario_module
(
    id          varchar(50)       not null comment 'Test case node ID'
        primary key,
    project_id  varchar(50)       not null comment 'Project ID this node belongs to',
    name        varchar(64)       not null comment 'Node name',
    parent_id   varchar(50)       null comment 'Parent node ID',
    level       int(10) default 1 null comment 'Node level',
    pos         double            null comment 'Node order',
    create_time bigint(13)        not null comment 'Create timestamp',
    update_time bigint(13)        not null comment 'Update timestamp',
    create_user varchar(100)      null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.api_scenario_reference_id
(
    id              varchar(50)  not null
        primary key,
    api_scenario_id varchar(255) null,
    create_time     bigint(13)   null,
    create_user_id  varchar(64)  null,
    reference_id    varchar(255) null,
    reference_type  varchar(255) null,
    data_type       varchar(255) null,
    url             varchar(500) null,
    method          varchar(20)  null
)
    charset = utf8mb4;
--
-- create index api_scenario_id
--     on supretest.api_scenario_reference_id (api_scenario_id);
--
-- create index data_type
--     on supretest.api_scenario_reference_id (data_type);
--
-- create index index_method
--     on supretest.api_scenario_reference_id (method);
--
-- create index index_url
--     on supretest.api_scenario_reference_id (url);
--
-- create index reference_id
--     on supretest.api_scenario_reference_id (reference_id);

create table IF NOT EXISTS  supretest.api_scenario_report
(
    id             varchar(50)   not null comment 'Test report ID'
        primary key,
    project_id     varchar(50)   not null comment 'scenario ID this test report belongs to',
    name           varchar(3000) null,
    description    longtext      null comment 'Test report name',
    create_time    bigint(13)    not null comment 'Create timestamp',
    update_time    bigint(13)    not null comment 'Update timestamp',
    status         varchar(64)   not null comment 'Status of this test run',
    user_id        varchar(64)   null,
    trigger_mode   varchar(64)   null,
    execute_type   varchar(200)  null,
    scenario_name  varchar(3000) null,
    scenario_id    varchar(3000) null,
    create_user    varchar(100)  null,
    actuator       varchar(100)  null,
    end_time       bigint(13)    null,
    report_version int           null,
    version_id     varchar(50)   null,
    report_type    varchar(100)  null
)
    charset = utf8mb4;
--
-- create index api_scenario_report_version_id_index
--     on supretest.api_scenario_report (version_id);
--
-- create index projectIdIndex
--     on supretest.api_scenario_report (project_id);
--
-- create index projectIdexectypeIndex
--     on supretest.api_scenario_report (project_id, execute_type);
--
-- create index update_time_index
--     on supretest.api_scenario_report (update_time);

create table IF NOT EXISTS  supretest.api_scenario_report_detail
(
    report_id  varchar(64) not null comment 'API Test Report ID'
        primary key,
    project_id varchar(64) not null comment 'scenario ID',
    content    longblob    null comment 'Report Content'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.api_scenario_report_result
(
    id               varchar(50)  not null comment 'ID'
        primary key,
    resource_id      varchar(200) null comment '请求资源 id',
    report_id        varchar(50)  null comment '报告 id',
    create_time      bigint(13)   null comment '创建时间',
    status           varchar(100) null comment '结果状态',
    request_time     bigint(13)   null comment '请求时间',
    total_assertions bigint(13)   null comment '总断言数',
    pass_assertions  bigint(13)   null comment '失败断言数',
    content          longblob     null comment '执行结果',
    error_code       varchar(255) null,
    base_info        longtext     null
)
    charset = utf8mb4;

-- create index api_scenario_report_result_report_id_IDX
--     on supretest.api_scenario_report_result (report_id);
--
-- create index index_resource_id
--     on supretest.api_scenario_report_result (resource_id);
--
-- create index report_id_index
--     on supretest.api_scenario_report_result (report_id);

create table IF NOT EXISTS  supretest.api_scenario_report_structure
(
    id            varchar(50) not null comment 'ID'
        primary key,
    report_id     varchar(50) null comment '请求资源 id',
    create_time   bigint(13)  null comment '创建时间',
    resource_tree longblob    null comment '资源步骤结构树',
    console       longtext    null comment '执行日志'
)
    charset = utf8mb4;
--
-- create index index_report_id
--     on supretest.api_scenario_report_structure (report_id);

create table IF NOT EXISTS  supretest.api_test
(
    id                  varchar(50)  not null comment 'Test ID'
        primary key,
    project_id          varchar(50)  not null comment 'Project ID this test belongs to',
    name                varchar(64)  not null comment 'Test name',
    description         varchar(255) null comment 'Test description',
    scenario_definition longtext     null comment 'Scenario definition (JSON format)',
    status              varchar(64)  null comment 'Status of this test',
    user_id             varchar(64)  null comment 'User ID',
    create_time         bigint(13)   not null comment 'Create timestamp',
    update_time         bigint(13)   not null comment 'Update timestamp'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.api_test_case
(
    id                varchar(50)       not null comment 'Test ID'
        primary key,
    project_id        varchar(50)       not null comment 'Project ID this test belongs to',
    name              varchar(255)      not null comment 'Test case name',
    priority          varchar(64)       not null comment 'priority',
    api_definition_id varchar(50)       not null comment 'api definition id',
    description       longtext          null comment 'Test description',
    request           longtext          null comment 'request (JSON format)',
    create_user_id    varchar(64)       null comment 'User ID',
    update_user_id    varchar(64)       null comment 'User ID',
    create_time       bigint(13)        not null comment 'Create timestamp',
    update_time       bigint(13)        not null comment 'Update timestamp',
    num               int               null comment 'api test case ID',
    tags              varchar(1000)     null,
    last_result_id    varchar(64)       null comment 'Last ApiDefinitionExecResult ID',
    status            varchar(50)       null,
    original_status   varchar(50)       null,
    delete_time       bigint(13)        null comment 'Delete timestamp',
    delete_user_id    varchar(64)       null comment 'Delete user id',
    version           int(10) default 0 null comment '版本号',
    `order`           bigint            not null comment '自定义排序，间隔5000',
    case_status       varchar(100)      null comment '用例状态等同场景的status',
    version_id        varchar(50)       null
)
    charset = utf8mb4;
--
-- create index api_definition_id
--     on supretest.api_test_case (api_definition_id);
--
-- create index api_test_case_version_id_index
--     on supretest.api_test_case (version_id);
--
-- create index create_user_id
--     on supretest.api_test_case (create_user_id);
--
-- create index update_user_id
--     on supretest.api_test_case (update_user_id);

create table IF NOT EXISTS  supretest.api_test_case_follow
(
    case_id   varchar(50) null,
    follow_id varchar(50) null,
    constraint api_case_follow_scenario_id_follow_id_pk
        unique (case_id, follow_id)
)
    charset = utf8mb4;

-- create index api_case_follow_id_index
--     on supretest.api_test_case_follow (follow_id);

create table IF NOT EXISTS  supretest.api_test_environment
(
    id          varchar(50)  not null comment 'Api Test Environment ID'
        primary key,
    name        varchar(64)  not null comment 'Api Test Environment Name',
    project_id  varchar(50)  not null comment 'Project ID',
    protocol    varchar(20)  null comment 'Api Test Protocol',
    socket      varchar(225) null comment 'Api Test Socket',
    domain      varchar(225) null comment 'Api Test Domain',
    port        int(10)      null comment 'Api Test Port',
    variables   text         null comment 'Global ariables',
    headers     text         null comment 'Global Heards',
    config      longtext     null comment 'Config Data (JSON format)',
    hosts       longtext     null comment 'hosts ',
    create_user varchar(100) null,
    create_time bigint(13)   null,
    update_time bigint(13)   null
)
    charset = utf8mb4;
--
-- create index project_id
--     on supretest.api_test_environment (project_id);

create table IF NOT EXISTS  supretest.api_test_file
(
    test_id varchar(64) null,
    file_id varchar(64) null,
    constraint api_test_file_unique_key
        unique (test_id, file_id)
)
    comment 'Api test test file relevance table' charset = utf8mb4;

create table IF NOT EXISTS  supretest.api_test_report
(
    id           varchar(50)  not null comment 'Test report ID'
        primary key,
    test_id      varchar(50)  not null comment 'Test ID this test report belongs to',
    name         varchar(64)  not null comment 'Test report name',
    description  varchar(255) null comment 'Test report name',
    create_time  bigint(13)   not null comment 'Create timestamp',
    update_time  bigint(13)   not null comment 'Update timestamp',
    status       varchar(64)  not null comment 'Status of this test run',
    user_id      varchar(64)  null comment 'User ID',
    trigger_mode varchar(64)  null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.api_test_report_detail
(
    report_id varchar(64) not null comment 'API Test Report ID'
        primary key,
    test_id   varchar(64) not null comment 'Test ID',
    content   longblob    null comment 'Report content'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.auth_source
(
    id            varchar(50)  not null
        primary key,
    configuration text         not null,
    status        varchar(64)  not null,
    create_time   bigint(13)   not null,
    update_time   bigint(13)   not null,
    description   varchar(255) null,
    name          varchar(60)  null,
    type          varchar(30)  null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.custom_field
(
    id          varchar(100)         not null
        primary key,
    name        varchar(64)          not null comment 'Custom field name',
    scene       varchar(30)          not null comment 'Custom field use scene',
    type        varchar(30)          not null comment 'Custom field type',
    remark      varchar(255)         null comment 'Custom field remark',
    options     text                 null comment 'Test resource pool status',
    `system`    tinyint(1) default 0 null comment 'Is system custom field',
    global      tinyint(1) default 0 null comment 'Is global custom field',
    create_time bigint(13)           not null comment 'Create timestamp',
    update_time bigint(13)           not null comment 'Update timestamp',
    create_user varchar(100)         null,
    project_id  varchar(64)          null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.custom_field_template
(
    id            varchar(50)  not null comment 'Custom field field template related id'
        primary key,
    field_id      varchar(50)  not null comment 'Custom field ID',
    template_id   varchar(50)  not null comment 'Field template ID',
    scene         varchar(30)  not null comment 'Use scene',
    required      tinyint(1)   null comment 'Is required',
    `order`       int          null comment 'Item order',
    default_value varchar(100) null,
    custom_data   varchar(255) null comment 'Custom data',
    `key`         varchar(1)   null comment 'Save Table Header Key'
)
    charset = utf8mb4;

-- create index custom_field_template_field_id_index
--     on supretest.custom_field_template (field_id);
--
-- create index custom_field_template_template_id_index
--     on supretest.custom_field_template (template_id);

create table IF NOT EXISTS  supretest.custom_function
(
    id          varchar(50)   not null
        primary key,
    name        varchar(255)  null comment '函数名',
    tags        varchar(1000) null comment '标签',
    description varchar(1000) null comment '函数描述',
    type        varchar(255)  null comment '脚本语言类型',
    params      longtext      null comment '参数列表',
    script      longtext      null comment '函数体',
    result      longtext      null comment '执行结果',
    create_user varchar(100)  null comment '创建人',
    create_time bigint(13)    null comment '创建时间',
    update_time bigint(13)    null comment '更新时间',
    project_id  varchar(50)   null comment '所属项目ID'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.enterprise_test_report
(
    id             varchar(50)  not null comment 'Test ID'
        primary key,
    project_id     varchar(50)  not null comment 'Project ID this report belongs to',
    create_time    bigint(13)   not null comment 'Create timestamp',
    update_time    bigint(13)   not null comment 'Update timestamp',
    create_user    varchar(64)  null comment 'User ID',
    update_user    varchar(64)  null comment 'User ID',
    name           varchar(255) not null comment 'report name',
    status         varchar(64)  null comment 'Status of email',
    send_freq      varchar(64)  null comment 'send freq',
    send_cron      varchar(64)  null comment 'send cron',
    last_send_time bigint(13)   null comment 'last send time',
    report_content longtext     null comment 'report content',
    addressee      longtext     null comment 'report content',
    duplicated     longtext     null comment 'report content'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.enterprise_test_report_send_record
(
    id                        varchar(50) not null comment 'Test ID'
        primary key,
    enterprise_test_report_id varchar(50) not null comment 'Enterprise report ID this record belongs to',
    create_time               bigint(13)  not null comment 'Create timestamp',
    create_user               varchar(64) null comment 'User ID',
    status                    varchar(64) null comment 'Status of email',
    report_content            longtext    null comment 'report content',
    addressee                 longtext    null comment 'report content',
    duplicated                longtext    null comment 'report content'
)
    charset = utf8mb4;

-- create index enterprise_test_report_id
--     on supretest.enterprise_test_report_send_record (enterprise_test_report_id);

create table IF NOT EXISTS  supretest.environment_group
(
    id           varchar(50)  not null comment '环境组id'
        primary key,
    name         varchar(50)  not null comment '环境组名',
    workspace_id varchar(64)  not null comment '所属工作空间',
    description  varchar(255) null comment '环境组描述',
    create_user  varchar(50)  null comment '创建人',
    create_time  bigint(13)   null comment '创建时间',
    update_time  bigint(13)   null comment '更新时间'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.environment_group_project
(
    id                   varchar(50) not null
        primary key,
    environment_group_id varchar(50) null comment '环境组id',
    environment_id       varchar(50) null comment 'api test environment 环境ID',
    project_id           varchar(50) null comment '项目id'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.error_report_library
(
    id          varchar(50)  not null comment 'Test ID'
        primary key,
    project_id  varchar(50)  not null comment 'Project ID this report belongs to',
    create_time bigint(13)   not null comment 'Create timestamp',
    update_time bigint(13)   not null comment 'Update timestamp',
    create_user varchar(64)  null comment 'User ID',
    update_user varchar(64)  null comment 'User ID',
    error_code  varchar(255) not null comment 'error code',
    match_type  varchar(255) not null comment 'match type',
    status      tinyint(1)   null comment 'status',
    content     longtext     null comment 'content',
    description longtext     null comment 'report content'
)
    charset = utf8mb4;

-- create index project_id
--     on supretest.error_report_library (project_id);
--
-- create index project_id_status
--     on supretest.error_report_library (project_id, status);

create table IF NOT EXISTS  supretest.esb_api_params
(
    id                   varchar(50) not null
        primary key,
    resource_id          varchar(50) null,
    data_struct          longtext    null,
    fronted_script       longtext    null,
    response_data_struct longtext    null,
    backed_script        longtext    null,
    constraint resource_id
        unique (resource_id)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.file_content
(
    file_id varchar(64) not null comment 'File ID'
        primary key,
    file    longblob    null comment 'File content'
)
    charset = utf8mb4;

-- create index file_id_index
--     on supretest.file_content (file_id);

create table IF NOT EXISTS  supretest.file_metadata
(
    id          varchar(64)  not null comment 'File ID'
        primary key,
    name        varchar(250) not null comment 'File name',
    type        varchar(64)  null comment 'File type',
    size        bigint(13)   not null comment 'File size',
    create_time bigint(13)   not null comment 'Create timestamp',
    update_time bigint(13)   not null comment 'Update timestamp',
    project_id  varchar(50)  null
)
    charset = utf8mb4;
--
-- create index file_name
--     on supretest.file_metadata (name);

create table IF NOT EXISTS  supretest.`group`
(
    id          varchar(64)  not null
        primary key,
    name        varchar(64)  not null,
    description varchar(100) null,
    `system`    tinyint(1)   not null comment '是否是系统用户组',
    type        varchar(20)  not null comment '所属类型',
    create_time bigint(13)   not null,
    update_time bigint(13)   not null,
    creator     varchar(64)  not null comment '创建人(操作人）',
    scope_id    varchar(64)  not null comment '应用范围'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.issue_comment
(
    id          varchar(64) not null
        primary key,
    issue_id    varchar(64) null,
    description text        null,
    author      varchar(50) null,
    create_time bigint(13)  null,
    update_time bigint(13)  null,
    status      varchar(80) null
)
    charset = utf8;

create table IF NOT EXISTS  supretest.issue_follow
(
    issue_id  varchar(50) null,
    follow_id varchar(50) null,
    constraint issue_follow_pk
        unique (issue_id, follow_id)
)
    charset = utf8mb4;

-- create index issue_follow_follow_id_index
--     on supretest.issue_follow (follow_id);
--
-- create index issue_follow_issue_id_index
--     on supretest.issue_follow (issue_id);

create table IF NOT EXISTS  supretest.issue_template
(
    id          varchar(100)         not null
        primary key,
    name        varchar(64)          not null comment 'Field template name',
    platform    varchar(30)          not null comment 'Field template type',
    description varchar(255)         null comment 'Field template description',
    title       varchar(64)          null comment 'Issue title',
    `system`    tinyint(1) default 0 null comment 'Is system field template ',
    global      tinyint(1) default 0 null comment 'Is global template',
    content     text                 null comment 'Issue content',
    create_time bigint(13)           not null comment 'Create timestamp',
    update_time bigint(13)           not null comment 'Update timestamp',
    create_user varchar(100)         null,
    project_id  varchar(64)          null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.issues
(
    id              varchar(50)  not null
        primary key,
    title           varchar(300) null,
    description     longtext     null,
    status          varchar(50)  null,
    create_time     bigint(13)   null,
    update_time     bigint(13)   null,
    reporter        varchar(50)  null comment 'case issues creator',
    lastmodify      varchar(50)  null,
    platform        varchar(50)  not null,
    custom_fields   text         null comment 'CustomField',
    project_id      varchar(50)  null,
    creator         varchar(50)  null comment 'Creator',
    resource_id     varchar(50)  null comment 'Issue resource id',
    num             int          null,
    platform_status varchar(50)  null comment '第三方平台状态',
    platform_id     varchar(50)  not null
)
    charset = utf8mb4;
--
-- create index issues_platform_id_IDX
--     on supretest.issues (platform_id);

create table IF NOT EXISTS  supretest.jar_config
(
    id            varchar(50)  not null comment 'ID'
        primary key,
    name          varchar(64)  not null comment 'Name',
    file_name     varchar(64)  not null comment 'File name',
    creator       varchar(50)  not null comment 'creator User ID',
    modifier      varchar(50)  not null comment 'Modifier User ID',
    path          varchar(255) not null comment 'File path',
    enable        tinyint(1)   null comment 'Config enable',
    description   varchar(255) null comment 'description',
    create_time   bigint(13)   not null comment 'Create timestamp',
    update_time   bigint(13)   not null comment 'Update timestamp',
    resource_id   varchar(50)  not null comment '资源所属的工作空间或者项目Id',
    resource_type varchar(20)  not null comment '资源的所属范围 WORKSPACE，PROJECT'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.license
(
    id           varchar(50) not null comment 'ID'
        primary key,
    create_time  bigint(13)  not null comment 'Create timestamp',
    update_time  bigint(13)  not null comment 'Update timestamp',
    license_code longtext    null comment 'license_code'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.load_test
(
    id                     varchar(50)          not null comment 'Test ID'
        primary key,
    project_id             varchar(50)          not null comment 'Project ID this test belongs to',
    name                   varchar(255)         not null comment 'Test name',
    description            varchar(255)         null comment 'Test description',
    load_configuration     longtext             null comment 'Load configuration (JSON format)',
    advanced_configuration longtext             null comment 'Load configuration (JSON format)',
    create_time            bigint(13)           not null comment 'Create timestamp',
    update_time            bigint(13)           not null comment 'Update timestamp',
    status                 varchar(64)          null comment 'Test Status Running, Completed, Error, etc.',
    test_resource_pool_id  varchar(50)          null,
    user_id                varchar(64)          null,
    num                    int                  null,
    create_user            varchar(100)         null,
    scenario_version       int(10)    default 0 null comment '关联的接口自动化版本号',
    scenario_id            varchar(255)         null comment '关联的场景自动化ID',
    `order`                bigint               not null comment '自定义排序，间隔5000',
    version_id             varchar(50)          null,
    ref_id                 varchar(50)          null,
    latest                 tinyint(1) default 0 null comment '是否为最新版本 0:否，1:是'
)
    charset = utf8mb4;
--
-- create index load_test_ref_id_index
--     on supretest.load_test (ref_id);
--
-- create index load_test_version_id_index
--     on supretest.load_test (version_id);

create table IF NOT EXISTS  supretest.load_test_file
(
    test_id varchar(64)   null,
    file_id varchar(64)   null,
    sort    int default 0 null,
    constraint load_test_file_unique_key
        unique (test_id, file_id)
)
    comment '测试和文件的关联表' charset = utf8mb4;

create table IF NOT EXISTS  supretest.load_test_follow
(
    test_id   varchar(50) null,
    follow_id varchar(50) null,
    constraint load_test_follow_test_id_follow_id_pk
        unique (test_id, follow_id)
)
    charset = utf8mb4;

-- create index load_test_follow_follow_id_index
--     on supretest.load_test_follow (follow_id);

create table IF NOT EXISTS  supretest.load_test_report
(
    id                     varchar(50) not null comment 'Test report ID'
        primary key,
    test_id                varchar(50) not null comment 'Test ID this test report belongs to',
    name                   varchar(64) not null comment 'Test report name',
    description            text        null comment 'Test report message',
    create_time            bigint(13)  not null comment 'Create timestamp',
    update_time            bigint(13)  not null comment 'Update timestamp',
    status                 varchar(64) not null comment 'Status of this test run',
    user_id                varchar(64) null,
    trigger_mode           varchar(64) null,
    load_configuration     longtext    null,
    file_id                varchar(50) null,
    max_users              varchar(10) null,
    avg_response_time      varchar(10) null,
    tps                    varchar(10) null,
    project_id             varchar(50) null,
    test_name              varchar(64) null,
    jmx_content            longtext    null,
    advanced_configuration longtext    null,
    test_resource_pool_id  varchar(50) null,
    test_start_time        bigint(13)  null,
    test_end_time          bigint(13)  null,
    test_duration          bigint(13)  null,
    version_id             varchar(50) null
)
    charset = utf8mb4;

-- create index load_test_report_test_resource_pool_id_index
--     on supretest.load_test_report (test_resource_pool_id);
--
-- create index load_test_report_version_id_index
--     on supretest.load_test_report (version_id);

create table IF NOT EXISTS  supretest.load_test_report_detail
(
    part      bigint(11) auto_increment,
    report_id varchar(50) not null,
    content   longtext    null,
    primary key (part, report_id)
)
    charset = utf8mb4;

-- create index load_test_report_detail_report_id_index
--     on supretest.load_test_report_detail (report_id);

create table IF NOT EXISTS  supretest.load_test_report_log
(
    id          varchar(50) not null
        primary key,
    report_id   varchar(50) not null,
    resource_id varchar(50) null,
    content     longtext    null,
    part        bigint      null
)
    charset = utf8mb4;

-- create index load_test_report_log_report_id_resource_name_index
--     on supretest.load_test_report_log (report_id, resource_id, part);

create table IF NOT EXISTS  supretest.load_test_report_result
(
    id           varchar(50) not null
        primary key,
    report_id    varchar(50) not null,
    report_key   varchar(64) null,
    report_value longtext    null
)
    charset = utf8mb4;

-- create index load_test_report_result_report_id_report_key_index
--     on supretest.load_test_report_result (report_id, report_key);

create table IF NOT EXISTS  supretest.load_test_report_result_part
(
    report_id      varchar(50) not null,
    report_key     varchar(64) not null,
    resource_index int         not null,
    report_value   longtext    null,
    primary key (report_id, report_key, resource_index)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.load_test_report_result_realtime
(
    report_id      varchar(50) not null,
    report_key     varchar(64) not null,
    resource_index int         not null,
    sort           int         not null,
    report_value   text        null,
    primary key (report_id, report_key, resource_index, sort)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.message_task
(
    id             varchar(255)         not null
        primary key,
    type           varchar(50)          not null comment '消息类型',
    event          varchar(255)         not null comment '通知事件类型',
    user_id        varchar(50)          not null comment '接收人id',
    task_type      varchar(64)          not null,
    webhook        varchar(255)         null comment 'webhook地址',
    identification varchar(50)          not null,
    is_set         tinyint(1)           not null,
    test_id        varchar(255)         null,
    create_time    bigint(13) default 0 null,
    template       text                 null,
    project_id     varchar(64)          null
)
    charset = utf8mb4;
--
-- create table IF NOT EXISTS  supretest.supretest_version
-- (
--     installed_rank int                                 not null
--         primary key,
--     version        varchar(50)                         null,
--     description    varchar(200)                        not null,
--     type           varchar(20)                         not null,
--     script         varchar(1000)                       not null,
--     checksum       int                                 null,
--     installed_by   varchar(100)                        not null,
--     installed_on   timestamp default CURRENT_TIMESTAMP not null,
--     execution_time int                                 not null,
--     success        tinyint(1)                          not null
-- );



create table IF NOT EXISTS  supretest.minder_extra_node
(
    id        varchar(50) not null
        primary key,
    parent_id varchar(50) not null comment '父节点的id',
    group_id  varchar(50) not null comment '所属的项目',
    type      varchar(30) not null comment '类型，如：用例编辑脑图',
    node_data longtext    null comment '存储脑图节点额外信息'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.mock_config
(
    id             varchar(50)   not null
        primary key,
    project_id     varchar(50)   not null,
    api_id         varchar(50)   not null,
    api_path       varchar(1000) null,
    api_method     varchar(64)   null,
    create_time    bigint(13)    null,
    update_time    bigint(13)    null,
    create_user_id varchar(64)   null
)
    charset = utf8mb4;

-- create index api_id
--     on supretest.mock_config (api_id);
--
-- create index project_id
--     on supretest.mock_config (project_id);

create table IF NOT EXISTS  supretest.mock_expect_config
(
    id             varchar(50)   not null
        primary key,
    mock_config_id varchar(50)   null,
    name           varchar(255)  null,
    tags           varchar(1000) null,
    request        longtext      null,
    response       longtext      null,
    status         varchar(10)   null,
    create_time    bigint(13)    null,
    update_time    bigint(13)    null,
    create_user_id varchar(64)   null,
    expect_num     varchar(50)   null
)
    charset = utf8mb4;

-- create index mock_config_id
--     on supretest.mock_expect_config (mock_config_id);

create table IF NOT EXISTS  supretest.notification
(
    id            bigint auto_increment comment 'ID'
        primary key,
    type          varchar(30)  null comment '通知类型',
    receiver      varchar(50)  null comment '接收人',
    title         varchar(100) null comment '标题',
    content       longtext     null comment '内容',
    status        varchar(30)  null comment '状态',
    create_time   bigint(13)   null comment '更新时间',
    operator      varchar(50)  null comment '操作人',
    operation     varchar(50)  null,
    resource_id   varchar(50)  null comment '资源ID',
    resource_type varchar(50)  null,
    resource_name varchar(100) null
)
    charset = utf8mb4;

-- create index IDX_RECEIVER
--     on supretest.notification (receiver);
--
-- create index IDX_RECEIVER_TYPE
--     on supretest.notification (receiver, type);

create table IF NOT EXISTS  supretest.operating_log
(
    id           varchar(50)   not null comment 'ID'
        primary key,
    project_id   varchar(50)   not null comment 'Project ID',
    oper_method  varchar(500)  null comment 'operating method',
    create_user  varchar(100)  null comment 'source create u',
    oper_user    varchar(50)   null comment 'operating user id',
    source_id    varchar(6000) null comment 'operating source id',
    oper_type    varchar(100)  null comment 'operating type',
    oper_module  varchar(100)  null comment 'operating module',
    oper_title   varchar(6000) null comment 'operating title',
    oper_path    varchar(500)  null comment 'operating path',
    oper_content longtext      null comment 'operating content',
    oper_params  longtext      null comment 'operating params',
    oper_time    bigint(13)    not null comment 'Update timestamp'
)
    charset = utf8mb4;

-- create index oper_module_index
--     on supretest.operating_log (oper_module);
--
-- create index oper_time_index
--     on supretest.operating_log (oper_time);

create table IF NOT EXISTS  supretest.operating_log_resource
(
    id               varchar(50) not null comment 'ID'
        primary key,
    operating_log_id varchar(50) not null comment 'Operating log ID',
    source_id        varchar(50) not null comment 'operating source id'
)
    charset = utf8mb4;

-- create index operating_log_id_index
--     on supretest.operating_log_resource (operating_log_id);
--
-- create index source_id_index
--     on supretest.operating_log_resource (source_id);

create table IF NOT EXISTS  supretest.organization
(
    id          varchar(50)  not null comment 'Organization ID'
        primary key,
    name        varchar(64)  not null comment 'Organization name',
    description varchar(255) null comment 'Organization description',
    create_time bigint(13)   not null comment 'Create timestamp',
    update_time bigint(13)   not null comment 'Update timestamp',
    create_user varchar(100) null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.plugin
(
    id             varchar(50)  not null comment 'ID'
        primary key,
    name           varchar(300) null comment 'plugin name',
    plugin_id      varchar(300) not null comment 'Plugin id',
    script_id      varchar(300) not null comment 'Ui script id',
    clazz_name     varchar(500) not null comment 'Plugin clazzName',
    jmeter_clazz   varchar(300) not null comment 'Jmeter base clazzName',
    source_path    varchar(300) not null comment 'Plugin jar path',
    source_name    varchar(300) not null comment 'Plugin jar name',
    form_option    longtext     null comment 'plugin form option',
    form_script    longtext     null comment 'plugin form script',
    exec_entry     varchar(300) null comment 'plugin init entry class',
    create_time    bigint(13)   null,
    update_time    bigint(13)   null,
    create_user_id varchar(64)  null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.project
(
    id                  varchar(50)                 not null comment 'Project ID'
        primary key,
    workspace_id        varchar(50)                 not null comment 'Workspace ID this project belongs to',
    name                varchar(64)                 not null comment 'Project name',
    description         varchar(255)                null comment 'Project description',
    create_time         bigint(13)                  not null comment 'Create timestamp',
    update_time         bigint(13)                  not null comment 'Update timestamp',
    tapd_id             varchar(50)                 null,
    jira_key            varchar(50)                 null,
    zentao_id           varchar(50)                 null,
    azure_devops_id     varchar(50)                 null,
    case_template_id    varchar(50)                 null comment 'Relate test case template id',
    issue_template_id   varchar(50)                 null comment 'Relate test issue template id',
    create_user         varchar(100)                null,
    system_id           varchar(50)                 null,
    azure_filter_id     varchar(50)                 null comment 'azure 过滤需求的 parent workItem ID',
    platform            varchar(20) default 'Local' not null comment '项目使用哪个平台的模板',
    third_part_template tinyint(1)  default 0       null comment '是否使用第三方平台缺陷模板',
    version_enable      tinyint(1)  default 1       null,
    issue_config        text                        null
)
    charset = utf8mb4;

-- create index project_workspace_id_index
--     on supretest.project (workspace_id);

create table IF NOT EXISTS  supretest.project_application
(
    project_id varchar(50)  null,
    type       varchar(50)  null,
    type_value varchar(255) null,
    constraint project_application_pk
        unique (project_id, type)
)
    charset = utf8mb4;

-- create index project_application_project_id_index
--     on supretest.project_application (project_id);
--
-- create index project_application_type
--     on supretest.project_application (type);

create table IF NOT EXISTS  supretest.project_version
(
    id           varchar(50)  not null
        primary key,
    project_id   varchar(50)  null,
    name         varchar(100) null,
    description  varchar(200) null,
    status       varchar(20)  null,
    latest       tinyint(1)   null,
    publish_time bigint(13)   null,
    start_time   bigint(13)   null,
    end_time     bigint(13)   null,
    create_time  bigint(13)   null,
    create_user  varchar(100) null
)
    charset = utf8mb4;

-- create index project_version_project_id_index
--     on supretest.project_version (project_id);

create table IF NOT EXISTS  supretest.qrtz_triggers
(
    SCHED_NAME     varchar(120) not null,
    TRIGGER_NAME   varchar(200) not null,
    TRIGGER_GROUP  varchar(200) not null,
    JOB_NAME       varchar(200) not null,
    JOB_GROUP      varchar(200) not null,
    DESCRIPTION    varchar(250) null,
    NEXT_FIRE_TIME bigint(13)   null,
    PREV_FIRE_TIME bigint(13)   null,
    PRIORITY       int          null,
    TRIGGER_STATE  varchar(16)  not null,
    TRIGGER_TYPE   varchar(8)   not null,
    START_TIME     bigint(13)   not null,
    END_TIME       bigint(13)   null,
    CALENDAR_NAME  varchar(200) null,
    MISFIRE_INSTR  smallint(2)  null,
    JOB_DATA       blob         null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint qrtz_triggers_ibfk_1
        foreign key (SCHED_NAME, JOB_NAME, JOB_GROUP) references supretest.QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.QRTZ_BLOB_TRIGGERS
(
    SCHED_NAME    varchar(120) not null,
    TRIGGER_NAME  varchar(200) not null,
    TRIGGER_GROUP varchar(200) not null,
    BLOB_DATA     blob         null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint qrtz_blob_triggers_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references supretest.qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
    charset = utf8mb4;

-- create index SCHED_NAME
--     on supretest.QRTZ_BLOB_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

create table IF NOT EXISTS  supretest.QRTZ_SIMPLE_TRIGGERS
(
    SCHED_NAME      varchar(120) not null,
    TRIGGER_NAME    varchar(200) not null,
    TRIGGER_GROUP   varchar(200) not null,
    REPEAT_COUNT    bigint(7)    not null,
    REPEAT_INTERVAL bigint(12)   not null,
    TIMES_TRIGGERED bigint(10)   not null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint qrtz_simple_triggers_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references supretest.qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.QRTZ_SIMPROP_TRIGGERS
(
    SCHED_NAME    varchar(120)   not null,
    TRIGGER_NAME  varchar(200)   not null,
    TRIGGER_GROUP varchar(200)   not null,
    STR_PROP_1    varchar(512)   null,
    STR_PROP_2    varchar(512)   null,
    STR_PROP_3    varchar(512)   null,
    INT_PROP_1    int            null,
    INT_PROP_2    int            null,
    LONG_PROP_1   bigint         null,
    LONG_PROP_2   bigint         null,
    DEC_PROP_1    decimal(13, 4) null,
    DEC_PROP_2    decimal(13, 4) null,
    BOOL_PROP_1   varchar(1)     null,
    BOOL_PROP_2   varchar(1)     null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint qrtz_simprop_triggers_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references supretest.qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.qrtz_cron_triggers
(
    SCHED_NAME      varchar(120) not null,
    TRIGGER_NAME    varchar(200) not null,
    TRIGGER_GROUP   varchar(200) not null,
    CRON_EXPRESSION varchar(120) not null,
    TIME_ZONE_ID    varchar(80)  null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint qrtz_cron_triggers_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references supretest.qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
    charset = utf8mb4;

-- create index IDX_QRTZ_T_C
--     on supretest.qrtz_triggers (SCHED_NAME, CALENDAR_NAME);
--
-- create index IDX_QRTZ_T_G
--     on supretest.qrtz_triggers (SCHED_NAME, TRIGGER_GROUP);
--
-- create index IDX_QRTZ_T_J
--     on supretest.qrtz_triggers (SCHED_NAME, JOB_NAME, JOB_GROUP);
--
-- create index IDX_QRTZ_T_JG
--     on supretest.qrtz_triggers (SCHED_NAME, JOB_GROUP);
--
-- create index IDX_QRTZ_T_NEXT_FIRE_TIME
--     on supretest.qrtz_triggers (SCHED_NAME, NEXT_FIRE_TIME);
--
-- create index IDX_QRTZ_T_NFT_MISFIRE
--     on supretest.qrtz_triggers (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME);
--
-- create index IDX_QRTZ_T_NFT_ST
--     on supretest.qrtz_triggers (SCHED_NAME, TRIGGER_STATE, NEXT_FIRE_TIME);
--
-- create index IDX_QRTZ_T_NFT_ST_MISFIRE
--     on supretest.qrtz_triggers (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_STATE);
--
-- create index IDX_QRTZ_T_NFT_ST_MISFIRE_GRP
--     on supretest.qrtz_triggers (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_GROUP, TRIGGER_STATE);
--
-- create index IDX_QRTZ_T_N_G_STATE
--     on supretest.qrtz_triggers (SCHED_NAME, TRIGGER_GROUP, TRIGGER_STATE);
--
-- create index IDX_QRTZ_T_N_STATE
--     on supretest.qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_STATE);
--
-- create index IDX_QRTZ_T_STATE
--     on supretest.qrtz_triggers (SCHED_NAME, TRIGGER_STATE);

create table IF NOT EXISTS  supretest.quota
(
    id            varchar(50)    not null
        primary key,
    api           int(10)        null,
    performance   int(10)        null,
    max_threads   int(10)        null,
    duration      int(10)        null,
    resource_pool varchar(1000)  null,
    workspace_id  varchar(50)    null,
    use_default   tinyint(1)     null,
    update_time   bigint(13)     null,
    member        int(10)        null comment '成员数量限制',
    project       int(10)        null comment '项目数量限制',
    project_id    varchar(50)    null comment '项目类型配额',
    vum_total     decimal(10, 2) null comment '总vum数',
    vum_used      decimal(10, 2) null comment '消耗的vum数'
)
    charset = utf8mb4;

-- create index quota_project_id_index
--     on supretest.quota (project_id);
--
-- create index quota_workspace_id_index
--     on supretest.quota (workspace_id);

create table IF NOT EXISTS  supretest.relationship_edge
(
    source_id   varchar(50) not null comment '源节点的ID',
    target_id   varchar(50) not null comment '目标节点的ID',
    type        varchar(20) not null comment '边的分类',
    graph_id    varchar(50) not null comment '所属关系图的ID',
    creator     varchar(50) not null comment '创建人',
    create_time bigint(13)  not null,
    primary key (source_id, target_id)
)
    charset = utf8mb4;

-- create index source_id_index
--     on supretest.relationship_edge (source_id);
--
-- create index target_id_index
--     on supretest.relationship_edge (target_id);

create table IF NOT EXISTS  supretest.report_statistics
(
    id            varchar(50) not null
        primary key,
    name          varchar(50) null,
    project_id    varchar(50) null comment 'Test plan ID',
    create_user   varchar(64) null comment 'create user',
    update_user   varchar(64) null comment 'create user',
    select_option longtext    null comment 'select option (JSON format)',
    data_option   longtext    null comment 'data option (JSON format)',
    report_type   varchar(50) not null,
    create_time   bigint(13)  null,
    update_time   bigint(13)  null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.role
(
    id          varchar(50)  not null comment 'Role ID'
        primary key,
    name        varchar(64)  not null comment 'Role name',
    description varchar(255) null comment 'Role description',
    type        varchar(50)  null comment 'Role type, (system|organization|workspace)',
    create_time bigint(13)   not null comment 'Create timestamp',
    update_time bigint(13)   not null comment 'Update timestamp'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.schedule
(
    id           varchar(255) not null
        primary key,
    `key`        varchar(255) null,
    type         varchar(50)  not null comment 'Schedule Type',
    value        varchar(255) not null comment 'Schedule value',
    `group`      varchar(50)  null comment 'Group Name',
    job          varchar(64)  not null comment 'Schedule Job Class Name',
    enable       tinyint(1)   null comment 'Schedule Eable',
    resource_id  varchar(255) null,
    user_id      varchar(50)  not null comment 'Change User',
    workspace_id varchar(50)  not null comment 'Workspace ID this schedule belongs to',
    create_time  bigint(13)   null comment 'Create timestamp',
    update_time  bigint(13)   null comment 'Update timestamp',
    project_id   varchar(50)  null,
    name         varchar(100) null,
    config       varchar(500) null
)
    charset = utf8mb4;

-- create index resource_id
--     on supretest.schedule (resource_id);

create table IF NOT EXISTS  supretest.service_integration
(
    id            varchar(50) not null
        primary key,
    platform      varchar(50) not null,
    configuration text        not null,
    workspace_id  varchar(50) null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.share_info
(
    id             varchar(50) not null comment 'Api Document Share Info ID'
        primary key,
    create_time    bigint(13)  not null comment 'Create timestamp',
    create_user_id varchar(64) null,
    update_time    bigint(13)  not null comment 'last visit timestamp',
    share_type     varchar(64) null comment 'single or batch',
    custom_data    longtext    null comment 'Share Custom Data',
    lang           varchar(10) null
)
    charset = utf8mb4;
--
-- create index share_api_id
--     on supretest.share_info (custom_data);
--
-- create index share_type
--     on supretest.share_info (share_type);

create table IF NOT EXISTS  supretest.swagger_url_project
(
    id          varchar(120) not null
        primary key,
    project_id  varchar(120) null,
    swagger_url varchar(255) null,
    module_id   varchar(120) null,
    module_path varchar(255) null,
    mode_id     varchar(120) null,
    config      longtext     null comment '鉴权配置信息'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.system_header
(
    type  varchar(150)  not null
        primary key,
    props varchar(1000) null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.system_parameter
(
    param_key   varchar(64)                 not null comment 'Parameter name'
        primary key,
    param_value varchar(255)                null comment 'Parameter value',
    type        varchar(100) default 'text' not null comment 'Parameter type',
    sort        int(5)                      null comment 'Sort'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_case
(
    id               varchar(50)          not null comment 'Test case ID'
        primary key,
    node_id          varchar(50)          not null comment 'Node ID this case belongs to',
    test_id          varchar(2000)        null,
    node_path        varchar(999)         not null comment 'Node path this case belongs to',
    project_id       varchar(50)          not null comment 'Project ID this test belongs to',
    name             varchar(255)         not null comment 'Test case name',
    type             varchar(25)          null comment 'Test case type',
    maintainer       varchar(50)          null comment 'Test case maintainer',
    priority         varchar(50)          null comment 'Test case priority',
    method           varchar(15)          null comment 'Test case method type',
    prerequisite     text                 null comment 'Test case prerequisite condition',
    remark           text                 null comment 'Test case remark',
    steps            text                 null comment 'Test case steps (JSON format)',
    create_time      bigint(13)           not null comment 'Create timestamp',
    update_time      bigint(13)           not null comment 'Update timestamp',
    sort             int                  null comment 'Import test case sort',
    num              int                  null comment 'Manually controlled growth identifier',
    other_test_name  varchar(200)         null,
    review_status    varchar(25)          null,
    tags             varchar(1000)        null,
    demand_id        varchar(120)         null,
    demand_name      varchar(999)         null,
    status           varchar(25)          null,
    step_description text                 null,
    expected_result  text                 null,
    custom_fields    text                 null comment 'CustomField',
    step_model       varchar(10)          null comment 'Test case step model',
    custom_num       varchar(64)          null comment 'custom num',
    create_user      varchar(100)         null,
    original_status  varchar(50)          null,
    delete_time      bigint(13)           null comment 'Delete timestamp',
    delete_user_id   varchar(64)          null comment 'Delete user id',
    `order`          bigint               not null comment '自定义排序，间隔5000',
    case_public      tinyint(1)           null comment '是否是公共用例',
    version_id       varchar(50)          null comment '版本ID',
    ref_id           varchar(50)          null comment '指向初始版本ID',
    latest           tinyint(1) default 0 null comment '是否为最新版本 0:否，1:是'
)
    charset = utf8mb4;

-- create index test_case_node_id_index
--     on supretest.test_case (node_id);
--
-- create index test_case_ref_id_index
--     on supretest.test_case (ref_id);
--
-- create index test_case_version_id_index
--     on supretest.test_case (version_id);

create table IF NOT EXISTS  supretest.test_case_comment
(
    id          varchar(64) not null
        primary key,
    case_id     varchar(64) null,
    description text        null,
    author      varchar(50) null,
    create_time bigint(13)  null,
    update_time bigint(13)  null,
    status      varchar(80) null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_case_file
(
    case_id varchar(64) null,
    file_id varchar(64) null,
    constraint test_case_file_unique_key
        unique (case_id, file_id)
)
    charset = utf8;

create table IF NOT EXISTS  supretest.test_case_follow
(
    case_id   varchar(50) null,
    follow_id varchar(50) null,
    constraint test_case_follow_scenario_id_follow_id_pk
        unique (case_id, follow_id)
)
    charset = utf8mb4;

-- create index test_case_follow_id_index
--     on supretest.test_case_follow (follow_id);

create table IF NOT EXISTS  supretest.test_case_issues
(
    id          varchar(50)                      not null
        primary key,
    resource_id varchar(50)                      not null,
    issues_id   varchar(100)                     not null,
    ref_type    varchar(30) default 'FUNCTIONAL' not null,
    ref_id      varchar(50)                      null comment '测试计划的用例所指向的用例的id'
)
    charset = utf8mb4;

-- create index issues_id_index
--     on supretest.test_case_issues (issues_id);

create table IF NOT EXISTS  supretest.test_case_node
(
    id          varchar(50)       not null comment 'Test case node ID'
        primary key,
    project_id  varchar(50)       not null comment 'Project ID this node belongs to',
    name        varchar(100)      not null comment 'Node name',
    parent_id   varchar(50)       null comment 'Parent node ID',
    level       int(10) default 1 null comment 'Node level',
    create_time bigint(13)        not null comment 'Create timestamp',
    update_time bigint(13)        not null comment 'Update timestamp',
    pos         double            null,
    create_user varchar(100)      null
)
    charset = utf8mb4;

-- create index test_case_node_project_id_index
--     on supretest.test_case_node (project_id);

create table IF NOT EXISTS  supretest.test_case_report
(
    id          varchar(50)  not null
        primary key,
    name        varchar(64)  not null comment 'Test case report name',
    content     longtext     null comment 'Report content (JSON format)',
    start_time  bigint(13)   null comment 'Test start time',
    end_time    bigint(13)   null comment 'Test end time',
    create_user varchar(100) null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_case_report_template
(
    id           varchar(50)  not null
        primary key,
    name         varchar(64)  not null comment 'Test case report template name',
    workspace_id varchar(50)  null comment 'Workspace ID this project belongs to',
    content      longtext     null comment 'Template content (JSON format)',
    create_user  varchar(100) null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_case_review
(
    id          varchar(50)   not null
        primary key,
    name        varchar(200)  null,
    creator     varchar(50)   null,
    status      varchar(50)   null,
    create_time bigint(13)    null,
    update_time bigint(13)    null,
    end_time    bigint(13)    null,
    description text          null,
    project_id  varchar(50)   null comment '用例评审所属项目',
    tags        varchar(2000) null,
    create_user varchar(100)  null
)
    charset = utf8mb4;

-- create index test_case_review_creator_index
--     on supretest.test_case_review (creator);

create table IF NOT EXISTS  supretest.test_case_review_api_case
(
    id                  varchar(50) not null
        primary key,
    test_case_review_id varchar(50) null,
    api_case_id         varchar(50) null,
    status              varchar(50) null,
    environment_id      varchar(50) null,
    create_time         bigint(13)  null,
    update_time         bigint(13)  null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_case_review_follow
(
    review_id varchar(50) null,
    follow_id varchar(50) null comment '关注人',
    constraint test_case_review_users_pk
        unique (review_id, follow_id)
)
    comment ' review and user association table' charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_case_review_load
(
    id                  varchar(50) not null
        primary key,
    test_case_review_id varchar(50) null,
    load_case_id        varchar(50) null,
    status              varchar(50) null,
    create_time         bigint(13)  null,
    update_time         bigint(13)  null,
    load_report_id      varchar(50) null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_case_review_project
(
    review_id  varchar(50) null,
    project_id varchar(50) null,
    constraint test_case_review_project_pk
        unique (review_id, project_id)
)
    charset = utf8mb4;

-- create index test_case_review_project_project_id_index
--     on supretest.test_case_review_project (project_id);

create table IF NOT EXISTS  supretest.test_case_review_scenario
(
    id                  varchar(50)  not null
        primary key,
    test_case_review_id varchar(50)  null,
    api_scenario_id     varchar(50)  null,
    status              varchar(50)  null,
    environment         longtext     null,
    create_time         bigint(13)   null,
    update_time         bigint(13)   null,
    pass_rate           varchar(100) null,
    last_result         varchar(100) null,
    report_id           varchar(50)  null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_case_review_test_case
(
    id          varchar(64)  not null
        primary key,
    review_id   varchar(64)  null,
    case_id     varchar(64)  null,
    status      varchar(64)  null,
    result      varchar(50)  null,
    reviewer    varchar(64)  null,
    create_time bigint(13)   null,
    update_time bigint(13)   null,
    create_user varchar(100) null,
    `order`     bigint       not null comment '自定义排序，间隔5000'
)
    charset = utf8mb4;

-- create index index_name
--     on supretest.test_case_review_test_case (case_id);
--
-- create index test_case_review_test_case_review_id_index
--     on supretest.test_case_review_test_case (review_id);

create table IF NOT EXISTS  supretest.test_case_review_users
(
    review_id varchar(50) null,
    user_id   varchar(50) null,
    constraint test_case_review_users_pk
        unique (review_id, user_id)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_case_template
(
    id               varchar(100)         not null
        primary key,
    name             varchar(64)          not null comment 'Field template name',
    type             varchar(30)          not null comment 'Field template type',
    description      varchar(255)         null comment 'Field template description',
    case_name        varchar(64)          null comment 'Test Case Name',
    `system`         tinyint(1) default 0 null comment 'Is system field template ',
    global           tinyint(1) default 0 null comment 'Is global template',
    prerequisite     varchar(255)         null comment 'Test case prerequisite condition',
    step_description text                 null comment 'Test case steps desc',
    expected_result  text                 null comment 'Test case expected result',
    actual_result    text                 null comment 'Test case actual result',
    create_time      bigint(13)           not null comment 'Create timestamp',
    update_time      bigint(13)           not null comment 'Update timestamp',
    step_model       varchar(10)          null comment 'Step model',
    steps            text                 null comment 'Test case step',
    create_user      varchar(100)         null,
    project_id       varchar(64)          null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_case_test
(
    test_case_id varchar(50) null,
    test_id      varchar(50) null,
    test_type    varchar(50) null,
    create_time  bigint(13)  null,
    update_time  bigint(13)  null,
    constraint test_case_test_unique_key
        unique (test_case_id, test_id)
)
    comment '测试用例和关联用例的关系表' charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_plan
(
    id                      varchar(50)          not null comment 'Test Plan ID'
        primary key,
    workspace_id            varchar(50)          not null comment 'Workspace ID this plan belongs to',
    report_id               varchar(50)          null comment 'Test plan report',
    name                    varchar(128)         not null comment 'Plan name',
    description             varchar(255)         null comment 'Plan description',
    status                  varchar(20)          not null comment 'Plan status',
    stage                   varchar(30)          not null comment 'Plan stage',
    test_case_match_rule    varchar(255)         null comment 'Test case match rule',
    executor_match_rule     varchar(255)         null comment 'Executor match rule)',
    tags                    text                 null comment 'Test plan tags (JSON format)',
    create_time             bigint(13)           not null comment 'Create timestamp',
    update_time             bigint(13)           not null comment 'Update timestamp',
    planned_start_time      bigint(13)           null comment 'Planned start time timestamp',
    planned_end_time        bigint(13)           null comment 'Planned end time timestamp',
    actual_start_time       bigint(13)           null comment 'Actual start time timestamp',
    actual_end_time         bigint(13)           null comment 'Actual end time timestamp',
    creator                 varchar(255)         not null,
    project_id              varchar(50)          null comment '测试计划所属项目',
    execution_times         int                  null,
    automatic_status_update tinyint(1) default 0 null comment '是否自定更新功能用例状态',
    report_summary          text                 null comment '测试计划报告总结',
    report_config           text                 null comment '测试计划报告配置',
    repeat_case             tinyint(1) default 0 null comment '是否允许重复添加用例',
    run_mode_config         longtext             null comment 'request (JSON format)'
)
    charset = utf8mb4;

-- create index project_id_index
--     on supretest.test_plan (project_id);

create table IF NOT EXISTS  supretest.test_plan_api_case
(
    id             varchar(50)  not null comment 'ID'
        primary key,
    test_plan_id   varchar(50)  not null comment 'Test plan ID',
    api_case_id    varchar(50)  not null comment 'Api test case ID',
    status         varchar(50)  null comment 'Api case status',
    environment_id varchar(50)  null comment 'Relevance environment_id',
    create_time    bigint(13)   not null comment 'Create timestamp',
    update_time    bigint(13)   not null comment 'Update timestamp',
    create_user    varchar(100) null,
    `order`        bigint       not null comment '自定义排序，间隔5000'
)
    charset = utf8mb4;

-- create index plan_id_index
--     on supretest.test_plan_api_case (test_plan_id);

create table IF NOT EXISTS  supretest.test_plan_api_scenario
(
    id                   varchar(50)  not null comment 'ID'
        primary key,
    test_plan_id         varchar(50)  not null comment 'Test plan ID',
    api_scenario_id      varchar(255) null,
    status               varchar(50)  null comment 'Scenario case status',
    environment          longtext     null comment 'Relevance environment',
    create_time          bigint(13)   not null comment 'Create timestamp',
    update_time          bigint(13)   not null comment 'Update timestamp',
    pass_rate            varchar(100) null,
    last_result          varchar(100) null,
    report_id            varchar(50)  null,
    create_user          varchar(100) null,
    `order`              bigint       not null comment '自定义排序，间隔5000',
    environment_type     varchar(20)  null comment '场景使用的环境类型',
    environment_group_id varchar(50)  null comment '场景使用的环境组ID'
)
    charset = utf8mb4;

-- create index api_scenario_id_index
--     on supretest.test_plan_api_scenario (api_scenario_id);
--
-- create index plan_id_index
--     on supretest.test_plan_api_scenario (test_plan_id);

create table IF NOT EXISTS  supretest.test_plan_execution_queue
(
    id           varchar(50)  not null comment 'ID'
        primary key,
    report_id    varchar(100) null comment '测试计划报告',
    run_mode     varchar(100) null comment '执行模式/scenario/api/test_paln_api/test_pan_scenario',
    create_time  bigint(13)   null comment '创建时间',
    test_plan_id varchar(100) null comment 'testPlanId',
    resource_id  varchar(100) null comment 'resourceId/批次id',
    num          int          null comment 'order'
)
    charset = utf8mb4;

-- create index num_index
--     on supretest.test_plan_execution_queue (num);
--
-- create index report_id_idx
--     on supretest.test_plan_execution_queue (report_id);
--
-- create index resource_id_index
--     on supretest.test_plan_execution_queue (resource_id);

create table IF NOT EXISTS  supretest.test_plan_follow
(
    test_plan_id varchar(50) null,
    follow_id    varchar(50) null comment '关注人',
    constraint test_plan_principal_pk
        unique (test_plan_id, follow_id)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_plan_load_case
(
    id                     varchar(50)  not null comment 'ID'
        primary key,
    test_plan_id           varchar(50)  not null comment 'Test plan ID',
    load_case_id           varchar(50)  not null comment 'Load test case ID',
    status                 varchar(50)  null comment 'Load case status',
    load_report_id         varchar(50)  null comment 'Load report id',
    create_time            bigint(13)   not null comment 'Create timestamp',
    update_time            bigint(13)   not null comment 'Update timestamp',
    create_user            varchar(100) null,
    test_resource_pool_id  varchar(50)  null,
    load_configuration     longtext     null,
    `order`                bigint       not null comment '自定义排序，间隔5000',
    advanced_configuration text         null
)
    charset = utf8mb4;

-- create index plan_id_index
--     on supretest.test_plan_load_case (test_plan_id);

create table IF NOT EXISTS  supretest.test_plan_principal
(
    test_plan_id varchar(50) null,
    principal_id varchar(50) null,
    constraint test_plan_principal_pk
        unique (test_plan_id, principal_id)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_plan_project
(
    test_plan_id varchar(50) null,
    project_id   varchar(50) null,
    constraint test_plan_project_pk
        unique (test_plan_id, project_id)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_plan_report
(
    id                       varchar(50)  not null comment 'ID'
        primary key,
    test_plan_id             varchar(50)  not null comment 'Test plan ID',
    create_time              bigint(13)   not null comment 'Create timestamp',
    update_time              bigint(13)   not null comment 'Update timestamp',
    name                     varchar(128) null comment 'name',
    status                   varchar(50)  null comment 'report status',
    trigger_mode             varchar(50)  null comment 'test plan execute triggerMode',
    creator                  varchar(50)  null comment 'report creator',
    start_time               bigint(13)   null comment 'report startTime',
    end_time                 bigint(13)   null comment 'report timestamp',
    is_api_case_executing    tinyint(1)   null comment 'is Api Case executing',
    is_scenario_executing    tinyint(1)   null comment 'is scenario Case executing',
    is_performance_executing tinyint(1)   null comment 'is performance executing',
    principal                varchar(50)  null comment 'principal',
    components               varchar(20)  null,
    is_new                   tinyint(1)   null comment 'v1.12报告改版标记',
    constraint executeInfoID
        unique (test_plan_id, create_time)
)
    charset = utf8mb4;

-- create index plan_id_index
--     on supretest.test_plan_report (test_plan_id);

create table IF NOT EXISTS  supretest.test_plan_report_content
(
    id                           varchar(50) not null comment 'ID'
        primary key,
    test_plan_report_id          varchar(50) not null comment 'Test plan ID',
    start_time                   bigint(13)  null,
    case_count                   bigint(10)  null,
    end_time                     bigint(13)  null,
    execute_rate                 double      null,
    pass_rate                    double      null,
    is_third_part_issue          tinyint(1)  null comment 'is third part issue',
    config                       text        null comment 'plan config (JSON format)',
    summary                      text        null comment 'summary',
    function_result              text        null comment 'function result (JSON format)',
    api_result                   text        null comment 'api result (JSON format)',
    load_result                  text        null comment 'api result (JSON format)',
    function_all_cases           longtext    null comment 'function all cases (JSON format)',
    function_failure_cases       longtext    null comment 'function failure cases (JSON format)',
    issue_list                   longtext    null comment 'issue list (JSON format)',
    api_all_cases                longtext    null comment 'api all cases (JSON format)',
    api_failure_cases            longtext    null comment 'api failure cases (JSON format)',
    scenario_all_cases           longtext    null comment 'scenario all cases (JSON format)',
    scenario_failure_cases       longtext    null comment 'scenario failure cases (JSON format)',
    load_all_Cases               longtext    null comment 'load all cases (JSON format)',
    load_failure_cases           longtext    null comment 'load failure cases (JSON format)',
    plan_scenario_report_struct  longtext    null,
    plan_api_case_report_struct  longtext    null,
    plan_load_case_report_struct longtext    null,
    error_report_cases           longtext    null comment '误报状态接口用例',
    error_report_scenarios       longtext    null comment '误报状态场景用例',
    un_execute_cases             longtext    null comment '未执行状态接口用例',
    un_execute_scenarios         longtext    null comment '未执行状态场景用例',
    constraint test_plan_report_id
        unique (test_plan_report_id)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_plan_report_data
(
    id                    varchar(50) not null comment 'ID'
        primary key,
    test_plan_report_id   varchar(50) not null comment 'Test plan ID',
    execute_result        longtext    null comment 'executeResult (JSON format)',
    failur_test_cases     longtext    null comment 'failurTestCases (JSON format)',
    module_execute_result longtext    null comment 'moduleExecuteResult (JSON format)',
    api_case_info         longtext    null comment 'apiCaseID list (JSON format)',
    scenario_info         longtext    null comment 'scenarioID list (JSON format)',
    performance_info      longtext    null comment 'performanceID list (JSON format)',
    issues_info           longtext    null comment 'issues (JSON format)',
    constraint test_plan_report_id
        unique (test_plan_report_id)
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_plan_test_case
(
    id            varchar(50)       not null comment 'ID'
        primary key,
    plan_id       varchar(50)       not null comment 'Plan ID relation to',
    case_id       varchar(50)       not null comment 'Case ID relation to',
    report_id     varchar(50)       null comment 'Test report ID relation to',
    executor      varchar(64)       not null comment 'Test case executor',
    status        varchar(15)       null comment 'Test case status',
    results       longtext          null comment 'Test case result',
    issues        longtext          null comment 'Test case result issues',
    remark        varchar(255)      null comment 'Test case remark',
    create_time   bigint(13)        not null comment 'Create timestamp',
    update_time   bigint(13)        not null comment 'Update timestamp',
    actual_result text              null,
    create_user   varchar(100)      null,
    issues_count  int(10) default 0 null,
    `order`       bigint            not null comment '自定义排序，间隔5000'
)
    charset = utf8mb4;

-- create index index_name
--     on supretest.test_plan_test_case (case_id);
--
-- create index plan_id_index
--     on supretest.test_plan_test_case (plan_id);
--
-- create index test_plan_test_case_plan_id_index
--     on supretest.test_plan_test_case (plan_id);

create table IF NOT EXISTS  supretest.test_resource
(
    id                    varchar(50) not null comment 'Test resource ID'
        primary key,
    test_resource_pool_id varchar(50) not null comment 'Test resource pool ID this test resource belongs to',
    configuration         longtext    null comment 'Test resource configuration',
    status                varchar(64) not null comment 'Test resource status',
    create_time           bigint(13)  not null comment 'Create timestamp',
    update_time           bigint(13)  not null comment 'Update timestamp'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.test_resource_pool
(
    id               varchar(50)          not null comment 'Test resource pool ID'
        primary key,
    name             varchar(64)          not null comment 'Test resource pool name',
    type             varchar(30)          not null comment 'Test resource pool type',
    description      varchar(255)         null comment 'Test resource pool description',
    status           varchar(64)          not null comment 'Test resource pool status',
    create_time      bigint(13)           not null comment 'Create timestamp',
    update_time      bigint(13)           not null comment 'Update timestamp',
    image            varchar(100)         null,
    heap             varchar(200)         null,
    gc_algo          varchar(200)         null,
    create_user      varchar(100)         null,
    api              tinyint(1)           null,
    performance      tinyint(1)           null,
    backend_listener tinyint(1) default 1 null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.ui_element
(
    id            varchar(50)              not null comment 'Ui element ID'
        primary key,
    num           int                      null comment 'Ui element ID',
    module_id     varchar(50)              not null comment 'Node ID this case belongs to',
    project_id    varchar(50)              not null comment 'Project ID this test belongs to',
    name          varchar(255)             not null comment 'Ui element name',
    location_type varchar(30)              not null comment 'Location type',
    location      varchar(300)             not null comment 'Location',
    create_user   varchar(100)             null,
    version_id    varchar(50)              not null comment '版本ID',
    ref_id        varchar(50)              not null comment '指向初始版本ID',
    `order`       bigint                   not null comment '自定义排序，间隔5000',
    latest        tinyint(1)    default 0  null comment '是否为最新版本 0:否，1:是',
    description   varchar(1000) default '' null comment '元素描述',
    create_time   bigint(13)               not null comment 'Create timestamp',
    update_time   bigint(13)               not null comment 'Update timestamp'
)
    charset = utf8mb4;

-- create index ui_element_order_index
--     on supretest.ui_element (`order`);

create table IF NOT EXISTS  supretest.ui_element_module
(
    id          varchar(50)       not null comment 'Ui scenario node ID'
        primary key,
    project_id  varchar(50)       not null comment 'Project ID this node belongs to',
    name        varchar(64)       not null comment 'Node name',
    parent_id   varchar(50)       null comment 'Parent node ID',
    level       int(10) default 1 null comment 'Node level',
    create_time bigint(13)        not null comment 'Create timestamp',
    update_time bigint(13)        not null comment 'Update timestamp',
    pos         double            null,
    create_user varchar(100)      null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.ui_scenario
(
    id                   varchar(255)         not null
        primary key,
    project_id           varchar(50)          not null comment 'Project ID this test belongs to',
    tags                 varchar(2000)        null comment 'tag list',
    user_id              varchar(64)          null comment 'User ID',
    module_id            varchar(64)          null comment 'Module ID',
    module_path          varchar(1000)        null,
    name                 varchar(255)         not null comment 'ui scenario name',
    level                varchar(100)         null comment 'ui scenario level ',
    status               varchar(100)         not null comment 'ui scenario status ',
    principal            varchar(100)         not null comment 'ui scenario principal ',
    step_total           int        default 0 null comment 'Step total ',
    schedule             varchar(255)         null comment 'Test schedule (cron list)',
    scenario_definition  longtext             null comment 'Test scenario_definition json',
    description          longtext             null comment 'ui scenario description',
    create_time          bigint(13)           not null comment 'Create timestamp',
    update_time          bigint(13)           not null comment 'Update timestamp',
    pass_rate            varchar(100)         null,
    last_result          varchar(100)         null,
    report_id            varchar(50)          null,
    num                  int                  null comment 'ui scenario ID',
    original_state       varchar(64)          null,
    custom_num           varchar(64)          null comment 'custom num',
    create_user          varchar(100)         null,
    use_url              longtext             null comment '步骤中用到的url',
    version              int(10)    default 0 null comment '版本号',
    delete_time          bigint(13)           null comment 'Delete timestamp',
    delete_user_id       varchar(64)          null comment 'Delete user id',
    execute_times        int                  null,
    `order`              bigint               not null comment '自定义排序，间隔5000',
    environment_type     varchar(20)          null,
    environment_json     longtext             null,
    environment_group_id varchar(50)          null,
    version_id           varchar(50)          not null,
    ref_id               varchar(255)         not null,
    latest               tinyint(1) default 0 null comment '是否为最新版本 0:否，1:是'
)
    charset = utf8mb4;

-- create index ui_scenario_ref_id_index
--     on supretest.ui_scenario (ref_id);
--
-- create index ui_scenario_version_id_index
--     on supretest.ui_scenario (version_id);

create table IF NOT EXISTS  supretest.ui_scenario_module
(
    id          varchar(50)       not null comment 'ui scenario node ID'
        primary key,
    project_id  varchar(50)       not null comment 'Project ID this node belongs to',
    name        varchar(64)       not null comment 'Node name',
    parent_id   varchar(50)       null comment 'Parent node ID',
    level       int(10) default 1 null comment 'Node level',
    create_time bigint(13)        not null comment 'Create timestamp',
    update_time bigint(13)        not null comment 'Update timestamp',
    pos         double            null,
    create_user varchar(100)      null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.ui_scenario_reference
(
    id             varchar(50)  not null
        primary key,
    ui_scenario_id varchar(255) null,
    create_time    bigint(13)   null,
    create_user_id varchar(64)  null,
    reference_id   varchar(255) null,
    reference_type varchar(255) null,
    data_type      varchar(255) null
)
    charset = utf8mb4;

-- create index ui_scenario_id_idx
--     on supretest.ui_scenario_reference (ui_scenario_id);

create table IF NOT EXISTS  supretest.user
(
    id                varchar(50)                      not null comment 'User ID'
        primary key,
    name              varchar(64)                      not null comment 'User name',
    email             varchar(64)                      not null comment 'E-Mail address',
    password          varchar(256) collate utf8mb4_bin null,
    status            varchar(50)                      not null comment 'User status',
    create_time       bigint(13)                       not null comment 'Create timestamp',
    update_time       bigint(13)                       not null comment 'Update timestamp',
    language          varchar(30)                      null,
    last_workspace_id varchar(50)                      null,
    phone             varchar(50)                      null comment 'Phone number of user',
    source            varchar(50)                      null,
    last_project_id   varchar(50)                      null,
    create_user       varchar(100)                     null,
    platform_info     longtext                         null comment ' 其他平台对接信息',
    selenium_server   varchar(255) default ''          null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.user_group
(
    id          varchar(64) not null
        primary key,
    user_id     varchar(64) not null,
    group_id    varchar(64) not null,
    source_id   varchar(64) not null,
    create_time bigint(13)  not null,
    update_time bigint(13)  not null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.user_group_permission
(
    id            varchar(64)  not null
        primary key,
    group_id      varchar(64)  not null comment '用户组ID',
    permission_id varchar(128) not null,
    module_id     varchar(64)  not null comment '功能菜单'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.user_header
(
    id      varchar(50)   not null
        primary key,
    user_id varchar(50)   null,
    props   varchar(1000) null,
    type    varchar(150)  null
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.user_key
(
    id          varchar(50) default '' not null comment 'user_key ID'
        primary key,
    user_id     varchar(50)            not null comment '用户ID',
    access_key  varchar(50)            not null comment 'access_key',
    secret_key  varchar(50)            not null comment 'secret key',
    create_time bigint(13)             not null comment '创建时间',
    status      varchar(10)            null comment '状态',
    constraint IDX_AK
        unique (access_key)
)
    charset = utf8mb4;

-- create index IDX_USER_ID
--     on supretest.user_key (user_id);

create table IF NOT EXISTS  supretest.user_role
(
    id          varchar(50) not null comment 'ID of user''s role info'
        primary key,
    user_id     varchar(50) not null comment 'User ID of this user-role info',
    role_id     varchar(50) not null comment 'Role ID of this user-role info',
    source_id   varchar(50) null comment 'The (system|organization|workspace) ID of this user-role info',
    create_time bigint(13)  not null comment 'Create timestamp',
    update_time bigint(13)  not null comment 'Update timestamp'
)
    charset = utf8mb4;

create table IF NOT EXISTS  supretest.workspace
(
    id          varchar(50)  not null comment 'Workspace ID '
        primary key,
    name        varchar(64)  not null comment 'Workspace name',
    description varchar(255) null comment 'Workspace description',
    create_time bigint(13)   not null comment 'Create timestamp',
    update_time bigint(13)   not null comment 'Update timestamp',
    create_user varchar(100) null
)
    charset = utf8mb4;

