//package com.bw.dentaldoor.service;
//
//import com.bw.TimeUtil;
//import com.bw.dentaldoor.dao.HealthInsuranceProviderRepository;
//import com.bw.dentaldoor.domain.PortalUserUpdateDto;
//import com.bw.dentaldoor.entity.InsuranceCompany;
//import com.bw.dentaldoor.entity.PortalUser;
//import com.bw.dentaldoor.entity.PortalUserInsuranceProvider;
//import com.bw.dentaldoor.test.IntegrationTest;
//import com.bw.enums.GenderConstant;
//import org.junit.jupiter.api.Test;
//
//import javax.inject.Inject;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @author Jabir Minjibir <jminjibir@byteworks.com.ng>
// */
//
//class UserServiceImplTest extends IntegrationTest {
//
//    @Inject
//    private UserService userService;
//
//    @Inject
//    private TimeUtil timeUtil;
//
//    @Inject
//    private HealthInsuranceProviderRepository healthInsuranceProviderRepository;
//
//    @Test
//    void updateUser_test() {
//        PortalUser user = loggedInUser();
//        PortalUserUpdateDto updateDto = new PortalUserUpdateDto();
//
//        updateDto.setFirstName("Jane");
//        updateDto.setLastName(user.getLastName());
//        updateDto.setGender(GenderConstant.FEMALE);
//        updateDto.setIsInstructor(false);
//        updateDto.setDateOfBirth(LocalDate.now().minusYears(10));
//
//        PortalUser updatedUser = userService.updateUser(updateDto);
//
//        assertNotNull(updateDto);
//        assertEquals(updateDto.getFirstName(), updatedUser.getFirstName());
//        assertEquals(GenderConstant.FEMALE, updatedUser.getGender());
//        assertFalse(updatedUser.getIsInstructor());
//        assertEquals(updateDto.getDateOfBirth(), timeUtil.toLocalDate(updatedUser.getDateOfBirth()));
//    }
//
//    @Test
//    void setInsuranceProviders() {
//        PortalUser portalUser = loggedInUser();
//        List<InsuranceCompany> insuranceCompanies = modelFactory.create(InsuranceCompany.class, 10);
//        List<PortalUserInsuranceProvider> healthInsuranceProviders = userService.setInsuranceProviders(portalUser, insuranceCompanies);
//        assertEquals(insuranceCompanies.size(), healthInsuranceProviders.size());
//        entityManager.flush();
//        assertEquals(insuranceCompanies.size(), healthInsuranceProviderRepository.findActiveByPortalUser(portalUser).size());
//    }
//
//    @Test
//    void setInsuranceProvidersTwice() {
//        PortalUser portalUser = loggedInUser();
//        List<InsuranceCompany> insuranceCompanies = modelFactory.create(InsuranceCompany.class, 10);
//        userService.setInsuranceProviders(portalUser, insuranceCompanies);
//        List<InsuranceCompany> newInsuranceCompanies = modelFactory.create(InsuranceCompany.class, 5);
//        newInsuranceCompanies.addAll(insuranceCompanies.subList(5, 10));
//        List<PortalUserInsuranceProvider> healthInsuranceProviders = userService.setInsuranceProviders(portalUser, newInsuranceCompanies);
//        assertEquals(newInsuranceCompanies.size(), healthInsuranceProviders.size());
//
//        List<Long> langs = healthInsuranceProviders.stream()
//                .map(it -> it.getInsuranceCompany().getId()).collect(Collectors.toList());
//
//        newInsuranceCompanies.forEach(insuranceCompany -> {
//            assertTrue(langs.contains(insuranceCompany.getId()));
//        });
//    }
//}
