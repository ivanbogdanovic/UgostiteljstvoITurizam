<%@page import="com.oglasi.model.Company"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="admin_top.jsp" flush="true" />
  <script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>
  <div class="container">
    <div class="page-header">
      <h1>Promena podataka kompanije</h1>
    </div>
    <ol class="breadcrumb">
        <li><a href="admin/admin_main">Main</a></li>
        <li><a href="admin/admin_company/${company.companyId}">Company</a></li>
        <li class="active">Update</li>
    </ol>
    <div class="row">
      <div class="col-sm-8">
        <div class="well">
          <form method="post" action="admin/admin_company_post">
            <div class="row">
              <div class="col-sm-4">
                <span>Naziv preduzeca:</span>
              </div>
              <div class="col-sm-8">
                <div class="form-group">
                  <input type="text" class="form-control" name="name" value="${company.name}">
                </div>
              </div>
            </div><!-- row -->
            <div class="row">
              <div class="col-sm-4">
                <span>Adresa:</span>
              </div>
              <div class="col-sm-8">
                <div class="form-group">
                  <input type="text" class="form-control" name="address" value="${company.address}">
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
                      <option value="${city.cityId}" <c:if test="${city.name==company.cityId.name}">selected</c:if>>
                          ${city.name}
                      </option>
                    </c:forEach>
                  </select>
                </div>
              </div>
            </div><!-- row -->
            <div class="row">
              <div class="col-sm-4">
                <span>Tip kompanije:</span>
              </div>
              <div class="col-sm-8">
                <div class="form-group">
                  <select class="form-control" name="companyTypeId">
                   <c:forEach items="${companyType}" var="companyType">
                       <option value="${companyType.companyTypeId}" <c:if test="${companyType.typeName==company.companyTypeId.typeName}">selected</c:if>>
                        ${companyType.typeName}
                    </option>
                   </c:forEach>
                 </select>
               </div>
             </div>
           </div><!-- row -->
           <div class="row">
            <div class="col-sm-4">
              <span>Website:</span>
            </div>
            <div class="col-sm-8">
              <div class="form-group">
                <input type="text" class="form-control" name="web" value="${company.web}">
              </div>
            </div>
          </div><!-- row -->
          <div class="row">
            <div class="col-sm-4">
              <span>Email:</span>
            </div>
            <div class="col-sm-8">
              <div class="form-group">
                <input type="email" class="form-control" name="email" value="${company.email}">
              </div>
            </div>
          </div><!-- row -->
          <div class="row">
            <div class="col-sm-4">
              <span>Phone:</span>
            </div>
            <div class="col-sm-8">
              <div class="form-group">
                <input type="phone" class="form-control" name="phone" value="${company.phone}">
              </div>
            </div>
          </div><!-- row -->
          <div class="row">
            <div class="col-sm-4">
              <span>Fax:</span>
            </div>
            <div class="col-sm-8">
              <div class="form-group">
                <input type="text" class="form-control" name="fax" value="${company.fax}">
              </div>
            </div>
          </div><!-- row -->
          <div class="row">
            <div class="col-sm-4">
              <span>Maticni broj:</span>
            </div>
            <div class="col-sm-8">
              <div class="form-group">
                <input type="text" class="form-control" name="mat_br" value="${company.maticniBroj}">
              </div>
            </div>
          </div><!-- row -->
          <div class="row">
            <div class="col-sm-4">
              <span>PIB:</span>
            </div>
            <div class="col-sm-8">
              <div class="form-group">
                <input type="text" class="form-control" name="pib" value="${company.pib}">
              </div>
            </div>
          </div><!-- row -->
          <div class="row">
            <div class="col-sm-4">
              <span>Opis preduzeca:</span>
            </div>
            <div class="col-sm-8">
              <div class="form-group">
                <textarea class="form-group" name="about" id="editor1" rows="10" cols="80">${company.about}</textarea>
              </div>
            </div>
          </div><!-- row -->
          <div class="well">
              <input type="hidden" name="id" value="${company.companyId}"/>
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
        <h4>Logo</h4>
        <div class="thumbnail">
          <img src="resources/logo/${company.logo}" alt="Nema logo-a">
        </div>
          <c:choose>
              <c:when test="${company.logo!=''}">
                  <div class="well">
                      <form method="post" action="admin/admin_company_logo_del">
                          <input type="hidden" name="id" value="${company.companyId}"/>
                          <button type="submit" class="btn btn-danger active btn-block"><b>Obrisi</b></button>
                      </form>
                  </div>
              </c:when>
              <c:when test="${company.logo==''}"> 
                  <h4>Dodaj logo:</h4>
                  <form method="post" action="admin/admin_company_logo" enctype="multipart/form-data">
                    <div class="form-group">
                      <input type="file" name="logo" />
                    </div>
                      <input type="hidden" name="id" value="${company.companyId}"/>
                    <div class="well">
                        <button type="submit" class="btn btn-success active btn-block"><b>Snimi</b></button>
                    </div> 
                  </form>
              </c:when>
          </c:choose>
      </div>
    </div>

    </div><!-- row -->
  </div><!-- container -->
<jsp:include page="admin_bottom.jsp" flush="true" />  