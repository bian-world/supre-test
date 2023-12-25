import TestPlanView from "@/business/components/track/plan/view/TestPlanView";
import  reportListView  from '@/business/components/track/plan/TestPlan';
import  TestPlan  from '@/business/components/track/plan/TestPlan';

export default {
  path: "/plan",
  name: "testplan",
  redirect: "/plan/all",
  components: {
    content: () => import('@/business/components/track/plan/TestPlanHome')
  },
  children: [
    {
      path: ":type",
      name: "testPlan",
      component: TestPlan
    },
    {
      path: "view/:planId",
      name: "planView",
      component: TestPlanView
    },
    {
      path: "view/edit/:caseId",
      name: "planViewEdit",
      component: TestPlanView
    },
    {
      path: 'reportList',
      name: 'testPlanReportList',
      component: reportListView,
    },
  ]
}
