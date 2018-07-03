package beans.tobuzPackage;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import models.AppUser;
import models.Country;
import models.tobuzpackage.TobuzPackage;
import models.tobuzpackage.TobuzPackageService;
import models.tobuzpackage.TobuzPackageServiceInfo;

public class TobuzPackageBean {
	
	public TobuzPackageService tobuzPackageService;
	
	public Map<TobuzPackage,Boolean> serviceMapBean = new LinkedHashMap<TobuzPackage,Boolean>();
	
	public static List<TobuzPackageBean> generatePackageData(Country country,AppUser appUser){
		List<TobuzPackageBean> resultBean = new LinkedList<TobuzPackageBean>();
		List<TobuzPackageService> services = TobuzPackageService.getPackageServicesByCountryAndUserRole(country,appUser.getRole().getUserRole());
		List<TobuzPackage> packages = TobuzPackage.getPackagesByCountryAndRole(country,appUser);
		for(TobuzPackageService service : services) {
			TobuzPackageBean bean = new TobuzPackageBean();
			bean.tobuzPackageService = service;
			bean.serviceMapBean = new LinkedHashMap<TobuzPackage,Boolean>();
			Boolean isAvailable = false;
			for(TobuzPackage tobuzPackage : packages) {
				TobuzPackageServiceInfo map = TobuzPackageServiceInfo.find.where().eq("tobuzPackage",tobuzPackage).eq("tobuzPackageService", service).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
				if(map != null) {
					isAvailable = true;
					bean.serviceMapBean.put(tobuzPackage,Boolean.TRUE);		
				}else {
					bean.serviceMapBean.put(tobuzPackage,Boolean.FALSE);	
				}
			}
			if(isAvailable) {
			resultBean.add(bean);
			}
		}
		return resultBean;
	}
	
	
	

}
