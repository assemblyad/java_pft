package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecords;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressBookCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactAddressBookRecordsFromXml() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/ContactAddressBookRecords.xml")));
    String xml ="";
    String line = reader.readLine();
    while (line!=null){
//      String[] split = line.split(";"); // for csv
//      list.add(new Object[] {new ContactAddressBookRecordData().withFirstName(split[0]).withLastName(split[1])}); //for csv
      xml +=line;
      line = reader.readLine();
    }

    XStream xStream = new XStream();
    xStream.processAnnotations(ContactAddressBookRecordData.class);
    List<ContactAddressBookRecordData> contacts
            = (List<ContactAddressBookRecordData>) xStream.fromXML(xml);
    return contacts.stream().map((c->new Object[]{c})).collect(Collectors.toList()).iterator();
/*
    list.add(new Object[] {new ContactAddressBookRecordData().withFirstName("Alex1").withLastName("Demi1")});
    list.add(new Object[] {new ContactAddressBookRecordData().withFirstName("Alex2'").withLastName("Demi2'")});
    list.add(new Object[] {new ContactAddressBookRecordData().withFirstName("Alex3").withLastName("Demi3")});
 */
//    return list.iterator();
  }

  @Test(enabled = true, dataProvider = "validContactAddressBookRecordsFromXml")
  public void testContactAddressBookCreation(ContactAddressBookRecordData contact) throws Exception {
    app.contact().gotoHome();
    ContactAddressBookRecords before = app.contact().all();
//    File photo= new File("src/test/resources/stru.png"); //Лекция 6.1. Пути к файлам и директориям
/*
    ContactAddressBookRecordData contact = new ContactAddressBookRecordData()
            .withFirstName("First_name_03").withMiddleName("Middle_name").withLastName("Last_name_03").withNickname("Nickname")
            .withTitle("Title").withCompany("Company").withAddress("Address").withHomePhone("").withMobilePhone("222")
            .withWorkPhone("111").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3")
            .withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home")
            .withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April")
            .withAyear("1980").withPhoto(photo);
 */
    app.contact().create(contact,true);
    assertThat(app.contact().count(), equalTo(before.size()+1));
    ContactAddressBookRecords after = app.contact().all();
//    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
//    before.add(contact);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }
  @Test(enabled = false)
  public void testContactBadAddressBookCreation() throws Exception {
    app.contact().gotoHome();
    ContactAddressBookRecords before = app.contact().all();
    ContactAddressBookRecordData contactAddressBookRecordData = new ContactAddressBookRecordData().withFirstName("First_name_03'").withMiddleName("Middle_name").withLastName("Last_name_03").withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address").withHomePhone("Home").withMobilePhone("Mobile").withWorkPhone("Work").withFax("Fax").withEmail("E-mail").withEmail2("E-mail2").withEmail3("E-mail3").withHomepage("Homepage").withGroupName("Group name").withAddress2("Greenwood Village").withHome1("Home").withNotes("Notes").withBday("5").withBmonth("April").withByear("1975").withAday("5").withAmonth("April").withAyear("1980");
    app.contact().create(contactAddressBookRecordData,true);
    assertThat(app.contact().count(), equalTo(before.size())); //Лекция 5.8. Хеширование и предварительные проверки
    ContactAddressBookRecords after = app.contact().all();
//    contactAddressBookRecordData.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
//    before.add(contactAddressBookRecordData);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    assertThat(after, equalTo(before));
  }

  @Test(enabled = false)
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo= new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsoluteFile());
    System.out.println(photo.exists());
  }

}
