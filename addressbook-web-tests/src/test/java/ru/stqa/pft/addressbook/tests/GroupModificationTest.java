package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTest extends TestBase  {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    //please check here validation for Group presence
    if(app.group().all().size()==0){
      app.group().create(new GroupData().withName("Group name").withHeader("Header").withFooter("Footer"));
    }
  }

  @Test(enabled = false)
  public void testGroupModification () {

    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("Group name").withHeader("header1").withFooter("footer");
    app.group().modify(group);
    Set <GroupData> after = app.group().all();
    Assert.assertEquals(after.size(),before.size());


    before.remove(modifiedGroup);
    before.add(group);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before,after);
  }

}
