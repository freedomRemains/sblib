package com.sb.sblib.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@RequiredArgsConstructor
public class S3Config {
    // private final S3Properties s3Properties;

    // @Bean
    // public S3Client s3Client() {
    //     AwsBasicCredentials credentials = AwsBasicCredentials.create(
    //         s3Properties.getAccessKey(),
    //         s3Properties.getSecretKey()
    //     );
    //     return S3Client.builder()
    //             .region(Region.of(s3Properties.getRegion()))
    //             .credentialsProvider(StaticCredentialsProvider.create(credentials))
    //             .endpointOverride(URI.create(s3Properties.getEndpoint()))
    //             .build();
    // }

    @Bean
    public S3Client s3Client() {

        // AWS認証情報をJavaシステムプロパティで設定する
        System.setProperty("aws.accessKeyId", "minioadmin");
        System.setProperty("aws.secretAccessKey", "minioadmin123");
        // 必要ならリージョンも
        System.setProperty("aws.region", "ap-northeast-1");

        return S3Client.builder()
                .region(Region.AP_NORTHEAST_1) // 東京リージョン等、必要に応じて変更
                .credentialsProvider(DefaultCredentialsProvider.create())
                .endpointOverride(URI.create("http://localhost:9000"))
                .build();
    }
}
