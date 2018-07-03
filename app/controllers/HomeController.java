package controllers;

import constants.AlertType;
import models.AppUser;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.AppResponse;
import util.SessionProperty;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
    	  AppUser appUser = LoginController.getLoggedInUser();
        return ok(views.html.index.render(appUser));
    }

    public Result dashboard(){
      AppUser appUser = LoginController.getLoggedInUser();
      return ok(views.html.dbindex.render(appUser));
    }

    public Result listings(){
      AppUser appUser = LoginController.getLoggedInUser();
      return ok(views.html.dblistings.render(appUser));
    }
    
    public Result listingForm() {
    	AppUser appUser=LoginController.getLoggedInUser();
    	return ok(views.html.listingForm.render(appUser));
    }
    public Result favorites(){
      AppUser appUser = LoginController.getLoggedInUser();
     
      return ok(views.html.dbfavorites.render(appUser));
    }

    public Result savedSearch(){
      AppUser appUser = LoginController.getLoggedInUser();
      return ok(views.html.dbsaved_search.render(appUser));
    }
    
    public Result searchList() {
    	AppUser appUser = LoginController.getLoggedInUser();
    	return ok(views.html.db_final_listings.render(appUser));
    }
    public Result messages(){
      AppUser appUser = LoginController.getLoggedInUser();
      return ok(views.html.dbmessages.render(appUser));
    }

    public Result updatedProfile(){
      AppUser appUser = LoginController.getLoggedInUser();
      return ok(views.html.dbupd_profile.render(appUser));
    }


    public Result updateSessionWithTimezone(String offset) {
    	SessionProperty.updateOffset(offset);
    	return ok(Json.toJson(new AppResponse(AlertType.SUCCESS)));
    }
    
    public Result saveFile() {
    	return ok(views.html.testFile.render());
    }

    public Result sellerDashboard(){
      AppUser appUser = LoginController.getLoggedInUser();
      return ok(views.html.seller.seller_dashboard_home.render(appUser));
    }

  
}
