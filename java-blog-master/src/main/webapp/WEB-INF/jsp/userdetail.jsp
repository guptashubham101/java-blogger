<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<%@include file="../layout/taglib.jsp"%>
<jsp:include page="home.jsp" flush="true" />
<html>
<body>
	<div class="container">
		<object align="middle">
			<h1><catlina:out value="${user.name}" /></h1>
		</object>

		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary btn-lg"
			data-toggle="modal" data-target="#myModal">New Blog</button>
		<form:form commandName="blog" cssClass="form-horizontal">
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">New Blog</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Name</label>
								<div class="col-sm-10">
									<form:input path="name" cssClass="form-control" />
								</div>
							</div>

							<div class="form-group">
								<label for="url" class="col-sm-2 control-label">Url</label>
								<div class="col-sm-10">
									<form:input path="url" cssClass="form-control" />
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<input type="submit" value="Save" class="btn btn-primary" />
						</div>
					</div>
				</div>
			</div>
		</form:form>
		<br />
		<br />
		
		<script type="text/javascript">
		  $(document).ready(function(){
			  $('.nav-tabs a:first').tab('show'); 
			  $(".triggerRemove").click(function(e){
				  
				  e.preventDefault();
				  $("#modalRemove").modal('show');
				  $("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			  });
		  });
		</script>
		
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<catlina:forEach items="${user.blogs}" var="blog">
				<li><a href="#blog_${blog.id}" data-toggle="tab">${blog.name}</a></li>
			</catlina:forEach>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<catlina:forEach items="${user.blogs}" var="blog">
				<div class="tab-pane" id="blog_${blog.id}">
					<h1>${blog.name}</h1>
					<p>
					<a href='<spring:url value="/blog/remove/${blog.id}.html"></spring:url>' class="btn btn-danger triggerRemove">Remove Blog</a>
					<br/><br/>
					${blog.url}</p>

					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>Date</th>
								<th>Item</th>
							</tr>
						</thead>
						<tbody>
							<catlina:forEach items="${blog.items}" var="item">
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
				</div>
			</catlina:forEach>
		</div>
	</div>
	
	<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove Blog</h4>
      </div>
      <div class="modal-body">
      Really you wanna do that?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>
	
	
	<jsp:include page="footer.jsp" />
</body>
</html>