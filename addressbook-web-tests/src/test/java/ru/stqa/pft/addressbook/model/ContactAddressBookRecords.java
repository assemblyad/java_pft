package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class ContactAddressBookRecords extends ForwardingSet<ContactAddressBookRecordData> {

  private Set<ContactAddressBookRecordData> delegate;

  public ContactAddressBookRecords(ContactAddressBookRecords contacts) {
    this.delegate = new HashSet<ContactAddressBookRecordData>(contacts.delegate);
  }

  public ContactAddressBookRecords() {
    this.delegate = new HashSet<ContactAddressBookRecordData>();
  }

  @Override
  protected Set<ContactAddressBookRecordData> delegate() {
    return delegate;
  }

  public ContactAddressBookRecords withAdded(ContactAddressBookRecordData contact){
    ContactAddressBookRecords contacts = new ContactAddressBookRecords (this);
    contacts.add(contact);
    return contacts;
  }
  public ContactAddressBookRecords withOut(ContactAddressBookRecordData contact){
    ContactAddressBookRecords contacts = new ContactAddressBookRecords (this);
    contacts.remove(contact);
    return contacts;
  }
}
