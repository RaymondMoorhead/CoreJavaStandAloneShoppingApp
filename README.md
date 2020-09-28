# CoreJavaStandAloneShoppingApp
A java console shopping app.

This project uses ANSI codes to clear the screen, and if they aren't supported on your system then one option is to change 'com.shoppingapp.utility.ConsoleExtras.ANSI_CLEAR' to be equal to "\n\n\n" in order to space out different menus.

It uses a local sql database by default, and will generate the database and tables if they are absent. If you wish to use a remote sql database, modify the url, username, and password in DaoManager. If you wish to change to local memory, then uncomment DaoManager.initializeLocal in Main.java. If local memory is used, then by default it has 3 items and a test user with an old and current invoice. The test user is TestUser with password TestPassword.
