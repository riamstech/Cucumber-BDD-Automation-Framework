package com.cucumberFramework.stepdefinitions;

import com.cucumberFramework.helper.LoggerHelper;
import com.cucumberFramework.helper.WaitHelper;
import com.cucumberFramework.pageObjects.ToDoMVCPage;
import com.cucumberFramework.testBase.TestBase;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;


public class ToDoMVCSteps extends TestBase {

	Logger logger = LoggerHelper.getLogger(ToDoMVCSteps.class);
	ToDoMVCPage toDoMVCPage = new ToDoMVCPage(driver);
	WaitHelper waitHelper = new WaitHelper(driver);

	@Given("^User on (.+) page$")
	public void userOnTodoMVCPage(String url) {
		driver.get(url);
	}

	@When("^user add (\\d+) todo item$")
	public void userAddTodoItem(int arg0) {
		toDoMVCPage.addToDoItems(arg0);
	}

	@And("^user delete (\\d+) todo item$")
	public void userDeleteTodoItem(int arg0) {
		toDoMVCPage.deleteToDoItems(arg0);
	}

	@And("^user complete (\\d+) todo item$")
	public void userCompleteTodoItem(int arg0) {
		toDoMVCPage.completeToDoItems(arg0);
	}

	@And("^user clicks Clear completed button$")
	public void userClicksClearCompletedButton() {
		toDoMVCPage.clickClearCompleted();
	}

	@Then("^user validate (\\d+) todo item and (\\d+) completed item should available in (.+) tab$")
	public void userValidateTodoItems(int todoItem, int completedItem, String tabName) {
		// Tab Navigation
		toDoMVCPage.clickOnTab(tabName);

		SoftAssert softAssert = new SoftAssert();
		// active count
		int activeTodoCount = toDoMVCPage.countTodoItems("active");
		softAssert.assertEquals(activeTodoCount, todoItem,"Active Todo count not matched");

		// completed count
		int completedTodoCount = toDoMVCPage.countTodoItems("completed");
		softAssert.assertEquals(completedTodoCount, completedItem, "Completed Todo count not matched");

		softAssert.assertAll();
	}
}