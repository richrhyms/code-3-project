package com.bw.modelfactories;

import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.DentalProfessionalLanguage;
import com.bw.dentaldoor.entity.Language;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class DentalProfessionalLanguageFactory implements FactoryHelper<DentalProfessionalLanguage> {
    @Override
    public Class<DentalProfessionalLanguage> getEntity() {
        return DentalProfessionalLanguage.class;
    }

    @Override
    public DentalProfessionalLanguage apply(Faker faker, ModelFactory factory) {
        DentalProfessionalLanguage dentalProfessionalLanguage = new DentalProfessionalLanguage();
        dentalProfessionalLanguage.setDentalProfessional(factory.create(DentalProfessional.class));
        dentalProfessionalLanguage.setLanguage(factory.create(Language.class));
        dentalProfessionalLanguage.setDateCreated(new Date());
        dentalProfessionalLanguage.setStatus(GenericStatusConstant.ACTIVE);
        return dentalProfessionalLanguage;
    }
}
