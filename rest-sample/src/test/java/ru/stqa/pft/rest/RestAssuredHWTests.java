package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredHWTests {

  @BeforeClass
  public void init(){
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
  }


  @Test(enabled = false)
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test me now issue 1234").withDescription("New test me now Issue #5 ");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }
  @Test(enabled = true)
  public void testTestExecutionWithResolvedIssue(){
    skipIfNotFixed(1);
    System.out.println("Test executed and issue # fixed :" + 1);
  }
  @Test(enabled = true)
  public void testTestExecutionWithNoTResolvedIssue(){
    skipIfNotFixed(345);
    System.out.println("Test executed and issue # fixed :" + 345);
  }
  //

  private Set<Issue> getIssues() throws IOException {

    String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json?limit=4000").asString();

    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues,new TypeToken<Set<Issue>>() {
    }.getType());

  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("2d81ba95abcbc53e43e945dcb4d48e77","");
  }

  private int createIssue(Issue newIssue) throws IOException {

    String json = RestAssured.given()
            .parameter("subject",newIssue.getSubject())
            .parameter("description",newIssue.getDescription())
            .post("https://bugify.stqa.ru/api/issues.json").asString();

    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }


  boolean isIssueOpen(int issueId)  {

    String json = RestAssured.get("https://bugify.stqa.ru/api/issues/"+issueId+".json").asString();

    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issue = parsed.getAsJsonObject().get("issues");
    JsonArray asJsonArray =issue.getAsJsonArray();
    String state_name = asJsonArray.get(0).getAsJsonObject().get("state_name").getAsString();

    if (state_name.equals("Resolved")){
      return false;
    } else{
/*
      Set<Map.Entry<String, JsonElement>> entries = asJsonArray.get(0).getAsJsonObject().entrySet();//will return members of your object
      for (Map.Entry<String, JsonElement> entry: entries) {
        if(entry.getKey().toString().equals("state_name")){
          System.out.println(entry.getKey());
          System.out.println(entry.getValue());
        }
      }
*/
    return  true;
    }


  }

  public void skipIfNotFixed(int issueId)  {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
