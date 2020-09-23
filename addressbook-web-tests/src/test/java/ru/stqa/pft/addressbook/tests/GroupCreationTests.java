package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test(enabled = true)
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("Group name 1").withHeader("Header").withFooter("Footer");
    app.group().create(group);
    Set <GroupData> after = app.group().all();
    Assert.assertEquals(after.size(),before.size()+1);
    //group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt());
    before.add(group);
//    Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));
    Assert.assertEquals(before,after);
  }

}
