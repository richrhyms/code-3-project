//package com.bw.dentaldoor.startup;
//
//import com.bw.dentaldoor.dao.CityRepository;
//import com.bw.dentaldoor.dao.CountryRepository;
//import com.bw.dentaldoor.dao.StateRepository;
//import com.bw.dentaldoor.test.IntegrationTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.inject.Inject;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
///**
// * @author Ifesinachi Eze <ieze@byteworks.com.ng>
// */
//
//public class MasterRecordsLoaderTest extends IntegrationTest {
//    @Inject
//    private CountryRepository countryRepository;
//    @Inject
//    private StateRepository stateRepository;
//    @Inject
//    private CityRepository cityRepository;
//
//    private MasterRecordsLoader masterRecordsLoader;
//
//    @BeforeEach
//    public void init() {
//        masterRecordsLoader = applicationContext.getAutowireCapableBeanFactory().createBean(MasterRecordsLoader.class);
//    }
//
//    @Test
//    void checkLoadedData() {
//        masterRecordsLoader.init();
//        assertEquals(1, countryRepository.count());
//        assertEquals(50, stateRepository.count());
//        assertEquals(51, cityRepository.count());
//    }
//}
