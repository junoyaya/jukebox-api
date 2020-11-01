package com.junoyaya.jukebox.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import com.junoyaya.jukebox.entities.Setting;
import com.junoyaya.jukebox.repos.SettingRepo;
import com.junoyaya.jukebox.servises.SettingService;

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
public class SettingServiceTest {
  @Autowired
  private SettingService settingService;

  @MockBean
  private SettingRepo settingRepo;

  private Pageable pageable = mock(Pageable.class);
  private List<Setting> settings = new ArrayList<>();

  @Before
  public void setUp() {
    Setting setting1 = new Setting();
    setting1.setId("id1");
    setting1.setName("name1");

    Setting setting2 = new Setting();
    setting2.setId("id2");
    setting2.setName("name2");

    settings.add(setting1);
    settings.add(setting2);
  }

  @Test
  public void whenFindWithPageSizeEqualsOne_thenFoundTwoPages() {
    int pageSize = 1;
    setPageable(pageSize);
    Page<Setting> settings = settingService.findAllSettings(pageable);
    assertThat(settings.getContent().size()).isEqualTo(pageSize);
  }

  @Test
  public void whenFindWithPageSizeEqualsTwo_thenFoundOnePages() {
    int pageSize = 2;
    setPageable(pageSize);
    Page<Setting> settings = settingService.findAllSettings(pageable);
    assertThat(settings.getContent().size()).isEqualTo(pageSize);
  }

  private void setPageable(int pageSize) {
    Mockito.when(pageable.getOffset()).thenReturn(0L);
    Mockito.when(pageable.getPageSize()).thenReturn(pageSize);

    Page<Setting> settingPages = new PageImpl<Setting>(settings.subList(0, pageSize), pageable, settings.size());

    Mockito.when(settingRepo.findAll(Mockito.eq(pageable))).thenReturn(settingPages);
  }
}
