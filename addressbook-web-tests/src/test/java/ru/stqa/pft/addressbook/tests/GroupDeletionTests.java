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

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    //please check here validation for group presence
//    if(app.group().all().size()==0){
    if(app.db().groups().size()==0){
      app.group().create(new GroupData().withName("Group name").withHeader("Header").withFooter("Footer"));
    }
  }

  @Test(enabled = true)
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    //    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);
//    app.group().returnGroupCreation();
    Groups after = app.db().groups();
//    Groups after = app.group().all();
    Assert.assertEquals(after.size(),before.size()-1);
    assertThat(after, equalTo(before.withOut(deletedGroup)));
//    Assert.assertEquals(before,after);
  }
}
