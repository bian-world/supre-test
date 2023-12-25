<template>
 <div id="building">
  <div class="container" v-if="ready">
    <el-row type="flex">
      <el-col :span="17"  >
<!--         <img class="image" src="../assets/supretest-login.png" alt="" style="padding-top: 200px">-->
      </el-col>
      <div class="divider"/>
      <el-col :span="7">
        <div class="content" >
          <div class="title">
            <div class="welcome">
              <img class="login-image" src="../assets/welcome.png" alt="">
<!--              <span >{{ loginTitle }}</span>-->
            </div>

          </div>

          <div class="sub-content" style="margin-bottom: 20%" >

            <el-form :model="form" :rules="rules" ref="form" >
              <div class="login">
                <span >账号登录</span>
              </div>
<!--              <el-form-item v-slot:default size="mini" >-->
<!--                <el-radio-group v-model="form.authenticate" @change="redirectAuth(form.authenticate)">-->
<!--                  <el-radio label="LDAP" size="mini" v-if="openLdap">LDAP</el-radio>-->
<!--                  <el-radio label="LOCAL" size="mini" v-if="openLdap">{{ $t('login.normal_Login') }}</el-radio>-->
<!--                  <el-radio :label="auth.id" size="mini" v-for="auth in authSources" :key="auth.id">{{ auth.type }}-->
<!--                    {{ auth.name }}-->
<!--                  </el-radio>-->
<!--                </el-radio-group>-->
<!--              </el-form-item>-->
              <el-form-item prop="username" >
                <el-input v-model="form.username"
                          :placeholder="$t('commons.login_username')"
                          style="width:100%;"
                          autofocus
                          autocomplete="off">
                          <i slot="prefix" class="el-icon-user"></i>
                  </el-input>
              </el-form-item>
              <el-form-item prop="password">

                <el-input v-model="form.password" :placeholder="$t('commons.login_password')" show-password
                          autocomplete="off"
                          maxlength="30" style="width:100%" show-word-limit>
                  <i slot="prefix" class="el-icon-key"></i>
                </el-input>

              </el-form-item>
            </el-form>
          </div>
          <div class="btn" style="width: 85%; align: center; margin-bottom: 50px">
            <el-button type="primary"  @click="submit('form')" style="background-color: #0099ff; width: 100%">
              {{ $t('commons.login') }}
            </el-button>
          </div>
          <div class="msg">
            {{ msg }}
          </div>
        </div>
<!--        <div class="content">-->
<!--          <img class="login-image" :src="'/display/file/loginImage'" alt="">-->
<!--        </div>-->
      </el-col>

    </el-row>

  </div>
 </div>
</template>

<script>
import {getCurrentUserId, hasPermissions, publicKeyEncrypt, saveLocalStorage} from '@/common/js/utils';
import {CURRENT_LANGUAGE, DEFAULT_LANGUAGE, PRIMARY_COLOR} from "@/common/js/constants";

const requireComponent = require.context('@/business/components/xpack/', true, /\.vue$/);
const display = requireComponent.keys().length > 0 ? requireComponent("./display/Display.vue") : {};
const auth = requireComponent.keys().length > 0 ? requireComponent("./auth/Auth.vue") : {};
const license = requireComponent.keys().length > 0 ? requireComponent("./license/LicenseMessage.vue") : null;

