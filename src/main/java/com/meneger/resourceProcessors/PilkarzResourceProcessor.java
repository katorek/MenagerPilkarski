package com.meneger.resourceProcessors;

import com.meneger.model.osoba.Pilkarz;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class PilkarzResourceProcessor implements ResourceProcessor<Resource<Pilkarz>> {
    @Override
    public Resource<Pilkarz> process(Resource<Pilkarz> resource) {
        return resource;
    }
}
