package com.bw.modelfactories;

import com.github.heywhy.springentityfactory.contracts.ModelFactory;

public class ModelFactoryRegistrar {
    public static void register(ModelFactory modelFactory) {
        modelFactory.register(PortalUserFactory.class);
        modelFactory.register(PortalAccountFactory.class);
        modelFactory.register(PortalAccountTypeRoleFactory.class);
        modelFactory.register(BwFileFactory.class);
        modelFactory.register(PortalAccountMemberRoleFactory.class);
        modelFactory.register(PortalAccountMembershipFactory.class);
        modelFactory.register(CountryFactory.class);
        modelFactory.register(StateFactory.class);
//        modelFactory.register(UserRegistrationDtoFactory.class);
        modelFactory.register(DentalOfficeFactory.class);
        modelFactory.register(DentalOfficeImageFactory.class);
        modelFactory.register(LanguageFactory.class);
        modelFactory.register(SpecializationFactory.class);
        modelFactory.register(DentalProfessionalFactory.class);
        modelFactory.register(CityFactory.class);
        modelFactory.register(DentalProfessionalLicenceFactory.class);
        modelFactory.register(DentalProfessionalSpecializationFactory.class);
        modelFactory.register(DentalProfessionalLanguageFactory.class);
        modelFactory.register(ReviewFactory.class);
        modelFactory.register(DentalOfficeInsuranceFactory.class);
        modelFactory.register(DentalOfficeLanguageFactory.class);
        modelFactory.register(DentalOfficeServiceFactory.class);
        modelFactory.register(DentalServiceFactory.class);
        modelFactory.register(InsuranceCompanyFactory.class);
        modelFactory.register(TwoFactorRequestFactory.class);
        modelFactory.register(OneTimePasswordFactory.class);
        modelFactory.register(InviteFactory.class);
        modelFactory.register(PaymentInvoiceFactory.class);
        modelFactory.register(RevenueItemFactory.class);
        modelFactory.register(SubscriptionFactory.class);
        modelFactory.register(SubscriptionCycleFactory.class);
        modelFactory.register(DentalOfficeSubscriptionFactory.class);
        modelFactory.register(PortalUserImageFactory.class);
        modelFactory.register(AddressFactory.class);
        modelFactory.register(DentalOfficeProfessionalFactory.class);
        modelFactory.register(DentalProfessionalAddressFactory.class);
        modelFactory.register(RolePermissionFactory.class);
        modelFactory.register(FeedbackFactory.class);
        modelFactory.register(CouponFactory.class);
        modelFactory.register(FlashCardCategoryFactory.class);
        modelFactory.register(FlashCardFactory.class);
        modelFactory.register(FlashCardDeckFactory.class);
        modelFactory.register(FlashCardDeckSubjectFactory.class);
        modelFactory.register(DentalProfessionalEndorsementFactory.class);
        modelFactory.register(DentalOfficeAddressFactory.class);
        modelFactory.register(CouponFactory.class);
        modelFactory.register(ImageTagFactory.class);
        modelFactory.register(PreloadedImageFactory.class);
        modelFactory.register(PreloadedImageTagFactory.class);
        modelFactory.register(CouponImageFactory.class);
    }
}
