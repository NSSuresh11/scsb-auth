package org.recap.UT.security;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.recap.model.jpa.RoleEntity;
import org.recap.model.jpa.UsersEntity;
import org.recap.repository.jpa.PermissionsRepository;
import org.recap.repository.jpa.UserDetailsRepository;
import org.recap.security.UserManagementService;


/**
 * @author Charan Raj C created on 03/10/24
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class UserManagementServiceTest {

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Mock
    private PermissionsRepository permissionsRepository;

    @InjectMocks
    private UserManagementService userManagementService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRolesListForUser() {
        Integer userId = 1;
        UsersEntity usersEntity = mock(UsersEntity.class);
        RoleEntity role1 = mock(RoleEntity.class);
        RoleEntity role2 = mock(RoleEntity.class);
        when(role1.getRoleName()).thenReturn("ROLE_ADMIN");
        when(role2.getRoleName()).thenReturn("ROLE_USER");
        List<RoleEntity> roles = List.of(role1, role2);
        when(usersEntity.getUserRole()).thenReturn(roles);
        when(userDetailsRepository.findById(userId)).thenReturn(Optional.of(usersEntity));
        List<String> result = userManagementService.getRolesListForUser(userId);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("ROLE_ADMIN"));
        assertTrue(result.contains("ROLE_USER"));
        verify(userDetailsRepository, times(1)).findById(userId);
        verify(usersEntity, times(1)).getUserRole();
    }

    @Test
    public void testGetRolesListForUser_UserDoesNotExist() {
        Integer userId = 1;
        when(userDetailsRepository.findById(userId)).thenReturn(Optional.empty());
        List<String> result = userManagementService.getRolesListForUser(userId);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userDetailsRepository, times(1)).findById(userId);
        verifyNoMoreInteractions(userDetailsRepository);
    }

    @Test
    public void testGetRolesListForUser_UserExistsNoRoles() {
        Integer userId = 1;
        UsersEntity usersEntity = mock(UsersEntity.class);
        when(usersEntity.getUserRole()).thenReturn(Collections.emptyList());
        when(userDetailsRepository.findById(userId)).thenReturn(Optional.of(usersEntity));
        List<String> result = userManagementService.getRolesListForUser(userId);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userDetailsRepository, times(1)).findById(userId);
        verify(usersEntity, times(1)).getUserRole();
    }
}
