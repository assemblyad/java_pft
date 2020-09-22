package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import java.util.List;

public class ContactAddressBookDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().gotoHome();
    if(app.contact().list().size()==0){
      app.contact().create(new ContactAddressBookRecordData("First name", "Middle_name", "Last_name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"),true);
    }
  }
  @Test(enabled = false)
  public void testContactAddressBookDeletion() throws Exception {
    List<ContactAddressBookRecordData> before = app.contact().list();
    int index = before.size()-1;
    app.contact().selectContact(index);
    app.contact().deleteContact();
    app.contact().acceptAlterMessage();
    app.contact().gotoHome();
    List<ContactAddressBookRecordData>  after = app.contact().list();

    Assert.assertEquals(after.size(),index);
    before.remove(index);

    Assert.assertEquals(after,before);
  }

}
