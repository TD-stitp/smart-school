<template>
  <div class="mysubscibe-container">
    <div class="box-card" style="margin-top: 15px; height: 78vh">
      <el-row
        style="line-height: 50px; text-align: start; padding: 10px 15px; margin-bottom: 10px"
        type="flex"
        align="center"
        justify="space-between"
      >
        <el-row type="flex" align="center">
          <h3 style="font-size: 17px">订阅的频道</h3>
          <el-badge :value="channelCount" class="item" style="margin-left: 5px; margin-top: 4px"></el-badge>
        </el-row>
        <el-row type="flex" align="center">
          <span style="margin-right: 25px" v-if="this.flag == '0'">
            <el-button type="warning" icon="el-icon-s-custom" @click="open2 ()">申请频道管理员</el-button>
          </span>
          <span style="margin-right: 25px" v-if="this.flag == '1'">
            <el-button
              type="primary"
              icon="el-icon-user-solid"
              @click="toChannelAdmin()"
              v-if="this.flag == '1'"
            >频道管理</el-button>
          </span>
          <span>
            <el-button type="primary" icon="el-icon-plus" @click="show = true">订阅频道</el-button>
          </span>
        </el-row>
      </el-row>
      <el-empty description="暂无订阅频道" v-if="channelCount == '0'"></el-empty>
      <div class="subscribe-item" v-for="item in channelList" :key="item.index">
        <el-row type="flex" align="center" justify="space-between">
          <el-col>
            <el-row type="flex" align="center">
              <el-tag style="font-size: 15px">{{ item.name }}</el-tag>
              <p style="margin-left: 15px; font-size: 15px">{{ item.message | ellipsis }}</p>
            </el-row>
          </el-col>
          <el-col>
            <el-row type="flex" align="center" justify="end">
              <span style="display: flex; margin-top: 3px; text-align: start">
                <p>订阅码：</p>
                <el-tag type="success" style="font-size: 14px" class="code-tag">{{ item.code }}</el-tag>
              </span>
              <el-button
                type="success"
                icon="el-icon-time"
                style="margin-left: 25px"
                @click="toHistoryMessage(item.code, item.index)"
              >历史消息</el-button>
              <el-button
                type="danger"
                icon="el-icon-delete"
                style="margin-left: 25px"
                @click="open(item.code, item.index)"
                size="mini"
              >取消订阅</el-button>
            </el-row>
          </el-col>
        </el-row>
        <el-divider class="divider-h"></el-divider>
      </div>
      <!-- 订阅频道 -->
      <el-dialog title="订阅频道" :visible.sync="show">
        <el-form>
          <el-form-item label="订阅码" :label-width="formLabelWidth">
            <el-input
              v-model="subscriptionCode"
              type="text"
              autocomplete="off"
              placeholder="请输入订阅码"
              clearable
            ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="show = false">取 消</el-button>
          <el-button type="primary" @click="addFollow()">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 申请频道管理 -->
      <el-dialog title="编辑" :visible.sync="channelAdminShow" class="ApplyChannelAdmin">
        <el-form>
          <el-form-item label="姓名:" :label-width="formLabelWidth2" style="margin-bottom: -2px">
            <el-input
              v-model="apply.name"
              type="text"
              autocomplete="off"
              placeholder="请填写您的姓名"
              clearable
              suffix-icon="el-icon-edit"
            ></el-input>
          </el-form-item>
          <el-form-item label="部门名称:" :label-width="formLabelWidth2" style="margin-bottom: -2px">
            <el-input
              v-model="apply.department"
              type="text"
              autocomplete="off"
              placeholder="请填写部门名称 (如校队, 社团)"
              clearable
              suffix-icon="el-icon-edit"
            ></el-input>
          </el-form-item>
          <el-form-item label="申请理由:" :label-width="formLabelWidth2" style="margin-bottom: -2px">
            <el-input
              type="textarea"
              placeholder="请填写申请理由"
              v-model="apply.reason"
              show-word-limit
              :autosize="{ minRows: 3, maxRows: 8}"
              style="margin-bottom: 30px"
            ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer" style="text-align: center">
          <el-button
            type="primary"
            @click="applyChannelAdmin()"
            style="width: 76%; height: 35px; font-size: 14px; margin-bottom: 10px"
          >提 交</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Mysubscribe',
  data () {
    return {
      channelCount: '',
      channelList: [],
      show: false,
      formLabelWidth: '70px',
      formLabelWidth2: '72px',
      subscriptionCode: '',
      channelCode: '',
      flag: '',
      channelAdminShow: false,
      apply: {
        name: '',
        department: '',
        reason: ''
      }
    }
  },
  created () {
    this.getChannel()
    this.flag = localStorage.getItem('adminFlag')
    console.log(this.flag);
  },
  filters: {
    // 限制通知字数显示
    ellipsis: function (value) {
      if (!value) return ''
      if (value.length > 50) {
        return value.slice(0, 50) + '...'
      }
      return value
    }
  },
  methods: {
    toChannelAdmin () {
      this.$router.push('/home/subscribe/channelAdmin')
    },
    toHistoryMessage (channelCode, id) {
      this.$router.push({ path: '/home/subscribe/userHistoryMessage', query: { code: channelCode, index: id } })
    },
    async getChannel () {
      const loading = this.$loading({
        lock: true,
        text: '数据加载中...',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/follow/getFollows',
      }).then(res => {
        console.log(res.data)
        if (res.data.code === '200') {
          loading.close()
          this.channelCount = res.data.total
          this.channelList = res.data.follows
        }
      }).catch(err => {
        console.log(err)
      })
    },
    //订阅频道
    async addFollow () {
      this.show = false
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/follow/addFollow',
        params: {
          code: this.subscriptionCode
        }
      }).then(res => {
        console.log(res.data)
        if (res.data.code === '200') {
          this.$notify({
            title: '成功',
            message: '订阅成功',
            type: 'success'
          })
          this.getChannel()
        } else if (res.data.code === '404') {
          this.$confirm('订阅失败，请检查订阅码是否正确', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {

          }).catch(() => {

          })
        }
      }).catch(err => {
        console.log(err)
      })
    },
    async cancelChannel () {
      const loading = this.$loading({
        lock: true,
        text: '请稍后...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/follow/cancelFollow',
        params: {
          code: this.channelCode
        }
      }).then(res => {
        console.log(res.data)
        if (res.data.code === '200') {
          loading.close()
          this.$message({
            message: '操作成功',
            type: 'success'
          })
          this.getChannel()
        } else if (res.data.code === '202') {
          loading.close()
          this.$confirm('取消失败请稍后重试', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {

          }).catch(() => {

          })
        }
      }).catch(err => {
        console.log(err)
      })
    },
    open (code) {
      this.$confirm('确定取消该频道订阅?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.channelCode = code
        this.cancelChannel()
        this.$message({
          type: 'success',
          message: '取消成功'
        })
      }).catch(() => {

      })
    },
    open2 () {
      this.channelAdminShow = true
    },
    async applyChannelAdmin () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/channel/apply',
        params: {
          name: this.apply.name,
          department: this.apply.department,
          reason: this.apply.reason
        }
      }).then(res => {
        this.channelAdminShow = false
        if (res.data.code === '200') {
          setTimeout(() => {
            this.$alert('申请成功，等待管理员审核。', '提示', {
              confirmButtonText: '确定',
            })
          }, 300)
        } else if (res.data.code === '202') {
          setTimeout(() => {
            this.$confirm('申请失败，请联系管理员。', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {

            }).catch(() => {

            })
          }, 300)
        }
        console.log(res.data)
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.box-card {
  border-radius: 5px;
  background-color: #fff;
}

.subscribe-item {
  line-height: 25px;
  padding: 0 15px;
}

.subscribe-title {
  display: block;
  height: 35px;
  border-left: 7px solid rgb(31, 154, 255);
  margin-top: 12px;
}

.divider-h {
  margin-top: 15px;
  background-color: rgb(236, 236, 236);
}

/deep/.el-dialog__header {
  line-height: 0;
}

/deep/.el-dialog__body {
  padding: 10px 0px;
  color: #606266;
  font-size: 14px;
  word-break: break-all;
  margin-left: -40px;
}

/deep/.el-dialog__footer {
  line-height: 0;
}

/deep/.el-dialog {
  width: 520px;
  text-align: start;
}

.dialog-footer /deep/.el-button {
  width: 80px;
  padding: 10px;
}

.code-tag {
  width: 78px;
  text-align: center;
}
</style>
