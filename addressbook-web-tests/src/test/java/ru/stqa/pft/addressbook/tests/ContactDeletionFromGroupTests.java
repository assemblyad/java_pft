package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroupTests extends TestBase{

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

    Groups groupsInDb = app.db().groups();
    List<AddressInGroupsData> before =app.db().addressInGroups();

    //Connect contact to group if no any contact linked to group
    if (before.size()==0){
      app.contact().addContactToGroup(app.db().contacts().iterator().next(),groupsInDb);
      before =app.db().addressInGroups();
    }
    // all groups within contacts
    Groups groupsContact = new Groups(app.db()
            .groups().stream().filter((g)->g.getContacts().size()>0).collect(Collectors.toSet()));

    GroupData group = groupsContact.iterator().next();
    ContactAddressBookRecordData  contactCandidateForDeletion =group.getContacts().iterator().next();
    app.contact().deleteContactFromGroup(contactCandidateForDeletion, group);

    List<AddressInGroupsData> after =app.db().addressInGroups();
    Assert.assertEquals(after.size(),before.size()-1);

    Assert.assertEquals(after
            ,before.stream().filter((c)->(c.getId()!=contactCandidateForDeletion.getId()
                    && c.getGroupId()!=group.getId())).collect(Collectors.toList()));

  }
}
