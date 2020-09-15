package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List <GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("Group name 1", "Header", "Footer");
    app.getGroupHelper().createGroup(group);
    List <GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size()+1);
/*
    int max=0;
    for (GroupData g: after) {
          if (g.getId()>max) {
            max=(g.getId());
          }
    }

*/
    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupData> byId= (Comparator<GroupData>) (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
//    Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));
    Assert.assertEquals(before,after);
  }

}
