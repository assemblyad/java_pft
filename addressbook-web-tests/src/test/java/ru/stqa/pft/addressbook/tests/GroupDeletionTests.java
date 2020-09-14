package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    //please check here validation for group presence
    if(!app.getGroupHelper().isThereAGroup()){
        app.getGroupHelper().createGroup(new GroupData("Group name", "Header", "Footer"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(before.size()-1);
    Assert.assertEquals(before,after);
/*
    for (int i=0;i<after.size();i++){
      Assert.assertEquals(before.get(i),after.get(i));
    }
*/
  }
}
