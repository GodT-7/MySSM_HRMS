/**
 * @name: index
 * @author: surui
 * @date: 2020-11-22 14:25
 * @description：index
 * @update: 2020-11-22 14:25
 */

window.addEventListener("load" ,function () {
    // 鼠标点击出现水波 canvas 动画
    var canvas = {},
        centerX = 0,
        centerY = 0,
        color = '',
        context = {},
        element = {},
        radius = 0,
        containers = document.querySelectorAll('.btn');


        requestAnimFrame = function () {
            return (
                window.requestAnimationFrame       ||
                window.mozRequestAnimationFrame    ||
                window.oRequestAnimationFrame      ||
                window.msRequestAnimationFrame     ||
                function (callback) {
                    window.setTimeout(callback, 1000 / 60);
                }
            );
        } (),

        init = function () {
            containers = Array.prototype.slice.call(containers);
            for (var i = 0; i < containers.length; i += 1) {
                canvas = document.createElement('canvas');
                canvas.addEventListener('mouseenter', enter, false);
                canvas.addEventListener('mouseout',function () {
                    color = '#fff';
                },false);
                containers[i].appendChild(canvas);
                canvas.style.width ='100%';
                canvas.style.height='100%';
                canvas.width  = canvas.offsetWidth;
                canvas.height = canvas.offsetHeight;
            }
        },
        enter = function (e) {
            // color = e.toElement.parentElement.dataset.color;
            color= 'deepskyblue';
            element = e.toElement;
            context = element.getContext('2d');
            radius = 0;
            centerX = e.offsetX;
            centerY = e.offsetY;
            context.clearRect(0, 0, element.width, element.height);
            draw();
        },
        draw = function () {
            context.beginPath();
            context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
            context.fillStyle = color;
            context.fill();
            radius += 2;
            if (radius < element.width) {
                requestAnimFrame(draw);
            }
        };
    init();

    // 鼠标样式
    var footer = document.querySelector('footer');
    footer.addEventListener('mouseenter',function () {
        this.style.cursor = 'pointer';
    })
    var article = document.querySelector('article');
    article.addEventListener('mouseenter',function () {
        this.style.cursor = 'default';
    })

    // 页脚跳转
    footer.addEventListener('click',function () {
        location.href = 'https://gitee.com/KSRsusu';
    })

    // logo 跳转官网
    var logo = document.querySelector('.logo');
    logo.addEventListener('click',function () {
        location.href = 'https://music.163.com/';
    })

    logo.addEventListener('mouseenter',function () {
        this.style.cursor = 'pointer';
    })

    // 时间
    var time = document.querySelector('.time');
    function date() {
        let now = new Date();
        let h = now.getHours();
        h = h >= 10 ? h : '0' + h;
        let m = now.getMinutes();
        m = m >= 10 ? m : '0' + m;
        let s = now.getSeconds();
        s = s >= 10 ? s : '0' + s;
        time.innerHTML =  h+ ':' + m + ':' + s;
    }
    date();
    setInterval(date,1000);



    // 登录头像转换
    var login_win  = document.querySelector('.login_win');  // 获取登陆显示窗口
    var img = document.querySelector('img');                // 获取头像
    login_win.style.display = 'none';

    // button 点击边框取消
    var btn = document.querySelectorAll('.section .btn');

    // 复制
    $('#btn').click(function () {
        console.log(1);
        var val = $('#text').val();
        Clipboard.copy(val);
    })

    // 禁止选中文字 与右键菜单
    var span = document.querySelector('span');
    span.addEventListener("contextmeau",function (e) {
        e.preventDefault();
    })

    span.addEventListener("selectstart",function (e) {
        e.preventDefault();
    })

    // 收藏 爱心
    var heart = document.querySelector('section').querySelector('span');
    heart.addEventListener('click',function () {
        heart.style.color = 'deepskyblue';
    })
    heart.addEventListener('mouseenter',function () {
        heart.style.cursor = 'pointer'
    })


    // 提交新句子  模态框
    var p = document.querySelector('footer').querySelectorAll('p');
    var login_form = document.querySelector('.login_form');
    var mask = document.querySelector('.login_hidden');
    var closebtn = login_form.querySelector('span');
    for (let i = 0 ; i<p.length ; i++) {
        p[i].addEventListener('click',function(){
            motai_start(login_form,mask);
        })
    }
    closebtn.addEventListener('click',function () {
        motai_end(login_form,mask);
    })

    // 模态框拖拽
    login_form.addEventListener('mousedown',function(e){
        this.style.cursor = 'move';
        var x = e.pageX - this.offsetLeft;
        var y = e.pageY - this.offsetTop;
        login_form.addEventListener('mousemove',move)

        function move(e){
            this.style.cursor = 'move';
            this.style.left = e.pageX - x + 'px';
            this.style.top = e.pageY - y + 'px';
        }
        login_form.addEventListener('mouseup',function(){
            this.style.cursor = 'default';
            login_form.removeEventListener('mousemove',move);
        })
    })
})

function motai_start(login_form,mask) {
    login_form.style.display = 'block';
    mask.style.display = 'block';
}
function motai_end(login_form,mask) {
    login_form.style.display = 'none';
    mask.style.display = 'none';
}
