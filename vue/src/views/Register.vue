<template>
  <div style="width: 100%; height: 100vh; background-color: #909399; overflow: hidden">
    <div style="width: 400px; margin: 150px auto">
      <div style="color: bisque; font-size: 30px; text-align: center">
        Welcome Register
      </div>

      <el-form ref="formRef" :model="form" style="margin-top: 20px;" size="normal"
               :rules="rules">
        <el-form-item prop="username">
          <el-input prefix-icon="UserFilled" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="Lock" v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item prop="confirm">
          <el-input prefix-icon="Lock" v-model="form.confirm" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <div style="float: left; width: 50%">
            <el-button plain style="width: 90%;" @click="register">Register</el-button>
          </div>
          <div style="float: right;width: 50%">
            <el-button type="primary" plain style="width: 90%; margin-left: 10%" @click="$router.push('/login')">
              Cancel
            </el-button>
          </div>
        </el-form-item>
      </el-form>

    </div>
  </div>

</template>

<script>
import {ElMessage} from "element-plus";
import request from "@/utils/request";

export default {
  name: "Register",
  data() {
    return {
      form: {},
      rules: {
        username: [{required: true, message: "Please enter username"}],
        password: [{required: true, message: "Please enter password"}],
        confirm: [{required: true, message: "Please validate password"}],
      }
    }
  },
  methods: {
    success(msg) {
      ElMessage({
        message: 'Congrats! ' + msg + ' Success.',
        type: 'success',
        duration: 2000,
        showClose: true,
      })
    },
    fail(msg) {
      ElMessage({
        message: 'Error! ' + msg + ' Fails.',
        type: 'error',
        duration: 2000,
        showClose: true,
      })
    },
    register() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          if (this.form.password !== this.form.confirm) {
            this.fail('Inconsistent passwords. Register')
            this.form = {}
          } else {
            request.post("/user/register", this.form).then(res => {
              if (res.code === '0') {
                this.success('Register')
                this.$router.push("/login")
              } else {
                this.fail(res.message + '.')
              }
            })
          }
        }
      })
    },

  }
}
</script>

<style scoped>

</style>