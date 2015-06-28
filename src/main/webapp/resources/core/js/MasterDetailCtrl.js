var technikFDAApp = angular.module('technikFDAApp', []);

//  Force AngularJS to call our JSON Web Service with a 'GET' rather than an 'OPTION'
//  Taken from: http://better-inter.net/enabling-cors-in-angular-js/
technikFDAApp.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
}]);

technikFDAApp.filter('sumByKey', function () {
    return function (data, key) {
        if (typeof (data) === 'undefined' || typeof (key) === 'undefined') {
            return 0;
        }
        var sum = 0;
        for (var i = data.length - 1; i >= 0; i--) {
            sum += parseInt(data[i][key]);
        }
        return sum;
    };
})

technikFDAApp.filter('customSum', function () {
    return function (listOfProducts, key) {
        //  Count how many items are in this order
        var total = 0;
        angular.forEach(listOfProducts, function (product) {
            //            alert(product + "." + key);
            total += eval("product." + key);
        });
        return total;
    }
});

technikFDAApp.filter('countItemsInOrder', function () {
    return function (listOfProducts) {
        //  Count how many items are in this order
        var total = 0;
        angular.forEach(listOfProducts, function (product) {
            total += product.Quantity;
        });
        return total;
    }
});

technikFDAApp.filter('orderTotal', function () {
    return function (listOfProducts) {
        //  Calculate the total value of a particular Order
        var total = 0;
        angular.forEach(listOfProducts, function (product) {
            total += product.Quantity * product.UnitPrice;
        });
        return total;
    }
});

technikFDAApp.controller('MasterDetailCtrl',
function ($scope, $http, $location) {

	$scope.currentServer = $location.protocol()+"://"+$location.host()+":"+$location.port();
    //  We'll load our list of Countries from our JSON Web Service into this variable
    $scope.listOfCountrys = null;
    

    //  When the user selects a "Country" from our MasterView list, we'll set the following variable.
    $scope.selectedCountry = null;

    $http.get('/technikfda/query/countries')

        .success(function (data) {
            $scope.countries = data;

            if ($scope.countries.length > 0) {

                //  If we managed to load more than one Country record, then select the first record by default.
                //  This line of code also prevents AngularJS from adding a "blank" <option> record in our drop down list
                //  (to cater for the blank value it'd find in the "selectedCountry" variable)
                $scope.selectedCountry = $scope.countries[0].term;

                //  Load the list of Orders, and their Products, that this Country has ever made.
                $scope.loadIncidents();
            }
        })
        .error(function (data, status, headers, config) {
            $scope.errorMessage = "Couldn't load the list of Countrys, error # " + status;
        });

    $scope.selectCountry = function (val) {
        //  If the user clicks on a <div>, we can get the ng-click to call this function, to set a new selected Country.
        $scope.selectedCountry = val.term;
        $scope.loadIncidents();
        $scope.changeDrugCharacterInfo();
    }
    
    $scope.getDrugCharacter = function(value){
       //alert("Value :"+value);
    	switch(value)
    	{
	    	case "1":
	    		 return 'Suspect drug';
	    	case "2":
	    		return 'Concomitant drug';
	    	case "3":
	    		return 'Interacting drug';
	    	default: 
	    		return 'UNKNOWN';
    	}
    }

    $scope.loadIncidents = function () {
        //  Reset our list of Incidents  (when binded, this'll ensure the previous list of incidents disappears from the screen while we're loading our JSON data)
        $scope.listOfIncidents = null;
        var limit = 10;
        var skip = 20;
        var currentQuery = '/technikfda/query/countryCode/'+$scope.selectedCountry+'/limit/'+limit+'/skip/'+skip;
        //  The user has selected a Country from our Drop Down List.  Let's load this country's records.
        $http.get(currentQuery)
                .success(function (data) {
                    $scope.listOfIncidents = data.results;
                })
                .error(function (data, status, headers, config) {
                    $scope.errorMessage = "Couldn't load the list of Incidents, error # " + status;
                });        
    }
});
