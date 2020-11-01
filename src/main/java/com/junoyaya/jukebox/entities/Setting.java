package com.junoyaya.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Setting extends AuditableEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String name;

  @ManyToMany
  @JoinTable(
      name = "settingComponent",
      joinColumns = @JoinColumn(name = "settingId"),
      inverseJoinColumns = @JoinColumn(name = "hardwareComponentId"))
  private List<HardwareComponent> hardwareComponents = new ArrayList<>();

}
