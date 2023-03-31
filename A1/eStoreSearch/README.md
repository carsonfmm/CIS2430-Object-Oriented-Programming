Name: Carson Mifsud (1089936)
Date: 2020-10-19
Description:  This program is an eStore simulator which allows a user to add new books and electronics
              to the store. The user also has the ability to search for a book or electronic
              by a specific productID, keyword(s) and/or date. This program contains multiple
              error checking senarios to increase the users experience while interacting with the
              eStore. Limitations of the program include only being able to enter a year from
              1000-9999, productIDs must be 6 digits long and no duplicate productIDs are permitted.
              In addition, the user must enter the productID, description and year as reqwuired fields.
              All other fields including price, authors, publisher and maker are optional. Moreover,
              the user cannot search a product via the price, author, publisher or maker. The search
              function may only be used with productIDs, keyword(s) and/or a time period.

User Guide:
'gradle build': compiles the program
'gradle run': executes the program
'gradle test': Runs multiple Junit tests on the methods created and used in the EStoreSearch program

NOTE: The possible improvements that could be made to the code are additional error checking statements.
      Although, I have attempted to make as many error detections as possible, the program may still
      crash for certain inputs. Furthermore, I plan to increase my programs efficiency by shortening
      my programs length and creating methods for repition of blocks of code.

Test Plan:
The user is greeted by the program.
The user should enter 'add' followed by 'book' to add a new book to the store.
The user is then prompted by the program to enter the productID, description, year, price, authors and publisher for the book.
After these steps are taken the user should have successfully entered and saved a new book to the eStore.
Next, the user should enter 'add' followed by 'electronic' to add a new electronic to the store.
The user is then prompted by the program to enter the productID, description, year, price and maker for the electronic.
After these steps are taken the user should have successfully entered and saved a new electronic to the eStore.
Lastly, the user should enter 'search'.
The user will then be prompted to enter a combination of the following three fields: productID, description and year.
After the user enters information for the above fields, the program will search through all books and electronics in the estore
and print out any matches to the user.
The user may then choose to add another book or electronic, search the eStore again, or enter 'quit' to quit the program.