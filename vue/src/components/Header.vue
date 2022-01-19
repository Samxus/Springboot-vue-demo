<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid lightgray; display: flex">
    <div style="width: 200px; padding-left: 30px; font-weight: bold; color: cornflowerblue">后台管理</div>
    <div style="flex: 1"></div>
    <div style="width: 100px">
      <el-dropdown trigger="click">
        <span class="el-dropdown-link">
          用户名
          <el-icon class="el-icon--right">
            <arrow-down/>
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item><span @click="person()">用户中心</span></el-dropdown-item>
            <el-dropdown-item><span @click="logout()">退出登录</span></el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import {ArrowDown} from '@element-plus/icons-vue';

export default {
  name: "Header",
  components: {
    ArrowDown
  },
  methods: {

    logout() {
      this.$router.push("/login")
    },
    person() {
      this.$router.push("/person")
    }
  }
}

</script>

<style scoped>

</style>