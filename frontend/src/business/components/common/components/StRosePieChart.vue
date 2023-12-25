<template>

  <ms-chart v-if="visible && data.length > 0" :options="options" :autoresize="autoresize" @legendselectchanged="legendChange"
            :style="{'width': width, 'height': height}"/>
  <!--    <div v-if="visible && data.length <= 0" style="height: 300px">-->

  <!--    </div>-->

</template>

<script>

import MsChart from "@/business/components/common/chart/MsChart";

export default {
  name: "StRosePieChart",
  components: {MsChart},
  data() {
    return {
      visible: false,
      options: {
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: '10%',
          bottom: '8%',
          icon: 'pin',
          textStyle: {
            lineHeight: 25,
            rich: {
              a: {
                width: 75,
                fontSize: 12
              }
            },
          },
        },
        series: [
          {
            name: this.name,
            type: 'pie',
            left: 100,
            radius: this.pieSize,
            center: this.canvasPosition,
            roseType: 'radius',
            itemStyle: {
              borderRadius: 0
            },
            label: {
              // padding: [10, 10, 20, 10],
              lineHeight: 35,
              fontSize: 20,
              // fontWeight: 'bold',
              position: 'center',
              color: 'black',
              formatter: function (params) {
                return '';
              },
            },
            labelLine: {
              show: false
            },
            data: this.data
          }
        ]
      }
    }
  },
  props: {
    autoresize:{
      type: Boolean,
      default(){
        return true
      }
    },
    name: {
      type: String,
      default: '数据名称'
    },
    data: {
      type: Array,
      default() {
        return [
          {
            value: 335, name: 'P0',
            itemStyle: {
              color: '#0099ff'
            }
          },
          {
            value: 274, name: 'P1',
            itemStyle: {
              color: '#6666ff'
            }
          },
          {
            value: 211, name: 'P2',
            itemStyle: {
              color: '#00ccff'
            }
          },
          {
            value: 100, name: 'P3',
            itemStyle: {
              color: '#33cccc'
            }
          }
        ]
      }
    },
    height: {
      type: [Number, String],
      default() {
        return 400
      }
    },
    width: {
      type: [Number, String],
      default() {
        return 400
      }
    },
    canvasPosition: {
      type: Array,
      default() {
        return ['20%', '30%']
      }
    },
    pieSize: {
      type: Array,
      default() {
        return ['40%', '80%']
      }
    },
    supreOptions: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  watch: {
    data() {
      this.reload();
    },
    supreOptions(){
      this.setFormatterFunc();
    }
  },
  mounted() {
    this.reload();
  },
  methods: {
    reload() {
      this.setFormatterFunc();
      this.visible = false;
      this.$nextTick(() => {
        this.visible = true;
      });
    },
    setFormatterFunc() {
      let data = this.data;
      let count = 0;
      let title = this.name;
      this.options.series[0].data = data;
      this.data.forEach(item => {
        count += item.value;
      });

      this.options.legend.formatter = (name) => {
        let target = 0;
        this.data.forEach(item => {
          if (item.name == name) {
            target = item.value;
          }
        })
        return `{a|${name} : ${target}}`;
      };

      this.options.series[0].label.formatter = (params) => {
        return count + '个';
      };

      if(Object.keys(this.supreOptions).length !== 0) {
        this.options = {...this.options, legend: {...this.supreOptions.legend}}
      }

    },
    legendChange(params) {
      let count = 0;
      console.log(this.data)
      count = this.data.reduce((prev, item) => {
        if (params.selected[item.name] == true) {
          return prev + item.value
        } else {
          return prev
        }
      }, 0)
      this.options.series[0].label.formatter = (params) => {
        return count + '个';
      };
    }
  }

}
</script>

<style scoped>
</style>
