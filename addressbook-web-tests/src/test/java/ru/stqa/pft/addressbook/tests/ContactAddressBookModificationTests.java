package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactAddressBookRecord;

public class ContactAddressBookModificationTests extends TestBase {
  @Test
  public void testContactAddressBookModification() {
    app.getContactAddressBookRecordHelper().gotoHome();
    app.getContactAddressBookRecordHelper().selectContactAddressRecord();
    app.getContactAddressBookRecordHelper().initContactAddressRecordsModification();
    app.getContactAddressBookRecordHelper().fillContactAddressBookRecord(new ContactAddressBookRecord("First name1", "Middle_name1", "Last_name1", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"));
    app.getContactAddressBookRecordHelper().submitContactAddressRecordsModification();
    app.getContactAddressBookRecordHelper().gotoHome();

  }
}
