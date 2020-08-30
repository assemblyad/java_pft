package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactAddressBookRecord;

public class ContactAddressBookCreationTests extends TestBase {

  @Test
  public void testContactAddressBookCreation() throws Exception {
    app.getContactAddressBookRecordHelper().gotoHome();
    app.getContactAddressBookRecordHelper().initContactAddressRecord();
    app.getContactAddressBookRecordHelper().fillContactAddressBookRecord(new ContactAddressBookRecord("First name", "Middle_name", "Last_name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"));
    app.getContactAddressBookRecordHelper().submitContactAddressBookRecord();
    app.getContactAddressBookRecordHelper().returnedHomePage();
//    app.logout();
  }

}
