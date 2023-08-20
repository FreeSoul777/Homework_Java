package com.example.cipheraesxorrsa.security;

import com.sshtools.common.publickey.InvalidPassphraseException;
import com.sshtools.common.publickey.SshKeyUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

public class SecurityServiceImpl implements SecurityService{
    @Override
    public String encryptMessage(String message, File publicKeyFile) throws IOException, GeneralSecurityException {
        // читаем публичный ключ
        PublicKey publicKey = SshKeyUtils.getPublicKey(publicKeyFile).getJCEPublicKey();

        // Генерация случайного вектора инициализации
        byte[] iv = generateIV();

        // Генерация секретного ключа AES
        SecretKey secretKey = generateSecKey();

        // Шифрование сообщения с использованием AES
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] encryptedMessage = aesCipher.doFinal(message.getBytes());

        // Шифрование ключа с использованием RSA
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedKey = rsaCipher.doFinal(secretKey.getEncoded());

        // Соединение зашифрованного сообщения, ключа и вектора инициализации в одну строку
        byte[] result = new byte[iv.length + encryptedKey.length + encryptedMessage.length];

        copy(iv, encryptedKey, encryptedMessage, result);

        return Base64.getEncoder().encodeToString(result);
    }

    @Override
    public String decryptMessage(String message, File privateKeyFile) throws IOException, GeneralSecurityException, InvalidPassphraseException {
        // чтение приватного ключа
        PrivateKey privateKey = SshKeyUtils.getPrivateKey(privateKeyFile,
                "").getPrivateKey().getJCEPrivateKey();

        // Разбиение зашифрованного сообщения, ключа и вектора инициализации на отдельные строки
        byte[] answer = Base64.getDecoder().decode(message);
        byte[] iv = Arrays.copyOfRange(answer, 0, 16);
        byte[] encryptedKey = Arrays.copyOfRange(answer,16, 528);
        byte[] encryptedMessage = Arrays.copyOfRange(answer, 528, answer.length);

        // Дешифрование ключа с использованием RSA
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedKey = rsaCipher.doFinal(encryptedKey);
        SecretKey secretKey = new SecretKeySpec(decryptedKey, "AES");

        // Дешифрование сообщения с использованием AES
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] decryptedMessage = aesCipher.doFinal(encryptedMessage);

        // Возврат дешифрованного сообщения
        return new String(decryptedMessage);
    }

    @Override
    public void encryptDocument(File document, File openKey) throws IOException, GeneralSecurityException {
        // читаем публичный ключ
        PublicKey publicKey = SshKeyUtils.getPublicKey(openKey).getJCEPublicKey();

        // Генерация случайного вектора инициализации
        byte[] iv = generateIV();

        // Генерация секретного ключа AES
        SecretKey secretKey = generateSecKey();

        // Инициализация AES и запись в файл iv
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
        FileInputStream fisDoc = new FileInputStream(document);
        FileOutputStream fosEncrypted = new FileOutputStream(document.getParent() + "/" + document.getName() + ".sde");
        fosEncrypted.write(iv);

        // Шифрование ключа с использованием RSA и запись в файл
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedKey = rsaCipher.doFinal(secretKey.getEncoded());
        fosEncrypted.write(encryptedKey);

        // Шифрование сообщения с использованием AES
        byte[] buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = fisDoc.read(buffer)) != -1) {
            byte[] output = aesCipher.update(buffer, 0, bytesRead);
            if (output != null) {
                fosEncrypted.write(output);
            }
        }
        byte[] outputBytes = aesCipher.doFinal();
        if (outputBytes != null) {
            fosEncrypted.write(outputBytes);
        }
        fisDoc.close();
        fosEncrypted.close();
    }

    @Override
    public void decryptDocument(File document, File secretKey) throws IOException, GeneralSecurityException, InvalidPassphraseException {
        PrivateKey privateKey = SshKeyUtils.getPrivateKey(secretKey,
                "").getPrivateKey().getJCEPrivateKey();

        // Чтение файла с зашифрованным документом
        FileInputStream fisEncrypted = new FileInputStream(document);
        byte[] iv = new byte[16];
        fisEncrypted.read(iv);

        byte[] encryptedAesKey = new byte[512];
        fisEncrypted.read(encryptedAesKey);

        // Расшифрование ключа AES с помощью RSA
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] aesKeyBytes = rsaCipher.doFinal(encryptedAesKey);
        SecretKey aesKey = new SecretKeySpec(aesKeyBytes, "AES");

        // Расшифрование файла AES
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
        FileOutputStream fosDecrypted = new FileOutputStream(document.getParent() + "/" + document.getName().replace(".sde", ""));

        byte[] buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = fisEncrypted.read(buffer)) != -1) {
            byte[] output = aesCipher.update(buffer, 0, bytesRead);
            if (output != null) {
                fosDecrypted.write(output);
            }
        }
        byte[] outputBytes = aesCipher.doFinal();
        if (outputBytes != null) {
            fosDecrypted.write(outputBytes);
        }
        fisEncrypted.close();
        fosDecrypted.close();
    }

    @Override
    public void signDocument(File document, File privateKey) throws IOException, GeneralSecurityException, InvalidPassphraseException {
        PrivateKey rsaPrivateKey = SshKeyUtils.getPrivateKey(privateKey,
                "").getPrivateKey().getJCEPrivateKey();

        // Читаем содержимое документа
        FileInputStream fis = new FileInputStream(document);
        byte[] bytes = new byte[(int) document.length()];
        fis.read(bytes);
        fis.close();

        // Создаем объект для подписи
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(rsaPrivateKey);
        signature.update(bytes);

        // Подписываем содержимое документа
        byte[] signedBytes = signature.sign();

        // Сохраняем цифровую подпись в файл с расширением ".sig"
        FileOutputStream fos = new FileOutputStream(document.getParent() + "/" + document.getName() + ".sig");
        fos.write(signedBytes);
        fos.close();
    }

    @Override
    public boolean verifyDocument(File document, File signFile, File publicKey) throws IOException, GeneralSecurityException {
        // читаем публичный ключ
        PublicKey rsaPublicKey = SshKeyUtils.getPublicKey(publicKey).getJCEPublicKey();

        // Читаем содержимое документа
        FileInputStream fis = new FileInputStream(document);
        byte[] bytes = new byte[(int) document.length()];
        fis.read(bytes);
        fis.close();

        //Читаем цифровую подпись из файла
        fis = new FileInputStream(signFile);
        byte[] signature = new byte[(int) signFile.length()];
        fis.read(signature);
        fis.close();


        // Создаем объект для проверки подписи
        Signature signatureVerifier = Signature.getInstance("SHA256withRSA");
        signatureVerifier.initVerify(rsaPublicKey);
        signatureVerifier.update(bytes);

        // Проверяем подпись
        return signatureVerifier.verify(signature);
    }

    private byte[] generateIV() {
        // Генерация случайного вектора инициализации
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }

    private SecretKey generateSecKey() throws NoSuchAlgorithmException {
        // Генерация секретного ключа AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        return secretKey;
    }

    private void copy(byte[] a, byte[] b, byte[] c, byte[] res) {
        System.arraycopy(a, 0, res, 0, a.length);
        System.arraycopy(b, 0, res, a.length, b.length);
        System.arraycopy(c, 0, res, a.length + b.length, c.length);
    }

    private void copyReverse(byte[] res, byte[] a, byte[] b, byte[] c) {
        System.arraycopy(res, 0, a, 0, a.length);
        System.arraycopy(res, a.length, b, 0, b.length);
        System.arraycopy(res, a.length + b.length, c, 0, c.length);
    }
}
