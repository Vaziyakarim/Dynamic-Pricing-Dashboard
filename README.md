# Dynamic Pricing Dashboard

A full-stack web application that implements a **Dynamic Pricing System** using Spring Boot, MySQL, HTML, CSS, JavaScript, and Chart.js.

---

# 📌 Project Overview

The Dynamic Pricing Dashboard helps businesses manage products and automatically adjust product prices based on:

* Demand
* Stock availability
* Market trend
* Recent sales
* Peak time

The system also allows users to:

* Add products
* Update products
* Delete products
* Record sales
* View sales history
* Track total revenue
* View revenue charts

---

# 🚀 Technologies Used

## Frontend

* HTML
* CSS
* JavaScript
* Chart.js

## Backend

* Java
* Spring Boot
* REST API

## Database

* MySQL

---

# ✨ Features

## Product Management

* Add products
* Edit products
* Delete products
* Search products
* Filter products

## Dynamic Pricing

Product prices change automatically based on:

* High demand
* Market trends
* Recent sales
* Peak time
* Stock levels

## Sales Management

* Record sales
* Update stock automatically
* Increase demand automatically
* Save sales history

## Revenue Tracking

* View total revenue
* Daily revenue chart
* Monthly revenue chart

---

# 📂 Project Structure

```text
DynamicPricing/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/pricing/dynamicpricing/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   │
│   │   └── resources/
│   │       └── application.properties
│
├── frontend/
│   ├── index.html
│   ├── add-product.html
│   ├── record-sale.html
│   ├── sales-history.html
│   ├── charts.html
│   ├── style.css
│   └── script.js
│
├── pom.xml
└── README.md
```

---

# ⚙️ How to Run the Project

## 1️⃣ Configure MySQL Database

Create a database in MySQL:

```sql
CREATE DATABASE dynamic_pricing;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/dynamic_pricing
spring.datasource.username=root
spring.datasource.password=yourpassword
```

---

## 2️⃣ Run Spring Boot Backend

Open terminal and run:

```bash
.\mvnw spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

---

## 3️⃣ Run Frontend

Open the frontend folder using Live Server in VS Code.

---

# 📊 API Endpoints

| Method | Endpoint                 | Description       |
| ------ | ------------------------ | ----------------- |
| GET    | /product/all             | Get all products  |
| POST   | /product/add             | Add product       |
| PUT    | /product/update/{id}     | Update product    |
| DELETE | /product/delete/{id}     | Delete product    |
| POST   | /product/sale/{id}/{qty} | Record sale       |
| GET    | /product/sales/all       | Get sales history |
| GET    | /product/revenue         | Get total revenue |
| GET    | /product/revenue-details | Get chart data    |

---

# 📈 Dynamic Pricing Logic

The final product price is calculated using:

* Base price
* Demand level
* Market trend
* Recent sales
* Peak time conditions

This helps simulate real-world pricing strategies used in modern businesses.

---

# 🎯 Learning Outcomes

Through this project, I learned:

* Full-stack development
* REST API integration
* Spring Boot CRUD operations
* MySQL database handling
* Dynamic pricing concepts
* Frontend-backend connectivity
* Data visualization using Chart.js

---

# 👩‍💻 Author

K Vaziya

BTech CSE Student
Amity University Bengaluru
