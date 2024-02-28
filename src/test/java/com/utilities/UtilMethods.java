package com.utilities;

import java.io.FileReader;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilMethods {

	public static Object getJsonData(String path) throws Exception {
		JSONParser jsonParser= new JSONParser();
		FileReader reader= new FileReader(path);
		Object jsonObject= jsonParser.parse(reader);
		return jsonObject;
	}
	
	public static void waitForElement(WebElement element, String elementName, WebDriverWait wait) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			
		}
		catch(Exception e) {
			System.out.println(elementName + " element is not loaded in the web page");
			e.printStackTrace();
		}
	}
	public static void waitForElements(List<WebElement> elements, String elementName, WebDriverWait wait) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			
		}
		catch(Exception e) {
			System.out.println(elementName + " elements are not loaded in the web page");
			e.printStackTrace();
		}
	}
	
	public static void clickOn(WebElement element, String elementName, WebDriverWait wait) {
		try {
			waitForElement(element, elementName, wait);
			element.click();
			System.out.println("Clicked on "+elementName);
		}
		catch(Exception e) {
			System.out.println("Failed to click on "+elementName);
			e.printStackTrace();
		}
	}
	public static void enterText(String text, WebElement element, String elementName, WebDriverWait wait) {
		try {
			waitForElement(element, elementName, wait);
			element.sendKeys(text);
			System.out.println("Entered the text: "+text);
		}
		catch(Exception e) {
			System.out.println("Failed to enter the text: "+text);
			e.printStackTrace();

		}
	}
}
