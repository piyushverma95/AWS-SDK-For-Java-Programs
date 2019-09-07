package com.amazonaws.samples;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.kinesisfirehose.*;
import com.amazonaws.services.kinesisfirehose.model.PutRecordRequest;
import com.amazonaws.services.kinesisfirehose.model.Record;


public class FirstKinesisFirehoseClass {

	public static void main(String[] args) {
		
		// Reading credentials from the PC's hard drive, path ~/.aws/credentials .
		ProfileCredentialsProvider credentials = new ProfileCredentialsProvider();
		credentials.getCredentials();
			
			// Creating Amazon Kinesis Firehose Client to use Amazon Kinesis Firehose Service
		    // Also, setting the region and credentials for the Amazon Kinesis Firehose Service on AWS
			AmazonKinesisFirehoseClient client= (AmazonKinesisFirehoseClient) AmazonKinesisFirehoseClient.builder().withCredentials(credentials)
		            .withRegion("us-east-1")
		            .build();

			// Converting String message to byte buffer
			String msg = "My Name is Piyush";
			ByteBuffer data = Charset.forName("UTF-8").encode(msg);

			// Creating a record and putting the data in the record.
			Record record = new Record();
			record.setData(data);
			
			// Putting the record on Firehose Delivery Stream named "test"
			PutRecordRequest putRecordRequest = new PutRecordRequest();
			putRecordRequest.setDeliveryStreamName("test");
			putRecordRequest.setRecord(record);

			client.putRecord(putRecordRequest);
			System.out.println("Record Successfully Uploaded to the Delivery Stream");
	}
}


//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
//import com.amazonaws.services.s3.model.Region;


//ProfileCredentialsProvider credentials = new ProfileCredentialsProvider();
//credentials.getCredentials();

//AmazonKinesisFirehose client = AmazonKinesisFirehoseClientBuilder.standard()
//.withCredentials(new AWSStaticCredentialsProvider(credentials))
//.withRegion("us-east-1")
//.build();


// ByteBuffer buff = Charset.forName("UTF-9").encode(msg);



//static ByteBuffer str_to_bb(String msg, Charset charset) {
//	return ByteBuffer.wrap(msg.getBytes(charset));
//}

//Charset charset = Charset.forName("UTF-8");
//ByteBuffer data = FirstKinesisFirehoseClass.str_to_bb(msg, charset);