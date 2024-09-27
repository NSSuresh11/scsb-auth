package org.recap.IT.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Test;
import org.recap.IT.BaseTestCase;
import org.recap.ScsbConstants;
import org.recap.model.UserForm;
import org.recap.model.jpa.RoleEntity;
import org.recap.model.jpa.UsersEntity;
import org.recap.repository.jpa.InstitutionDetailsRepository;
import org.recap.repository.jpa.RolesDetailsRepositorty;
import org.recap.security.AuthenticationService;
import org.recap.security.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dharmendrag on 1/2/17.
 */
public class AuthenticationServiceImplIT extends BaseTestCase {

    @Autowired
    public InstitutionDetailsRepository institutionDetailsRepository;

    @Autowired
    public AuthenticationService authenticationService;

    @Autowired
    public AuthenticationServiceImpl authenticationServiceImpl;

    @Autowired
    RolesDetailsRepositorty rolesDetailsRepositorty;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void testAuthentication()throws Exception{
        UsersEntity usersEntity = createUser();
        UserForm userForm = new UserForm();
        userForm.setUsername("SupportSuperAdmin");
        userForm.setInstitution(1);
        userForm.setPassword("12345");
        UsernamePasswordToken token=new UsernamePasswordToken(userForm.getUsername()+ ScsbConstants.TOKEN_SPLITER+"PUL",userForm.getPassword(),true);
        UserForm returnForm=authenticationService.doAuthentication(token);

        assertEquals(userForm.getUsername(),returnForm.getUsername());
        assertEquals(userForm.getInstitution(),returnForm.getInstitution());
        assertEquals(userForm.getWrongCredentials(),returnForm.getWrongCredentials());
        assertEquals(userForm.getPermissions(),returnForm.getPermissions());
    }

    public UsersEntity createUser(){
        UsersEntity usersEntity=new UsersEntity();
        usersEntity.setLoginId("SupportSuperAdmin");
        usersEntity.setEmailId("julius@example.org");
        usersEntity.setUserDescription("super admin");
        usersEntity.setInstitutionId(1);
        usersEntity.setCreatedBy("superadmin");
        usersEntity.setCreatedDate(new Date());
        usersEntity.setLastUpdatedBy("superadmin");
        usersEntity.setLastUpdatedDate(new Date());
        userRoles(usersEntity);

        UsersEntity savedUser=userRepo.saveAndFlush(usersEntity);
        entityManager.refresh(savedUser);

        assertEquals(usersEntity.getLoginId(),savedUser.getLoginId());

        UsersEntity byLoginId=userRepo.findByLoginId("SupportSuperAdmin");
        return byLoginId;

    }


    private void userRoles(UsersEntity usersEntity){
        List<RoleEntity> roleList=new ArrayList<RoleEntity>();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("patron");
        roleEntity.setRoleDescription("patron from CUL");
        roleEntity.setCreatedDate(new Date());
        roleEntity.setCreatedBy("superadmin");
        roleEntity.setLastUpdatedDate(new Date());
        roleEntity.setLastUpdatedBy("superadmin");
        RoleEntity savedRole=rolesDetailsRepositorty.saveAndFlush(roleEntity);
        entityManager.refresh(savedRole);
        roleList.add(savedRole);
        usersEntity.setUserRole(roleList);

    }


}
