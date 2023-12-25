
export default {
  path: "/ui",
  name: "ui",
  redirect: "/ui/page",
  components: {
    content: () => import('@/business/components/ui/UiTest')
  },
  children: [
    {
      path: "page",
      name: "page",
      component: () => import('@/business/components/ui/uipage/UiPage'),
    },
    {
      path: "automation",
      name: "automation",
      component: () => import('@/business/components/ui/uiautomation/UiAutomation'),
    },
    {
      path: "automation/report",
      name: "ApiReportList",
      component: () => import('@/business/components/api/automation/report/ApiReportList'),
    },
    {
      path: "automation/report/view/:reportId",
      name: "ApiReportView",
      component: () => import('@/business/components/api/automation/report/ApiReportView'),

    },
    {
      path: "definition/:redirectID?/:dataType?/:dataSelectRange?/:projectId?/:type?/:workspaceId?",
      name: "ApiDefinition",
      component: () => import('@/business/components/api/definition/ApiDefinition'),
    },
    {
      path: "automation/:redirectID?/:dataType?/:dataSelectRange?/:projectId?/:workspaceId?",
      name: "ApiAutomation",
      component: () => import('@/business/components/api/automation/ApiAutomation'),
    },
    {
      path: 'monitor/view',
      name: 'ApiMonitor',
      component: () => import('@/business/components/api/monitor/ApiMonitor'),
    },
    {
      path: 'definition/edit/:definitionId',
      name: 'editCompleteContainer',
      component: () => import('@/business/components/api/definition/ApiDefinition'),
    },
  ]
};
