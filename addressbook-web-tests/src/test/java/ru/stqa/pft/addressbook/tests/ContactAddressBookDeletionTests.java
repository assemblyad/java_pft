package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactAddressBookDeletionTests extends TestBase {

  @Test
  public void testContactAddressBookDeletion() throws Exception {
    app.gotoHome();
    app.selectContactAddressRecord();
    app.deleteSelectedContactAddressRecords();
    app.acceptAlterMessage();
    app.gotoHome();
    app.logout();
  }

}
