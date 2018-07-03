
var app = angular.module("tobuzApp", []);
app.controller("myCtrl", function($scope) {
	$scope.records = [
    {
    	
    	"Name" : "Ameliea Cammmer1",
    	"message" : "Did you talk to Mark1 ?",
    	"msgs":[ "abs", "def", "ghi" ],
    	"Mymsgs":[ "111", "222", "3333","4444" ]
    },
    {
    	
    	"Name" : "Ameliea Cammmer2",
    	"message" : "Did you talk to Mark2 ?",
    	"msgs":[ "fddf", "32424", "543546" ],
    	"Mymsgs":[ "aaa", "bbb", "ccc" ]
    },
    {
    	
    	"Name" : "Ameliea Cammmer3",
    	"message" : "Did you talk to Mark3 ?",
    	"msgs":[ "rssss", "rrrr", "kkkk" ],
    	"Mymsgs":[ "xxxx", "yyy", "zzzz" ]
    },
    {
    	
    	"Name" : "Ameliea Cammmer4",
    	"message" : "Did you talk to Mark4 ?",
    	"msgs":[ "ssss", "fsdgsdfffffsdfyfhsdtgf", "s44444444dg" ],
    	"Mymsgs":[ "4444", "66666", "8888","55555555" ]
    },
    {
    	
    	"Name" : "Ameliea Cammmer5",
        "message" : "Did you talk to Mark5 ?",
        "msgs":[ "sagdhgshdfsff", "fsdgfsdfdsyfhsdtgf", "sdfsdghfdsfdfhsdg" ],
        "Mymsgs":[ "888", "9999", "00000" ]
    },
    {
    	
    	"Name" : "Ameliea Cammmer6",
        "message" : "Did you talk to Mark6 ?",
        "msgs":[ "sagddfdhgshf", "fsdgyfhsdfgdgdtgf", "sdfsdgdfgdfgghfhsdg" ],
        "Mymsgs":[ "@@@@@@@@@@@@@", "**********************", "&&&&&&&&&&&&&&&&&&&&&&&&" ]
    }
  ]
	$scope.usrName;
	$scope.count;
	$scope.len;
	$scope.newmsg;
	$scope.userMessage = function(index) {
	$scope.usrName=$scope.records[index].Name;
	count=index;
    $scope.obj = $scope.records[index];
      
  }
	$scope.sendMessage=function(chat){
		if(typeof(chat)=="undefined"||$scope.chat=="")
			{
				alert("Enter Messages to send..")
				return;
			}
		else{
			$scope.len=$scope.records[count].Mymsgs.length;
			
			$scope.records[count].Mymsgs[$scope.len]=chat;
			
			
			var myEl = angular.element( document.querySelector( '#divID' ) );
			myEl.append('<div class="left_clip_patth" ><div class="flipped_text" >'+chat+'</div></div>')    
			
			
			
			console.log($scope.records[count].Mymsgs);
			
		}
		
		
	}
});





var isLogin = true;
var userRole = "";

$(function(){
	var countryIsoCode = "";
	var offset = new Date().getTimezoneOffset()
	jQuery.getScript('http://www.geoplugin.net/javascript.gp', function()
		    {
		countryIsoCode = geoplugin_countryCode();
		$.cookie("isocode", countryIsoCode);
		$.cookie("offset", offset);
		$('#mobileDerivedCountryId option[data-iso="'+countryIsoCode+'"]').attr("selected","selected");
//		        $.get('/country-data?isoCode='+countryIsoCode,function(response){
//		    		console.log(response);
//		    		});

		    });

});

function register(role){
	userRole = role;
	$(document).find('#register-userRole').val(role);
	$('#register').modal('show');

	isLogin = false;
}
$(document).on('click','#login',function(){
	isLogin = true;
	$('#signin').modal('show');
});

function socialRegister(receivedEmail,receivedName,reveivedLoginType){
  $.post('/register',{email:receivedEmail,name:receivedName,loginType:reveivedLoginType,role:userRole},function(response){
	  isLogin = true;
    console.log(response);
    var obj = response;
		if(obj.alertType == "SUCCESS"){
			$('#register').modal('hide');
		 $(document).find("#address-userId").val(obj.payLoad.userId);
			$('#address').modal('show');
		}else{
			new PNotify({
							title: "Error!",
							text: obj.responseMessage,
							delay: 4000,
							type:"danger"
						});
		}
  });
}
// function socialregister(receivedEmail,receivedName,loginFrom){
// 	$.ajax({
// 			type: 'POST',
// 			url: "/register",
// 			data: {email:receivedEmail,name:receivedName,loginFrom:reveivedLoginType},
// 			dataType: "text",
// 			success: function(resultData) {
// 				var obj = jQuery.parseJSON( resultData);
// 				if(obj.alertType == "SUCCESS"){
// 					$('#register').modal('hide');
// 				 $(document).find("#address-userId").val(obj.payLoad.userId);
// 					$('#address').modal('show');
// 				}else{
// 					new PNotify({
// 									title: "Error!",
// 									text: obj.responseMessage,
// 									delay: 4000,
// 									type:"danger"
// 								});
// 				}
// 			}
// });
// }

