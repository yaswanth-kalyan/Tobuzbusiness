package util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import play.Logger;
import util.Constants.FolderRoots;

public class AmazonS3Bucket {
	
private static final String SUFFIX = "/";
	
	@SuppressWarnings("deprecation")
	public static void cdn() {
		Logger.debug("inside method call");
		try {
			// credentials object identifying user for authentication
			// user must have AWSConnector and AmazonS3FullAccess for 
			// this example to work
			// create a client connection based on credentials
			AmazonS3 s3client = new AmazonS3Client(new PropertiesCredentials(new File(FolderRoots.AMAZON_AWS_CREDENTIALS_FILE_PATH)));
			
			// create bucket - name must be unique for all S3 users
			/*String bucketName = "mrigaathrymrbucket";
			s3client.createBucket(bucketName);*/
			
			//UploadFile.uploadFile("article/manik.jpg","/home/thrymr/Desktop/test.jpg");
			//S3Object object = s3client.getObject("mrigaathrymrbucket","/article/manik-prabhakar.jpg");
			// list buckets
			
			//createFolder("mrigaa-testing-bucket", "articles", s3client);
			
			//deleteFolder("mrigaathrymrbucket", "articles", s3client);
			//s3client.deleteObject(Constants.AMAZON_S3_BUCKET_NAME, "/article/b1bcf9ca.jpg");
			//s3client.deleteBucket("mrigaathrymrbucket");
			for (Bucket bucket : s3client.listBuckets()) {
				Logger.debug(bucket.getCreationDate() + " - " + bucket.getName());
			}
			for (S3ObjectSummary file : s3client.listObjects(Constants.AMAZON_S3_BUCKET_NAME).getObjectSummaries()) {
				Logger.debug(file.getBucketName()+" - "+file.getLastModified()+" - "+file.getKey());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
				folderName + SUFFIX, emptyContent, metadata);
		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}
	/**
	 * This method first deletes all the files in given folder and than the
	 * folder itself
	 */
	public static void deleteFolder(String bucketName, String folderName, AmazonS3 client) {
		List<S3ObjectSummary> fileList = client.listObjects(bucketName, folderName).getObjectSummaries();
		for (S3ObjectSummary file : fileList) {
			client.deleteObject(bucketName, file.getKey());
		}
		client.deleteObject(bucketName, folderName);
	}

}
