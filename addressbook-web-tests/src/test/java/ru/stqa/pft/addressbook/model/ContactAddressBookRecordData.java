package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactAddressBookRecordData {
  private int id =Integer.MAX_VALUE;
  private  String firstName;
  private  String middleName;
  private  String lastName;
  private  String nickname;
  private  String title;
  private  String company;
  private  String address;
  private  String home;
  private  String mobile;
  private  String work;
  private  String fax;
  private  String email;
  private  String email2;
  private  String email3;
  private  String homepage;
  private  String groupName;
  private  String address2;
  private  String home1;
  private  String notes;
  private  String bday;
  private  String bmonth;
  private  String byear;
  private  String aday;
  private  String amonth;
  private  String ayear;

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getGroupName() {
    return groupName;
  }

  public String getAddress2() {
    return address2;
  }

  public String getHome1() {
    return home1;
  }

  public String getNotes() {
    return notes;
  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

  public String getAday() {
    return aday;
  }

  public String getAmonth() {
    return amonth;
  }

  public String getAyear() {
    return ayear;
  }

  public ContactAddressBookRecordData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactAddressBookRecordData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactAddressBookRecordData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactAddressBookRecordData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactAddressBookRecordData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactAddressBookRecordData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactAddressBookRecordData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactAddressBookRecordData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactAddressBookRecordData withHome(String home) {
    this.home = home;
    return this;
  }

  public ContactAddressBookRecordData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactAddressBookRecordData withWork(String work) {
    this.work = work;
    return this;
  }

  public ContactAddressBookRecordData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public ContactAddressBookRecordData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactAddressBookRecordData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactAddressBookRecordData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactAddressBookRecordData withHomepage(String homepage) {
    this.homepage = homepage;
    return this;
  }

  public ContactAddressBookRecordData withGroupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public ContactAddressBookRecordData withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public ContactAddressBookRecordData withHome1(String home1) {
    this.home1 = home1;
    return this;
  }

  public ContactAddressBookRecordData withNotes(String notes) {
    this.notes = notes;
    return this;
  }

  public ContactAddressBookRecordData withBday(String bday) {
    this.bday = bday;
    return this;
  }

  public ContactAddressBookRecordData withBmonth(String bmonth) {
    this.bmonth = bmonth;
    return this;
  }

  public ContactAddressBookRecordData withByear(String byear) {
    this.byear = byear;
    return this;
  }

  public ContactAddressBookRecordData withAday(String aday) {
    this.aday = aday;
    return this;
  }

  public ContactAddressBookRecordData withAmonth(String amonth) {
    this.amonth = amonth;
    return this;
  }

  public ContactAddressBookRecordData withAyear(String ayear) {
    this.ayear = ayear;
    return this;
  }

  @Override
  public String toString() {
    return "ContactAddressBookRecordData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
/*
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactAddressBookRecordData that = (ContactAddressBookRecordData) o;
    return Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }
*/

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactAddressBookRecordData that = (ContactAddressBookRecordData) o;
    return Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

  public int getId() {
    return id;
  }

}
