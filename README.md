# Motomensajería

The Motomesajería project aims to develop an efficient and modern solution for the fast messaging service. This system is designed to connect customers with drivers who use various means of transportation, such as motorcycles, cars and others, optimizing the management of orders, routes and delivery times. In an environment where speed and reliability are essential, Motomesajería seeks to be a key tool for individuals and companies that require urgent or scheduled shipments. The platform combines features such as real-time geolocation, integrated payments and a notification system, ensuring a seamless experience for all parties involved.<br><br>

Technologies:<br>
•	Backend: Java<br>
•	Testing: Mockito<br>
•	Database: MySQL<br>
•	ORMs: Hibernate | JPA<br>
•	Framework: Spring Boot<br>
• Template Engine: Thymeleaf<br>
•	Frontend: HTML5 | CSS3 | Javascript<br>
•	APIs: Mercado Pago API | Google Maps API<br>
• Management and Automation Tool: Maven<br><br>

> [!NOTE]
> Prerequisites:<br>
> • MYSQL<br>
> • Java SDK 11<br>
> • Intellij IDEA (Optional)

<br>

> [!IMPORTANT]
>Steps for the operation of the project:
>1.	Clone Repository: git clone https://github.com/JoacoMongelos28/Motomensajeria.git
>2.	Create Database in MySQL: CREATE DATABASE motomensajeria;
>3. Open application and integrate Java SDK 11 if it asks you
>4.	Synchronize Hibernate with your Database. Go to src/main/java/config/HibernateConfig
><br><br>dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");   --> Configure according to the drivers of your Database<br>
>dataSource.setUrl("jdbc:mysql://localhost:3306/motomensajeria");  --> Configure according to your Database<br>
>dataSource.setUsername("YOUR_USERNAME");<br>
>dataSource.setPassword("YOUR_PASSWORD");<br><br>
>5.	Start the project with Jetty:Run: Go to the "m" of Maven in the right bar --> Open Spring Web MVC --> Plugins --> Jetty --> Jetty:Run
>6. Open Web at http://localhost:8080/home
>7.	Register as a Customer and create a shipment
>8.	When paying with Mercado Pago, log in with the following username and password.<br><br>
>User: TESTUSER2053005099<br>
>Password: FC981613#be14#4f1f#<br>
>Verification code: 810057<br><br>
>9.	Register as a Driver and accept the shipment created

<br>

> [!TIP]
> Enjoy the Web by testing the features you want!
