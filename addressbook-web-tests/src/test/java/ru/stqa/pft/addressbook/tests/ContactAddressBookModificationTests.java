package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import java.util.List;

public class ContactAddressBookModificationTests extends TestBase {
  @Test
  public void testContactAddressBookModification() {
    app.getContactAddressBookRecordHelper().gotoHome();
    //please check here validation for contact address book presence.
    if(!app.getContactAddressBookRecordHelper().isThereAContactAddressBookRecord()){
      app.getContactAddressBookRecordHelper().ContactAddressBookRecord(new ContactAddressBookRecordData("First name", "Middle_name", "Last_name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"),true);
    }
//    int before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
    List<ContactAddressBookRecordData> before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();
    app.getContactAddressBookRecordHelper().selectContactAddressRecord(before.size()-1);
    app.getContactAddressBookRecordHelper().initContactAddressRecordsModification();
    app.getContactAddressBookRecordHelper().fillContactAddressBookRecord(new ContactAddressBookRecordData("First name1", "Middle_name1", "Last_name1", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", null, "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"),false);
    app.getContactAddressBookRecordHelper().submitContactAddressRecordsModification();
    app.getContactAddressBookRecordHelper().gotoHome();
//    int after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
    List<ContactAddressBookRecordData> after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();
    Assert.assertEquals(after.size(),before.size());
  }
}
