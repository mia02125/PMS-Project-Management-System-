package com.c4i.pms.config;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.c4i.pms.web.method.serverpage.ServerPageArgumentResolver;

/**
 * Spring MVC ?????? ??????
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * Spring MVC
     * Resolves views selected for rendering by @Controllers to .jsp
     * resources in the /WEB-INF/views directory
     * @return
     */
    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(2);
        return resolver;
    }

    /**
     * ????????? ????????? ???????????? ?????? ?????? ??????
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    /**
     * Apache Tile ??????
     * @return
     */
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions("classpath:config/tiles3-def.xml");
        configurer.setCheckRefresh(true);
        return configurer;
    }
    @Bean
    public TilesViewResolver tilesViewResolver() {
        final TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setViewClass(TilesView.class);
        tilesViewResolver.setOrder(1);
        return tilesViewResolver;
    }


    /**
     *  HandlerMethodArgumentResolver ????????? ??????
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ServerPageArgumentResolver());
    }
    /**
     * CommonsMultipartResolver ?????? ?????? : commons-fileupload gradle ???????????????
     * @return
     */
    private final int MAX_SIZE = 10 * 1024 * 1024;
    @Bean
    public MultipartResolver multipartResolver() {
    	StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
    	return multipartResolver;
    }
    
    protected void customizeRegistration(Dynamic registration) {
       MultipartConfigElement multipartConfig = new MultipartConfigElement("/tmp/upload", MAX_SIZE, MAX_SIZE, 0);
       registration.setMultipartConfig(multipartConfig);
    }
    
    /**
     * ????????? ?????? ????????? 
     */
    @Bean
    @Order(0)
    public MultipartFilter multipartFilter() {
        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }
    
    
    
    /**
     * ?????? ?????? ????????? ??????
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    
    

    /**
     * XmlViewResolver
     */
    @Bean
    public XmlViewResolver xmlViewResolver() throws FileNotFoundException {
        XmlViewResolver resolver = new XmlViewResolver();
        Resource resource = new ClassPathResource("config/views.xml");
        resolver.setLocation(resource);
        resolver.setOrder(0);
        return resolver;
    }


    /**
     * Message Properties
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA); // ??????&??????????????? ?????? ?????? ???????????? ??????????????? ??????
        return localeResolver;
    }
    @Bean public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/message");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(180); // ???????????? ?????? ????????? ????????? ???????????? ??? MessageSource?????? ????????? ?????? ????????????.
        Locale.setDefault(Locale.KOREA);
        return messageSource;
    }



    /**
     * ?????? ??????
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error"));
        return factory;
    }
}