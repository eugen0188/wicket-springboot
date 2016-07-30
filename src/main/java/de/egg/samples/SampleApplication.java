package de.egg.samples;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sample application object.
 */
@SpringBootApplication
public class SampleApplication extends WebApplication {

    /**
     * Runs a {@link org.springframework.boot.SpringApplication} using default settings.
     *
     * @param args the application arguments
     * @throws Exception exception if teh application cannot be started
     */
    public static void main(final String[] args) throws Exception {
        SpringApplication.run(SampleApplication.class, args);
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return SampleHomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        // remove wicket tags from the output
        Application.get().getMarkupSettings().setStripWicketTags(true);
        // spring injector for @SpringBean annotations
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }
}