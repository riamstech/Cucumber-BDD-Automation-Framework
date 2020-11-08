package com.cucumberFramework.pageObjects;

import com.cucumberFramework.helper.LoggerHelper;
import com.cucumberFramework.helper.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ToDoMVCPage {

	private WebDriver driver;
	WaitHelper waitHelper;
	Actions actions;
	Logger logger = LoggerHelper.getLogger(ToDoMVCPage.class);

	@FindBy(className = "new-todo")
	WebElement edtNewToDo;

	@FindBy(className = "clear-completed")
	WebElement btnClearCompleted;

	@FindBy(xpath = "//a[text()='All']")
	WebElement allItemTab;

	@FindBy(xpath = "//a[text()='Active']")
	WebElement activeItemTab;

	@FindBy(xpath = "//a[text()='Completed']")
	WebElement completedItemTab;

	@FindBy(className = "destroy")
	public List<WebElement> deleteTodo;

	@FindBy(className = "toggle")
	public List<WebElement> completeTodo;

	@FindBy(xpath = "//div[@class='view']/label")
	public List<WebElement> itemList;

	@FindBy(xpath = "//li[@class='todo']")
	public List<WebElement> activeTodoItems;

	@FindBy(xpath = "//li[@class='todo completed']")
	public List<WebElement> completedTodoItems;

	public ToDoMVCPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		actions = new Actions(driver);
	}

	public void addToDoItems(int itemCount){
		for (int i=0; i<itemCount; i++) edtNewToDo.sendKeys("Todo item-" + i + Keys.ENTER);
		logger.info("Item Added: " + itemCount);
	}

	public void deleteToDoItems(int itemCount){
		for (int i=0; i<itemCount; i++) {
			actions.moveToElement(itemList.get(i)).build().perform();
			deleteTodo.get(i).click();
		}
		logger.info("Item Deleted: " + itemCount);
	}

	public void completeToDoItems(int itemCount){
		for (int i=0; i<itemCount; i++) {
			actions.moveToElement(itemList.get(i)).build().perform();
			completeTodo.get(i).click();
		}
		logger.info("Item Completed: " + itemCount);
	}

	public void clickClearCompleted(){
		btnClearCompleted.click();
	}

	public void clickOnTab(String tabName){
		switch (tabName.toLowerCase()){
			case "all":
				allItemTab.click();
				break;

			case "active":
				activeItemTab.click();
				break;

			case "completed":
				completedItemTab.click();
				break;
		}
	}

	public int countTodoItems(String todoType){
		switch (todoType.toLowerCase()) {
			case "active":
				return activeTodoItems.size();
			case "completed":
				return completedTodoItems.size();
			default:
				return 0;
		}
	}
}
