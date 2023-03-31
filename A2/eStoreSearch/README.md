Assume the input file will always be in the form:

type = "(book)"
productID = "(6 digit number)"
description = "(description of product)"
price = "(price of product)"
year = "(year of product)"
authors = "(authors of products)"
publisher = "(publisher of product)"

type = "(electronics)"
productID = "(6 digit number)"
description = "(description of product)"
price = "(price of product)"
year = "(year of product)"
maker = "(maker of product)"

Name: 
Date: 2020-11-08
Description:  This program is an eStore simulator which allows a user to add new books and electronics
              to the store. The user also has the ability to search for a book or electronic
              by a specific productID, keyword(s) and/or date. When searching for keyword(s) a HashMapping
              method is used to reduce code redundancy. This program contains multiple
              error checking senarios to increase the users experience while interacting with the
              eStore. This program also accepts a file name as the first command line argument. The program
              will read this file and add the products in the file to the eStore. In addition, after
              each new product is added by the user, this information will be written to the same file
              which the original products were read from. This file is used to give the user a visual
              representation of all products within the eStore. Limitations of the program
              include only being able to enter a year from 1000-9999, productIDs must be 6 digits long
              and no duplicate productIDs are permitted. In addition, the user must enter the productID,
              description and year as reqwuired fields. All other fields including price, authors, publisher
              and maker are optional. Moreover, the user cannot search a product via the price, author,
              publisher or maker. The search function may only be used with productIDs, keyword(s) and/or a time period.

User Guide:
'gradle build': compiles the program
'gradle run --args'read.txt': executes the program, with the input file name as the first command line argument
'gradle test': Runs multiple Junit tests on the methods created and used in the EStoreSearch program

NOTE: The possible improvements that could be made to the code are additional error checking statements.
      Although, I have attempted to make as many error detections as possible, the program may still
      crash for certain inputs. An example being, if the program is expecting an integer and a String is
      inputted by the user instead. Furthermore, I plan to increase my programs efficiency by shortening
      my programs length and creating methods for repition of blocks of code. Lastly, I could add another
      method which allows the user to delete products from the store to allow the user to have more
      functionality and freedom with the eStoreSearch program.

Test Plan:
The program reads in the .txt file given as the first command line argument, and adds all products in the .txt file to the eStore.
The user is greeted by the program.
The user should enter 'add' followed by 'book' to add a new book to the store.
The user is then prompted by the program to enter the productID, description, year, price, authors and publisher for the book.
After these steps are taken the user should have successfully entered and saved a new book to the eStore and have this new product
printed to the output file.
Next, the user should enter 'add' followed by 'electronic' to add a new electronic to the store.
The user is then prompted by the program to enter the productID, description, year, price and maker for the electronic.
After these steps are taken the user should have successfully entered and saved a new electronic to the eStore and have this new product
printed to the output file.
If at any point the user attempts to enter a productID which was previously reserved in the store (by manually adding a product or
read in from the input file), an error message will be printed to the user and they will be brought back to the main menu.
Lastly, the user should enter 'search'.
The user will then be prompted to enter a combination of the following three fields: productID, description and year.
After the user enters information for the above fields, the program will search through all books and electronics in the estore
and print out any matches to the user.
The user may then choose to add another book or electronic, search the eStore again, or enter 'quit' to quit the program.
