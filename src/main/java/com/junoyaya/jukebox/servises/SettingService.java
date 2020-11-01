package com.junoyaya.jukebox.servises;

import javax.transaction.Transactional;

import com.junoyaya.jukebox.entities.Setting;
import com.junoyaya.jukebox.repos.SettingRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SettingService {
  private SettingRepo settingRepo;

  @Autowired
  public SettingService(SettingRepo jukeRepo) {
    this.settingRepo = jukeRepo;
  }

  @Transactional
  public Page<Setting> findAllSettings(Pageable pageable) {
    return settingRepo.findAll(pageable);
  }
}
