package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    //please check here validation for group presence
    if(app.group().list().size()==0){
      app.group().create(new GroupData("Group name", "Header", "Footer"));
    }
  }

  @Test(enabled = true)
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.group().list();
    int index =before.size()-1;
    app.group().delete(index);
    app.group().returnGroupCreation();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(index);
    Assert.assertEquals(before,after);
/*
    for (int i=0;i<after.size();i++){
      Assert.assertEquals(before.get(i),after.get(i));
    }
*/
  }


}
