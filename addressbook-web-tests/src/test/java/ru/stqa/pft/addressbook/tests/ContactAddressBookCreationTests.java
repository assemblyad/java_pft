package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import java.util.Set;

public class ContactAddressBookCreationTests extends TestBase {


  @Test(enabled = false)
  public void testContactAddressBookCreation() throws Exception {
    app.contact().gotoHome();
    Set<ContactAddressBookRecordData> before = app.contact().all();
    ContactAddressBookRecordData contactAddressBookRecordData = new ContactAddressBookRecordData().withFirstName("First_name_03").withMiddleName("Middle_name").withLastName("Last_name_03").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home").withMobile("Mobile").withWork("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980");
    app.contact().create(contactAddressBookRecordData,true);
    Set<ContactAddressBookRecordData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

//    contactAddressBookRecordData.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    contactAddressBookRecordData.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
    before.add(contactAddressBookRecordData);

//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before, after);
  }

}
