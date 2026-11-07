package com.example.simplewebapp;

import com.example.simplewebapp.controller.UserController;
import com.example.simplewebapp.dto.user.SearchUserInputDTO;
import com.example.simplewebapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TmpSearchBindingTest {
    @Test
    void bindsDates() throws Exception {
        UserService service = Mockito.mock(UserService.class);
        Mockito.when(service.searchUsers(Mockito.any())).thenReturn(List.of());
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new UserController(service)).build();

        mvc.perform(get("/api/v1/users/")
                        .param("joinedDateFrom", "2026-11-04 12:41:52.713")
                        .param("joinedDateTo", "2026-12-04 12:41:52.713"))
                .andExpect(status().isOk());

        ArgumentCaptor<SearchUserInputDTO> captor = ArgumentCaptor.forClass(SearchUserInputDTO.class);
        Mockito.verify(service).searchUsers(captor.capture());
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        assertEquals("2026-11-04 12:41:52.713", f.format(captor.getValue().joinedDateFrom()));
        assertEquals("2026-12-04 12:41:52.713", f.format(captor.getValue().joinedDateTo()));
    }
}
