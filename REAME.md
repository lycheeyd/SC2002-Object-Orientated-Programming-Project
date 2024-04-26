# SC2002-FOMS-App-Project

## Introduction
This is a project for the NTU module SC2002 Object Oriented Design & Programming for 2024 S2. 

It involves creating a Food Ordering and Management System (FOMS) through the use of Object Oriented Programming conforming to the SOLID design principles.

## Contributors
| Name                   | Username                                     | Course  |
|------------------------|----------------------------------------------|---------|
| Michael Yip Hoi Weng   | [@TinkyBala](https://github.com/Tinkybala)  | DSAI    |
| Muhammad Aidil Firdaus | [@Aidilil](https://github.com/Aidilil)       | DSAI    |
| Nathan Pua Jun Shen    | [@nathanpua](https://github.com/nathanpua)   | DSAI    |
| Tay Kai Wen            | [@newiakyat](https://github.com/newiakyat)   | DSAI    |
| Tan Yi Da              | [@lycheeyd](https://github.com/lycheeyd)     | BS      |


Documentation of the code can be found in the [Javadoc folder]().
Open the `index.html` file after downloading to view an interactive Javadoc HTML webpage.

An [UML class diagram]([https://github.com/weihonglwh/SC2002-CAM-App-Project/blob/main/class-diagram-final.jpg](https://github.com/lycheeyd/SC2002-Object-Orientated-Programming-Project/blob/main/UML.svg)) is available for reference.
 
The [SC2002 Assignment 2024S2 PDF file]() consists of the full requirements for the project.

Java is the language of choice for this project.

## Preconditions
Before using the application, note that 3 `.csv` files are required to be in the same working directory as your `.class` files after compilation. See [Usage](#Usage) for details of compilation.

It is recommended to not tamper with the files manually with the exception of `student.csv` and `staff.csv` to create new accounts.

Refer to the table for the required files:
| File | Description | Data Types |
| - | - | - |
| branch_list.csv | This file consists of all the branches in the system. | Name, location and Staff Quota.|
| menu_list.csv | This file consists of all the menu list for each branches in the system.  | Name, Price, Branch and Category.|
| staff_list.csv| This file consists of all the staff in the system. | Name, Staff Login ID, Role, Gender, Age, Branch and Default Password.|



## Usage
Please see [preconditions](#Preconditions) prior to running the application to avoid issues.

A JDK is required to compile the source files and run the binaries.

Upon compilation, navigate into the folder containing the `.class` files and execute the `java CAMApp` command to run the program.
