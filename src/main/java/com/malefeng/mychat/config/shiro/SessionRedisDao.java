package com.malefeng.mychat.config.shiro;


import com.malefeng.mychat.util.RedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * redis实现共享session
 *
 * @author mlf
 * @date 2021-03-16
 */
@Component
public class SessionRedisDao extends EnterpriseCacheSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(SessionRedisDao.class);

    @Value("${property.session.timeOut}")
    private Long expireTime;

    public static String prefix = "shiro:session:";

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        logger.debug("创建session:{}", session.getId());
        redisUtil.set(prefix + sessionId.toString(), session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.debug("获取session:{}", sessionId);
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = (Session) redisUtil.get(prefix + sessionId.toString(),new StringRedisSerializer());
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        logger.debug("更新session:{}", session.getId());
        String key = prefix + session.getId().toString();
        if (!redisUtil.hasKey(key)) {
            redisUtil.set(key, session);
        }
        redisUtil.expire(key, expireTime, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void doDelete(Session session) {
        logger.debug("删除session:{}", session.getId());
        super.doDelete(session);
        redisUtil.del(prefix + session.getId().toString());
    }
}