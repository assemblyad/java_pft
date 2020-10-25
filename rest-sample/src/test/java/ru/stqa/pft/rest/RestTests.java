package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;


public class RestTests {


  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test me now issue").withDescription("New test me now Issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  private Set<Issue> getIssues() throws IOException {

    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json?limit=4000"))
            .returnContent().asString();


//    String json = RestAssured.get("http://demo.bugify.com/api/issues.json?limit=4000").asString();
    JsonElement parsed = new JsonParser().parse(json);
//    JsonObject jsonObject = JsonParser.parseString(myString).getAsJsonObject();
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues,new TypeToken<Set<Issue>>() {
    }.getType());

//    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
//            .returnContent().asString();
    //String json = Request.Get("http://demo.bugify.com/api/issues.json").execute().returnContent().asString();
    //2e499e19c8511e6e8f8d4c0e91af19f4
    //return null;
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("2d81ba95abcbc53e43e945dcb4d48e77","");
    //return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }

  private int createIssue(Issue newIssue) throws IOException {

    String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject",newIssue.getSubject())
                    ,new BasicNameValuePair("description",newIssue.getDescription())))
            .returnContent().asString();

    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

}
