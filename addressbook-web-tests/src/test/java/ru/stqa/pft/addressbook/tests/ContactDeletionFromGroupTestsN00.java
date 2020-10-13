package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecords;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroupTestsN00 extends TestBase {

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

    ContactAddressBookRecords before = app.db().contacts();
    Groups groupsBefore =app.db().groups();
    ContactAddressBookRecordData contactDeletedFromGroup=null;
    GroupData groupWithDeletedContact;
    ContactAddressBookRecords contactListBefore;

    // all groups that have contacts
    Groups groupsContact = new Groups(app.db()
            .groups().stream().filter((g)->g.getContacts().size()>0).collect(Collectors.toSet()));

    //if no group has contact, add contact to any existing group
    if (groupsContact.size()<1){
      app.contact().addContactToGroup(app.db().contacts().iterator().next(),groupsBefore);
      groupsBefore =app.db().groups();
    }

    //delete contact from group
    groupWithDeletedContact= app.contact().deleteContactFromGroup(before, groupsBefore);
    contactListBefore=groupWithDeletedContact.getContacts();

    //find deleted contact from given group
    Groups groupsAfter = app.db().groups();
    ContactAddressBookRecords contactListAfter=groupsAfter.stream()
            .filter((g)->g.getId()==groupWithDeletedContact.getId()).findFirst().get().getContacts();

    for (ContactAddressBookRecordData contactBefore: contactListBefore) {
      if (!contactListAfter.contains(contactBefore)) {
        contactDeletedFromGroup = contactBefore;
        break;
      }
    }

      assertThat(groupWithDeletedContact.getContacts().size(), equalTo(contactListAfter.size() + 1));
      assertThat(groupWithDeletedContact.getContacts(), equalTo(contactListAfter.withAdded(contactDeletedFromGroup)));

    //verifyContactListInUI();
  }
}
