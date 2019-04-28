package com.assure.test;

import org.testng.annotations.*;



/**
 * Created by Vipin Alias Neo De Van
 */
public class AssureAcceptanceTest extends BaseTest{


    
	@Test(dataProvider="TestData")
    public void verifyNameAcceptanceTest(String keyNode, String ExpectedData) {
        testUtils.verifyAPIDataByRootNodes(keyNode,response,ExpectedData);
    }
    
	@Test(dataProvider="TestData")
    public void verifyCanRelistAcceptanceTest(String keyNode, String ExpectedData) {
		Boolean expectedBoolean = Boolean.parseBoolean(ExpectedData);
    	testUtils.verifyAPIDataByRootNodes(keyNode,response,expectedBoolean);
    }
    
	@Test(dataProvider="TestData")
    public void verifyGalleryDescriptionAcceptanceTest(String criteriaKey, String criteriaValue, String ParentNode, String ExpectedKey, String ExpectedValue) {
        testUtils.verifyAPIDataByFilterCriteria(criteriaKey, criteriaValue, ParentNode, response, ExpectedKey, ExpectedValue);
    }
    

    
}
