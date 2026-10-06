package org.recap.UT.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.recap.model.UserForm;
import org.recap.security.AuthenticationService;
import org.recap.security.AuthorizationService;
import org.recap.security.realm.SimpleAuthorizationRealm;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author Charan Raj C created on 03/10/24
 */
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class SimpleAuthorizationRealmUT {

    @InjectMocks
    SimpleAuthorizationRealm realm;

    @Mock
    AuthenticationService authenticationService;

    @Mock
    AuthorizationService authorizationService;

    @Mock
    PrincipalCollection principals;

    @Mock
    AuthenticationToken authToken;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void doGetAuthorizationInfoTest(){
        Integer loginId = 123;
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        when(principals.fromRealm(anyString())).thenReturn(List.of(loginId));
        when(authorizationService.doAuthorizationInfo(any(SimpleAuthorizationInfo.class), eq(loginId)))
                .thenReturn(authorizationInfo);
        Object result = ReflectionTestUtils.invokeMethod(realm, "doGetAuthorizationInfo", principals);
        assertNotNull(result);
    }

    @Test
    public void testDoGetAuthenticationInfo_Success() {
        UsernamePasswordToken token = new UsernamePasswordToken("testUser", "testPassword");
        UserForm userForm = new UserForm();
        userForm.setUserId(123);
        userForm.setPasswordMatcher(true);
        when(authenticationService.doAuthentication(any(UsernamePasswordToken.class)))
                .thenReturn(userForm);
        AuthenticationInfo result = ReflectionTestUtils.invokeMethod(realm, "doGetAuthenticationInfo", token);
        assertNotNull(result);
        assertTrue(result instanceof SimpleAuthenticationInfo);
        assertEquals(userForm.getUserId(), result.getPrincipals().getPrimaryPrincipal());
        assertArrayEquals(token.getPassword(), (char[]) result.getCredentials());
        verify(authenticationService, times(1)).doAuthentication(token);
    }

    @Test
    public void testDoGetAuthenticationInfo() {
        UsernamePasswordToken token = new UsernamePasswordToken("testUser", "testPassword");
        when(authenticationService.doAuthentication(any(UsernamePasswordToken.class)))
                .thenReturn(null);
        AuthenticationInfo result = ReflectionTestUtils.invokeMethod(realm, "doGetAuthenticationInfo", token);
        verify(authenticationService, times(1)).doAuthentication(token);
    }

    @Test
    public void doGetAuthenticationInfoException() {
        AuthenticationToken token = null;
        AuthenticationInfo result = ReflectionTestUtils.invokeMethod(realm, "doGetAuthenticationInfo", token);
        assertNull(result);
    }


    @Test
    public void testDoGetAuthenticationInfo_InvalidPassword() {
        UsernamePasswordToken token = new UsernamePasswordToken("testUser", "testPassword");
        UserForm userForm = new UserForm();
        userForm.setUserId(123);
        userForm.setPasswordMatcher(false);
        when(authenticationService.doAuthentication(any(UsernamePasswordToken.class)))
                .thenReturn(userForm);
        AuthenticationInfo result = ReflectionTestUtils.invokeMethod(realm, "doGetAuthenticationInfo", token);
        assertNull(result);
        verify(authenticationService, times(1)).doAuthentication(token);
    }


}
