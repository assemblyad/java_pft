package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{


  @Test(enabled = false)
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {

      Set<Project> projects =app.soap().getProjects();

    System.out.println(projects.size());
    for (Project project : projects){
      System.out.println(project.getName());
    }

  }



  @Test(enabled = false)
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects =app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created =app.soap().addIssue(issue);
    assertEquals(issue.getSummary(),created.getSummary());
  }

  @Test(enabled = true)
  public void executeTest() throws RemoteException, MalformedURLException, ServiceException {
    skipIfNotFixed(1);
    System.out.println("Test executed and issue # fixed :" +1);
    //skipIfNotFixed(1);

  }
}
