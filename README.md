# Getting Started

## Start app
Open terminal(or cmd in Win) in dir with `pom.xml` and enter command:

 ``mvn spring-boot:run``
#
#### Additional info
Bot's `name` and `token` you can find in
 
 `<project-dir>/src/main/resources/telegram.properties`
 
 #
 ### REST API
 REST API is available on URL:
 
 `http://localhost:8080/api/v1/cities`
 
 If You want to create (or update) city, You should enter `name` in upper case
 
 for example:
  
    {
        "name": "МИНСК",
        "advice": "some text"
    }

#
#### Initialized Data
On startup, next cities are available:

	Минск
	Люксембург
	Стокгольм
	Антверпен
	Брюссель
	Карловы Вары
	Венеция
	Прага
	Рим
	Женева
	Вена
	Флоренция
	Барселона
