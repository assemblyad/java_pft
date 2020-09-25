package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().gotoHome();
    if(app.contact().all().size()==0){
      app.contact().create(new ContactAddressBookRecordData().withFirstName("First name").withMiddleName("Middle_name").withLastName("Last_name").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHomePhone("Home").withMobilePhone("Mobile").withWorkPhone("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980"),true);
    }
  }


  @Test(enabled = true)
  public void testContactPhones() {
    app.goTo().gotoHomePage();
    ContactAddressBookRecordData contact = app.contact().all().iterator().next();
    ContactAddressBookRecordData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
  }

  public String cleaned(String phone) {

    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
