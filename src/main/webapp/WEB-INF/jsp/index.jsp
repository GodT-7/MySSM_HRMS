<%--
  Created by IntelliJ IDEA.
  User: 83965
  Date: 2020/10/22
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,user-scalable=0,minimum-scale=1.0,maximum-sclae=1.0">
    <title>网抑云~将自己释放</title>
    <!--  引入title图标  -->
    <link rel="icon" href="../../PIC/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="../../PIC/favicon.ico" type="image/x-icon">
    <!-- 引入响应式css-->
    <link rel="stylesheet" href="../../CSS/bootstrap3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../CSS/bootstrap3.3.7/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="../../CSS/bootstrap3.3.7/css/bootstrap-theme.min.css.map">
    <!--  全局样式  -->
    <link rel="stylesheet" href="../../CSS/rest.css">
    <link rel="stylesheet" href="../../CSS/index.css">
    <!-- 全局js-->
    <script src="../../JS/index.js"> </script>
    <script src="../../JS/prefixfree.min.js"> </script>
    <script src="../../JS/animate.js"></script>
    <!--响应式框架js-->
    <script src="../../JQ/jquery.ripples.js"></script>
    <script src="../../JS/index.js"></script>
    <script rel="stylesheet" src="../../JQ/jquery-3.3.1.min.js"></script>
<%--    <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>--%>

    <script src="../../CSS/bootstrap3.3.7/js/bootstrap.min.js"> </script>
<%--    <script src="http://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>--%>
    <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" crossorigin="anonymous"></script>-->

</head>
<body>
    <!--头部  -->
    <header>
        <div class="logo">
        </div>
        <div class="time">
        </div>
        <!-- 登陆接口-->
        <div class="login_win" id="login_win">
            <img src="PIC/via.jpg" alt="via" id="img_">
        </div>
    </header>
    <!-- content   -->
    <article>
        <span>生而为人，我很抱歉</span>
        <p id="author">太宰治</p>
    </article>
    <section>
        <button class="btn" id="next_btn">
            <b>再来一句</b>
            <canvas width="120" height="40"></canvas>
        </button>
        <span class="glyphicon glyphicon-heart" id="heart"></span>
        <button class="btn">
            <b>复制</b>
            <canvas width="120" height="40"></canvas>
        </button>
    </section>
    <!--  音乐接口   -->
    <div class="music">
        <audio autoplay loop   src="xi.mp3" >
        </audio>
    </div>
    <!-- footer   -->
    <footer>
        <p><a href="script:;">提交新的句子</a></p>
        <p><a href="script:;">用法</a></p>
        <p style="font-size: 10px">版权所有<em>©</em>thk&ksr</p>
    </footer>

    <!--模态框-->
    <form action="" method="post" enctype="multipart/form-data" class="login_form" id="form_">
        <div class="title">
            登陆到网易云
            <span> <a href="script:;"><i class="glyphicon glyphicon-remove"></i></a></span>
        </div>
        <div class="login_input">
            <div class="login_content"> 用&nbsp;户&nbsp;名&nbsp;：<input type="tel" name="username" id="username" autocomplete="off"></div>
            <div class="login_content"> 登陆密码：<input type="password" name="password" id="password" autocomplete="off"></div>
        </div>
        <div class="login_btn">
            <a href="javascript:;" id="login-button-submit">登录会员</a>
        </div>
        <div class="go_register">
            <a href="javascript:;"> 啊咧~没有账号，去注册~</a>
        </div>
    </form>
    <div class="login_hidden"></div>


<script type="text/javascript">
    var heart1 = document.querySelector('section').querySelector('span');
    heart1.setAttribute("sentenceId",1);
    $(function () {
        $("#next_btn").click(function () {
            $.ajax({
                url:"/sentence/findNextSentence",
                type:"GET",
                success:function (result) {
                    if(result.code == 100){
                        let span = document.querySelector("span");
                        var sentence = result.extendInfo.sentence;
                        span.innerHTML=sentence.sentence;
                        let p = document.querySelector("p");
                        p.innerHTML=sentence.author;
                        heart1.setAttribute("sentenceId",sentence.id);
                        $.ajax({
                            url:"/collection/collected?sentenceId="+heart1.getAttribute("sentenceId"),
                            type:"GET",
                            success:function (result) {
                                if(result.code == 100){
                                    let isok = result.extendInfo.isok;
                                    if(isok == "isok_collect"){
                                        heart1.style.color = 'deepskyblue';
                                    }else{
                                        heart1.style.color = 'black';
                                    }
                                }
                            }
                        });
                    }else {
                        alert(result.extendInfo.error);
                    }
                }
            });
        });

        $("#heart").click(function () {
            $.ajax({
                url:"/collection/collect?sentenceId="+heart1.getAttribute("sentenceId"),
                type:"GET",
                success:function (result) {
                    if(result.code == 100){
                        let isok = result.extendInfo.isok;
                        if(isok == "isok_collect"){
                            heart1.style.color = 'deepskyblue';
                        }else{
                            heart1.style.color = 'black';
                        }
                    }else {
                        heart1.style.color = 'black';
                        let errorMsg = result.extendInfo.error;
                        if(errorMsg == "没有登陆"){
                            let login_form = document.querySelector('.login_form');
                            let mask = document.querySelector('.login_hidden');
                            motai_start(login_form,mask);
                        }else
                            alert(errorMsg);
                    }
                }
            });
        });

        $("#login-button-submit").click(function () {
            $.ajax({
                url:"/wyy/dologin",
                type:"POST",
                data:$("#form_").serialize(),
                success:function (result) {
                    if(result.code == 100){
                        let login_form = document.querySelector('.login_form');
                        let mask = document.querySelector('.login_hidden');
                        motai_end(login_form,mask);
                        let login_win  = document.querySelector('.login_win');  // 获取登陆显示窗口
                        let img = document.querySelector('img');                // 获取头像
                        login_win.style.display = 'block';
                        img.src = "PIC/via.jpg";
                        $.ajax({
                            url:"/collection/collected?sentenceId="+heart1.getAttribute("sentenceId"),
                            type:"GET",
                            success:function (result) {
                                if(result.code == 100){
                                    let isok = result.extendInfo.isok;
                                    if(isok == "isok_collect"){
                                        heart1.style.color = 'deepskyblue';
                                    }else{
                                        heart1.style.color = 'black';
                                    }
                                }
                            }
                        });
                    }else {
                        let errorMsg = result.extendInfo.error;
                        alert(errorMsg);
                    }
                }
            });
        });
    });
</script>
</body>
</html>
