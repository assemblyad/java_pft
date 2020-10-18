package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class RegistrationHelper extends HelperBase{

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start (String username, String email){
      wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
      type(By.name("username"),username);
      type(By.name("email"),email);
      click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("realname"),"Me");
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.xpath("//span[text()='Update User']"));
  }
}
