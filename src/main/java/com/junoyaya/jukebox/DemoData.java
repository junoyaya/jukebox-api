package com.junoyaya.jukebox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.junoyaya.jukebox.entities.HardwareComponent;
import com.junoyaya.jukebox.entities.Juke;
import com.junoyaya.jukebox.entities.Setting;
import com.junoyaya.jukebox.error.ErrorCode;
import com.junoyaya.jukebox.error.ResponseErrorException;
import com.junoyaya.jukebox.repos.ComponentRepo;
import com.junoyaya.jukebox.repos.EquipmentSettingRepo;
import com.junoyaya.jukebox.repos.JukeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DemoData {
  @Autowired
  private ComponentRepo componentRepo;

  @Autowired
  private EquipmentSettingRepo settingRepo;

  @Autowired
  private JukeRepo jukeRepo;


  @EventListener
  @Transactional
  public void appReady(ApplicationReadyEvent event) {
    if (componentRepo.count() == 0) {
      List<HardwareComponent> components = new ArrayList<>();
      addComponent(components, "led_panel");
      addComponent(components, "amplifier");
      addComponent(components, "camera");
      addComponent(components, "speaker");
      addComponent(components, "pcb");
      addComponent(components, "touchscreen");
      addComponent(components, "money_pcb");
      addComponent(components, "money_receiver");
      componentRepo.saveAll(components);
    }

    if (settingRepo.count() == 0) {
      List<Setting> settings = new ArrayList<>();
      addSetting(settings, "currency");
      addSetting(settings, "animation_type");
      addSetting(settings, "outdoor");

      settingRepo.saveAll(settings);
    }

    if (jukeRepo.count() == 0) {
      List<Juke> jukes = new ArrayList<>();
      addJuke(jukes, "fusion");
      addJuke(jukes, "mars");
      addJuke(jukes, "moon");
      jukeRepo.saveAll(jukes);
    }

    addJukeComponents();
    addSettingComponents();
  }

  private void addSettingComponents() {
    List<Setting> settings = settingRepo.findAll();
    settings.forEach(setting -> {
      if (setting.getName().equalsIgnoreCase("currency")) {
        addComponentsForSetting(setting, "money_receiver");
      } else if (setting.getName().equalsIgnoreCase("animation_type")) {
        addComponentsForSetting(setting, "led_panel");
      } else if (setting.getName().equalsIgnoreCase("outdoor")) {
        addComponentsForSetting(setting, "amplifier");
      }
    });
  }

  private void addJukeComponents() {
    List<Juke> jukes = jukeRepo.findAll();
    jukes.forEach(juke -> {
      if (juke.getModel().equalsIgnoreCase("fusion")) {
        addComponentsForJuke(juke, Arrays.asList("money_receiver", "led_panel", "amplifier"));
      } else if (juke.getModel().equalsIgnoreCase("mars")) {
        addComponentsForJuke(juke, Arrays.asList("money_receiver", "touchscreen", "led_panel"));
      } else if (juke.getModel().equalsIgnoreCase("moon")) {
        addComponentsForJuke(juke, Arrays.asList("touchscreen", "amplifier"));
      }
    });
  }

  private void addComponentsForSetting(Setting setting, String componentName) {
    List<HardwareComponent> hardwareComponents = setting.getHardwareComponents();
    List<String> ids = hardwareComponents.stream().map(HardwareComponent::getId).collect(Collectors.toList());
    Optional<HardwareComponent> findByName = componentRepo.findByName(componentName);
    if (findByName.isEmpty()) {
      throw new ResponseErrorException(ErrorCode.NOT_EXIST, "Hardware Component does not exist with name: " + componentName);
    }
    if (!ids.contains(findByName.get().getId())) {
      hardwareComponents.add(findByName.get());
    }
  }

  private void addComponentsForJuke(Juke juke, List<String> componentNames) {
    List<HardwareComponent> hardwareComponents = juke.getHardwareComponents();
    List<String> ids = hardwareComponents.stream().map(HardwareComponent::getId).collect(Collectors.toList());
    componentNames.forEach(componentName -> {
      Optional<HardwareComponent> findByName = componentRepo.findByName(componentName);
      if (findByName.isEmpty()) {
        throw new ResponseErrorException(ErrorCode.NOT_EXIST, "Hardware Component does not exist with name: " + componentName);
      }
      if (!ids.contains(findByName.get().getId())) {
        hardwareComponents.add(findByName.get());
      }
    });
  }

  private void addJuke(List<Juke> jukes, String model) {
    Juke juke = new Juke();
    juke.setModel(model);
    jukes.add(juke);
  }


  private void addSetting(List<Setting> settings, String name) {
    Setting setting = new Setting();
    setting.setName(name);
    settings.add(setting);
  }


  private void addComponent(List<HardwareComponent> components, String name) {
    HardwareComponent component = new HardwareComponent();
    component.setName(name);
    components.add(component);
  }
}
