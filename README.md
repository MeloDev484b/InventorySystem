# InventorySystem
This project utilizes Java and JavaFX to create an inventory system application. 

### Scenario
>You are working for a small manufacturing organization that has outgrown its current inventory system. Members of the organization have been using a spreadsheet  
>program to manually enter inventory additions, deletions, and other data from a paper-based system but would now like you to develop a more sophisticated inventory  
>program.

>You have been provided with a mock-up of the user interface to use in the design and development of the system and a UML diagram to assist you in your work.  
>The organization also has specific business requirements that must be considered for the application. A systems analyst created the solution statements outlined  
>in the requirements section of this task based on the business requirements. You will use these solution statements to develop your application.

I began the project by creating the provided classes and their member methods. I used SceneBuilder to build fxml files and generate the methods for fxml 
objects like the buttons and TableViews. After I had the GUI set up, I worked to make the classes interact with eachother using the GUI. I used Inventory as 
my base of operations as it would hold both the list of Parts and list of Products in stock. I mistakenly began documentation closer to the end of development 
but I have learned that I should begin documentation before I begin coding, and continue to revise it as I work.

A portion of this project that I struggled with was the requirement to use specified functions. Originally, I was using an ObservableList wrapped in a FilteredList 
that was wrapped in a SortedList; It seemed to work beautifully. The assignment required me to use provided methods for looking up Parts and Products that returned 
either a Part or Product, or an ObservableList of Parts/Products. Unfortunately, the wrapped ObservableList made using those methods completely redundant because 
the FilteredList updated automatically, based on the predicates. Since I was required to use the provided methods, I had to scrap the better search method.
