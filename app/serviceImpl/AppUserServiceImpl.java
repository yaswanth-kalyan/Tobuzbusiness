package serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import constants.UserRole;
import models.AppUser;
import models.AppUserLog;
import models.Role;
import service.AppUserService;
import util.SessionProperty;

public class AppUserServiceImpl implements AppUserService{

	@Override
	public List<Role> findRoleUserByEmailAndRoleAndIsActiveTrue(String email, UserRole role) {
		// TODO Auto-generated method stub
		return Role.find.where().eq("userRole", role).eq("appUser.email", email).eq("appUser.isActive", Boolean.TRUE).findList();
	}
	
	@Override
	public List<Role> findUserRoleList(AppUser appUser){
		return Role.find.where().eq("appUser", appUser).eq("appUser.isActive", Boolean.TRUE).eq("isActive", Boolean.TRUE).findList();
	}
	
	@Override
	public List<UserRole> findUserRoleListAndIsActiveTrue(AppUser appUser) {
		// TODO Auto-generated method stub
		Set<UserRole> roles = new HashSet<UserRole>();
		for(Role role : findUserRoleList(appUser)) {
			roles.add(role.getUserRole());
		}
		return new ArrayList<UserRole>(roles);
	}
	
	@Override
	public List<AppUser> findAppUserByEmailAndRoleAndIsActiveTrue(String email, UserRole role) {
		// TODO Auto-generated method stub
		Set<AppUser> appUserList = new HashSet<AppUser>();
		for(Role roles : findRoleUserByEmailAndRoleAndIsActiveTrue(email,role)) {
			appUserList.add(roles.getAppUser());
		}
		return new ArrayList<AppUser>(appUserList);
		
	}

	@Override
	public AppUser findById(Long id) {
		// TODO Auto-generated method stub
		return AppUser.find.byId(id);
	}

	@Override
	public Role setRole(AppUser appUser, UserRole userRole) {
		// TODO Auto-generated method stub
		if(!findUserRoleListAndIsActiveTrue(appUser).contains(userRole)) {
			Role role = new Role();
			role.setAppUser(appUser);
			role.setUserRole(userRole);
			role.setIsActive(Boolean.TRUE);
			role.save();
			return role;
		}
		return null;
	}

	@Override
	public List<Role> findRoleUserByEmailAndRoleAndIdNotInAndIsActiveTrue(AppUser appUser, String email,
			UserRole role) {
		// TODO Auto-generated method stub
		return Role.find.where().ne("appUser.id", appUser.getId()).eq("userRole", role).eq("appUser.email", email).eq("appUser.isActive", Boolean.TRUE).findList();
		
	}
	
	@Override
	public List<AppUser> findUserByEmailAndRoleAndIdNotInAndIsActiveTrue(AppUser appUser, String email) {
		// TODO Auto-generated method stub
		return AppUser.find.where().ne("id", appUser.getId()).ieq("email", email.trim()).eq("isActive", Boolean.TRUE).findList();
		
	}
	
	@Override
	public AppUserLog createAppUserLoginInfo(AppUser appUser,String remote) {
		AppUserLog appUserLoginInfo = new AppUserLog();
		appUserLoginInfo.setAppUser(appUser);
		appUserLoginInfo.setAuthToken(appUser.generateAuthToken());
		appUserLoginInfo.setIpAddress(remote);
		appUserLoginInfo.setIsActive(Boolean.TRUE);
		appUserLoginInfo.setLoggedInOn(new Date());
		appUserLoginInfo.save();
		
		return appUserLoginInfo;
	}

	@Override
	public AppUser findUserByEmailAndIsActiveTrue(String email) {
		// TODO Auto-generated method stub
		return AppUser.find.where().ieq("email",email.trim()).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
	}

}
