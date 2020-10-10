package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecords;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdditionToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsGroupsExists() {
    if(app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Group name").withHeader("Header").withFooter("Footer"));
    }
  }
  @BeforeMethod
  public void ensurePreconditionsContactExists(){
    app.contact().gotoHome();
    if(app.db().contacts().size()==0){
      app.contact().create(new ContactAddressBookRecordData().withFirstName("First name").withMiddleName("Middle_name").withLastName("Last_name").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHomePhone("Home").withMobilePhone("Mobile").withWorkPhone("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage")
              //.withGroupName("Group name")
              .withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980"),true);
    }
  }

  @Test(enabled = true)
  public void testContactAdditionToGroup() {

    ContactAddressBookRecords before = app.db().contacts();
    Groups groupsInDB =app.db().groups();
    ContactAddressBookRecordData contactAddedToGroup = before.iterator().next();
    Groups contactGroups = contactAddedToGroup.getGroups();
    int contactGroupSizeBefore=contactGroups.size();

    GroupData addedGroup = app.contact().addContactToGroup(contactAddedToGroup,groupsInDB);

    if (addedGroup==null){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Group name").withHeader("Header").withFooter("Footer"));
      addedGroup = app.contact().addContactToGroup(contactAddedToGroup,groupsInDB);
    }

    ContactAddressBookRecords after = app.db().contacts();
    assertThat(after.stream().filter((c)->c.getId()==contactAddedToGroup.getId()).findFirst().get()
            .getGroups().size(),equalTo(contactGroupSizeBefore+1)); //Лекция 5.8. Хеширование и предварительные проверки
    assertThat(after.stream().filter((c)->c.getId()==contactAddedToGroup.getId()).findFirst().get()
            .getGroups(), equalTo(contactAddedToGroup.withAddedGroup(addedGroup).getGroups()));
    //verifyContactListInUI();
  }

}
