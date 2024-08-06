<template>
  <div>
    <el-form
      ref="form"
      :model="queryParams"
      class="sys-search"
      label-width="80px"
    >
      <el-form-item label="描述">
        <el-input
          type="text"
          placeholder="描述"
          v-model="queryParams.desc"
        ></el-input>
      </el-form-item>
      <el-form-item label-width="40px">
        <el-button
          icon="el-icon-search"
          style="color: #ffffff"
          class="search-btn"
          @click="onSubmit"
          >查询
        </el-button>
      </el-form-item>
    </el-form>

    <el-button
      type="success"
      class="insertBtn"
      icon="el-icon-document-add"
      @click="onAdd"
      >新增
    </el-button>

    <el-container style="display: block">
      <el-table size="mini" :data="listData" stripe>
        <el-table-column
          label="序号"
          align="center"
          width="50"
          type="index"
          :index="indexMethod"
        ></el-table-column>
        <el-table-column
          label="MsgId"
          align="center"
          prop="id"
        ></el-table-column>
        <el-table-column
          label="描述"
          align="center"
          prop="desc"
        ></el-table-column>
        <el-table-column
          label="创建时间"
          align="center"
          width="150"
          prop="createTime"
        ></el-table-column>
        <el-table-column
          label="修改时间"
          align="center"
          width="150"
          prop="updateTime"
        >
        </el-table-column>
        <el-table-column label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="primary"
              icon="el-icon-edit"
              @click="onUpdate(scope.row)"
              >修改
            </el-button>
            <el-button
              size="mini"
              type="danger"
              icon="el-icon-delete"
              @click="onDelete(scope.row)"
              >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-size.sync="pageSize"
        layout="total, prev, pager, next, jumper,sizes"
        :total="total"
      >
      </el-pagination>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "MessageConfigList",
  data() {
    return {
      queryParams: {
        desc: "",
      },
      listData: [],
      currentPage: 1,
      pageSize: 10,
      total: 0,
    };
  },
  methods: {
    onSubmit() {
      this.getList();
    },
    getList() {
      let param = {};
      param.pageNo = this.currentPage - 1;
      param.pageSize = this.pageSize;
      param.data = this.queryParams;
      this.apiRequest
        .post(this.url.data.config.list, param, {
          headers: {
            "Content-Type": "application/json;charset=UTF-8",
          },
        })
        .then((res) => {
          this.listData = res["content"];
          this.currentPage = res["number"] + 1;
          this.total = res["totalElements"];
        })
        .catch((e) => {
          this.msgShow.errMsgShow(this, e);
        });
    },
    onDelete(row) {
      this.$confirm("确定删除消息配置吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.apiRequest
            .post(
              this.url.data.config.delete + "/" + row.id,
              {},
              {
                headers: {
                  "Content-Type": "application/json;charset=UTF-8",
                },
              }
            )
            .then(() => {
              this.msgShow.sucMsgShow(this, "消息配置删除成功");
              this.getList();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作",
          });
        });
    },
    indexMethod(index) {
      if (index === 0) {
        return 1;
      } else {
        return index + 1;
      }
    },
    onAdd() {
      this.$router.push("/messageconfig/edit");
    },
    onUpdate(row) {
      this.$router.push("/messageconfig/edit/" + row.id);
    },
    handleSizeChange() {
      this.getList();
    },
    handleCurrentChange() {
      this.getList();
    },
  },
  created() {
    this.getList();
  },
  mounted() {},
};
</script>

<style scoped>
.insertBtn {
  margin-bottom: 10px;
}

.goods {
  width: 50px;
  height: 50px;
}
</style>