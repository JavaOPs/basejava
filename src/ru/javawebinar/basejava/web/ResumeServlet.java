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
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    Writer writer = response.getWriter();
    writer.write(
        "<html>\n" +
            "<head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
            "    <title>Список всех резюме</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<section>\n" +
            "<table border=\"1\" cellpadding=\"8\" cellspacing=\"0\">\n" +
            "    <tr>\n" +
            "        <th>Имя</th>\n" +
            "        <th>Email</th>\n" +
            "    </tr>\n");
    for (Resume resume : storage.getAllSorted()) {
      writer.write(
          "<tr>\n" +
              "     <td><a href=\"resume?uuid=" + resume.getUuid() + "\">" + resume.getFullName()
              + "</a></td>\n" +
              "     <td>" + resume.getContact(ContactType.EMAIL) + "</td>\n" +
              "</tr>\n");
    }
    writer.write("</table>\n" +
        "</section>\n" +
        "</body>\n" +
        "</html>\n");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
