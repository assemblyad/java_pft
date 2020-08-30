package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

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

  public void fillContactAddressBookRecord(ContactAddressBookRecord addressBookRecord) {
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
      select(By.name("new_group"), addressBookRecord.getGroupName());
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

  public void deleteSelectedContactAddressRecords() {
    click(By.xpath("//input[@value='Delete']"));
    //assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  public void selectContactAddressRecord() {
    click(By.id("18"));
    boolean acceptNextAlert = true;
  }
}
