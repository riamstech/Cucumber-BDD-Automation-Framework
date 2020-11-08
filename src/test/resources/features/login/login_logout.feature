Feature: As a Amazon user I should be able to login and logout with valid credentials

  Scenario: Addition & Deletion in ToDo List
    Given User on http://todomvc.com/examples/vue/ page
    When user add 3 todo item
    And user delete 2 todo item
    Then user validate 1 todo item and 0 completed item should available in All tab
    And user validate 1 todo item and 0 completed item should available in Active tab
    And user validate 0 todo item and 0 completed item should available in Completed tab

  Scenario: Completion of ToDo items
    Given User on http://todomvc.com/examples/vue/ page
    When user add 3 todo item
    And user complete 2 todo item
    Then user validate 1 todo item and 2 completed item should available in All tab
    And user validate 1 todo item and 0 completed item should available in Active tab
    And user validate 0 todo item and 2 completed item should available in Completed tab

  Scenario: Clear completed ToDo items
    Given User on http://todomvc.com/examples/vue/ page
    When user add 3 todo item
    And user complete 2 todo item
    And user clicks Clear completed button
    Then user validate 1 todo item and 0 completed item should available in All tab
    And user validate 1 todo item and 0 completed item should available in Active tab
    And user validate 0 todo item and 0 completed item should available in Completed tab
  
   
