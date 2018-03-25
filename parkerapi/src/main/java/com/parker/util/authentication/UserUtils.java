package com.parker.util.authentication;

import com.parker.domain.exception.user.UserPasswordException;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

public class UserUtils {
    public static String encryptPassword(String password) throws UserPasswordException {
        //todo: find a better hashing method than MD5
//        if (StringUtils.isEmpty(password)) {
//            throw new UserPasswordException("Password can not be empty");
//        }
//
//        byte[] bytes = DigestUtils.md5Digest(password.getBytes());
//        return new String(bytes);

        return password;
    }
}
