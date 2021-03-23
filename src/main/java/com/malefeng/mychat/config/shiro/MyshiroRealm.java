package com.malefeng.mychat.config.shiro;

import com.malefeng.mychat.bean.entity.UserEntity;
import com.malefeng.mychat.bean.enums.RuntimeErrorEnum;
import com.malefeng.mychat.bean.exception.MyException;
import com.malefeng.mychat.dao.mapper.UserDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyshiroRealm extends AuthorizingRealm {

    /*密码盐值*/
    private static final String SALT = "fengji";

    @Autowired
    private UserDao userDao;

    public MyshiroRealm() {
    }

    public MyshiroRealm(CredentialsMatcher matcher) {
        super(matcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        UserEntity userEntity = new UserEntity().setUserCode(userName);
        UserEntity user = userDao.selectOne(userEntity);
        if (null == user) {
            throw new MyException(RuntimeErrorEnum.UN_AUTH);
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassWord(),
                ByteSource.Util.bytes(MyshiroRealm.SALT),
                getName()
        );
        return authenticationInfo;
    }
}
