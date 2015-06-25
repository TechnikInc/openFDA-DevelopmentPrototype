<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html ng-app="technikFDAApp">
  <head>
    <meta charset="utf-8">
    <title>FDA/Technik, Inc Data Fusion Example</title>
    <spring:url value="/resources/core/css/MasterDetails.css" var="masterDetailCss" />
	<spring:url value="/resources/core/js/MasterDetailCtrl.js" var="masterDetailCtrl" />	
	<spring:url value="/resources/core/images/icnOffice.png" var="icnOfficImage" />
	<link href="${masterDetailCss}" rel="stylesheet" />
     <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
     <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
     <script src="${masterDetailCtrl}" type="text/javascript"></script>
  </head>
  <body>
	  <h1>Technik - FDA Incidents  by Country View</h1>
    <br />
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
  </body>
</html>