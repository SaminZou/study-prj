import Axios from "axios";
import config from "../config/config"

export default {
    get(url, configs) {
        return new Promise((resolve, reject) => {
            Axios.get(config.baseUrl + url,
                configs
            ).then(function (res) {
                let data = res.data;
                if (data['code'] === "0") {
                    resolve(data['data']);
                } else {
                    reject(data['msg']);
                }
            }).catch(err => {
                reject(err);
            });
        })
    },
    post(url, data, configs) {
        return new Promise((resolve, reject) => {
            Axios.post(config.baseUrl + url,
                data,
                configs
            ).then(function (res) {
                let data = res.data;
                if (data['code'] === 0) {
                    resolve(data['data']);
                } else {
                    reject(data['msg']);
                }
            }).catch(err => {
                reject(err);
            });
        });
    }
}