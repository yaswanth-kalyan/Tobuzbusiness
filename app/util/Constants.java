package util;

import play.Play;

public class Constants {
public static final String AMAZON_S3_BUCKET_NAME =  Play.application().configuration().getString("AmazonS3BucketName");


	
	public static class FolderRoots {
		//change the ROOT as the path where the images to be stored in local system
		public static final String AMAZON_AWS_CREDENTIALS_FILE_PATH = "conf/AwsCredentials.properties";
	}
	
}
