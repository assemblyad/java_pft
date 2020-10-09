package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecords;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

public class ContactAddressBookRecordHelper extends HelperBase{
  public ContactAddressBookRecordHelper(WebDriver wd) {
    super(wd);
  }

  public void returnedHomePage() {
      click(By.linkText("home page"));
    }

  public void submitContactAddressBookRecord() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

  public void fillContactAddressBookRecord(ContactAddressBookRecordData addressBookRecord, boolean creation) {
      type(By.name("firstname"),addressBookRecord.getFirstName());
      type(By.name("middlename"),addressBookRecord.getMiddleName());
      type(By.name("lastname"),addressBookRecord.getLastName());
      type(By.name("nickname"),addressBookRecord.getNickName());
      type(By.name("title"),addressBookRecord.getTitle());
      type(By.name("company"),addressBookRecord.getCompany());
      type(By.name("address"),addressBookRecord.getAddress());
      type(By.name("home"),addressBookRecord.getHomePhone());
      type(By.name("mobile"), addressBookRecord.getMobilePhone());
      type(By.name("work"), addressBookRecord.getWorkPhone());
      type(By.name("fax"),addressBookRecord.getFax());
      type(By.name("email"),addressBookRecord.getEmail());
      type(By.name("email2"), addressBookRecord.getEmail2());
      type(By.name("email3"),addressBookRecord.getEmail3());
      type(By.name("homepage"),addressBookRecord.getHomepage());
      select(By.name("bday"), addressBookRecord.getBday());
      select(By.name("bmonth"), addressBookRecord.getBmonth());
      type(By.name("byear"),addressBookRecord.getByear());
      select(By.name("aday"), addressBookRecord.getAday());
      select(By.name("amonth"), addressBookRecord.getAmonth());
      type(By.name("ayear"),addressBookRecord.getAyear());
//      type(By.name("photo"),addressBookRecord.getPhoto().getAbsolutePath());
      attach(By.name("photo"), addressBookRecord.getPhoto());

      if(creation){
        if(addressBookRecord.getGroups().size()>0) {
          Assert.assertTrue(addressBookRecord.getGroups().size()==1);
        select(By.name("new_group"), addressBookRecord.getGroups().iterator().next().getName());
        }
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
      type(By.name("address2"),addressBookRecord.getAddress2());
      type(By.name("phone2"),addressBookRecord.getPhone2());
      type(By.name("notes"),addressBookRecord.getNotes());
    }

  public void initContactAddressRecord() {
      click(By.linkText("add new"));
    }

  public void gotoHome() {
      click(By.linkText("home"));
    }
/*
  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
*/

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    //assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
//    click(By.id("37"));
    boolean acceptNextAlert = true;
  }
  public void selectContactById(int contactId) {
    wd.findElement(By.cssSelector("input[value='"+contactId+"']")).click();
  }
  public void initContactAddressRecordsModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void initContactAddressRecordsModification(int id) {

//  wd.findElements(By.xpath("//tr[@name='entry']["+(index+1)+"]//img[@title='Edit']")).get(0).click();
    wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();
  }


  public void submitContactAddressRecordsModification() {
    click(By.name("update"));
  }

  public void create(ContactAddressBookRecordData contactAddressBookRecordData, boolean b) {
    initContactAddressRecord();
//    fillContactAddressBookRecord(new ContactAddressBookRecordData("First name", "Middle_name", "Last_name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"),true);
    fillContactAddressBookRecord(contactAddressBookRecordData,true);
    submitContactAddressBookRecord();
    contactAddressBookRecord = null; //Лекция 5.7. Кеширование результатов длительных операций
    returnedHomePage();
  }
  public void modify(ContactAddressBookRecordData contact) {
    selectContactById(contact.getId());
//    app.getContactAddressBookRecordHelper().initContactAddressRecordsModification();
    initContactAddressRecordsModification(contact.getId());
    fillContactAddressBookRecord(contact,false);
    submitContactAddressRecordsModification();
    contactAddressBookRecord = null; //Лекция 5.7. Кеширование результатов длительных операций
    gotoHome();
  }

  public void addToGroup(int id, int groupId) {
    gotoHome();
    selectContactById(id);
    click(By.xpath("//select[@name='to_group']//option[@value='"+groupId+"']"));
    click(By.name("add"));
    gotoHome();
  }

