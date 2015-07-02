<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html ng-app="technikFDAApp">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>OpenFDA Adverse Drug Event Viewer by Technik Inc.</title>
    <spring:url value="/resources/core/css/MasterDetails.css" var="masterDetailCss" />	
  	<spring:url value="/resources/core/images/icnOffice.png" var="icnOfficImage" />
    <link href='https://fonts.googleapis.com/css?family=Raleway:400,600,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="./resources/core/css/normalize.min.css">
    <link rel="stylesheet" href="./resources/core/css/main.css">
    <link rel="stylesheet" href="/technikfda/resources/core/js/chart/angular/angular-chart.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link href="${masterDetailCss}" rel="stylesheet" />
    <link rel="shortcut icon" href="./resources/core/images/favicon.png">
    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!--  angular scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
    <!--  chart.js -->
    <script src="/technikfda/resources/core/js/chart/Chart.js"></script>
    <!--  angular plugs to chart.js -->
    <script src="/technikfda/resources/core/js/chart/angular/angular-chart.js"></script>
    <script src="./resources/core/js/typeahead.js"></script>
    <script src="./resources/core/js/drugTypeAhead.js"></script>
    <script src="/technikfda/resources/core/js/MasterDetailCtrl.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function (){
    })
    </script>
  </head>
  <body>
        <div class="header-container">
            <header class="wrapper clearfix">
                <img src="./resources/core/images/openFDA.png" class="header_logo" alt="openFDA Logo" />
            </header>
        </div>
        <div class="main-container">
            <div class="main wrapper clearfix">
                <div class="panel panel-default">
                    <div class="panel-heading">Adverse Drug Event Reports by Country</div>
                    <div class="panel-body">
                        <div class="search_bar">
                            <form class="form-inline">
                              <div class="form-group">
                                <label class="sr-only" for="search_drug">Drug Name</label>
                                <input ng-model="searchDrugField" type="text" 
                                	class="form-control" 
                                	id="search_drug" 
                                	placeholder="Drug name (e.g. aspirin)" 
                                	value="Aspirin" 
                                	autocomplete="off">
                              </div>
                              <!--button type="submit" class="btn btn-default">Search</button-->
                            </form>
                        </div>
                        <div 	id="divMasterDetailWrapper" 
                        		ng-controller="MasterDetailCtrl" 
                        		style="position:relative;">
                            <div id="divMasterView" >
                               <div id="{{country.term}}" 
                               			class="cssOneCompanyRecord" 
                               			ng-class="{cssCompanySelectedRecord: country == selectedCountry}" 
                               			ng-repeat="country in countries" 
                               			ng-click="selectCountry(country);">
                                    <div class="cssCompanyName">{{country.countryName}}</div>
                                     <img src="{{country.imageSrc}}"
                                       title="{{country.countryName}}" 
                                       class="cssCustomerIcon" />
                                </div>
                            </div>
                            <div id="divDetailView">
                              <h3>Adverse events involving {{searchDrugField}} in {{selectedCountry.countryName}}</h3>
                              <section  ng-controller="DoughnutCtrl">
  	                            <canvas id="doughnut" 
  	                            		class="chart chart-doughnut" 
  	                            		data="data"
  	                            		legend="true"
  	  						 			labels="seriousIncidentLabels">
  	  							             </canvas>
            	  							</section>
                              <div class="disclaimer">
                                Disclaimer: Users are cautioned to not draw conclusions about products based solely on adverse event information. There is no certainty that the reported event was actually caused by the product. The report merely indicates that the patient was taking the medication at the time of the adverse event. Reports do not contain enough detail to fully evaluate an event.
                              </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-container">
            <footer class="wrapper">
                <img src="./resources/core/images/technik_logo.png" alt="Technik Logo" class="technik_logo" />
                <p>
                  Designed & developed by <a href="http://www.technikinc.com" target="_blank">Technik, Inc.</a>
                </p>
                <p>
                    12950 Worldgate Drive, Suite 230, Herndon, VA 20170
                </p>
                <p>
                    Data from <a href="https://open.fda.gov" target="_blank">openFDA</a>
                </p>
                <p ng-controller="MasterDetailCtrl">
					<span> Dataset Date: {{fdaDatasetDate}}</span>
                </p>
            </footer>
            
        </div>
  </body>
</html>