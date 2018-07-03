package models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.annotation.CreatedTimestamp;

import controllers.LoginController;
import play.mvc.Http.MultipartFormData.FilePart;

@Entity
public class FileEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Boolean isActive = Boolean.FALSE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	private String fileName;

	private String mimeType;

	private String filePath;

	@ManyToOne
	public AppUser uploadedBy;
	
	@Lob
	private byte[] byteContent;

	private Date uploadedOn;

	@Column(columnDefinition = "TEXT")
	private String comment;
	
	public static com.avaje.ebean.Model.Finder<Long, FileEntity> find = new com.avaje.ebean.Model.Finder<Long, FileEntity>(FileEntity.class);

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public byte[] getByteContent() {
		return byteContent;
	}

	public void setByteContent(byte[] byteContent) {
		this.byteContent = byteContent;
	}

	public Date getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	

	public AppUser getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(AppUser uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public static FileEntity toFileEntity(FileEntity fileEntity,FilePart filePart,String cloudURL) {
		if(fileEntity == null) {
			fileEntity = new FileEntity();
			fileEntity.fileName = filePart.getFilename();
			fileEntity.mimeType = filePart.getContentType();
			fileEntity.setUploadedBy(LoginController.getLoggedInUser());
			fileEntity.setUploadedOn(new Date());
			fileEntity.setFilePath(cloudURL);
			fileEntity.save();
		}else {
			fileEntity = new FileEntity();
			fileEntity.fileName = filePart.getFilename();
			fileEntity.mimeType = filePart.getContentType();
			fileEntity.setUploadedBy(LoginController.getLoggedInUser());
			fileEntity.setUploadedOn(new Date());
			fileEntity.setFilePath(cloudURL);
			fileEntity.update();
		}
		return fileEntity;
	}
}
