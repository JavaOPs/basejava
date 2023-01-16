package ru.javawebinar.basejava.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

public class ResumeServlet extends HttpServlet {

  private final Storage storage = Config.get().getStorage();


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String uuid = request.getParameter("uuid");
    String action = request.getParameter("action");
    if (action == null) {
      request.setAttribute("resumes", storage.getAllSorted());
      request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
      return;
    }
    Resume resume = null;
    switch (action) {
      case "delete":
        storage.delete(uuid);
        response.sendRedirect("resume");
        return;
      case "view":
      case "edit":
        resume = storage.get(uuid);
        break;
      default:
        throw new IllegalStateException("Action " + action + " is illegal!");
    }
    request.setAttribute("resume", resume);
    request.getRequestDispatcher(
        ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
    ).forward(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
