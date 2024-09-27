package org.recap.IT.repository;

import org.junit.Test;
import org.recap.IT.BaseTestCase;
import org.recap.model.jpa.PermissionEntity;
import org.recap.repository.jpa.PermissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by dharmendrag on 1/2/17.
 */
public class PermissionsRepositoryIT extends BaseTestCase {


    @Autowired
    PermissionsRepository permissionsRepository;

    @PersistenceContext
    EntityManager entityManager;



    @Test
    public void createPermission() {

        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermissionDesc("Permission to edit user");
        permissionEntity.setPermissionName("EditUser");
        PermissionEntity savedPermission = permissionsRepository.saveAndFlush(permissionEntity);
        entityManager.refresh(savedPermission);

        assertNotNull(savedPermission);
        assertEquals(permissionEntity.getPermissionName(),savedPermission.getPermissionName());
        assertEquals(permissionEntity.getPermissionDesc(),savedPermission.getPermissionDesc());

        List<PermissionEntity> permissionEntities=permissionsRepository.findAll();
        permissionEntities.contains(savedPermission);

    }

}
