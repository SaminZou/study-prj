import SparkMD5 from 'spark-md5'
const DEFAULT_SIZE = 20 * 1024 * 1024
const md5 = (file, chunkSize = DEFAULT_SIZE) => {
    return new Promise((resolve, reject) => {
        const startMs = new Date().getTime();
        let blobSlice =
            File.prototype.slice ||
            File.prototype.mozSlice ||
            File.prototype.webkitSlice;
        let chunks = Math.ceil(file.size / chunkSize);
        let currentChunk = 0;
        let spark = new SparkMD5.ArrayBuffer(); //追加数组缓冲区。
        let fileReader = new FileReader(); //读取文件
        fileReader.onload = function (e) {
            spark.append(e.target.result);
            currentChunk++;
            if (currentChunk < chunks) {
                loadNext();
            } else {
                const md5 = spark.end(); //完成md5的计算，返回十六进制结果。
                console.log('文件md5计算结束，总耗时：', (new Date().getTime() - startMs) / 1000, 's')
                resolve(md5);
            }
        };
        fileReader.onerror = function (e) {
            reject(e);
        };

        function loadNext() {
            console.log('当前part number：', currentChunk, '总块数：', chunks);
            let start = currentChunk * chunkSize;
            let end = start + chunkSize;
            (end > file.size) && (end = file.size);
            fileReader.readAsArrayBuffer(blobSlice.call(file, start, end));
        }
        loadNext();
    });
}

export default md5
