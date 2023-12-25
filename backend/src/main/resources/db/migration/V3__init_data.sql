insert into supretest.group (id, `name`, description, `system`, `type`, create_time, update_time, creator, scope_id)
values  ('admin', '系统管理员(系统)', '默认用户组', 1, 'SYSTEM', 1621224000000, 1621224000000, 'admin', 'global');

insert into supretest.group (id, `name`, description, `system`, `type`, create_time, update_time, creator, scope_id)
values('project_admin', '项目管理员(系统)', '项目管理员', 1, 'PROJECT', 1620674220004, 1620674220000, 'admin', 'global');

insert into supretest.group (id, `name`, description, `system`, `type`, create_time, update_time, creator, scope_id)
values ('project_member', '项目成员(系统)', '项目成员', 1, 'PROJECT', 1620674220005, 1620674220000, 'admin', 'global');
insert into supretest.group (id, `name`, description, `system`, `type`, create_time, update_time, creator, scope_id)
values ('read_only', '只读用户(系统)', '只读用户', 1, 'PROJECT', 1620674220006, 1620674220000, 'admin', 'global');
insert into supretest.group (id, `name`, description, `system`, `type`, create_time, update_time, creator, scope_id)
values ('ws_admin', '工作空间管理员(系统)', '工作空间管理员', 1, 'WORKSPACE', 1620674220007, 1620674220000, 'admin', 'global');
insert into supretest.group (id, `name`, description, `system`, `type`, create_time, update_time, creator, scope_id)
values ('ws_member', '工作空间成员(系统)', '工作空间成员', 1, 'WORKSPACE', 1620674220008, 1620674220000, 'admin', 'global');


