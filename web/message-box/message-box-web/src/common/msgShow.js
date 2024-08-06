export default {
    errMsgShow(vue, msg) {
        vue.$notify.error({
            title: '错误',
            message: msg
        });
    },
    sucMsgShow(vue, msg) {
        vue.$notify({
            title: '成功',
            message: msg,
            type: 'success'
        });
    },
    infoMsgShow(vue, msg) {
        vue.$message({
            type: '提示',
            message: msg
        })
    }
}