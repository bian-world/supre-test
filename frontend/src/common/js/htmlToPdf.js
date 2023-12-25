import html2Canvas from 'html2canvas'
import JsPDF from 'jspdf'

export default {
  install(Vue, options) {
    // id-导出pdf的div容器；title-导出文件标题
      Vue.prototype.htmlToPdf = (id, title) => {
        const element = document.getElementById(`${id}`)
        const opts = {
          // scale: 4, // 缩放比例，提高生成图片清晰度
          useCORS: true, // 允许加载跨域的图片
          // allowTaint: false, // 允许图片跨域，和 useCORS 二者不可共同使用
          // tainttest: true, // 检测每张图片已经加载完成
          logging: true,// 日志开关，发布的时候记得改成 false
          scrollY: 0,
          scrollX: 0,
          width: element.clientWidth, //dom 原始宽度
          height: element.clientHeight,
          dpi: window.devicePixelRatio,
          scale:1

        }

        html2Canvas(element, opts)
          .then((canvas) => {
            console.log(canvas)
            const contentWidth = canvas.width
            const contentHeight = canvas.height
            // 一页pdf显示html页面生成的canvas高度;
            const pageHeight = (contentWidth / 1190.55) * 841.89
            // 未生成pdf的html页面高度
            let leftHeight = contentHeight
            // 页面偏移
            let position = 0
            // a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
            const imgWidth = 1190.55
            const imgHeight = (1190.55 / contentWidth) * contentHeight
            const pageData = canvas.toDataURL('image/jpeg', 1.0)
            var saveFile = function(data, filename){
              var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
              save_link.href = data;
              save_link.download = filename;

              var event = document.createEvent('MouseEvents');
              event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
              save_link.dispatchEvent(event);
            };

// 下载后的文件名
//             var filename = '二维码 .' + type;
// download
            saveFile(pageData,'file.jpeg');
            // return pageData
          //   console.log(pageData)
          //   // a4纸纵向，一般默认使用；new JsPDF('landscape'); 横向页面
          //   const PDF = new JsPDF('landscape', 'pt', 'a3')
          //
          //   // 当内容未超过pdf一页显示的范围，无需分页
          //
          //   console.log(canvas.width)
          //   if (leftHeight < pageHeight) {
          //     // addImage(pageData, 'JPEG', 左，上，宽度，高度)设置
          //     // PDF.addImage('', 'JPEG', 0, 0, 0, 0)
          //     PDF.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight)
          //   } else {
          //     // 超过一页时，分页打印（每页高度841.89）
          //     while (leftHeight > 0) {
          //       PDF.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
          //       leftHeight -= pageHeight
          //       position -= 841.89
          //       if (leftHeight > 0) {
          //         PDF.addPage()
          //       }
          //     }
          //   }
          //   PDF.save(title + '.pdf')
          // })
          // .catch((error) => {
          //   console.log('打印失败', error)
          })
      }
    }




}
