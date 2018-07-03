package authAction;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import models.AppUser;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import util.SessionProperty;
import util.StringUtils;

public class BasicAuthAction extends BaseAction{

	@Override

	public CompletionStage<Result> call(Http.Context ctx) {

		AppUser appUser = SessionProperty.getLoggedInUser();

		if (appUser != null && appUser.getIsActive()) {
			if(StringUtils.isNotEmpty(SessionProperty.getUserAfterLoginUrl())) {
				String afterLoginUrl = SessionProperty.getUserAfterLoginUrl();
				SessionProperty.removeFromSession(SessionProperty.TOBUZ_URL_AFTER_LOGIN);
				return CompletableFuture.completedFuture(Results.redirect(afterLoginUrl));
			}else {
						return this.delegate.call(ctx);
			}

		}
		return CompletableFuture.completedFuture(Results.redirect(controllers.routes.HomeController.index()));
	}
}
