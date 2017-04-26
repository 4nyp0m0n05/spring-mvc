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
    <script src="<c:url value="/resources/excel.js" />"></script>
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
        #test{
            background-color: #bada55;
        }
    </style>
   <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
</head>
<body>
    <div class="clock">
      <div class="clock-face">
        <div class="hand hour-hand"></div>
        <div class="hand min-hand"></div>
        <div class="hand second-hand"></div>
      </div>
    </div>
    <%--${dt} --%>
    <center>
    <c:url var="addTravel" value="/users/addtravel"></c:url>
    <form action="${addTravel}" method="POST">
        <table>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <tr>
                    <td>
                        
                        <spring:message text="ID:"/>
                        <c:if test="${!empty travels.travelid}">
                            <input  name="id" readonly="true" class="text3" value="${travels.travelid}"/>
                        </c:if>
                        <c:if test="${empty travels.travelid}">
                            <input  name="id" readonly="true" class="text3" value="0"/>
                        </c:if>
                        
                    </td>
                    
                </tr>
                
                <input type="hidden" name="username" class="text3" value="${pageContext.request.userPrincipal.name}"/>
                        
                   
                <tr>
                    <td>
                        <spring:message text="Place:"/>
                        <input name="place" class="text3" value="${travels.place}"/>
                    </td>
                    
                </tr>
                <tr>
                    <td>
                        <spring:message text="Goal:"/>
                        <input name="goal" class="text3" value="${travels.goal}"/>
                    </td>
                    
                </tr>
                <tr>
                    <td>
                        <spring:message text="Start Date:"/>
                        <input type="date" class="text3" name="startdate" value="${travels.startdate}"/>
                    </td>
                    
                </tr>
                <tr>
                    <td>
                        <spring:message text="Finish Date:"/>
                        <input type="date" class="text3" name="finishdate" value="${travels.finishdate}"/>
                    </td>
                    
                </tr>
                <c:if test="${!empty travels.cost}">
                    <input type="hidden" name="cost" class="text3" value="${travels.cost}"/>
                </c:if>
                <c:if test="${empty travels.cost}">
                    <input type="hidden"name="cost" class="text3" value="0"/>
                </c:if>
                    
                <tr>
                    <td>
                        <spring:message text="Information:"/>
                        <input  name="information" class="text3" value="${travels.information}"/>

                    </td>
                        
                </tr>
                                
                 <tr>
                    <td>
                        <spring:message text="Code Project:"/>
                        <input name="codepr" class="text3" value="${travels.codepr}"/>
                    </td>
                    
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty travels.users.username}">
                            <input type="Submit" value="<spring:message text="Edit"/>"
                        </c:if>
                        <c:if test="${empty travels.users.username}">
                            <input type="Submit" value="<spring:message text="Add"/>"
                        </c:if>   
                    </td>
                </tr>
        </table>
    </form>
    
    <br/>
    <h3>Information</h3>
    <c:url var="list" value="/users/listbydate"></c:url>
    <form action="${list}" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <spring:message text="Show by date:" />
        <input type="date" class="text3" name="listByDate1" />
        <input type="date" class="text3" name="listByDate2" />
        <input type="Submit" value="Order"/>
    </form>
        
    <a href="#" id="test" onClick="javascript:fnExcelReport();">Download</a>
    <c:if test="${!empty travelDetailss}">
        <table class="download">
            <tr>
                <th>Person Id</th>
                <th>Person Name </th>
                <th>Person Goal</th>
                <th>Place</th>
                <th>Start Date</th>
                <th>Finish Date</th>
                <th>Project Code</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="us" items="${travelDetails}">
                <tr>
                    <td>${us.travelid}</td>
                    <td>${us.users.username}</td>
                    <td>${us.goal}</td>
                    <td>${us.place}</td>
                    <td>${us.startdate}</td>
                    <td class="fd">${us.finishdate}</td>
                    <td>${us.codepr}</td>
                    <td><a href="<c:url value='/users/edittravel/${us.travelid}'/>">Edit</a></td>
                    <td><a href="<c:url value='/users/removetravel/${us.travelid}'/>">Remove</a></td>
                </tr>
            </c:forEach>
                <tr>
                    <th>Department</th>
                    <th>Department Manager</th>
                </tr>
                <tr>
                    <td>${selectedUser.department}</td>
                    <td>${selectedUser.departmanager}</td>
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

    const fd=document.querySelectorAll('.fd').length;
    let fi="";
    for(i=0;i<fd;i++){
        fi+=document.querySelectorAll('.fd')[i].innerHTML+"-";
    }
    let fdArray=fi.split('-');

    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd.toString().length==1){
            dd="0"+today.getDate();
        }
        if(mm.toString().length==1){
            mm="0"+(today.getMonth()+1);
        }
    for(i=0;i<fd*3;i+=3){

        if(fdArray[i]==yyyy&&fdArray[i+1]==mm&&fdArray[i+2]==dd){
            alert("A travel time has finished."+yyyy+mm+dd);
        }
    }
</script>
</body>
</html>
