<template>
  <div style="padding: 10px">
    <div style="margin: 10px">
      <el-button type="primary" plain @click="addBook">新增</el-button>
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
      <el-table-column prop="price" label="Price" sortable/>
      <el-table-column prop="author" label="Author"/>
      <el-table-column label="Cover">
        <template #default="scope">
          <el-image style="width: 80px; height: 80px"
                    :src="scope.row.cover"
                    :preview-src-list="[scope.row.cover]"></el-image>
        </template>
      </el-table-column>
      >
      <el-table-column prop="createTime" label="Date"/>

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
          <el-form-item label="Name">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="Price">
            <el-input v-model="form.price" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="Author">
            <el-input v-model="form.author" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="Date">
            <el-date-picker v-model="form.createTime" value-format="YYYY-MM-DD" type="date" style="width: 80%"
                            clearable></el-date-picker>
          </el-form-item>
          <el-form-item label="Cover">
            <el-upload
                ref="uploadCover"
                :action="filesUploadUrl"
                :limit="1"
                :on-success="filesUploadSuccess"
                :auto-upload="false"
            >
              <template #trigger>
                <el-button type="primary">select file</el-button>
              </template>
              <el-button class="ml-3" type="success" @click="submitUpload">upload to server</el-button>
            </el-upload>
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
  name: 'Book',
  components: {},
  data() {
    return {
      form: {},
      dialogVisible: false,
      total: 0,
      pageSize: 10,
      currentPage: 1,
      search: '',
      tableData: [],
      uploadStatus: false,
      filesUploadUrl: "http://" + "45.76.231.189" + "/api/files/upload"
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

    filesUploadSuccess(res) {
      console.log('filesUploadSuccess');
      console.log(res + ': this is result');
      this.form.cover = res.data;
      console.log(this.form.cover);
    },


    load() {
      request.get("/book", {
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
    submitUpload(){
      this.$refs.uploadCover.submit();
    },
    save() {
      if (this.form.id) {
        request.put("/book", this.form).then(res => {
          console.log(res);
          this.load();
          this.dialogVisible = false;
          if (res.code === '0') {
            this.success('Update');
            this.$refs.uploadCover.clearFiles()
          }
        })
      } else {
        request.post("/book", this.form).then(res => {
          console.log(res);
          this.load();
          this.dialogVisible = false;
          if (res.code === '0') {
            this.success('Insert');
            this.$refs.uploadCover.clearFiles()
          }
        });
      }
    },

    addBook() {
      this.dialogVisible = true;
      this.form = {};
    },

    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
    },

    handleDelete(id) {
      request.delete("/book/" + id).then(res => {
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
