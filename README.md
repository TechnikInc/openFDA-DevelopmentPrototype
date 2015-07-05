[![Build Status](https://travis-ci.org/krishnachaganti/openFDA-DevelopmentPrototype.svg?branch=master)](https://travis-ci.org/krishnachaganti/openFDA-DevelopmentPrototype)



# OpenFDA Adverse Drug Event Reports by Country - [Technik, Inc.](http://www.technikinc.com) 

[Click here to view the prototype](http://ec2-54-175-24-134.compute-1.amazonaws.com:8080/technikfda/)

This prototype allows consumers to view adverse drug event reports by country via a simple and intuitive graphical user interface. The [openFDA drug adverse event API](https://open.fda.gov/drug/event) was consumed [Restfully](https://en.wikipedia.org/wiki/Representational_state_transfer) to return data from the [FDA Adverse Event Reporting System (FAERS)](https://open.fda.gov/data/faers/) 


## Approach used to create Pool Two Development prototype

***We followed the U.S. Digital Services Playbook requirements***

a. Mr. Satish Venkatesan was the assigned leader (Product Manager) with authority, responsibility and accountability for the quality of the prototype submitted.

b. The Technik openFDA development team consisted of three resources assigned to the following Design Pool Labor categories from the Development Pool Labor categories from 18F Agile Labor Categories. Attachment C is updated accordingly

 - Technical Architect - Manage the technical implementation of the prototype
 - Dev Ops  Engineer - Configure continuous integration, continuous deployment and configured operating system level virtualization
 - Front  End Web Developer - Developed the prototype using modern techniques and frameworks. 
 -  Backend Web Developer - Used modern open source web programming languages to develop and consume web-based RESTful APIs.

c.The following modern and open-source technologies are applied to this effort

**Data Sources** 
 - Drug Adverse Event data from [openFDA    API] (https://open.fda.gov/api/reference/)  ( artifact - constants.java)
 - Lightweight data packages from [data.okfn.org](http://data.okfn.org/) to translate country codes to country names (artifact - CountryNameCodeRefresher.java)
 - GeoNames geographical database from [geonames.org](http://www.geonames.org)  to retreive country flag images for the displayed country (artifact - constants.java)

**Programming languages**
 - [Java](https://en.wikipedia.org/wiki/Java_programming_language) (artifact - RestUtil.java )
 - [JavaScript](https://en.wikipedia.org/wiki/JavaScript) (artifact - MasterDetailsCtrl.js)
 - [HTML](https://en.wikipedia.org/wiki/HTML) (artifact - index.jsp)

**Frameworks (Serverside)**
 - [Spring Web model-view-controller (MVC)](https://spring.io/guides/gs/serving-web-content/) Framework (artifact - spring-core-config.xml)
 - [Jackson-databinding](https://github.com/FasterXML/jackson-databind/) package  ( artifact - spring-mvc-config.xml)

**Frameworks (Clientside)**
 - HTML Vocabulary Extension using [AngularJS](https://angularjs.org/) (artifact - index.jsp)
 - [jQuery](https://jquery.com/) JavaScript library (artifact - index.jsp)
 - [Bootstrap](http://getbootstrap.com) for basic web components (artifact - index.jsp)

**Testing tools**
 - [JUnit](http://junit.org/) as the framework to write repeatable tests  ( artifact -  WelcomeControllerTest.java)
 - [Mockito](http://mockito.org/) mocking framework for creating JUnit tests (artifact - WelcomeControllerTest.java)

**Build Tools**
 - [Gradle](https://gradle.org/) open-source build automation tool (artifact -  build.gradle)

**Version Tools**
 - Web-based Git repository and source code management (SCM) using [GitHub](https://github.com/)
 - [Git Shell](http://git-scm.com/docs/git-shell) Login shell for Git-only SSH access

**Editor**
 - [Stackedit](https://stackedit.io/) Mark down editor ( artifact README.md)


d. The prototype is deployed on [Amazon Web Services (AWS)](https://aws.amazon.com/?nc2=h_lg) Platform as a Service (PaaS).  See "Evidence" folder in GitHub Repository for artifacts. ( artifact - Technik_18F_Web_Services.jpeg)

e.  Unit tests are implemented using Mockito with Junit (artifact - WelcomeControllerTest.java)

f. [Travis Continous Integration System](https://travis-ci.org) is used to automate the running of tests and continuously deploy code to Amazon Web Services using [AWS CodeDeploy](http://aws.amazon.com/codedeploy/) (  artifact  -  .travis.yml)

g. Used [Git](https://git-scm.com/) open source distributed version control system on [GitHub](https://github.com/) for configuration management

h. Used [Amazon CloudWatch](http://aws.amazon.com/cloudwatch/) for monitoring AWS cloud resources and the applications on AWS. See "Evidence" folder in GitHub Repository for artifacts. ( artifact - Technik_18F_Amazon_Cloud_Watch.jpeg)
 
i. Used [Docker container](https://www.docker.com/), an open platform for to deploy the application and all of its dependencies.  See "Evidence" folder in GitHub Repository for artifacts. ( artifact - Technik_18F_Docker.jpeg, Dockerfile  and  https://registry.hub.docker.com/u/krishnachaganti/openfda-developmentprototype/)

    1. one can run the docker image using docker run -p 8082:8080 -d krishnachaganti/openfda-developmentprototype which can use  get the latest image of this project and run in their container.
    
    2. one can build the docker image using the Docker file and technikfda.war file , copy them into a single directory then run this command to build image locally using this command ' docker build -t technikfda/tomcat . '  then to run the image in the local docker container 'docker run -p 8081:8080 techknikfda/tomcat'.  this will run this application in the docker container.
    
    
 
j. Feedback from user tests led to subsequent work and an updated prototype. (Ex. users suggested visual aesthetic enhancements such as displaying country flags in addition to the country names). The feature was incorporated in subsequent releases. We also used GitHub issues, labels and milestones as our collaboration platform. (artifact - sprint 0, sprint 1, sprint 2)

k. Steps to install and run prototype on another machine

**Local machine installation**
•	Install [Apache Tomcat 7.0]( https://tomcat.apache.org/tomcat-7.0-doc/deployer-howto.html) open-source web server on local machine
•	Manually deploy Web application Archive (WAR) file in Tomcat (use technikfda.war file in GitHub repository)
•	Restart Tomcat server

**Setup Continuous Integration (CI) using [Travis CI](https://www.travis-ci.org/) :**
The major CI steps include
•	Clone  or download [TechnikInc/openFDA-DevelopmentPrototype](https://github.com/TechnikInc/openFDA-DevelopmentPrototype)  GitHub repository  to create a new repository
•	From Travis CI activate the new GitHub Repository
•	Trigger the first build to Travis with a Git push
 
**Setup Continuous Deployment to [Amazon Web Services (AWS)](http://aws.amazon.com/codedeploy/) using AWS CodeDeploy:**
The major steps include
•	Create and provision applicable instances in AWS
•	Deploy application from GitHub to the applicable instances using AWS CodeDeploy
•	Edit .travis.yml file to point to the correct AWS instances and access keys 
•	Track and monitor the status of your deployments through [Amazon CloudWatch](http://aws.amazon.com/cloudwatch/) real-time monitoring tools.

l. The prototype and underlying platforms are openly licensed and free of charge using the MIT free software license.

----------
