# CSX42: Assignment 3
## Name: Omkar Udawant

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [studentskills/src](./studentskills/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile studentskills/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile studentskills/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile studentskills/src/build.xml run -Dinput="input.txt" -Dmodify="modify.txt" -Dout1="output1.txt" -Dout2="output2.txt" -Dout3="output3.txt" -Derror="error.txt" -Ddebug="debug.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:
StudentRecord class created to store the student details from input file like student's Bnumber(4 digits long non-negative), Firstname, Lastname, GPA, Major, Skills(Max 10 skills per student).
TreeHelper class implements Cloneable and constructs a Binary tree by calling insert method. It also performs search operation to search for existing node in the tree. Upon successful search(when student node found), the instance of that node along with all stored values returned to the calling object. Various operations can be performed on this object.
Observer pattern is implemented using SubjectI and ObserverI classes. These classes are interface classes. They contain methods to register observer who gets notified upon modification of original instance. StudentRecord object is set to register observers and notify observer list.
Enum named Operation is created. It contains two values INSERT and MODIFY. INSERT denotes record insertion whereas MODIFY denotes updating values of existing record.
Driver class accepts 7 commandline arguments and creates necessary FlieProcessor object to access the input and modification files.
Apart from Driver class, DriverHelper class is created which parses the input line from input.txt and modify.txt files and performs action based on operation(INSERT or MODIFY).  
Results class object created to write the output of cloned trees to a respective file. 
MyLogger class is used to debug the program.
Exception handling done to trace appropriate errors. Code is well commented for better understanding of function's purpose.
Created class for custom Exception handling called StudentException.
Data Structure used:    `LinkedHashMap to store StudentRecord data where each record is identified through student's Bnumber.`
                        `ArrayList of type Object to store different data types.`

Binary Search Tree(BST) is implemented to construct Tree. It stores data in sorted order which makes it easy to retrive when and where necessary. Also, its search time complexity is O(h), insertion based time complexity is also O(h) and deletion time complexity is O(h) where, h is height of the tree. I have chosen to use BST because it is easy to implement for smaller data set.  

Reference used for implementing BST: `https://j2eereference.com/binary-search-tree-implementation-java/`
Reference used for implementing Observer Pattern: `https://www.baeldung.com/java-observer-pattern`

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 07/12/2020