insert into user_group_permission (id, group_id, permission_id, module_id) values ('36c05551-5195-4cb8-98d4-737f15ffe0bb', 'project_admin', 'PROJECT_VERSION:READ+DELETE', 'PROJECT_VERSION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4783870f-c29c-4b00-9797-be618b4464a2', 'project_admin', 'PROJECT_VERSION:READ+ENABLE', 'PROJECT_VERSION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4c6c219a-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_REPORT_ANALYSIS:READ+UPDATE', 'PROJECT_REPORT_ANALYSIS');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4c6cc3d8-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_REPORT_ANALYSIS:READ+CREATE', 'PROJECT_REPORT_ANALYSIS');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4c6d39f4-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_REPORT_ANALYSIS:READ+UPDATE', 'PROJECT_REPORT_ANALYSIS');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4c6dcbfa-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_REPORT_ANALYSIS:READ+CREATE', 'PROJECT_REPORT_ANALYSIS');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6992b5-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_SERVICE:READ', 'WORKSPACE_SERVICE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d69f3da-cc0d-11ec-820e-0242ac110002', 'ws_member', 'WORKSPACE_SERVICE:READ', 'WORKSPACE_SERVICE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6a4fc0-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_SERVICE:READ+EDIT', 'WORKSPACE_SERVICE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6aabd6-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_MESSAGE:READ', 'WORKSPACE_MESSAGE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6b0a3b-cc0d-11ec-820e-0242ac110002', 'ws_member', 'WORKSPACE_MESSAGE:READ', 'WORKSPACE_MESSAGE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6b7898-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_MESSAGE:READ+EDIT', 'WORKSPACE_MESSAGE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6bbdea-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_FILE:READ+JAR', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6c16e7-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_FILE:READ+UPLOAD+JAR', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6c8553-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_FILE:READ+DELETE+JAR', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6ce201-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_FILE:READ+JAR', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6d36c1-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_FILE:READ+UPLOAD+JAR', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6d8a39-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_FILE:READ+DELETE+JAR', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6df273-cc0d-11ec-820e-0242ac110002', 'read_only', 'PROJECT_FILE:READ+JAR', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6e73b3-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_FILE:READ+FILE', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6ecc15-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_FILE:READ+UPLOAD+FILE', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6f4f79-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_FILE:READ+DELETE+FILE', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6f9f55-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_FILE:READ+FILE', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d6ff1cb-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_FILE:READ+UPLOAD+FILE', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d70521c-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_FILE:READ+DELETE+FILE', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d70a653-cc0d-11ec-820e-0242ac110002', 'read_only', 'PROJECT_FILE:READ+FILE', 'PROJECT_FILE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d70eea6-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_CUSTOM_CODE:READ', 'PROJECT_CUSTOM_CODE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d7149c8-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_CUSTOM_CODE:READ+CREATE', 'PROJECT_CUSTOM_CODE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d71a4b5-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_CUSTOM_CODE:READ+EDIT', 'PROJECT_CUSTOM_CODE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d71f67b-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_CUSTOM_CODE:READ+DELETE', 'PROJECT_CUSTOM_CODE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d72461f-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_CUSTOM_CODE:READ+COPY', 'PROJECT_CUSTOM_CODE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d729721-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_CUSTOM_CODE:READ', 'PROJECT_CUSTOM_CODE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d72ea04-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_CUSTOM_CODE:READ+CREATE', 'PROJECT_CUSTOM_CODE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d734426-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_CUSTOM_CODE:READ+EDIT', 'PROJECT_CUSTOM_CODE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d739c44-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_CUSTOM_CODE:READ+DELETE', 'PROJECT_CUSTOM_CODE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d7404c4-cc0d-11ec-820e-0242ac110002', 'project_member', 'PROJECT_CUSTOM_CODE:READ+COPY', 'PROJECT_CUSTOM_CODE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4d748070-cc0d-11ec-820e-0242ac110002', 'read_only', 'PROJECT_CUSTOM_CODE:READ', 'PROJECT_CUSTOM_CODE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4f18986e-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_MANAGER:READ+UPLOAD_JAR', 'WORKSPACE_PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4f192e7f-cc0d-11ec-820e-0242ac110002', 'ws_member', 'WORKSPACE_PROJECT_MANAGER:READ+UPLOAD_JAR', 'WORKSPACE_PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4f4f95bc-cc0d-11ec-820e-0242ac110002', 'project_group', 'PROJECT_GROUP:READ', 'PROJECT_GROUP');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4f50437d-cc0d-11ec-820e-0242ac110002', 'project_group', 'PROJECT_GROUP:READ+CREATE', 'PROJECT_GROUP');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4f50c6ad-cc0d-11ec-820e-0242ac110002', 'project_group', 'PROJECT_GROUP:READ+EDIT', 'PROJECT_GROUP');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4f516d19-cc0d-11ec-820e-0242ac110002', 'project_group', 'PROJECT_GROUP:READ+DELETE', 'PROJECT_GROUP');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('4f51e95e-cc0d-11ec-820e-0242ac110002', 'project_group', 'PROJECT_GROUP:READ+SETTING_PERMISSION', 'PROJECT_GROUP');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('502eb101-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_ENVIRONMENT:READ+CREATE_GROUP', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('502efb4a-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_ENVIRONMENT:READ+EDIT_GROUP', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('502f410e-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_ENVIRONMENT:READ+COPY_GROUP', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('502f880f-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_ENVIRONMENT:READ+DELETE_GROUP', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('5030264b-cc0d-11ec-820e-0242ac110002', 'admin', 'SYSTEM_PLUGIN:READ', 'SYSTEM_PLUGIN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('50306e40-cc0d-11ec-820e-0242ac110002', 'admin', 'SYSTEM_PLUGIN:UPLOAD', 'SYSTEM_PLUGIN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('5030b666-cc0d-11ec-820e-0242ac110002', 'admin', 'SYSTEM_PLUGIN:DEL', 'SYSTEM_PLUGIN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('50dcd2a2-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_APP_MANAGER:READ+EDIT', 'PROJECT_APP_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('5436d8d0-d29c-11ec-b7cc-0242ac110002', 'project_admin', 'PERSONAL_INFORMATION:READ+UI_SETTING', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc28cd-cc0d-11ec-820e-0242ac110002', 'admin', 'PERSONAL_INFORMATION:READ+EDIT', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc2bc1-cc0d-11ec-820e-0242ac110002', 'admin', 'PERSONAL_INFORMATION:READ+THIRD_ACCOUNT', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc2c58-cc0d-11ec-820e-0242ac110002', 'admin', 'PERSONAL_INFORMATION:READ+API_KEYS', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc2c98-cc0d-11ec-820e-0242ac110002', 'admin', 'PERSONAL_INFORMATION:READ+EDIT_PASSWORD', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc2e01-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'PERSONAL_INFORMATION:READ+EDIT', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc2e5c-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'PERSONAL_INFORMATION:READ+THIRD_ACCOUNT', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc2e95-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'PERSONAL_INFORMATION:READ+API_KEYS', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc2ec3-cc0d-11ec-820e-0242ac110002', 'ws_admin', 'PERSONAL_INFORMATION:READ+EDIT_PASSWORD', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc2f81-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PERSONAL_INFORMATION:READ+EDIT', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc2fcb-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PERSONAL_INFORMATION:READ+THIRD_ACCOUNT', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc2ff8-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PERSONAL_INFORMATION:READ+API_KEYS', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('54fc301d-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PERSONAL_INFORMATION:READ+EDIT_PASSWORD', 'PERSONAL_INFORMATION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('56609e1b-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_MESSAGE:READ', 'PROJECT_MESSAGE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('566107d2-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_MESSAGE:READ+EDIT', 'PROJECT_MESSAGE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('571e2468-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TEMPLATE:READ+CASE_TEMPLATE', 'PROJECT_TEMPLATE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('571e9ea9-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TEMPLATE:READ', 'PROJECT_TEMPLATE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('571f3402-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TEMPLATE:READ+CUSTOM', 'PROJECT_TEMPLATE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('571faf9b-cc0d-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TEMPLATE:READ+ISSUE_TEMPLATE', 'PROJECT_TEMPLATE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('7396b1f2-2ed4-4582-bbd8-8d721dac96fa', 'project_admin', 'PROJECT_VERSION:READ+CREATE', 'PROJECT_VERSION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('75a35739-832d-4edf-8bba-f19e46d9a8df', 'project_admin', 'PROJECT_VERSION:READ', 'PROJECT_VERSION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('8d0ba6b9-938c-4e94-b60f-df791b36f56c', 'project_admin', 'PROJECT_VERSION:READ+EDIT', 'PROJECT_VERSION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb5cf29-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_GROUP:READ+CREATE', 'SYSTEM_GROUP');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb60c7f-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_USER:READ+DELETE', 'SYSTEM_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb6584c-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_WORKSPACE:READ', 'SYSTEM_WORKSPACE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb6db1a-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_GROUP:READ+EDIT', 'SYSTEM_GROUP');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb716f3-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_QUOTA:READ+EDIT', 'SYSTEM_QUOTA');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb75470-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_USER:READ+EDIT_PASSWORD', 'SYSTEM_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb78d2c-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_SETTING:READ', 'SYSTEM_SETTING');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb7c6ee-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_USER:READ+EDIT', 'SYSTEM_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb804b9-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_USER:READ+IMPORT', 'SYSTEM_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb83ffd-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_GROUP:READ+DELETE', 'SYSTEM_GROUP');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb87db1-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_USER:READ', 'SYSTEM_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb8bd50-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_SETTING:READ+EDIT', 'SYSTEM_SETTING');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb8fb34-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_WORKSPACE:READ+EDIT', 'SYSTEM_WORKSPACE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb93992-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_WORKSPACE:READ+CREATE', 'SYSTEM_WORKSPACE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adb9b9ea-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_WORKSPACE:READ+DELETE', 'SYSTEM_WORKSPACE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adba7355-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_USER:READ+CREATE', 'SYSTEM_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbab575-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_AUTH:READ', 'SYSTEM_AUTH');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbaf29e-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_GROUP:READ+SETTING_PERMISSION', 'SYSTEM_GROUP');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbb35a4-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_GROUP:READ', 'SYSTEM_GROUP');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbb70d7-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_AUTH:READ+EDIT', 'SYSTEM_AUTH');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbbb344-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_SETTING:READ+AUTH_MANAGE', 'SYSTEM_SETTING');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbbf78f-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_QUOTA:READ', 'SYSTEM_QUOTA');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbc35fd-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_TEST_POOL:READ+DELETE', 'SYSTEM_TEST_POOL');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbc7077-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_TEST_POOL:READ+CREATE', 'SYSTEM_TEST_POOL');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbcb2ad-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_TEST_POOL:READ', 'SYSTEM_TEST_POOL');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbd0611-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_TEST_POOL:READ+EDIT', 'SYSTEM_TEST_POOL');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbd650b-cc09-11ec-820e-0242ac110002', 'admin', 'SYSTEM_OPERATING_LOG:READ', 'SYSTEM_OPERATING_LOG');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adbfad4e-cc09-11ec-820e-0242ac110002', 'org_admin', 'PROJECT_TRACK_REVIEW:READ+CREATE', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc62546-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_USER:READ+CREATE', 'WORKSPACE_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc6642b-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_TEMPLATE:READ+CUSTOM', 'WORKSPACE_TEMPLATE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc6ebec-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_USER:READ', 'WORKSPACE_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc727e6-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_USER:READ+DELETE', 'WORKSPACE_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc761f5-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_USER:READ+EDIT', 'WORKSPACE_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc79cee-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_TEMPLATE:READ', 'WORKSPACE_TEMPLATE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc7daaa-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_TEMPLATE:READ+ISSUE_TEMPLATE', 'WORKSPACE_TEMPLATE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc816bc-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_TEMPLATE:READ+CASE_TEMPLATE', 'WORKSPACE_TEMPLATE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc85846-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_MANAGER:READ', 'WORKSPACE_PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc8994a-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_MANAGER:READ+CREATE', 'WORKSPACE_PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc8dc1e-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_MANAGER:READ+DELETE', 'WORKSPACE_PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc9179e-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_MANAGER:READ+EDIT', 'WORKSPACE_PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc955d0-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_ENVIRONMENT:READ', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc99dfa-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_ENVIRONMENT:READ+COPY', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adc9db32-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_ENVIRONMENT:READ+CREATE', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adca1869-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_ENVIRONMENT:READ+IMPORT', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adca543a-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_ENVIRONMENT:READ+EXPORT', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adca9258-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_ENVIRONMENT:READ+EDIT', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcad0e9-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_PROJECT_ENVIRONMENT:READ+DELETE', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcb0c25-cc09-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_OPERATING_LOG:READ', 'WORKSPACE_OPERATING_LOG');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcb4af9-cc09-11ec-820e-0242ac110002', 'ws_member', 'WORKSPACE_USER:READ', 'WORKSPACE_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcb8941-cc09-11ec-820e-0242ac110002', 'ws_member', 'WORKSPACE_TEMPLATE:READ', 'WORKSPACE_TEMPLATE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcbc304-cc09-11ec-820e-0242ac110002', 'ws_member', 'WORKSPACE_PROJECT_ENVIRONMENT:READ', 'WORKSPACE_PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcbf8ff-cc09-11ec-820e-0242ac110002', 'ws_member', 'WORKSPACE_PROJECT_MANAGER:READ', 'WORKSPACE_PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcc3085-cc09-11ec-820e-0242ac110002', 'ws_member', 'WORKSPACE_OPERATING_LOG:READ', 'WORKSPACE_OPERATING_LOG');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcc678e-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_USER:READ+CREATE', 'PROJECT_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcca406-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_PERFORMANCE_TEST:READ+SCHEDULE', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adccde79-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_PLAN:READ+EDIT', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcd1538-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_PERFORMANCE_TEST:READ+COPY', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcd4d86-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_PERFORMANCE_TEST:READ+EDIT', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcd8bb2-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+DEBUG', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcdc7d7-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ+MOVE_BATCH', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adce0163-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_PLAN:READ+RELEVANCE_OR_CANCEL', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adce3c0a-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+MOCK', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adce7a02-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adceb9a1-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+EDIT_CASE', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcef9a2-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+DELETE_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcf38e0-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_PERFORMANCE_TEST:READ', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcf7bc8-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_REVIEW:READ', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adcfcb3a-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_MANAGER:READ+EDIT', 'PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add0349c-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_PERFORMANCE_REPORT:READ+DELETE', 'PROJECT_PERFORMANCE_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add08bd1-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ+CREATE', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add0d96d-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_ENVIRONMENT:READ+EXPORT', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add11c9e-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_REVIEW:READ+COMMENT', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add156b9-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ+DEBUG', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add1a245-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_REVIEW:READ+DELETE', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add1e6ff-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_PERFORMANCE_TEST:READ+RUN', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add22058-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_USER:READ', 'PROJECT_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add25d50-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+COPY_CASE', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add2991d-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_CASE:READ+EXPORT', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add2d6a7-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_PLAN:READ+CREATE', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add314f1-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+EXPORT_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add34ed9-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+IMPORT_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add38a4e-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ+EDIT', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add3c58d-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_PERFORMANCE_TEST:READ+CREATE', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add3fdaa-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_ENVIRONMENT:READ+EDIT', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add43b7e-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_ENVIRONMENT:READ+COPY', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add4770d-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ+EXPORT_SCENARIO', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add4afb1-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_ENVIRONMENT:READ+IMPORT', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add4e8e6-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_REVIEW:READ+EDIT', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add5278b-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_REVIEW:READ+RELEVANCE_OR_CANCEL', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add56087-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+DELETE_CASE', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add5a1c1-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+CREATE_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add5e6cb-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_PERFORMANCE_TEST:READ+DELETE', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add62581-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_REVIEW:READ+REVIEW', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add6688c-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_PLAN:READ+SCHEDULE', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add6a622-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_MANAGER:READ', 'PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add6e646-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add728bc-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_CASE:READ+DELETE', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add7643e-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_REVIEW:READ+CREATE', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add79bac-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ+RUN', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add7f6b6-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_PLAN:READ+DELETE', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add83476-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_CASE:READ', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add87146-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ+IMPORT_SCENARIO', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add8ba6c-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_REPORT:READ', 'PROJECT_API_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add8f8a0-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_CASE:READ+IMPORT', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add937c7-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ+CREATE_PERFORMANCE', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add979f8-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_USER:READ+EDIT', 'PROJECT_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add9bb03-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_USER:READ+DELETE', 'PROJECT_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('add9fffb-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+EDIT_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adda4311-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_CASE:READ+CREATE', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addb3c14-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ+DELETE', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addb7de4-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_PLAN:READ', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addbca89-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+CREATE_CASE', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addc0e4a-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_REPORT:READ+DELETE', 'PROJECT_API_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addc55fd-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_PERFORMANCE_REPORT:READ', 'PROJECT_PERFORMANCE_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addca4b8-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_CASE:READ+EDIT', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addce6fe-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+CREATE_PERFORMANCE', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addd2748-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ+SCHEDULE', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addd754b-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_SCENARIO:READ+COPY', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adddbee7-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+RUN', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adde0a65-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_ENVIRONMENT:READ', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adde58ad-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_CASE:READ+COPY', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addea41d-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_OPERATING_LOG:READ', 'PROJECT_OPERATING_LOG');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addeeb2d-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_PLAN:READ+CASE_BATCH_EDIT', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addf36b2-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_PLAN:READ+CASE_BATCH_RUN', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('addf8ae4-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_PLAN:READ+CASE_BATCH_DELETE', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade01a7f-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_ENVIRONMENT:READ+CREATE', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade06105-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_ENVIRONMENT:READ+DELETE', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade0a9fa-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+DELETE_CASE', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade0ed9d-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_MANAGER:READ', 'PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade13127-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_PLAN:READ+RELEVANCE_OR_CANCEL', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade1728e-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+CREATE_CASE', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade1b3f5-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_CASE:READ+IMPORT', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade1fc17-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_PLAN:READ+EDIT', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade23391-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_PLAN:READ+CREATE', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade282f5-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+IMPORT_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade2c41c-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ+EXPORT_SCENARIO', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade2f7ae-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_ENVIRONMENT:READ+IMPORT', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade3362b-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_CASE:READ+CREATE', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade37f2c-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_REVIEW:READ+DELETE', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade3d094-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ+IMPORT_SCENARIO', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade4133b-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_PLAN:READ+DELETE', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade44f46-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_CASE:READ+DELETE', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade49731-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+CREATE_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade4d867-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+MOCK', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade5181c-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ+DELETE', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade55204-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_ENVIRONMENT:READ+COPY', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade586c5-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+RUN', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade5c2e0-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ+MOVE_BATCH', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade5fd0f-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_CASE:READ+EXPORT', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade64273-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_PERFORMANCE_REPORT:READ', 'PROJECT_PERFORMANCE_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade68f6e-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_PERFORMANCE_TEST:READ', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade6dde7-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_CASE:READ+EDIT', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade72b1c-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_PERFORMANCE_TEST:READ+CREATE', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade76935-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_USER:READ+DELETE', 'PROJECT_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade7a196-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_PLAN:READ+SCHEDULE', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade7e4f8-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+EXPORT_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade82332-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_ENVIRONMENT:READ+EDIT', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade85ddc-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_PERFORMANCE_REPORT:READ+DELETE', 'PROJECT_PERFORMANCE_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade90b70-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_MANAGER:READ+EDIT', 'PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade94d31-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_REVIEW:READ', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade98eb3-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_PERFORMANCE_TEST:READ+COPY', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ade9cb34-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ+DEBUG', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adea17a5-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_REPORT:READ+DELETE', 'PROJECT_API_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adea5f90-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+EDIT_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adeaba12-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+CREATE_PERFORMANCE', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adeb0c50-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_REVIEW:READ+EDIT', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adeb6494-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+COPY_CASE', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adebbca7-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ+COPY', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adec3b0c-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_ENVIRONMENT:READ', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adecb455-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_REPORT:READ', 'PROJECT_API_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('aded3fb4-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_REVIEW:READ+REVIEW', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adedafe7-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_ENVIRONMENT:READ+EXPORT', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adee236a-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ+SCHEDULE', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adee858f-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_PERFORMANCE_TEST:READ+EDIT', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adeed5af-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_PERFORMANCE_TEST:READ+DELETE', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adef78fc-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_CASE:READ', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf0014a-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ+CREATE_PERFORMANCE', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf0e6d5-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_USER:READ', 'PROJECT_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf18275-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+DELETE_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf2168e-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_REVIEW:READ+COMMENT', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf2aab0-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_CASE:READ+COPY', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf33f2e-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_REVIEW:READ+CREATE', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf3e01a-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_PERFORMANCE_TEST:READ+SCHEDULE', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf46a44-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ+EDIT', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf4f5bc-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ+CREATE', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf58241-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_REVIEW:READ+RELEVANCE_OR_CANCEL', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf60257-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_PERFORMANCE_TEST:READ+RUN', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf683fd-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+DEBUG', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf6ff37-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+EDIT_CASE', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf7623b-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf7bb5b-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf8154d-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_SCENARIO:READ+RUN', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf871c6-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_PLAN:READ', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf8d136-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_OPERATING_LOG:READ', 'PROJECT_OPERATING_LOG');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf92dec-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_PLAN:READ+CASE_BATCH_EDIT', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf99302-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_PLAN:READ+CASE_BATCH_RUN', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adf9f018-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_PLAN:READ+CASE_BATCH_DELETE', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfa968f-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_ENVIRONMENT:READ+CREATE', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfadf7b-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_ENVIRONMENT:READ+DELETE', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfb391d-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_API_DEFINITION:READ', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfb830b-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_PERFORMANCE_TEST:READ', 'PROJECT_PERFORMANCE_TEST');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfcc12a-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_TRACK_REVIEW:READ', 'PROJECT_TRACK_REVIEW');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfd22ac-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_TRACK_PLAN:READ', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfd7194-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_TRACK_CASE:READ', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfdb86b-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_PERFORMANCE_REPORT:READ', 'PROJECT_PERFORMANCE_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfe0aa3-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_API_SCENARIO:READ', 'PROJECT_API_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfe6351-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_ENVIRONMENT:READ', 'PROJECT_ENVIRONMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfea820-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_API_REPORT:READ', 'PROJECT_API_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adfee881-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_USER:READ', 'PROJECT_USER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adff2e06-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_MANAGER:READ', 'PROJECT_MANAGER');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('adff7a2d-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_OPERATING_LOG:READ', 'PROJECT_OPERATING_LOG');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae297e13-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_PLAN:READ+RUN', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae29ee5b-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_ISSUE:READ', 'PROJECT_TRACK_ISSUE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2abe45-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_ISSUE:READ+CREATE', 'PROJECT_TRACK_ISSUE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2b22f5-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_ISSUE:READ+EDIT', 'PROJECT_TRACK_ISSUE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2b7a08-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_ISSUE:READ+DELETE', 'PROJECT_TRACK_ISSUE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2bcf29-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_REPORT:READ', 'PROJECT_TRACK_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2c22b7-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_REPORT:READ+DELETE', 'PROJECT_TRACK_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2c691c-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_REPORT:READ+EXPORT', 'PROJECT_TRACK_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2cabed-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_REPORT:READ+EXPORT', 'PROJECT_API_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2cfbe2-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_PERFORMANCE_REPORT:READ+EXPORT', 'PROJECT_PERFORMANCE_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2d3fd4-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+COPY_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2d7fda-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_PLAN:READ+RUN', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2dbcf2-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_ISSUE:READ', 'PROJECT_TRACK_ISSUE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2df171-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_ISSUE:READ+CREATE', 'PROJECT_TRACK_ISSUE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2e2973-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_ISSUE:READ+EDIT', 'PROJECT_TRACK_ISSUE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2e6609-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_ISSUE:READ+DELETE', 'PROJECT_TRACK_ISSUE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2e9c2e-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_REPORT:READ', 'PROJECT_TRACK_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2ed088-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_REPORT:READ+DELETE', 'PROJECT_TRACK_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2f0552-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_REPORT:READ+EXPORT', 'PROJECT_TRACK_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2f3b71-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_REPORT:READ+EXPORT', 'PROJECT_API_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2f7804-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_PERFORMANCE_REPORT:READ+EXPORT', 'PROJECT_PERFORMANCE_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2fb0a8-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+COPY_API', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae2fe8f6-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_TRACK_ISSUE:READ', 'PROJECT_TRACK_ISSUE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ae302923-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_TRACK_REPORT:READ', 'PROJECT_TRACK_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('afff6b34-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_REPORT_ANALYSIS:READ', 'PROJECT_REPORT_ANALYSIS');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('afffbeb4-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_REPORT_ANALYSIS:READ+EXPORT', 'PROJECT_REPORT_ANALYSIS');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('b00009a2-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_REPORT_ANALYSIS:READ', 'PROJECT_REPORT_ANALYSIS');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('b0007574-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_REPORT_ANALYSIS:READ+EXPORT', 'PROJECT_REPORT_ANALYSIS');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('b000cdb5-cc09-11ec-820e-0242ac110002', 'read_only', 'PROJECT_REPORT_ANALYSIS:READ', 'PROJECT_REPORT_ANALYSIS');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('b1c1bdf6-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_PLAN:READ+COPY', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('b1c21b1a-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_PLAN:READ+COPY', 'PROJECT_TRACK_PLAN');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('b1caeee0-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_TRACK_CASE:READ+RECOVER', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('b1cb3b27-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_API_DEFINITION:READ+TIMING_SYNC', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('b1cb8c7b-cc09-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_PERFORMANCE_REPORT:READ+COMPARE', 'PROJECT_PERFORMANCE_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('b1cbe93e-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_TRACK_CASE:READ+RECOVER', 'PROJECT_TRACK_CASE');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('b1cc3a37-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_API_DEFINITION:READ+TIMING_SYNC', 'PROJECT_API_DEFINITION');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('b1cc8af1-cc09-11ec-820e-0242ac110002', 'project_member', 'PROJECT_PERFORMANCE_REPORT:READ+COMPARE', 'PROJECT_PERFORMANCE_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c09b7c56-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_SCENARIO:READ+DEBUG', 'PROJECT_UI_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c0a19d9a-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_SCENARIO:READ+EXPORT_SCENARIO', 'PROJECT_UI_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c0aa0a3b-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_SCENARIO:READ+CREATE', 'PROJECT_UI_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c0ba40e4-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_SCENARIO:READ+BATCH_COPY', 'PROJECT_UI_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c0ca56d2-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_SCENARIO:READ+COPY', 'PROJECT_UI_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c0d92c9a-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_SCENARIO:READ+DELETE', 'PROJECT_UI_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c0eca858-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_SCENARIO:READ+RUN', 'PROJECT_UI_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c0f2b575-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_SCENARIO:READ', 'PROJECT_UI_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c0fab126-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_SCENARIO:READ+IMPORT_SCENARIO', 'PROJECT_UI_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c106841e-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_SCENARIO:READ+MOVE_BATCH', 'PROJECT_UI_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c116e22d-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_SCENARIO:READ+EDIT', 'PROJECT_UI_SCENARIO');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c124f5cc-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_REPORT:READ+DELETE', 'PROJECT_UI_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c13ae0f8-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_REPORT:READ', 'PROJECT_UI_REPORT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c1488c19-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_ELEMENT:READ', 'PROJECT_UI_ELEMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c14f3bf7-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_ELEMENT:READ+CREATE', 'PROJECT_UI_ELEMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c1587056-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_ELEMENT:READ+DELETE', 'PROJECT_UI_ELEMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c16d4dfd-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_ELEMENT:READ+EDIT', 'PROJECT_UI_ELEMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('c17dfa17-cc24-11ec-820e-0242ac110002', 'project_admin', 'PROJECT_UI_ELEMENT:READ+COPY', 'PROJECT_UI_ELEMENT');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('ce9c3d30-cc24-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_QUOTA:READ', 'WORKSPACE_QUOTA');
insert into user_group_permission (id, group_id, permission_id, module_id) values ('cea2bdae-cc24-11ec-820e-0242ac110002', 'ws_admin', 'WORKSPACE_QUOTA:READ+EDIT', 'WORKSPACE_QUOTA');




insert into supretest.user (id, `name`, email, password, status, create_time, update_time, `language`, last_workspace_id, phone, `source`, last_project_id, create_user, platform_info, selenium_server)
values  ('admin', 'Administrator', 'admin@metersphere.io', 'adba756194d2233480b8636537fa4d26', '1', 1651685762000, 1652258276873, null, 'aad4b966-cbd0-11ec-a0b1-0242ac110002', null, 'LOCAL', 'aad59bdc-cbd0-11ec-a0b1-0242ac110002', null, null, '');


insert into user_group (id, user_id, group_id, source_id, create_time, update_time)
values  ('18734693-fedb-48b6-9d4e-942dc0da0ff8', 'admin', 'project_admin', '90d789f8-5373-4398-bdf3-56367e624e0b', 1651800907926, 1651800907926);
insert into user_group (id, user_id, group_id, source_id, create_time, update_time)
values ('2363e639-992e-48d0-ac53-7a8422f4fef0', 'admin', 'project_admin', '421871a5-2871-4b29-a34c-fcefac6f8e4f', 1651801183184, 1651801183184);
insert into user_group (id, user_id, group_id, source_id, create_time, update_time)
values ('adb373ae-cc09-11ec-820e-0242ac110002', 'admin', 'admin', 'system', 1651685762000, 1651685762000);
insert into user_group (id, user_id, group_id, source_id, create_time, update_time)
values  ('adb3da76-cc09-11ec-820e-0242ac110002', 'admin', 'ws_admin', 'aad4b966-cbd0-11ec-a0b1-0242ac110002', 1651685762000, 1651685762000);
insert into user_group (id, user_id, group_id, source_id, create_time, update_time)
values  ('adb469ef-cc09-11ec-820e-0242ac110002', 'admin', 'project_admin', 'aad59bdc-cbd0-11ec-a0b1-0242ac110002', 1651685762000, 1651685762000);



insert into user_role (id, user_id, role_id, source_id, create_time, update_time)
values  ('aad041d3-cbd0-11ec-a0b1-0242ac110002', 'admin', 'admin', 'system', 1651685762000, 1651685762000);
insert into user_role (id, user_id, role_id, source_id, create_time, update_time)
values  ('aad5eb91-cbd0-11ec-a0b1-0242ac110002', 'admin', 'org_admin', 'aad3cdfc-cbd0-11ec-a0b1-0242ac110002', 1651685762000, 1651685762000);
insert into user_role (id, user_id, role_id, source_id, create_time, update_time)
values  ('aad64339-cbd0-11ec-a0b1-0242ac110002', 'admin', 'test_manager', 'aad4b966-cbd0-11ec-a0b1-0242ac110002', 1651685762000, 1651685762000);

insert into supretest.role (id, `name`, description, `type`, create_time, update_time)
values  ('admin', '系统管理员', null, null, 1651685762000, 1651685762000);
insert into supretest.role (id, `name`, description, `type`, create_time, update_time)
values   ('org_admin', '组织管理员', null, null, 1651685762000, 1651685762000);
insert into supretest.role (id, `name`, description, `type`, create_time, update_time)
values  ('org_member', '组织成员', null, null, 1651685770000, 1651685770000);
insert into supretest.role (id, `name`, description, `type`, create_time, update_time)
values  ('test_manager', '测试经理', null, null, 1651685762000, 1651685762000);
insert into supretest.role (id, `name`, description, `type`, create_time, update_time)
values  ('test_user', '测试人员', null, null, 1651685762000, 1651685762000);
insert into supretest.role (id, `name`, description, `type`, create_time, update_time)
values  ('test_viewer', 'Viewer', null, null, 1651685762000, 1651685762000);


insert into organization (id, `name`, description, create_time, update_time, create_user)
values  ('aad3cdfc-cbd0-11ec-a0b1-0242ac110002', '默认组织', '系统默认创建的组织', 1651685762000, 1651685762000, null);



insert into system_header (`type`, props) values ('api_case_list', '[{"id":"num","label":"ID"},{"id":"name","label":"用例名称"},{"id":"priority","label":"用例等级"},{"id":"path","label":"路径"},{"id":"tags","label":"标签"},{"id":"createUser","label":"创建人"},{"id":"updateTime","label":"最后更新时间"}]');
insert into system_header (`type`, props) values ('api_list', '[{"id":"num","label":"ID"},{"id":"status","label":"接口状态"},{"id":"name","label":"接口名称"},{"id":"method","label":"请求类型"},{"id":"userName","label":"负责人"},{"id":"path","label":"路径"},{"id":"tags","label":"标签"},{"id":"updateTime","label":"最后更新时间"},{"id":"caseTotal","label":"用例数"},{"id":"caseStatus","label":"用例状态"},{"id":"casePassingRate","label":"用例通过率"}]');
insert into system_header (`type`, props) values ('api_scenario_list', '[{"id":"num","label":"ID"},{"id":"name","label":"场景名称"},{"id":"level","label":"用例等级"},{"id":"status","label":"当前状态"},{"id":"tags","label":"标签"},{"id":"principal","label":"负责人"},{"id":"updateTime","label":"最后更新时间"},{"id":"stepTotal","label":"步骤数"},{"id":"lastResult","label":"最后结果"},{"id":"passRate","label":"通过率"}]');
insert into system_header (`type`, props) values ('test_case_list', '[{"id":"num","label":"ID"},{"id":"name","label":"名称"},{"id":"createUser","label":"创建人"},{"id":"maintainer","label":"责任人"},{"id":"priority","label":"用例等级"},{"id":"reviewStatus","label":"评审状态"},{"id":"tags","label":"标签"},{"id":"nodePath","label":"所属模块"},{"id":"updateTime","label":"更新时间"}]');
insert into system_header (`type`, props) values ('test_case_review_case_list', '[{"id":"num","label":"ID"},{"id":"name","label":"名称"},{"id":"priority","label":"用例等级"},{"id":"type","label":"类型"},{"id":"method","label":"测试方式"},{"id":"nodePath","label":"所属模块"},{"id":"projectName","label":"所属项目"},{"id":"reviewerName","label":"评审人"},{"id":"maintainer","label":"责任人"},{"id":"reviewStatus","label":"评审状态"},{"id":"updateTime","label":"更新时间"}]');
insert into system_header (`type`, props) values ('test_case_review_list', '[{"id":"name","label":"评审名称"},{"id":"reviewer","label":"评审人"},{"id":"projectName","label":"所属项目"},{"id":"creatorName","label":"发起人"},{"id":"status","label":"当前状态"},{"id":"createTime","label":"创建时间"},{"id":"endTime","label":"截止时间"},{"id":"tags","label":"标签"}]');
insert into system_header (`type`, props) values ('test_plan_api_case', '[{"id":"num","label":"ID"},{"id":"name","label":"名称"},{"id":"priority","label":"用例等级"},{"id":"path","label":"路径"},{"id":"createUser","label":"创建人"},{"id":"maintainer","label":"责任人"},{"id":"custom","label":"最后更新时间"},{"id":"tags","label":"标签"},{"id":"execResult","label":"执行状态"}]');
insert into system_header (`type`, props) values ('test_plan_function_test_case', '[{"id":"num","label":"ID"},{"id":"name","label":"名称"},{"id":"priority","label":"用例等级"},{"id":"type","label":"类型"},{"id":"tags","label":"标签"},{"id":"nodePath","label":"所属模块"},{"id":"projectName","label":"所属项目"},{"id":"issuesContent","label":"缺陷"},{"id":"executorName","label":"执行人"},{"id":"status","label":"执行结果"},{"id":"updateTime","label":"更新时间"},{"id":"maintainer","label":"责任人"}]');
insert into system_header (`type`, props) values ('test_plan_list', '[{"id":"name","label":"名称"},{"id":"userName","label":"负责人"},{"id":"createUser","label":"创建人"},{"id":"status","label":"当前状态"},{"id":"stage","label":"测试阶段"},{"id":"testRate","label":"测试进度"},{"id":"projectName","label":"所属项目"},{"id":"plannedStartTime","label":"计划开始"},
{"id":"plannedEndTime","label":"计划结束"},{"id":"actualStartTime","label":"实际开始"},
{"id":"actualEndTime","label":"实际结束"},{"id":"tags","label":"标签"},
{"id":"executionTimes","label":"执行次数"},{"id":"passRate","label":"通过率"}]');
insert into system_header (`type`, props) values ('test_plan_load_case', '[{"id":"num","label":"ID"},{"id":"caseName","label":"名称"},{"id":"projectName","label":"所属项目"},{"id":"userName","label":"创建人"},{"id":"createTime","label":"创建时间"},{"id":"status","label":"状态"},{"id":"caseStatus","label":"执行状态"},{"id":"loadReportId","label":"查看报告"}]');
insert into system_header (`type`, props) values ('test_plan_scenario_case', '[{"id":"num","label":"ID"},{"id":"name","label":"名称"},{"id":"level","label":"用例等级"},{"id":"tagNames","label":"标签"},{"id":"userId","label":"创建人"},{"id":"maintainer","label":"责任人"},{"id":"updateTime","label":"最后更新时间"},{"id":"stepTotal","label":"通过"},{"id":"lastResult","label":"失败"},{"id":"passRate","label":"通过率"}]');




insert into issue_template (id, `name`, platform, description, title, `system`, `global`, content, create_time, update_time, create_user, project_id)
values  ('5d7c87d2-f405-4ec1-9a3d-71b514cdfda3', 'default', 'Local', '', '', 1, 1, '', 1651710242000, 1651710242000, null, 'global');
insert into issue_template (id, `name`, platform, description, title, `system`, `global`, content, create_time, update_time, create_user, project_id)
values  ('c7f26296-cf62-4149-a4d2-ce2492729e41', 'JIRA-默认模版', 'Jira', 'JIRA默认模版', '', 1, 1, '', 1651710242000, 1651710242000, null, 'global');
insert into issue_template (id, `name`, platform, description, title, `system`, `global`, content, create_time, update_time, create_user, project_id)
values  ('f2b70824-471e-426e-9219-f82aba6dd560', '禅道-默认模版', 'Zentao', '禅道默认模版', '', 1, 1, '', 1651710242000, 1651710242000, null, 'global');
insert into issue_template (id, `name`, platform, description, title, `system`, `global`, content, create_time, update_time, create_user, project_id)
values  ('f2cd9e48-f136-4528-8249-a649c15aa3a4', 'TAPD-默认模版', 'Tapd', 'TAPD默认模版', '', 1, 1, '', 1651710242000, 1651710242000, null, 'global');


insert into custom_field (id, `name`, scene, `type`, remark, `options`, `system`, `global`, create_time, update_time, create_user, project_id) values ('45f2de57-9d1d-11eb-b418-0242ac120002', '用例状态', 'TEST_CASE', 'select', '', '[{"value":"Prepare", "text":"test_track.case.status_prepare", "system": true},{"value":"Underway", "text":"test_track.case.status_running","system": true},{"value":"Completed", "text":"test_track.case.status_finished", "system": true}]', 1, 1, 1651710242000, 1651710242000, null, 'global');
insert into custom_field (id, `name`, scene, `type`, remark, `options`, `system`, `global`, create_time, update_time, create_user, project_id) values ('46065143-9d1d-11eb-b418-0242ac120002', '责任人', 'TEST_CASE', 'member', '', '[]', 1, 1, 1651710242000, 1651710242000, null, 'global');
insert into custom_field (id, `name`, scene, `type`, remark, `options`, `system`, `global`, create_time, update_time, create_user, project_id) values ('4619cc23-9d1d-11eb-b418-0242ac120002', '用例等级', 'TEST_CASE', 'select', '', '[{"value":"P0", "text":"P0", "system": true},{"value":"P1", "text":"P1","system": true},{"value":"P2", "text":"P2", "system": true},{"value":"P3", "text":"P3", "system": true}]', 1, 1, 1651710242000, 1651710242000, null, 'global');
insert into custom_field (id, `name`, scene, `type`, remark, `options`, `system`, `global`, create_time, update_time, create_user, project_id) values ('a577bc60-75fe-47ec-8aa6-32dca23bf3d6', '处理人', 'ISSUE', 'member', '', '[]', 1, 1, 1651710242000, 1651710242000, null, 'global');
insert into custom_field (id, `name`, scene, `type`, remark, `options`, `system`, `global`, create_time, update_time, create_user, project_id) values ('beb57501-19c8-4ca3-8dfb-2cef7c0ea087', '状态', 'ISSUE', 'select', '', '[{"text":"test_track.issue.status_new","value":"new","system": true},{"text":"test_track.issue.status_resolved","value":"resolved","system": true},{"text":"test_track.issue.status_closed","value":"closed","system": true}]', 1, 1, 1651710242000, 1651710242000, null, 'global');
insert into custom_field (id, `name`, scene, `type`, remark, `options`, `system`, `global`, create_time, update_time, create_user, project_id) values ('d392af07-fdfe-4475-a459-87d59f0b1626', '严重程度', 'ISSUE', 'select', '', '[{"text":"P0","value":"P0","system": true},{"text":"P1","value":"P1","system": true},{"text":"P2","value":"P2","system": true},{"text":"P3","value":"P3","system": true}]', 1, 1, 1651710242000, 1651710242000, null, 'global');
insert into custom_field (id, `name`, scene, `type`, remark, `options`, `system`, `global`, create_time, update_time, create_user, project_id) values ('e392af07-fdfe-4475-a459-87d59f0b1625', '测试阶段', 'PLAN', 'select', '', '[{"text":"test_track.plan.smoke_test","value":"smoke","system": true},{"text":"test_track.plan.system_test","value":"system","system": true},{"text":"test_track.plan.regression_test","value":"regression","system": true}]', 1, 1, 1651711808000, 1651711808000, null, 'global');




INSERT INTO schedule (id,`key`,`type`,`value`,`group`,job,enable,resource_id,user_id,workspace_id,create_time,update_time,project_id,name)
VALUES ('7a23d4db-9909-438d-9e36-58e432c8c4ae','ISSUE_SYNC','CRON','0 0 3 * * ?','ISSUE_SYNC','com.supretest.job.sechedule.IssueSyncJob',1,'system','admin','system',unix_timestamp() * 1000,unix_timestamp() * 1000,'system','ISSUE_SYNC');


insert into system_parameter (param_key, param_value, `type`, sort) values ('base.prometheus.host', 'http://ms-prometheus:9090', 'text', 1);
insert into system_parameter (param_key, param_value, `type`, sort) values ('base.url', 'http://127.0.0.1:8081', 'text', 1);
insert into system_parameter (param_key, param_value, `type`, sort) values ('default.language', 'zh_CN', 'text', 5);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.default.module.id', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.issue', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.issueCount', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.jar.path', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.mock.expectNum', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.scenario.executeTimes', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.scenario.load.test', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.scenario.referenceId', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.scenario.referenceId.reset', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.sort.api.scenario', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.sort.api.test.case', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.sort.api.test.definition', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.sort.load.case', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.sort.plan.api.case', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.sort.plan.api.load', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.sort.plan.api.scenario', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.sort.plan.test.case', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.sort.review.test.case', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('init.sort.test.case', 'over', 'text', null);
insert into system_parameter (param_key, param_value, `type`, sort) values ('metersphere.module.api', 'ENABLE', 'text', 1);
insert into system_parameter (param_key, param_value, `type`, sort) values ('metersphere.module.performance', 'ENABLE', 'text', 1);
insert into system_parameter (param_key, param_value, `type`, sort) values ('metersphere.module.reportStat', 'ENABLE', 'text', 1);
insert into system_parameter (param_key, param_value, `type`, sort) values ('metersphere.module.testTrack', 'ENABLE', 'text', 1);
insert into system_parameter (param_key, param_value, `type`, sort) values ('metersphere.module.ui', 'ENABLE', 'text', 1);
insert into system_parameter (param_key, param_value, `type`, sort) values ('metersphere.module.workstation', 'ENABLE', 'text', 1);
insert into system_parameter (param_key, param_value, `type`, sort) values ('project.jar.limit.size', '1', 'text', 1);
insert into system_parameter (param_key, param_value, `type`, sort) values ('ui.loginImage', null, 'file', 3);
insert into system_parameter (param_key, param_value, `type`, sort) values ('ui.loginLogo', null, 'file', 2);
insert into system_parameter (param_key, param_value, `type`, sort) values ('ui.loginTitle', '', 'text', 4);
insert into system_parameter (param_key, param_value, `type`, sort) values ('ui.logo', null, 'file', 1);
insert into system_parameter (param_key, param_value, `type`, sort) values ('ui.title', '', 'text', 5);



insert into workspace (id, `name`, description, create_time, update_time, create_user)
values  ('aad4b966-cbd0-11ec-a0b1-0242ac110002', '默认工作空间', '系统默认创建的工作空间', 1651685762000, 1651685762000, null);


insert into test_resource_pool (id, `name`, `type`, description, status, create_time, update_time, image, heap, gc_algo, create_user, api, performance, backend_listener)
values  ('aad6f002-cbd0-11ec-a0b1-0242ac110002', 'LOCAL', 'NODE', '系统默认创建的本地资源池', 'VALID', 1651685762000, 1651685762000, null, null, null, null, 1, 1, 1);


insert into test_resource (id, test_resource_pool_id, configuration, status, create_time, update_time)
values  ('aad7b354-cbd0-11ec-a0b1-0242ac110002', 'aad6f002-cbd0-11ec-a0b1-0242ac110002', 'GgC8aAZVAsiNDdnvp4gobdv1iwAvloLCAaeqb7Ms1VaLzW+iXHsFhGg8ZaPEk53W6xA5A6g+UUUxbKvU2s7yZw==', 'VALID', 1651685762000, 1651685762000);


insert into test_case_report_template (id, `name`, workspace_id, content, create_user)
values  ('aad874a8-cbd0-11ec-a0b1-0242ac110002', 'default', null, '{"components": [1,2,3,4,5]}', null);


insert into project_version (id, project_id, `name`, description, status, latest, publish_time, start_time, end_time, create_time, create_user)
values  ('51b8c915-cc0d-11ec-820e-0242ac110002', 'aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'v1.0.0', '系统默认版本', 'open', 1, 1651711811000, 1651711811000, 1651711811000, 1651711811000, 'admin');



insert into custom_field_template (id, field_id, template_id, scene, required, `order`, default_value, custom_data, `key`) values ('56d5f6df-cc0d-11ec-820e-0242ac110002', '46065143-9d1d-11eb-b418-0242ac120002', 'b395d8fe-2ad6-4de7-81d3-2006b53a97c8', 'TEST_CASE', 1, null, '', null, 'A');
insert into custom_field_template (id, field_id, template_id, scene, required, `order`, default_value, custom_data, `key`) values ('56d645a9-cc0d-11ec-820e-0242ac110002', 'd392af07-fdfe-4475-a459-87d59f0b1626', '5d7c87d2-f405-4ec1-9a3d-71b514cdfda3', 'ISSUE', 1, null, '"P0"', null, null);
insert into custom_field_template (id, field_id, template_id, scene, required, `order`, default_value, custom_data, `key`) values ('56d66343-cc0d-11ec-820e-0242ac110002', '4619cc23-9d1d-11eb-b418-0242ac120002', 'b395d8fe-2ad6-4de7-81d3-2006b53a97c8', 'TEST_CASE', 1, null, '"P0"', null, 'B');
insert into custom_field_template (id, field_id, template_id, scene, required, `order`, default_value, custom_data, `key`) values ('56d66cb0-cc0d-11ec-820e-0242ac110002', 'a577bc60-75fe-47ec-8aa6-32dca23bf3d6', '5d7c87d2-f405-4ec1-9a3d-71b514cdfda3', 'ISSUE', 1, null, '', null, null);
insert into custom_field_template (id, field_id, template_id, scene, required, `order`, default_value, custom_data, `key`) values ('56d672af-cc0d-11ec-820e-0242ac110002', 'beb57501-19c8-4ca3-8dfb-2cef7c0ea087', '5d7c87d2-f405-4ec1-9a3d-71b514cdfda3', 'ISSUE', 1, null, '"new"', null, null);
insert into custom_field_template (id, field_id, template_id, scene, required, `order`, default_value, custom_data, `key`) values ('56d67613-cc0d-11ec-820e-0242ac110002', '45f2de57-9d1d-11eb-b418-0242ac120002', 'b395d8fe-2ad6-4de7-81d3-2006b53a97c8', 'TEST_CASE', 1, null, '"Prepare"', null, 'C');



insert into project_application (project_id, `type`, type_value) values ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'TRACK_SHARE_REPORT_TIME', '24H');
insert into project_application (project_id, `type`, type_value) values ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'PERFORMANCE_SHARE_REPORT_TIME', '24H');
insert into project_application (project_id, `type`, type_value) values ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'API_SHARE_REPORT_TIME', '24H');
insert into project_application (project_id, `type`, type_value) values ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'CASE_CUSTOM_NUM', 'false');
insert into project_application (project_id, `type`, type_value) values ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'SCENARIO_CUSTOM_NUM', 'false');
insert into project_application (project_id, `type`, type_value) values ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'CASE_PUBLIC', 'false');
insert into project_application (project_id, `type`, type_value) values ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'MOCK_TCP_OPEN', 'false');
insert into project_application (project_id, `type`, type_value) values ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'CLEAN_TRACK_REPORT', 'false');
insert into project_application (project_id, `type`, type_value) values ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'CLEAN_API_REPORT', 'false');
insert into project_application (project_id, `type`, type_value) values ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'CLEAN_LOAD_REPORT', 'false');
insert into project_application (project_id, `type`, type_value) values ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'URL_REPEATABLE', 'false');
insert into project_application (project_id, `type`, type_value) values ('90d789f8-5373-4398-bdf3-56367e624e0b', 'TRACK_SHARE_REPORT_TIME', '24H');
insert into project_application (project_id, `type`, type_value) values ('90d789f8-5373-4398-bdf3-56367e624e0b', 'PERFORMANCE_SHARE_REPORT_TIME', '24H');
insert into project_application (project_id, `type`, type_value) values ('90d789f8-5373-4398-bdf3-56367e624e0b', 'API_SHARE_REPORT_TIME', '24H');
insert into project_application (project_id, `type`, type_value) values ('90d789f8-5373-4398-bdf3-56367e624e0b', 'CASE_CUSTOM_NUM', 'false');
insert into project_application (project_id, `type`, type_value) values ('421871a5-2871-4b29-a34c-fcefac6f8e4f', 'TRACK_SHARE_REPORT_TIME', '24H');
insert into project_application (project_id, `type`, type_value) values ('421871a5-2871-4b29-a34c-fcefac6f8e4f', 'PERFORMANCE_SHARE_REPORT_TIME', '24H');
insert into project_application (project_id, `type`, type_value) values ('421871a5-2871-4b29-a34c-fcefac6f8e4f', 'API_SHARE_REPORT_TIME', '24H');
insert into project_application (project_id, `type`, type_value) values ('421871a5-2871-4b29-a34c-fcefac6f8e4f', 'CASE_CUSTOM_NUM', 'false');