  public GroupData addContactToGroup(ContactAddressBookRecordData contactAddedToGroup, Groups groupsInDB) {
    for (GroupData groupInDB: groupsInDB){
      if (!contactAddedToGroup.getGroups().contains(groupInDB)) {
        addToGroup(contactAddedToGroup.getId(), groupInDB.getId());
        return groupInDB;
      }
    }
    return null;
  }

  public GroupData deleteContactFromGroup(ContactAddressBookRecordData contactAddedToGroup, Groups groupsInDB) {
    for (GroupData groupInDB: groupsInDB){
      if (contactAddedToGroup.getGroups().contains(groupInDB)) {
        deleteFromGroup(contactAddedToGroup.getId(), groupInDB.getId());
        return groupInDB;
      }
    }
    return null;
  }

  public void deleteFromGroup(int id, int groupID) {
    gotoHome();
    click(By.xpath("//select[@name='group']//option[@value='"+groupID+"']"));
    selectContactById(id);
    click(By.name("remove"));
    gotoHome();
  }




  public ContactAddressBookRecordData infoFromEditForm(ContactAddressBookRecordData contact) {
    selectContactById(contact.getId());
    initContactAddressRecordsModification(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName  = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email1 = wd.findElement(By.name("email2")).getAttribute("value");
    String email2 = wd.findElement(By.name("email3")).getAttribute("value");
    String mail = wd.findElement(By.name("address")).getText();
    gotoHome();
    return new ContactAddressBookRecordData().withId(contact.getId())
            .withFirstName(firstName).withLastName(lastName).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email1).withEmail3(email2).withAddress(mail);
  }

  public void delete(ContactAddressBookRecordData contact) {
    selectContactById(contact.getId());
    deleteContact();
    acceptAlterMessage();
    contactAddressBookRecord = null; //Лекция 5.7. Кеширование результатов длительных операций
    gotoHome();
  }
  public boolean isThereAContactAddressBookRecord() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return  wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactAddressBookRecordData> list() {
    List <ContactAddressBookRecordData> contactAddressBookRecord = new ArrayList<ContactAddressBookRecordData>();
    List <WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement row: rows){
      String lastName = row.findElements(By.tagName("td")).get(1).getText();
      String firstName = row.findElements(By.tagName("td")).get(2).getText();
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      ContactAddressBookRecordData contactAddressBookRecordData = new ContactAddressBookRecordData().withId(id).withFirstName(firstName).withLastName(lastName);

      contactAddressBookRecord.add(contactAddressBookRecordData);
    }
    return contactAddressBookRecord;
  }

  private ContactAddressBookRecords contactAddressBookRecord = null;  //Лекция 5.7. Кеширование результатов длительных операций

  public ContactAddressBookRecords all() {
    if (contactAddressBookRecord !=null) {
      return new ContactAddressBookRecords(contactAddressBookRecord);
    }

    contactAddressBookRecord = new ContactAddressBookRecords();
    List <WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement row: rows){
      String lastName =  row.findElements(By.tagName("td")).get(1).getText();
      String firstName = row.findElements(By.tagName("td")).get(2).getText();
//    String [] phones = row.findElements(By.tagName("td")).get(5).getText().split("\n"); // Лекция 5.10. Режем строки (и немного про регулярные выражения)
      String allPhones = row.findElements(By.tagName("td")).get(5).getText(); //Лекция 5.11. Клеим строки: метод обратных проверок
      String allEmails = row.findElements(By.tagName("td")).get(4).getText(); //Лекция 5.11. Клеим строки: метод обратных проверок
      String mail = row.findElements(By.tagName("td")).get(3).getText(); //Лекция 5.11. Клеим строки: метод обратных проверок
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      ContactAddressBookRecordData contactAddressBookRecordData = new ContactAddressBookRecordData()
              .withId(id).withFirstName(firstName).withLastName(lastName).withAllPhone(allPhones).withAllEmails(allEmails).withAddress(mail);

      contactAddressBookRecord.add(contactAddressBookRecordData);
    }
    return new ContactAddressBookRecords(contactAddressBookRecord);
  }


}
