package com.junoyaya.jukebox.repos;

import com.junoyaya.jukebox.entities.Setting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface EquipmentSettingRepo extends JpaRepository<Setting, String>, JpaSpecificationExecutor<Setting>, QueryByExampleExecutor<Setting> {

}
