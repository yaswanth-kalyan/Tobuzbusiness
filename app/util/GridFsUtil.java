package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.avaje.ebeaninternal.util.IOUtils;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import models.AppUser;
import models.FileEntity;
import play.mvc.Http.MultipartFormData.FilePart;

public class GridFsUtil {
	private String userName = "admin";
	private String password = "admin";
	private String dataBaseName = "admin";

	private Integer PORT = 27017;
	private String COLLECTION = "fileentity";

	private DB db;
	private DBCollection collection;

	public GridFsUtil() {
		getDataBase();
	}

	// @PostConstruct
	public void getDataBase() {
		MongoCredential createCredential = MongoCredential.createCredential(userName, dataBaseName,password.toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", PORT),Arrays.asList(createCredential));

		this.db = mongoClient.getDB(dataBaseName);
		if (db == null) {
			throw new RuntimeException("Null");
		}

		this.collection = db.getCollection(COLLECTION);
	}

	public MongoCollection getMongoConnection() {
		MongoClient mongoClient = new MongoClient("localhost", PORT);
		MongoDatabase db = mongoClient.getDatabase("FileSys");
		MongoCollection<Document> collection = db.getCollection("myNewCollection1");
		return collection;
	}

	/**
	 * @author lakshmi This functionality is for uploading a file into the
	 *         filesystem with predefined file location path for now we had
	 *         taken static path for testing the file uploads
	 */
	public FileEntity toFileEntity(FilePart image, AppUser uploadByUser) {
		GridFSInputFile gfsFile = this.uploadFile(image);
		FileEntity fileEntity = null;
		if (gfsFile != null) {
			fileEntity = new FileEntity();
			fileEntity.setFileName(image.getFilename());
			fileEntity.setMimeType(image.getContentType());
			fileEntity.setFilePath(gfsFile.getId().toString());
			/*
			 * //Logger.debug(" check image "); //
			 * Logger.info(image.getContentType()+"   "+image.getFilename());
			 * //We can append differentparameters of uploaded user to the
			 * filename/path try {
			 * com.google.common.io.Files.write(com.google.common.io.Files.
			 * toByteArray(file), new File(path)); } catch (IOException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); return null;
			 * }
			 */
			fileEntity.setUploadedBy(uploadByUser);
			fileEntity.setUploadedOn(new Date());
			fileEntity.save();
		}
		return fileEntity;
	}

	public GridFSInputFile uploadFile(FilePart filePart) {

		/*
		 * Mongo mongo = new Mongo("localhost", 27017); DB db =
		 * mongo.getDB("FileSys"); DBCollection collection =
		 * db.getCollection("myNewCollection1");
		 */
		String newFileName = filePart.getFilename();

		File imageFile = (File) filePart.getFile();

		// create a "photo" namespace
		GridFS gfsPhoto = new GridFS(db);

		// get image file from local drive
		GridFSInputFile gfsFile = null;
		try {
			gfsFile = gfsPhoto.createFile(imageFile);
			// set a new filename for identify purpose
			gfsFile.setFilename(newFileName);
			gfsFile.setContentType(filePart.getContentType());
			// save the image file into mongoDB
			gfsFile.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gfsFile;

	}

	public byte[] getFile(FileEntity fileEntity) {
		byte[] br = null;
		Mongo mongo = null;
		try {
			/*
			 * mongo = new Mongo("localhost", 27017); DB db =
			 * mongo.getDB("FileSys");
			 */
			GridFS gfsPhoto = new GridFS(db);
			Object fileId = fileEntity.getFilePath();
			ObjectId onId = new ObjectId(fileEntity.getFilePath());
			GridFSDBFile file = gfsPhoto.findOne(onId);
			if (file != null) {
				System.out.println(file.getFilename() + "  " + file.getContentType());
				try {
					br = IOUtils.read(file.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("file is null");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return br;
	}

	/**
	 * Used to place order for Grocery and Shop & Delivery upload list
	 * 
	 * @param imageContent
	 *            Encoded image string
	 * @param imageName
	 *            Name of the image
	 * @param contentType
	 *            Image content type
	 * @return Returns {@link FileEntity}
	 */
	public static FileEntity toFileEntity(String imageContent, String imageName, AppUser appUser) {
		FileEntity fileEntity = new FileEntity();
		if (imageName != null) {
			fileEntity.setFileName(imageName);
		} else {
			imageName = RandomStringUtils.randomAlphanumeric(6).toLowerCase();
			fileEntity.setFileName(imageName);
		}
		fileEntity.setMimeType("image/jpeg");
		byte[] b = Base64.decodeBase64(imageContent);
		try (FileOutputStream fileOuputStream = new FileOutputStream(imageName)) {
			fileOuputStream.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (appUser != null) {
			fileEntity.setUploadedBy(appUser);
		}
		// fileEntity.setFilePath(filePath+imageName);
		fileEntity.setUploadedOn(new Date());             
		return fileEntity;
	}

	public FileEntity uploadFilePart(FilePart filePart) {
		FileEntity fileEntity = null;
		try {
			GridFSInputFile gsfFile = uploadFile(filePart);
			if (gsfFile != null) {
				fileEntity = new FileEntity();
				fileEntity.setFileName(filePart.getFilename());
				fileEntity.setFilePath(gsfFile.getId().toString());
				fileEntity.setMimeType(filePart.getContentType());
				fileEntity.save();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fileEntity;
	}

}
