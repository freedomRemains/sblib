package com.sb.sblib.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sb.sblib.util.PropUtil;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@RequiredArgsConstructor
public class S3Config {

    private final PropUtil propUtil;

    @Bean
    public S3Client s3Client() {

        // S3アクセスのためのクレデンシャル情報を生成する
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                propUtil.getS3().get("accessKey"),
                propUtil.getS3().get("secretKey")
        );

        // S3クライアントを生成する
        return S3Client.builder()
                .endpointOverride(URI.create(propUtil.getS3().get("endpoint")))
                .region(Region.of(propUtil.getS3().get("region")))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .serviceConfiguration(config -> config.pathStyleAccessEnabled(true))
                .build();
    }
}
