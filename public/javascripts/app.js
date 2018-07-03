


  /*app.controller('listingCntrl',function($scope){
	  $scope.lookBusinessList=["Business","Commercial","Franchise"];
	  $scope.lookByValue=["business","commercial","franchise"];
  });*/
 
  var app = angular.module('tobuzApp', []);
  app.controller('listingCntrl', function($scope,$rootScope,$http) {

	  $scope.categoryList = [];
	  $scope.subCategoryList = [];
	  $scope.countryList = [];
	  $scope.stateList = [];
	  $scope.cityList = [];
	  $scope.mericsList = [];
	  $scope.categoryType = 'BUSINESS';
	  $http({
            method: 'GET',
            url: '/category-list/'+$scope.categoryType
        }).then(function (response) {
        	$scope.formListing.categoryList = response.data;
        });

        $scope.getCategoryDetails = function(categoryType){
		  $http({
	            method: 'GET',
	            url: '/category-list/'+categoryType
	        }).then(function (response) {
	        	$scope.categoryList = response.data;
	        });
	    }
	  
	  $scope.getSubCategories = function(category){
            angular.forEach(category, function(value, key) {
            	  var selectedCategoryId = value.id;
        		  $http({
        	            method: 'GET',
        	            url: '/sub-category-list/'+selectedCategoryId
        	        }).then(function (response) {
        	        	$scope.formListing.subCategoryList = response.data;
        	        });
            	});
	  }
	  
	  $scope.getBusinessAdvertData = function(){
		  $http({
	            method: 'GET',
	            url: '/country-data'
	        }).then(function (response) {
	        	$scope.formListing.countryList = response.data;
	        	  $http({
	  	            method: 'GET',
	  	            url: '/area-metrics'
	  	        }).then(function (arearesponse) {
	  	        	$scope.formListing.metricsList = arearesponse.data;
	  	        });
	        	
	        
	        });
	  }
	  
	  $scope.getStateData = function(country){
		  
		  var selectedCountryId = country.id;
		  $http({
	            method: 'GET',
	            url: '/state-data/'+selectedCountryId
	        }).then(function (response) {
	        	$scope.formListing.stateList = response.data;
	        });
	  }
	  
	  $scope.getCityData = function(state){
		  var selectedStateId = state.stateId;
		  $http({
	            method: 'GET',
	            url: '/city-data-by-state/'+selectedStateId
	        }).then(function (response) {
	        	$scope.formListing.cityList = response.data;
	        });
	  }
	  
	  
	 $scope.formSubmit = function click(){

         var data = $scope.formListing;
         var ref=JSON.stringify(data);
         console.log(data);
         $http({
             url: '/business-advert/add',
             method: "POST",
             data: ref
           }).then(function(response) {
             console.log(response)
             if (response.data.alertType == "SUCCESS") {
             	window.location.href = "/dashboard/finalListings";
             }else{
             	 new PNotify({
     	                title: "Error!",
     	                text: response.data.responseMessage,
     	                delay: 4000,
     	                type:"danger"
     	              });
             }
           }); 

     }    
 });
 
 app.controller('updateProfileCntrl', function($scope,$rootScope,$http) {
	 
	 // loggedin user data
	 	$scope.cities = [];
	 	$scope.countries = [];
	 	$scope.updatedProfile;
	   
	 	$scope.getUserUpdateData = function () {
 		  $http({
	            method: 'GET',
	            url: '/country-data'
	        }).then(function (countryResponse) {	
	        	$scope.countries = countryResponse.data;
	        	console.log($scope.countries);
		        $http({
		            method: 'GET',
		            url: '/user-data'
		        }).then(function (response) {
		            $scope.updatedProfile = response.data;
		        	$scope.updatedProfileId = response.data.cityId;	
		        	console.log($scope.updatedProfile.mobileNumber);
		            $http({
			            method: 'GET',
			            url: '/city-data'
			        }).then(function (response) {
			            console.log(response);
			            $scope.cities = response.data;
			            angular.forEach($scope.cities, function(value, key) {
			            	
			            		if(value.cityId == $scope.updatedProfileId){
			            			$scope.selectedCityId = value;
			            		}
			            	});
			        });
		        });
	    	});  
	        
	        
	    }
		 $scope.profileUpdate = function click(){
		     //var data=$("#listingForm").serialize();
		     var updateData = $scope.updatedProfile;
		     updateData.updImageId = $scope.updatedProfile.updImageId;
		     var modified=JSON.stringify(updateData);
		     console.log(modified);
		   $http({
		    url: '/update-user-profile',
		    method: "POST",
		    data: modified
		  }).then(function(response) {
		    console.log(response)
		    if (response.data.alertType == "SUCCESS") {
		    	
		    }else{
		    	 new PNotify({
		                title: "Error!",
		                text: response.data.responseMessage,
		                delay: 4000,
		                type:"danger"
		              });
		    }
		  }); 
     } 

      //the image
    if($scope.uploadme == null){
    	$scope.showProfileImage = false;
    }
    $scope.uploadme;

    $scope.showUpdImage = function(){
    	$scope.showProfileImage = true;
    	$scope.showConf = true;
    }

    $scope.uploadImage = function() {
    	$scope.showConf=false;
    	var fd = new FormData();
      	var imgBlob = dataURItoBlob($scope.uploadme);
      	fd.append('image', imgBlob);
      $http({
		    url: '/save-image/0',
		    method: "POST",
		    data: fd,
		    headers: {
              'Content-Type': undefined
            },
            transformRequest: angular.identity	
		  }).then(function(response) {
		    $scope.updatedProfile.updImageId = response.data.payLoad;
		   console.log($scope.updatedProfile.updImageId);
		  }); 
      
    }


    //you need this function to convert the dataURI
    function dataURItoBlob(dataURI) {
      var binary = atob(dataURI.split(',')[1]);
      var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
      var array = [];
      for (var i = 0; i < binary.length; i++) {
        array.push(binary.charCodeAt(i));
      }
      return new Blob([new Uint8Array(array)], {
        type: mimeString
      });
    } 
 });
 
 app.controller('sendMesgController', function($scope) {
	 $scope.sendMsg = function click(){
         //var data=$("#listingForm").serialize();
         var sendMsgDta = $scope.sendmessage;
         var modifiedDta=JSON.stringify(sendMsgDta);
         console.log(modifiedDta);

//      $http.get("welcome.htm")
//           .then(function(response) {
//               $scope.myWelcome = response.data;
//           });

     }    
 });

 
 

 app.controller('sendMesgController', function($scope) {
	 
	 
	 
		$scope.getUserUpdateData = function () {
			
			
			var json=
				{
					
					SavedSearch:[
						
						{},
						{}
					]
				}
			
			
			$scope.scopesearch=json.SavedSearch;
			
			
		}
	 
 });

	 

 //Directives

 app.directive("fileread", [
  function() {
    return {
      scope: {
        fileread: "="
      },
      link: function(scope, element, attributes) {
        element.bind("change", function(changeEvent) {
          var reader = new FileReader();
          reader.onload = function(loadEvent) {
            scope.$apply(function() {
              scope.fileread = loadEvent.target.result;
            });
          }
          reader.readAsDataURL(changeEvent.target.files[0]);
        });
      }
    }
  }
]);
