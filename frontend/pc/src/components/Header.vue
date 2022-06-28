<template>
  <div class="header-container">
    <el-row type="flex" justify="space-between" align="center">
      <el-row type="flex" align="center">
        <el-col>
          <el-row type="flex" align="center" style="cursor: pointer">
            <img
              src="../assets/images/icon-logo.svg"
              alt
              srcset
              width="30px"
              height="30px"
              style="margin-top: 14px"
            />
            <h5 style="margin-left: 12px; font-size: 18px; color: rgb(102, 101, 101)">校园教学综合信息服务平台</h5>
          </el-row>
        </el-col>
        <!-- 学期选择 -->
      </el-row>
      <el-row type="flex" align="center">
        <el-select
          v-model="value"
          placeholder="请选择学期"
          value-key="id"
          @change="selectData()"
          style="margin-right: 35px"
        >
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.label"
            :value="item.requestData.dataParams"
          ></el-option>
        </el-select>
        <el-dropdown trigger="click">
          <el-col class="dropdown-menu">
            <div class="user el-dropdown-link demonstration">
              <img src="../assets/images/icon-user.svg" alt srcset width="37px" height="37px" />
              <p style="margin-left: 5px">{{ this.loginUsername }}</p>
            </div>
          </el-col>
          <!-- 点击用户区域下拉菜单 -->
          <el-dropdown-menu slot="dropdown">
            <span @click="exitLogin">
              <el-dropdown-item icon="iconfont icon-exit">退出登录</el-dropdown-item>
            </span>
            <span @click="dialogFormVisible = true">
              <el-dropdown-item icon="el-icon-edit">修改密码</el-dropdown-item>
            </span>
          </el-dropdown-menu>
        </el-dropdown>
      </el-row>
    </el-row>
    <!-- 修改密码弹框 -->
    <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form">
        <el-form-item label="请输入原始密码" :label-width="formLabelWidth">
          <el-input v-model="form.oldPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请输入新密码" :label-width="formLabelWidth">
          <el-input v-model="form.newPasswoed" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" :label-width="formLabelWidth">
          <el-input v-model="form.againPassword" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="changePassword()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Header',
  data () {
    return {
      year: '',
      semester: '',
      dialogVisible: false,
      loginUsername: '',
      options: [{
        label: '2021-2022-2学期',
        requestData: {
          id: '1',
          dataParams: ['2021', '2']
        }
      }, {
        label: '2021-2022-1学期',
        requestData: {
          id: '2',
          dataParams: ['2021', '1']
        }
      }, {
        label: '2020-2021-2学期',
        requestData: {
          id: '3',
          dataParams: ['2020', '2']
        }
      }, {
        label: '2020-2021-1学期',
        requestData: {
          id: '4',
          dataParams: ['2020', '1']
        }
      }],
      value: '',
      dialogFormVisible: false,
      formLabelWidth: '120px',
      form: {
        oldPassword: '',
        newPassword: '',
        againPassword: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  created () {
    this.getLoginUsername()
  },
  mounted () {
    this.value = this.options[0].requestData.dataParams
    localStorage.setItem('yearData', JSON.stringify(this.value[0]))
    localStorage.setItem('semesterData', JSON.stringify(this.value[1]))
    this.getExam()
    this.getScord()
    this.getInfromation()
    this.getLessonData()
  },
  methods: {
    exitLogin () {
      this.$confirm('确认退出登录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        localStorage.removeItem('saveUsername')
        localStorage.removeItem('examData')
        localStorage.removeItem('scordData')
        localStorage.removeItem('informationData')
        localStorage.removeItem('yearData')
        localStorage.removeItem('semesterData')
        localStorage.removeItem('el-account')
        localStorage.removeItem('saveUsername')
        localStorage.removeItem('savePassword')
        localStorage.removeItem('startDate')
        localStorage.removeItem('endDate')
        localStorage.removeItem('eaToken')
        localStorage.removeItem('eleToken')
        localStorage.removeItem('lessonData')
        localStorage.removeItem('adminFlag')
        this.loginOut()
        this.$message({
          type: 'success',
          message: '操作成功 !'
        }, this.$router.push('/login')
        )
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        })
      })
    },
    async loginOut () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/education/logout'
      }).then(res => {
        console.log(res)
      })
    },
    //下拉框监听事件
    selectData () {
      localStorage.setItem('yearData', JSON.stringify(this.value[0]))
      localStorage.setItem('semesterData', JSON.stringify(this.value[1]))
      //当下拉框选择相应值时请求对应的数据
      this.getExam()
      this.getScord()
      this.getInfromation()
      this.getLessonData()
      //跳转至刷新页面
      this.$router.push('/home/refresh')
    },
    //获取用户名展示在头部
    getLoginUsername () {
      this.loginUsername = localStorage.getItem('saveUsername')
    },
    async changePassword () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/updatePassword',
        params: {
          oldPassword: this.form.oldPassword,
          newPassword: this.form.newPassword,
          newAgain: this.form.againPassword
        }
      }).then(res => {
        this.dialogFormVisible = false
      }).catch(err => {
        console.log(err)
      })
    },
    //考试信息
    async getExam () {
      this.year = localStorage.getItem('yearData')
      this.semester = localStorage.getItem('semesterData')
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/queryExamInfo',
        params: {
          year: JSON.parse(this.year),
          semester: JSON.parse(this.semester)
        }
      }).then(res => {
        this.$delete(res.data, 0)
        localStorage.setItem('examData', JSON.stringify(res.data))
      }).catch(err => {
        console.log(err)
      })
    },
    //成绩信息
    async getScord () {
      this.year = localStorage.getItem('yearData')
      this.semester = localStorage.getItem('semesterData')
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/queryScore',
        params: {
          year: JSON.parse(this.year),
          semester: JSON.parse(this.semester)
        }
      }).then(res => {
        this.$delete(res.data, 0)
        localStorage.setItem('scordData', JSON.stringify(res.data))
      }).catch(err => {
        console.log(err)
      })
    },
    //通知信息
    async getInfromation () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/queryMessage',
        params: {
          flag: '0'
        }
      }).then(res => {
        this.$delete(res.data, 0)
        localStorage.setItem('informationData', JSON.stringify(res.data))
      }).catch(err => {
        console.log(err)
      })
    },
    //获取课表数据
    async getLessonData () {
      this.year = localStorage.getItem('yearData')
      this.semester = localStorage.getItem('semesterData')
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/queryClasses',
        params: {
          year: JSON.parse(this.year),
          semester: JSON.parse(this.semester)
        }
      }).then(res => {
        localStorage.setItem('lessonData', JSON.stringify(res.data.kbList))
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.el-col img {
  margin-top: 11px;
}

.user {
  display: flex;
}

.dropdown-menu {
  cursor: pointer;
}
</style>
