package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.HttpSessionId;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.MatchResult;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver wd;
  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private JamesHelper jamesHelper;
  private DbHelper dbhelper;
  private UserHelper userHelper;
  private SessionHelper sessionHelper;
  private SoapHelper soapHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {

    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));
  }

  public void stop() {
    if(wd!=null){
    wd.quit();
    }
  }

  public HttpSession newSession(){
    return new HttpSession(this);
  }

  public FtpHelper ftp(){
    if(ftp==null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail(){
    if(mailHelper == null){
      mailHelper= new MailHelper(this);
    }
    return mailHelper;
  }

  public String getProperty(String key){
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public JamesHelper james(){
    if (jamesHelper == null) {
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }

  public UserHelper user() {
    if (userHelper == null) {
      userHelper = new UserHelper(this);
    }
    return userHelper;
  }

  public SessionHelper session(){
    if (sessionHelper == null) {
      sessionHelper = new SessionHelper(this);
    }
    return sessionHelper;
  }

  public DbHelper db(){
    if (dbhelper == null){
      dbhelper = new DbHelper();
    }
    return dbhelper;
  }

  public SoapHelper soap(){
    if (soapHelper == null){
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
  }


  public WebDriver getDriver() {
    if(wd==null){
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else  if(browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if(browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }


      /*wd = new ChromeDriver(); */
//      driver = new ChromeDriver();
      wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//    wd.get("http://localhost/addressbook/");
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }

}
