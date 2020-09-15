package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase  {

  @Test
  public void testGroupModification () {
    app.getNavigationHelper().gotoGroupPage();
    //please check here validation for Group presence
    if(!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("Group name", "Header", "Footer"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get((before.size()-1)).getId(),"Group name", "header1", "footer");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnGroupCreation();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());


    before.remove(before.size()-1);
    before.add(group);

    Comparator<? super GroupData> byId= (Comparator<GroupData>) (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before,after);
  }
}
