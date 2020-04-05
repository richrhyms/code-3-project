//package com.bw.modelfactories;
//
//import com.bw.dentaldoor.domain.account.UserRegistrationDto;
//import com.bw.dentaldoor.domain.enumeration.UserTypeConstant;
//import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
//import com.github.heywhy.springentityfactory.contracts.ModelFactory;
//import com.github.javafaker.Faker;
//
//import java.util.UUID;
//
//public class UserRegistrationDtoFactory implements FactoryHelper<UserRegistrationDto> {
//    @Override
//    public Class<UserRegistrationDto> getEntity() {
//        return UserRegistrationDto.class;
//    }
//
//    @Override
//    public UserRegistrationDto apply(Faker faker, ModelFactory factory) {
//        UserRegistrationDto dto = new UserRegistrationDto();
//        dto.setEmail(UUID.randomUUID().toString());
//        dto.setFirstName(faker.name().firstName());
//        dto.setLastName(faker.name().lastName());
//        dto.setMobileNumber(faker.phoneNumber().phoneNumber());
//        dto.setPassword("password");
//        dto.setUserType(UserTypeConstant.PATIENT);
//        return dto;
//    }
//}
