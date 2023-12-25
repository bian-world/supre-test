export function STEP() {
    let map = new Map([
        ['ALL', init()],
        ['scenario', init()],
        ['HTTPSamplerProxy', getDefaultSamplerMenu()],
        ['DubboSampler', getDefaultSamplerMenu()],
        ['JDBCSampler', getDefaultSamplerMenu()],
        ['TCPSampler', getDefaultSamplerMenu()],
        ['OT_IMPORT', getDefaultSamplerMenu()],
        ['AbstractSampler', getDefaultSamplerMenu()],
        ['IfController', getAll()],
        ['TransactionController', getAll()],
        ['LoopController', getAll()],
        ['ConstantTimer', []],
        ['JSR223Processor', getDefaultSamplerMenu()],
        ['JSR223PreProcessor', []],
        ['JSR223PostProcessor', []],
        ['JDBCPreProcessor', []],
        ['JDBCPostProcessor', []],
        ['Assertions', []],
        ['Extract', []],
        ['JmeterElement', []],
        ['CustomizeReq', getDefaultSamplerMenu()],
        ['MaxSamplerProxy', getDefaultSamplerMenu()],
        ['GenericController', getAll()],
        ['AllSamplerProxy', ['StWebDriverSampler', 'HTTPSamplerProxy', 'DubboSampler', 'JDBCSampler', 'TCPSampler', 'Sampler', 'AbstractSampler', 'JSR223Processor', 'API', 'MsUiCommand']],
        ['DEFINITION', ['HTTPSamplerProxy', 'DubboSampler', 'JDBCSampler', 'TCPSampler']],
        ['ALlSamplerStep', ['JSR223PreProcessor', 'JSR223PostProcessor', 'JDBCPreProcessor', 'JDBCPostProcessor', 'Assertions', 'Extract', 'ConstantTimer']],
        ['AllCanExecType', ['HTTPSamplerProxy', 'DubboSampler', 'JDBCSampler', 'TCPSampler', 'JSR223Processor', 'AbstractSampler']]]);
    return map
}

export const ELEMENT_TYPE = {
    scenario: 'scenario',
    HTTPSamplerProxy: 'HTTPSamplerProxy',
    WebDriverSampler: 'WebDriverSampler',
    OT_IMPORT: 'OT_IMPORT',
    IfController: 'IfController',
    TransactionController: 'TransactionController',
    ConstantTimer: 'ConstantTimer',
    JSR223Processor: 'JSR223Processor',
    JSR223PreProcessor: 'JSR223PreProcessor',
    JSR223PostProcessor: 'JSR223PostProcessor',
    JDBCPostProcessor: 'JDBCPostProcessor',
    JDBCPreProcessor: 'JDBCPreProcessor',
    Assertions: 'Assertions',
    Extract: 'Extract',
    CustomizeReq: 'CustomizeReq',
    LoopController: 'LoopController',
    Plugin: 'Plugin'
}

export const TYPE_TO_C = new Map([
    ['scenario', 'com.supretest.api.dto.definition.request.MsScenario'],
    ['UiScenario', 'com.supretest.ui.dto.definition.request.StUiScenario'],
    ['HTTPSamplerProxy', 'com.supretest.api.dto.definition.request.sampler.MsHTTPSamplerProxy'],
    ['WebDriverSampler', 'com.supretest.api.dto.definition.request.sampler.StWebDriverSampler'],
    ['DubboSampler', 'com.supretest.api.dto.definition.request.sampler.MsDubboSampler'],
    ['JDBCSampler', 'com.supretest.api.dto.definition.request.sampler.MsJDBCSampler'],
    ['TCPSampler', 'com.supretest.api.dto.definition.request.sampler.MsTCPSampler'],
    ['IfController', 'com.supretest.api.dto.definition.request.controller.MsIfController'],
    ['TransactionController', 'com.supretest.api.dto.definition.request.controller.MsTransactionController'],
    ['LoopController', 'com.supretest.api.dto.definition.request.controller.MsLoopController'],
    ['ConstantTimer', 'com.supretest.api.dto.definition.request.timer.MsConstantTimer'],
    ['JSR223Processor', 'com.supretest.api.dto.definition.request.processors.MsJSR223Processor'],
    ['JSR223PreProcessor', 'com.supretest.api.dto.definition.request.processors.pre.MsJSR223PreProcessor'],
    ['JSR223PostProcessor', 'com.supretest.api.dto.definition.request.processors.post.MsJSR223PostProcessor'],
    ['JDBCPreProcessor', 'com.supretest.api.dto.definition.request.processors.pre.MsJDBCPreProcessor'],
    ['JDBCPostProcessor', 'com.supretest.api.dto.definition.request.processors.post.MsJDBCPostProcessor'],
    ['Assertions', 'com.supretest.api.dto.definition.request.assertions.MsAssertions'],
    ['Extract', 'com.supretest.api.dto.definition.request.extract.MsExtract'],
    ['JmeterElement', 'com.supretest.api.dto.definition.request.unknown.MsJmeterElement'],
    ['TestPlan', 'com.supretest.api.dto.definition.request.MsTestPlan'],
    ['ThreadGroup', 'com.supretest.api.dto.definition.request.MsThreadGroup'],
    ['DNSCacheManager', 'com.supretest.api.dto.definition.request.dns.MsDNSCacheManager'],
    ['DebugSampler', 'com.supretest.api.dto.definition.request.sampler.MsDebugSampler'],
    ['AuthManager', 'com.supretest.api.dto.definition.request.auth.MsAuthManager']
])

