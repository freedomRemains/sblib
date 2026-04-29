// package com.sb.sblib.service;

// import java.io.InputStream;
// import java.util.List;
// import java.util.stream.Collectors;
// import java.util.stream.StreamSupport;

// import org.springframework.stereotype.Service;

// import io.minio.GetObjectArgs;
// import io.minio.ListObjectsArgs;
// import io.minio.MinioClient;
// import io.minio.PutObjectArgs;
// import io.minio.RemoveObjectArgs;
// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class MinioStorageService implements StorageService {

//     private final MinioClient minioClient;

//     @Override
//     public void upload(String bucket, String key, InputStream stream, long size, String contentType) throws Exception {

//         minioClient.putObject(
//                 PutObjectArgs.builder()
//                         .bucket(bucket)
//                         .object(key)
//                         .stream(stream, size, -1) // -1 = unknown part size（MinIOが自動処理）
//                         .contentType(contentType)
//                         .build()
//         );
//     }

//     @Override
//     public InputStream download(String bucket, String key) throws Exception {

//         return minioClient.getObject(
//                 GetObjectArgs.builder()
//                         .bucket(bucket)
//                         .object(key)
//                         .build()
//         );
//     }

//     @Override
//     public List<String> listFiles(String bucket, String prefix) throws Exception {

//         Iterable<Result<Item>> results = minioClient.listObjects(
//                 ListObjectsArgs.builder()
//                         .bucket(bucket)
//                         .prefix(prefix)
//                         .recursive(true)
//                         .build()
//         );

//         return StreamSupport.stream(results.spliterator(), false)
//                 .map(result -> {
//                     try {
//                         return result.get().objectName();
//                     } catch (Exception e) {
//                         throw new RuntimeException(e);
//                     }
//                 })
//                 .collect(Collectors.toList());
//     }

//     @Override
//     public void delete(String bucket, String key) throws Exception {

//         minioClient.removeObject(
//                 RemoveObjectArgs.builder()
//                         .bucket(bucket)
//                         .object(key)
//                         .build()
//         );
//     }
// }
