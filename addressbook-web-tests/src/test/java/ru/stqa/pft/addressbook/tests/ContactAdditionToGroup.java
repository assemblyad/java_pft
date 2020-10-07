package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecords;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdditionToGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditionsContactExists(){
    app.contact().gotoHome();
    //please check here validation for contact address book presence.
    //if(app.contact().all().size()==0){
    if(app.db().contacts().size()==0){
      app.contact().create(new ContactAddressBookRecordData().withFirstName("First name").withMiddleName("Middle_name").withLastName("Last_name").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHomePhone("Home").withMobilePhone("Mobile").withWorkPhone("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage")
              //.withGroupName("Group name")
              .withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980"),true);
    }
  }
  @BeforeMethod
  public void ensurePreconditionsGroupsExists() {
    //app.goTo().groupPage();
    //please check here validation for group presence
//    if(app.group().all().size()==0){
    if(app.db().groups().size()==0){
      app.group().create(new GroupData().withName("Group name").withHeader("Header").withFooter("Footer"));
    }
  }
  @Test(enabled = true)
  public void testContactAdditionToGroup() {

//    int before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
//    ContactAddressBookRecords before = app.contact().all();
    ContactAddressBookRecords before = app.db().contacts();
    Groups groups =app.db().groups();
    ContactAddressBookRecordData modifiedContact = before.iterator().next();
/*
    ContactAddressBookRecordData contact = new ContactAddressBookRecordData()
            .withId(modifiedContact.getId()).withFirstName("First name8").withMiddleName("Middle_name").withLastName("Last_name8").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHomePhone("Home").withMobilePhone("Mobile").withWorkPhone("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage")
            //.withGroupName("Group name")
            .withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980");

 */

    app.contact().addToGroup(modifiedContact.getId(),groups.iterator().next().getName());
//    assertThat(app.contact().count(),equalTo(before.size())); //Лекция 5.8. Хеширование и предварительные проверки
    assertThat(app.db().contacts().size(),equalTo(before.size())); //Лекция 5.8. Хеширование и предварительные проверки
//    int after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
//    ContactAddressBookRecords after = app.contact().all();
    ContactAddressBookRecords after = app.db().contacts();
    Assert.assertEquals(after.size(),before.size());
//    assertThat(after, equalTo(
//            before.withOut(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }


}
