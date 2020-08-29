package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.appmanager.ContactAddressBookRecord;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager();
  private WebDriver wd;
/*
  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    //app.init();
  }
*/

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
//    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");

  }

  protected void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  protected void returnedHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  protected void submitContactAddressBookRecord() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  protected void fillContactAddressBookRecord(ContactAddressBookRecord addressBookRecord) {
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

  protected void initContactAddressRecord() {
    wd.findElement(By.linkText("add new")).click();
  }

  protected void gotoHome() {
    wd.findElement(By.linkText("home")).click();
  }

  private void login(String username, String password) {
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }
/*
  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }
*/

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }


  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected void acceptAlterMessage() {
    wd.switchTo().alert().accept();
  }

  protected void deleteSelectedContactAddressRecords() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
    //assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  protected void selectContactAddressRecord() {
    wd.findElement(By.id("17")).click();
    boolean acceptNextAlert = true;
  }
}
