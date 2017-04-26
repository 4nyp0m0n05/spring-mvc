<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Home Page</title>
<style>
:root{
	--base:#ffc600;
}
html {
      font-family: sans-serif;
      background:var(--base);
    }
.box{
	display:inline-block;
	margin:40px;
	border-radius:5px;
	box-shadow:10px 10px 0 rgba(0,0,0,0.1);
	height:125px;
	width:250px;
	background:rgba(0, 184, 255, 0.61);

	
}

center{
      
	margin:0px;
    padding:35px;
    font-size: 20px;
    font-weight: 200;
    border-left: 1px solid #D1E2FF;
    }
</style> 

</head>
<body>
        
	<div>
	<label for="base">Color:</label>
	<input type="color" name="base" value="#ffc600" >
	</div>

	<div class="box" >
            <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
            <p><center> 
                <c:if test="${role =='ROLE_USER'}">
                    <a href="${contextPath}/users/ustravel"> Seyahat </a>
                </c:if>
                <c:if test="${role =='ROLE_ADMIN'}">
                    <a href="${contextPath}/admin/ustravel"> Seyahat </a>
                </c:if>
                <c:if test="${empty role }">
                     Seyahat 
                </c:if>
            </center></p>
	
	</div>
	<div class="box" >
            <p><center> <a href="${contextPath}/login">Login</a> </center></p>
	
	</div>
	<div class="box" >
	<p><center> 
                <c:if test="${role =='ROLE_ADMIN'}">
                    <a href="${contextPath}/admin/user"> Users </a>
                </c:if>
                <c:if test="${role =='ROLE_USER'}">
                    <a href="${contextPath}/users/user"> User </a>
                </c:if>
                <c:if test="${empty role }">
                      Şimdilik boş
                </c:if>
        </center></p>
	
	</div>
	
	<div class="box" >
	<p><center> Şimdilik boş </center></p>
	
	</div>
	<div class="box">
	<p><center> Şimdilik boş </center></p>
	
	</div>
	
	<script>
	const input=document.querySelector('input');
	function handleUpdate(){
		console.log(this.value);
		const suffix=this.dataset.sizing||'';
		console.log(suffix);
		document.documentElement.style.setProperty(`--base`,this.value+suffix);//$ {this.name} bir sey dondurmediginden
		
	}
	input.addEventListener('change',handleUpdate);

	</script>
</body>
</html>
