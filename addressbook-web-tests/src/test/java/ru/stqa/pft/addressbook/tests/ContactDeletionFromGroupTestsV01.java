package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.security.acl.Group;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroupTestsV01 extends TestBase{

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

    Groups beforeGroupsInDb = app.db().groups();
    List<AddressInGroupsData> before =app.db().addressInGroups();
    ContactAddressBookRecords beforeAllContacts = app.db().contacts();
    //  List<AddressInGroupsData> before =app.db().addressInGroups();
    //
    //Connect contact to group if no any contact linked to group
    if (before.size()==0){
      app.contact().addContactToGroup(app.db().contacts().iterator().next(),beforeGroupsInDb);
      before =app.db().addressInGroups();
    }

    ContactAddressBookRecordData deletedContact =
            app.contact().deleteContactFrGroup(beforeAllContacts,beforeGroupsInDb);


    List<AddressInGroupsData> after =app.db().addressInGroups();
    ContactAddressBookRecords afterAllContacts = app.db().contacts();
    assertThat(after.size(),equalTo(before.size()-1)); //Лекция 5.8. Хеширование и предварительные проверки

    assertThat(afterAllContacts.stream().filter((c)->c.getId()==deletedContact.getId()).findFirst().get().getGroups()
            , equalTo(deletedContact.getGroups()));

  }
}
