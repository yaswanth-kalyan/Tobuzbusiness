package authAction;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import constants.UserRole;
import models.AppUser;
import models.BaseEntity;
import models.Role;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import util.SessionProperty;
import util.StringUtils;

public class ListingAccessAuthAction extends BaseAction {

	@Override

	public CompletionStage<Result> call(Http.Context ctx) {

		AppUser appUser = SessionProperty.getLoggedInUser();
		if (appUser != null && appUser.getIsActive() ) {
			Role role = appUser.getRole();
			if(!role.getUserRole().equals(UserRole.BUYER) && !role.getUserRole().equals(UserRole.BUSINESS_SERVICE)) {
				if(StringUtils.isNotEmpty(SessionProperty.getUserAfterLoginUrl())) {
					String afterLoginUrl = SessionProperty.getUserAfterLoginUrl();
					SessionProperty.removeFromSession(SessionProperty.TOBUZ_URL_AFTER_LOGIN);
					return CompletableFuture.completedFuture(Results.redirect(afterLoginUrl));
				}else {
							return this.delegate.call(ctx);
				}
			}else {
				//Un authorised page
				return CompletableFuture.completedFuture(Results.redirect(controllers.routes.HomeController.index()));
			}

		}

		return CompletableFuture.completedFuture(Results.redirect(controllers.routes.HomeController.index()));

	}
}
