package com.junoyaya.jukebox.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.junoyaya.jukebox.entities.Juke;
import com.junoyaya.jukebox.servises.JukeboxService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JukeboxControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private JukeboxController controller;
  @Autowired
  private JukeboxService service;

  @Mock
  private Page<Juke> jukes;
  @Mock
  private Pageable pageableMock;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .build();

    Pageable pageable = null;
    when(service.findAllJukes(Mockito.eq(pageable))).thenReturn(jukes);
    when(service.findJukesMatchesModel(Mockito.any(String.class), Mockito.eq(pageable))).thenReturn(jukes);
  }

  @Test
  public void whenGetAllJukes_thenOk() throws Exception {
    mockMvc.perform(get("/api/jukes")).andExpect(status().isOk());
  }

  @Test
  public void whenGetJukesWithModel_thenOk() throws Exception {
    mockMvc.perform(get("/api/jukes").param("model", "mars")).andExpect(status().isOk());
    // .andExpect(jsonPath("$._embedded.jukes", hasSize(greaterThanOrEqualTo(1))));
  }
}
