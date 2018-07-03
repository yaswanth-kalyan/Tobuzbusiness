/*var favoritesApp = angular.module('favoritesApp', []); yaswanthkalyan*/
app.controller('favoritesController', function($scope, $rootScope, $http) {
	
	 $scope.currentPage = 0;
	    $scope.pageSize = 4;
	    $scope.data = $scope.sellerList;
	    $scope.numberOfPages=function(){
	        return Math.ceil($scope.data.length/$scope.pageSize);                
	    }
	
	
	$scope.getBusRoutes = function() {

		console.log("kalyan");
		// alert("yaswanthkalyan");

		var json = {

			"Name" : "yaswanthkalyan",
			"Favourites" : "70",
			"Listings" : "103",
			"SavedSearch" : "23",
			"Messages" : "08",
			buyersList : [

			{    "id" :"1",
				"status" : "new",
				"name" : "Dickwella Resort1 &amp; Spa",
				"description" : "Relax, explore and indulge with a package1",
				"listings_rate" : "INR 1,00,000",
				"likes" : 10
			},

			{
				
				"id" :"2",
				"status" : "old",
				"name" : "Dickwella Resort2 &amp; Spa",

				"description" : "Relax, explore and indulge with a package2",
				"listings_rate" : "INR 2,00,000",
				"likes" : 20
			},
			{
				
				"id" :"3",
				"status" : "old",
				"name" : "Dickwella Resort3 &amp; Spa",

				"description" : "Relax, explore and indulge with a package3",
				"listings_rate" : "INR 3,00,000",
				"likes" : 30
			},

			{
				
				"id" :"4",
				"status" : "old",
				"name" : "Dickwella Resort4 &amp; Spa",

				"description" : "Relax, explore and indulge with a package4",
				"listings_rate" : "INR 4,00,000",
				"likes" : 40
			},
			{
				
				"id" :"5",
				"name" : "Dickwella Resort5 &amp; Spa",
				"status" : "old",
				"description" : "Relax, explore and indulge with a package5",
				"listings_rate" : "INR 5,00,000",
				"likes" : 50
			},

			{
				
			     "id":"6",
				"name" : "Dickwella Resort6 &amp; Spa",
				"status" : "new",
				"description" : "Relax, explore and indulge with a package6",
				"listings_rate" : "INR 6,00,000",
				"likes" : 60
			},

			{    "id":"7",
				"name" : "Dickwella Resort7 &amp; Spa",
				"status" : "new",
				"description" : "Relax, explore and indulge with a package7",
				"listings_rate" : "INR 7,00,000",
				"likes" : 70
			},

			{   "id":"8",
				"name" : "Dickwella Resort8 &amp; Spa",
				"status" : "new",
				"description" : "Relax, explore and indulge with a package8",
				"listings_rate" : "INR 8,00,000",
				"likes" : 50
			}
			

			],
			
			sellersList: [
				
				
				
				
				{    "id" :"1",
					"status" : "new",
					"name" : "Dickwella Resort1 &amp; Spa",
					"description" : "Relax, explore and indulge with a package1",
					"listings_rate" : "INR 1,00,000",
					"likes" : 10
				},

				{
					
					"id" :"2",
					"status" : "new",
					"name" : "Dickwella Resort2 &amp; Spa",

					"description" : "Relax, explore and indulge with a package2",
					"listings_rate" : "INR 2,00,000",
					"likes" : 20
				},
				{
					
					"id" :"3",
					"status" : "hotitem",
					"name" : "Dickwella Resort3 &amp; Spa",

					"description" : "Relax, explore and indulge with a package3",
					"listings_rate" : "INR 3,00,000",
					"likes" : 30
				},

				{
					
					"id" :"4",
					"status" : "hotitem",
					"name" : "Dickwella Resort4 &amp; Spa",

					"description" : "Relax, explore and indulge with a package4",
					"listings_rate" : "INR 4,00,000",
					"likes" : 40
				},
				{
					
					"id" :"5",
					"name" : "Dickwella Resort5 &amp; Spa",
					"status" : "hotitem",
					"description" : "Relax, explore and indulge with a package5",
					"listings_rate" : "INR 5,00,000",
					"likes" : 50
				},

				{
					
				     "id":"6",
					"name" : "Dickwella Resort6 &amp; Spa",
					"status" : "new",
					"description" : "Relax, explore and indulge with a package6",
					"listings_rate" : "INR 6,00,000",
					"likes" : 60
				},

				{    "id":"7",
					"name" : "Dickwella Resort7 &amp; Spa",
					"status" : "new",
					"description" : "Relax, explore and indulge with a package7",
					"listings_rate" : "INR 7,00,000",
					"likes" : 70
				},

				{   "id":"8",
					"name" : "Dickwella Resort8 &amp; Spa",
					"status" : "new",
					"description" : "Relax, explore and indulge with a package8",
					"listings_rate" : "INR 8,00,000",
					"likes" : 50
				},
				
				{   "id":"9",
					"name" : "Dickwella Resort9 &amp; Spa",
					"status" : "hotitem",
					"description" : "Relax, explore and indulge with a package9",
					"listings_rate" : "INR 9,00,000",
					"likes" : 590
				}
				
				
				
				
				
			]

		};
		$scope.users = json;

		console.log($scope.users);

		$scope.buyerList = json.buyersList;

		console.log($scope.buyerList);
		
		
		
		$scope.sellerList = json.sellersList;

		console.log($scope.sellerList);

	}
	
	
	
	
	
	$scope.getBuyerList = function(buyer) {
		
		console.log(buyer);
		$scope.testBuyer=buyer;
		
	}
	
	
	
	
	
	
	
});

app.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
});