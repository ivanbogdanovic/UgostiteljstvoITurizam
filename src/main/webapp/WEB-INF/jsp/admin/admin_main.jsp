<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="admin_top.jsp" flush="true" />
    <div class="container">
      <div class="page-header">
          <h1>Kompanije i poslovi za aktivaciju</h1>
          <div class="row">
            <div class="col-sm-3 col-xs-8">
             <div class="well">
              <h1><small>Legenda</small></h1>
              <ul class="list-group">
                <li class="list-group-item list-group-item-success">Novo</li>
                <li class="list-group-item list-group-item-info">Abdejtovano</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="well">
            <h1><small>Kompanije</small></h1>
            <c:forEach items="${company}" var="company">
                <div class="list-group">
                  <a href="admin/admin_company/${company.companyId}" class="list-group-item list-group-item-<c:choose><c:when test="${company.status==1}">success</c:when><c:when test="${company.status==3}">info</c:when></c:choose>">
                      <h5>${company.name}</h5>
                  </a>
                </div>
            </c:forEach>
          </div>
        </div>
        <div class="col-md-6">
          <div class="well">
            <h1><small>Poslovi</small></h1>
            <c:forEach items="${job}" var="job">
                <div class="list-group">
                  <a href="admin/admin_job/${job.jobId}" class="list-group-item list-group-item-<c:choose><c:when test="${job.status==1}">success</c:when><c:when test="${job.status==3}">info</c:when></c:choose>">
                      <h5>${job.title}</h5>
                  </a>
                </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
<jsp:include page="admin_bottom.jsp" flush="true" />
