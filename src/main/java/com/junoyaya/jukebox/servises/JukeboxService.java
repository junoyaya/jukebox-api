package com.junoyaya.jukebox.servises;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.junoyaya.jukebox.entities.HardwareComponent;
import com.junoyaya.jukebox.entities.Juke;
import com.junoyaya.jukebox.entities.Setting;
import com.junoyaya.jukebox.error.ErrorCode;
import com.junoyaya.jukebox.error.ResponseErrorException;
import com.junoyaya.jukebox.repos.JukeRepo;
import com.junoyaya.jukebox.repos.SettingRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JukeboxService {

  private JukeRepo jukeRepo;
  private SettingRepo settingRepo;

  @Autowired
  public JukeboxService(JukeRepo jukeRepo, SettingRepo settingRepo) {
    this.jukeRepo = jukeRepo;
    this.settingRepo = settingRepo;
  }

  @Transactional
  public Page<Juke> findJukesMatchesSetting(String settingId, Pageable pageable) {
    Optional<Setting> findById = settingRepo.findById(settingId);
    if (findById.isEmpty()) {
      throw new ResponseErrorException(ErrorCode.NOT_EXIST, "Setting does not exist with id: " + settingId);
    }
    Setting setting = findById.get();
    List<HardwareComponent> requiredHardwareComponents = setting.getHardwareComponents();
    List<Juke> matchedJukes = jukeRepo.findAll().stream().filter(juke -> matchRequirments(requiredHardwareComponents, juke)).collect(Collectors.toList());

    return listToPage(pageable, matchedJukes);
  }


  private Page<Juke> listToPage(Pageable pageable, List<Juke> matchedJukes) {
    int start = (int) pageable.getOffset();
    int end = (start + pageable.getPageSize()) > matchedJukes.size() ? matchedJukes.size() : (start + pageable.getPageSize());
    return new PageImpl<Juke>(matchedJukes.subList(start, end), pageable, matchedJukes.size());
  }

  private boolean matchRequirments(List<HardwareComponent> requiredHardwareComponents, Juke juke) {
    if (requiredHardwareComponents.isEmpty()) {
      return false;
    }
    return requiredHardwareComponents.stream().allMatch(requiedComponent -> juke.getHardwareComponents().contains(requiedComponent));
  }

  @Transactional
  public Page<Juke> findAllJukes(Pageable pageable) {
    return jukeRepo.findAll(pageable);
  }

  public Page<Juke> findJukesMatchesModel(String model, Pageable pageable) {
    List<Juke> finByModel = jukeRepo.findByModel(model);
    return listToPage(pageable, finByModel);
  }
}
