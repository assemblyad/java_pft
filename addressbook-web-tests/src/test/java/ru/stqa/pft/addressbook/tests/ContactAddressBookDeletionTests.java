package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecords;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddressBookDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().gotoHome();
    if(app.contact().all().size()==0){
      app.contact().create(new ContactAddressBookRecordData().withFirstName("First name").withMiddleName("Middle_name").withLastName("Last_name").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home").withMobile("Mobile").withWork("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980"),true);
    }
  }
  @Test(enabled = true)
  public void testContactAddressBookDeletion() throws Exception {
    ContactAddressBookRecords before = app.contact().all();
    ContactAddressBookRecordData deletedContact = before.iterator().next();
//    int index = before.size()-1;
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size()-1)); //Лекция 5.8. Хеширование и предварительные проверки
    ContactAddressBookRecords after = app.contact().all();
//    assertEquals(after.size(),before.size()-1);
    assertThat(after, equalTo(before.withOut(deletedContact)));
  }

}
