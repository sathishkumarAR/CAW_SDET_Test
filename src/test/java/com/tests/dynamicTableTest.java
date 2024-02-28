package com.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utilities.TestNGconfig;
import com.utilities.UtilMethods;

public class dynamicTableTest extends TestNGconfig{
	

	@Test
	public void tc01VerifyDynamicTable() throws Exception {
		
		//Initialize variables
		String expectedName,expectedGender,expectedAge, nameInTable, genderInTable, ageInTable;
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		//Get Data from JSON
		Object jsonData= UtilMethods.getJsonData(".//src/test/java/com/data/testData.json");
		JSONArray dataArray= (JSONArray)jsonData;
		
		
		//Load URL
		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		
		//Paste JSON data and refresh
		UtilMethods.clickOn(dynamicTablePage.tableDataButton, "Table Data button", wait);
		UtilMethods.clickOn(dynamicTablePage.jsonTextArea, "Text field", wait);
		dynamicTablePage.jsonTextArea.clear();
		
		UtilMethods.enterText(jsonData.toString(), dynamicTablePage.jsonTextArea, "Text field", wait);
		UtilMethods.clickOn(dynamicTablePage.refreshTableButton, "Refresh table button", wait);
		
		UtilMethods.waitForElements(dynamicTablePage.listOfAges, "Data in table", wait);
		
		//Data count verification
		if(dynamicTablePage.listOfAges.size()==dataArray.size()) {
			softAssert.assertTrue(true);
			System.out.println("Data count is matching!");
		}
		else {
			//used hard assert to avoid index out of bound exception in data verification loop
			Assert.assertTrue(false);
			System.out.println("Data count is not matching. Expected count: "+dataArray.size()+", Actual count in table: "+dynamicTablePage.listOfAges.size());
		}
		
		//Data Verification
		for(int i=0;i<dataArray.size();i++) {
			
			//Gender verification
			expectedGender= ((String) ((JSONObject)dataArray.get(i)).get("gender")).trim();
			genderInTable= dynamicTablePage.listOfGenders.get(i).getText().trim();
			
			if(expectedGender.equals(genderInTable)) {
				softAssert.assertTrue(true);
				System.out.println("Gender in row number "+(i+1)+" is matching with the input");
			}
			else {
				softAssert.assertTrue(false);
				System.out.println("Gender in row number "+(i+1)+" is not matching with the input value. Input Gender: "+expectedGender+", Gender in table: "+genderInTable);
			}
			
			//Name verification
			expectedName= ((String) ((JSONObject)dataArray.get(i)).get("name")).trim();
			nameInTable= dynamicTablePage.listOfNames.get(i).getText().trim();
			
			if(expectedName.equals(nameInTable)) {
				softAssert.assertTrue(true);
				System.out.println("Name in row number "+(i+1)+" is matching with the input");
			}
			else {
				softAssert.assertTrue(false);
				System.out.println("Name in row number "+(i+1)+" is not matching with the input value. Input name: "+expectedName+", Name in table: "+nameInTable);
			}
			
			//Age verification
			expectedAge= ((JSONObject)dataArray.get(i)).get("age").toString().trim();
			ageInTable= dynamicTablePage.listOfAges.get(i).getText().trim();
			
			if(expectedAge.equals(ageInTable)) {
				softAssert.assertTrue(true);
				System.out.println("Age in row number "+(i+1)+" is matching with the input");
			}
			else {
				softAssert.assertTrue(false);
				System.out.println("Age in row number "+(i+1)+" is not matching with the input value. Input Age: "+expectedAge+", Age in table: "+ageInTable);
			}
		}
		
		//Pausing the screen to see the final result
		Thread.sleep(5000);
		
	}
}
