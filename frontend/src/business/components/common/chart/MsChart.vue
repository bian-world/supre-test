<template>
  <chart
      v-if="loaded"
      :style="{'height': chartHeight, 'width': chartWidth}"
      class="ms-chart"
      :init-options="defaultInitOptions"
      :option="options"
      :theme="themes"
      :group="group"
      @click="onClick"
      @datazoom="datazoom"
      @legendselectchanged="legendselectchanged"
      :watch-shallow="watchShallow"
      :manual-update="manualUpdate"
      :autoresize="autoresize" id="chartsShow"/>
</template>

<script>
export default {
  name: "MsChart",
  props: {
    options: Object,
    theme: [String, Object],
    initOptions: {
      type: Object,
      default() {
        return {
          renderer: 'canvas'
        }
      }
    },
    group: String,
    autoresize: Boolean,
    watchShallow: Boolean,
    manualUpdate: Boolean,
    height: {
      type: [Number, String],
      default() {
        return 400
      }
    },
    width: [Number, String],
  },
  data() {
    return {
      loaded: true,
      defaultInitOptions: this.initOptions,
      themes: {
        "color": [
          "#3fb1e3",
          "#6be6c1",
          "#626c91",
          "#a0a7e6",
          "#c4ebad",
          "#96dee8"
        ],
        "backgroundColor": "rgba(252,252,252,0)",
        "textStyle": {},
        "title": {
          "textStyle": {
            "color": "#666666"
          },
          "subtextStyle": {
            "color": "#999999"
          }
        },
        "line": {
          "itemStyle": {
            "borderWidth": "2"
          },
          "lineStyle": {
            "width": "3"
          },
          "symbolSize": "8",
          "symbol": "emptyCircle",
          "smooth": false
        },
        "radar": {
          "itemStyle": {
            "borderWidth": "2"
          },
          "lineStyle": {
            "width": "3"
          },
          "symbolSize": "8",
          "symbol": "emptyCircle",
          "smooth": false
        },
        "bar": {
          "itemStyle": {
            "barBorderWidth": 0,
            "barBorderColor": "#ccc"
          }
        },
        "pie": {
          "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
          }
        },
        "scatter": {
          "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
          }
        },
        "boxplot": {
          "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
          }
        },
        "parallel": {
          "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
          }
        },
        "sankey": {
          "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
          }
        },
        "funnel": {
          "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
          }
        },
        "gauge": {
          "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
          }
        },
        "candlestick": {
          "itemStyle": {
            "color": "#e6a0d2",
            "color0": "transparent",
            "borderColor": "#e6a0d2",
            "borderColor0": "#3fb1e3",
            "borderWidth": "2"
          }
        },
        "graph": {
          "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
          },
          "lineStyle": {
            "width": "1",
            "color": "#cccccc"
          },
          "symbolSize": "8",
          "symbol": "emptyCircle",
          "smooth": false,
          "color": [
            "#3fb1e3",
            "#6be6c1",
            "#626c91",
            "#a0a7e6",
            "#c4ebad",
            "#96dee8"
          ],
          "label": {
            "color": "#ffffff"
          }
        },
        "map": {
          "itemStyle": {
            "areaColor": "#eeeeee",
            "borderColor": "#aaaaaa",
            "borderWidth": 0.5
          },
          "label": {
            "color": "#ffffff"
          },
          "emphasis": {
            "itemStyle": {
              "areaColor": "rgba(63,177,227,0.25)",
              "borderColor": "#3fb1e3",
              "borderWidth": 1
            },
            "label": {
              "color": "#3fb1e3"
            }
          }
        },
        "geo": {
          "itemStyle": {
            "areaColor": "#eeeeee",
            "borderColor": "#aaaaaa",
            "borderWidth": 0.5
          },
          "label": {
            "color": "#ffffff"
          },
          "emphasis": {
            "itemStyle": {
              "areaColor": "rgba(63,177,227,0.25)",
              "borderColor": "#3fb1e3",
              "borderWidth": 1
            },
            "label": {
              "color": "#3fb1e3"
            }
          }
        },
        "categoryAxis": {
          "axisLine": {
            "show": true,
            "lineStyle": {
              "color": "#cccccc"
            }
          },
          "axisTick": {
            "show": false,
            "lineStyle": {
              "color": "#333"
            }
          },
          "axisLabel": {
            "show": true,
            "color": "#999999"
          },
          "splitLine": {
            "show": true,
            "lineStyle": {
              "color": [
                "#eeeeee"
              ]
            }
          },
          "splitArea": {
            "show": false,
            "areaStyle": {
              "color": [
                "rgba(250,250,250,0.05)",
                "rgba(200,200,200,0.02)"
              ]
            }
          }
        },
        "valueAxis": {
          "axisLine": {
            "show": true,
            "lineStyle": {
              "color": "#cccccc"
            }
          },
          "axisTick": {
            "show": false,
            "lineStyle": {
              "color": "#333"
            }
          },
          "axisLabel": {
            "show": true,
            "color": "#999999"
          },
          "splitLine": {
            "show": true,
            "lineStyle": {
              "color": [
                "#eeeeee"
              ]
            }
          },
          "splitArea": {
            "show": false,
            "areaStyle": {
              "color": [
                "rgba(250,250,250,0.05)",
                "rgba(200,200,200,0.02)"
              ]
            }
          }
        },
        "logAxis": {
          "axisLine": {
            "show": true,
            "lineStyle": {
              "color": "#cccccc"
            }
          },
          "axisTick": {
            "show": false,
            "lineStyle": {
              "color": "#333"
            }
          },
          "axisLabel": {
            "show": true,
            "color": "#999999"
          },
          "splitLine": {
            "show": true,
            "lineStyle": {
              "color": [
                "#eeeeee"
              ]
            }
          },
          "splitArea": {
            "show": false,
            "areaStyle": {
              "color": [
                "rgba(250,250,250,0.05)",
                "rgba(200,200,200,0.02)"
              ]
            }
          }
        },
        "timeAxis": {
          "axisLine": {
            "show": true,
            "lineStyle": {
              "color": "#cccccc"
            }
          },
          "axisTick": {
            "show": false,
            "lineStyle": {
              "color": "#333"
            }
          },
          "axisLabel": {
            "show": true,
            "color": "#999999"
          },
          "splitLine": {
            "show": true,
            "lineStyle": {
              "color": [
                "#eeeeee"
              ]
            }
          },
          "splitArea": {
            "show": false,
            "areaStyle": {
              "color": [
                "rgba(250,250,250,0.05)",
                "rgba(200,200,200,0.02)"
              ]
            }
          }
        },
        "toolbox": {
          "iconStyle": {
            "borderColor": "#999999"
          },
          "emphasis": {
            "iconStyle": {
              "borderColor": "#666666"
            }
          }
        },
        "legend": {
          "textStyle": {
            "color": "#999999"
          }
        },
        "tooltip": {
          "axisPointer": {
            "lineStyle": {
              "color": "#cccccc",
              "width": 1
            },
            "crossStyle": {
              "color": "#cccccc",
              "width": 1
            }
          }
        },
        "timeline": {
          "lineStyle": {
            "color": "#626c91",
            "width": 1
          },
          "itemStyle": {
            "color": "#626c91",
            "borderWidth": 1
          },
          "controlStyle": {
            "color": "#626c91",
            "borderColor": "#626c91",
            "borderWidth": 0.5
          },
          "checkpointStyle": {
            "color": "#3fb1e3",
            "borderColor": "#3fb1e3"
          },
          "label": {
            "color": "#626c91"
          },
          "emphasis": {
            "itemStyle": {
              "color": "#626c91"
            },
            "controlStyle": {
              "color": "#626c91",
              "borderColor": "#626c91",
              "borderWidth": 0.5
            },
            "label": {
              "color": "#626c91"
            }
          }
        },
        "visualMap": {
          "color": [
            "#2a99c9",
            "#afe8ff"
          ]
        },
        "dataZoom": {
          "backgroundColor": "rgba(255,255,255,0)",
          "dataBackgroundColor": "rgba(222,222,222,1)",
          "fillerColor": "rgba(114,230,212,0.25)",
          "handleColor": "#cccccc",
          "handleSize": "100%",
          "textStyle": {
            "color": "#999999"
          }
        },
        "markPoint": {
          "label": {
            "color": "#ffffff"
          },
          "emphasis": {
            "label": {
              "color": "#ffffff"
            }
          }
        }
      }
    };
  },
  computed: {
    chartHeight() {
      if (this.height.indexOf) {
        return this.height;
      } else {
        return this.height + 'px';
      }
    },
    chartWidth() {
      if (!this.width) {
        return this.width;
      }
      if (this.width.indexOf) {
        return this.width;
      } else {
        return this.width + 'px';
      }
    }
  },
  mounted() {
    // this.defaultInitOptions = this.defaultInitOptions || {};
    // 默认渲染svg
    // BUG: 渲染svg之后 图上的legend 太多会不显示
    // if (!this.defaultInitOptions.renderer) {
    // this.defaultInitOptions.renderer = 'svg';
    // }
  },
  methods: {
    onClick(params) {
      this.$emit('onClick', params.data);
    },
    reload() {
      this.loaded = false;
      this.$nextTick(() => {
        this.loaded = true;
      })
    },
    exportCharts(fileName, type) {
      if (document.getElementsByClassName('ms-chart')) {
        let chartsCanvas = document.getElementsByClassName('ms-chart')[0].querySelectorAll('canvas')[0];
        let mime = 'image/png';
        if (chartsCanvas) {
          // toDataURL()是canvas对象的一种方法，用于将canvas对象转换为base64位编码
          let imageUrl = chartsCanvas && chartsCanvas.toDataURL("image/png");
          if (navigator.userAgent.indexOf('Trident') > -1) {
            // IE11
            let arr = imageUrl.split(',');
            // atob() 函数对已经使用base64编码编码的数据字符串进行解码
            let baseStr = atob(arr[1]);
            let baseStrLen = baseStr.length;
            // Uint8Array, 开辟 8 位无符号整数值的类型化数组。内容将初始化为 0
            let u8arr = new Uint8Array(baseStrLen);
            while (baseStrLen--) {
              // charCodeAt() 方法可返回指定位置的字符的 Unicode 编码
              u8arr[baseStrLen] = baseStr.charCodeAt(baseStrLen);
            }
            //  msSaveOrOpenBlob 方法允许用户在客户端上保存文件，方法如同从 Internet 下载文件，这是此类文件保存到“下载”文件夹的原因
            window.navigator.msSaveOrOpenBlob(new Blob([u8arr], {type: mime}), fileName + '.' + type.toLowerCase());
          } else {
            // 其他浏览器
            let $a = document.createElement('a');
            $a.setAttribute('href', imageUrl);
            $a.setAttribute('download', fileName + '.' + type.toLowerCase());
            $a.click();
          }
        }
      }
    },
    datazoom(params) {
      this.$emit('datazoom', params);
    },
    legendselectchanged(params){
      this.$emit('legendselectchanged', params);
    }
  }
};
</script>

<style scoped>

</style>
