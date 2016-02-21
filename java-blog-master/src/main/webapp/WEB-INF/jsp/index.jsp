<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../layout/taglib.jsp"%>
<jsp:include page="home.jsp" flush="true"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body> 
 <h1>Latest news for java</h1>
 
 <table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>Date</th>
								<th>Item</th>
							</tr>
						</thead>
						<tbody>
							<catlina:forEach items="${items}" var="item">
								<tr>
									<td>${item.date}</td>
									<td>
									   <strong>
								          <a href="<catlina:out value="${item.link}" />" target="_blank">
								         <catlina:out value="${item.title}" /> 
									   </a>
									   </strong>
									   <br />
									   ${item.description}
									</td>
								</tr>
							</catlina:forEach>
						</tbody>
					</table>
  <jsp:include page="footer.jsp"/>
</body>
</html>