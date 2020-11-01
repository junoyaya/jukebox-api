package com.junoyaya.jukebox.repos;

import java.util.List;

import com.junoyaya.jukebox.entities.Juke;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JukeRepo extends JpaRepository<Juke, String>, JpaSpecificationExecutor<Juke>, QueryByExampleExecutor<Juke> {
  List<Juke> findByModel(String name);
}