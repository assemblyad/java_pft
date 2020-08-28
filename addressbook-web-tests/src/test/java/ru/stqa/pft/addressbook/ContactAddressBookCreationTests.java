package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactAddressBookCreationTests {
  private WebDriver wb;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wb = new FirefoxDriver();
    wb.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wb.get("http://localhost/addressbook/");
    login("admin", "secret");

  }

  @Test
  public void testContactAddressBookCreation() throws Exception {
    gotoHome();
    initContactAddressRecord();
    fillContactAddressBookRecord(new ContactAddressBookRecord("First name", "Middle_name", "Last_name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Group name", "Greenwood Village", "Home", "Notes", "5", "April", "1975", "5", "April", "1980"));
    submitContactAddressBookRecord();
    gotoHomePage();
    logout();
  }

  private void logout() {
    wb.findElement(By.linkText("Logout")).click();
  }

  private void gotoHomePage() {
    wb.findElement(By.linkText("home page")).click();
  }

  private void submitContactAddressBookRecord() {
    wb.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void fillContactAddressBookRecord(ContactAddressBookRecord addressBookRecord) {
    wb.findElement(By.name("firstname")).clear();
    wb.findElement(By.name("firstname")).sendKeys(addressBookRecord.getFirstName());
    wb.findElement(By.name("middlename")).clear();
    wb.findElement(By.name("middlename")).sendKeys(addressBookRecord.getMiddleName());
    wb.findElement(By.name("lastname")).clear();
    wb.findElement(By.name("lastname")).sendKeys(addressBookRecord.getLastName());
    wb.findElement(By.name("nickname")).sendKeys(addressBookRecord.getNickname());
    wb.findElement(By.name("title")).clear();
    wb.findElement(By.name("title")).sendKeys(addressBookRecord.getTitle());
    wb.findElement(By.name("company")).clear();
    wb.findElement(By.name("company")).sendKeys(addressBookRecord.getCompany());
    wb.findElement(By.name("address")).clear();
    wb.findElement(By.name("address")).sendKeys(addressBookRecord.getAddress());
    wb.findElement(By.name("home")).clear();
    wb.findElement(By.name("home")).sendKeys(addressBookRecord.getHome());
    wb.findElement(By.name("mobile")).clear();
    wb.findElement(By.name("mobile")).sendKeys(addressBookRecord.getMobile());
    wb.findElement(By.name("work")).clear();
    wb.findElement(By.name("work")).sendKeys(addressBookRecord.getWork());
    wb.findElement(By.name("fax")).clear();
    wb.findElement(By.name("fax")).sendKeys(addressBookRecord.getFax());
    wb.findElement(By.name("email")).clear();
    wb.findElement(By.name("email")).sendKeys(addressBookRecord.getEmail());
    wb.findElement(By.name("email2")).clear();
    wb.findElement(By.name("email2")).sendKeys(addressBookRecord.getEmail2());
    wb.findElement(By.name("email3")).clear();
    wb.findElement(By.name("email3")).sendKeys(addressBookRecord.getEmail3());
    wb.findElement(By.name("homepage")).clear();
    wb.findElement(By.name("homepage")).sendKeys(addressBookRecord.getHomepage());
    new Select(wb.findElement(By.name("bday"))).selectByVisibleText(addressBookRecord.getBday());
    new Select(wb.findElement(By.name("bmonth"))).selectByVisibleText(addressBookRecord.getBmonth());
    wb.findElement(By.name("byear")).clear();
    wb.findElement(By.name("byear")).sendKeys(addressBookRecord.getByear());
    new Select(wb.findElement(By.name("aday"))).selectByVisibleText(addressBookRecord.getAday());
    new Select(wb.findElement(By.name("amonth"))).selectByVisibleText(addressBookRecord.getAmonth());
    wb.findElement(By.name("ayear")).clear();
    wb.findElement(By.name("ayear")).sendKeys(addressBookRecord.getAyear());
    new Select(wb.findElement(By.name("new_group"))).selectByVisibleText(addressBookRecord.getGroupName());
    wb.findElement(By.name("address2")).clear();
    wb.findElement(By.name("address2")).sendKeys(addressBookRecord.getAddress2());
    wb.findElement(By.name("phone2")).clear();
    wb.findElement(By.name("phone2")).sendKeys(addressBookRecord.getHome1());
    wb.findElement(By.name("notes")).clear();
    wb.findElement(By.name("notes")).sendKeys(addressBookRecord.getNotes());
  }

  private void initContactAddressRecord() {
    wb.findElement(By.linkText("add new")).click();
  }

  private void gotoHome() {
    wb.findElement(By.linkText("home")).click();
  }

  private void login(String username, String password) {
    wb.findElement(By.name("user")).sendKeys(username);
    wb.findElement(By.name("pass")).sendKeys(password);
    wb.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wb.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wb.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wb.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
