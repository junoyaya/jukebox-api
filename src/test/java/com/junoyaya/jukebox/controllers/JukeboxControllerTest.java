package com.junoyaya.jukebox.controllers;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.junoyaya.jukebox.entities.Juke;
import com.junoyaya.jukebox.servises.JukeboxService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class JukeboxControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private JukeboxController controller;
  @MockBean
  private JukeboxService service;
  @MockBean
  private PagedResourcesAssembler<Juke> jukePagedResourcesAssembler;
  @Mock
  private Page<Juke> jukes;

  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .apply(documentationConfiguration(this.restDocumentation))
        .build();

    Mockito.when(service.findAllJukes(Mockito.any(Pageable.class))).thenReturn(jukes);
    Mockito.when(service.findJukesMatchesModel(Mockito.any(String.class), Mockito.any(Pageable.class))).thenReturn(jukes);
    Mockito.when(service.findJukesMatchesSetting(Mockito.any(String.class), Mockito.any(Pageable.class))).thenReturn(jukes);
  }

  @Test
  public void whenGetAllJukes_thenOk() throws Exception {
    mockMvc.perform(get("/api/jukes")).andDo(print()).andExpect(status().isOk()).andDo(document("juke"));
  }

  @Test
  public void whenGetJukesWithModel_thenOk() throws Exception {
    mockMvc.perform(get("/api/jukes").param("model", "mars")).andExpect(status().isOk())
        .andDo(document("juke-model", org.springframework.restdocs.request.RequestDocumentation.requestParameters(
            org.springframework.restdocs.request.RequestDocumentation.parameterWithName("model").description("The model of the juke."))));
  }

  @Test
  public void whenGetJukesWitSetting_thenOk() throws Exception {
    mockMvc.perform(get("/api/jukes").param("settingId", "testId")).andExpect(status().isOk())
        .andDo(document("juke-setting", org.springframework.restdocs.request.RequestDocumentation.requestParameters(
            org.springframework.restdocs.request.RequestDocumentation.parameterWithName("settingId").description("The juke tha matches the setting."))));
  }
}
