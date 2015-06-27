<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html ng-app="technikFDAApp">
  <head>
    <meta charset="utf-8">
    <title>OpenFDA Adverse Drug Event Viewer by Technik Inc.</title>
    <spring:url value="/resources/core/css/MasterDetails.css" var="masterDetailCss" />
  	<spring:url value="/resources/core/js/MasterDetailCtrl.js" var="masterDetailCtrl" />	
  	<spring:url value="/resources/core/images/icnOffice.png" var="icnOfficImage" />
  	<link href="${masterDetailCss}" rel="stylesheet" />
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,600,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="./resources/core/css/normalize.min.css">
    <link rel="stylesheet" href="./resources/core/css/main.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="./resources/core/images/favicon.png">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
    <script src="${masterDetailCtrl}" type="text/javascript"></script>
  </head>
  <body>
        <div class="header-container">
            <header class="wrapper clearfix">
                <img src="./resources/core/images/openFDA.png" class="header_logo" alt="openFDA Logo" />
            </header>
        </div>
        <div class="main-container">
            <div class="main wrapper clearfix">
                <div 	id="divMasterDetailWrapper" 
                		ng-controller="MasterDetailCtrl" 
                		style="position:relative;">
                    <div style="left:-30px;top:-1px;position:absolute;">
                    </div>
                    <div id="divMasterView" >
                       <div id="{{country.term}}" 
                       			class="cssOneCompanyRecord" 
                       			ng-class="{cssCompanySelectedRecord: country.term == selectedCountry}" 
                       			ng-repeat="country in countries" 
                       			ng-click="selectCountry(country);">
                            <div class="cssCompanyName">{{country.term.toUpperCase()}} - {{country.countryName}}</div>
                            <div class="cssCompanyCity">{{country.count}}</div>
                            <img src="{{country.imageSrc}}"
                               width="16" height="16"
                               title="This is a tooltip for the country: {{country.countryName}}" 
                               class="cssCustomerIcon" />
                        </div>
                    </div>
                    <div id="divDetailView">
                        <div 	id="Patient Reaction_{{incident.receiptdate}}" 
                        		ng-repeat="incident in listOfIncidents" 
                        		class="cssOneOrderRecord">
                            <div class="cssOneOrderHeader">
                                <div class="cssOrderID">Safety Report # {{incident.safetyreportid}}</div>
                                <div class="cssOrderDate">Incident Receipt Date: {{incident.receiptdate}}</div>
                            </div>
                            <div class="cssOneProductRecord" 
                            	ng-repeat='reaction in incident.patient.reaction' 
                            	ng-class-odd="'cssProductOdd'" 
                            	ng-class-even="'cssProductEven'">
                                <div class="cssOneProductQty">{{reaction.reactionmeddrapt}}</div>
                                <div class="cssOneProductName">{{reaction.reactionmeddraversionpt}}</div>
                                <div class="cssOneProductPrice">{{reaction.reactionoutcome}}</div>
                            </div>   
                            <div class="cssOneProductRecord" 
                            	ng-repeat='drug in incident.patient.drug' 
                            	ng-class-odd="'cssProductOdd'" 
                            	ng-class-even="'cssProductEven'">
                                <div class="cssOneProductQty">{{drug.medicinalproduct}}</div>
                                <div class="cssOneProductName">{{drug.drugindication}}</div>
                                <div class="cssOneProductPrice">{{drug.drugdosageform}}</div>
                            </div>
                        </div>
                    </div>
                       
                </div>
            </div>
        </div>
  </body>
</html>