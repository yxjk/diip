<template>
    <div class="bg">
        <div class="login-wrap animated flipInY" v-title="'统一调度平台'">
            <h3>{{$t('m.login.introduction')}}</h3>
            <el-form ref="form" :model="form" :rules="rules" label-width="0px">
                <el-form-item prop="name">
                    <el-input :placeholder="$t('m.login.name_holder')"  v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :placeholder="$t('m.login.password_holder')" @keyup.enter.native="Login('form')" v-model="form.password" type="password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-row type="flex" justify="space-between">
                        <el-checkbox v-model="isMemery" style="color:#eee">{{$t('m.login.remember')}}</el-checkbox>
                        <a href="" @click.prevent="openMsg"  style="color:#eee">{{$t('m.login.forget')}}</a>
                    </el-row>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="Login('form')">{{$t('m.login.button')}}</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!-- 粒子漂浮物 -->
        <vue-particles
            color="#fff"
            :particleOpacity="0.7"
            :particlesNumber="30"
            shapeType="star"
            :particleSize="5"
            linesColor="#fff"
            :linesWidth="2"
            :lineLinked="true"
            :lineOpacity="0.4"
            :linesDistance="150"
            :moveSpeed="3"
            :hoverEffect="true"
            hoverMode="grab"
            :clickEffect="true"
            clickMode="push"
        >
        </vue-particles>
    </div>
</template>
<script>
export default {
  name: "signin",
  data() {
    return {
      form: {
        name: localStorage.userInfo,
        password: localStorage.passwordInfo
      },
      isMemery: false,
      rules: {
        name: [
          {
            required: true,
            message: this.$t('m.login.name_tip'),
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: this.$t('m.login.password_tip'),
            trigger: "blur"
          }
        ]
      }
    };
  },
  created() {
  },
  methods: {
    Login(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios({
            url: "/api/login",
            method: "POST",
            data: {
              userLogin: this.form.name,
              userPwd: this.form.password
            }
          }).then(res => {
            if(res.status!="200"){
              this.$message.error("服务异常["+res.status+"]，请重试");
              return false;
            }
            if(res.data.status!="S"){
              this.$message.error(res.data.msg);
              return false;
            }
            localStorage.userLogin = res.data.data.userLogin;
            localStorage.userId = res.data.data.userId;
            localStorage.token = res.data.data.token;
            this.getMenu();
          }).catch(error=> {
            this.$message.error(error);
          });
        } else {
          return false;
        }
      });
    },
    getMenu() {
      this.$axios({
        url: "/api/getMenu",
        method: "GET"
      }).then(res => {
        if(res.status!="200"){
          this.$message.error("服务异常，请重试");
          return false;
        }
        // 提取菜单数组，交给本地存储
        let menu = res.data.data;
        localStorage.menu = JSON.stringify(menu);
        this.$router.push("weather");
      }).catch(function (error) {
        this.$message.error(error);
      });
    },
    openMsg() {
      // 注意这里使用了国际化
      this.$message.warning(this.$t('m.login.info'));
    }
  },
  watch: {
    isMemery(n, o) {
      if (n) {
        localStorage.userInfo = this.form.name
        localStorage.passwordInfo = this.form.password
      } else {
        localStorage.removeItem('userInfo')
        localStorage.removeItem('passwordInfo')
      }
    }
  }
};
</script>
<style scoped lang="less">
.bg {
    position: relative;
    overflow: hidden;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
   background-image: url('../../../static/img/sky.jpg');
    background-position: -20% 10%;
    background-size: contain;
    #particles-js {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
    }
}
.login-wrap {
  width: 330px;
  border-radius: 5px;
  padding: 20px;
  z-index: 3;
  margin-right: -37%;
  background: rgba(216,220,229,0.5);
  .el-form-item {
    margin-bottom: 25px !important;
  }
  h3 {
    text-align: center;
    color: #ebedef;
    margin-top: 0px;
    margin-bottom: 5px;
    span {
        color: #20a0ff;
    }
  }
  form {
    margin-top: 25px;
    .el-form-item {
      margin-bottom: 15px;
    }
  }
  a {
    text-decoration: none;
    color: #1f2d3d;
  }
  button {
    width: 100%;
    font-weight: 600;
  }
}
</style>
