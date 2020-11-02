package com.junoyaya.jukebox.controllers;

import com.junoyaya.jukebox.entities.Juke;
import com.junoyaya.jukebox.servises.JukeboxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JukeboxController {
  @Autowired
  private JukeboxService jukeboxService;
  @Autowired
  private PagedResourcesAssembler<Juke> jukePagedResourcesAssembler;

  // @Autowired
  // public JukeboxController(JukeboxService jukeboxService, PagedResourcesAssembler<Juke> jukePagedResourcesAssembler) {
  // this.jukeboxService = jukeboxService;
  // this.jukePagedResourcesAssembler = jukePagedResourcesAssembler;
  // }

  @GetMapping("/api/jukes")
  @ResponseBody
  public PagedModel<EntityModel<Juke>> findJukesMatchesSetting(@RequestParam(required = false, name = "settingId") String settingId,
      @RequestParam(required = false, name = "model") String model,
      Pageable pageable) {
    if (settingId != null) {
      return jukePagedResourcesAssembler.toModel(jukeboxService.findJukesMatchesSetting(settingId, pageable));
    }
    if (model != null) {
      return jukePagedResourcesAssembler.toModel(jukeboxService.findJukesMatchesModel(model, pageable));
    }
    return jukePagedResourcesAssembler.toModel(jukeboxService.findAllJukes(pageable));
  }

}
