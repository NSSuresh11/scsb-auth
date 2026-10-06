package org.recap.UT.config;

import brave.sampler.Sampler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.recap.config.SamplerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = SamplerConfig.class)
public class SamplerConfigTest {

    @Autowired
    private Sampler sampler;

    @Test
    public void testDefaultSamplerBean() {
        assertNotNull(sampler);
        assertSame(Sampler.ALWAYS_SAMPLE, sampler);
    }
}
