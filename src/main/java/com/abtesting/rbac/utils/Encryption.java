package com.abtesting.rbac.utils;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.exceptions.RetryableException;
import com.abtesting.rbac.exceptions.StaticErrorsEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;

@Component
public class Encryption {

    private static String HASHING_ALGORITHM;

    /**
     * Encrypts data into Hash mentioned in Property file, either SHA-256 or MD5
     *
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        if (StringUtils.isEmpty(data)) {
            return data;
        }
        try {
            MessageDigest md = MessageDigest.getInstance(HASHING_ALGORITHM);
            byte[] messageDigest = md.digest(data.getBytes());
            BigInteger number = new BigInteger(CommonConstants.EncryptionConstants.BIG_INTEGER_SIGN, messageDigest);
            String hashText = number.toString(CommonConstants.EncryptionConstants.HEXADECIMAL_ENCODING);
            while (hashText.length() < CommonConstants.EncryptionConstants.MINIMUM_HASH_LENGTH) {
                hashText = CommonConstants.EncryptionConstants.HASH_PREFIX + hashText;
            }
            return hashText;
        } catch (Exception err) {
            throw new RetryableException(StaticErrorsEnum.SOMETHING_WENT_WRONG);
        }
    }

    @Value("${password.hashing.algorithm}")
    public void setHashingAlgorithm(String hashingAlgorithm) {
        HASHING_ALGORITHM = hashingAlgorithm;
    }
}