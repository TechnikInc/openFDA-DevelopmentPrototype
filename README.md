[![Build Status](https://travis-ci.org/krishnachaganti/travis_test.svg?branch=master)](https://travis-ci.org/krishnachaganti/technikfda)

# OpenFDA Adverse Drug Event Reports by Country - Technik Inc. 

[Click here to view the prototype](http://ec2-54-175-24-134.compute-1.amazonaws.com:8080/technikfda/)

This prototype allows consumers to view adverse drug event reports by country via a simple and intuitive user interface. The [openFDA drug adverse event API](https://api.fda.gov/drug/event) was consumed RESTfully used to return data from the [FDA Adverse Event Reporting System (FAERS)](https://open.fda.gov/data/faers/) 

## Approach used to create Pool Two Development prototype

***We followed the U.S. Digital Services Playbook requirements***

a. Mr. Satish Venkatesan was the assigned leader (Product Manager) with authority, responsibility and accountability for the quality of the prototype submitted.

b. The Technik openFDA development prototype team consisted of three resources assigned to the following Development Pool Labor categories from 18F Agile Labor Categories. Attachment C is updated accordingly. 
This rapid development effort project was developed and scaled using agile methodologies. The chosen labor categories are as follows: 

 - Technical Architect
 - Dev Ops  Engineer
 - Front  End Web Developer

c. The following modern and open-source technologies are applied to this effort

**Data** 
 - Drug Adverse Event data from [openFDA    API](https://open.fda.gov/api/reference/)
 - Lightweight data packages from [data.okfn.org](http://data.okfn.org/) to translate country code to    country name
- GeoNames geographical database from [geonames.org](http://www.geonames.org)  to retreive country flag images for the displayed country

Programming languages
 - Java
 - Javascript
 - HTML 

Frameworks (Serverside)
 - Spring
 - Jackson-databind 

Frameworks (Clientside)
 - Angular JS
 - jQuery
Testing tools
 - jUnit
 - Mokito

Build Tools
 - Gradle

Version Tools
 - GitHub, 
 - Git shell




d. The following three collaborative human-centered design techniques and tools supported the development of this prototype with an agile process to determine user needs: See "Evidence" folder in GitHub Repository for artifacts. 
- Conducted user interviews to identify audience personas and representative use cases (contextual inquiry). (artifact-Technik_18F_brainstrom.jpeg) 
-  Applied white-boarding and card sorting techniques to identify possible layout/flow approaches. (artifacts-Technik_18F_userstory.docx, Technik_18F_cardeck.jpeg) 
-  Used Balsamiq Mockups to create wireframes of web and mobile flows for user review. (artifact-Technik_18F_balsamiq.jpeg) 
- Performed usability testing with rapid iteration of working prototypes, adding and validating enhancements requested by users. (artifact-Technik_18F_testinglog.xls), (artifact-Technik_18F_SystemUsability_Responses.xls)

e. Standard, responsive components and layouts from the Bootstrap UI library were used to create the interface


f. In addition to openFDA APIs, several open source libraries were used to power this prototype.
 - [jQuery](https://jquery.com/) for general JavaScript utilities and
   JSON manipulation
 -  [HTML5   Boilerplate](https://github.com/h5bp/html5-boilerplate) for basic responsive web template 
 - [Bootstrap](http://getbootstrap.com/) for basic web components
 - [Bootstrap-Table](https://github.com/wenzhixin/bootstrap-table) for table generation from JSON data and frozen table headers
 - [jQuery Bing Search](http://cbenard.github.io/jquery-bingsearch/) for authenticated Bing image searches using the Windows Azure Marketplace Bing Search API

g. Two people tested the prototype, validated the acceptance criteria and provided feedback for improvement. 
An independent group not associated with prototype development completed usability tests and provided feedback. (artifact -Technik_18F_SystemUsability_Responses). See artifact in the "evidence folder" in GitHub repository.

h. Feedback from the tests led to subsequent work and an updated prototype. (Ex. users requested visual images for search results). - A gallery that displays product images from Microsoft's Bing search API was implemented to facilitate rapid recognition. Used GitHub issues, labels and milestones for collaboration.
 
i. This prototype was tested successfully in Windows (Internet Explorer, Chrome, Firefox), Mac OS (Safari, Chrome, Firefox), and on the iPhone and iPad (Safari) and on Android phones and tablets (Chrome). The prototype uses a responsive layout that reduces the amount of data shown on-screen at a given time on mobile devices. (artifacts - Technik_18F_Iphone.jpeg, Technik_18F_android.jpeg, Technik_18F_IE.jpeg, Technik_18F_Chrome.jpeg, Technik_18F_Firefox.jpeg)
 
j. Note: This prototype requires a browser that supports File API and Indexed Database API, such as Internet Explorer 10 or later, or recent versions of Chrome or Firefox. For security reasons, Indexed Database API only works through the http, https, ms-wwa, or ms-wwa-web protocols in Windows Internet Explorer. 

To view in IE 10 or later: Simply clone this repository or download the ZIP file and deploy the project on a web server (e.g. IIS, Apache). Navigate to index.html to use the prototype.
To view in Chrome or Firefox – Simply download the zip file on to the local machine and launch index.html

k. The prototype and underlying platforms used to create and run the prototype are openly licensed and free of charge using the MIT free software license.


----------
