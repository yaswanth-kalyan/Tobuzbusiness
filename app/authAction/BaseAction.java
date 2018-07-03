package authAction;

import java.util.concurrent.CompletionStage;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

public class BaseAction extends Action<Object>{

	@Override
	public CompletionStage<Result> call(Context ctx) {
		return this.delegate.call(ctx);
	}

}
