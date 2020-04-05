package com.bw.dentaldoor.service;

import com.bw.SettingService;
import com.bw.dentaldoor.dao.SettingRepository;
import com.bw.entity.Setting;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Named
public class SettingServiceImpl implements SettingService {

    @Inject
    private SettingRepository settingRepository;

    @Transactional
    @Override
    public String getString(String name, String value) {
        return getString(name).orElseGet(() -> {
            Setting setting = new Setting();
            setting.setName(name);
            setting.setValue(value);
            settingRepository.save(setting);
            return value;
        });
    }

    @Transactional
    @Override
    public String getString(String name, Supplier<? extends String> value) {
        return getString(name).orElseGet(() -> {
            Setting setting = new Setting();
            setting.setName(name);
            setting.setValue(value.get());
            settingRepository.save(setting);
            return value.get();
        });
    }

    @Transactional
    @Override
    public String getString(String name, String value, String description) {
        return getString(name).orElseGet(() -> {
            Setting setting = new Setting();
            setting.setName(name);
            setting.setValue(value);
            setting.setDescription(description);
            settingRepository.save(setting);
            return value;
        });

    }

    @Override
    public Optional<String> getString(String name) {
        Setting setting = settingRepository.findByName(name);
        return setting != null ? Optional.of(setting.getValue()) : Optional.empty();
    }

    @Transactional
    @Override
    public Integer getInteger(String name, int value) {
        return getInteger(name).orElseGet(() -> {
            Setting setting = new Setting();
            setting.setName(name);
            setting.setValue(String.valueOf(value));
            settingRepository.save(setting);
            return value;
        });
    }

    @Override
    public Optional<Integer> getInteger(String name) {
        Setting setting = settingRepository.findByName(name);
        return setting != null ? Optional.of(Integer.valueOf(setting.getValue())) : Optional.empty();
    }

    @Transactional
    @Override
    public Long getLong(String name, long value) {
        return getLong(name).orElseGet(() -> {
            Setting setting = new Setting();
            setting.setName(name);
            setting.setValue(String.valueOf(value));
            settingRepository.save(setting);
            return value;
        });
    }

    @Override
    public Optional<Long> getLong(String name) {
        Setting setting = settingRepository.findByName(name);
        return setting != null ? Optional.of(Long.valueOf(setting.getValue())) : Optional.empty();
    }
}
