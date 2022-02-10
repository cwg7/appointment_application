
This application, Appointment Manager, is designed to manage appointments and allow users
to create, edit, and delete appointments; as well as create, edit, and delete customer records.  Users can also view
reports such as the number of appointments by type and month, and appointment schedules by contact. There is also a
third report which simply keeps track of the total number of appointments on record.


Instructions:

Launch the application. The application currently only accepts two usernames and their corresponding passwords, as those
are the only valid credentials on record in the database. As of now the only two valid usernames are 'test' and 'admin',
and their passwords are the same as the usernames (ie Username: 'test', Password: 'test'). Upon successfully logging in,
a popup will inform the user ('test') if there are any appointments scheduled within the next 15 minutes.
The Main Menu is effectively the launch pad of the application. There the user can click on the 'Customer Data' button,
which will take the user to a screen where customer data can be accessed/managed. A full schedule of appointments
is displayed on the Main Menu and allows the user to interact with appointment modalities via create/update/delete
options.

There is also a 'Reports' button on the Main Menu as well, where the user can go to access the three different
report options as per the requirements of the project.

Report 1: filters appointment info by month and type
Report 2: filters appointment info by contact (schedules per contact)
Report 3: returns total number of appointments on file



This program was developed with IntelliJ/IDEA 20.1.1 using Java jdk-11.0.11
The JavaFX version used is openJFX 11.0.2

Using MySQL JDBC Connector 8.0.26