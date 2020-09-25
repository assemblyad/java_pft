package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecords;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressBookCreationTests extends TestBase {


  @Test(enabled = true)
  public void testContactAddressBookCreation() throws Exception {
    app.contact().gotoHome();
    ContactAddressBookRecords before = app.contact().all();
    ContactAddressBookRecordData contactAddressBookRecordData = new ContactAddressBookRecordData().withFirstName("First_name_03").withMiddleName("Middle_name").withLastName("Last_name_03").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHomePhone("").withMobilePhone("222").withWorkPhone("111").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980");
    app.contact().create(contactAddressBookRecordData,true);
    assertThat(app.contact().count(), equalTo(before.size()));
    ContactAddressBookRecords after = app.contact().all();
//    contactAddressBookRecordData.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
//    before.add(contactAddressBookRecordData);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    assertThat(after, equalTo(
            before.withAdded(contactAddressBookRecordData.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }
  @Test(enabled = false)
  public void testContactBadAddressBookCreation() throws Exception {
    app.contact().gotoHome();
    ContactAddressBookRecords before = app.contact().all();
    ContactAddressBookRecordData contactAddressBookRecordData = new ContactAddressBookRecordData().withFirstName("First_name_03'").withMiddleName("Middle_name").withLastName("Last_name_03").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHomePhone("Home").withMobilePhone("Mobile").withWorkPhone("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980");
    app.contact().create(contactAddressBookRecordData,true);
    assertThat(app.contact().count(), equalTo(before.size())); //Лекция 5.8. Хеширование и предварительные проверки
    ContactAddressBookRecords after = app.contact().all();
//    contactAddressBookRecordData.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
//    before.add(contactAddressBookRecordData);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    assertThat(after, equalTo(before));
  }
}
