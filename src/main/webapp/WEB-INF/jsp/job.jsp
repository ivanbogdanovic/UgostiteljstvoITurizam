
<%@page import="java.util.Date"%>
<%@page import="com.oglasi.model.JobDao"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page import="com.oglasi.model.Job"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <jsp:include page="top.jsp" flush="true" />
     
    <div class="main">
      <div class="container">
        <div class="row">

          <div class="main_content col-sm-8">
            <h3 class="disp_title">${job.title}</h3>
            <div class="col-sm-8">
              <table>
                <tr><td>Grad: </td><td>${job.cityId.name}</td></tr>
                <tr><td>Oglas objavljen: </td><td><fmt:formatDate value="${job.activeDate}" pattern="dd.MM.yyyy "/></td></tr>
                <tr><td>Oglas istice: </td><td><fmt:formatDate value="${expirationDate}" pattern="dd.MM.yyyy "/></td></tr>
              </table>
            </div>  
            <div class="comp_description">
              <p>${job.description}</p>
              <h5>Kontakt:</h5>
              <table>
                <tr><td>Kontakt osoba: </td><td>${job.contactPerson}</td></tr>
                <tr><td>Telefon: </td><td>${job.phone}</td></tr>
                <tr><td>Email: </td><td>${job.email}</td></tr>
              </table>
            </div> 
          </div><!-- main_content -->

          <div class="side_job col-sm-4">
            <h3>${job.companyId.name}</h3>
            <span>${job.companyId.address}</span>
            <span>${job.companyId.cityId.name}</span>
            <span>${job.companyId.web}</span>
            <span>${job.companyId.phone}</span>
            <span>${job.companyId.email}</span>
          </div><!-- side_job -->
          
        </div><!-- row -->
      </div><!-- container -->
    </div><!-- main -->

       <jsp:include page="bottom.jsp" flush="true" />
