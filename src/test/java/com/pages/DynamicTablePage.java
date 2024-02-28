package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DynamicTablePage {

	
	@FindBy(xpath="//summary[text()='Table Data']")
	public WebElement tableDataButton;
	
	@FindBy(id="jsondata")
	public WebElement jsonTextArea;
	
	@FindBy(id="refreshtable")
	public WebElement refreshTableButton;
	
	@FindBy(xpath="//table[@id='dynamictable']/tr/td[1]")
	public List<WebElement> listOfGenders;
	
	@FindBy(xpath="//table[@id='dynamictable']/tr/td[2]")
	public List<WebElement> listOfNames;
	
	@FindBy(xpath="//table[@id='dynamictable']/tr/td[3]")
	public List<WebElement> listOfAges;
	
	
}
