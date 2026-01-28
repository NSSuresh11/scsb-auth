package org.recap.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.recap.ScsbConstants;
import org.recap.security.AuthorizationServiceImpl;
import org.recap.security.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dharmendrag on 10/1/17.
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthorizationController {


    @Autowired
    private AuthorizationServiceImpl authorizationService;

    /**
     * The User management service.
     */
    @Autowired
    UserManagementService userManagementService;


    /**
     * Check the privilege for the search record screen
     *
     * @param request the request
     * @param token   the token
     * @return the boolean
     */
    @PostMapping(value="/search")
    @Operation(summary="search authentication",description="Used to Authenticate User")
    @ApiResponse(responseCode = "200", description = "search authentication success", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Boolean.class)) })
    public boolean searchRecords(HttpServletRequest request, @RequestBody UsernamePasswordToken token) {
        return authorizationService.checkPrivilege(token, userManagementService.getPermissionId(ScsbConstants.SCSB_SEARCH_EXPORT));
    }

    /**
     * Check the privilege for the request screen
     *
     * @param token the token
     * @return the boolean
     */
    @PostMapping(value="/request")
    @Operation(summary="request authentication",description ="Used to Authenticate User")
    @ApiResponse(responseCode = "200", description = "request authentication success", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Boolean.class)) })
    public Boolean request(@RequestBody UsernamePasswordToken token) {
        return authorizationService.checkPrivilege(token,userManagementService.getPermissionId(ScsbConstants.REQUEST_PLACE));
    }

    /**
     * Check the privilege for the collection screen
     *
     * @param token the token
     * @return the boolean
     */
    @PostMapping(value = "/collection")
    @Operation(summary="collection authentication",description ="Used to Authenticate User")
    @ApiResponse(responseCode = "200", description = "collection authentication success", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Boolean.class)) })
    public Boolean collection(@RequestBody UsernamePasswordToken token) {
        return authorizationService.checkPrivilege(token, userManagementService.getPermissionId(ScsbConstants.WRITE_GCD));

    }

    /**
     * Check the privilege for the report screen
     *
     * @param usernamePasswordToken the username password token
     * @return the boolean
     */
    @PostMapping(value="/reports")
    @Operation(summary="reports authentication",description ="Used to Authenticate User")
    @ApiResponse(responseCode = "200", description = "reports authentication success", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Boolean.class)) })
    public boolean reports(@RequestBody UsernamePasswordToken usernamePasswordToken) {
        return authorizationService.checkPrivilege(usernamePasswordToken,userManagementService.getPermissionId(ScsbConstants.VIEW_PRINT_REPORTS));

    }

    /**
     * Check the privilege for the user screen
     *
     * @param usernamePasswordToken the username password token
     * @return the boolean
     */
    @PostMapping(value="/userRoles")
    @Operation(summary="user authentication",description ="Used to Authorizer User for Users")
    @ApiResponse(responseCode = "200", description = "User & Role authentication success", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Boolean.class)) })
    public boolean userRoles(@RequestBody UsernamePasswordToken usernamePasswordToken) {
        return authorizationService.checkPrivilege(usernamePasswordToken,userManagementService.getPermissionId(ScsbConstants.CREATE_USER));

    }

    /**
     * Check the privilege for the roles screen
     *
     * @param usernamePasswordToken the username password token
     * @return the boolean
     */
    @PostMapping(value="/roles")
    @Operation(summary="roles authentication",description ="Used to Authorizer User for Roles")
    @ApiResponse(responseCode = "200", description = "Role authentication success", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Boolean.class)) })
    public boolean roles(@RequestBody UsernamePasswordToken usernamePasswordToken) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        List<Integer> roleId = userManagementService.getRolesForUser((Integer) subject.getPrincipal());
        return roleId.contains(1);
    }

    /**
     * Check the privilege for the roles screen
     *
     * @param usernamePasswordToken the username password token
     * @return the boolean
     */
    @PostMapping(value="/touchExistingSession")
    @Operation(summary="touch existing session",description ="Used to touch existing session for the user")
    @ApiResponse(responseCode = "200", description = "Successfully extended the session", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Boolean.class)) })
    public boolean touchExistingSession(@RequestBody UsernamePasswordToken usernamePasswordToken) {
        Subject subject = authorizationService.getSubject(usernamePasswordToken);
        try {
            subject.getSession().touch();
            return true;
        } catch (InvalidSessionException e) {
            log.error("Invalid Session Exception",e);
        }
        return false;
    }

    @PostMapping(value="/bulkRequest")
    public boolean bulkRequest(@RequestBody UsernamePasswordToken usernamePasswordToken) {
        return authorizationService.checkPrivilege(usernamePasswordToken,userManagementService.getPermissionId(ScsbConstants.BULK_REQUEST));

    }
    @PostMapping(value="/monitoring")
    public boolean monitoring(@RequestBody UsernamePasswordToken usernamePasswordToken){
        return authorizationService.checkPrivilege(usernamePasswordToken,userManagementService.getPermissionId(ScsbConstants.MONITORING_REQUEST));
    }
    @PostMapping(value="/logging")
    public boolean logging(@RequestBody UsernamePasswordToken usernamePasswordToken){
        return authorizationService.checkPrivilege(usernamePasswordToken,userManagementService.getPermissionId(ScsbConstants.LOGGING_REQUEST));
    }
    @PostMapping(value="/dataExport")
    public boolean dataExport(@RequestBody UsernamePasswordToken usernamePasswordToken){
        return authorizationService.checkPrivilege(usernamePasswordToken,userManagementService.getPermissionId(ScsbConstants.DATAEXPORT_REQUEST));
    }
}
