# IMPORTANT
# PORT NUMBER HAS BEEN CHANGE 
# DONT FORGETTO CHAGE IT BACK TO 23000
#-----------

# Laundrative Rest API 

## How to set up the Rest API 

* Download the repository
* Open the project in the preferred code editor / idea
* Download the dependencies using maven download
* Open the application.properties file
* Change the  following properties according to the os

```
   server.ssl.key-store  - This is the certificate path
   spring.datasource.url - This is the url for the datasource
   spring.datasource.username - This is the username for which the API will connect with
   spring.datasource.password - This is the password for which the API will connect with
   spring.datasource.driver-class-name - This is the driver which the API wll use while connecting to the database (MYSQL driver is already on the dependencies)
```

* Run the src/main/java/com/laundrative_v2/app/AppApplication.java 
