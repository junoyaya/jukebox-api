package com.junoyaya.jukebox.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.junoyaya.jukebox.entities.HardwareComponent;
import com.junoyaya.jukebox.entities.Juke;
import com.junoyaya.jukebox.entities.Setting;
import com.junoyaya.jukebox.error.ResponseErrorException;
import com.junoyaya.jukebox.repos.JukeRepo;
import com.junoyaya.jukebox.repos.SettingRepo;
import com.junoyaya.jukebox.servises.JukeboxService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JukeboxServiceTest {
  @Autowired
  private JukeboxService jukeboxService;

  @MockBean
  private JukeRepo jukeRepo;

  @MockBean
  private SettingRepo settingRepo;

  private Pageable pageable = mock(Pageable.class);
  private List<Juke> jukes = new ArrayList<>();

  @Before
  public void setUp() {
    Juke juke1;
    Juke juke2;

    HardwareComponent hardwareComponent1 = new HardwareComponent();
    hardwareComponent1.setId("hardwareComponent1");
    hardwareComponent1.setName("hardwareComponent1");
    HardwareComponent hardwareComponent2 = new HardwareComponent();
    hardwareComponent2.setId("hardwareComponent2");
    hardwareComponent2.setName("hardwareComponent2");

    juke1 = new Juke();
    juke1.setId("id1");
    juke1.setModel("model1");
    juke1.getHardwareComponents().add(hardwareComponent2);

    juke2 = new Juke();
    juke2.setId("id2");
    juke2.setModel("model2");
    juke2.getHardwareComponents().add(hardwareComponent1);

    jukes.add(juke1);
    jukes.add(juke2);

    Setting setting1 = new Setting();
    setting1.setId("settingId1");
    setting1.setName("name1");
    setting1.getHardwareComponents().add(hardwareComponent1);
    setting1.getHardwareComponents().add(hardwareComponent2);

    Setting setting2 = new Setting();
    setting2.setId("settingId2");
    setting2.setName("name2");
    setting2.getHardwareComponents().add(hardwareComponent1);

    Mockito.when(jukeRepo.findByModel(juke1.getModel())).thenReturn(Optional.of(juke1));
    Mockito.when(jukeRepo.findAll()).thenReturn(jukes);

    Mockito.when(settingRepo.findById(setting1.getId())).thenReturn(Optional.of(setting1));
    Mockito.when(settingRepo.findById(setting2.getId())).thenReturn(Optional.of(setting2));

  }

  private void setPageable(int pageSize) {
    Mockito.when(pageable.getOffset()).thenReturn(0L);
    Mockito.when(pageable.getPageSize()).thenReturn(pageSize);
    Page<Juke> jukePages = new PageImpl<Juke>(jukes.subList(0, pageSize), pageable, jukes.size());
    Mockito.when(jukeRepo.findAll(Mockito.eq(pageable))).thenReturn(jukePages);
  }

  @Test
  public void whenFindWithPage_thenFoundWithPage() {
    int pageSize = 2;
    setPageable(pageSize);
    Page<Juke> jukes = jukeboxService.findAllJukes(pageable);
    assertThat(jukes.getContent().size()).isEqualTo(pageSize);
  }

  @Test(expected = ResponseErrorException.class)
  public void whenFindByNonExistedModel_thenFoundNone() {
    int pageSize = 2;
    setPageable(pageSize);
    jukeboxService.findJukesMatchesModel("na", pageable);
  }

  @Test
  public void whenFindByModel_thenFound() {
    int pageSize = 2;
    setPageable(pageSize);
    Page<Juke> jukes = jukeboxService.findJukesMatchesModel("model1", pageable);
    assertThat(jukes.getContent().size()).isEqualTo(1);
  }

  @Test
  public void whenFindBySetting_thenFoundNone() {
    int pageSize = 2;
    setPageable(pageSize);
    Page<Juke> jukes = jukeboxService.findJukesMatchesSetting("settingId1", pageable);
    assertThat(jukes.getContent().size()).isEqualTo(0);
  }

  @Test
  public void whenFindBySetting_thenFound() {
    int pageSize = 2;
    setPageable(pageSize);
    Page<Juke> jukes = jukeboxService.findJukesMatchesSetting("settingId2", pageable);
    assertThat(jukes.getContent().size()).isEqualTo(1);
  }
}
