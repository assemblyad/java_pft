package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTest extends TestBase  {

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().groups().size()==0){

    app.goTo().groupPage();
    app.group().create(new GroupData().withName("Group name").withHeader("Header").withFooter("Footer"));
    //please check here validation for Group presence
//    if(app.group().all().size()==0){
//      app.group().create(new GroupData().withName("Group name").withHeader("Header").withFooter("Footer"));
//    }
    }
  }

  @Test(enabled = true)
  public void testGroupModification () {

//    Groups before = app.group().all();
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("Group name").withHeader("header1").withFooter("footer");
    app.goTo().groupPage();
    app.group().modify(group);
//    Groups after = app.group().all();
    Groups after = app.db().groups();
    Assert.assertEquals(after.size(),before.size());
    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }

}
