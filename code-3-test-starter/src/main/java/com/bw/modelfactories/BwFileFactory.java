package com.bw.modelfactories;

import com.bw.entity.BwFile;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

/**
 * @author Gibah Joseph
 * Email: gibahjoe@gmail.com
 * Oct, 2019
 **/
public class BwFileFactory implements FactoryHelper<BwFile> {

    @Override
    public Class<BwFile> getEntity() {
        return BwFile.class;
    }

    @Override
    public BwFile apply(Faker faker, ModelFactory factory) {
        BwFile workspaceUser = new BwFile();
        workspaceUser.setContentType("text/plain");
        workspaceUser.setData(faker.shakespeare().hamletQuote().getBytes());
        workspaceUser.setDateCreated(new Date());
        return workspaceUser;
    }
}
