package ru.javawebinar.basejava;

import java.time.Month;
import java.util.UUID;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Organization;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionLine;
import ru.javawebinar.basejava.model.SectionList;
import ru.javawebinar.basejava.model.SectionOrganization;
import ru.javawebinar.basejava.model.SectionType;

public class TestData {

  public static final String UUID_1 = UUID.randomUUID().toString();
  public static final String UUID_2 = UUID.randomUUID().toString();
  public static final String UUID_3 = UUID.randomUUID().toString();
  public static final String UUID_4 = UUID.randomUUID().toString();
  public static final Resume R1;
  public static final Resume R2;
  public static final Resume R3;
  public static final Resume R4;

  static {
    R1 = new Resume(UUID_1, "Name1");
    R2 = new Resume(UUID_2, "Name2");
    R3 = new Resume(UUID_3, "Name3");
    R4 = new Resume(UUID_4, "Name4");

    R1.setContact(ContactType.MOBILE_PHONE, "+79001234567");
    R1.setContact(ContactType.EMAIL, "abc@mail.ru");
    R4.setContact(ContactType.SKYPE, "Skype");
    R4.setContact(ContactType.MOBILE_PHONE, "+76666666666");

    R1.setSection(SectionType.OBJECTIVE, new SectionLine("Objective"));
    R1.setSection(SectionType.PERSONAL, new SectionLine("Personal data"));
    R1.setSection(SectionType.ACHIEVEMENT,
        new SectionList("Achievement11", "Achievement12", "Achievement13"));
    R1.setSection(SectionType.QUALIFICATION, new SectionList("Java", "git", "SQL"));
    R1.setSection(SectionType.EXPERIENCE,
        new SectionOrganization(
            new Organization("Organization11", "http://organization11.ru",
                new Organization.Position(2015, Month.JANUARY, "position11", "content11"),
                new Organization.Position(2010, Month.JULY, 2014, Month.DECEMBER, "position12",
                    "content12"))));
    R1.setSection(SectionType.EDUCATION,
        new SectionOrganization(
            new Organization("university", null,
                new Organization.Position(2005, Month.SEPTEMBER, 2010, Month.JUNE, "aspirant",
                    null),
                new Organization.Position(2001, Month.SEPTEMBER, 2005, Month.APRIL, "student",
                    "IT")),
            new Organization("Organization12", "http://organization12.ru")));

    R2.setContact(ContactType.MOBILE_PHONE, "1234567");
    R2.setContact(ContactType.SKYPE, "@SecondMember");
    R2.setSection(SectionType.EXPERIENCE,
        new SectionOrganization(
            new Organization("Organization21", "http://organization21.ru",
                new Organization.Position(2018, Month.JULY, "position21", "content21"))));
  }

}