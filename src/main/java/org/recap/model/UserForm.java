package org.recap.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dharmendrag on 29/11/16.
 */
@Schema(name="userAuthRequest",description = "Model to show user details")
@Data
public class UserForm {

    @Schema(name="userId",description="primary key against each user",maxLength = 0)
    private Integer userId;

    @Schema(name="username",description="Login Id or login name",maxLength = 1)
    private String username;

    @Schema(name="password",description="password for login",maxLength = 2)
    private String password;

    @Schema(name="institution",description="User's Institution",maxLength = 3 , allowableValues = "1,2,3")
    private int institution;

    private String userInstitution;

    private Set<String> permissions=new HashSet<>();

    private String wrongCredentials;

    private boolean passwordMatcher;

    /**
     * Gets permissions.
     *
     * @return the permissions
     */
    public Set<String> getPermissions() {
        return permissions;
    }

    /**
     * Sets permissions.
     *
     * @param permissions the permissions
     */
    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

}
