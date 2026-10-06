package org.recap.IT.config;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.recap.config.SwaggerConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import static org.mockito.Mockito.*;


@ExtendWith({SpringExtension.class})
public class SwaggerConfigTest {

    @InjectMocks
    private SwaggerConfig swaggerConfig;

    private ResourceHandlerRegistry registry;

    @Mock
    private InterceptorRegistry interceptorRegistry;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registry = mock(ResourceHandlerRegistry.class);
        ResourceHandlerRegistration registration = mock(ResourceHandlerRegistration.class);
        when(registry.addResourceHandler(anyString())).thenReturn(registration);
    }
/*

    @Test
    public void testDocumentation() {
        Docket docket = swaggerConfig.documentation();
        assertNotNull(docket);
    }

    @Test
    public void testDocumentationSelect() {
        Docket docket = swaggerConfig.documentation();
        assertNotNull(docket.select());
    }

    @Test
    public void testDocumentationPathMapping() {
        Docket docket = swaggerConfig.documentation();
    }

    @Test
    public void testDocumentationSecuritySchemes() {
        Docket docket = swaggerConfig.documentation();
        assertNotNull(docket.securitySchemes(new AbstractList<SecurityScheme>() {
            @Override
            public SecurityScheme get(int index) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }
        }));
    }

    @Test
    public void testDocumentationSecurityContexts() {
        Docket docket = swaggerConfig.documentation();
        assertNotNull(docket.securityContexts(new List<SecurityContext>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NotNull
            @Override
            public Iterator<SecurityContext> iterator() {
                return null;
            }

            @NotNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NotNull
            @Override
            public <T> T[] toArray(@NotNull T[] a) {
                return null;
            }

            @Override
            public boolean add(SecurityContext securityContext) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NotNull Collection<? extends SecurityContext> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NotNull Collection<? extends SecurityContext> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public SecurityContext get(int index) {
                return null;
            }

            @Override
            public SecurityContext set(int index, SecurityContext element) {
                return null;
            }

            @Override
            public void add(int index, SecurityContext element) {

            }

            @Override
            public SecurityContext remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NotNull
            @Override
            public ListIterator<SecurityContext> listIterator() {
                return null;
            }

            @NotNull
            @Override
            public ListIterator<SecurityContext> listIterator(int index) {
                return null;
            }

            @NotNull
            @Override
            public List<SecurityContext> subList(int fromIndex, int toIndex) {
                return List.of();
            }
        }));
    }

    @Test
    public void testAddResourceHandlers() {
        swaggerConfig.addResourceHandlers(registry);
        verify(registry).addResourceHandler("swagger-ui.html");
        verify(registry).addResourceHandler("/webjars/**");
        verify(registry, Mockito.times(2)).addResourceHandler(Mockito.anyString());
    }

    @Test
    public void testAddResourceHandlers_MultipleCalls() {
        swaggerConfig.addResourceHandlers(registry);
        swaggerConfig.addResourceHandlers(registry);
        verify(registry, Mockito.times(2)).addResourceHandler("swagger-ui.html");
        verify(registry, Mockito.times(2)).addResourceHandler("/webjars/**");
        verify(registry, Mockito.times(4)).addResourceHandler(Mockito.anyString());
    }


    @Test
    public void testSpringfoxHandlerProviderBeanPostProcessor() {
        BeanPostProcessor beanPostProcessor = swaggerConfig.springfoxHandlerProviderBeanPostProcessor();
        assertNotNull(beanPostProcessor);
    }
*/


}