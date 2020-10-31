package com.junoyaya.jukebox.repos;

import com.junoyaya.jukebox.entities.EquipmentSetting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface EquipmentSettingRepo extends JpaRepository<EquipmentSetting, String>, JpaSpecificationExecutor<EquipmentSetting>, QueryByExampleExecutor<EquipmentSetting> {

}
