var baseSearchURL = "https://api.fda.gov/drug/label.json?";
var limit = 100;
var openFDAKey = "uN2mlhVgIcwgvh8FrfmpT5f7U65RGxnrtrZCxInc"; /* API key for openFDA queries */

var searchURL = baseSearchURL;
searchURL += ("api_key=" + openFDAKey);
searchURL += ("&limit=" + limit);
searchURL += "&search=brand_name:";

var drugs = new Bloodhound({
    datumTokenizer: function (datum) {
        return Bloodhound.tokenizers.whitespace(datum.value);
    },
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    remote: {
        url: searchURL + "%QUERY",
        filter: function (drugs) {
            // Map the remote source JSON array to a JavaScript object array
            return $.map(drugs.results, function (drug) {
                return {
                    value: drug.openfda.brand_name
                };
            });
        },
        wildcard: "%QUERY"
    }
});

 
function initiateTypeAhead(){
  $('#search_drug').typeahead({
      hint: false,
      minLength: 2 }, {
      displayKey: 'value',
      source: drugs.ttAdapter(),
  });
// Initialize the Bloodhound suggestion engine
drugs.initialize();
}