package aopu.config;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    ApplicationHome applicationHome = new ApplicationHome(this.getClass());
    String path = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath()+
            "\\src\\main\\resources\\static\\images\\";
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/index.html");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        super.addViewControllers(registry);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("进入了！！");
        System.out.println("file:"+path);
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+path);
    }
}
