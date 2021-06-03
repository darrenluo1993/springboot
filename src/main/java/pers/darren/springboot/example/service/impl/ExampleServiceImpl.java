package pers.darren.springboot.example.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pers.darren.springboot.example.service.IExampleService;
import pers.darren.springboot.props.AcmePropertiesCB;
import pers.darren.springboot.props.AcmePropertiesMCB;
import pers.darren.springboot.props.AcmePropertiesPB;

@Slf4j
@Service
public class ExampleServiceImpl implements IExampleService {

    @Autowired
    private AcmePropertiesMCB acmePropertiesMCB;

    private final AcmePropertiesPB acmePropertiesPB;

    private final AcmePropertiesCB acmePropertiesCB;

    @Autowired
    public ExampleServiceImpl(final AcmePropertiesPB acmePropertiesPB, final AcmePropertiesCB acmePropertiesCB) {
        this.acmePropertiesPB = acmePropertiesPB;
        this.acmePropertiesCB = acmePropertiesCB;
    }

    @PostConstruct
    public void printAcmeProperties() {
        log.info(this.acmePropertiesPB.toString());
        log.info(this.acmePropertiesCB.toString());
        log.info(this.acmePropertiesMCB.toString());
    }
}