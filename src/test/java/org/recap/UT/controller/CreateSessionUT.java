package org.recap.UT.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.support.DefaultWebSubjectContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.recap.controller.LoginController;
import org.recap.model.LoginValidator;
import org.recap.repository.jpa.InstitutionDetailsRepository;
import org.recap.repository.jpa.UserDetailsRepository;
import org.recap.security.AuthenticationService;
import org.recap.security.AuthorizationServiceImpl;
import org.recap.security.UserManagementService;
import org.recap.security.UserService;
import org.recap.util.HelperUtil;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;


/** @author Charan Raj C created on 03/10/24 */
@ExtendWith({SpringExtension.class})
public class CreateSessionUT {

    @Mock
    private InstitutionDetailsRepository institutionDetailsRepository;

    private LoginValidator loginValidator = new LoginValidator();

    @Mock
    private UserService userService;

    @Mock
    private AuthorizationServiceImpl authorizationService;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private DefaultWebSubjectContext defaultWebSubjectContext;

    @Mock
    UserDetailsRepository userDetailsRepository;

    @Mock
    UserManagementService userManagementService;

    @Mock
    HelperUtil helperUtil;

    @InjectMocks
    LoginController loginController;

    @Mock
    UsernamePasswordToken token;

    @Mock
    HttpServletRequest request;

    @Mock
    BindingResult error;

    @Mock
    SecurityUtils securityUtils;

    @Mock
    Subject subject;

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private SecurityManager securityManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void createSessionTest(){
        loginController.createSession(token, request, error);
    }

}
