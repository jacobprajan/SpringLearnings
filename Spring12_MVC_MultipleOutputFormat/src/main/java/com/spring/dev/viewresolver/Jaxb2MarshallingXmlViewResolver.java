package com.spring.dev.viewresolver;

import java.util.Locale;

import org.springframework.oxm.Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

/*
 
This view resolver relies on JAXB2 Marshalling/unmarshalling to produce XML output. The domain class needs 
to be annotated with JAXB2 annotations.

*/

public class Jaxb2MarshallingXmlViewResolver implements ViewResolver {

	private Marshaller marshaller;

    
    public Jaxb2MarshallingXmlViewResolver(Marshaller marshaller) {
        this.marshaller = marshaller;
    }
    
    
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception 
    {
        MarshallingView view = new MarshallingView();
        view.setMarshaller(marshaller);
        return view;
    }

}
