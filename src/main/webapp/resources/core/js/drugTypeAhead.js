var baseSearchURL = "https://api.fda.gov/drug/event.json?count=patient.drug.medicinalproduct.exact&limit=1000";
var limit = 1000;
var openFDAKey = "uN2mlhVgIcwgvh8FrfmpT5f7U65RGxnrtrZCxInc"; /* API key for openFDA queries */

var searchURL = baseSearchURL;
searchURL += ("api_key=" + openFDAKey);
searchURL += ("&limit=" + limit);

var drugList=[];

var substringMatcher = function(strs) {
  return function findMatches(q, cb) {
    var matches, substringRegex;
    // an array that will be populated with substring matches
    matches = [];
    // regex used to determine if a string contains the substring `q`
    substrRegex = new RegExp(q, 'i');
    // iterate through the pool of strings and for any string that
    // contains the substring `q`, add it to the `matches` array
    $.each(strs, function(i, str) {
      if (substrRegex.test(str)) {
        matches.push(str);
      }
    });
    cb(matches);
  };
};

$.getJSON(searchURL, {})
.done(function(data) {
      $.each(data.results, function(i, result){
        drugList.push(result.term);
      });
      initiateTypeAhead();
});

function initiateTypeAhead(){
  var ta = $('#search_drug').typeahead({
              hint: false,
              minLength: 1},
              {
              name: "drugs",
              source: substringMatcher(drugList)
          })
}