$(document).on('click','.btn-register',function(){

    // Use Ajax to submit form data
    $.ajax({
	      type: 'POST',
	      url: "/register",
	      data: $('#registerform').serialize(),
	      dataType: "text",
	      success: function(resultData) {
	    	  var obj = jQuery.parseJSON( resultData);
	    	  if(obj.alertType == "SUCCESS"){
	    		  $('#register').modal('hide');
	    		 $(document).find("#address-userId").val(obj.payLoad.userId);
	    		  $('#address').modal('show');
	    	  }else{
	    		  new PNotify({
	 	                title: "Error!",
	 	                text: obj.responseMessage,
	 	                delay: 4000,
	 	                type:"danger"
	 	              });
	    	  }
	      }
	});
});

$(document).on('click','.btn-address',function(){

    // Use Ajax to submit form data
    $.ajax({
	      type: 'POST',
	      url: "/update-location",
	      data: $('#addressForm').serialize(),
	      dataType: "text",
	      success: function(resultData) {
	    	  resultData = jQuery.parseJSON( resultData);
	    	   if(resultData.alertType == "SUCCESS"){
	    		   $('#address').modal('hide');
	    		   window.location.href='/packages';
	    	   }else{
	    		   new PNotify({
	 	                 title: "Error!",
	 	                 text: resultData.responseMessage,
	 	                 delay: 4000,
	 	                 type:"danger"
	 	               });
	    	   }
					
	      }
	});
});

$(document).on('click','.btn-login',function(){

    // Use Ajax to submit form data
    $.ajax({
	      type: 'POST',
	      url: "/login",
	      data: $('#loginform').serialize(),
	      dataType: "text",
	      success: function(resultData) {
	    	   resultData = jQuery.parseJSON( resultData);
	    	  console.log(resultData)
	    	  if(resultData.alertType == "SUCCESS"){
	    		  $('#address').modal('hide');
	    		  if(resultData.payLoad.isLocationUpdated){
	    			  window.location.href="/dashboard/home";
	    		  }else{
	    			  $('#login').modal('hide');
	    			  $(document).find("#address-userId").val(resultData.payLoad.userId);
		    		  $('#address').modal('show');
	    		  }
	    	  }else{
	    		  new PNotify({
	 	                title: "Error!",
	 	                text: resultData.responseMessage,
	 	                delay: 4000,
	 	                type:"danger"
	 	              });
	    	  }
	      }
	});
});
$(document).on('change','#address-city',function(){
	if($(this).val()){
		var selectedCityId = $(this).val();
		var stateId = $('#address-city-state'+selectedCityId).attr('data-state-id');
		var stateName = $('#address-city-state'+selectedCityId).attr('data-state-name');
		var countryId = $('#address-city-country'+selectedCityId).attr('data-country-id');
		var countryName = $('#address-city-country'+selectedCityId).attr('data-country-name');
		$(document).find('#address-sate-name').val(stateName).attr('readonly','readonly');
		$(document).find('#address-sate-id').val(stateId).attr('readonly','readonly');
		$(document).find('#address-Country-name').val(countryName).attr('readonly','readonly');
		$(document).find('#address-Country-id').val(countryId).attr('readonly','readonly');
		//console.log(stateId+" "+stateName+" "+countryId+"  "+countryName);
	}
});

$("#login,#nav_Buyer,#nav_Seller").on("click", function() {
     $(window).scrollTop(0);
});

function statusChangeCallback(response) {
  console.log('statusChangeCallback');
  console.log(response);
  // The response object is returned with a status field that lets the
  // app know the current login status of the person.
  // Full docs on the response object can be found in the documentation
  // for FB.getLoginStatus().
  if (response.status === 'connected') {
    // Logged into your app and Facebook.
    testAPI();
  } else {
    // The person is not logged into your app or we are unable to tell.
    document.getElementById('status').innerHTML = 'Please log ' +
      'into this app.';
  }
}

// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });
}

