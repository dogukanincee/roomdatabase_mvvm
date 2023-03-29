# Harnessing the Power of Room Database Library in Android Development

https://dogukanincee.medium.com/harnessing-the-power-of-room-database-library-in-android-development-4f6b97befb49

Managing and retrieving data is a key component of creating accurate and efficient applications as an Android developer. Use of a local database is one of the most well-liked and practical ways to accomplish this. With its simple abstraction layer over SQLite, the Room database library from the Android Jetpack libraries is a great option for managing databases in Android applications. Many Android apps depend on the ability to store and retrieve data.

We will cover Room in-depth in this article, as well as its major benefits and how to effectively handle data in Android applications.

Why Use the Room Database Library?

The Room database library comes with several key benefits that make it a popular choice among developers:

Reduced boilerplate code: Room takes care of the low-level SQLite code, allowing developers to focus on defining the database schema and writing high-level database queries.
Type safety: Room generates compile-time checks to ensure that SQL queries and schema definitions are correct, helping to catch potential issues early.
Easy integration with other Jetpack components: Room seamlessly integrates with other Jetpack libraries such as ViewModel and LiveData, making it easier to build robust and maintainable applications.
Automatic database migrations: Room can automatically handle database migrations, reducing the complexity of updating your app’s data storage as requirements evolve.
Enhanced testing capabilities: Room offers built-in support for testing database operations, enabling developers to write comprehensive tests for their data storage solutions.
Let us now begin with the integration of Room in an Android app, as the benefits of Room have been acknowledged.

Open the project’s build.gradle file.
Add the necessary Room dependencies to the file.
Sync the project to ensure that the dependencies are added successfully.
By completing these steps, you will have successfully added Room to your project’s dependencies and can now use it to implement local data storage in your Android app.

Adding Room to the project’s dependencies: The necessary Room dependencies must first be added to the project’s build.gradle file.

Define the database schema In Room, the database schema is represented by annotated data classes that define the tables and columns in your database. This makes it easy to manage and understand your data structure.

We are defining a User entity that consists of an ID, a name, and an age. We use the @Entity annotation to inform Room that this class represents a table in the database. Moreover, we use the @PrimaryKey annotation to indicate that the id field is the primary key for the table.

Now, we need to define the DAO (Data Access Object) interface which will be used to interact with our Room database. The DAO interface will contain methods that correspond to specific database queries or operations.

The UserDao interface defines the methods that can be used to interact with the User table in the database. These methods are annotated with the appropriate @Query, @Insert, @Update, and @Delete annotations to perform the corresponding database operations. We have the methods for retrieving all users, retrieving a user by ID, inserting a user, updating a user, and deleting a user. The @Query annotation indicates that these methods correspond to SQL queries.

Build the database Create a RoomDatabase class to build and manage your database. This class will include methods for accessing the database and managing its lifecycle.

The database is built using the RoomDatabase class.

Here, an AppDatabase class is defined, which is extended from RoomDatabase. The entities belonging to this database and the database version are declared using the @Database annotation. In this case, only one entity, User, is present, and the database version is 1.

Additionally, the AppDatabase class contains an abstract method that returns an instance of the UserDao interface. This interface provides access to the database operations for the User entity. The User table in the database can be accessed by clients using this method.

Furthermore, a singleton instance of the AppDatabase is stored by a companion object that is defined in the AppDatabase class, which ensures that the AppDatabase class can only exist once in the application.

Implement a repository pattern by using a repository class that abstracts the details of database operations and provides a clear interface for accessing and manipulating data.

Encapsulating the database operations into a single repository class, the UserRepository class serves as an abstraction layer between the ViewModel and the database.

Database operations are managed by the UserRepository class. It contains a UserDao interface instance that can be used to implement actions like fetching all users, fetching a user by ID, adding, updating, and deleting users.

To manage UI-related data, create a viewmodel. Your application’s ViewModel class is in control of handling data-related operations and providing your UI components a data source. It interacts with LiveData to update the status to your data and modify the UI.

The UserViewModel class, which controls user-related actions in the app, is made possible by inheriting the AndroidViewModel class. For gaining access to system resources or services, such as the resources of the application, this class provides a reference to the application context.

The UserViewModel class in the application is in responsible of user-related operations. Methods for fetching all users, fetching a user by ID, creating a new user, modifying an existing user, and deleting a user are all given for the class. It makes reference to the UserRepository, which is in charge of handling database operations. A LiveData object used by the UserViewModel class maintains a list of all users in the repository. A life — cycle data holder class defined LiveData enables Ui components to monitor data changes and adapt the UI accordingly.

Configure a RecyclerView to show data Implement a RecyclerView in your application to display a list of data, along with a unique adapter to link data to UI elements.

The UserAdapter class, a RecyclerView adapter, displays a list of users. A list of User objects is stored in the user item layout once it has been inflated using a reference to the LayoutInflater.

Use data operations in your application. For data operations like inserting, updating, and deleting entries in your database, use the viewmodel and repository classes.

The MainActivity class detects the UserViewModel and configures the RecyclerView with the UserAdapter and the list of users. Some users are also added to the database for testing purposes.

The UserViewModel is instantiated, the RecyclerView is set with the UserAdapter, and some test users are added to the database in the onCreate() method. The UserViewModel’s getAllUsers() method, which provides a LiveData object with a list of each and every user in the database, is then used to observe the user list. The setUsers() function of the UserAdapter will be triggered to update the UI whenever the list of users changes, notifying the observer and updating the UI.

Conclusion
Room is a robust and user-friendly library that can be used to create local data storage alternatives in Android applications. With Room, developers are provided with a high-level abstraction over SQLite, enabling them to write less repetitive code and focus on creating excellent user experiences.

Throughout this article, a simple user database was built in an Android application using Room. The basics of Room, including entity creation, data access object definition, and database setup, were covered. Additionally, the repository pattern was discussed as a means of abstracting database operation details, and the ViewModel was examined as a way to provide a data source to UI components.

By adhering to the principles outlined in this article, scalable and maintainable local data storage solutions can be developed for Android applications.
