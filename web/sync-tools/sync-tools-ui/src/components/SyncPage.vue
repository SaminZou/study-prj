<template>
  <div class="container">
    <!-- Head -->
    <div class="head">
      <input v-model="ipAddress" placeholder="输入 IP 地址" :disabled="isConnected"/>
      <button @click="handleConnection">
        {{ isConnected ? '关闭' : '连接' }}
      </button>
    </div>

    <!-- Body -->
    <div class="body">
      <textarea
          v-model="websocketMessage"
          placeholder="输入消息"
          @input="sendMessage"
          :disabled="!isConnected"
          class="message-input"
      ></textarea>
    </div>

    <!-- Foot -->
    <div class="foot" :class="{ active: isConnected }">
      <p>{{ connectionStatus }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 用户输入的 IP 地址
      ipAddress: '',
      // 是否已连接
      isConnected: false,
      // 连接状态信息
      connectionStatus: '等待连接...',
      // 消息内容
      websocketMessage: '',
      // WebSocket 实例
      websocket: null,
      // 重试次数
      retryCount: 0,
      // 最大重试次数
      maxRetries: 3,
    };
  },
  methods: {
    // 处理连接、关闭操作
    handleConnection() {
      if (this.isConnected) {
        this.closeConnection();
      } else {
        this.connectToServer();
      }
    },

    // 连接服务器
    connectToServer() {
      if (!this.ipAddress) {
        this.connectionStatus = '请输入 IP 地址';
        return;
      }

      // 校验 IP 地址格式
      const ipv4WithPortRegex = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(:[0-9]{1,5})?$/;
      if (!ipv4WithPortRegex.test(this.ipAddress)) {
        this.connectionStatus = '请输入有效的 IPv4 地址（可选端口号）';
        return;
      }

      this.connectionStatus = '正在连接服务器...';
      this.connectWebSocket();
    },

    // 连接 WebSocket
    connectWebSocket() {
      this.websocket = new WebSocket(`ws://${this.ipAddress}/ws`);

      this.websocket.onopen = () => {
        this.isConnected = true;
        this.connectionStatus = 'WebSocket 连接成功';
      };

      this.websocket.onmessage = (event) => {
        this.websocketMessage = event.data;
      };

      this.websocket.onerror = (error) => {
        this.connectionStatus = `WebSocket 错误：${error.message}`;
        this.retryConnection();
      };

      this.websocket.onclose = () => {
        this.connectionStatus = 'WebSocket 连接已关闭';
        this.isConnected = false;
        this.websocket = null;
        this.retryConnection();
      };
    },

    // 发送消息
    sendMessage() {
      if (this.isConnected && this.websocketMessage.trim() !== '') {
        this.websocket.send(this.websocketMessage);
      }
    },

    // 重试连接
    retryConnection() {
      if (this.retryCount < this.maxRetries) {
        this.retryCount++;
        this.connectionStatus = `尝试重新连接 (${this.retryCount}/${this.maxRetries})...`;
        setTimeout(() => {
          this.connectToServer();
        }, 2000); // 2 秒后重试
      } else {
        this.connectionStatus = '连接失败，已超过最大重试次数';
      }
    },

    // 关闭连接
    closeConnection() {
      if (this.websocket) {
        this.websocket.close();
      }
      this.isConnected = false;
      this.connectionStatus = '连接已关闭';
      this.retryCount = 0; // 重置重试次数
    },
  },

  beforeDestroy() {
    // 组件销毁时关闭 WebSocket 连接
    if (this.websocket) {
      this.websocket.close();
    }
  },
};
</script>

<style>
body, html {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
}

.container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  width: 100vw;
  overflow: hidden;
  margin: 0;
  padding: 0;
}

.head, .foot {
  width: 100%;
  padding: 10px;
  box-sizing: border-box;
}

.head input, .head button {
  width: 100%;
  box-sizing: border-box;
  margin: 0;
  padding: 8px;
}

.body {
  flex: 1;
  padding: 10px;
  box-sizing: border-box;
  overflow: auto;
  overflow-x: hidden;
}

.message-input {
  width: 100%;
  height: 100%;
  padding: 10px;
  box-sizing: border-box;
  font-size: 16px;
  border: none;
  outline: none;
  /* 禁止调整大小 */
  resize: none;
}

.foot.active {
  background-color: #e0f7fa;
}

.foot {
  text-align: center;
}
</style>