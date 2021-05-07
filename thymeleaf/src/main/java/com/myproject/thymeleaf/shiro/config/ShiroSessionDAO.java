package com.myproject.thymeleaf.shiro.config;

import com.myproject.thymeleaf.exception.ProjectEnum;
import com.myproject.thymeleaf.exception.ProjectGlobalException;
import com.myproject.thymeleaf.shiro.util.SerializeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Created by ThomasYu on 2019-07-31
 */
@Slf4j
public class ShiroSessionDAO extends AbstractSessionDAO {

    private static final String PREFIX = "shiro:session:";

    @Resource(name = "shiroRedisTemplate")
    private RedisTemplate<String, Object> shiroRedisTemplate;

    public ShiroSessionDAO() {

    }

    public ShiroSessionDAO(RedisTemplate<String, Object> shiroRedisTemplate) {
        this.shiroRedisTemplate = shiroRedisTemplate;
    }

    @Override
    protected Serializable doCreate(Session session) {
        checkSession(session);
        Serializable sessionId = this.generateSessionId(session);
        assignSessionId(session, sessionId);

        String key = getKey(sessionId);
        long timeout = session.getTimeout();
        byte[] value = SerializeUtils.serialize(session);
        if (log.isDebugEnabled()) {
            log.debug("-> create session {}", session);
        }
        shiroRedisTemplate.opsForValue()
                .setIfAbsent(key, value, timeout, TimeUnit.MILLISECONDS);

        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            log.warn("-w-w-w-> can find session by null session id");
            return null;
        }
        Session session;

//        if ((session = sessionInMem.get()) != null) {
//            return session;
//        }

        if (log.isDebugEnabled()) {
            log.debug("-> read session from redis id {}", sessionId);
        }
        byte[] value = (byte[]) shiroRedisTemplate.opsForValue().get(getKey(sessionId));
        if (ArrayUtils.isEmpty(value)) {
            log.warn("-w-w-w-> can't find session from redis by id {}", sessionId);
            return null;
        }

        session = (Session) SerializeUtils.deserialize(value);
//        sessionInMem.set(session);
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        checkSession(session);
        String key = getKey(session.getId());
        long timeout = session.getTimeout();
        byte[] value = SerializeUtils.serialize(session);
        if (log.isDebugEnabled()) {
            log.debug("-> update session {}", session);
        }
        shiroRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
//        sessionInMem.set(session);
    }

    @Override
    public void delete(Session session) {
        checkSession(session);
        String key = getKey(session.getId());
        shiroRedisTemplate.delete(key);
        clearSessionInMem();
    }

    public void clearSessionInMem() {
        log.debug("-> remove session in memory ");
//        sessionInMem.remove();
    }

    private void checkSession(Session session) {
        if (session == null) {
            log.error("-x-x-x-> session is null!");
            throw new ProjectGlobalException(ProjectEnum.SESSION_IS_NULL);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }

    private String getKey(Serializable sessionId) {
        return PREFIX + sessionId;
    }
}
