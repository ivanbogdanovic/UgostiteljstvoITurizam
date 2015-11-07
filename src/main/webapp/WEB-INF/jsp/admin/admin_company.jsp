<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="admin_top.jsp" flush="true" />
    <div class="container">
    <div class="page-header">
      <h1>Podaci kompanije</h1>
    </div>
    <div class="row">
      <div class="col-sm-8">
        <div class="well">
          <h3>${company.name}</h3>
          <div class="row">
            <div class="col-sm-4 col-sm-push-8">
              <div class="thumbnail">
                <img src="resources/logo/${company.logo}" alt="Nema logo-a">
              </div>
            </div> 
            <div class="col-sm-8 col-sm-pull-4">
              <table class="table table-condensed table-striped">
                <tr><td>Grad: </td><td>${company.cityId.name}</td></tr>
                <tr><td>Adresa: </td><td>${company.address}</td></tr>
                <tr><td>Vebsajt: </td><td>${company.web}</td></tr>
                <tr><td>Telefon: </td><td>${company.phone}</td></tr>
                <tr><td>Fax: </td><td>${company.fax}</td></tr>
                <tr><td>Email: </td><td>${company.email}</td></tr>
                <tr><td>Maticni broj: </td><td>${company.maticniBroj}</td></tr>
                <tr><td>PIB: </td><td>${company.pib}</td></tr>
              </table>
            </div>  
          </div>
          <div class="well">
            <h4>O kompaniji</h4>
            <p>${company.about}</p>
          </div> 
          <div class="well">
            <c:if test="${company.status!=2}">
              <a href="admin/admin_company_activate/${company.companyId}" class="btn btn-success active btn-block"><b>Aktiviraj</b></a>
            </c:if>
            <a href="admin/admin_company_update/${company.companyId}" class="btn btn-warning active btn-block"><b>Izmeni</b></a>
            <a href="admin/admin_company_delete/${company.companyId}" class="btn btn-danger active btn-block"><b>Obrisi</b></a>
          </div> 
        </div>
      </div>

      <div class="col-sm-4">
        <div class="well">
          <h4>Aktivni poslovi ovog poslodavca</h4>
          <c:forEach items="${jobA}" var="job">
            <div>
              <span><a href="admin/admin_job/${job.jobId}">${job.title}</a></span>
            </div>
          </c:forEach>
        </div>
        <div class="well">
          <h4>Neaktivni poslovi ovog poslodavca</h4>
          <c:forEach items="${jobI}" var="job">
            <div>
              <span><a href="admin/admin_job/${job.jobId}">${job.title}</a></span>
            </div>
          </c:forEach>
        </div>
        <div class="well">
          <h4>Abdejtovani poslovi ovog poslodavca</h4>
          <c:forEach items="${jobU}" var="job">
            <div>
              <span><a href="admin/admin_job/${job.jobId}">${job.title}</a></span>
            </div>
          </c:forEach>
        </div>
        <div class="well">
          <h4>Istekli poslovi ovog poslodavca</h4>
          <c:forEach items="${jobE}" var="job">
            <div>
              <span><a href="admin/admin_job/${job.jobId}">${job.title}</a></span>
            </div>
          </c:forEach>
        </div>
      </div>

    </div><!-- row -->
  </div><!-- container -->
<jsp:include page="admin_bottom.jsp" flush="true" />  