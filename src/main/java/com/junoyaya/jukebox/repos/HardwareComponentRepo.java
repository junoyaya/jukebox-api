package com.junoyaya.jukebox.repos;

import java.util.Optional;

import com.junoyaya.jukebox.entities.HardwareComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface HardwareComponentRepo
    extends JpaRepository<HardwareComponent, String>, JpaSpecificationExecutor<HardwareComponent>, QueryByExampleExecutor<HardwareComponent> {
  Optional<HardwareComponent> findByName(String name);
}
