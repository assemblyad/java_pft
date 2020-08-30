package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;
  private ContactAddressBookRecordHelper contactAddressBookRecordHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;

  public void init() {
    wd = new FirefoxDriver();
    /*wd = new ChromeDriver(); */
//      driver = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    contactAddressBookRecordHelper = new ContactAddressBookRecordHelper(wd);
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }


  public void returnToGroupPage() {
    wd.findElement(By.linkText("Logout")).click();
  }

  public void stop() {
    sessionHelper.logout();
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  /*
      @BeforeMethod(alwaysRun = true)
      public void setUp() throws Exception {
        wd = new FirefoxDriver();
    //    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        login("admin", "secret");
      }
    */

/*
  private void login(String username, String password) {
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }
*/

  public ContactAddressBookRecordHelper getContactAddressBookRecordHelper() {
    return contactAddressBookRecordHelper;
  }
}
