# smsbanking
SMS interface for managing accounts

This is a sms banking project by Cemil Aydogdu. https://github.com/caydogdu/smsbanking

These are the main abilities of project

    Ability to allow customers to perform simple operations on their account using their mobile phones

This project was developed with spring boot. Java 8 is required. No database was used. Some interfaces (**TransferManager**, **UserManager**) do not have implementations so **Mockito** was used in unit tests.

## Run options and deployment

This project is a microservice. So you can easily run it.

1- Running as a packaged application If you use the Spring Boot Maven or Gradle plugins first create an executable jar then you can run your application using java -jar. For example: 

    $ java -jar target/smsbanking-0.0.1-SNAPSHOT.jar 
    
It is also possible to run a packaged application with remote debugging support enabled. This allows you to attach a debugger to your packaged application:

2- Using the Maven plugin The Spring Boot Maven plugin includes a run goal which can be used to quickly compile and run your application. Applications run in an exploded form just like in your IDE.

    $ mvn spring-boot:run
