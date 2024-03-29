package org.technical.test.model.service;

import javax.crypto.Cipher;

import org.technical.test.model.dao.UserKeyDao;
import org.technical.test.model.entity.Customer;
import org.technical.test.model.entity.UserKey;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@ApplicationScoped
public class RSAEncryptService {

    @Inject 
    UserKeyDao userKeyDao;

    @Inject
    CsrfTokenManagerService tokenManagerService;

    public void testCifrado(String pPass) throws Exception{
        KeyPair keyPair = generateKeyPair();

        System.out.println("Menseje inicial:\n"+pPass);

        byte[] encryptedBytes = encript(pPass, keyPair.getPublic());

        System.out.println("Mensaje cifrado:\n"+encryptedBytes);

        // Convertir claves a bytes
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        // Convertir bytes a strings Base64 para almacenar en la base de datos
        String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKeyBytes);
        String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKeyBytes);

        // Desencriptar
        String decryptPass = decrypt(encryptedBytes, retrieveFromDatabase(publicKeyBase64, privateKeyBase64).getPrivate());

        System.out.println("Mensaje descifrado:\n"+decryptPass);
    }

    public Customer encriptPassword(Customer customer) throws Exception{
        //Genero primero las llaves publica y privada
        KeyPair keyPair = generateKeyPair();

        //Encripto la contrasena
        byte[] encryptedBytes = encript(customer.getPassword(), keyPair.getPublic());

        // Seteo la contrasena por la contrasena encriptada
        customer.setPassword(Base64.getEncoder().encodeToString(encryptedBytes));

        // Convertir claves a bytes
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        // Convertir bytes a strings Base64 para almacenar en la base de datos
        String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKeyBytes);
        String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKeyBytes);

        UserKey userKey = new UserKey();
        userKey = tokenManagerService.generateToken(userKey);
        userKey.setPublic64_key(publicKeyBase64);
        userKey.setPrivate64_key(privateKeyBase64);
        userKey.setCustomer(customer);

        userKeyDao.persist(userKey);

        return customer;
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

    // Recuperar claves desde la base de datos y convertirlas de nuevo a objetos KeyPair
    public KeyPair retrieveFromDatabase(String public64, String private64) throws Exception {
        String retrievedPublicKeyBase64 = public64; // Obtener desde la base de datos
        String retrievedPrivateKeyBase64 = private64; // Obtener desde la base de datos

        // Convertir strings Base64 a bytes
        byte[] retrievedPublicKeyBytes = Base64.getDecoder().decode(retrievedPublicKeyBase64);
        byte[] retrievedPrivateKeyBytes = Base64.getDecoder().decode(retrievedPrivateKeyBase64);

        // Convertir bytes a objetos PublicKey y PrivateKey
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey retrievedPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(retrievedPublicKeyBytes));
        PrivateKey retrievedPrivateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(retrievedPrivateKeyBytes));

        return new KeyPair(retrievedPublicKey, retrievedPrivateKey);
    }
}
