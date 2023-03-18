# Java project on JDBC (Java Database connectivity)

1.	Write a program (in java or any other language) to access and manage the contents of database tables. Follow the following steps to set your computer for the assignment and accomplish the goal.

a.	Download the University database from the class textbook website and load it to your local MySQL database server.
    https://www.db-book.com/university-lab-dir/sample_tables-dir/DDL.sql
b.	Load the simple data set.
    https://www.db-book.com/university-lab-dir/sample_tables-dir/smallRelations/smallRelationsInsertFile.sql
c.	Download the database driver (mysql-connector.java for java language) set it up with your system
    https://dev.mysql.com/downloads/connector/j/

d.	Create a  program to display the content of Instructor, advisor, and student tables. 
      i.	Program should ask the table name to display when selecting the display data option from the main menu. Then display the content of the selected table. Student must not hard code the table name

e.	Add the functionality to insert record to the Student table. 
      i.	User should be able to select the option to insert data from the main menu
      ii.	User should be asked to enter each column data one after the other and once the final column data is inserted, it should insert the data to the student table.

f.	Add a main menu item to modify the advisor of a given student
      i.	User should be able to select the modify advisor menu option from the main menu.
      ii.	Program should display the current advisor of the student. NULL will be displayed if the student does not have an advisor yet.
      iii.	Program should display the available instructors in a list and all the students in the student table. User will select Student and the Instructor that will create the advisor relation between the student and the instructor.

g.	Add a main menu item to delete a student from the student table
      i.	User should be able to select the delete student menu option from the main menu.
      ii.	The user is presented to enter the student ID and the program displays the information of that student. Then the program asks the user “Are you sure?”

1.	If the user selects yes-> delete that student from the student table. It must take care of the advisor relationship accordingly.
2.	If the user selects no -> return to main menu

Note: You can implement this assignment as a console application. If you are comfortable of using GUIs, feel free to do so.
