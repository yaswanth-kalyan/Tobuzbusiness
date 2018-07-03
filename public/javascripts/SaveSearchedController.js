app.controller('saveSearchedController', function($scope) {

	$scope.getSaveSearched = function() {
		console.log("dataaaaa-------")

		var json = {

			saveSearch : [

			{
				"id":1,
				"name" : "Finance,Insurance,Legal,Construction & Real Estate-1",
				"location" : "Kurnool ,AP"

			}, 
			{
				"id":2,
				"name" : "Finance,Insurance,Legal,Construction & Real Estate-2",
				"location" : "Nandyal ,AP"

			}, 
			{
				"id":3,
				"name" : "Finance,Insurance,Legal,Construction & Real Estate-3",
				"location" : "Kadapa ,AP"

			},
			{
				"id":4,
				"name" : "Finance,Insurance,Legal,Construction & Real Estate-4",
				"location" : "Gunter ,AP"

			}, 
			{
				"id":5,
				"name" : "Finance,Insurance,Legal,Construction & Real Estate-5",
				"location" : "Vijayawada ,AP"

			},
			{
				"id":6,
				"name" : "Finance,Insurance,Legal,Construction & Real Estate-6",
				"location" : "Vizag ,AP"

			},
			{
				"id":7,
				"name" : "Finance,Insurance,Legal,Construction & Real Estate-7",
				"location" : "Thirupathi ,AP"

			},
			{
				"id":8,
				"name" : "Finance,Insurance,Legal,Construction & Real Estate-8",
				"location" : "Amaravathi ,AP"

			} ]
	
		}

		$scope.scopesearch = json.saveSearch;
		console.log($scope.scopesearch);
	}

});
