<%@page import="com.oglasi.model.JobDao"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page import="com.oglasi.model.Job"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="top.jsp" flush="true" />

<div class="main">
  <div class="container">
    <div class="row">

      <div class="main_content col-sm-8">
        <div class="col-sm-8">
          <h3>${company.name}</h3>
          <table>
            <tr><td>Adresa: </td><td>${company.address}</td></tr>
            <tr><td>Grad: </td><td>${company.cityId.name}</td></tr>
            <tr><td>Vebsajt: </td><td>${company.web}</td></tr>
            <tr><td>Telefon: </td><td>${company.phone}</td></tr>
            <tr><td>Fax: </td><td>${company.fax}</td></tr>
            <tr><td>Email: </td><td>${company.email}</td></tr>
            <tr><td>Maticni broj: </td><td>${company.maticniBroj}</td></tr>
            <tr><td>PIB: </td><td>${company.pib}</td></tr>
          </table>
        </div> 
        <div class="col-sm-4">
          <img src="resources/logo/${company.logo}">
        </div>  
        <div class="comp_description">
          <h4>O kompaniji</h4>
          <p>${company.about}</p>
        </div> 
      </div><!-- main_content -->

      <div class="side_comp_job col-sm-4">
        <h4>Aktivni poslovi ovog poslodavca</h4>
        <c:forEach items="${job}" var="job">
          <div>
            <span><a href="job/${job.jobId}">${job.title}</a></span>
          </div>
        </c:forEach>
      </div><!-- side_search -->

    </div><!-- row -->
  </div><!-- container -->
</div><!-- main -->

<jsp:include page="bottom.jsp" flush="true" />