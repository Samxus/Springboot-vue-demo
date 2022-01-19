<template>
  <div style="padding: 10px">
    <div style="margin: 10px">
      <el-button type="primary" plain @click="addUser">新增</el-button>
      <el-button type="primary" plain>导入</el-button>
      <el-button type="primary" plain>导出</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
      <el-button type="primary" plain style="margin-left: 10px" @click="load">查询</el-button>
    </div>
    <el-table
        style="width: 100%"
        stripe
        border
        :data="tableData"
        :row-class-name="tableRowClassName">
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="name" label="Name"/>
      <el-table-column prop="nickname" label="Nickname"/>
      <el-table-column prop="age" label="Age"/>
      <el-table-column prop="gender" label="Gender"/>
      <el-table-column prop="address" label="Address"/>

      <el-table-column label="Operations">
        <template #default="scope">
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" plain>Delete</el-button>
            </template>
          </el-popconfirm>
          <el-button plain @click="handleEdit(scope.row)"> Edit</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0;">
      <el-pagination
          :currentPage="currentPage"
          :page-sizes="[5, 10, 20]"
          :pageSize="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange">
      </el-pagination>

      <el-dialog v-model="dialogVisible" title="Tips" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="Username">
            <el-input v-model="form.username" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="Nickname">
            <el-input v-model="form.nickname" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="Age">
            <el-input v-model="form.age" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="Gender">
            <el-radio v-model="form.gender" label="1">Male</el-radio>
            <el-radio v-model="form.gender" label="0">Female</el-radio>
          </el-form-item>
          <el-form-item label="Address">
            <el-input v-model="form.address" type="textarea"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">Cancel</el-button>
            <el-button type="primary" @click="save">Confirm</el-button>
          </span>
        </template>
      </el-dialog>

    </div>
  </div>
</template>

<script>


import request from "@/utils/request";
import {ElMessage} from 'element-plus'

export default {
  name: 'User',
  components: {},
  data() {
    return {
      form: {},
      dialogVisible: false,
      total: 0,
      pageSize: 10,
      currentPage: 1,
      search: '',
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    success(msg) {
      ElMessage({
        message: 'Congrats! ' + msg + ' Success.',
        type: 'success',
        duration: 1500,
        showClose: true,
      })
    },
    load() {
      request.get("/user", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res);
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },

    save() {
      if (this.form.id) {
        request.put("/user", this.form).then(res => {
          console.log(res);
          this.load();
          this.dialogVisible = false;
          if (res.code === '0') {
            this.success('Update');
          }
        })
      } else {
        request.post("/user", this.form).then(res => {
          console.log(res);
          this.load();
          this.dialogVisible = false;
          if (res.code === '0') {
            this.success('Insert');
          }
        });
      }
    },

    addUser() {
      this.dialogVisible = true;
      this.form = {};
    },

    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
    },

    handleDelete(id) {
      request.delete("/user/" + id).then(res => {
        if (res.code === '0') {
          this.success('Delete');
        }
        this.load();
      })
    },
    //改变每页个数
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load();
    },
    //改变页码
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum
      this.load();
    },
  }
  ,
}


</script>
