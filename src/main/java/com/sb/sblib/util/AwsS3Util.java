package com.sb.sblib.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

@Component
@RequiredArgsConstructor
public class AwsS3Util {

    private final S3Client s3Client;

	// 1. ファイルアップロード
	public void uploadFile(String bucket, String key, File file) {
		PutObjectRequest request = PutObjectRequest.builder()
				.bucket(bucket)
				.key(key)
				.build();
		s3Client.putObject(request, file.toPath());
	}

	// 2. ファイルダウンロード
	public void downloadFile(String bucket, String key, File destFile) throws IOException {
		GetObjectRequest request = GetObjectRequest.builder()
				.bucket(bucket)
				.key(key)
				.build();
		try (ResponseInputStream<GetObjectResponse> s3Obj = s3Client.getObject(request);
			 FileOutputStream fos = new FileOutputStream(destFile)) {
			s3Obj.transferTo(fos);
		}
	}

	// 3. ファイル一覧取得
	public List<String> listFiles(String bucket, String prefix) {
		ListObjectsV2Request request = ListObjectsV2Request.builder()
				.bucket(bucket)
				.prefix(prefix)
				.build();
		ListObjectsV2Response response = s3Client.listObjectsV2(request);
		return response.contents().stream()
				.map(S3Object::key)
				.collect(Collectors.toList());
	}

	// 4. ファイル削除
	public void deleteFile(String bucket, String key) {
		DeleteObjectRequest request = DeleteObjectRequest.builder()
				.bucket(bucket)
				.key(key)
				.build();
		s3Client.deleteObject(request);
	}
}
