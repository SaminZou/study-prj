<template>
  <ul>
    <li v-for="(item, index) in kv" :key="index" class="complex-input-li">
      <el-input
        placeholder="key"
        v-model="item.key"
        class="complex-input-input"
      ></el-input>
      <el-select
        v-model="item.type"
        slot="prepend"
        class="complex-input-select"
        placeholder="请选择"
      >
        <el-option label="字符串" value="0"></el-option>
        <el-option label="数字" value="1"></el-option>
        <el-option label="布尔型" value="2"></el-option>
      </el-select>
      <el-input
        placeholder="value"
        v-model="item.value"
        class="complex-input-input"
      ></el-input>
      <el-button
        v-if="index === 0"
        slot="append"
        icon="el-icon-plus"
        v-on:click="add"
        class="complex-input-button"
      ></el-button>
      <el-button
        v-else
        slot="append"
        icon="el-icon-minus"
        v-on:click="del(index)"
        class="complex-input-button"
      ></el-button>
    </li>
  </ul>
</template>

<script>
export default {
  name: "ComplexInput",
  props: ["kv"],
  data() {
    return {
      labelWidth: "120px",
    };
  },
  methods: {
    add() {
      this.kv.push({ key: "", type: "字符串", value: "" });
    },
    del(index) {
      this.kv.splice(index, 1);
    },
  },
  created() {
    if (this.kv.length === 0) {
      this.kv.push({ key: "", type: "字符串", value: "" });
    }
  },
};
</script>

<style scoped>
li {
  list-style: none;
  padding-bottom: 5px;
  display: flex;
  justify-content: center;
}

.el-button {
  display: flex;
  justify-content: center;
}

.complex-input-li {
  width: 450px;
}

.complex-input-input {
  width: 150px;
}

.complex-input-button {
  width: 50px;
}

.complex-input-select {
  width: 90px;
}
</style>