package com.start.stockdata.rest.controller;

import com.start.stockdata.identity.dto.CompanyDto;
import com.start.stockdata.util.enums.CompanyType;
import com.start.stockdata.utils.TokenGenerator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.start.stockdata.util.constants.UriPath.COMPANIES_PATH;
import static com.start.stockdata.utils.TokenGenerator.getToken;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CompanyControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void save_success() throws Exception {

      /*  CompanyDto companyDto = new CompanyDto();
        companyDto.setName("companyName");
        companyDto.setUserId(2L);
        companyDto.setCompanyType("Product");
        companyDto.setCompanyType(CompanyType.DAIRY.getValue());

        final MvcResult mvcResult = this.mockMvc
                .perform(post(COMPANIES_PATH)
                        .content(gson.toJson(companyDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + getToken()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        CompanyDto companyDtoMvcResult =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), CompanyDto.class);

        Assert.assertEquals(companyDto, companyDtoMvcResult);*/
    }


}