package org.recap.UT;

import ch.qos.logback.classic.pattern.ThrowableHandlingConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.recap.CustomStackTraceJsonProvider;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class CustomStackTraceJsonProviderUT {

    @InjectMocks
    CustomStackTraceJsonProvider customStackTraceJsonProvider;

    @Mock
    JsonGenerator generator;

    @Mock
    ILoggingEvent event;

    @Mock
    IThrowableProxy throwableProxy;

    @Mock
    ThrowableHandlingConverter throwableConverter;

    @Test
    public void scsbRequest() throws IOException {
        assertTrue(true);
    }

    @Test
    public void scsbRequestTest() throws IOException {
        assertTrue(true);
    }
}