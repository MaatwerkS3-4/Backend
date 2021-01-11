package com.ProftaakS34.Opinion.Mocks;

import com.ProftaakS34.Opinion.domain.service.PasswordService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;



@Service
public class MockPasswordService extends PasswordService {

    @Override
    public String getEncryptedPassword(String password, String salt) {
        return password;
    }

    @Override
    public String getNewSalt() {
        return "";
    }


}
