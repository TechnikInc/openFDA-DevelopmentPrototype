[![Build Status](https://travis-ci.org/krishnachaganti/travis_test.svg?branch=master)](https://travis-ci.org/krishnachaganti/technikfda)

# OpenFDA Adverse Drug Event Reports by Country - [Technik, Inc.](http://www.technikinc.com) 

[Click here to view the prototype](http://ec2-54-175-24-134.compute-1.amazonaws.com:8080/technikfda/)

This prototype allows consumers to view adverse drug event reports by country via a simple and intuitive user interface. The [openFDA drug adverse event API](https://api.fda.gov/drug/event) was consumed [Restfully](https://en.wikipedia.org/wiki/Representational_state_transfer) to return data from the [FDA Adverse Event Reporting System (FAERS)](https://open.fda.gov/data/faers/) 


## Approach used to create Pool Two Development prototype

***We followed the U.S. Digital Services Playbook requirements***

a. Mr. Satish Venkatesan was the assigned leader (Product Manager) with authority, responsibility and accountability for the quality of the prototype submitted.

b. The Technik openFDA development team consisted of three resources assigned to the following Design Pool Labor categories from the Developement Pool Labor categories from 18F Agile Labor Categories. Attachment C is updated accordingly

 - Technical Architect - Manage the technical implementation of the prototype
 - Dev Ops  Engineer - Configure continuous integration, continuous deployment and configured operating system level virtualization
 - Front  End Web Developer - Developed the prototype using modern techniques and frameworks. The following modern and open-source technologies are applied to this effort

**Data Sources** 
 - Drug Adverse Event data from [openFDA    API](https://open.fda.gov/api/reference/)
 - Lightweight data packages from [data.okfn.org](http://data.okfn.org/) to translate country code to    country name
 - GeoNames geographical database from [geonames.org](http://www.geonames.org)  to retreive country flag images for the displayed country

**Programming languages**
 - [Java](https://en.wikipedia.org/wiki/Java_programming_language)
 - [Javascript](https://en.wikipedia.org/wiki/JavaScript) 
 - [HTML](https://en.wikipedia.org/wiki/HTML)

**Frameworks (Serverside)**
 - [Spring Web model-view-controller (MVC)](https://spring.io/guides/gs/serving-web-content/) Framework
 - [Jackson-databinding](https://github.com/FasterXML/jackson-databind/) package 

**Frameworks (Clientside)**
 - HTML Vocabulary Extension using [AngularJS](https://angularjs.org/)
 - [jQuery](https://jquery.com/) JavaScript library

**Testing tools**
 - [JUnit](http://junit.org/) as the framework to write repeatable tests
 - [Mockito](http://mockito.org/) mocking framework for creating JUnit tests 

**Build Tools**
 - [Gradle](https://gradle.org/) open-source build automation tool

**Version Tools**
 - Web-based Git repository and source code management (SCM) using [GitHub](https://github.com/)
 - [Git Shell](http://git-scm.com/docs/git-shell) - Login shell for Git-only SSH access

**Editor**
 - [Stackedit](https://stackedit.io/) Mark down editor


d. The prototype is deployed on [Amazon Web Services (AWS)](https://aws.amazon.com/?nc2=h_lg) Platform as a Service (PaaS).

e.  Unit tests are implemented using Mockito with Junit

f. [Travis Continous Integration System](https://travis-ci.org) is used to automate the running of tests and continuously deploy code to Amazon Web Services using [AWS CodeDeploy](http://aws.amazon.com/codedeploy/)

g. Used [Git](https://git-scm.com/) open source distributed version control system on [GitHub](https://github.com/) for configuration management

h. Used AWS contiunous monitoring dashboard
 
i. Used [Docker container](https://www.docker.com/), an open platform for to deploy the application and all of its dependencies.
 
j. Used an iterative approach, where feedback informed subsequent work or versions of the prototype

k. Write documentation on how to install an run prototype

l. The prototype and underlying platforms are openly licensed and free of charge using the MIT free software license.


----------
