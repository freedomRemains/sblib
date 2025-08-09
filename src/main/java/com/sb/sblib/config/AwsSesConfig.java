// package com.sb.sblib.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.mail.javamail.JavaMailSender;

// import com.amazonaws.auth.AWSStaticCredentialsProvider;
// import com.amazonaws.auth.BasicAWSCredentials;
// import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
// import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
// import com.sb.sblib.util.PropUtil;

// import lombok.RequiredArgsConstructor;

// @Configuration
// @RequiredArgsConstructor
// public class AwsSesConfig {

//     private final PropUtil propUtil;

//     @Bean
//     private JavaMailSender SimpleEmailServiceJavaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {

//         // AWSアクセスのための情報を取得する
//         // TODO シークレットマネージャ経由とする
//         String aswRegion = propUtil.getS3().get("region");
//         String awsAccessKey = propUtil.getS3().get("accessKey");
//         String awsSecretKey = propUtil.getS3().get("secretKey");

//         return SimpleEmailServiceJavaMailSender(
//                 AmazonSimpleEmailServiceClientBuilder.standard()
//                         .withRegion(aswRegion)
//                         .withCredentials(new AWSStaticCredentialsProvider(
//                                 new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
//                         .build());
//     }
// }
