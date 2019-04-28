package com.assure.test;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.assure.testutil.TestUtils;
import com.assure.utils.ApiUtils;
import com.assure.utils.TestDataReader;
import com.jayway.restassured.response.Response;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

/**
 * Created by Vipin Alias Neo De Van
 */
public class BaseTest {

	protected Logger log=Logger.getLogger(BaseTest.class);
    public Response res = null;
    TestUtils testUtils = new TestUtils();
    protected static Response response;

    @BeforeTest
    public void setup (){
        ApiUtils.setBaseURI();
        response= ApiUtils.getResponseBody();
    }

    @AfterTest
    public void afterTest (){
        ApiUtils.resetBaseURI();
        ApiUtils.resetBasePath();
    }
    
    @DataProvider(name="TestData") 
    public Object[][] getData(Method method)
    {
        log.info("Reading test data for : " +method.getName());
        String currentTestClass=method.getDeclaringClass().toString();
        currentTestClass=currentTestClass.substring(currentTestClass.lastIndexOf(".")+1, currentTestClass.length());
        return TestDataReader.getDataFromExcelFile(currentTestClass,method.getName());
    }
    

}
