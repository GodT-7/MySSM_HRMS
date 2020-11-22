/**
 * @name: index
 * @author: surui
 * @date: 2020-11-22 14:25
 * @description：index
 * @update: 2020-11-22 14:25
 */
window.addEventListener("load" ,function () {
    // 禁止选中文字 与右键菜单
    var span = document.querySelector('span');
    span.addEventListener("contextmeau",function (e) {
        e.preventDefault();
    })
    span.addEventListener("selectstart",function (e) {
        e.preventDefault();
    })

    // 提交新句子  模态框
    var p = document.querySelector('footer').querySelectorAll('p');
    for (let i = 0 ; i < p.length ; i++) {
        p[i].addEventListener('click',function () {
            
        })
    }
})