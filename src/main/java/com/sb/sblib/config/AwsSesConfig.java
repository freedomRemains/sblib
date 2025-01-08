//package com.sb.sblib.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
//import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
//
//@Configuration
//public class AwsSesConfig {
//
//	// TODO 外部読み込みとする(アクセスキーはプロパティ読み込みNG)
//	private String aswRegion = "northeast_1"; // リージョン
//	private String awsAccessKey = "accessKey"; // アクセスキー
//	private String awsSecretKey = "secretKey"; // シークレットキー
//
//	@Bean
//	private JavaMailSender SimpleEmailServiceJavaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
//		return SimpleEmailServiceJavaMailSender(
//				AmazonSimpleEmailServiceClientBuilder.standard()
//						.withRegion(aswRegion)
//						.withCredentials(new AWSStaticCredentialsProvider(
//								new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
//						.build());
//	}
//}
