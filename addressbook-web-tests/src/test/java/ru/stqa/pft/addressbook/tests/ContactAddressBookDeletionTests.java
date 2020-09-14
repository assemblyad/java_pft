package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactAddressBookDeletionTests extends TestBase {

  @Test
  public void testContactAddressBookDeletion() throws Exception {
    app.getContactAddressBookRecordHelper().gotoHome();
    //please check here validation for contact address book presence
    if(!app.getContactAddressBookRecordHelper().isThereAContactAddressBookRecord()){
      app.getContactAddressBookRecordHelper().ContactAddressBookRecord(new ContactAddressBookRecordData("First name", "Middle_name", "Last_name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"),true);
    }
//    int before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
    List<ContactAddressBookRecordData> before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();
    app.getContactAddressBookRecordHelper().selectContactAddressRecord(before.size()-1);
    app.getContactAddressBookRecordHelper().deleteSelectedContactAddressRecords();
    app.getContactAddressBookRecordHelper().acceptAlterMessage();
    app.getContactAddressBookRecordHelper().gotoHome();
//    int after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
    List<ContactAddressBookRecordData>  after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();

    Assert.assertEquals(after.size(),before.size()-1);
    before.remove(before.size()-1);

    Assert.assertEquals(after,before);
  }

}
