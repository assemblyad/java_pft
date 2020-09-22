package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactAddressBookModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().gotoHome();
    //please check here validation for contact address book presence.
    if(app.contact().list().size()==0){
      app.contact().create(new ContactAddressBookRecordData().withFirstName("First name").withMiddleName("Middle_name").withLastName("Last_name").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home").withMobile("Mobile").withWork("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980"),true);
    }
  }


  @Test(enabled = true)
  public void testContactAddressBookModification() {

//    int before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
    List<ContactAddressBookRecordData> before = app.contact().list();
    int index =before.size()-1;
    ContactAddressBookRecordData contactAddressBookRecordData = new ContactAddressBookRecordData().withId(before.get(index).getId()).withFirstName("First name8").withMiddleName("Middle_name").withLastName("Last_name8").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home").withMobile("Mobile").withWork("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980");
    app.contact().modify(index, contactAddressBookRecordData);
//    int after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
    List<ContactAddressBookRecordData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size());

    before.remove(index);
    before.add(contactAddressBookRecordData);

    Comparator<? super ContactAddressBookRecordData> byId=
            (Comparator<ContactAddressBookRecordData>) (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before,after);
  }


}
