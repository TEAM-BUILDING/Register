package nl.rabobank.register.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.jasper.servlet.JspServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

    private static final String JSP_SERVLET_NAME = "jsp";
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        registerListener(aServletContext);
        registerDispatcherServlet(aServletContext);
        registerJspServlet(aServletContext);
    }

    private void registerListener(ServletContext aContext) {
        AnnotationConfigWebApplicationContext root = createContext(ApplicationModule.class);
        aContext.addListener(new ContextLoaderListener(root));
    }

    private void registerDispatcherServlet(ServletContext aContext) {
        AnnotationConfigWebApplicationContext _ctx = createContext(WebModule.class);
        ServletRegistration.Dynamic dispatcher =
                aContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(_ctx));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private void registerJspServlet(ServletContext aContext) {
        ServletRegistration.Dynamic dispatcher = aContext.addServlet(JSP_SERVLET_NAME, new JspServlet());
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("*.jsp");
    }

    private AnnotationConfigWebApplicationContext createContext(final Class<?>... aModules) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(aModules);
        return ctx;
    }
}
