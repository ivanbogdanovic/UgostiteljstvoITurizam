<%@page import="com.oglasi.model.JobDao"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page import="com.oglasi.model.Job"%>
<%@page import="com.oglasi.servlet.Pagination" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>
    <base href="/UgostiteljstvoITurizam/"/>
 
    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>

    <div class="header">
      <div class="navbar-default">
        <div class="container">
          <div class="main_nav">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example"> 
               <span class="icon-bar"></span> 
               <span class="icon-bar"></span> 
               <span class="icon-bar"></span> 
             </button>
           </div><!-- navbar-header -->
           
           <div class="collapse navbar-collapse" id="example">
            <ul class="nav navbar-nav ">
              <li><a href="">Home</a></li>
              <li><a href="#">O nama</a></li>
              <li><a href="#">Kontakt</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
            <c:when test="${comp==null}">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Poslodavci</a>
                <ul class="dropdown-menu">
                  <li>
                    <form method="post" action="company/company_profile_log" class="form">
                      <div class="form-group">
                        <label class="sr-only" for="exampleInputEmail3">Email address</label>
                        <input type="email" name="email" class="form-control input-sm" id="exampleInputEmail3" placeholder="Email"/>
                      </div>
                      <div class="form-group">
                        <label class="sr-only" for="exampleInputPassword3">Password</label>
                        <input type="password" name="password" class="form-control input-sm" id="exampleInputPassword3" placeholder="Password"/>
                      </div>
                      <button type="submit" class="btn btn-default input-sm" name="snimi">Uloguj se</button>
                      <span><a href="#"> Zaboravili ste lozinku?</a></span>
                    </form>
                  </li>
                  <li role="separator" class="divider"></li>
                  <li><a href="company/sign_up_form">Neregistrovani korisnici</a></li>
                </ul>
              </li>
              </c:when>
            <c:when test="${comp!=null}">
                <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Profil</a>
                <ul class="dropdown-menu">
                    <li><a href="company/company_profile" >Moj profil</a></li>
                    <li><a href="company/add_job" >Postavi posao</a></li>
                    <li><a href="sign_out">Sign Out</a></li>
                </ul>
                 </li>
            </c:when>
              </c:choose>
            </ul>
          </div> <!-- collapse navbar-collapse -->
        </div>
      </div><!-- container --> 
      </div><!-- navbar-default --> 
    </div><!-- header -->
     <div class="container">
        <div class="message">${message}</div>
    </div>
