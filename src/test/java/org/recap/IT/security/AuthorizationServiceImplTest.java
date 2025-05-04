package org.recap.IT.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.recap.ScsbConstants;
import org.recap.security.AuthorizationServiceImpl;
import org.recap.security.UserManagementService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Charan Raj C created on 04/05/25
 */
public class AuthorizationServiceImplTest {

    @InjectMocks
    private AuthorizationServiceImpl authorizationServiceImpl;

    @Mock
    private UserManagementService userManagementService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckPrivilege_EditCgd_Success() {
        UsernamePasswordToken token = new UsernamePasswordToken("user", "pass");
        Subject subject = mock(Subject.class);
        Session session = mock(Session.class);
        Map<Integer, String> permissions = new HashMap<>();
        permissions.put(1, "permission:write");
        permissions.put(2, "permission:deaccession");
        when(userManagementService.getPermissionId(ScsbConstants.WRITE_GCD)).thenReturn(1);
        when(userManagementService.getPermissionId(ScsbConstants.DEACCESSION)).thenReturn(2);
        try (MockedStatic<UserManagementService> mockedStatic = Mockito.mockStatic(UserManagementService.class)) {
            mockedStatic.when(() -> UserManagementService.getPermissions(subject)).thenReturn(permissions);
            authorizationServiceImpl.setSubject(token, subject);
            when(subject.getSession()).thenReturn(session);
            doNothing().when(session).touch();
            when(subject.isPermitted("permission:write")).thenReturn(true);
            boolean result = authorizationServiceImpl.checkPrivilege(token, ScsbConstants.EDIT_CGD_ID);
            assertTrue(result);
        }
    }

    @Test
    public void testCheckPrivilege_RequestPlace_Success() {
        UsernamePasswordToken token = new UsernamePasswordToken("user", "pass");
        Subject subject = mock(Subject.class);
        Session session = mock(Session.class);
        Map<Integer, String> permissions = new HashMap<>();
        permissions.put(3, "permission:requestPlace");
        when(userManagementService.getPermissionId(ScsbConstants.REQUEST_PLACE)).thenReturn(3);
        try (MockedStatic<UserManagementService> mockedStatic = Mockito.mockStatic(UserManagementService.class)) {
            mockedStatic.when(() -> UserManagementService.getPermissions(subject)).thenReturn(permissions);
            authorizationServiceImpl.setSubject(token, subject);
            when(subject.getSession()).thenReturn(session);
            doNothing().when(session).touch();
            when(subject.isPermitted("permission:requestPlace")).thenReturn(true);
            boolean result = authorizationServiceImpl.checkPrivilege(token, ScsbConstants.REQUEST_PLACE_ID);
            assertTrue(result);
        }
    }

    @Test
    public void testCheckPrivilege_DefaultPermission_Success() {
        UsernamePasswordToken token = new UsernamePasswordToken("user", "pass");
        Subject subject = mock(Subject.class);
        Session session = mock(Session.class);
        Map<Integer, String> permissions = new HashMap<>();
        permissions.put(99, "permission:default");

        try (MockedStatic<UserManagementService> mockedStatic = Mockito.mockStatic(UserManagementService.class)) {
            mockedStatic.when(() -> UserManagementService.getPermissions(subject)).thenReturn(permissions);
            authorizationServiceImpl.setSubject(token, subject);
            when(subject.getSession()).thenReturn(session);
            doNothing().when(session).touch();
            when(subject.isPermitted("permission:default")).thenReturn(true);
            boolean result = authorizationServiceImpl.checkPrivilege(token, 99);
            assertTrue(result);
        }
    }

    @Test
    public void testCheckPrivilege_InvalidPermission() {
        UsernamePasswordToken token = new UsernamePasswordToken("user", "pass");
        Subject subject = mock(Subject.class);
        Session session = mock(Session.class);
        Map<Integer, String> permissions = new HashMap<>();
        permissions.put(999, "permission:invalid");
        try (MockedStatic<UserManagementService> mockedStatic = Mockito.mockStatic(UserManagementService.class)) {
            mockedStatic.when(() -> UserManagementService.getPermissions(subject)).thenReturn(permissions);
            authorizationServiceImpl.setSubject(token, subject);
            when(subject.getSession()).thenReturn(session);
            doNothing().when(session).touch();
            when(subject.isPermitted("permission:invalid")).thenReturn(false);
            doNothing().when(subject).logout();
            boolean result = authorizationServiceImpl.checkPrivilege(token, 999);
            assertFalse(result);
        }
    }


