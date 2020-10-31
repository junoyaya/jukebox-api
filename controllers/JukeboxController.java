package com.junoyaya.jukebox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JukeboxController {
  @Autowired
  private JukeboxService JukeboxService;

  @GetMapping("/api/settings")
  @ResponseBody
  public String index() {
    return "Greetings from jukebox!";
  }

}
