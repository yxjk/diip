<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="用户姓名：" labelWidth="120px">
        <el-input v-model="params.userName" @keyup.enter.native="search" style="width:180px"
                  placeholder="请输入内容"></el-input>
      </el-form-item>
      <el-form-item label="登录名：" labelWidth="120px">
        <el-input v-model="params.userLogin" @keyup.enter.native="search" style="width:180px"
                  placeholder="请输入内容"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
      </el-form-item>
    </el-form>
    <div class="table-tool">
      <el-button type="primary" icon="el-icon-plus" size="small" @click="handleEdit">新增</el-button>
    </div>
    <el-table-page ref="elTable" :params="params" url="/api/userList">
      <el-table-column label="用户姓名" prop="userName" sortable="custom"/>
      <el-table-column label="登录名" prop="userLogin" sortable="custom"/>
      <el-table-column label="状态" prop="status" sortable="custom">
        <template slot-scope="scope">
          {{statusOptions,scope.row.status | transform}}
        </template>
      </el-table-column>
      <el-table-column label="最后登录时间" prop="loginTime" align="center" width="200px">
        <template slot-scope="scope">
          {{ scope.row.loginTime | formatDate }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="220px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="handleView(scope.$index, scope.row)">查看
          </el-button>
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table-page>
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" :modal-append-to-body="false" width="680px">
      <el-form ref="dataForm" :model="temp" :rules="rules" :inline="true">
        <el-form-item label="用户姓名" prop="userName" labelWidth="100px">
          <el-input v-model="temp.userName" style="width:200px" :disabled="disabledFlag"></el-input>
        </el-form-item>
        <el-form-item label="登录名" prop="userLogin" labelWidth="100px">
          <el-input v-model="temp.userLogin" style="width:200px" :disabled="disabledFlag"></el-input>
        </el-form-item>
        <el-form-item v-if="temp.userId" label="状态" prop="status" labelWidth="100px">
          <el-select class="filter-item" v-model="temp.status" style="width:200px" :disabled="disabledFlag">
            <el-option v-for="item in statusOptions" :key="item.key" :value="item.key" :label="item.label">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户描述" prop="userDesc" labelWidth="100px">
          <el-input type="textarea" :rows="3" v-model="temp.userDesc"
                    style="width:512px" :disabled="disabledFlag"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import ElTablePage from '@/components/ElTable.js';
  import {formatDate} from '@/utils/index.js';

  export default {
    components: {ElTablePage},
    data() {
      return {
        params: {
          sortBy: 'userId',
          order: 'desc',
          userName: null,
          userLogin: null
        },
        temp: {
          userId: '',
          userName: '',
          userLogin: '',
          userDesc: '',
          loginTime: '',
          status: ''
        },
        dialogFormVisible: false,
        disabledFlag: false,
        statusOptions: [
          {key: 'S', label: '正常'},
          {key: 'F', label: '失效'},
          {key: 'L', label: '锁定'}
        ], rules: {
          userName: [
            {required: true, message: '请输入用户姓名', trigger: 'blur'}
          ],
          userLogin: [
            {required: true, message: '请输入登录名', trigger: 'blur'}
          ],
          status: [
            {required: true, message: '请选择状态', trigger: 'blur'}
          ],
        }
      }
    },
    methods: {
      search() {
        this.params.userName = this.params.userName == '' ? null : this.params.userName;
        this.params.userLogin = this.params.userLogin == '' ? null : this.params.userLogin;
        this.$refs.elTable.search();
      },
      handleView(index, row) {
        this.temp = Object.assign({}, row) // copy obj
        this.disabledFlag = true;
        this.dialogFormVisible = true;
        this.$nextTick(() => {
          this.$refs.dataForm.clearValidate()
        })
      },
      handleEdit(index, row) {
        this.temp = Object.assign({}, row) // copy obj
        this.disabledFlag = false;
        this.dialogFormVisible = true;
        this.$nextTick(() => {
          this.$refs.dataForm.clearValidate()
        })
      }, handleDelete(index, row) {
        this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios({
            url: "/api/deleteUser",
            method: "POST",
            data: {userId: row.userId}
          }).then(res => {
            if (res.status != "200") {
              this.$message.error("服务异常[" + res.status + "]，请重试");
              return false;
            }
            if (res.data.status != "S") {
              this.$message.error(res.data.msg);
              return false;
            }
            this.$message.success(res.data.msg);
            this.$refs.elTable.search();
          }).catch(error => {
            this.$message.error(error);
            return false;
          });
        })
      }, submitForm() {
        if (this.disabledFlag) {
          this.dialogFormVisible = false;
        } else {
          this.$axios({
            url: "/api/saveUser",
            method: "POST",
            data: {
              userId: this.temp.userId,
              userName: this.temp.userName,
              userLogin: this.temp.userLogin,
              userDesc: this.temp.userDesc,
              status: this.temp.status
            }
          }).then(res => {
            if (res.status != "200") {
              this.$message.error("服务异常[" + res.status + "]，请重试");
              return false;
            }
            if (res.data.status != "S") {
              this.$message.error(res.data.msg);
              return false;
            }
            this.$message.success(res.data.msg);
            this.dialogFormVisible = false;
            this.$refs.elTable.search();
          }).catch(error => {
            this.$message.error(error);
            return false;
          });
        }
      }
    },
    filters: {
      formatDate(time) {
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
      },
      transform(options, key) {
        let label = "";
        options.some(function (type) {
          if (type.key == key) {
            label = type.label;
            return true;
          }
        })
        return label;
      }
    }
  }
</script>
