package models;

import javax.persistence.Entity;

import com.avaje.ebean.Model;

@Entity
public class AdminData extends BaseEntity{
	
	private String emailFromId;
	
	private String emailSmtpUserName;
	
	private String emailHost;
	
	private Integer emailPort;
	
	private String emailSmtpPassword;
	
	private String smsAuthKey;
	
	private String smsSenderId;
	
	private String operationsTeamEmailId;
	
	private String googleApiKey;
	
	// Payment gateway credentials needs to come here
	
	public static Model.Finder<Long, AdminData> find = new Model.Finder<Long, AdminData>(AdminData.class);

	public String getEmailFromId() {
		return emailFromId;
	}

	public void setEmailFromId(String emailFromId) {
		this.emailFromId = emailFromId;
	}

	public String getEmailSmtpUserName() {
		return emailSmtpUserName;
	}

	public void setEmailSmtpUserName(String emailSmtpUserName) {
		this.emailSmtpUserName = emailSmtpUserName;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public String getGoogleApiKey() {
		return googleApiKey;
	}

	public void setGoogleApiKey(String googleApiKey) {
		this.googleApiKey = googleApiKey;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public Integer getEmailPort() {
		return emailPort;
	}

	public void setEmailPort(Integer emailPort) {
		this.emailPort = emailPort;
	}

	public String getEmailSmtpPassword() {
		return emailSmtpPassword;
	}

	public void setEmailSmtpPassword(String emailSmtpPassword) {
		this.emailSmtpPassword = emailSmtpPassword;
	}

	public String getSmsAuthKey() {
		return smsAuthKey;
	}

	public void setSmsAuthKey(String smsAuthKey) {
		this.smsAuthKey = smsAuthKey;
	}

	public String getSmsSenderId() {
		return smsSenderId;
	}

	public void setSmsSenderId(String smsSenderId) {
		this.smsSenderId = smsSenderId;
	}

	public String getOperationsTeamEmailId() {
		return operationsTeamEmailId;
	}

	public void setOperationsTeamEmailId(String operationsTeamEmailId) {
		this.operationsTeamEmailId = operationsTeamEmailId;
	}
	
	public static AdminData getAdminData(){
		return AdminData.find.setMaxRows(1).findUnique();
	}
}
