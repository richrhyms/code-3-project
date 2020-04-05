package com.bw.modelfactories;
import java.util.Date;
import com.bw.dentaldoor.entity.Language;
import com.bw.enums.GenericStatusConstant;
import com.bw.dentaldoor.entity.DentalOffice;

import com.bw.dentaldoor.entity.DentalOfficeLanguage;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class DentalOfficeLanguageFactory implements FactoryHelper<DentalOfficeLanguage> {
    @Override
    public Class<DentalOfficeLanguage> getEntity() {
        return DentalOfficeLanguage.class;
    }

    @Override
    public DentalOfficeLanguage apply(Faker faker, ModelFactory factory) {
        DentalOfficeLanguage language = new DentalOfficeLanguage();
        language.setDateCreated(new Date());
        language.setStatus(GenericStatusConstant.ACTIVE);
        language.setDentalOffice(factory.create(DentalOffice.class));
        language.setLanguage(factory.create(Language.class));
        return language;
    }
}