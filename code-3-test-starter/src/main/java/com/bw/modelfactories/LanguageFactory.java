package com.bw.modelfactories;

import com.bw.dentaldoor.entity.Language;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.UUID;

public class LanguageFactory implements FactoryHelper<Language> {

    @Override
    public Class<Language> getEntity() {
        return Language.class;
    }

    @Override
    public Language apply(Faker faker, ModelFactory factory) {
        Language language = new Language();
        language.setCode(UUID.randomUUID().toString());
        language.setName(faker.lorem().word());
        language.setStatus(GenericStatusConstant.ACTIVE);
        return language;
    }
}
