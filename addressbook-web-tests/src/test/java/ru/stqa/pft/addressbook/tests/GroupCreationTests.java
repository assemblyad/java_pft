package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
//    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      // since java 7 files closes automatically using try and close with resources option only
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
//      String [] split = line.split(";");
//      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g})
              .collect(Collectors.toList()).iterator();
//    list.add(new Object[] {new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
//    list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
//    list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
//    return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
//    List<Object[]> list = new ArrayList<Object[]>();
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))){
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }

    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
    }.getType()); //List<Groupdata>.class
    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }


  @Test(enabled = true, dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups before = app.db().groups();
//    Groups before = app.group().all();
//    GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
    app.group().create(group);
//    assertThat(app.group().count(), equalTo(before.size() + 1));
    assertThat(app.db().groups().size(), equalTo(before.size() + 1));
    Groups after = app.db().groups();
//    Groups after = app.group().all();
//    assertThat(after.size(),equalTo(before.size()+1));
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyGroupListInUI();
  }

  @Test(enabled = true)
  public void testBadCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.db().groups();
//    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Group name 1'").withHeader("Header").withFooter("Footer");
    app.group().create(group);
//    assertThat(app.group().count(), equalTo(before.size()));
    assertThat(app.db().groups().size(), equalTo(before.size()));
    Groups after = app.db().groups();
//    Groups after = app.group().all();
    assertThat(after, equalTo(
            before));
    verifyGroupListInUI();
  }
}
