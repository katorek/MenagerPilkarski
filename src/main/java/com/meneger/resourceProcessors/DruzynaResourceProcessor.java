package com.meneger.resourceProcessors;

import com.meneger.model.druzyna.Druzyna;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class DruzynaResourceProcessor implements ResourceProcessor<Resource<Druzyna>> {
    @Override
    public Resource<Druzyna> process(Resource<Druzyna> resource) {
        return resource;
    }
}