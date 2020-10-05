package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactAddressBookRecordData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactAddressBookRecordDataGenerator {

  @Parameter(names = "-c", description = "ContactAddressBookRecordData count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;


  public static void main(String[] args) throws IOException {
    ContactAddressBookRecordDataGenerator generator = new ContactAddressBookRecordDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();


//    int count = Integer.parseInt(args[0]);
//    File file = new File(args[1]);


  }

  private void run() throws IOException {
    List<ContactAddressBookRecordData> contactAddressBookRecords = generateContactAddressBookRecords(count);
    if (format.equals("csv")) {
      saveAsCsv(contactAddressBookRecords, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contactAddressBookRecords, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contactAddressBookRecords, new File(file));
    }
    {
      System.out.println("Unrecognized format " + format);
    }


  }

  private void saveAsJson(List<ContactAddressBookRecordData> contactAddressBookRecords, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contactAddressBookRecords);
    try (Writer writer = new FileWriter(file)) {


      writer.write(json);
      writer.close();
    }
    ;
  }

  private void saveAsXml(List<ContactAddressBookRecordData> contactAddressBookRecords, File file) throws IOException {
    XStream xstream = new XStream();
//    xstream.alias("ContactAddressBookRecordData",ContactAddressBookRecordData.class );
    xstream.processAnnotations(ContactAddressBookRecordData.class);
    String xml = xstream.toXML(contactAddressBookRecords);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
      writer.close();
    }
  }

  private void saveAsCsv(List<ContactAddressBookRecordData> contactAddressBookRecords, File file) throws IOException {
    System.out.println(new File(".").getAbsoluteFile());
    try (Writer writer = new FileWriter(file)) {
      for (ContactAddressBookRecordData contactAddressBookRecord : contactAddressBookRecords) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n"
                , contactAddressBookRecord.getFirstName()
                , contactAddressBookRecord.getLastName()
                , contactAddressBookRecord.getMiddleName()
                , contactAddressBookRecord.getLastName()
                , contactAddressBookRecord.getNickName()
                , contactAddressBookRecord.getTitle()
                , contactAddressBookRecord.getCompany()
                , contactAddressBookRecord.getAddress()
                , contactAddressBookRecord.getAllPhones()
                , contactAddressBookRecord.getHomePhone()
                , contactAddressBookRecord.getMobilePhone()
                , contactAddressBookRecord.getWorkPhone()
                , contactAddressBookRecord.getFax()
                , contactAddressBookRecord.getAllEmails()
                , contactAddressBookRecord.getEmail()
                , contactAddressBookRecord.getEmail2()
                , contactAddressBookRecord.getEmail3()
                , contactAddressBookRecord.getHomepage()
                , contactAddressBookRecord.getGroupName()
                , contactAddressBookRecord.getAddress2()
                , contactAddressBookRecord.getPhone2()
                , contactAddressBookRecord.getNotes()
                , contactAddressBookRecord.getBday()
                , contactAddressBookRecord.getBmonth()
                , contactAddressBookRecord.getByear()
                , contactAddressBookRecord.getAday()
                , contactAddressBookRecord.getAmonth()
                , contactAddressBookRecord.getAyear()));
      }
      writer.close();
    }
    ;
  }

  private List<ContactAddressBookRecordData> generateContactAddressBookRecords(int count) {
    List<ContactAddressBookRecordData> contactAddressBookRecords = new ArrayList<ContactAddressBookRecordData>();
    for (int i = 0; i < count; i++) {
      contactAddressBookRecords
              .add(new ContactAddressBookRecordData()
                      .withFirstName(String.format("First Name %s", i))
                      .withMiddleName(String.format("Middle Name %s", i))
                      .withLastName(String.format("Last Name %s", i))
                      .withNickname(String.format("Nickname %s", i))
                      .withTitle(String.format("Title %s", i))
                      .withCompany(String.format("Company %s", i))
                      .withAddress(String.format("Address %s", i))
                      .withHomePhone(String.format("Home %s", i))
                      .withMobilePhone(String.format("Mobile %s", i))
                      .withWorkPhone(String.format("Work %s", i))
                      .withFax(String.format("Fax %s", i))
                      .withEmail(String.format("gt%s@test1.com", i))
                      .withEmail2(String.format("gt%s@test2.com", i))
                      .withEmail3(String.format("gt%s@test3.com", i))
                      .withHomepage(String.format("Homepage %s", i))
                      .withAddress2(String.format("Address2 %s", i))
                      .withHome1(String.format("Home1 %s", i))
                      .withNotes(String.format("Notes %s", i))
                      .withBday(String.format("5"))
                      .withBmonth(String.format("April"))
                      .withByear(String.format("1975"))
                      .withAday(String.format("15"))
                      .withAmonth(String.format("April"))
                      .withAyear(String.format("1985")));
    }
    return contactAddressBookRecords;
  }
}