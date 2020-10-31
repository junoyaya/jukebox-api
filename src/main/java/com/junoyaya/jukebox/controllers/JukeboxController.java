package com.junoyaya.jukebox.controllers;

import com.junoyaya.jukebox.dtos.JukeDto;
import com.junoyaya.jukebox.servises.JukeboxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JukeboxController {
  @Autowired
  private JukeboxService JukeboxService;
  @Autowired
  private PagedResourcesAssembler<JukeDto> jukePagedResourcesAssembler;

  @GetMapping("/api/jukes")
  @ResponseBody
  public PagedModel<EntityModel<JukeDto>> findAllJukes(Pageable pageable) {
    return jukePagedResourcesAssembler.toModel(JukeboxService.findAllJukes());
  }

  @GetMapping("/api/jukes")
  @ResponseBody
  public PagedModel<EntityModel<JukeDto>> findJukesMatchesName(@RequestParam(name = "name ") String name, Pageable pageable) {
    return jukePagedResourcesAssembler.toModel(JukeboxService.findJukesMatchesName(name));
  }

  @GetMapping("/api/jukes")
  @ResponseBody
  public PagedModel<EntityModel<JukeDto>> findJukesMatchesSetting(@RequestParam(name = "settingId ") String settingId, Pageable pageable) {
    return jukePagedResourcesAssembler.toModel(JukeboxService.findJukesMatchesSetting(settingId));
  }

}
