<%--
  Created by IntelliJ IDEA.
  User: Gavin
  Date: 2021/11/2
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>影视信息展示</title>

    <style type="text/css">
        table {
            border: 1px solid black;
            margin: auto;
            width: 88%;
        }

        td, th {
            border: 1px solid black;

        }


    </style>
    <script src="js/jquery.min.js"></script>
   <%-- <script src="https://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>--%>
    <script>
        function changePage(currentPage){
            if(currentPage<1){
                alert("当前已经是第一页了,再往前没有了哦");
                return;
            }
            if(currentPage>${requestScope.pageBean.totalPage}){
                alert("当前已经是最后一页了,再往后没有了哦");
                return;
            }
           /* var PageSizeDoc= document.getElementById("pageSize");
            var val= PageSizeDoc.getAttribute("value");*/
            window.location.href="ShowFilmController.do?filmName="+$("#filmName").val()+"&filmDesc="+$("#filmDesc").val()+"&currentPage="+currentPage+"&pageSize="+$("#pageSize").val()

        }


    </script>
</head>
<body>
<div style="text-align: center">

    片名查询<input type="text"   id="filmName" value="${requestScope.filmName}" placeholder="请输入片名关键字">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    剧情查询<input type="text" id="filmDesc" value="${requestScope.filmDesc}" placeholder="请输入剧情关键字">
    <input type="button" value="查询" onclick="changePage(1)">


</div>



<table align="center" cellspacing="0px">
    <tr>
        <th>film_id</th>
        <th>title</th>
        <th>description</th>
        <th>release_year</th>
        <th>rental_duration</th>
        <th>rental_rate</th>
        <th>length</th>
        <th>replacement_cost</th>
        <th>rating</th>
        <th>special_features</th>
    </tr>
    <c:forEach items="${requestScope.pageBean.data}" var="film">

        <tr>
            <td>${film.film_id}</td>

            <td>${film.title}</td>
            <td>${film.description}</td>

            <td>${film.release_year}</td>
            <td>${film.rental_duration}</td>

            <td>${film.rental_rate}</td>
            <td>${film.length}</td>

            <td>${film.replacement_cost}</td>
            <td>${film.rating}</td>

            <td>${film.special_features}</td>
            <td>
                <a href="#">删除</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="11" align="center">
            <a href="javascript:void(0)"  onclick="changePage(${requestScope.pageBean.currentPage}-1)">上一页</a>&nbsp;&nbsp;&nbsp;
            <c:forEach begin="${requestScope.pageBean.currentPage}" end="${requestScope.pageBean.currentPage+5 lt pageBean.totalPage ? requestScope.pageBean.currentPage+5:pageBean.totalPage}"
                       var="pageNum">
                <c:choose>
                    <c:when test="${pageNum eq pageBean.currentPage}">[${pageNum}]</c:when>
                    <c:otherwise>${pageNum}</c:otherwise>
                </c:choose>

            </c:forEach>.....${requestScope.pageBean.totalPage}&nbsp;&nbsp;&nbsp;



            <a href="javascript:void(0)" onclick="changePage(${requestScope.pageBean.currentPage}+1)">下一页</a>&nbsp;&nbsp;&nbsp;
           <a href="javascript:void(0)" onclick="changePage(${pageBean.totalPage})"> 尾页</a>&nbsp;&nbsp;&nbsp;
            每页&nbsp;&nbsp;<input type="text" id="pageSize" value="${requestScope.pageBean.pageSize} " style="width: 30px">&nbsp;&nbsp;条记录&nbsp;&nbsp;&nbsp;
            当前第&nbsp;&nbsp;${requestScope.pageBean.currentPage}&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;
            总共 &nbsp;&nbsp;${requestScope.pageBean.totalPage}&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;
        </td>
    </tr>


</table>
</body>
</html>