export default {
  name: "Login",
  data() {
    return {
      result: {},
      form: {
        username: '',
        password: '',
        authenticate: 'LOCAL'
      },
      rules: this.getDefaultRules(),
      msg: '',
      ready: false,
      openLdap: false,
      authSources: [],
      loginUrl: 'signin',
      lastUser: null,
      loginTitle: this.$t('commons.welcome')
    };
  },
  beforeCreate() {
    this.$get('/system/theme', res => {
      this.color = res.data ? res.data : PRIMARY_COLOR;
      document.body.style.setProperty('--primary_color', this.color);
    });
    // 保存当前站点url
    this.$get('/system/save/baseurl?baseurl=' + window.location.origin)
      .then(() => {
      })
      .catch(() => {
      });

    this.result = this.$get("/isLogin").then(response => {

      if (display.default !== undefined) {
        display.default.showLogin(this);
      }

      if (auth.default !== undefined) {
        auth.default.getAuthSources(this);
      }

      if (!response.data.success) {
        this.ready = true;
        // 保存公钥
        localStorage.setItem("publicKey", response.data.message);
        let lang = localStorage.getItem(CURRENT_LANGUAGE);
        if (lang) {
          this.$setLang(lang);
          this.rules = this.getDefaultRules();
        }
      } else {
        let user = response.data.data;
        saveLocalStorage(response.data);
        this.getLanguage(user.language);
        window.location.href = "/";
      }
    });
    this.$get("/ldap/open", response => {
      this.openLdap = response.data;
      if (this.openLdap) {
        this.form.authenticate = 'LDAP';
      }
    });
  },
  created: function () {
    // 主页添加键盘事件,注意,不能直接在焦点事件上添加回车
    document.addEventListener("keydown", this.watchEnter);
    //
    if (license.default) {
      license.default.valid(this);
    }

    // 上次登录的用户
    this.lastUser = sessionStorage.getItem('lastUser');
  },

  destroyed() {
    //移除监听回车按键
    document.removeEventListener("keydown", this.watchEnter);
  },
  methods: {
    //监听回车按钮事件
    watchEnter(e) {
      let keyNum = e.which; //获取被按下的键值
      //判断如果用户按下了回车键（keycody=13）
      if (keyNum === 13) {
        // 按下回车按钮要做的事
        this.submit('form');
      }
    },
    submit(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          switch (this.form.authenticate) {
            case "LOCAL":
              this.loginUrl = "/signin";
              this.doLogin();
              break;
            case "LDAP":
              this.loginUrl = "/ldap/signin";
              this.doLogin();
              break;
            default:
              this.loginUrl = "/sso/signin";
              this.doLogin();
          }
        } else {
          return false;
        }
      });
    },
    doLogin() {
      // 删除缓存
      sessionStorage.removeItem('changePassword');
      let publicKey = localStorage.getItem("publicKey");

      let form = {
        username: publicKeyEncrypt(this.form.username, publicKey),
        password: publicKeyEncrypt(this.form.password, publicKey),
        authenticate: this.form.authenticate
      };

      this.result = this.$post(this.loginUrl, form, response => {
        saveLocalStorage(response);
        sessionStorage.setItem('loginSuccess', 'true');
        sessionStorage.setItem('changePassword', response.message);
        this.getLanguage(response.data.language);
        // 检查登录用户的权限
        this.checkRedirectUrl();
      });
    },
    getLanguage(language) {
      if (!language) {
        this.$get("language", response => {
          language = response.data;
          localStorage.setItem(DEFAULT_LANGUAGE, language);
          window.location.href = "/";
        });
      } else {
        window.location.href = "/";
      }
    },
    redirectAuth(authId) {
      if (auth.default) {
        auth.default.redirectAuth(this, authId);
      }
    },
    getDefaultRules() { // 设置完语言要重新赋值
      return {
        username: [
          {required: true, message: this.$t('commons.input_login_username'), trigger: 'change'},
        ],
        password: [
          {required: true, message: this.$t('commons.input_password'), trigger: 'change'},
          {min: 6, max: 30, message: this.$t('commons.input_limit', [6, 30]), trigger: 'blur'}
        ]
      };
    },
    checkRedirectUrl() {
      if (this.lastUser === getCurrentUserId()) {
        return;
      }
      let redirectUrl = '/';
      if (hasPermissions('PROJECT_USER:READ', 'PROJECT_ENVIRONMENT:READ', 'PROJECT_OPERATING_LOG:READ', 'PROJECT_FILE:READ+JAR', 'PROJECT_FILE:READ+FILE', 'PROJECT_CUSTOM_CODE:READ', 'PROJECT_MESSAGE:READ', 'PROJECT_TEMPLATE:READ')) {
        redirectUrl = '/dashboard';
      } else if (hasPermissions('WORKSPACE_SERVICE:READ', 'WORKSPACE_USER:READ', 'WORKSPACE_PROJECT_MANAGER:READ', 'WORKSPACE_PROJECT_ENVIRONMENT:READ', 'WORKSPACE_OPERATING_LOG:READ')) {
        redirectUrl = '/setting/project/:type';
      } else if (hasPermissions('SYSTEM_USER:READ', 'SYSTEM_WORKSPACE:READ', 'SYSTEM_GROUP:READ', 'SYSTEM_TEST_POOL:READ', 'SYSTEM_SETTING:READ', 'SYSTEM_AUTH:READ', 'SYSTEM_QUOTA:READ', 'SYSTEM_OPERATING_LOG:READ')) {
        redirectUrl = '/setting';
      } else {
        redirectUrl = '/';
      }

      sessionStorage.setItem('redirectUrl', redirectUrl);
    }
  }
};
</script>

