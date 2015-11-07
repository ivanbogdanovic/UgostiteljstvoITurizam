<%@page import="com.oglasi.model.JobDao"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page import="com.oglasi.model.Job"%>
<%@page import="com.oglasi.servlet.Pagination" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

     <jsp:include page="top.jsp" flush="true" />
            
    <div class="main">
      <div class="container">
        <div class="row">
            <form method="get" action="">

          <div class="side_search col-sm-4 col-sm-push-8">
           <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example1"> 
              <span class="icon-bar"></span> 
              <span class="icon-bar"></span> 
              <span class="icon-bar"></span> 
            </button>
            </div><!-- navbar-header -->

            <div class="collapse navbar-collapse" id="example1">
              <div class="search">
                <div class="form-group has-feedback">
                 <input type="text" class="form-control" id="inputSuccess2" placeholder="Search"/>
                 <span class="glyphicon glyphicon-search form-control-feedback"></span>
                </div>
               </div><!-- search --> 
               
                <select name="cnt" class="form-control" onchange="this.form.submit()">
                            <c:forEach items="${country}" var="country">
                                <option <c:if test="${country.countryId==param.cnt}">selected</c:if> value="${country.countryId}">${country.name}</option>
                                </c:forEach>
                        </select>
                                
                <select name="city_search" class="form-control" onchange="this.form.submit()">
                            <option value="${null}">Svi gradovi</option>
                            <c:forEach items="${city}" var="city">
                                <option <c:if test="${city.cityId==param.city_search}">selected</c:if> value="${city.cityId}">${city.name}</option>
                            </c:forEach>
                        </select>
                            
                <select name="job_position" class="form-control" onchange="this.form.submit()">
                            <option value="${null}">Sve pozicije</option>
                            <c:forEach items="${jobPosition}" var="jobPosition">
                                <option <c:if test="${jobPosition.jobPositionId==param.job_position}">selected</c:if> value="${jobPosition.jobPositionId}">${jobPosition.positionName}</option>
                            </c:forEach>
                        </select>
                            
                <select name="company_type" class="form-control" onchange="this.form.submit()">
                            <option value="${null}">Svi tipovi firmi</option>
                            <c:forEach items="${companyType}" var="companyType">
                                <option <c:if test="${companyType.companyTypeId==param.company_type}">selected</c:if> value="${companyType.companyTypeId}">${companyType.typeName}</option>
                            </c:forEach>
                        </select>
                            
                <select name="employment_type" class="form-control" onchange="this.form.submit()">
                            <option value="${null}">Sve vrste zaposlenja</option>
                            <c:forEach items="${employmentType}" var="employmentType">
                                <option <c:if test="${employmentType.employmentTypeId==param.employment_type}">selected</c:if> value="${employmentType.employmentTypeId}">${employmentType.typeName}</option>
                            </c:forEach>
                        </select>
              
            </div> <!-- collapse navbar-collapse -->
          </div><!-- side_search -->
          
          <div class="main_content col-sm-8 col-sm-pull-4">
              <div class="row"> 
              <div class="col-xs-2">
                <select name="per_page" class="form-control" onchange="this.form.submit()">
                  <option>10</option>
                  <option>20</option>
                  <option>30</option>
                </select>
              </div>
              <div class="col-xs-offset-7 col-xs-3">
                <select name="sort_by" class="form-control" onchange="this.form.submit()">
                  <option>po datumu</option>
                </select>
              </div>
            </div>
            <jsp:include page="job_display.jsp" flush="true" />
            
            <c:if test="${totalPages>1}">
                <jsp:include page="/Pagination" flush="true"/>
            </c:if>
          </div><!-- main_content -->
          </form>
        </div><!-- row -->
      </div><!-- container -->
    </div><!-- main -->

   <jsp:include page="bottom.jsp" flush="true" />
