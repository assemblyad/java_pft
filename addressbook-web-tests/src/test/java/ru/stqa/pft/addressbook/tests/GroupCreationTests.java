package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test(enabled = true)
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    List <GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("Group name 1").withHeader("Header").withFooter("Footer");
    app.group().create(group);
    List <GroupData> after = app.group().list();
    Assert.assertEquals(after.size(),before.size()+1);
/*
    int max=0;
    for (GroupData g: after) {
          if (g.getId()>max) {
            max=(g.getId());
          }
    }
*/
    group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupData> byId= (Comparator<GroupData>) (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
//    Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));
    Assert.assertEquals(before,after);
  }

}
