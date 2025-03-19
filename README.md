# 🚗📦 Motomensajería

The Motomesajería project aims to develop an efficient and modern solution for the fast messaging service. This system is designed to connect customers with drivers who use various means of transportation, such as motorcycles, cars and others, optimizing the management of orders, routes and delivery times. In an environment where speed and reliability are essential, Motomesajería seeks to be a key tool for individuals and companies that require urgent or scheduled shipments. The platform combines features such as real-time geolocation, integrated payments and a notification system, ensuring a seamless experience for all parties involved.<br><br>

## 💻 Technologies:<br>
•	**Backend:** Java<br>
•	**Database:** MySQL<br>
•	**ORMs:** Hibernate | JPA<br>
•	**Framework:** Spring Boot<br>
• **Template Engine:** Thymeleaf<br>
•	**Testing:** Mockito | Playwright<br>
•	**Frontend:** HTML5 | CSS3 | Javascript<br>
• **Management and Automation Tool:** Maven<br>
•	**APIs:** Mercado Pago API | Google Maps API<br><br>

> [!NOTE]
> ## Prerequisites:<br>
> • **MYSQL**<br>
> • **Java SDK 11**<br>
> • **Intellij IDEA (Optional)**

<br>

> [!IMPORTANT]
> ## 🚀 How to install locally
> ### 1.	Clone Repository:
> ```bash
> git clone https://github.com/JoacoMongelos28/Motomensajeria.git
> ```
> ### 2.	Create Database in MySQL:
> ```bash
> CREATE DATABASE motomensajeria;
> ```
> ### 3. Open application and integrate Java SDK 11 if it asks you
> ### 4.	Synchronize Hibernate with your Database.
> **Go to src/main/java/config/HibernateConfig**
>```bash
> dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");   --> Configure according to the drivers of your Database
> dataSource.setUrl("jdbc:mysql://localhost:3306/motomensajeria");  --> Configure according to your Database
> dataSource.setUsername("YOUR_USERNAME");
> dataSource.setPassword("YOUR_PASSWORD");
>```
> ### 5.	Start the project with Jetty:Run.
> **Go to the "m" of Maven in the right bar --> Open Spring Web MVC --> Plugins --> Jetty --> Jetty:Run**
> ### 6. Open Web at:
> **http://localhost:8080/home**
> ### 7.	Register as a Customer and create a shipment
> ### 8.	When paying with Mercado Pago, log in with the following username and password.
> ```bash
> User: TESTUSER2053005099
> Password: FC981613#be14#4f1f#
> Verification code: 810057
> ```
> ### 9.	Register as a Driver and accept the shipment created

<br>

> [!TIP]
> Enjoy the Web by testing the features you want!

## 📫 Contact me
• 🌐 [Portfolio](https://joaquinmongelos.netlify.app/)<br>
• 💼 [LinkedIn - Joaquin Mongelos](https://www.linkedin.com/in/joaquinmongelos)<br>
• 📧 Email: joaquinmongelos75@gmail.com
