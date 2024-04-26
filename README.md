# SC2002-FOMS-App-Project

## Introduction
This is a project for the NTU module SC2002 Object Oriented Design & Programming for 2024 S2. 

It involves creating a Food Ordering and Management System (FOMS) through the use of Object Oriented Programming conforming to the SOLID design principles.

Documentation of the code can be found in the [Javadoc folder]().
Open the `index.html` file after downloading to view an interactive Javadoc HTML webpage.

An [UML class diagram](https://github.com/lycheeyd/SC2002-Object-Orientated-Programming-Project/blob/afb4b06fbba1bc33f94121bdc5f4906b45a0493d/UML%20Class%20Diagram.jpg) is available for reference.
 
The [SC2002 Assignment 2024S2 PDF file]() consists of the full requirements for the project.

Java is the language of choice for this project.

## Contributors
| Name                   | Username                                     | Course  |
|------------------------|----------------------------------------------|---------|
| Michael Yip Hoi Weng             | [@TinkyBala](https://github.com/Tinkybala)  | DSAI    |
| Muhammad Aidil Firdaus           | [@Aidilil](https://github.com/Aidilil)       | DSAI    |
| Nathan Pua Jun Shen              | [@nathanpua](https://github.com/nathanpua)   | DSAI    |
| Tay Kai Wen                      | [@newiakyat](https://github.com/newiakyat)   | DSAI    |
| lycheeyd (Name Hidden on GitHub) | [@lycheeyd](https://github.com/lycheeyd)     | BS      |


## Preconditions
Before using the application, note that 3 `.csv` files are required to be in the same working directory as your `.jar` or `.class` files after compilation. See [Usage](#Usage) for details of compilation.

1. There is only one user accessing the system at a given time
2. We expect users to remove or redirect staff and menu items out of the closed branch as the compulsory and minimum requirement of this application. Closing a branch without removing its staff and menu items will result in boot up error when the Application is next-run.


Refer to the table for the required files:
| File | Description | Data Types |
| - | - | - |
| branch_list.csv | This file consists of all the branches in the system. | Name, location and Staff Quota.|
| menu_list.csv | This file consists of all the menu list for each branches in the system.  | Name, Price, Branch and Category.|
| staff_list.csv| This file consists of all the staff in the system. | Name, Staff Login ID, Role, Gender, Age, Branch and Default Password.|



## Usage
Please see [preconditions](#Preconditions) prior to running the application to avoid issues.

The folder `FOMSApp` has been provided. It includes a `FOMSApp-with-dependencies.jar` and the 3 required `.csv` files.

You may directly navigate into the `FOMSApp` folder and execute the `java -jar FOMSApp-with-dependencies.jar` command to run the program.
OR
Choose to compile the source code yourself then run the program.
