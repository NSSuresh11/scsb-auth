package org.recap.IT.config;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.recap.IT.BaseTestCase;
import org.recap.config.ApacheShiroCustomConfig;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ApacheShiroCustomConfigIT extends BaseTestCase {

    @Test
    public void subject() {
        ApacheShiroCustomConfig apacheShiroCustomConfig = new ApacheShiroCustomConfig();
        Subject subject = apacheShiroCustomConfig.subject();
    }

    @Test
    public void subjectException() {
        ApacheShiroCustomConfig apacheShiroCustomConfig = new ApacheShiroCustomConfig();
        Subject subject = apacheShiroCustomConfig.subject();
    }

    @Test
    public void handleException() {
        ApacheShiroCustomConfig apacheShiroCustomConfig = new ApacheShiroCustomConfig();
        AuthorizationException exception = new AuthorizationException("Test");
        Model model = Mockito.mock(Model.class);
        String error = apacheShiroCustomConfig.handleException(exception, model);
        assertNotNull(error);
        assertEquals("error", error);
    }

}