insert into project (id, workspace_id, `name`, description, create_time, update_time, tapd_id, jira_key, zentao_id, azure_devops_id, case_template_id, issue_template_id, create_user, system_id, azure_filter_id, platform, third_part_template, version_enable, issue_config)
values     ('aad59bdc-cbd0-11ec-a0b1-0242ac110002', 'aad4b966-cbd0-11ec-a0b1-0242ac110002', '默认项目', '系统默认创建的项目', 1651685762000, 1651685762000, null, null, null, null, 'b395d8fe-2ad6-4de7-81d3-2006b53a97c8', null, null, '100001', null, 'Local', 0, 1, null);



-- 系统模板

INSERT INTO test_case_template (id,name,`type`,description,case_name,prerequisite,step_description,expected_result,actual_result,`system`,`global`,create_time,update_time)
VALUES ('b395d8fe-2ad6-4de7-81d3-2006b53a97c8','default','functional','Test case default template.','','','','','',1,1,unix_timestamp() * 1000,unix_timestamp() * 1000);


create table zendao_branch_tree
(
    id                      varchar(50) not null primary key,
    project_id              varchar(50) not null comment 'supretest项目id',
    zendao_product_id       varchar(20) not null comment '禅道产品id',
    zendao_branch_id        varchar(20) default '0' comment '禅道产品分支/平台id',
    zendao_branch_name      varchar(100) comment '禅道产品分支/平台名称',
    zendao_branch_status     varchar(10) comment '禅道产品分支/平台状态',
    zendao_module_id        varchar(20) comment '禅道产品模块id',
    zendao_module_name      varchar(100) comment '禅道产品模块名称',
    zendao_parent_module_id varchar(20) comment '父模块',
    zendao_module_path      varchar(50) comment '模块路径',
    create_time             bigint(13)  not null comment 'Create timestamp',
    update_time             bigint(13)  not null comment 'Update timestamp'
);
x

