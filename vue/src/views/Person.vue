<template>
  <div>
    <el-card style="width: 40%; margin: 10px;">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="Username">
          <el-input v-model="form.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="Nickname">
          <el-input v-model="form.nickname"></el-input>
        </el-form-item>
        <el-form-item label="Age">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="Gender">
          <el-radio v-model="form.gender" label="1">Male</el-radio>
          <el-radio v-model="form.gender" label="0">Female</el-radio>
        </el-form-item>
        <el-form-item label="Password">
          <el-input v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="Address">
          <el-input v-model="form.address" type="textarea"></el-input>
        </el-form-item>
      </el-form>
    </el-card>

  </div>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      form: {}
    }
  },

  created() {

  }

}
</script>

<style scoped>

</style>