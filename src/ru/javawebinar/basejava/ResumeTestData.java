package ru.javawebinar.basejava;

import java.util.Map.Entry;
import ru.javawebinar.basejava.model.Contact;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;

public class ResumeTestData {

  public static void main(String[] args) {
    Resume newResume = new Resume("Vladimir");
    Contact vladimirTelephone = new Contact(ContactType.TELEPHONE_NUMBER, "+79001234567");
    Contact vladimirSkype = new Contact(ContactType.SKYPE, "@vladimir");
    Contact vladimirEmail = new Contact(ContactType.EMAIL, "vladimir@gmail.com");
    Contact vladimirOther = new Contact(ContactType.OTHER_INFO, "other info");
    newResume.addContactInfo(ContactType.TELEPHONE_NUMBER, vladimirTelephone);
    newResume.addContactInfo(ContactType.SKYPE, vladimirSkype);
    newResume.addContactInfo(ContactType.EMAIL, vladimirEmail);
    newResume.addContactInfo(ContactType.OTHER_INFO, vladimirOther);
    newResume.removeContactInfo(ContactType.OTHER_INFO);

    for (Entry<ContactType, Contact> contact : newResume.getContacts().entrySet()) {
      System.out.println(contact.getKey() + " : " + contact.getValue().getContent());
    }
  }
}
