package com.junoyaya.jukebox.repos;

import java.util.List;

import com.junoyaya.jukebox.entities.Component;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ComponentRepo extends JpaRepository<Component, String>, JpaSpecificationExecutor<Component>, QueryByExampleExecutor<Component> {
  List<Component> findByName(String name);
}
