package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactAddressBookModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getContactAddressBookRecordHelper().gotoHome();
    //please check here validation for contact address book presence.
    if(!app.getContactAddressBookRecordHelper().isThereAContactAddressBookRecord()){
      app.getContactAddressBookRecordHelper().createContactAddressBookRecord(new ContactAddressBookRecordData("First name", "Middle_name", "Last_name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"),true);
    }
  }


  @Test(enabled = false)
  public void testContactAddressBookModification() {

//    int before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
    List<ContactAddressBookRecordData> before = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();
    int index =before.size()-1;
    ContactAddressBookRecordData contactAddressBookRecordData = new ContactAddressBookRecordData(before.get(index).getId(),"First name8", "Middle_name1", "Last_name8", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", null, "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980");
    app.getContactAddressBookRecordHelper().modifyContactAddressBookRecordData(index, contactAddressBookRecordData);
//    int after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordCount();
    List<ContactAddressBookRecordData> after = app.getContactAddressBookRecordHelper().getContactAddressBookRecordList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(index);
    before.add(contactAddressBookRecordData);

    Comparator<? super ContactAddressBookRecordData> byId=
            (Comparator<ContactAddressBookRecordData>) (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before,after);
  }


}
