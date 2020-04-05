package com.bw.modelfactories;

import com.bw.dentaldoor.entity.InsuranceCompany;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.UUID;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class InsuranceCompanyFactory implements FactoryHelper<InsuranceCompany> {
    @Override
    public Class<InsuranceCompany> getEntity() {
        return InsuranceCompany.class;
    }

    @Override
    public InsuranceCompany apply(Faker faker, ModelFactory factory) {
        InsuranceCompany company = new InsuranceCompany();
        company.setName(faker.lorem().word());
        company.setCode(UUID.randomUUID().toString());
        company.setStatus(GenericStatusConstant.ACTIVE);
        return company;
    }
}
