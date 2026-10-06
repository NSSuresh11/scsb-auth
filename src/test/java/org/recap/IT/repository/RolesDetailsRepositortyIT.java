package org.recap.IT.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.recap.IT.BaseTestCase;
import org.recap.model.jpa.PermissionEntity;
import org.recap.model.jpa.RoleEntity;
import org.recap.model.jpa.UsersEntity;
import org.recap.repository.jpa.PermissionsRepository;
import org.recap.repository.jpa.RolesDetailsRepositorty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by hemalathas on 22/12/16.
 */
@ExtendWith({SpringExtension.class})
public class RolesDetailsRepositortyIT extends BaseTestCase {

    @Autowired
    RolesDetailsRepositorty rolesDetailsRepositorty;

    @Autowired
    PermissionsRepository permissionsRepository;

    @PersistenceContext
    EntityManager entityManager;


    @Test
    public void createRole() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("patron");
        roleEntity.setRoleDescription("patron from CUL");
        roleEntity.setCreatedDate(new Date());
        roleEntity.setCreatedBy("superadmin");
        roleEntity.setLastUpdatedDate(new Date());
        roleEntity.setLastUpdatedBy("superadmin");
        Set<UsersEntity> users = new HashSet<UsersEntity>();
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);
        users.add(usersEntity);
        roleEntity.setUsers(users);
        try {
            PermissionEntity permissionEntity = getPermissionEntity();
            Set<PermissionEntity> permissionEntities = new HashSet<>();
            permissionEntities.add(permissionEntity);
            roleEntity.setPermissions(permissionEntities);
            RoleEntity savedRoleEntity = rolesDetailsRepositorty.save(roleEntity);
            assertEquals(roleEntity.getRoleName(), savedRoleEntity.getRoleName());
            assertEquals(roleEntity.getRoleDescription(), savedRoleEntity.getRoleDescription());
            assertEquals(roleEntity.getCreatedDate(), savedRoleEntity.getCreatedDate());
            assertEquals(roleEntity.getCreatedBy(), savedRoleEntity.getCreatedBy());
            assertEquals(roleEntity.getLastUpdatedBy(), savedRoleEntity.getLastUpdatedBy());
            assertEquals(roleEntity.getLastUpdatedDate(), savedRoleEntity.getLastUpdatedDate());
            assertEquals(roleEntity.getUsers(), savedRoleEntity.getUsers());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    public PermissionEntity getPermissionEntity() {
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermissionDesc("Permission to create Request");
        permissionEntity.setId(1);
        permissionEntity.setPermissionName("CreateRequest");
        PermissionEntity savedPermission = permissionsRepository.saveAndFlush(permissionEntity);
        entityManager.refresh(savedPermission);
        return savedPermission;
    }


}