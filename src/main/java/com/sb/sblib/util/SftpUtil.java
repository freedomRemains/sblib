package com.sb.sblib.util;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.transport.verification.HostKeyVerifier;

@Component
@RequiredArgsConstructor
public class SftpUtil {

    private final PropUtil prop;

    /**
     * SFTPのアップロードを行う。
     * 
     * @param localPath アップロードするファイルのパス
     * @param remotePath リモートパス
     * @throws IOException IO例外
     */
    public void upload(String localPath, String remotePath) throws IOException {

        // SSH接続を確立する
        var sshClient = connect();

        // SFTPクライアントを作成する
        try (var sftpClient = sshClient.newSFTPClient()) {

            // ファイルをアップロードする
            ClassPathResource resource = new ClassPathResource(localPath);
            sftpClient.put(resource.getFile().getAbsolutePath(), remotePath);

        } finally {

            // SSH接続を切断する
            sshClient.disconnect();
        }
    }

    private SSHClient connect() throws IOException {

        // SSHクライアントを作成する
        var sshClient = new SSHClient();
        sshClient.addHostKeyVerifier(new HostKeyVerifier() {
            @Override
            public boolean verify(String hostname, int port, PublicKey key) {
                return true; // 全てのホストキーを受け入れる（セキュリティリスクがあるため、本番環境では適切な検証を行うこと）
            }

            @Override
            public List<String> findExistingAlgorithms(String hostname, int port) {

                // 空のリストを返すことで、SSHJ側のデフォルト処理が動くようにする
                return Collections.emptyList();
            }
        });
        sshClient.connect(host(), port());
        sshClient.authPassword(user(), password());
        return sshClient;
    }

    // TODO 正式にはAWSパラメータストアもしくはシークレットマネージャからの取得とする
    private String host() {
        return prop.getSftp().get("host");
    }

    // TODO 正式にはAWSパラメータストアもしくはシークレットマネージャからの取得とする
    private String user() {
        return prop.getSftp().get("user");
    }

    // TODO 正式にはAWSパラメータストアもしくはシークレットマネージャからの取得とする
    private String password() {
        return prop.getSftp().get("password");
    }

    // TODO 正式にはAWSパラメータストアもしくはシークレットマネージャからの取得とする
    private int port() {
        return Integer.parseInt(prop.getSftp().get("port"));
    }
}
