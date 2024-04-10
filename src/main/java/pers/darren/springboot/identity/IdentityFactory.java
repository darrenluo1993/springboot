package pers.darren.springboot.identity;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Component
public class IdentityFactory {
    @Autowired
    private CostIdentity costIdentity;
    @Autowired
    private CutlineIdentity cutlineIdentity;
    @Autowired
    private IntQuantityIdentity intQuantityIdentity;
    @Getter
    private List<IIdentity> identities = newArrayList();

    @PostConstruct
    private void addIdentities() {
        this.identities.add(this.costIdentity);
        this.identities.add(this.cutlineIdentity);
        this.identities.add(this.intQuantityIdentity);
    }
}