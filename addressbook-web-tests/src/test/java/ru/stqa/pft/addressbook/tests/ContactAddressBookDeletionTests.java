package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactAddressBookDeletionTests extends TestBase {

  @Test
  public void testContactAddressBookDeletion() throws Exception {
    gotoHome();
    selectContactAddressRecord();
    deleteSelectedContactAddressRecords();
    acceptAlterMessage();
    gotoHome();
    logout();
  }

}
