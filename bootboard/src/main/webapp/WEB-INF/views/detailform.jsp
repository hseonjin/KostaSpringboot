<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="page" value="${page==null?1:page}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판글상세</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src=" jquery.lightbox-0.5.js"></script>
    <style>
        h2 {
            text-align: center;
        }

        table {
            margin: 0 auto;
            /* width: 450px; */
        }

        .left {
            width: 150px;
            background: orange;
        }

        .right {
            width: 300px;
            background: skyblue;
        }

        .btn {
            margin-top: 10px;
            text-align: center;
        }
		#likebtn {
		  text-align: center; 
		}
		
		#heart {
		  margin: 30px auto 10px auto; 
		}
    </style>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    
    <script>
    	$(function() {
    		$('#heart').click(function() {
    			$.ajax({
    				url: '${contextPath}/like',
    				type: 'post',
    				data: {'num' : '<c:out value="${board.num}"/>'},
    				success: function(res) {
    					console.log(res.select);
    					// console.log(res.likecount);
    					if(res=="true") {
    						$("#heart").attr("src", "${contextPath}/resources/img/afterlike.png")
                            $("#likecount").text(+$("#likecount").text()+1);
    					} else {
    						$("#heart").attr("src", "${contextPath}/resources/img/beforelike.png")
                            $("#likecount").text(+$("#likecount").text()-1);
                        }
    				},
    				error:function(err) {
    					console.log(err);
    				}
    			})
    		})
    	})
    </script>
</head>
<body>
<jsp:include page="main.jsp"/>
    <div class="container">
        <h2>게시판글상세</h2>
        <form action="${contextPath}/boardmodify" method="post">
            <table>
                <tr>
                    <td class="left"><label for="writer">글쓴이</label></td>
                    <td class="right"><div>${board.writer }</div>
                </tr>
                <tr>
                    <td class="left"><label for="title">제 목</label></td>
                    <td class="right"><div>${board.subject }</div></td>
                </tr>
                <tr>
                    <td class="left"><label for="content">내 용</label></td>
                    <td><div style="height:100px;">${board.content }</div></td>
                </tr>
<%--                <c:if test="${board.fileurl ne null }">--%>
                <tr>
                    <td class="left"><label for="writer">이미지</label></td>
                    <td>
                        <c:choose>
                            <c:when test="${board.fileurl ne null}">
                                <img src="${contextPath}/image/${board.fileurl}" width="100px" height="100px"/>
                            </c:when>
                            <c:otherwise>
                                <img src="${contextPath}/resources/img/image.png" width="100px" height="100px"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
<%--                    <td><img src="image?file=${board.fileurl }" alt="" width="100" height="100" ></td>--%>
                </tr>
<%--                </c:if>--%>
            </table>
        </form>
        <div id="likebtn">
            <c:if test="${user ne Empty }">
				<c:choose>
				    <c:when test="${select eq true}">
				        <img alt="after" src="${contextPath}/resources/img/afterlike.png" width="50px" height="50px" id="heart">
				    </c:when>
				    <c:otherwise>
				        <img alt="before" src="${contextPath}/resources/img/beforelike.png" width="50px" height="50px" id="heart">
				    </c:otherwise>
				</c:choose>
            </c:if>
         </div>
        <div class="btn">
            <c:if test="${user.id eq board.writer }">
                <a href="${contextPath}/boardmodify/${board.num}/${page}">수정</a>&nbsp;&nbsp;
            </c:if>
            <a href="${contextPath}/boardlist/${page}">목록</a>&nbsp;&nbsp;
            좋아요(<span id="likecount">${board.likecount}</span>)&nbsp;&nbsp;
        </div>

    </div>
</body>

</html>