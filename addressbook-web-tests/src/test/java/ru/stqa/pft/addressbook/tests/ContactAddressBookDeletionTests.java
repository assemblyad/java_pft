package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactAddressBookDeletionTests extends TestBase {

  @Test
  public void testContactAddressBookDeletion() throws Exception {
    app.getContactAddressBookRecordHelper().gotoHome();
    app.getContactAddressBookRecordHelper().selectContactAddressRecord();
    app.getContactAddressBookRecordHelper().deleteSelectedContactAddressRecords();
    app.getContactAddressBookRecordHelper().acceptAlterMessage();
    app.getContactAddressBookRecordHelper().gotoHome();
//    app.logout();
  }

}
