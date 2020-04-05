//package com.bw.dentaldoor.service;
//
//import com.bw.SettingService;
//import com.bw.dentaldoor.dao.SettingRepository;
//import com.bw.entity.Setting;
//import com.bw.dentaldoor.test.IntegrationTest;
//import org.junit.jupiter.api.Test;
//
//import javax.inject.Inject;
//import java.util.Optional;
//import java.util.Random;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
// */
//class SettingServiceImplTest extends IntegrationTest {
//
//    @Inject
//    private SettingService settingService;
//
//    @Inject
//    private SettingRepository settingRepository;
//
//    @Test
//    void getString() {
//        String key = UUID.randomUUID().toString(), value = UUID.randomUUID().toString();
//        String settingValue = settingService.getString(key, value);
//        assertEquals(value, settingValue);
//        Optional<String> optionalValue = settingService.getString(key);
//        assertNotNull(optionalValue);
//        assertTrue(optionalValue.isPresent());
//        assertEquals(value, optionalValue.get());
//    }
//
//    @Test
//    void getStringWithSupplier() {
//        String key = UUID.randomUUID().toString(), value = UUID.randomUUID().toString();
//        String settingValue = settingService.getString(key, () -> value);
//        assertEquals(value, settingValue);
//        Optional<String> optionalValue = settingService.getString(key);
//        assertNotNull(optionalValue);
//        assertTrue(optionalValue.isPresent());
//        assertEquals(value, optionalValue.get());
//    }
//
//    @Test
//    void getStringWithDescription() {
//        String key = UUID.randomUUID().toString(),
//                value = UUID.randomUUID().toString(),
//                description = UUID.randomUUID().toString();
//        String settingValue = settingService.getString(key, value, description);
//        assertEquals(value, settingValue);
//        Setting setting = settingRepository.findByName(key);
//        assertNotNull(setting);
//        assertEquals(description, setting.getDescription());
//    }
//
//    @Test
//    void getStringWithDuplicateDefault() {
//        String key = UUID.randomUUID().toString(), value = UUID.randomUUID().toString();
//        String settingValue = settingService.getString(key, value);
//        assertEquals(value, settingValue);
//        assertEquals(value, settingService.getString(key, UUID.randomUUID().toString()));
//    }
//
//    @Test
//    void getInteger() {
//        String key = UUID.randomUUID().toString();
//        Integer value = new Random().nextInt();
//        Integer settingValue = settingService.getInteger(key, value);
//        assertEquals(value, settingValue);
//        Optional<Integer> optionalValue = settingService.getInteger(key);
//        assertNotNull(optionalValue);
//        assertTrue(optionalValue.isPresent());
//        assertEquals(value, optionalValue.get());
//    }
//
//    @Test
//    void getIntegerWithDuplicateDefault() {
//        String key = UUID.randomUUID().toString();
//        Integer value = new Random().nextInt();
//        Integer settingValue = settingService.getInteger(key, value);
//        assertEquals(value, settingValue);
//        assertEquals(value, settingService.getInteger(key, settingValue + 1));
//    }
//
//    @Test
//    void getLong() {
//        String key = UUID.randomUUID().toString();
//        Long value = new Random().nextLong();
//        Long settingValue = settingService.getLong(key, value);
//        assertEquals(value, settingValue);
//        Optional<Long> optionalValue = settingService.getLong(key);
//        assertNotNull(optionalValue);
//        assertTrue(optionalValue.isPresent());
//        assertEquals(value, optionalValue.get());
//    }
//
//    @Test
//    void getLongWithDuplicateDefault() {
//        String key = UUID.randomUUID().toString();
//        Long value = new Random().nextLong();
//        Long settingValue = settingService.getLong(key, value);
//        assertEquals(value, settingValue);
//        assertEquals(value, settingService.getLong(key, settingValue + 1));
//    }
//}
