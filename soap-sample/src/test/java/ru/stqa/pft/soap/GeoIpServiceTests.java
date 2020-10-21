package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GeoIpServiceTests {

  @Test
  public void testMyIp(){

   String geoIp  =new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("50.58.55.140");
   assertTrue(geoIp.contains("<Country>US</Country>"));
   //assertEquals(geoIp.indexOf("US")., "US");

  }

}
