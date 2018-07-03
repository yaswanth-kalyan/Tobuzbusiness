package service;

import java.util.List;

import constants.UserRole;
import models.AppUser;
import models.AppUserLog;
import models.Role;

public interface AppUserService {
	
	public List<Role> findRoleUserByEmailAndRoleAndIsActiveTrue(String email,UserRole role);
	
	public AppUser findUserByEmailAndIsActiveTrue(String email);
	
	public List<Role> findRoleUserByEmailAndRoleAndIdNotInAndIsActiveTrue(AppUser appUser,String email,UserRole role);
	
	public List<AppUser> findUserByEmailAndRoleAndIdNotInAndIsActiveTrue(AppUser appUser, String email) ;
	
	public List<AppUser> findAppUserByEmailAndRoleAndIsActiveTrue(String email,UserRole role);
	
	public AppUser findById(Long id);
	
	public Role setRole(AppUser appUser,UserRole userRole);
	
	public List<Role> findUserRoleList(AppUser appUser);
	
	public List<UserRole> findUserRoleListAndIsActiveTrue(AppUser appUser);
	
	public AppUserLog createAppUserLoginInfo(AppUser appUser,String remote);
	
	

}
