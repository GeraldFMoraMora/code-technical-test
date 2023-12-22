package org.technical.test.model.service;

import javax.crypto.Cipher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAEncryptService {
    public void testCifrado(String pPass) throws Exception{
        KeyPair keyPair = generateKeyPair();

        System.out.println("Menseje inicial:\n"+pPass);

        byte[] encryptedBytes = encript(pPass, keyPair.getPublic());

        System.out.println("Mensaje cifrado:\n"+encryptedBytes);

        String decryptPass = decrypt(encryptedBytes, keyPair.getPrivate());

        System.out.println("Mensaje descifrado:\n"+decryptPass);
    }

    //Generar mi par de llaves RSA
    public KeyPair generateKeyPair() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    // Cifrar pass usando RSA_PKCS1_OAEP_PADDING
    public byte[] encript(String plaintext, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plaintext.getBytes());
    }

    // Descifrar pass usando RSA_PKCS1_OAEP_PADDING
    public String decrypt(byte[] cyperTxt, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(cyperTxt);
        return new String(decryptedBytes);

    }
}
