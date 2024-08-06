module.exports = {
    publicPath: process.env.NODE_ENV === 'production-xx' ||
    process.env.NODE_ENV === 'production-yd'
        ? '/msgboxweb/'
        : ''
};

console.log("打包环境为：" + process.env.NODE_ENV);