<%@page import="com.oglasi.model.JobDao"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page import="com.oglasi.model.Job"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <jsp:include page="../top.jsp" flush="true" />
    <script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>

    <div class="main">
      <div class="container">
        <div class="row">

          <div class="main_content sign_up col-sm-8">
            <div class="container">
              <form method="post" action="company/sign_up_form" enctype="multipart/form-data">
                <div class="row">
                  <div class="col-sm-4">
                    <span>Naziv preduzeca:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="text" class="form-control" name="name">
                    </div>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Adresa:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="text" class="form-control" name="address">
                    </div>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Grad:</span>
                  </div>
                  <div class="col-sm-8">
                    <select class="form-control" name="cityId">
                        <c:forEach items="${city}" var="city">
                            <option value="${city.cityId}">${city.name}</option>
                        </c:forEach>
                    </select>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Tip kompanije:</span>
                  </div>
                  <div class="col-sm-8">
                    <select class="form-control" name="companyTypeId">
                         <c:forEach items="${companyType}" var="companyType">
                            <option value="${companyType.companyTypeId}">${companyType.typeName}</option>
                        </c:forEach>
                    </select>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Website:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="text" class="form-control" name="web">
                    </div>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Email:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="email" class="form-control" name="email">
                    </div>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Phone:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="phone" class="form-control" name="phone">
                    </div>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Fax:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="text" class="form-control" name="fax">
                    </div>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Maticni broj:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="text" class="form-control" name="mat_br">
                    </div>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>PIB:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="text" class="form-control" name="pib">
                    </div>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Lozinka:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="password" class="form-control" name="password">
                    </div>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Potvrdi lozinku:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="password" class="form-control" name="password_rpt">
                    </div>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Opis preduzeca:</span>
                  </div>
                  <div class="col-sm-8">
                    <textarea class="form-group" name="about" id="editor1" rows="10" cols="80"></textarea>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Dodaj logo:</span>
                  </div>
                  <div class="col-sm-8">
                    <input type="file" name="logo" />
                  </div>
                </div><!-- row -->
                <div class="subm_btn">
                  <button class="btn btn-warning btn-lg" type="submit" value="Dodaj posao">Sacuvaj</button>
                </div>
                <script type="text/javascript">
                  CKEDITOR.replace( 'editor1' );
                </script>
              </form>

            </div><!-- container -->
          </div><!-- main_content -->

          <div class="side_job col-sm-4">
            
          </div><!-- side_search -->

        </div><!-- row -->
      </div><!-- container -->
    </div><!-- main -->

   <jsp:include page="../bottom.jsp" flush="true" />
