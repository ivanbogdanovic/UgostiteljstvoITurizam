<%@page import="com.oglasi.model.JobDao"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page import="com.oglasi.model.Job"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
      <c:forEach items="${job}" var="job">
      <div class="job">    
        <div class="job_title">
          <h5><a href="job/${job.jobId}">${job.title}</a></h5>
          <span class="label <c:choose><c:when test="${job.employmentTypeId.employmentTypeId == 1}">label-warning</c:when><c:when test="${job.employmentTypeId.employmentTypeId == 2}">label-success</c:when><c:when test="${job.employmentTypeId.employmentTypeId == 3}">label-info</c:when></c:choose>">${job.employmentTypeId.typeName}</span>
          
        </div>
        <div class="job_lo">
          <span class="job_comp"><a href="company_info/${job.companyId.companyId}"><strong>${job.companyId.name}</strong></a>, ${job.cityId.name}</span>
              <span><fmt:formatDate value="${job.activeDate}" pattern="dd.MM.yyyy "/></span>
        </div>
        <div class="job_logo">
          <img src="resources/logo/${job.companyId.logo}">
        </div>
      </div><!-- job -->
      </c:forEach>