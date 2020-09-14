package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import java.util.List;

public class ContactAddressBookCreationTests extends TestBase {

  @Test
  public void testContactAddressBookCreation() throws Exception {
    app.getContactAddressBookRecordHelper().gotoHome();
    List<ContactAddressBookRecordData> before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();
    app.getContactAddressBookRecordHelper().initContactAddressRecord();
    app.getContactAddressBookRecordHelper().fillContactAddressBookRecord(new ContactAddressBookRecordData("First name", "Middle_name", "Last_name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"),true);
    app.getContactAddressBookRecordHelper().submitContactAddressBookRecord();
    app.getContactAddressBookRecordHelper().returnedHomePage();
    List<ContactAddressBookRecordData> after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();
    Assert.assertEquals(after.size(),before.size()+1);
  }

}
