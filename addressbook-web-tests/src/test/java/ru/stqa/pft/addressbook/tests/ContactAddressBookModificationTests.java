package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import java.util.HashSet;
import java.util.Set;

public class ContactAddressBookModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().gotoHome();
    //please check here validation for contact address book presence.
    if(app.contact().all().size()==0){
      app.contact().create(new ContactAddressBookRecordData().withFirstName("First name").withMiddleName("Middle_name").withLastName("Last_name").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home").withMobile("Mobile").withWork("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980"),true);
    }
  }


  @Test(enabled = true)
  public void testContactAddressBookModification() {

//    int before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
    Set<ContactAddressBookRecordData> before = app.contact().all();
    ContactAddressBookRecordData modifiedContact = before.iterator().next();
    ContactAddressBookRecordData contact = new ContactAddressBookRecordData()
            .withId(modifiedContact.getId()).withFirstName("First name8").withMiddleName("Middle_name").withLastName("Last_name8").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home").withMobile("Mobile").withWork("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980");
    app.contact().modify(contact);
//    int after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
    Set<ContactAddressBookRecordData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size());

    before.remove(modifiedContact);
    before.add(contact);

    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before,after);
  }


}