alter table project add column zentao_product_type  varchar(20) comment '禅道产品类型';

alter table project modify column description  varchar(500);


-- 禅道产品及分支模块同步任务
INSERT INTO schedule (id,`key`,`type`,`value`,`group`,job,enable,resource_id,user_id,workspace_id,create_time,update_time,project_id,name)
VALUES ('8a23d4db-9909-438d-9e36-58e432c8c4ae','ZENDAO_SYNC','CRON','0 0 23 * * ?','ZENDAO_SYNC','com.supretest.job.sechedule.ZendaoProductSyncJob',1,'system','admin','system',unix_timestamp() * 1000,unix_timestamp() * 1000,'system','ZENDAO_SYNC');


create table project_test_plan_progress
(
    id              varchar(50)      null,
    projectId       varchar(50)      null,
    plan_id         varchar(50)      null,
    plan_name       varchar(50)      null,
    pass_rate       double default 0 null comment 'case通过率',
    pass_num        int    default 0 null comment 'case通过数',
    plan_case_total int    default 0 null comment '测试计划用例总数',
    create_time     bigint(13)       null
);

-- 数据概览页面测试计划测试进度同步
INSERT INTO schedule (id,`key`,`type`,`value`,`group`,job,enable,resource_id,user_id,workspace_id,create_time,update_time,project_id,name)
VALUES ('9a0822ea-614a-40b8-bbcb-756c91e1735b','ProjectProgressSchedule','CRON','0 0 1 * * ?','PROJECT_PLAN_PROGRESS','com.supretest.job.sechedule.ProjectWeekPlanProgressJob',1,'ProjectProgressSchedule','admin','system',unix_timestamp() * 1000,unix_timestamp() * 1000, 'system','ProjectProgressSchedule');



