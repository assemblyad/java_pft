package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import java.util.Comparator;
import java.util.List;

public class ContactAddressBookCreationTests extends TestBase {


  @Test(enabled = true)
  public void testContactAddressBookCreation() throws Exception {
    app.contact().gotoHome();
    List<ContactAddressBookRecordData> before = app.contact().list();
    ContactAddressBookRecordData contactAddressBookRecordData = new ContactAddressBookRecordData().withFirstName("First_name_03").withMiddleName("Middle_name").withLastName("Last_name_03").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home").withMobile("Mobile").withWork("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980");
    app.contact().create(contactAddressBookRecordData,true);
    List<ContactAddressBookRecordData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
/*
    int max=0;
      for (ContactAddressBookRecordData contactAddressBookRecord: after){
        if (contactAddressBookRecord.getId()>max) {
          max=contactAddressBookRecord.getId();
        }
      }
*/

    contactAddressBookRecordData.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contactAddressBookRecordData);

    Comparator<? super ContactAddressBookRecordData> byId = (Comparator<ContactAddressBookRecordData>) (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);

//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before, after);
  }

}
