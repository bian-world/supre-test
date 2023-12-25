export default {
  path: "/dashboard",
  name: "DashBoard",

  components: {
    content: () => import('@/business/components/dashboard/DashBoard')
  },
  children: [

  ]
}
