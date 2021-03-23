package com.malefeng.mychat.config.shiro;

import com.malefeng.mychat.util.StringUtil;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {

    public MyHashedCredentialsMatcher() {
    }

    public MyHashedCredentialsMatcher(String hashAlgorithmName) {
        super(hashAlgorithmName);
    }

    @SneakyThrows
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
            Object tokenHashedCredentials = StringUtil.MD5(new String((char[]) token.getCredentials()));
            return this.equals(tokenHashedCredentials, info.getCredentials());
    }
}
