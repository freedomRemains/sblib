package com.sb.sblib.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
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

    /** プロパティユーティリティ */
    private final PropUtil prop;

    /** AWS S3クライアント */
    private final S3Client s3Client;

    /**
     * S3にファイルをアップロードする。
     * 
     * @param localDirPath 元ファイルのディレクトリパス
     * @param localFileName 元ファイル名
     * @param s3DirPath アップロード先のディレクトリパス
     * @param s3FileName アップロード先のファイル名
     * @throws Exception 例外
     */
    public void upload(String localDirPath, String localFileName, String s3DirPath, String s3FileName) throws Exception {
        upload(localDirPath + "/" + localFileName, s3DirPath + "/" + s3FileName);
    }

    /**
     * S3にファイルをアップロードする。
     * 
     * @param localFilePath 元ファイルのファイルパス
     * @param s3FilePath アップロード先のファイルパス
     * @throws Exception 例外
     */
    public void upload(String localFilePath, String s3FilePath) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource(localFilePath);
        upload(classPathResource.getFile(), s3FilePath);
    }

    /**
     * S3にファイルをアップロードする。
     * 
     * @param localFile 元ファイル(File型)
     * @param s3FilePath アップロード先のファイルパス
     * @throws Exception 例外
     */
    public void upload(File localFile, String s3FilePath) throws Exception {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(prop.getS3().get("bucket"))
                .key(s3FilePath)
                .build();
        s3Client.putObject(request, localFile.toPath());
    }

    /**
     * S3からファイルをダウンロードする。
     * 
     * @param s3DirPath ダウンロードするファイルのディレクトリパス
     * @param s3FileName ダウンロード対象のファイル名
     * @param localDirPath ダウンロード先のディレクトリパス
     * @param localFileName ダウンロードファイル名
     * @throws Exception
     */
    public void download(String s3DirPath, String s3FileName, String localDirPath, String localFileName) throws Exception {
        download(s3DirPath + "/" + s3FileName, localDirPath + "/" + localFileName);
    }

    /**
     * S3からファイルをダウンロードする。
     * 
     * @param s3FilePath ダウンロードするファイルのファイルパス
     * @param localFilePath ダウンロード先のファイルパス
     * @throws Exception 例外
     */
    public void download(String s3FilePath, String localFilePath) throws Exception {
        download(s3FilePath, new File(localFilePath));
    }

    /**
     * S3からファイルをダウンロードする。
     * 
     * @param s3FilePath ダウンロードするファイルのファイルパス
     * @param localFile ダウンロード先のローカルファイル(File型)
     * @throws Exception 例外
     */
    public void download(String s3FilePath, File localFile) throws Exception {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(prop.getS3().get("bucket"))
                .key(s3FilePath)
                .build();
        try (ResponseInputStream<GetObjectResponse> s3Obj = s3Client.getObject(request);
                FileOutputStream fos = new FileOutputStream(localFile)) {
            s3Obj.transferTo(fos);
        }
    }

    /**
     * S3上の所定のディレクトリパスにあるファイルの一覧を取得する。
     * サブディレクトリがある場合は、再帰的にたどって全てのファイルパスを取得できる。
     * ＜例＞
     * xxx.txt 　←　指定したディレクトリ直下のファイル
     * subdir1/yyy.txt　←　サブディレクトリ配下にあるファイル
     * subdir2/zzz.txt　←　別のサブディレクトリ配下にあるファイル
     * 
     * @param targetDirPath 一覧取得対象のディレクトリパス
     * @return S3上にあるファイル名のリスト
     */
    public List<String> listFiles(String targetDirPath) {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(prop.getS3().get("bucket"))
                .prefix(targetDirPath)
                .build();
        ListObjectsV2Response response = s3Client.listObjectsV2(request);
        return response.contents().stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }

    /**
     * S3上からファイルを削除する。
     * 
     * @param targetDirPath 削除するファイルのディレクトリ
     * @param targetFileName 削除するファイル名
     */
    public void delete(String targetDirPath, String targetFileName) {
        delete(targetDirPath + "/" + targetFileName);
    }

    /**
     * S3上からファイルを削除する。
     * 
     * @param targetFilePath 削除するファイルのパス
     */
    public void delete(String targetFilePath) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(prop.getS3().get("bucket"))
                .key(targetFilePath)
                .build();
        s3Client.deleteObject(request);
    }
}
