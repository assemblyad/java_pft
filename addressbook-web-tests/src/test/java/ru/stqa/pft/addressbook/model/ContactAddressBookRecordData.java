package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactAddressBookRecordData {
  private int id;
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String home;
  private final String mobile;
  private final String work;
  private final String fax;
  private final String email;
  private final String email2;
  private final String email3;
  private final String homepage;
  private final String groupName;
  private final String address2;
  private final String home1;
  private final String notes;
  private final String bday;
  private final String bmonth;
  private final String byear;
  private final String aday;
  private final String amonth;
  private final String ayear;

  public ContactAddressBookRecordData(String firstName, String middleName, String lastName, String nickname, String title, String company, String address, String home, String mobile, String work, String fax, String email, String email2, String email3, String homepage, String groupName, String address2, String home1, String notes, String bday, String bmonth, String byear, String aday, String amonth, String ayear) {
    this.id=Integer.MAX_VALUE;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.fax = fax;
    this.email = email;
    this.email2 = email2;
    this.email3 = email3;
    this.homepage = homepage;
    this.groupName = groupName;
    this.address2 = address2;
    this.home1 = home1;
    this.notes = notes;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.aday = aday;
    this.amonth = amonth;
    this.ayear = ayear;
  }

  public ContactAddressBookRecordData(int id, String firstName, String middleName, String lastName, String nickname, String title, String company, String address, String home, String mobile, String work, String fax, String email, String email2, String email3, String homepage, String groupName, String address2, String home1, String notes, String bday, String bmonth, String byear, String aday, String amonth, String ayear) {
    this.id=id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.fax = fax;
    this.email = email;
    this.email2 = email2;
    this.email3 = email3;
    this.homepage = homepage;
    this.groupName = groupName;
    this.address2 = address2;
    this.home1 = home1;
    this.notes = notes;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.aday = aday;
    this.amonth = amonth;
    this.ayear = ayear;
  }


  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickname() {
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

  public void setId(int id) {
    this.id = id;
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
