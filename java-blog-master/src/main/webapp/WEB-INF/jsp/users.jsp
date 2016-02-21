<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include  file="../layout/taglib.jsp" %>
<html>
<head>
<jsp:include page="home.jsp" flush="true"/>
</head>
<body>

<script type="text/javascript">
		  $(document).ready(function(){
			  $(".triggerRemove").click(function(e){
				  e.preventDefault();
				  $("#modalRemove").modal('show');
				  $("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			  });
		  });
		</script>

<div class="container">
<table class="table table-bordered table-hover table-striped">
  <thead>
     <tr>
          <th>user name</th>
          <th>Operation</th>
     </tr>   
  </thead>
  <tbody>
  <catlina:forEach items="${users}" var="user">
  <tr>
       <td>
            <a href='<spring:url value="/users/${user.id}.html" />'>
           <catlina:out value="${user.name}" />
           </a>
       </td>
       <td>
            <a href='<spring:url value="/users/remove/${user.id}.html" />' class="btn btn-danger triggerRemove">
             Remove
           </a>
       </td>
       </tr>
</catlina:forEach>
  </tbody>
   
</table>
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
	

<jsp:include page="footer.jsp"/>
</body>
</html>