package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactAddressBookDeletionTests extends TestBase {

  @Test
  public void testContactAddressBookDeletion() throws Exception {
    app.getContactAddressBookRecordHelper().gotoHome();
    if(!app.getContactAddressBookRecordHelper().isThereAContactAddressBookRecord()){
      app.getContactAddressBookRecordHelper().ContactAddressBookRecord(new ContactAddressBookRecordData("First name", "Middle_name", "Last_name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"),true);
    }
    app.getContactAddressBookRecordHelper().selectContactAddressRecord();
    app.getContactAddressBookRecordHelper().deleteSelectedContactAddressRecords();
    app.getContactAddressBookRecordHelper().acceptAlterMessage();
    app.getContactAddressBookRecordHelper().gotoHome();
//    app.logout();
  }

}
