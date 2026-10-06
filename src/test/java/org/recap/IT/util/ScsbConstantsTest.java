package org.recap.IT.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.recap.ScsbConstants;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith({SpringExtension.class})
public class ScsbConstantsTest {

    @InjectMocks
    ScsbConstants scsbConstants;

    @Test
    public void testPermissionMap() {
        assertEquals("permissionsMap", ScsbConstants.PERMISSION_MAP);
    }

    @Test
    public void testUserAuthentication() {
        assertEquals("isAuthenticated", ScsbConstants.USER_AUTHENTICATION);
    }

    @Test
    public void testUserId() {
        assertEquals("id", ScsbConstants.USER_ID);
    }

    @Test
    public void testUserInstitution() {
        assertEquals("userInstitution", ScsbConstants.USER_INSTITUTION);
    }

    @Test
    public void testRequestPrivilege() {
        assertEquals("isRequestAllowed", ScsbConstants.REQUEST_PRIVILEGE);
    }

    @Test
    public void testRequestAllPrivilege() {
        assertEquals("isRequestAllAllowed", ScsbConstants.REQUEST_ALL_PRIVILEGE);
    }

    @Test
    public void testRequestItemPrivilege() {
        assertEquals("isRequestItemAllowed", ScsbConstants.REQUEST_ITEM_PRIVILEGE);
    }

    @Test
    public void testCollectionPrivilege() {
        assertEquals("isCollectionAllowed", ScsbConstants.COLLECTION_PRIVILEGE);
    }

    @Test
    public void testReportsPrivilege() {
        assertEquals("isReportAllowed", ScsbConstants.REPORTS_PRIVILEGE);
    }

    @Test
    public void testSearchPrivilege() {
        assertEquals("isSearchAllowed", ScsbConstants.SEARCH_PRIVILEGE);
    }

    @Test
    public void testUserRolePrivilege() {
        assertEquals("isUserRoleAllowed", ScsbConstants.USER_ROLE_PRIVILEGE);
    }

    @Test
    public void testBarcodeRestrictedPrivilege() {
        assertEquals("isBarcodeRestricted", ScsbConstants.BARCODE_RESTRICTED_PRIVILEGE);
    }

    @Test
    public void testDeaccessionPrivilege() {
        assertEquals("isDeaccessionAllowed", ScsbConstants.DEACCESSION_PRIVILEGE);
    }

    @Test
    public void testSuperAdminUser() {
        assertEquals("isSuperAdmin", ScsbConstants.SUPER_ADMIN_USER);
    }

    @Test
    public void testSuperAdminRole() {
        assertEquals("Super Admin", ScsbConstants.SUPER_ADMIN_ROLE);
    }

    @Test
    public void testUserAdministratorRole() {
        assertEquals("User Administrator", ScsbConstants.USER_ADMINISTRATOR_ROLE);
    }

    @Test
    public void testRepositoryRole() {
        assertEquals("Repository", ScsbConstants.REPOSITORY_ROLE);
    }

    @Test
    public void testRequestPlaceId() {
        assertEquals(4, ScsbConstants.REQUEST_PLACE_ID);
    }

    @Test
    public void testEditCgdId() {
        assertEquals(2, ScsbConstants.EDIT_CGD_ID);
    }

    @Test
    public void testUserAuthErrorMsg() {
        assertEquals("authErrorMsg", ScsbConstants.USER_AUTH_ERRORMSG);
    }

    // Test more constants if necessary...

    @Test
    public void testErrorMessages() {
        assertEquals("SCSB authentication failed. Please try again.", ScsbConstants.ERROR_AUTHENTICATION_FAILED);
        assertEquals("User Name Password token is empty", ScsbConstants.ERROR_USER_TOKEN_EMPTY);
        assertEquals("User Name Password token validation fails", ScsbConstants.ERROR_USER_TOKEN_VALIDATION_FAILED);
        assertEquals("User is not available in database", ScsbConstants.ERROR_USER_NOT_AVAILABLE);
        assertEquals("Subject Authentication Failed", ScsbConstants.ERROR_SUBJECT_AUTHENTICATION_FAILED);
        assertEquals("Exception occurred in authentication : ", ScsbConstants.EXCEPTION_IN_AUTHENTICATION);
    }

    // More tests for other constants if required.
}
