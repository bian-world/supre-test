<template>
  <div class="main">
    <el-upload
      :class="{ hide: hideUploadEdit }"
      action=""
      :on-change="getImageFile"
      :on-remove="handlePicRemove"
      :on-preview="handlePicPreview"
      :limit="1"
      list-type="picture-card"
      :auto-upload="false"
    >
      <i class="el-icon-plus"></i>
    </el-upload>

    <el-button @click="showImage">点这里</el-button>
    <div class="martop">下面是用来回显的图片</div>

    <el-image :src="img" class="imgwidth">
      <div slot="error" class="image-slot">
        <i class="el-icon-picture-outline"></i>
      </div>
    </el-image>

    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="" />
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "ImageUpload",
  data() {
    return {
      imageUrl: "",
      fileList: [],
      dialogImageUrl: "",
      dialogVisible: false,
      hideUploadEdit: false, // 是否隐藏上传按钮
      img:"",
    };
  },
methods: {
  // 获取图片信息
  getImageFile(file, fileList) {
    console.log("fileList", fileList);
    this.getImageBase64(file.raw).then((res) => {
      console.log("res", res);
      this.imageUrl = res;
    });
    // 大于1张隐藏
    this.hideUploadEdit = fileList.length >= 1;
  },
  //转换成base64方法
  getImageBase64(file) {
    return new Promise(function (resolve, reject) {
      let newImagereader = new FileReader();
      let imgInfo = "";
      newImagereader.readAsDataURL(file);
      newImagereader.onload = function () {
        imgInfo = newImagereader.result;
      };
      newImagereader.onerror = function (error) {
        reject(error);
      };
      newImagereader.onloadend = function () {
        resolve(imgInfo);
      };
    });
  },
  //删除
  handlePicRemove(file, fileList) {
    this.hideUploadEdit = fileList.length >= 1;
    this.imageUrl = ""
  },
  //预览
  handlePicPreview(file) {
    console.log("file", file);
    this.dialogImageUrl = file.url;
    this.dialogVisible = true;
  },
  showImage(){
    let img = document.createElement('img');
    img.setAttribute( 'src', "data:image/png;base64,"
    );
    // 在新窗口打开
    let newWindow = window.open();	// 注意：短时间内重复调用会被浏览器拦截
    newWindow .document.write(img.outerHTML);
// this.img = img
  }
},
};

</script>

<style scoped>

.main {
  width: 400px;
  height: 500px;
}
.martop {
  margin-top: 20px;
}
.imgwidth {
  width: 100px;
  height: 100px;
}
.image-slot {
  width: 100px;
  height: 100px;
}
.image-slot i {
  font-size: 100px;
}
.hide .el-upload--picture-card {
  display: none;
}


</style>
