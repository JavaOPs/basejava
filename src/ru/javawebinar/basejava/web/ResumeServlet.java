package ru.javawebinar.basejava.web;

import java.io.Writer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

public class ResumeServlet extends HttpServlet {

  private final Storage storage = Config.get().getStorage();


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setAttribute("resumes", storage.getAllSorted());
    request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
