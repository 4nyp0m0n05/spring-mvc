<%-- 
    Document   : add
    Created on : 19.Mar.2017, 15:24:41
    Author     : Anypomonos
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
        .text3{
            box-shadow:1px 1px 45px #333;
            transition: 200ms all ease;
            border:none;
            padding: 5px 5px;
        }
        .text3:hover{
            margin:-5px 0 0 -5px;
            height:29px;
            width:250px;
        }
           h3{
            background-color: blue;
        }
        table{
            background-color: #bada55;
            
        }
        
        html {
            background:#018DED url(http://unsplash.it/1500/1000?image=881&blur=50);
            background-size:cover;
            font-family:'helvetica neue';
            text-align: center;
            font-size: 10px;
          }

        body {
          font-size: 2rem;
          display:block;
          flex:1;
          min-height: 100vh;
          align-items: center;
        }

        .clock {
          width: 30rem;
          height: 30rem;
          border:20px solid white;
          border-radius:50%;
          margin:50px auto;
          position: relative;
          padding:2rem;
          box-shadow:
            0 0 0 4px rgba(0,0,0,0.1),
            inset 0 0 0 3px #EFEFEF,
            inset 0 0 10px black,
            0 0 10px rgba(0,0,0,0.2);
        }

        .clock-face {
          position: relative;
          width: 100%;
          height: 100%;
          transform: translateY(-3px); /* account for the height of the clock hands */
        }

        .hand {
          width:50%;
          height:6px;
          background:black;
          position: absolute;
          top:50%;
          transform-origin: 100%;
          transform: rotate(90deg);
          transition: all 0.05s;
          transition-timing-function: cubic-bezier(0.1, 2.7, 0.58, 1);
        }
    </style>
    
</head>
<body>
    <div class="clock">
      <div class="clock-face">
        <div class="hand hour-hand"></div>
        <div class="hand min-hand"></div>
        <div class="hand second-hand"></div>
      </div>
    </div>
    <center>
    <c:url var="addUser" value="/users/add"></c:url>
    <form action="${addUser}" method="POST" class="selectForm" onsubmit="return control()">
        <table>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <tr>
                    <td>
                        
                        <spring:message text="ID:"/>
                        <input  name="id" class="text3" value="${users.id}"/>
                    </td>
                    
                </tr>
                <tr>
                    <td>
                        <spring:message text="User:"/>
                        
                       
                         
                              <input name="username" class="text3" value="${users.username}"/>
                       
                    </td>
                   
                </tr>
                <tr>
                    <td>
                        <spring:message text="Password:"/>
                        <input name="password" class="text3" value="${users.password}"/>
                    </td>
                    
                </tr>
                <tr>
                    <td>
                        <spring:message text="Record Number:"/>
                        <input name="recordnum" class="text3" value="${users.recordnum}"/>
                    </td>
                    
                </tr>
                <tr>
                    <td>
                        <spring:message text="Email:"/>
                        <input  name="email" class="text3" value="${users.email}"/>
                    </td>
                    
                </tr>
                <tr>
                    <td>
                        <spring:message text="Phone:" />
                        <input  name="phone" class="text3" value="${users.phone}"/>
                    </td>
                    
                </tr>
                <tr>
                    <td>
                        <spring:message text="Department:"/>
                        <input name="department" class="text3" value="${users.department}"/>
                    </td>
                    
                </tr>
                <tr>
                    <td>
                        <spring:message text="Department Manager:"/>
                        <input name="departmanager" class="text3" value="${users.departmanager}"/>
                    </td>
                    
                </tr>
                <tr>
                    <td>
                      <input name="role" type="hidden" value="ROLE_USER"/>
                        
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty users.username}">
                            <input type="Submit" value="<spring:message text="Edit"/>"
                        </c:if>
                        <c:if test="${empty users.username}">
                            <input type="Submit" value="<spring:message text="Add"/>"
                        </c:if>   
                    </td>
                </tr>
        </table>
    </form>
    
    <br/>
    
  
    <h3>Information</h3>
    <c:if test="${!empty userDetails}">
        <table >
            <tr>
                <th>Person Id</th>
                <th>Person Name </th>
                <th>Email</th>
                <th>Department</th>
                <th>Department Manager</th>
                
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            
            
                <tr>
                    <td>${userDetails.id}</td>
                    <td>${userDetails.username}</td>
                    
                    <td>${userDetails.email}</td>
                    <td>${userDetails.department}</td>
                    <td>${userDetails.departmanager}</td>
                    <td><a href="<c:url value='/users/edit/${userDetails.username}'/>">Edit</a></td>
                    <td><a href="<c:url value='/users/remove/${userDetails.username}'/>">Remove</a></td>
                </tr>
            
        </table>
    </c:if>
    <c:url value="/j_spring_security_logout" var="logoutUrl" />

	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden"
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
                                <a href="<c:url value="/" />">Home</a>
		</h2>
	</c:if>
    </center>
        <script>
  const secondHand = document.querySelector('.second-hand');
  const minsHand = document.querySelector('.min-hand');
  const hourHand = document.querySelector('.hour-hand');
  const formThis=document.querySelector('form');
  
  function setDate() {
    const now = new Date();
    
    const seconds = now.getSeconds();
    const secondsDegrees = ((seconds / 60) * 360) + 90;
    secondHand.style.transform = `rotate(`+secondsDegrees+`deg)`;
    
    const mins = now.getMinutes();
    const minsDegrees = ((mins / 60) * 360) + 90;
    minsHand.style.transform = `rotate(`+minsDegrees+`deg)`;

    const hour = now.getHours();
    const hourDegrees = ((hour / 12) * 360) + 90;
    hourHand.style.transform = `rotate(`+hourDegrees+`deg)`;
  }

  setInterval(setDate, 1000);

  setDate();
  document.onmousedown=disableclick;status="Right Click Disabled";function disableclick(event){ if(event.button==2) { alert(status); return false; }}
  let form=document.querySelector('.selectForm');
  let username=form.querySelector('input[name="username"]');
  let password=form.querySelector('input[name="password"]');
  let recordnum=form.querySelector('input[name="recordnum"]');
  let email=form.querySelector('input[name="email"]');
  let phone=form.querySelector('input[name="phone"]');
  let department=form.querySelector('input[name="department"]');
  let departmanager=form.querySelector('input[name="departmanager"]');
  //var re = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
  let re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  function control(){
      if(username.value==""||password.value==""||password.value.length<6||recordnum.value.length<11||email.value==""||department.value==""||phone.value.length<10||departmanager.value.lentgth==""){
          alert("Error");
          return false;
      }
      
      
      if(re.test(email.value)){
          return true;
      }else{
          alert("Email error");
          return false;
      }
  }
</script>
</body>
</html>
