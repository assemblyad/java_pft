package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import java.util.Comparator;
import java.util.List;

public class ContactAddressBookCreationTests extends TestBase {


  @Test(enabled = false)
  public void testContactAddressBookCreation() throws Exception {
    app.getContactAddressBookRecordHelper().gotoHome();
    List<ContactAddressBookRecordData> before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();
    ContactAddressBookRecordData contactAddressBookRecordData = new ContactAddressBookRecordData("First_name_03", "Middle_name", "Last_name_03", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980");
    app.getContactAddressBookRecordHelper().createContactAddressBookRecord(contactAddressBookRecordData,true);
    List<ContactAddressBookRecordData> after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();
    Assert.assertEquals(after.size(), before.size() + 1);
/*
    int max=0;
      for (ContactAddressBookRecordData contactAddressBookRecord: after){
        if (contactAddressBookRecord.getId()>max) {
          max=contactAddressBookRecord.getId();
        }
      }
*/

    contactAddressBookRecordData.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contactAddressBookRecordData);

    Comparator<? super ContactAddressBookRecordData> byId = (Comparator<ContactAddressBookRecordData>) (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);

//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before, after);
  }

}
