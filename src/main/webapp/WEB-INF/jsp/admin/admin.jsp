<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="admin_top.jsp" flush="true" />
    <div class="container">
      <div class="row well">
        <div class="col-xs-9 col-sm-6 col-md-5 col-lg-4 well">
          <form method="post" action="admin/admin_login">
            <div class="form-group">
              <input type="text" name="username" class="form-control" placeholder="Username">
            </div>
            <div class="form-group">
              <input type="password" name="password" class="form-control" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-info">Login</button>
          </form>
        </div>
      </div>
      <div class="row">
         <c:if test="${message!=null}"><div class="alert alert-danger">${message}</div></c:if>
      </div>
    </div>
<jsp:include page="admin_bottom.jsp" flush="true" />     