export const PLUGIN_ELEMENTS = new Map([
    ['menu_post_processors', ['HtmlExtractor', 'JMESPathExtractor', 'JSONPostProcessor', 'RegexExtractor', 'BoundaryExtractor', 'Separator', 'XPath2Extractor', 'XPathExtractor', 'ResultAction', 'DebugPostProcessor', 'BeanShellPostProcessor']],
    ['menu_assertions', ['JSONPathAssertion', 'SizeAssertion', 'JSR223Assertion', 'XPath2Assertion', 'Separator', 'HTMLAssertion', 'JMESPathAssertion', 'MD5HexAssertion', 'SMIMEAssertion', 'XMLSchemaAssertion', 'XMLAssertion', 'XPathAssertion', 'DurationAssertion', 'CompareAssertion', 'BeanShellAssertion']],
    ['menu_listener', ['AbstractVisualizer', 'AbstractListener', 'ViewResultsFullVisualizer', 'SummaryReport', 'StatVisualizer', 'BackendListener', 'Separator', 'JSR223Listener', 'ResultSaver', 'RespTimeGraphVisualizer', 'GraphVisualizer', 'AssertionVisualizer', 'ComparisonVisualizer', 'StatGraphVisualizer', 'Summariser', 'TableVisualizer', 'SimpleDataWriter', 'MailerVisualizer', 'BeanShellListener']],
    ['menu_pre_processors', ['AbstractPostProcessor', 'UserParameters', 'Separator', 'AnchorModifier', 'URLRewritingModifier', 'SampleTimeout', 'RegExUserParameters', 'BeanShellPreProcessor']],
    ['menu_logic_controller', ['GenericController', 'scenario', 'IfController', 'LoopController', 'IfControllerPanel', 'TransactionController', 'LoopControlPanel', 'WhileController', 'Separator', 'ForeachControlPanel', 'IncludeController', 'RunTime', 'CriticalSectionController', 'InterleaveControl', 'OnceOnlyController', 'RecordController', 'LogicController', 'RandomControl', 'RandomOrderController', 'ThroughputController', 'SwitchController', 'ModuleController']],
    ['menu_fragments', ['TestFragmentController']],
    ['menu_non_test_elements', ['ProxyControl', 'HttpMirrorControl', 'GenerateTree', 'PropertyControl']],
    ['menu_generative_controller', ['HTTPSamplerProxy', 'JSR223Processor', 'DubboSampler', 'JDBCSampler', 'TCPSampler', 'Sampler', 'AbstractSampler', 'CustomizeReq', 'HttpTestSample', 'TestAction', 'DebugSampler', 'JSR223Sampler', 'Separator', 'AjpSampler', 'WebDriverSampler', 'AccessLogSampler', 'BeanShellSampler', 'BoltSampler', 'FtpTestSampler', 'GraphQLHTTPSampler', 'JDBCSampler', 'JMSPublisher', 'JMSSampler', 'JMSSubscriber', 'JUnitTestSampler', 'JavaTestSampler', 'LdapExtTestSampler', 'LdapTestSampler', 'SystemSampler', 'SmtpSampler', 'TCPSampler', 'MailReaderSampler']],
    ['menu_threads', ['SetupThreadGroup', 'PostThreadGroup', 'ThreadGroup']],
    ['menu_timer', ['ConstantTimer', 'UniformRandomTimer', 'PreciseThroughputTimer', 'ConstantThroughputTimer', 'Separator', 'JSR223Timer', 'SyncTimer', 'PoissonRandomTimer', 'GaussianRandomTimer', 'BeanShellTimer']],
    ['menu_config_element', ['CSVDataSet', 'HeaderPanel', 'CookiePanel', 'CacheManager', 'HttpDefaults', 'Separator', 'BoltConnectionElement', 'DNSCachePanel', 'FtpConfig', 'AuthPanel', 'DataSourceElement', 'JavaConfig', 'LdapExtConfig', 'LdapConfig', 'TCPConfig', 'KeystoreConfig', 'ArgumentsPanel', 'LoginConfig', 'SimpleConfig', 'CounterConfig', 'RandomVariableConfig']],
])

export function getDefaultSamplerMenu() {
    let array = [];
    array = array.concat(PLUGIN_ELEMENTS.get('menu_assertions'));
    array = array.concat(PLUGIN_ELEMENTS.get('menu_pre_processors'));
    array = array.concat(PLUGIN_ELEMENTS.get('menu_post_processors'));
    array = array.concat(PLUGIN_ELEMENTS.get('menu_config_element'));
    array = array.concat(PLUGIN_ELEMENTS.get('menu_listener'));
    return array;
}

export function init() {
    let allArray = [];
    allArray = allArray.concat(PLUGIN_ELEMENTS.get('menu_generative_controller'));
    allArray = allArray.concat(PLUGIN_ELEMENTS.get('menu_logic_controller'));
    allArray = allArray.concat(['scenario', 'ConstantTimer', 'JSR223Processor']);
    return allArray;
}

export function getAll() {
    let allArray = [];
    allArray = allArray.concat(getDefaultSamplerMenu());
    allArray = allArray.concat(PLUGIN_ELEMENTS.get('menu_logic_controller'));
    allArray = allArray.concat(PLUGIN_ELEMENTS.get('menu_non_test_elements'));
    allArray = allArray.concat(PLUGIN_ELEMENTS.get('menu_generative_controller'));
    allArray = allArray.concat(PLUGIN_ELEMENTS.get('menu_threads'));
    return allArray;
}
