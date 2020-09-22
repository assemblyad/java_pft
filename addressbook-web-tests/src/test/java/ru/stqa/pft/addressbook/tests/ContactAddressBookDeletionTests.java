package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import java.util.List;

public class ContactAddressBookDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getContactAddressBookRecordHelper().gotoHome();
    if(!app.getContactAddressBookRecordHelper().isThereAContactAddressBookRecord()){
      app.getContactAddressBookRecordHelper().createContactAddressBookRecord(new ContactAddressBookRecordData("First name", "Middle_name", "Last_name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"),true);
    }
  }
  @Test(enabled = false)
  public void testContactAddressBookDeletion() throws Exception {
    List<ContactAddressBookRecordData> before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();
    int index = before.size()-1;
    app.getContactAddressBookRecordHelper().selectContactAddressRecord(index);
    app.getContactAddressBookRecordHelper().deleteSelectedContactAddressRecords();
    app.getContactAddressBookRecordHelper().acceptAlterMessage();
    app.getContactAddressBookRecordHelper().gotoHome();
    List<ContactAddressBookRecordData>  after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();

    Assert.assertEquals(after.size(),index);
    before.remove(index);

    Assert.assertEquals(after,before);
  }

}
