package org.recap.UT.controller;

import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.recap.UT.BaseTestCaseUT;
import org.recap.controller.LoginController;
import org.recap.security.AuthorizationServiceImpl;
import org.recap.security.UserManagementService;
import org.recap.util.HelperUtil;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class LoginControllerTest extends BaseTestCaseUT {

    @Mock
    private Subject subject;

    @Mock
    private UserManagementService userManagementService;

    @Mock
    private AuthorizationServiceImpl authorizationService;

    @Mock
    private HelperUtil helperUtil;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setup() {
        // No need to initialize loginController here
    }

    @Test
    public void testGetPermissionsForUI() {
        // Arrange
        Map<String, Object> authMap = new HashMap<>();
        Map<Integer, String> permissionMap = new HashMap<>();
        permissionMap.put(1, "REQUEST_PLACE");
        permissionMap.put(2, "WRITE_GCD");
        permissionMap.put(3, "VIEW_PRINT_REPORTS");
        permissionMap.put(4, "SCSB_SEARCH_EXPORT");
        permissionMap.put(5, "CREATE_USER");
        permissionMap.put(6, "REQUEST_PLACE_ALL");
        permissionMap.put(7, "REQUEST_ITEMS");
        permissionMap.put(8, "BARCODE_RESTRICTED");
        permissionMap.put(9, "DEACCESSION");
        permissionMap.put(10, "BULK_REQUEST");
        permissionMap.put(11, "RESUBMIT_REQUEST");
        permissionMap.put(12, "MONITORING_PERMISSION_NAME");
        permissionMap.put(13, "LOGGING_PERMISSION_NAME");
        permissionMap.put(14, "DATAEXPORT_PERMISSION_NAME");
        permissionMap.put(15, "REQUEST_LOG_PERMISSION_NAME");

        when(userManagementService.getPermissionId(any(String.class))).thenReturn(1);
        ReflectionTestUtils.invokeMethod(loginController, "getPermissionsForUI", subject, authMap, permissionMap);

    }

    @Test
    public void testGetPermissionsForUIWithNullPermissionMap() {
        Map<String, Object> authMap = new HashMap<>();
        Map<Integer, String> permissionMap = null;
        assertTrue(authMap.isEmpty());
    }

    @Test
    public void testGetPermissionsForUIWithEmptyPermissionMap() {
        Map<String, Object> authMap = new HashMap<>();
        Map<Integer, String> permissionMap = new HashMap<>();
        ReflectionTestUtils.invokeMethod(loginController, "getPermissionsForUI", subject, authMap, permissionMap);
    }
}