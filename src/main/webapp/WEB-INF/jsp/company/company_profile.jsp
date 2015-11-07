<%@page import="com.oglasi.model.JobDao"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page import="com.oglasi.model.Job"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <jsp:include page="../top.jsp" flush="true" />

    <div class="main">
      <div class="container">
        <div class="row">

          <div class="main_content col-sm-8">
            <div class="col-sm-8">
              <h3>${comp.name}</h3>
              <table>
                <tr><td>Adresa: </td><td>${comp.address}</td></tr>
                <tr><td>Grad: </td><td>${comp.cityId.name}</td></tr>
                <tr><td>Vebsajt: </td><td>${comp.web}</td></tr>
                <tr><td>Telefon: </td><td>${comp.phone}</td></tr>
                <tr><td>Email: </td><td>${comp.email}</td></tr>
                <tr><td>Maticni broj: </td><td>${comp.maticniBroj}</td></tr>
                <tr><td>PIB: </td><td>${comp.pib}</td></tr>
              </table>
            </div> 
            <div class="col-sm-4">
              <img src="resources/img/${comp.logo}">
            </div>  
            <div class="comp_description">
              <h4>O kompaniji</h4>
              <p>${comp.about}</p>
            </div>  
          </div><!-- main_content -->

          <div class="side_comp_job col-sm-4">
            <h4>Trenutno aktivni poslovi</h4>
            <c:forEach items="${comp.jobCollection}" var="job">
                <div>
                    <span><a href="job/${job.jobId}">${job.title}</a></span>
                </div>
            </c:forEach>
            
            <form action="company/add_job" class="add_job_form">
              <button class="btn btn-primary btn-lg btn-block">Dodaj posao</button>
            </form>
            
            <h4>Istekli poslovi</h4>
            <span><a href="#">Potreban konobar</a></span>
            <span><a href="#">Potreban konobar</a></span>
            <span><a href="#">Potreban konobar</a></span>
          </div><!-- side_search -->

        </div><!-- row -->
      </div><!-- container -->
    </div><!-- main -->

     <jsp:include page="../bottom.jsp" flush="true" />
