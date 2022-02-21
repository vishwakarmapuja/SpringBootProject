package net.javaguides.springboot.controller;


import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.FDRequest;

@RestController
public class FixedDepositeRateController {

    private final KieContainer kieContainer;

    public FixedDepositeRateController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @RequestMapping(value = "/getFDInterestRate", method = RequestMethod.GET, produces = "application/json")
    public FDRequest getQuestions(@RequestParam(required = true) String bank, @RequestParam(required = true) Integer durationInYear) {
        KieSession kieSession = kieContainer.newKieSession();
        FDRequest fdRequest = new FDRequest(bank,durationInYear);
        kieSession.insert(fdRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return fdRequest;
    }
}
