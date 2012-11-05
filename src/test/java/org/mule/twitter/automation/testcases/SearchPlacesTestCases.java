package org.mule.twitter.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.twitter.automation.TestPlace;

import twitter4j.Place;
import twitter4j.ResponseList;

public class SearchPlacesTestCases extends TwitterTestCase {
    
    @Category({SanityTests.class, RegressionTests.class})
	@Test
	public void testSearchPlacesByIp() {
		
    	TestPlace place = (TestPlace) context.getBean("placeByIp");
    	
		try {

			flow = lookupFlowConstruct("search-places-by-ip");
			response = flow.process(getTestEvent(place.getIp()));
        	ResponseList<Place> placesList = (ResponseList<Place>) response.getMessage().getPayload();
        	
        	assertNotNull(placesList);
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			fail();
		}
	}
    
    @Category({RegressionTests.class})
	@Test
	public void testSearchPlacesByCoordinates() {
    	
    	TestPlace place = (TestPlace) context.getBean("placeByCoordinates");
		
		try {
			
			Map<String,Object> operationParams = new HashMap<String,Object>();
			operationParams.put("latitude", place.getLatitude());
			operationParams.put("longitude", place.getLongitude());
			
			flow = lookupFlowConstruct("search-places-by-coordinates");
        	response = flow.process(getTestEvent(operationParams));
        	ResponseList<Place> placesList = (ResponseList<Place>) response.getMessage().getPayload();
        	
        	assertNotNull(placesList);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}  
     
	}
    

}
