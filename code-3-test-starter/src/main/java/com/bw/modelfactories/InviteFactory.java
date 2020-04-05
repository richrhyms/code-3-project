package com.bw.modelfactories;

import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.Invite;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.enums.InvitationStatusConstant;
import com.bw.dentaldoor.enums.InvitationTypeConstant;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class InviteFactory implements FactoryHelper<Invite> {

    @Override
    public Class<Invite> getEntity() {
        return Invite.class;
    }

    @Override
    public Invite apply(Faker faker, ModelFactory factory) {
        Invite invite = new Invite();
        invite.setDateCreated(new Date());
        invite.setStatus(GenericStatusConstant.ACTIVE);
        invite.setInvitationStatus(InvitationStatusConstant.PENDING);
        invite.setType(InvitationTypeConstant.DENTAL_PRACTITIONER);
        invite.setInvitedUser(factory.create(DentalProfessional.class));
        invite.setDentalOffice(factory.create(DentalOffice.class));
        invite.setCreatedBy(factory.create(PortalUser.class));
        invite.setEmail(faker.internet().emailAddress());
        invite.setName(faker.name().name());
        invite.setPhoneNumber(faker.phoneNumber().phoneNumber());
        invite.setLastUpdated(new Date());
        return invite;
    }
}
