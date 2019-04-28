package com.assure.testutil;


import java.util.List;
import java.util.Map;

import com.assure.utils.ApiUtils;
import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
//import com.jayway.jsonpath.JsonPath
import com.jayway.restassured.response.Response;

import org.apache.log4j.Logger;
import org.testng.Assert;


/**
 * Created by neodevan
 */
public class TestUtils {

	
	private static final Logger log = Logger.getLogger(TestUtils.class);
	
	public void checkStatusIs200(Response res) {
		Assert.assertEquals(res.getStatusCode(), 200, "Status Check Failed!");
	}
	
	public void verifyAPIDataByRootNodes( String key , Response response, Object expectedData){
	      Assert.assertEquals(JsonPath.parse(ApiUtils.getJsonPath(response)).read("$['"+key+"']"), expectedData);
	}
	
	public void verifyAPIDataByFilterCriteria(String criteriaKey, Object criteraValue, String parentNode, Response response, String expectedKey, Object expectedValue){
		  String ActualData = filterJsonData(criteriaKey, criteraValue, parentNode, response, expectedKey).toString();
	      Assert.assertEquals(ActualData.contains((CharSequence) expectedValue), true);
	}
	
	public Object filterJsonData(String criteriaKey, Object criteraValue, String parentNode, Response response, String expectedKey){
		Filter criteriaFilter = Filter.filter(Criteria.where(criteriaKey).eq(criteraValue));
		List<Map<String, Object>> filteredData = JsonPath.parse(ApiUtils.getJsonPath(response)).read("$['"+parentNode+"'][?]", criteriaFilter);
		for (Map<String, Object> m : filteredData) {
			if(m.containsKey(expectedKey)){
			    return m.get(expectedKey);
			}
		}
		return null;
	}
	

	

	

}
