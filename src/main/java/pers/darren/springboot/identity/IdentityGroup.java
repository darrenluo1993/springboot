package pers.darren.springboot.identity;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class IdentityGroup<E extends IIdentity> extends ArrayList<IIdentity> {

    private static final long serialVersionUID = -4637041028579831669L;
    @Autowired
    private CostIdentity costIdentity;
    @Autowired
    private CutlineIdentity cutlineIdentity;
    @Autowired
    private IntQuantityIdentity intQuantityIdentity;

    @PostConstruct
    private void addIdentities() {
        super.add(this.costIdentity);
        super.add(this.cutlineIdentity);
        super.add(this.intQuantityIdentity);
    }
}