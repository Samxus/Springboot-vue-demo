<template>
  <div>
    <el-menu
        :default-active="path"
        class="el-menu-vertical-demo"
        style="width: 200px; min-height: calc(100vh - 50px);"
        background-color="#8696a7"
        text-color="#FFFFFF"
        active-text-color="#87CEFA"
        :default-openeds="['1']"
        router
    >
      <el-sub-menu index="1">
        <template #title>
          <el-icon>
            <location/>
          </el-icon>
          <span>系统管理</span>
        </template>
        <el-menu-item index="/user">用户管理</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="/book">
        <el-icon>
          <icon-menu/>
        </el-icon>
        <span>书籍管理</span>
      </el-menu-item>
      <el-menu-item index="3" disabled>
        <el-icon>
          <document/>
        </el-icon>
        <span>导航三</span>
      </el-menu-item>
      <el-menu-item index="4">
        <el-icon>
          <setting/>
        </el-icon>
        <span>导航四</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import {
  Location,
  Document,
  Menu as IconMenu,
  Setting,
} from '@element-plus/icons-vue'



export default {
  name: "Aside",
  data() {
    return {
      path: this.$route.path
    }
  },
  created() {
    console.log(this.$route.path)
  },
  components: {
    Setting, Document, IconMenu, Location
  }
}
</script>

<style scoped>
.el-menu-item {
  border-right: none;
  text-align: center;
}

.el-menu {
  border-right: none;
  text-align: center;
}

</style>