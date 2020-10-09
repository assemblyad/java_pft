package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroup extends TestBase{

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
  public void testContactDeletionFromGroup() {

    List<AddressInGroupsData> allAddressRecordsInGroups = app.db().addressInGroups();
    AddressInGroupsData allAddressRecordsInGroup = allAddressRecordsInGroups.iterator().next();



    ContactAddressBookRecords before = app.db().contacts();
    Groups groupsInDB =app.db().groups();


    ContactAddressBookRecordData contactAddedToGroup = before.iterator().next();
    Groups contactGroups = contactAddedToGroup.getGroups();
    int contactGroupSizeBefore=contactGroups.size();

    GroupData deletedGroup = app.contact().deleteContactFromGroup(contactAddedToGroup,groupsInDB);

    if (deletedGroup==null){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Group name").withHeader("Header").withFooter("Footer"));
      deletedGroup = app.contact().deleteContactFromGroup(contactAddedToGroup,groupsInDB);
    }

    ContactAddressBookRecords after = app.db().contacts();
    assertThat(after.stream().filter((c)->c.getId()==contactAddedToGroup.getId()).findFirst().get()
            .getGroups().size(),equalTo(contactGroupSizeBefore+1)); //Лекция 5.8. Хеширование и предварительные проверки
    assertThat(after.stream().filter((c)->c.getId()==contactAddedToGroup.getId()).findFirst().get()
            .getGroups(), equalTo(contactAddedToGroup.withAddedGroup(deletedGroup).getGroups()));

    verifyContactListInUI();
  }

}
