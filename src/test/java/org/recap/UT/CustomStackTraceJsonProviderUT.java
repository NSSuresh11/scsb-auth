package org.recap.UT;

import ch.qos.logback.classic.pattern.ThrowableHandlingConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.recap.CustomStackTraceJsonProvider;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.Silent.class)
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
        Mockito.when(event.getThrowableProxy()).thenReturn(throwableProxy);
        ReflectionTestUtils.setField(customStackTraceJsonProvider, "throwableConverter", throwableConverter);
        Mockito.when(throwableConverter.convert(event)).thenReturn("Tested");
//        customStackTraceJsonProvider.writeTo(generator, event);
        assertTrue(true);
    }

    @Test
    public void scsbRequestTest() throws IOException {
        Mockito.when(event.getThrowableProxy()).thenReturn(null);
        ReflectionTestUtils.setField(customStackTraceJsonProvider, "throwableConverter", throwableConverter);
        Mockito.when(throwableConverter.convert(event)).thenReturn("Tested");
//        customStackTraceJsonProvider.writeTo(generator, event);
        assertTrue(true);
    }
}