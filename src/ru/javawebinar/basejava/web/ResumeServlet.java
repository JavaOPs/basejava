package ru.javawebinar.basejava.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Link;
import ru.javawebinar.basejava.model.Organization;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.Section;
import ru.javawebinar.basejava.model.SectionLine;
import ru.javawebinar.basejava.model.SectionList;
import ru.javawebinar.basejava.model.SectionOrganization;
import ru.javawebinar.basejava.model.SectionType;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.DateUtil;
import ru.javawebinar.basejava.util.HtmlUtil;

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
    Resume resume;
    switch (action) {
      case "delete":
        storage.delete(uuid);
        response.sendRedirect("resume");
        return;
      case "view":
        resume = storage.get(uuid);
        break;
      case "edit":
        resume = storage.get(uuid);
        for (SectionType type : SectionType.values()) {
          Section section = resume.getSection(type);
          switch (type) {
            case OBJECTIVE:
            case PERSONAL:
              if (section == null) {
                section = SectionLine.EMPTY;
              }
              break;
            case ACHIEVEMENT:
            case QUALIFICATION:
              if (section == null) {
                section = SectionList.EMPTY;
              }
              break;
            case EXPERIENCE:
            case EDUCATION:
              SectionOrganization orgSection = (SectionOrganization) section;
              List<Organization> emptyFirstOrganizations = new ArrayList<>();
              emptyFirstOrganizations.add(Organization.EMPTY);
              if (orgSection != null) {
                for (Organization org : orgSection.getOrganizations()) {
                  List<Organization.Position> emptyFirstPositions = new ArrayList<>();
                  emptyFirstPositions.add(Organization.Position.EMPTY);
                  emptyFirstPositions.addAll(org.getPositions());
                  emptyFirstOrganizations.add(
                      new Organization(org.getHomePage(), emptyFirstPositions));
                }
              }
              section = new SectionOrganization(emptyFirstOrganizations);
              break;
          }
          resume.setSection(type, section);
        }
        break;
      default:
        throw new IllegalArgumentException("Action " + action + " is illegal");
    }
    request.setAttribute("resume", resume);
    request.getRequestDispatcher(
        ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
    ).forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String uuid = request.getParameter("uuid");
    String fullName = request.getParameter("fullName");
    Resume resume;
    final boolean isNotCreate = (uuid == null || uuid.length() == 0);
    if (isNotCreate) {
      resume = new Resume(fullName);
    } else {
      resume = storage.get(uuid);
      resume.setFullName(fullName);
    }
    for (ContactType type : ContactType.values()) {
      String value = request.getParameter(type.name());
      if (HtmlUtil.isEmpty(value)) {
        resume.getContacts().remove(type);
      } else {
        resume.setContact(type, value);
      }
    }
    for (SectionType type : SectionType.values()) {
      String value = request.getParameter(type.name());
      String[] values = request.getParameterValues(type.name());
      if (HtmlUtil.isEmpty(value) && values.length < 2) {
        resume.getSections().remove(type);
      } else {
        switch (type) {
          case OBJECTIVE:
          case PERSONAL:
            resume.setSection(type, new SectionLine(value));
            break;
          case ACHIEVEMENT:
          case QUALIFICATION:
            resume.setSection(type, new SectionList(value.split("\\n")));
            break;
          case EDUCATION:
          case EXPERIENCE:
            List<Organization> organizations = new ArrayList<>();
            String[] urls = request.getParameterValues(type.name() + "url");
            for (int i = 0; i < values.length; i++) {
              String name = values[i];
              if (!HtmlUtil.isEmpty(name)) {
                List<Organization.Position> positions = new ArrayList<>();
                String prefix = type.name() + i;
                String[] startDates = request.getParameterValues(prefix + "startDate");
                String[] endDates = request.getParameterValues(prefix + "endDate");
                String[] titles = request.getParameterValues(prefix + "title");
                String[] descriptions = request.getParameterValues(prefix + "description");
                for (int j = 0; j < titles.length; j++) {
                  if (!HtmlUtil.isEmpty(titles[j])) {
                    positions.add(new Organization.Position(DateUtil.parse(startDates[j]),
                        DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                  }
                }
                organizations.add(new Organization(new Link(name, urls[i]), positions));
              }
            }
            resume.setSection(type, new SectionOrganization(organizations));
            break;
        }
      }
    }
    if (isNotCreate) {
      storage.save(resume);
    } else {
      storage.update(resume);
    }
    response.sendRedirect("resume");
  }
}
