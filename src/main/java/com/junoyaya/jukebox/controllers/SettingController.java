package com.junoyaya.jukebox.controllers;

import com.junoyaya.jukebox.entities.Setting;
import com.junoyaya.jukebox.servises.SettingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SettingController {
  @Autowired
  private SettingService settingService;
  @Autowired
  private PagedResourcesAssembler<Setting> settingPagedResourcesAssembler;

  @GetMapping("/api/settings")
  @ResponseBody
  public PagedModel<EntityModel<Setting>> findAllSettings(Pageable pageable) {
    return settingPagedResourcesAssembler.toModel(settingService.findAllSettings(pageable));
  }
}
