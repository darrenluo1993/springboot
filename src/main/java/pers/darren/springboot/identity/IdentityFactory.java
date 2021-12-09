package pers.darren.springboot.identity;

import static com.fr.third.guava.collect.Lists.newArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;

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