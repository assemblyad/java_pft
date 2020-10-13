package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name= "addressbook")
@XStreamAlias("ContactAddressBookRecordData")
public class ContactAddressBookRecordData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id =Integer.MAX_VALUE;
  @Expose
  @Column(name="firstName")
  private  String firstName;
  @Expose
  private  String middleName;
  @Expose
  @Column(name="lastName")
  private  String lastName;
  @Expose
  private  String nickname;
  @Expose
  private  String title;
  @Expose
  private  String company;
  @Expose
  @Type(type="text")
  private  String address;
  @Expose
  @Column(name="home")
  @Type(type="text")
  private  String home;
  @Expose
  @Type(type="text")
  @Column(name="mobile")
  private  String mobile;
  @Expose
  @Column(name="work")
  @Type(type="text")
  private  String work;
  @Expose
  @Type(type="text")
  private  String fax;
  @Expose
  @Type(type="text")
  private  String email;
  @Expose
  @Type(type="text")
  private  String email2;
  @Expose
  @Type(type="text")
  private  String email3;
  @Expose
  @Type(type="text")
  private  String homepage;
//  @Expose
//  @Transient
//  private  String groupName;
  @Expose
  @Type(type="text")
  private  String address2;
  @Expose
  @Type(type="text")
  private  String phone2;
  @Expose
  @Type(type="text")
  private  String notes;
  @Expose
  @Transient
//  @Type(type="tinyint")
  private  String bday;
  @Expose
  private  String bmonth;
  @Expose
  private  String byear;
  @Expose
  @Transient
  private  String aday;
  @Expose
  private  String amonth;
  @Expose
  private  String ayear;
  @Expose
  @Transient
  private String allPhones;
  @Expose
  @Transient
  private String allEmails;
  @Expose
  @Column(name="photo")
  @Type(type="text")
  private String photo;
//  private File photo;


  @ManyToMany(fetch= FetchType.EAGER)
  @JoinTable(name="address_in_groups",joinColumns = @JoinColumn(name="id")
                                     ,inverseJoinColumns =@JoinColumn(name="group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactAddressBookRecordData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  public File getPhoto() {
    if (photo!=null) {    return new File(photo);}
    else return null;
  }

  public ContactAddressBookRecordData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }



  /*
        public  ContactAddressBookRecordData() {
        }
        public ContactAddressBookRecordData(ContactAddressBookRecordData contactData) {
          this.id =contactData.getId();
          this.firstName=contactData.firstName;
          this.middleName=contactData.middleName;
          this.lastName=contactData.lastName;
          this.nickname=contactData.nickname;
          this.title=contactData.title;
          this.company=contactData.company;
          this.address=contactData.address;
          this.home=contactData.home;
          this.mobile=contactData.mobile;
          this.work=contactData.work;
          this.fax=contactData.fax;
          this.email=contactData.email;
          this.email2=contactData.email2;
          this.email3=contactData.email3;
          this.homepage=contactData.homepage;
          this.groupName=contactData.groupName;
          this.address2=contactData.address2;
          this.home1=contactData.home1;
          this.notes=contactData.notes;
          this.bday=contactData.bday;
          this.bmonth=contactData.bmonth;
          this.byear=contactData.byear;
          this.aday=contactData.aday;
          this.amonth=contactData.amonth;
          this.ayear=contactData.ayear;
        }
      */
  public int getId() {
  return id;
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

  public String getAllPhones() { return allPhones; }

  public String getHomePhone() {return home; }

  public String getMobilePhone() {
    return mobile;
  }

  public String getWorkPhone() {
    return work;
  }

  public String getFax() {
    return fax;
  }

  public String getAllEmails() { return allEmails; }

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
/*
  public String getGroupName() {
    return groupName;
  }
*/
  public String getAddress2() {
    return address2;
  }

  public String getPhone2() {
    return phone2;
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
  public ContactAddressBookRecordData withAllPhone(String allPhones) {
    this.allPhones=allPhones;
    return this;
  }
  public ContactAddressBookRecordData withHomePhone(String home) {
    this.home = home;
    return this;
  }

  public ContactAddressBookRecordData withMobilePhone(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactAddressBookRecordData withWorkPhone(String work) {
    this.work = work;
    return this;
  }

  public ContactAddressBookRecordData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public ContactAddressBookRecordData withAllEmails (String allEmails) {
    this.allEmails = allEmails;
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

  public ContactAddressBookRecordData withAddedGroup(GroupData group) {
    this.groups.add(group);
    return this;
  }
  public ContactAddressBookRecordData withDeletedGroup(GroupData group) {
    this.groups.remove(group);
    return this;
  }

  public ContactAddressBookRecordData withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public ContactAddressBookRecordData withHome1(String home1) {
    this.phone2 = home1;
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
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactAddressBookRecordData that = (ContactAddressBookRecordData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(middleName, that.middleName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, middleName, lastName);
  }

}
