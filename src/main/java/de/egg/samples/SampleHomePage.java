package de.egg.samples;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Base page for the application.
 */
public class SampleHomePage extends WebPage {

    /**
     * @param parameters {@link org.apache.wicket.request.mapper.parameter.PageParameters}
     */
    public SampleHomePage(final PageParameters parameters) {
        super(parameters);

        final Label infoLabel = new Label("info", Model.of("Application is running..."));
        add(infoLabel);
    }
}