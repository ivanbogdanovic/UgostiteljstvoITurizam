package com.oglasi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Pagination extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter(); 
            out.print(" <div class='pagin'>");
                     if(request.getParameterMap().containsKey("page")&&Integer.parseInt(request.getParameter("page"))>1&&Integer.parseInt(request.getParameter("page"))<=((int)request.getAttribute("totalPages"))){
                            out.print("<span>");
                            out.print("<a href='?page="+(Integer.parseInt(request.getParameter("page"))-1)+"&");
                            out.print(request.getAttribute("javax.servlet.forward.query_string")==null?"":request.getAttribute("javax.servlet.forward.query_string").toString().replaceAll("page=[0-9]+&", ""));
                            out.print("'>");
                            out.print("&LT;&LT;");
                            out.print("</a>"); 
                            out.print("</span>");
                        }
                    for(int i=1;i<=((int)request.getAttribute("totalPages"));i++){
                        out.print("<span>");
                        if(!request.getParameterMap().containsKey("page")){
                            if(i==1){
                              out.print(i);  
                            }else{
                                out.print("<a href='?page="+i+"&");
                                out.print(request.getAttribute("javax.servlet.forward.query_string")==null?"":request.getAttribute("javax.servlet.forward.query_string").toString().replaceAll("page=[0-9]+&", ""));
                                out.print("'>");
                                out.print(i);
                                out.print("</a>");  
                            }
                        }else{
                            if(Integer.parseInt(request.getParameter("page"))!=i){
                                out.print("<a href='?page="+i+"&");
                                out.print(request.getAttribute("javax.servlet.forward.query_string")==null?"":request.getAttribute("javax.servlet.forward.query_string").toString().replaceAll("page=[0-9]+&", ""));
                                out.print("'>");
                                out.print(i);
                                out.print("</a>");
                            } else {
                                out.print(i);
                            }
                        }
                        out.print("</span>");
                    }
                    if(!request.getParameterMap().containsKey("page")&&((int)request.getAttribute("totalPages"))!=1){
                            out.print("<span><a href='?page=2&'>&GT;&GT;</a></span>");
                    }else if(Integer.parseInt(request.getParameter("page"))<((int)request.getAttribute("totalPages"))){
                            out.print("<span>");
                            out.print("<a href='?page="+(Integer.parseInt(request.getParameter("page"))+1)+"&");
                            out.print(request.getAttribute("javax.servlet.forward.query_string")==null?"":request.getAttribute("javax.servlet.forward.query_string").toString().replaceAll("page=[0-9]+&", ""));
                            out.print("'>");
                            out.print("&GT;&GT;");
                            out.print("</a>"); 
                            out.print("</span>");
                        }
                    out.print("</div>");
                    
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
