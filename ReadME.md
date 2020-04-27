# Getting Started

### Dockerfile with fabric8 plugin
Docker-fabric8 plugin is included in the pom file.
Dockerfile exists in the project base directory

* No Docker host is given in pom file, thus before starting the build please start docker in local.
* mvn clean install #this will build the docker image

for example -

[ankitm@localhost covid-tracker]$ docker images
REPOSITORY               TAG                 IMAGE ID            CREATED             SIZE
covid-tracker            0.0.1-SNAPSHOT      9f2fc5938b3b        14 seconds ago      683MB

* To start the application - docker run -i covid-tracker:0.0.1-SNAPSHOT

### Apis


* [initialData](localhost:8080/initialData) PUT
	- Header: Content-Type:text/csv
	- Request 
Id,Location,Tested,Confirmed,Active,Recovered,Dead
1,Delhi,3000,51,49,1,1
2,Gujrat,11000,545,544,0,1
3,Kerala,7000,145,144,1,0


* [updateCityData](localhost:8080/updateCityData/delhi) PATCH
	- Header: Content-Type:application/json
	- Request 
{
    "Tested":150,
    "Confirmed":10,
    "Active":1
}

* [Search Filter](localhost:8080/findAll?location=Delhi&location=Gujrat) GET

* [Search Filter](localhost:8080/findAll?location=Delhi&location=Gujrat&type=min&selected=active) GET

