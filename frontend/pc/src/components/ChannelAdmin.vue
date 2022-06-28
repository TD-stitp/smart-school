<template>
  <div class="channelAdmin-container">
    <div class="box-card">
      <el-row
        style="line-height: 50px; text-align: start; padding: 10px 15px; margin-bottom: 10px"
        type="flex"
        align="center"
        justify="space-between"
      >
        <p style="font-size: 17px">频道列表</p>
        <el-row type="flex" align="center">
          <span style="margin-right: 25px">
            <el-button icon="el-icon-back" @click="toMysubscribe()">返回</el-button>
          </span>
          <span style="margin-right: 25px">
            <el-button type="primary" icon="el-icon-plus" @click="dialogFormVisible = true">创建频道</el-button>
          </span>
          <p style="color: rgb(31, 154, 255)">创建频道总数: {{ channelCount }}</p>
        </el-row>
      </el-row>
      <vue-scroll :ops="ops" style="overflow-y: auto;">
        <div class="channelAdmin-box">
          <el-table :data="channelList" style="width: 98%" :v-for="channelList">
            <el-table-column type="index" label="ID" width="230"></el-table-column>
            <el-table-column prop="name" label="频道名称"></el-table-column>
            <el-table-column prop="code" label="订阅码"></el-table-column>
            <el-table-column label="创建时间">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.date }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="address" label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  icon="el-icon-edit"
                  @click="showChangeChannelName(scope.row.code, scope.$index)"
                >编辑</el-button>
                <el-button
                  size="mini"
                  type="danger"
                  icon="el-icon-delete"
                  @click="deleteChannel(scope.row.code, scope.$index)"
                >删除</el-button>
              </template>
            </el-table-column>
            <el-table-column label="更多" width="200">
              <template slot-scope="scope">
                <el-button
                  @click="toChannelDetail(scope.row.code, scope.row.name, scope.$index)"
                  type="text"
                  size="small"
                  style="font-size: 15px"
                >查看详情</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!-- 创建频道 -->
          <el-dialog
            title="创建频道"
            :visible.sync="dialogFormVisible"
            width="30%"
            style="text-align: start"
          >
            <el-form>
              <el-form-item label="频道名称" :label-width="formLabelWidth">
                <el-input
                  v-model="channelName"
                  type="text"
                  autocomplete="off"
                  placeholder="请输入频道名称"
                  clearable
                ></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="handleDelete()">确 定</el-button>
            </div>
          </el-dialog>
          <!-- 修改频道名称 -->
          <el-dialog title="修改频道名称" :visible.sync="show" width="30%" style="text-align: start">
            <el-form>
              <el-form-item label="频道名称" :label-width="formLabelWidth">
                <el-input
                  v-model="changeChannelNames"
                  type="text"
                  autocomplete="off"
                  placeholder="请输入频道名称"
                  clearable
                ></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="show = false">取 消</el-button>
              <el-button type="primary" @click="changeChannelName()">确 定</el-button>
            </div>
          </el-dialog>
        </div>
      </vue-scroll>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ChannelAdmin',
  data () {
    return {
      channelList: [],
      channelName: '',
      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      channelCount: '',
      channelCode: '',
      show: false,
      changeChannelNames: ''
    }
  },
  created () {
    this.getChannelList()
  },
  methods: {
    toMysubscribe () {
      this.$router.push('/home/subscribe/mySubscribe')
    },
    toChannelDetail (channelCode, channelName) {
      this.$router.push({ path: '/home/subscribe/channelDetail', query: { code: channelCode, name: channelName } })
    },
    async getChannelList () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/channel/getChannels',
      }).then(res => {
        this.channelList = res.data.channels
        this.channelCount = res.data.total
      }).catch(err => {
        console.log(err)
      })
    },
    async createChannel () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/channel/createChannel',
        params: {
          name: this.channelName
        }
      }).then(res => {
        console.log(res.data)
        if (res.data.msg === '创建成功') {
          this.dialogFormVisible = false
          this.getChannelList()
          this.$notify({
            title: '成功',
            message: '创建成功',
            type: 'success'
          });
        }
      }).catch(err => {
        console.log(err)
      })
    },
    showChangeChannelName (code) {
      this.show = true
      this.channelCode = code
    },
    async changeChannelName () {
      const loading = this.$loading({
        lock: true,
        text: '请稍后...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      this.show = false
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/channel/setChannelName',
        params: {
          code: this.channelCode,
          name: this.changeChannelNames
        }
      }).then(res => {
        if (res.data.code === '200') {
          loading.close()
          this.$message({
            message: '操作成功',
            type: 'success'
          })
          this.getChannelList()
          this.changeChannelNames = ''
        }
      }).catch(err => {
        console.log(err)
      })
    },
    handleDelete () {
      this.createChannel()
    },

    async deleteChannel (code) {
      this.channelCode = code
      const loading = this.$loading({
        lock: true,
        text: '请稍后...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/channel/deleteChannel',
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
          this.getChannelList()
        }
      }).catch(err => {
        console.log(err)
      })
    }
  },
}
</script>

<style lang="less" scoped>
.box-card {
  border-radius: 5px;
  background-color: #fff;
  margin-top: 15px;
  height: 680px;
}

.el-table {
  font-size: 15px;
  margin: auto;
  line-height: 0;
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB',
    'Microsoft YaHei', '微软雅黑', Arial, sans-serif;
}

.el-form {
  margin-top: -10px;
}

/deep/.el-dialog__header {
  line-height: 0;
}

/deep/.el-dialog__body {
  padding: 10px 0px;
  color: #606266;
  font-size: 14px;
  word-break: break-all;
  margin-left: -30px;
}

/deep/.el-dialog__footer {
  line-height: 0;
}

.dialog-footer /deep/.el-button {
  width: 80px;
  padding: 10px;
}

/deep/ .el-pagination {
  float: right;
  margin-right: 5px;
  margin-top: 30px;
}
</style>
