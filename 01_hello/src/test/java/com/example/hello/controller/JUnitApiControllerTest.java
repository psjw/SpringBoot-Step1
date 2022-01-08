package com.example.hello.controller;

import com.example.hello.dto.JUnitRequestDto;
import com.example.hello.junit.componet.Calculator;
import com.example.hello.junit.componet.DollarCalculator;
import com.example.hello.junit.componet.MarketApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(JUnitApiController.class)
@AutoConfigureWebMvc
@Import({Calculator.class, DollarCalculator.class}) //주입 시켜준 class들은 모두 Import
class JUnitApiControllerTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }


    @Test
    public void sumTest() throws Exception {
        //http://localhost:9090/api/sum

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9090/api/sum")
                        .queryParam("x","10")
                        .queryParam("y","10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("60000"))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void minusTest() throws Exception {
        JUnitRequestDto req = new JUnitRequestDto();
        req.setX(10);
        req.setY(9);

        String json = new ObjectMapper().writeValueAsString(req);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:9090/api/minus")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                //{"result":3000,"response":{"resultCode":"OK"}} 접근법
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("3000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK"))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    void contextLoads(){

    }
}