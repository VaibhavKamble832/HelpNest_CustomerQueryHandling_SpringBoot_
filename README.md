<h1>HelpNest_CustomerQueryHandling_SpringBoot</h1>

<h2>Overview: </h2>
<p>This project is a comprehensive User and Query Management System developed using Spring Boot, integrating Spring Security for robust authentication and authorization. The application features a structured database using MySQL, with dynamic web pages rendered using Thymeleaf, HTML, and CSS. It's designed to handle different user roles including Admins, Employees, and Customers, each with specific privileges.</p>

<h2>Features: </h2>
<p><b>1) Multi-level Login:</b> Differentiated access levels for Admins, Employees, and Customers, each with custom views and permissions.</p>
<P><b>2) Admin Panel: </b>Admins can log in with default credentials, view all users (Employees and Customers), and register new Employees.</P>
<P><b>3) Employee Interactions: </b> Employees can sign up, log in, view all customer details, and manage customer queries by updating statuses such as Read or Dumped.</P>
<P><b>4) Customer Engagement:</b> Customers can register, log in, and submit queries which are accessible to both Admins and Employees.</P>
<P><b>5) Query Status Management: </b>Allows Employees to update the status of each query to keep track of processing stages.</P>

<h2>Technologies Used: </h2>
<P>-<b>Backend: </b>Java, SpringBoot.</P>
<P>-<b>Security</b> Spring Security for authentication and authorization</P>
<P>-<b>Database</b> MySQL for data storage</P>
<P>-<b>Frontend: </b> Thymeleaf for server-side rendering, complemented by HTML/CSS for styling</P>

<h2>Usage:</h2>
<P><b>Admin </b>can use default login credentials to access the system, oversee all user activities, and register Employees.</P>
<P><b>Employees </b> can sign up, manage their profiles, and handle customer queries after logging in.</P>
<P><b>Customers </b>can register and log in to submit queries, view their statuses, and update their profiles.</P>
