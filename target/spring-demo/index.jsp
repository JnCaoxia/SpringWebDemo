<%@page import="com.caox.listen.User"%>
<%@page import="com.caox.listen.MySessionListener"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
//    //添加数据
//    session.setAttribute("test", "Dragon");
//    //替换数据
//    session.setAttribute("test", "long");
//    //删除数据
//    session.removeAttribute("test");

    //创建一个uesr对象并将其添加到session中
    session.setAttribute("uesr", new User());

    //将刚刚创建的uesr对象从session中删除
    session.removeAttribute("uesr");
%>


<html>
<body>
<h2>Hello World!</h2>
<h2>我是一个长的不咋样的主页面</h2>
<hr>
<h4>当前在线的用户人数： ${OnlinePeople }</h4>
</body>
</html>

<script type="text/javascript">
    <%--window.onbeforeunload = onbeforeunload_handler;--%>
    <%--function onbeforeunload_handler() {--%>
        <%--<%System.out.println("a123"); %>--%>
    <%--}--%>

    window.onbeforeunload = function() {
        var n = window.event.screenX - window.screenLeft;
        var b = n > document.documentElement.scrollWidth-20;
        if(b && window.event.clientY < 0 || window.event.altKey)
        {
            alert("是关闭而非刷新");
            window.event.returnValue = "<%System.out.println("a123"); %>"; //这里可以放置你想做的操作代码
        }
    }

    $(window).unload(function(){
        //这里面写在关闭页面时，要调用的事件
        alert("页面要关闭了");
    });


</script>
