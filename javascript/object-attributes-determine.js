let obj = {a: 0}
let obj2 = {a: 1}

// (obj.a) 和 (obj.a !== undefined && obj.a !== null && obj.a !== "" && obj.a * 1 > 0) 用法相同
if (obj.a !== undefined && obj.a !== null && obj.a !== "" && obj.a * 1 > 0) {
    // 不输出
    console.log("obj attibutes exist")
}

if (obj.a) {
    // 不输出
    console.log("obj attibutes exist")
}

if (obj2.a !== undefined && obj2.a !== null && obj2.a !== "" && obj2.a * 1
    > 0) {
    // 输出
    console.log("obj2 attibutes exist")
}

if (obj2.a) {
    // 输出
    console.log("obj2 attibutes exist")
}