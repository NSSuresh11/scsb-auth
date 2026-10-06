package org.recap.UT.controller;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.recap.controller.AuthorizationController;
import org.recap.security.UserManagementService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

@ExtendWith({SpringExtension.class})
public class AuthorizationControllerTest {

    private AuthorizationController mockAuthorizationController;
    private Subject subject;
    private UserManagementService userManagementService;

    @BeforeEach
    public void setUp() {
        mockAuthorizationController = mock(AuthorizationController.class);
        userManagementService = mock(UserManagementService.class);
        subject = mock(Subject.class);
        SecurityManager securityManager = new DefaultSecurityManager();
        ThreadContext.bind(securityManager);
        new SubjectThreadState(subject).bind();
    }


    @Test
    public void roles() {
        boolean result = true;
        UsernamePasswordToken usernamePasswordToken = mock(UsernamePasswordToken.class);
        Mockito.doNothing().when(subject).login(usernamePasswordToken);
        Mockito.when(subject.getPrincipal()).thenReturn(9);
        Mockito.when(mockAuthorizationController.roles(usernamePasswordToken))
                .thenCallRealMethod();
        try {
            result = mockAuthorizationController.roles(usernamePasswordToken);
            assertFalse(result);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
