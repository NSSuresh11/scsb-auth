package org.recap.UT.model;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.recap.model.LoginValidator;
import org.recap.model.UserForm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginValidatorTest {

    private LoginValidator loginValidator;

    @Before
    public void setUp() {
        loginValidator = new LoginValidator();
    }

    @Test
    public void testValidate_WhenUsernameIsValidAndInstitutionIsValid_ShouldReturnTrue() {
        // Arrange
        UserForm userForm = new UserForm();
        userForm.setUsername("validUser");
        userForm.setInstitution(1); // non-zero value for institution

        // Act
        boolean result = loginValidator.validate(userForm);

        // Assert
        assertTrue(result, "Validation should pass with a valid username and non-zero institution.");
    }

    @Test
    public void testValidate_WhenUsernameIsBlank_ShouldReturnFalse() {
        // Arrange
        UserForm userForm = new UserForm();
        userForm.setUsername(""); // blank username
        userForm.setInstitution(1);

        // Act
        boolean result = loginValidator.validate(userForm);

        // Assert
        assertFalse(result, "Validation should fail when username is blank.");
    }

    @Test
    public void testValidate_WhenInstitutionIsZero_ShouldReturnFalse() {
        // Arrange
        UserForm userForm = new UserForm();
        userForm.setUsername("validUser");
        userForm.setInstitution(0); // invalid institution value

        // Act
        boolean result = loginValidator.validate(userForm);

        // Assert
        assertFalse(result, "Validation should fail when institution is zero.");
    }

    @Test
    public void testValidate_WhenUsernameIsBlankAndInstitutionIsZero_ShouldReturnFalse() {
        // Arrange
        UserForm userForm = new UserForm();
        userForm.setUsername(""); // blank username
        userForm.setInstitution(0); // invalid institution value

        // Act
        boolean result = loginValidator.validate(userForm);

        // Assert
        assertFalse(result, "Validation should fail when both username is blank and institution is zero.");
    }
}