window.fbAsyncInit = function() {
FB.init({
  appId      : '121980711810260',
  cookie     : true,  // enable cookies to allow the server to access
                      // the session
  xfbml      : true,  // parse social plugins on this page
  version    : 'v2.10' // use graph api version 2.8
});

// Now that we've initialized the JavaScript SDK, we call
// FB.getLoginStatus().  This function gets the state of the
// person visiting this page and can return one of three states to
// the callback you provide.  They can be:
//
// 1. Logged into your app ('connected')
// 2. Logged into Facebook, but not your app ('not_authorized')
// 3. Not logged into Facebook and can't tell if they are logged into
//    your app or not.
//
// These three cases are handled in the callback function.

//FB.getLoginStatus(function(response) {
//  statusChangeCallback(response);
//});

};

// Load the SDK asynchronously
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.
function testAPI() {
  console.log('Welcome!  Fetching your information.... ');
  FB.api('/me', 'GET', {fields: 'first_name,last_name,name,id,email'}, function(response) {
            console.log(response);
            // $("#login-username").val(response.email);
            //$("#login-password").val()
            document.getElementById('status').innerHTML =
            'Thanks for logging in, ' + response.name+ '!';

            if(isLogin){
            	isLogin = false;
            socialLogin(response.email,'FACEBOOK');
            }else{
			socialRegister(response.email,response.name,'FACEBOOK');
            }
        });
      // document.getElementById('status').innerHTML =
      // 'Thanks for logging in, ' + response.name+ '!';

}
function socialLogin(receivedEmail,reveivedLoginType){
  $.post('/login',{email:receivedEmail,loginFrom:reveivedLoginType},function(resultData){
	  if(resultData.alertType == "SUCCESS"){
		  $('#address').modal('hide');
		  if(resultData.payLoad.isLocationUpdated){
			  window.location.href="/dashboard/home";
		  }else{
			  $('#login').modal('hide');
			  $(document).find("#address-userId").val(resultData.payLoad.userId);
    		  $('#address').modal('show');
		  }
	  }else{
		  new PNotify({
	                title: "Error!",
	                text: resultData.responseMessage,
	                delay: 4000,
	                type:"danger"
	              });
	  }
  })
}



$(document).on('click','.btn-linkedin',function(){
	 IN.User.authorize(function(){
		 getProfileData();
	 })
});

// Social integration
	/*
	 * Linked In integration starts
	 * @returns
	 */

    // Setup an event listener to make an API call once auth is complete
    function onLinkedInLoad() {
        IN.Event.on(IN, "auth", getProfileData);
      $('a[id*=li_ui_li_gen_] span[id*=li_ui_li_gen_]').remove();
    }

    // Use the API call wrapper to request the member's profile data
    function getProfileData() {
        IN.API.Profile("me").fields("id", "first-name", "last-name", "headline", "location", "picture-url", "public-profile-url", "email-address").result(displayProfileData).error(onError);
    }

    // Handle the successful return from the API call
    function displayProfileData(data){
        var user = data.values[0];
        var name = user.firstName+' '+user.lastName;
        var email = user.emailAddress;;
//        document.getElementById("picture").innerHTML = '<img src="'+user.pictureUrl+'" />';
//        document.getElementById("name").innerHTML = user.firstName+' '+user.lastName;
//        document.getElementById("intro").innerHTML = user.headline;
//        document.getElementById("email").innerHTML = user.emailAddress;
//        document.getElementById("location").innerHTML = user.location.name;
//        document.getElementById("link").innerHTML = '<a href="'+user.publicProfileUrl+'" target="_blank">Visit profile</a>';
//        document.getElementById('profileData').style.display = 'block';
        if(isLogin){
        	isLogin = false;
        socialLogin(email,'LINKED_IN');
        }else{
		socialRegister(email,name,'LINKED_IN');
        }
    }

    // Handle an error response from the API call
    function onError(error) {
        console.log(error);
    }

    // Destroy the session of linkedin
    function closeLinkedInSession(){
        IN.User.logout();
        return true;
    }

   
  
    /*
	 * Google integration starts
	 * @returns
	 */
    var clicked=false;//Global Variable
    function ClickLogin()
    {
        clicked=true;
    }
    function onSignIn(googleUser) {
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        console.log('Full Name: ' + profile.getName());
        console.log('Given Name: ' + profile.getGivenName());
        console.log('Family Name: ' + profile.getFamilyName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());

        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
        if (clicked) {
        if(isLogin){
        	isLogin = false;
        socialLogin(profile.getEmail(),'GOOGLE');
        }else{
		socialRegister(profile.getEmail(),profile.getName(),'GOOGLE');
        }
        }
      };

      
      
  
     
      
  