package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

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
      type(By.name("nickname"),addressBookRecord.getNickname());
      type(By.name("title"),addressBookRecord.getTitle());
      type(By.name("company"),addressBookRecord.getCompany());
      type(By.name("address"),addressBookRecord.getAddress());
      type(By.name("home"),addressBookRecord.getHome());
      type(By.name("mobile"), addressBookRecord.getMobile());
      type(By.name("work"), addressBookRecord.getWork());
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
      if(creation){
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(addressBookRecord.getGroupName());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
      type(By.name("address2"),addressBookRecord.getAddress2());
      type(By.name("phone2"),addressBookRecord.getHome1());
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

  public void initContactAddressRecordsModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void initContactAddressRecordsModification(int index) {

      wd.findElements(By.xpath("//tr[@name='entry']["+(index+1)+"]//img[@title='Edit']")).get(0).click();
}

  public void submitContactAddressRecordsModification() {
    click(By.name("update"));
  }

  public void create(ContactAddressBookRecordData contactAddressBookRecordData, boolean b) {
    initContactAddressRecord();
//    fillContactAddressBookRecord(new ContactAddressBookRecordData("First name", "Middle_name", "Last_name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"),true);
    fillContactAddressBookRecord(contactAddressBookRecordData,true);
    submitContactAddressBookRecord();
    returnedHomePage();
  }
  public void modify(int index, ContactAddressBookRecordData contactAddressBookRecordData) {
    selectContact(index);
//    app.getContactAddressBookRecordHelper().initContactAddressRecordsModification();
    initContactAddressRecordsModification(index);
    fillContactAddressBookRecord(contactAddressBookRecordData,false);
    submitContactAddressRecordsModification();
    gotoHome();
  }
  public boolean isThereAContactAddressBookRecord() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactAddressBookRecordCount() {
    return  wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactAddressBookRecordData> list() {
    List <ContactAddressBookRecordData> contactAddressBookRecord = new ArrayList<ContactAddressBookRecordData>();
    List <WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement row: rows){
      String lastName = row.findElements(By.tagName("td")).get(1).getText();
      String firstName = row.findElements(By.tagName("td")).get(2).getText();
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      ContactAddressBookRecordData contactAddressBookRecordData = new ContactAddressBookRecordData(id,firstName,null,lastName,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
      contactAddressBookRecord.add(contactAddressBookRecordData);
    }
    return contactAddressBookRecord;
  }
}
