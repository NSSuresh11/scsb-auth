package org.recap.IT;


import org.apache.shiro.mgt.SecurityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.recap.Main;
import org.recap.config.ApacheShiroCustomConfig;
import org.recap.repository.jpa.InstitutionDetailsRepository;
import org.recap.repository.jpa.PermissionsRepository;
import org.recap.repository.jpa.RolesDetailsRepositorty;
import org.recap.repository.jpa.UserDetailsRepository;
import org.recap.security.AuthenticationService;
import org.recap.security.AuthorizationService;
import org.recap.security.realm.SimpleAuthorizationRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
@Transactional
@Rollback()
public class BaseTestCase {

    protected MockMvc mockMvc;
    protected HttpMessageConverter mappingJackson2HttpMessageConverter;
    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext applicationContext;

    protected SecurityManager securityManager;

    @Autowired
    public ApacheShiroCustomConfig shiroConfig;

    @Autowired
    public SimpleAuthorizationRealm simpleAuthorizationRealm;

    @Mock
    public AuthenticationService authenticationService;

    @Autowired
    public AuthorizationService authorizationService;

    @Autowired
    public UserDetailsRepository userRepo;

    @Autowired
    public InstitutionDetailsRepository institutionDetailsRepository;

    @Autowired
    RolesDetailsRepositorty rolesDetailsRepositorty;

    @Autowired
    PermissionsRepository permissionsRepository;

    @Autowired
    public void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
        Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @Test
    public void loadContexts() {
        System.out.println();
    }

}