    @Test
    public void testCheckPrivilege_EditCgd_NoPermissions() {
        UsernamePasswordToken token = new UsernamePasswordToken("user", "pass");
        Subject subject = mock(Subject.class);
        Session session = mock(Session.class);
        Map<Integer, String> permissions = new HashMap<>();
        permissions.put(1, "permission:write");
        permissions.put(2, "permission:deaccession");
        when(userManagementService.getPermissionId(ScsbConstants.WRITE_GCD)).thenReturn(1);
        when(userManagementService.getPermissionId(ScsbConstants.DEACCESSION)).thenReturn(2);
        try (MockedStatic<UserManagementService> mockedStatic = Mockito.mockStatic(UserManagementService.class)) {
            mockedStatic.when(() -> UserManagementService.getPermissions(subject)).thenReturn(permissions);
            authorizationServiceImpl.setSubject(token, subject);
            when(subject.getSession()).thenReturn(session);
            doNothing().when(session).touch();
            when(subject.isPermitted("permission:write")).thenReturn(false);
            when(subject.isPermitted("permission:deaccession")).thenReturn(false);
            doNothing().when(subject).logout();
            boolean result = authorizationServiceImpl.checkPrivilege(token, ScsbConstants.EDIT_CGD_ID);
            assertFalse(result);
        }
    }

    @Test
    public void testCheckPrivilege_RequestPlace_NoPermissions() {
        UsernamePasswordToken token = new UsernamePasswordToken("user", "pass");
        Subject subject = mock(Subject.class);
        Session session = mock(Session.class);
        Map<Integer, String> permissions = new HashMap<>();
        permissions.put(3, "permission:requestPlace");
        permissions.put(4, "permission:requestPlaceAll");
        permissions.put(5, "permission:requestItems");
        when(userManagementService.getPermissionId(ScsbConstants.REQUEST_PLACE)).thenReturn(3);
        when(userManagementService.getPermissionId(ScsbConstants.REQUEST_PLACE_ALL)).thenReturn(4);
        when(userManagementService.getPermissionId(ScsbConstants.REQUEST_ITEMS)).thenReturn(5);
        try (MockedStatic<UserManagementService> mockedStatic = Mockito.mockStatic(UserManagementService.class)) {
            mockedStatic.when(() -> UserManagementService.getPermissions(subject)).thenReturn(permissions);
            authorizationServiceImpl.setSubject(token, subject);
            when(subject.getSession()).thenReturn(session);
            doNothing().when(session).touch();
            when(subject.isPermitted("permission:requestPlace")).thenReturn(false);
            when(subject.isPermitted("permission:requestPlaceAll")).thenReturn(false);
            when(subject.isPermitted("permission:requestItems")).thenReturn(false);
            doNothing().when(subject).logout();
            boolean result = authorizationServiceImpl.checkPrivilege(token, ScsbConstants.REQUEST_PLACE_ID);
            assertFalse(result);
        }
    }

    @Test
    public void testCheckPrivilege_DefaultPermission_NotPermitted() {
        UsernamePasswordToken token = new UsernamePasswordToken("user", "pass");
        Subject subject = mock(Subject.class);
        Session session = mock(Session.class);
        Map<Integer, String> permissions = new HashMap<>();
        permissions.put(99, "permission:default");
        try (MockedStatic<UserManagementService> mockedStatic = Mockito.mockStatic(UserManagementService.class)) {
            mockedStatic.when(() -> UserManagementService.getPermissions(subject)).thenReturn(permissions);
            authorizationServiceImpl.setSubject(token, subject);
            when(subject.getSession()).thenReturn(session);
            doNothing().when(session).touch();
            when(subject.isPermitted("permission:default")).thenReturn(false);
            doNothing().when(subject).logout();
            boolean result = authorizationServiceImpl.checkPrivilege(token, 99);
            assertFalse(result);
        }
    }
}
