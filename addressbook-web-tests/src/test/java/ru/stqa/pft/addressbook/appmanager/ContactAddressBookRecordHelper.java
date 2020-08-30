package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactAddressBookRecordHelper {
  private WebDriver wd;
  public ContactAddressBookRecordHelper(WebDriver wd) {
    this.wd=wd;
  }

  public void returnedHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

  public void submitContactAddressBookRecord() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

  public void fillContactAddressBookRecord(ContactAddressBookRecord addressBookRecord) {
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(addressBookRecord.getFirstName());
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(addressBookRecord.getMiddleName());
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(addressBookRecord.getLastName());
      wd.findElement(By.name("nickname")).sendKeys(addressBookRecord.getNickname());
      wd.findElement(By.name("title")).clear();
      wd.findElement(By.name("title")).sendKeys(addressBookRecord.getTitle());
      wd.findElement(By.name("company")).clear();
      wd.findElement(By.name("company")).sendKeys(addressBookRecord.getCompany());
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(addressBookRecord.getAddress());
      wd.findElement(By.name("home")).clear();
      wd.findElement(By.name("home")).sendKeys(addressBookRecord.getHome());
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys(addressBookRecord.getMobile());
      wd.findElement(By.name("work")).clear();
      wd.findElement(By.name("work")).sendKeys(addressBookRecord.getWork());
      wd.findElement(By.name("fax")).clear();
      wd.findElement(By.name("fax")).sendKeys(addressBookRecord.getFax());
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(addressBookRecord.getEmail());
      wd.findElement(By.name("email2")).clear();
      wd.findElement(By.name("email2")).sendKeys(addressBookRecord.getEmail2());
      wd.findElement(By.name("email3")).clear();
      wd.findElement(By.name("email3")).sendKeys(addressBookRecord.getEmail3());
      wd.findElement(By.name("homepage")).clear();
      wd.findElement(By.name("homepage")).sendKeys(addressBookRecord.getHomepage());
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(addressBookRecord.getBday());
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(addressBookRecord.getBmonth());
      wd.findElement(By.name("byear")).clear();
      wd.findElement(By.name("byear")).sendKeys(addressBookRecord.getByear());
      new Select(wd.findElement(By.name("aday"))).selectByVisibleText(addressBookRecord.getAday());
      new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(addressBookRecord.getAmonth());
      wd.findElement(By.name("ayear")).clear();
      wd.findElement(By.name("ayear")).sendKeys(addressBookRecord.getAyear());
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(addressBookRecord.getGroupName());
      wd.findElement(By.name("address2")).clear();
      wd.findElement(By.name("address2")).sendKeys(addressBookRecord.getAddress2());
      wd.findElement(By.name("phone2")).clear();
      wd.findElement(By.name("phone2")).sendKeys(addressBookRecord.getHome1());
      wd.findElement(By.name("notes")).clear();
      wd.findElement(By.name("notes")).sendKeys(addressBookRecord.getNotes());
    }

  public void initContactAddressRecord() {
      wd.findElement(By.linkText("add new")).click();
    }

  public void gotoHome() {
      wd.findElement(By.linkText("home")).click();
    }

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

  public void acceptAlterMessage() {
    wd.switchTo().alert().accept();
  }

  public void deleteSelectedContactAddressRecords() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
    //assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  public void selectContactAddressRecord() {
    wd.findElement(By.id("21")).click();
    boolean acceptNextAlert = true;
  }
}
