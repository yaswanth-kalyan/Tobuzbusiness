package controllers;

import java.util.List;

import com.google.inject.Inject;
import play.Logger;

import beans.tobuzPackage.BusinessAdvertBean;
import constants.ListingType;
import models.AppUser;
import models.business.Category;
import models.business.SubCategory;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.LoginService;
import util.AppResponse;

public class BusinessAdvertController extends Controller {

	@Inject
	LoginService loginService;

	@Inject
	FormFactory formFactory;

	public Result createBusinessAdvert() {
		AppResponse appResponse = new AppResponse();
		try {
			Form<BusinessAdvertBean> form = formFactory.form(BusinessAdvertBean.class).bindFromRequest();	
			play.Logger.debug("=======================================>>>>>>>>>>>>>>>>>>" + form.toString());
			play.Logger.info(form.toString());
			AppUser appuser = AppUser.find.byId(1L);
			play.Logger.info(" appuser id " + appuser.getId());
			BusinessAdvertBean bean = form.get();
			System.out.println(bean.toString());
			bean.setAdvertedByUserId(appuser.getId());
			bean.setRoleId(appuser.getRole().getId());
			// appResponse = bean.toBusinessAdvert();
			play.Logger.info(bean.toString());
		} catch (Exception e) {
			e.printStackTrace();
			appResponse = new AppResponse();
		}

		return ok(Json.toJson(appResponse));
	}

	public Result getCategoryList(String businessType) {
		List<Category> categoryList = Category.getCategoriesByBusinessType(ListingType.valueOf(businessType));
		return ok(Json.toJson(categoryList));
	}

	public Result getSubCategoryList(Long categoryId) {
		List<SubCategory> subCategoryList = SubCategory.find.where().eq("category", Category.find.byId(categoryId))
				.eq("isActive", Boolean.TRUE).findList();
		return ok(Json.toJson(subCategoryList));
	}

	// Form(of=class beans.tobuzPackage.BusinessAdvertBean,
	// data={inputSelctdBusinessArea=sdsd ddsfdsf, categoryList[0]=object:23,
	// spaceSize=100, googleSearchAddress=gachibow, sourceOfBusiness=Kukaatpally,
	// listingType=BUSINESS, advertDescription=sadasdas, title=dsds sdsdf dssdfsdf,
	// lookBy=business, timeLineInDays=4, investmentRange=200000 - 1000000,
	// selctdAreaSquare=3},
	// value=Optional[beans.tobuzPackage.BusinessAdvertBean@9a6ca4f],
	// errors={categoryList[0]=[ValidationError(categoryList[0],[error.invalid],[])]})

}
