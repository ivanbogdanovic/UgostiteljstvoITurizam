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
              <form method="post" action="company/add_job_post">
                <div class="row">
                  <div class="col-sm-4">
                    <span>Naslov:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="text" class="form-control" name="jobTitle">
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
                    <span>Pozicija:</span>
                  </div>
                  <div class="col-sm-8">
                    <select class="form-control" name="jobPositionId">
                        <c:forEach items="${jobPosition}" var="jobPosition">
                            <option value="${jobPosition.jobPositionId}">${jobPosition.positionName}</option>
                        </c:forEach>
                    </select>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Tip zaposlenja:</span>
                  </div>
                  <div class="col-sm-8">
                    <select class="form-control" name="employmentTypeId">
                        <c:forEach items="${employmentType}" var="employmentType">
                            <option value="${employmentType.employmentTypeId}">${employmentType.typeName}</option>
                        </c:forEach>
                    </select>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Duzina trajanja oglasa:</span>
                  </div>
                  <div class="col-sm-8">
                    <select class="form-control" name="duration">
                        <c:forEach items="${duration}" var="d">
                            <option value="${d}">${d}</option>
                        </c:forEach>
                    </select>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Opis posla:</span>
                  </div>
                  <div class="col-sm-8">
                    <textarea class="form-group" name="description" id="editor1" rows="10" cols="80"></textarea>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Ime kontakt osobe:</span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="text" class="form-control" name="contactPerson">
                    </div>
                  </div>
                </div><!-- row -->
                <div class="row">
                  <div class="col-sm-4">
                    <span>Broj telefona: </span>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group">
                      <input type="text" class="form-control" name="phone">
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
