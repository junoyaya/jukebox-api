package com.junoyaya.jukebox;

import static org.assertj.core.api.Assertions.assertThat;

import com.junoyaya.jukebox.controllers.JukeboxController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
  @Autowired
  private JukeboxController controller;

  @Test
  public void contextLoads() throws Exception {
    assertThat(controller).isNotNull();
  }
}
