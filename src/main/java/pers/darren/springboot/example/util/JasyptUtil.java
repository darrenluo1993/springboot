package pers.darren.springboot.example.util;

import static org.apache.commons.codec.binary.Base64.encodeBase64String;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * Jasypt加解密工具
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jun 4, 2021 4:17:35 PM
 */
public final class JasyptUtil {

    private JasyptUtil() {
        throw new UnsupportedOperationException("This is a utility class!");
    }

    /**
     * Jasypt生成加密结果
     *
     * @param password 配置文件中设定的加密盐值
     * @param value    加密值
     * @return
     */
    public static String encryptPwd(final String password, final String value) {
        final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(buildConfig(password));
        return encryptor.encrypt(value);
    }

    /**
     * 解密
     *
     * @param password 配置文件中设定的加密盐值
     * @param value    解密密文
     * @return
     */
    public static String decryptPwd(final String password, final String value) {
        final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(buildConfig(password));
        return encryptor.decrypt(value);
    }

    public static SimpleStringPBEConfig buildConfig(final String password) {
        final SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    public static void main(final String[] args) {
        final String base64 = encodeBase64String("password".getBytes());
        System.out.println("base64===================" + base64);
        final String username = encryptPwd(base64, "username");
        System.out.println("username=================" + username);
        final String password = encryptPwd(base64, "password");
        System.out.println("password=================" + password);
        System.out.println("username=================" + decryptPwd(base64, username));
        System.out.println("password=================" + decryptPwd(base64, password));
    }
}