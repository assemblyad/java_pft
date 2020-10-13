package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecords;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdditionToGroupTestsN00 extends TestBase {

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
    GroupData addedGroupToContact=null;
    ContactAddressBookRecordData contactAddedToGroup;
    Groups contactGroupsBefore;



    //if all contacts are in all groups then create a new group
    if (app.contact().addContactToGroup(before, groupsInDB)==null) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Group name").withHeader("Header").withFooter("Footer"));
      groupsInDB =app.db().groups();
    }

    //Link to contact to any free group
    contactAddedToGroup=app.contact().addContactToGroup(before, groupsInDB);
    contactGroupsBefore=contactAddedToGroup.getGroups();
    ContactAddressBookRecords after = app.db().contacts();
    Groups contactGroupsAfter=after.stream().filter((c)->c.getId()==contactAddedToGroup.getId()).findFirst().get()
            .getGroups();

    //find added group to contact
   outer: for (GroupData groupDataAfter: contactGroupsAfter){
      for(GroupData groupDataBefore: contactGroupsBefore) {
        if(groupDataBefore.getId()!=groupDataAfter.getId()){
            addedGroupToContact=groupDataAfter;
            break outer;
          }
      }
    }

    assertThat(after.stream().filter((c)->c.getId()==contactAddedToGroup.getId()).findFirst().get()
            .getGroups().size(),equalTo(contactAddedToGroup.getGroups().size()+1));

    assertThat(after.stream().filter((c)->c.getId()==contactAddedToGroup.getId()).findFirst().get()
            .getGroups(), equalTo(contactAddedToGroup.withAddedGroup(addedGroupToContact).getGroups()));

    //verifyContactListInUI();
  }
}
