<template>
  <el-form
    :model="form"
    :label-width="labelWidth"
    class="form"
    :rules="rules"
    ref="form"
  >
    <el-form-item label="消息id">
      <el-input
        v-model="form.id"
        placeholder="添加自动生成"
        disabled
      ></el-input>
    </el-form-item>

    <el-form-item label="标签" required>
      <el-tag
        :key="tag"
        v-for="tag in form.tags"
        closable
        :disable-transitions="false"
        @close="handleClose(tag)"
      >
        {{ tag }}
      </el-tag>
      <el-input
        class="input-new-tag"
        v-if="inputVisible"
        v-model="inputValue"
        ref="saveTagInput"
        size="small"
        @keyup.enter.native="handleInputConfirm"
        @blur="handleInputConfirm"
      >
      </el-input>
      <el-button v-else class="button-new-tag" size="small" @click="showInput"
        >+ New Tag</el-button
      >
    </el-form-item>

    <el-form-item label="描述" prop="desc">
      <el-input v-model="form.desc" placeholder="描述"></el-input>
    </el-form-item>

    <el-form-item label="消息通道" required>
      <el-checkbox-group v-model="form.channelTags">
        <el-checkbox
          v-for="ele in channelTagsCheckboxData"
          :key="ele.value"
          :label="ele.value"
          :disabled="ele.disabled"
          >{{ ele.label }}</el-checkbox
        >
      </el-checkbox-group>
    </el-form-item>

    <el-form-item label="消息聚合" required>
      <el-switch
        v-model="form.isAggregate"
        :active-value="1"
        :inactive-value="0"
        active-color="#13ce66"
        inactive-color="#ff4949"
      >
      </el-switch>
    </el-form-item>

    <el-form-item label="推送类型" required>
      <el-select v-model="form.deliveryType" placeholder="请选择">
        <el-option
          v-for="item in deliveryTypeSelectData"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="优先级" required>
      <el-select v-model="form.priority" placeholder="请选择">
        <el-option
          v-for="item in prioritySelectData"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="清理模式" required>
      <el-select v-model="form.clearMode" placeholder="请选择">
        <el-option
          v-for="item in clearModeSelectData"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="渲染类型">
      <el-checkbox-group v-model="form.renderType">
        <el-checkbox
          v-for="ele in renderTypeCheckboxData"
          :key="ele.value"
          :label="ele.value"
          >{{ ele.label }}</el-checkbox
        >
      </el-checkbox-group>
    </el-form-item>

    <el-form-item label="过期时间" required>
      <el-input
        v-model="form.expireTime"
        type="number"
        placeholder="过期时间"
        autocomplete="off"
      ></el-input>
    </el-form-item>

    <el-form-item>
      <el-button @click="closePage">取 消</el-button>
      <el-button type="primary" @click="saveConfig">确 定</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "EditMessageConfig",
  props: ["id"],
  data() {
    return {
      rules: {
        desc: [
          { required: true, message: "请输入备注", trigger: "blur" },
          {
            min: 1,
            max: 255,
            message: "长度在 1 到 255 个字符",
            trigger: "blur",
          },
        ],
      },
      labelWidth: "90px",
      inputVisible: false,
      inputValue: "",
      form: {
        id: null,
        priority: 2,
        clearMode: 0,
        renderType: [],
        channelTags: [],
        deliveryType: 0,
        expireTime: 0,
        tags: [],
      },
      deliveryTypeSelectData: [
        { value: 0, label: "单体" },
        { value: 1, label: "分组" },
        { value: 2, label: "群发" },
      ],
      prioritySelectData: [
        { value: 2, label: "低" },
        { value: 1, label: "中" },
        { value: 0, label: "高" },
      ],
      clearModeSelectData: [
        { value: 0, label: "已读不销毁" },
        { value: 1, label: "已读销毁" },
      ],
      renderTypeCheckboxData: [
        { value: 0, label: "Badge" },
        { value: 1, label: "轮播通知" },
        { value: 2, label: "弹窗" },
        { value: 3, label: "Toast" },
        { value: 4, label: "Notification" },
      ],
      channelTagsCheckboxData: [
        { value: "APP", label: "移动端", disabled: false },
        { value: "WEB", label: "Web端", disabled: false },
        { value: "EMAIL", label: "Email", disabled: false },
        { value: "JG", label: "极光", disabled: false },
        { value: "SMS", label: "短信（未开发）", disabled: true },
        { value: "WECHAT", label: "微信（未开发）", disabled: true },
      ],
    };
  },
  methods: {
    handleClose(tag) {
      this.form.tags.splice(this.form.tags.indexOf(tag), 1);
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      let inputValue = this.inputValue;

      if (this.form.tags.indexOf(inputValue) > -1) {
        this.msgShow.errMsgShow(this, "标签不允许重复");
        return;
      }

      if (inputValue) {
        this.form.tags.push(inputValue);
      }

      this.inputVisible = false;
      this.inputValue = "";
    },

    init() {
      this.apiRequest
        .post(
          this.url.data.config.detail + "/" + this.id,
          {},
          {
            headers: {
              "Content-Type": "application/json;charset=UTF-8",
            },
          }
        )
        .then((res) => {
          this.form = res;
        })
        .catch((e) => {
          this.msgShow.errMsgShow(this, e);
        });
    },

    closePage() {
      window.history.length > 1 ? this.$router.go(-1) : this.$router.push("/");
    },

    saveConfig() {
      if (this.form.tags.length === 0) {
        this.msgShow.errMsgShow(this, "标签不能为空");
        return;
      }

      if (this.form.id === "") {
        this.apiRequest
          .post(this.url.data.config.save, this.form, {
            headers: {
              "Content-Type": "application/json;charset=UTF-8",
            },
          })
          .then((res) => {
            this.form.id = res.id;
            this.msgShow.sucMsgShow(this, "消息配置新增成功");
            this.closePage();
          })
          .catch((e) => {
            this.msgShow.errMsgShow(this, e);
          });
      } else {
        this.apiRequest
          .post(this.url.data.config.save, this.form, {
            headers: {
              "Content-Type": "application/json;charset=UTF-8",
            },
          })
          .then(() => {
            this.msgShow.sucMsgShow(this, "消息配置更新成功");
            this.closePage();
          })
          .catch((e) => {
            this.msgShow.errMsgShow(this, e);
          });
      }
    },
  },
  mounted() {
    if (this.id !== undefined) {
      this.form.id = this.id;
      this.init();
    }
  },
};
</script>

<style scoped>
.el-input {
  width: 300px;
}

.el-select {
  width: 300px;
}

.form {
  width: 400px;
  margin-top: 20px;
}

.avatar-uploader .el-upload {
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  border: 1px dashed #d9d9d9;
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}

.avatar {
  width: 100px;
  height: 100px;
  display: block;
}

.el-tag + .el-tag {
  margin-left: 10px;
}

.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}

.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>