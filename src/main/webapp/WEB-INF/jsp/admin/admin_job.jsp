<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="admin_top.jsp" flush="true" />
    <div class="container">
    <div class="page-header">
      <h1>Posao</h1>
    </div>
    <ol class="breadcrumb">
        <li><a href="admin/admin_main">Main</a></li>
        <li class="active">Job</li>
    </ol>
    <div class="row">
      <div class="col-sm-7">
        <div class="well">
          <div class="list-group">
              <div class="list-group-item list-group-item-text"><h3>${job.title}</h3></div>
          </div>  
          <table class="table table-condensed table-striped">
            <tr><td>Grad: </td><td>${job.cityId.name}</td></tr>
            <tr><td>Adresa: </td><td>${job.address}</td></tr>
            <tr><td>Pozicija: </td><td>${job.jobPositionId.positionName}</td></tr>
            <tr><td>Tip: </td><td>${job.employmentTypeId.typeName}</td></tr>
            <tr><td>Trajanje: </td><td>${job.duration}</td></tr>            
            <tr><td colspan="2"><h4>Kontakt:</h4></td></tr>
            <tr><td>Osoba: </td><td>${job.contactPerson}</td></tr>
            <tr><td>Telefon: </td><td>${job.phone}</td></tr>
            <tr><td>Email: </td><td>${job.email}</td></tr>
          </table>
          <h4>Opis:</h4>
          <div class="well">
            <p>${job.description}</p>
          </div>
          <div class="well">
            <c:choose>
              <c:when test="${job.status!=2}">
                 <a href="admin/admin_job_activate/${job.jobId}" class="btn btn-success active btn-block"><b>Aktiviraj</b></a> 
              </c:when>
              <c:when test="${job.status==2}">
                 <a href="admin/admin_job_canceled/${job.jobId}" class="btn btn-warning active btn-block"><b>Deaktiviraj</b></a> 
              </c:when>   
            </c:choose>
            <a href="admin/admin_job_update/${job.jobId}" class="btn btn-info active btn-block"><b>Izmeni</b></a>
            <a href="admin/admin_job_delete/${job.jobId}" class="btn btn-danger active btn-block"><b>Obrisi</b></a>
          </div> 
        </div>
      </div>

      <div class="col-sm-5">
        <div class="well">
          <div class="list-group">
              <a href="admin/admin_company/${job.companyId.companyId}" class="list-group-item list-group-item-info">
                <h3>${job.companyId.name}</h3>
              </a>
          </div>
          <div class="thumbnail">
            <img src="resources/img/${job.companyId.logo}" alt="Nema logo-a">
          </div>
          <table class="table table-condensed table-striped">
            <tr><td>Grad: </td><td>${job.companyId.cityId.name}</td></tr>
            <tr><td>Adresa: </td><td>${job.companyId.address}</td></tr>
            <tr><td>Vebsajt: </td><td>${job.companyId.web}</td></tr>
            <tr><td>Telefon: </td><td>${job.companyId.phone}</td></tr>
            <tr><td>Fax: </td><td>${job.companyId.fax}</td></tr>
            <tr><td>Email: </td><td>${job.companyId.email}</td></tr>
            <tr><td>M broj: </td><td>${job.companyId.maticniBroj}</td></tr>
            <tr><td>PIB: </td><td>${job.companyId.pib}</td></tr>
          </table>
          <h4>O kompaniji:</h4>
          <div class="well">
            <p>${job.companyId.about}</p>
          </div>
        </div>
      </div>

    </div><!-- row -->
  </div><!-- container -->
<jsp:include page="admin_bottom.jsp" flush="true" />  