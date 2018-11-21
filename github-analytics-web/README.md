# Github Analytics Demo

Project has two profiles:

* deployment
* development

##Configuration

In application.yml please specify github connector credentials. In current sample I am using basic 
authentication, so you can set your user name and password (personal token).

##Build
For building go to project's root directory and execute this command:
----
	$ mvn clean package -P <profile>

##Run

After building the project, you can copy created .jar file to the cloud, docker or somewhere on your local machine and run entire Spring application by
choosing the environment profile and just running this command:

``java -jar -Dspring.profiles.active=<profile> github-analytics-web-[version].jar``

## Author
**Aram Mkrtchyan**
