<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="admin_top.jsp" flush="true" />
  <script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>
  <div class="container">
    <div class="page-header">
      <h1>Promena podataka posla</h1>
    </div>
    <div class="row">
      <div class="col-sm-8">
        <div class="well">
          <form method="post" action="admin/admin_job_post">
            <div class="row">
              <div class="col-sm-4">
                <span>Naslov:</span>
              </div>
              <div class="col-sm-8">
                <div class="form-group">
                    <input type="text" class="form-control" value="${job.title}" name="title">
                </div>
              </div>
            </div><!-- row -->
            <div class="row">
              <div class="col-sm-4">
                <span>Grad:</span>
              </div>
              <div class="col-sm-8">
                <div class="form-group">
                  <select class="form-control" name="cityId">
                      <c:forEach items="${city}" var="city">
                          <option value="${city.cityId}" <c:if test="${city.name==job.cityId.name}">selected</c:if>>${city.name}</option>
                      </c:forEach>
                  </select>
                </div>
              </div>
            </div><!-- row -->
            <div class="row">
              <div class="col-sm-4">
                <span>Adresa:</span>
              </div>
              <div class="col-sm-8">
                <div class="form-group">
                  <input type="text" class="form-control" value="${job.address}" name="address">
                </div>
              </div>
            </div><!-- row -->
            <div class="row">
              <div class="col-sm-4">
                <span>Pozicija:</span>
              </div>
              <div class="col-sm-8">
                <div class="form-group">
                  <select class="form-control" name="jobPositionId">
                      <c:forEach items="${jobPosition}" var="jobPosition">
                          <option value="${jobPosition.jobPositionId}" <c:if test="${jobPosition.positionName==job.jobPositionId.positionName}">selected</c:if>>${jobPosition.positionName}</option>
                      </c:forEach>
                  </select>
                </div>
              </div>
            </div><!-- row -->
            <div class="row">
              <div class="col-sm-4">
                <span>Tip zaposlenja:</span>
              </div>
              <div class="col-sm-8">
                <div class="form-group">
                  <select class="form-control" name="employmentTypeId">
                      <c:forEach items="${employmentType}" var="employmentType">
                          <option value="${employmentType.employmentTypeId}" <c:if test="${employmentType.typeName==job.employmentTypeId.typeName}">selected</c:if>>${employmentType.typeName}</option>
                      </c:forEach>
                  </select>
                </div>
              </div>
            </div><!-- row -->
            <div class="row">
              <div class="col-sm-4">
                <span>Trajanja oglasa:</span>
              </div>
              <div class="col-sm-8">
                <div class="form-group">
                  <select class="form-control" name="duration">
                      <c:forEach items="${duration}" var="d">
                            <option value="${d}" <c:if test="${job.duration==d}">selected</c:if>>${d}</option>
                        </c:forEach>
                  </select>
                </div>
              </div>
            </div><!-- row -->
          <div class="row">
            <div class="col-sm-4">
              <span>Opis posla:</span>
            </div>
            <div class="col-sm-8">
              <div class="form-group">
                <textarea class="form-group" name="description" id="editor1" rows="10" cols="80">${job.description}</textarea>
              </div>
            </div>
          </div><!-- row -->
          <div class="row">
            <div class="col-sm-4">
              <span>Ime kontakt osobe:</span>
            </div>
            <div class="col-sm-8">
              <div class="form-group">
                  <input type="text" class="form-control" value="${job.contactPerson}" name="contactPerson">
              </div>
            </div>
          </div><!-- row -->
          <div class="row">
            <div class="col-sm-4">
              <span>Broj telefona: </span>
            </div>
            <div class="col-sm-8">
              <div class="form-group">
                <input type="text" class="form-control" value="${job.phone}" name="phone">
              </div>
            </div>
          </div><!-- row -->
          <div class="row">
            <div class="col-sm-4">
              <span>Email:</span>
            </div>
            <div class="col-sm-8">
              <div class="form-group">
                <input type="email" class="form-control" value="${job.email}" name="email">
              </div>
            </div>
          </div><!-- row -->
          <div class="well">
            <input type="hidden" name="id" value="${job.jobId}"/>
            <button type="submit" class="btn btn-info active btn-block"><b>Sacuvaj izmene</b></button>
          </div> 
          <script type="text/javascript">
            CKEDITOR.replace( 'editor1' );
          </script>
        </form>
      </div>
    </div>

    <div class="col-sm-4">
      <div class="well">
        <h3>${job.companyId.name}</h3>
        <div class="thumbnail">
          <img src="resources/logo/${job.companyId.logo}" alt="Nema logo-a">
        </div>
        <table class="table table-condensed table-striped">
          <tr></tr>
          <tr><th>Grad: </th></tr><tr><td>${job.companyId.cityId.name}</td></tr>
          <tr><th>Adresa: </th></tr><tr><td>${job.companyId.address}</td></tr>
          <tr><th>Vebsajt: </th></tr><tr><td>${job.companyId.web}</td></tr>
          <tr><th>Telefon: </th></tr><tr><td>${job.companyId.phone}</td></tr>
          <tr><th>Fax: </th></tr><tr><td>${job.companyId.fax}</td></tr>
          <tr><th>Email: </th></tr><tr><td>${job.companyId.email}</td></tr>
          <tr><th>M broj: </th></tr><tr></tr><tr><td>${job.companyId.maticniBroj}</td></tr>
          <tr><th>PIB: </th></tr><tr><td>${job.companyId.pib}</td></tr>
        </table>
        <div class="well">
          <h4>O kompaniji</h4>
          <p>${job.companyId.about}</p>
        </div>
      </div>
    </div>

    </div><!-- row -->
  </div><!-- container -->
<jsp:include page="admin_bottom.jsp" flush="true" />  
