package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

public class UserHelper extends HelperBase {


  public UserHelper(ApplicationManager app) {
    super(app);
  }

  long now = System.currentTimeMillis();
  String email = String.format("user%s@localhost.localdomain",now);
  String user = String.format("user%s",now);
  String password = "password";


  public void createUser(){
    app.james().createUser(user, password);
  }

  public void registerUser(){
    app.registration().start(user, email);
  }

  public void inviteUser(){
    click(By.cssSelector("a[href='manage_user_create_page.php']"));
  }

  public void manageUsers(){
    //href="manage_user_create_page.php"
    click(By.xpath("//a[text()='Manage Users']"));
  }

  public void resetPassword(){

    click(By.cssSelector("input[value='Reset Password']"));
  //<a href="manage_user_edit_page.php?user_id=42">user1603121148342</a>
  }

  public void manageUser(int id) {
//    Reset Password
    click(By.xpath("//a[@href='manage_user_edit_page.php?user_id="+id+"']"));
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  /*




  //List<MailMessage> mailMessages = app.mail().waitForMail(2,10000);
  List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
  String confirmationLink =findConfirmationLink(mailMessages,email);

    app.registration().finish(confirmationLink, password);

 */

}
