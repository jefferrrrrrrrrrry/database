<template>
  <div class="wrapper">
    <div
        style="margin: 200px auto; background-color: #fff; width: 350px; height: 280px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="sys_username">
          <el-input size="medium" prefix-icon="el-icon-user" v-model="user.sys_username"></el-input>
        </el-form-item>
        <el-form-item prop="sys_password">
          <el-input size="medium" prefix-icon="el-icon-lock" show-password
                    v-model="user.sys_password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {setRoutes} from "@/router";
import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      user: {},
      rules: {
        sys_username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        sys_password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    login() {
      //console.log(this.$refs)
      this.$refs.userForm.validate((valid) => {
        if (valid) {  // 表单校验合法
          console.log(this.user)

          request.post("http://localhost:9090/login", this.user).then(res => {
            if (res.status=="SUCCESS") {
              localStorage.setItem("token", res.data.token)  // 存储用户信息到浏览器
              // 动态设置当前用户的路由
              // setRoutes()
              this.$message.success("登录成功")
              //this.$router.push("/student")
              console.log(res.data)
              if (res.data.permission ===1) {
                this.$router.push("/student")
              } else if (res.data.permission ===2) {
                this.$router.push("/teacher")
              } else if (res.data.permission ===3) {
                this.$router.push("/manager");
              }
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    }
  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
  background-image: url('~@/assets/buaaa.png'); /* 请替换为你的图片路径 */
  background-size: contain; /* 图片将会被放大或缩小以完全覆盖整个容器 */
  background-position: center; /* 图片在容器中居中显示 */
  overflow: hidden;
  background-repeat: no-repeat; /* 防止图片平铺或重复 */
}

</style>
