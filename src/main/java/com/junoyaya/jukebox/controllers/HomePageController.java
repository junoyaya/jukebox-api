package com.junoyaya.jukebox.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

  @RequestMapping("/")
  public String index() {
    return "Greetings from jukebox!";
  }
}
