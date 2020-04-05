package com.bw.modelfactories;
import java.util.Date;
import com.bw.dentaldoor.entity.InsuranceCompany;
import com.bw.enums.GenericStatusConstant;
import com.bw.dentaldoor.entity.DentalOffice;

import com.bw.dentaldoor.entity.DentalOfficeInsurance;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class DentalOfficeInsuranceFactory implements FactoryHelper<DentalOfficeInsurance> {
    @Override
    public Class<DentalOfficeInsurance> getEntity() {
        return DentalOfficeInsurance.class;
    }

    @Override
    public DentalOfficeInsurance apply(Faker faker, ModelFactory factory) {
        DentalOfficeInsurance it = new DentalOfficeInsurance();
        it.setDateCreated(new Date());
        it.setStatus(GenericStatusConstant.ACTIVE);
        it.setInsuranceCompany(factory.create(InsuranceCompany.class));
        it.setDentalOffice(factory.create(DentalOffice.class));
        return it;
    }
}
