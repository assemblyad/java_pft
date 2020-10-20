package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserName;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{


  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }


  @Test
  public void testPasswordReset() throws MessagingException, IOException {
    UserName userNameCandidateForReset;
    String password = "password";

    List<UserName> userNames = app.db().userNames();
    if(app.db().userNames().size()==1) {
      app.user().createUser();
      app.user().registerUser();;
    }
    userNameCandidateForReset = userNames.stream().filter((c)->c.getId()!=1).findFirst().get();

    app.getDriver();
    app.session().login("administrator","root");
    app.user().inviteUser();
    app.user().manageUsers();
    app.user().manageUser(userNameCandidateForReset.getId());
    app.user().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1,10000);
    String confirmationLink =app.user().findConfirmationLink(mailMessages,userNameCandidateForReset.getEmail());
    app.registration().finish(confirmationLink, "root");
    //Затем тесты должны проверить, что пользователь может войти в систему с новым паролем.
    HttpSession session = app.newSession();
    assertTrue(session.login(userNameCandidateForReset.getUserName(),"root"));
    assertTrue(session.isLoggedInAs(userNameCandidateForReset.getUserName()));

  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }


}
