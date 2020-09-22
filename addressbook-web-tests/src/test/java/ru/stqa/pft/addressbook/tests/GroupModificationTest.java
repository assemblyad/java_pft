package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase  {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    //please check here validation for Group presence
    if(app.group().list().size()==0){
      app.group().create(new GroupData("Group name", "Header", "Footer"));
    }
  }

  @Test(enabled = true)
  public void testGroupModification () {

    List<GroupData> before = app.group().list();
    int  index = before.size()-1;
    GroupData group = new GroupData(before.get(index).getId(),"Group name", "header1", "footer");

    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(),before.size());


    before.remove(index);
    before.add(group);

    Comparator<? super GroupData> byId= (Comparator<GroupData>) (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before,after);
  }

}
