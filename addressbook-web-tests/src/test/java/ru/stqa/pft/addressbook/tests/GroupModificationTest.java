package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase  {

  @Test
  public void testGroupModification () {
    app.getNavigationHelper().gotoGroupPage();
    //please check here validation for Group presence
    if(!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("Group name", "Header", "Footer"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("Group name", "header1", "footer"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnGroupCreation();
  }
}
