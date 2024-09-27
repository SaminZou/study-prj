let obj = { a: 0}
let obj2 = { a: 1}

// 以下两个用法等同
if (obj.a !== undefined && obj.a !== null && obj.a !== "" && obj.a * 1 > 0){
    console.log("obj attibutes exist")
}

if (obj.a){
    console.log("obj attibutes exist")
}

if (obj2.a !== undefined && obj2.a !== null && obj2.a !== "" && obj2.a * 1 > 0){
    console.log("obj2 attibutes exist")
}

if (obj2.a){
    console.log("obj2 attibutes exist")
}