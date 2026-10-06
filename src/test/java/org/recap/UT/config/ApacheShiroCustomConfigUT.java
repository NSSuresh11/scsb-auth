package org.recap.UT.config;

import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.subject.support.DefaultWebSubjectContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.recap.UT.BaseTestCaseUT;
import org.recap.config.ApacheShiroCustomConfig;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApacheShiroCustomConfigUT extends BaseTestCaseUT {

    @InjectMocks
    ApacheShiroCustomConfig apacheShiroCustomConfig;

    @Mock
    AuthorizationException authorizationException;

    @Mock
    Model model;

    private String sessionTimeOut = "2700000";

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(apacheShiroCustomConfig, "sessionTimeOut", sessionTimeOut);
    }

    @Test
    public void subjectException() {
        Subject subject = apacheShiroCustomConfig.subject();
        assertNotNull(subject);
    }

    @Test
    public void handleException() {
        String error = apacheShiroCustomConfig.handleException(authorizationException, model);
        assertNotNull(error);
        assertEquals("error", error);
    }

    @Test
    public void authenticator() {
        ModularRealmAuthenticator modularRealmAuthenticator = apacheShiroCustomConfig.authenticator();
        assertNotNull(modularRealmAuthenticator);
    }

    @Test
    public void authorizer() {
        ModularRealmAuthorizer modularRealmAuthorizer = apacheShiroCustomConfig.authorizer();
        assertNotNull(modularRealmAuthorizer);
    }

    @Test
    public void sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = apacheShiroCustomConfig.sessionManager();
        assertNotNull(defaultWebSessionManager);
    }

    @Test
    public void securityManager() {
        SecurityManager securityManager = apacheShiroCustomConfig.securityManager();
        assertNotNull(securityManager);
    }

    @Test
    public void getSubjectContext() {
        DefaultWebSubjectContext defaultWebSubjectContext = apacheShiroCustomConfig.getSubjectContext();
        assertNotNull(defaultWebSubjectContext);
    }

    @Test
    public void shiroFilterChainDefinition() {
        ShiroFilterChainDefinition shiroFilterChainDefinition = apacheShiroCustomConfig.shiroFilterChainDefinition();
        assertNotNull(shiroFilterChainDefinition);
    }

    @Test
    public void shiroFilterFactoryBean() {
        SecurityManager securityManager = new DefaultWebSecurityManager();
        ShiroFilterFactoryBean shiroFilterFactoryBean = apacheShiroCustomConfig.shiroFilterFactoryBean(securityManager);
        assertNotNull(shiroFilterFactoryBean);


    }

}
