function buyPackage(packageId,packageName){
	console.log("User tried to buy : "+packageName);
	$.post('/update-package',{'businessPackageId':packageId},function(response){
		var resultData = response;
  	  if(resultData.alertType == "SUCCESS"){
  		window.location.href='/dashboard/home';
  	  }else{
  		  new PNotify({
	                title: "Error!",
	                text: resultData.responseMessage,
	                delay: 4000,
	                type:"danger"
	              });
  	  }
	});
}