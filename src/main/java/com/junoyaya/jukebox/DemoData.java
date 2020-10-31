package com.junoyaya.jukebox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.junoyaya.jukebox.entities.Component;
import com.junoyaya.jukebox.entities.EquipmentSetting;
import com.junoyaya.jukebox.entities.Juke;
import com.junoyaya.jukebox.repos.ComponentRepo;
import com.junoyaya.jukebox.repos.EquipmentSettingRepo;
import com.junoyaya.jukebox.repos.JukeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public class DemoData {
  @Autowired
  private ComponentRepo componentRepo;

  @Autowired
  private EquipmentSettingRepo settingRepo;

  @Autowired
  private JukeRepo jukeRepo;


  @EventListener
  public void appReady(ApplicationReadyEvent event) {
    if (componentRepo.count() == 0) {
      List<Component> components = new ArrayList<>();
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
      List<EquipmentSetting> settings = new ArrayList<>();
      addSetting(settings, "currency", componentRepo.findByName("money_receiver"));
      addSetting(settings, "animation_type", componentRepo.findByName("led_panel"));
      addSetting(settings, "outdoor", componentRepo.findByName("amplifier"));

      settingRepo.saveAll(settings);
    }

    if (jukeRepo.count() == 0) {
      List<Juke> jukes = new ArrayList<>();
      addJuke(jukes, "fusion", Arrays.asList("money_receiver", "led_panel", "amplifier"));
      addJuke(jukes, "mars", Arrays.asList("money_receiver", "touchscreen", "led_panel"));
      addJuke(jukes, "moon", Arrays.asList("touchscreen", "amplifier"));
      jukeRepo.saveAll(jukes);
    }
  }


  private void addJuke(List<Juke> jukes, String model, List<String> componentNames) {
    List<Component> componentsInJuke = new ArrayList<>();
    componentNames.forEach(componentName -> componentsInJuke.addAll(componentRepo.findByName(componentName)));
    Juke juke = new Juke();
    juke.setModel(model);
    juke.setComponents(componentsInJuke);
    jukes.add(juke);

  }


  private void addSetting(List<EquipmentSetting> settings, String name, List<Component> components) {
    EquipmentSetting setting = new EquipmentSetting();
    setting.setName(name);
    setting.setComponents(components);
    settings.add(setting);
  }


  private void addComponent(List<Component> components, String name) {
    Component component = new Component();
    component.setName(name);
    components.add(component);
  }
}
