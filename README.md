
# 🌟 Customer Details Management Service 🌟

Welcome to the Customer Details Management Service! This API-driven service empowers you to seamlessly Create, Read, Update, and Delete customer details. Dive into our documentation to explore the world of hassle-free customer data management.

## REST API Documentation

**Title:** Customer Details Management Service  
**Description:** A modern service for managing customer details with flair!  
**Version:** v1

### Unique Constraints
- The email will be a unique identifier for each customer.
- The magic lies in the uniqueness of the combination: occupation, customer group, and date of birth (dob).

## Technologies Used

- **Java:** The backbone of our service.
- **Spring Boot:** Powering our service with robust and efficient features.
- **JPA (Java Persistence API):** For easy and seamless interaction with the database.
- **Swagger:** Providing interactive API documentation.
- **JUnit & Mockito:** Ensuring the reliability and correctness of our service through testing.

## 🚀 Controller

### 🌈 Create Customer Details Record

**Endpoint:** `POST /api/customer`  
**Description:** Create a new customer record effortlessly. Your gateway to personalized customer experiences!

### 🕵️‍♂️ Fetch Customer Details

**Endpoint:** `GET /api/customer`  
**Description:** Explore customer details based on their email. Knowledge is power!

### ✨ Update Customer Details

**Endpoint:** `PUT /api/customer`  
**Description:** Elevate customer details with ease. Remember, the uniqueness of each customer is our priority!

### 🗑️ Delete Customer Details

**Endpoint:** `DELETE /api/customer/{email}`  
**Description:** Bid farewell to customer records based on their email. A clean slate for new beginnings!

## 🛠️ Service

### Method: `createCustomerDetailsRecord`

- Validates input data.
- Checks if the email already exists, ensuring the uniqueness of each customer.
- Validates the age, because age is just a number!
- Assigns a customer group based on email and occupation—making it personal!
- Ensures the uniqueness of the combination of occupation, dob, and customer group.
- Saves the customer details and opens the door to a world of possibilities.

### Method: `getCustomerDetailsByEmail`

- Fetches customer details based on the provided email.
- Throws a `ResourceNotFoundException` if the record is not found. After all, we want you to find what you're looking for!

### Method: `updateCustomerDetailsRecord`

- Fetches existing customer details by email because updates should be seamless!
- Validates the age, because, let's face it, age matters.
- Assigns a new customer group based on the updated data—keeping it fresh!
- Checks for the uniqueness of occupation, dob, and customer group combination.
- Updates and saves the customer details.
- Returns true if the record is updated successfully. Because successful updates make everyone smile!

### Method: `deleteCustomerDetailsByEmail`

- Fetches existing customer details by email, ensuring precision.
- Deletes the customer details with a gentle farewell.
- Returns true if the record is deleted successfully. Out with the old, in with the new!

## 🗄️ Repository

### ICustomerDetailsRepo

- JPA repository for CustomerDetails entity.
- Provides methods for finding records by email and a combination of occupation, dob, and customer group.

## 🌈 Model

### CustomerDetails

- Represents the customer details entity. It's not just data; it's an experience!
- Includes fields for id, name, email, dob, occupation, customer group, created and updated timestamps, and audit-related fields. Because every detail matters!

## 🔍 Swagger Documentation

Explore our API using Swagger for an interactive experience! Simply visit: [Swagger Documentation](<your-swagger-docs-url>)




## Requirements for setup

Make sure you have the following installed:

- **Java Development Kit (JDK) 17** The heart of our service.
- **Maven:** For managing dependencies and building the project.
- **Docker (Optional):** If you want to run the service in a containerized environment.

## Local Deployment

Follow these steps to deploy the Customer Details Management Service locally:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/arjunagi-a-rehman/customer_details_management_service.git
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd customer-details-management-service
   ```
note:- in application.properties at src/main/resources set environment variable or hard code the credentials
3. **Build the Project:**
   ```bash
   mvn clean install
   ```

4. **Run the Application:**
   ```bash
   java -jar target/customer-details-management-service.jar
   ```

5. **Access Swagger Documentation:**
   Open your browser and visit [Swagger Documentation](http://localhost:8080/swagger-ui/) to interactively explore our API.

6. **Test the Endpoints:**
   You can use tools like curl, Postman, or any API testing tool to interact with the service.

7. **Enjoy Managing Customer Details Locally!**

## Dockerized Deployment (Optional)

If you prefer running the service in a Docker container using the Jib Maven plugin:

1. **Build and Push Docker Image:**
   ```bash
   mvn clean compile jib:build
   ```

   _Note: Make sure to configure your Jib plugin settings in your `pom.xml` for authentication if your Docker registry requires it._

2. **Run the Docker Container:**
   ```bash
   docker run -p 8080:8080 <your-docker-image>
   ```

   _Replace `<your-docker-image>` with the image name and tag you specified during the Jib build._

3. **Access Swagger Documentation:**
   Open your browser and visit [Swagger Documentation](http://localhost:8080/swagger-ui/) to interactively explore our API.

4. **Test the Endpoints:**
   You can use tools like curl, Postman, or any API testing tool to interact with the service.

5. **Enjoy Managing Customer Details in a Docker Container!**