alter table test_case_node add column zentao_tree_id varchar(10) comment '禅道产品模块id';

alter table test_case_node add column zentao_branch_id varchar(10) comment '禅道产品分支、平台id';

alter table test_case_node add column zentao_branch_name varchar(50) comment '禅道产品分支、平台名';

alter table api_module add column zentao_tree_id varchar(10) comment '禅道产品模块id';

alter table api_module add column zentao_branch_id varchar(10) comment '禅道产品分支、平台id';

alter table api_module add column zentao_branch_name varchar(50) comment '禅道产品分支、平台名';

alter table api_scenario_module add column zentao_tree_id varchar(10) comment '禅道产品模块id';

alter table api_scenario_module add column zentao_branch_id varchar(10) comment '禅道产品分支、平台id';

alter table api_scenario_module add column zentao_branch_name varchar(50) comment '禅道产品分支、平台名';


create table test_plan_published_module
(
    id                  varchar(50)                 not null comment '' primary key,
    plan_id             varchar(50)                 not null comment 'plan ID ',
    node_id             varchar(64)                 not null comment '测试计划中已上线的模块id',
    create_user         varchar(100)               null  comment '创建者',
    create_time         bigint(13)                  not null comment 'Create timestamp',
    update_time         bigint(13)                  not null comment 'Update timestamp'
);