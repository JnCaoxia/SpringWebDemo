<%@page import="com.caox.listen.User"%>
<%@page import="com.caox.listen.MySessionListener"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
//    //�������
//    session.setAttribute("test", "Dragon");
//    //�滻����
//    session.setAttribute("test", "long");
//    //ɾ������
//    session.removeAttribute("test");

    //����һ��uesr���󲢽�����ӵ�session��
    session.setAttribute("uesr", new User());

    //���ոմ�����uesr�����session��ɾ��
    session.removeAttribute("uesr");
%>


<html>
<body>
<h2>Hello World!</h2>
<h2>����һ�����Ĳ�զ������ҳ��</h2>
<hr>
<h4>��ǰ���ߵ��û������� ${OnlinePeople }</h4>
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
            alert("�ǹرն���ˢ��");
            window.event.returnValue = "<%System.out.println("a123"); %>"; //������Է����������Ĳ�������
        }
    }

    $(window).unload(function(){
        //������д�ڹر�ҳ��ʱ��Ҫ���õ��¼�
        alert("ҳ��Ҫ�ر���");
    });


</script>
