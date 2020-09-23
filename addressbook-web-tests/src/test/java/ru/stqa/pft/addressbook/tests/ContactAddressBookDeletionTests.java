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
      app.contact().create(new ContactAddressBookRecordData().withFirstName("First name").withMiddleName("Middle_name").withLastName("Last_name").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home").withMobile("Mobile").withWork("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980"),true);
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