<style scoped>
.container {
  width: 1200px;
  height: 730px;
  margin: 0 auto;
}

#building{
  background:url("../assets/background.png");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}
.content {
  margin-top: 20%;
  width: 90%;
  background: rgba(255,255,255,0.9);
}

.sub-content {
  width:85%;
  margin-left: 8%;
}

.el-row--flex {
  height: 730px;
  margin-top: calc((100vh - 800px) / 2);
}

.el-col:nth-child(3) {
  align-items: center;
  display: flex;
}

.title img {
  width: 180px;
  max-height: 60px;
  margin-top: 50px;
}

.title-img {
  letter-spacing: 0;
  text-align: center;
}

.login-image {
  height: 40px;
  width: 160px;
  display: block;
}

.welcome {
  margin-top: 70px;
  margin-left: 30px;
  font-size: 30px;
  color: #0099ff;
  line-height: 10px;
  text-align: left;
  font-weight:bold
}

.login {
  margin-top: 50px;
  margin-left: 0px;
  margin-bottom: 20px;
  font-size: 15px;
  color: gray;
  line-height: 10px;
  text-align: left;
  font-weight:bold
}

.form, .btn {
  padding: 0;
  width: 100%;
  margin: auto;
}

.btn > .submit {
  width: 100px;
  margin-left: 150px;
  margin-bottom: 70px;
  border-color: var(--primary_color);
  background-color: var(--primary_color);
}

.btn > .submit:hover {
  border-color: var(--primary_color);
  background-color: var(--primary_color);
}

.btn > .submit:active {
  border-color: var(--primary_color);
  background-color: var(--primary_color);
}

.el-form-item:first-child {
  margin-top: 60px;
}

/deep/ .el-radio__input.is-checked .el-radio__inner {
  background-color: var(--primary_color);
  background: var(--primary_color);
  border-color: var(--primary_color);
}

/deep/ .el-radio__input.is-checked + .el-radio__label {
  color: var(--primary_color);
}


/deep/input::-webkit-input-placeholder {
  color: #bfc3cd;
  font-size: 6px;
}

/deep/ .el-input__inner {
  /*border-radius: 70px !important;*/
  background-color: transparent  !important;
  border-color: #A29FA4 !important;
  /*谷歌浏览器默认填充的颜色无法替换，使用下列样式填充*/
  /*box-shadow: inset 0 0 0 1000px rgba(255,255,255,0)  !important;*/
}

.el-input, .el-button {
  width: 443px;
}

/deep/ .el-input__inner:focus {
  border: 0px solid var(--primary_color) !important;
}

.divider {
  border: 1px solid #FFFFFF;
  opacity:0;
  height: 480px;
  margin: 165px 0px;
}


body {
  font-family: -apple-system, BlinkMacSystemFont, "Neue Haas Grotesk Text Pro", "Arial Nova", "Segoe UI", "Helvetica Neue", ".PingFang SC", "PingFang SC", "Source Han Sans SC", "Noto Sans CJK SC", "Source Han Sans CN", "Noto Sans SC", "Source Han Sans TC", "Noto Sans CJK TC", "Hiragino Sans GB", sans-serif;
  font-size: 14px;
  /*background-color: #F5F5F5;*/
  line-height: 26px;
  color: #2B415C;
  -webkit-font-smoothing: antialiased;
  margin: 0;
  height: auto;
}

/deep/ .el-input__inner {
  border-radius: 0;
  border: 0px;
  height: 30px;
}

/deep/ .el-input {
  background-color: #edf0fc;
}

/deep/ .el-form-item__content{
  line-height: 30px
}
</style>

