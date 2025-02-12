                                        User Management Service (RBAC) API Documentation


1. Overview

   
The User Management Service is a backend system implementing Role-Based Access Control
(RBAC) for User, Role, and Permission Management. It ensures secure authentication and
authorization based on assigned roles.


2. Features :
   
    User Management
    - Create users
    - Retrieve user list
    - Assign predefined roles (Staff, Supervisor, Admin)
    
    Role Management :
    - Retrieve a list of predefined roles
    - Admin can dynamically assign permissions to roles
      
    Permission Management :
    - Define and associate permissions with actions dynamically
    - List all available permissions
    - Retrieve permissions assigned to a specific role
      
    Access Validation:
    - Validate whether a user has permission to perform an action on a resource
      
3. Setup Instructions :
        Prerequisites
   
        - Java 17+
        - Spring Boot 3.x
        - Maven
        - MySQL

4. API Documentation :


User Management APIs 

          POST /users/register - Create a new user (All Users)
          POST /users/login - Login a user (All Users)
          GET /users/{id} - Get user details by ID (Admin, Staff)
          POST /users/{userId}/assign-role - Assign a role to a user (Admin)
      
     
      
Role Management APIs


         GET /roles/ - Retrieve all roles (Admin, Staff)
         POST /roles/assign-permission - Assign permission to a role (Admin)


Permission Management APIs

     
        GET /permissions/ - Retrieve all permissions (Admin, Staff)
        GET /permissions/role/{roleName} - Get permissions assigned to a role (Admin)




5. Technologies Used
- Spring Boot
- Spring Security & JWT
- Hibernate & JPA
- MySQL

  
6. Postman Documentation
Postman API Collection: https://documenter.getpostman.com/view/35165121/2sAYXCidcc
