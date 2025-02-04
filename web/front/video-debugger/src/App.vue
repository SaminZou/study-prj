<template>
  <div id="app">
    <div>
      <el-container class="editMain">
        <el-form
            :model="videoInfo"
            :rules="rules"
            ref="videoInfo"
            label-width="80px"
            size="mini"
        >
          <el-form-item label="视频地址(HLS)" prop="videoUrl">
            <el-input
                v-model="videoInfo.videoUrl"
                :rules="rules.videoUrl"
                placeholder="视频地址"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
                size="mini"
                type="primary"
                icon="el-icon-edit"
                style="margin-left: 1vw"
                @click="onPlay"
            >播放
            </el-button>
          </el-form-item>
        </el-form>
      </el-container>
    </div>
    <div>
      <video ref="video01" width="500" height="500" controls></video>
    </div>
  </div>
</template>

<script>
import Hls from "hls.js";

export default {
  data() {
    return {
      videoInfo: {
        videoUrl: "",
      },
      rules: {
        videoUrl: [
          {
            required: true,
            message: "请输入视频地址",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    async onPlay() {
      if (
          this.videoInfo.videoUrl === undefined ||
          this.videoInfo.videoUrl === ""
      ) {
        return;
      }

      let hls = new Hls();
      let stream = this.videoInfo.videoUrl;
      let video = this.$refs["video01"];
      hls.loadSource(stream);
      hls.attachMedia(video);
      hls.on(Hls.Events.MEDIA_ATTACHED, function () {
        video.muted = true;
        video.play();
      });
    },
  },
  mounted() {
  },
};
</script>

<style scoped>
body {
  margin: 0px;
  padding: 0px;
}

#app {
  padding: 0px;
  margin: 0px;
}

.video-window {
  border: 5px solid red;
}
</style>
