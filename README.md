
# Inventory Management System

This project was developed for the Reuse and Component Based Development - ISB 37804 course at the Universiti Kuala Lumpur.

This project is using Java Enterprise (Java EE), Enterprise Java Bean (EJB) module, and Shared Library. Furthermore, the project also using Data Access Object (DAO) patterns to connect with the database and Model View Controller (MVC) architecture to separate application logic from the user interface.

Inventory Management System (IMS) frequently used in many business and enterprise in order to maintain stock keeping of their products. In this project, the IMS being used to record and store product inventories systematically.

This project consists two sides;
1. Administrator <br>
Administrator are able to manage all order, product and the staff including their account credentials.

2. Staff <br>
Staff able to manage order that been created by their own account and not anyone else, restock product quantity and manage their own account details.

## License

The source code has been published on GitHub Repository under  _MIT License_.  
Please visit  `LICENSE`  file to read the details about the license.

## About the project

Language :
- Java
- Javascript

Technologies:
-  Java Enterprise Edition (Java EE)
- Enterprise Java Bean (EJB)
- Java Web Application
- MDBootstrap 5 CSS
- MySQL Database

IDE:
- Netbeans IDE 8.2

Dependencies:
- JDK 1.8.0 Update 341
- GlassFish Server 4.1.1
- MySQL Connector J 8.0.17


## How to use ?
1. Fork, clone and download this repository .
2. Import and restore the MySQL database.
3. There are four (4) project file : 
- `InventoryEA-ejb` <br>
- `InventoryEA-SharedLibrary` <br>
- `InventoryEA-war` <br>
- `Inventory-EnterpriseApplication` <br>
4. Open all projects via Netbeans IDE. <br>
5. Deploy and run `InventoryEA-war` <br>
6. Navigate to URL; `/InventoryEA-war/AdminLogin` <br>
7. Login with the following default admin credentials : <br>
> Username : admin
> Password : admin

Notes: If necessary, please build the project by following this sequence;<br>
 `InventoryEA-SharedLibrary` -> `InventoryEA-ejb`. <br>
Finally, deploy and run `InventoryEA-war`.<br>

## 1. Use Case Diagram
<img src="https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-Java-Enterprise/main/img/Usecase2.png" width=380>

## 2. Business Process Flow

2.1 Administrator Process Flow

<img src="https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-Java-Enterprise/main/img/admin_flow.png" width=650>

2.2 Staff Process Flow

<img src="https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-Java-Enterprise/main/img/staff_flow.png" width=590>


## 3. Database

<img src="https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-Java-Enterprise/main/img/erd2.png" width=650>

<img src="https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-Java-Enterprise/main/img/datamodel2.png" width=650>

Database SQL command to create the tables is located at [`Database/inventoryms.sql`](https://github.com/iamashraff/Inventory-Management-System-JavaEE-Web/blob/main/Database/inventoryms.sql)




## 4. User Interface

**4.1 Administrator**

4.1.1 Admin Login Page

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Admin/1%20-%20Admin%20Login.png)

<hr>

4.1.2 Admin Dashboard

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Admin/2%20-%20Dashboard.png)

<hr>

4.1.3 Category Page

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Admin/3%20-%20Category.png)

<hr>

4.1.4 Product Page

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Admin/4%20-%20Product.png)

<hr>

4.1.5 Order Page

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Admin/5%20-%20Order.png)

4.1.5.1 Create New Order

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Admin/5.1%20-%20Create%20New%20Order.png)

4.1.5.2 View Order

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Admin/5.2%20-%20View%20Order.png)

4.1.5.3 Edit Order Quantity

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Admin/5.3%20-%20Edit%20Order%20Quantity.png)

4.1.5.4 Add Product to Order

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Admin/5.4%20-%20Add%20Product%20to%20Order.png)

<hr>

4.1.6 Staff Page

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Admin/6%20-%20Staff.png)

4.1.7 Administrator Page

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Admin/7%20-%20Administrator.png)

<hr>

**4.2 Staff**

4.2.1 Staff Login Page

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Staff/1%20-%20Staff%20Login.png)

<hr>

4.2.2 Staff Dashboard Page

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Staff/2%20-%20Dashboard.png)

<hr>

4.2.3 Product Page

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Staff/3%20-%20Product.png)

4.2.3.1 Restock Product

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Staff/3.1%20-%20Restock%20Product.png)

<hr>

4.2.4 Order Page

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Staff/4%20-%20Order.png)

4.2.4.1 View Order

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Staff/4.1%20-%20View%20Order.png)

4.2.4.2 Add Product to Order

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Staff/4.2%20Add%20Order.png)

4.2.4.3 Edit Order Quantity

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Staff/4.3%20Edit%20Order%20Quantity.png)

<hr>

4.2.5 Staff Profile Page

![enter image description here](https://raw.githubusercontent.com/iamashraff/Inventory-Management-System-JavaEE-Web/main/img/Staff/5%20-%20Staff%20Profile.png)


## Credit

**Developed by :**  
_Muhamad Ashraff Othman_  
© 2023 All rights reserved.
