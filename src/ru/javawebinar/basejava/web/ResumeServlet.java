package ru.javawebinar.basejava.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    String name = request.getParameter("name");
    response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + '!');
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
