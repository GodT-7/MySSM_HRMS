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
    <!--响应式框架js-->
    <script src="JS/index.js"> </script>
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
    <!-- 登陆接口-->
    <div class="login">
        登陆接口
    </div>
</header>
<!-- content   -->
<article>
    <span id="sentence">人在饿的时候会选择不爱的食物，会在寂寞的时候选择不爱的人。因为强扭的瓜不甜，但是解渴。</span>
    <span id="sentence_author">12</span>
</article>
<section>
    <button class="btn" id="next_btn">
        再来一句
    </button>
    <button class="btn">
        <b>复制</b>
    </button>
</section>
<!-- footer   -->
<footer>
    <p><a href="script:;">提交新的句子</a></p>
    <p><a href="script:;">用法</a> </p>
</footer>

<script type="text/javascript">
    $(function () {
        $("#next_btn").click(function () {

            $.ajax({
                url:"/sentence/findNextSentence",
                type:"GET",
                success:function (result) {
                    if(result.code == 100){
                        let span = document.querySelector("span");
                        span.innerHTML=result.extendInfo.sentence;
                    }else {
                        alert(result.extendInfo.error);
                    }
                }
            });
        });
    });
</script>
</body>
</html>
