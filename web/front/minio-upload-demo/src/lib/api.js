import axios from 'axios'
import axiosExtra from 'axios-extra'
const baseUrl = 'http://localhost:8080'

const http = axios.create({
    baseURL: baseUrl
})

const httpExtra = axiosExtra.create({
    maxConcurrent: 5, //并发为1
    queueOptions: {
        retry: 3, //请求失败时,最多会重试3次
        retryIsJump: false //是否立即重试, 否则将在请求队列尾部插入重试请求
    }
})

http.interceptors.response.use(response => {
    return response.data
})

/**
 * 根据文件的md5获取未上传完的任务
 * @param identifier 文件md5
 * @returns {Promise<AxiosResponse<any>>}
 */
const taskInfo = (identifier) => {
    return http.get(`/v1/minio/tasks/${identifier}`)
}

/**
 * 初始化一个分片上传任务
 * @param identifier 文件md5
 * @param fileName 文件名称
 * @param totalSize 文件大小
 * @param chunkSize 分块大小
 * @returns {Promise<AxiosResponse<any>>}
 */
const initTask = ({ identifier, fileName, totalSize, chunkSize }) => {
    return http.post('/v1/minio/tasks', {identifier, fileName, totalSize, chunkSize})
}

/**
 * 获取预签名分片上传地址
 * @param identifier 文件md5
 * @param partNumber 分片编号
 * @returns {Promise<AxiosResponse<any>>}
 */
const preSignUrl = ({ identifier, partNumber }) => {
    return http.get(`/v1/minio/tasks/${identifier}/${partNumber}`)
}

/**
 * 合并分片
 * @param identifier
 * @returns {Promise<AxiosResponse<any>>}
 */
const merge = (identifier) => {
    return http.post(`/v1/minio/tasks/merge/${identifier}`)
}

export {
    taskInfo,
    initTask,
    preSignUrl,
    merge,
    httpExtra
}
