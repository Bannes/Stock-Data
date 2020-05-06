package com.start.stockdata.rest.controller.attribute;

import com.start.stockdata.identity.converter.active.Converter;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.rest.controller.AbstractIntegrationTest;
import com.start.stockdata.rest.controller.dto.ErrorDto;
import com.start.stockdata.wrapper.attributes.FieldWrapper;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.start.stockdata.utils.SecurityTestUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class FieldControllerTest extends AbstractIntegrationTest {

    @Autowired
    FieldWrapper fieldWrapper;
    @Autowired
    Converter<Field, FieldRequestDto, FieldResponseDto> converter;


    @Sql(value = {"/sql/field_controller/save_success/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/save_success/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void saveSuccess200() throws Exception {

        //final String TOKEN = getToken(EMAIL_ADMIN, PASSWORD_ADMIN);
        final FieldRequestDto requestDto = getFieldSuccessRequestDto();
        final FieldResponseDto expectedResponseDto = getFieldSuccessResponseDto();


        final MvcResult mvcResult = this.mockMvc
                .perform(post("/companies/1/fields")
                                .content(gson.toJson(requestDto))
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                                /* instead of getting real token and then parse it ,
                                   and put in SecurityContextHolder(details) then,
                                   we use 'initSecurityContext' method.
                                   In this way we can control userId for our sql scripts
                                   and another details
                                 */
                                .with(initSecurityContext(getAdmin()))
                        //.header(API_TOKEN, BEARER_WITH_SPACE + TOKEN)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        FieldResponseDto mvcResultResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), FieldResponseDto.class);

        Field fieldFormDb = fieldWrapper.findById(mvcResultResponseDto.getId()).get();

        // check response with actual entity in db(if it really has been saved to db)
        Assert.assertEquals(converter.toDto(fieldFormDb), mvcResultResponseDto);
        // id, removalDate excluded from (equals and hashcode)
        Assert.assertEquals(expectedResponseDto, mvcResultResponseDto);
        // id must be generated for new Field
        Assert.assertNotNull(expectedResponseDto.getId());
    }

    @Test
    void saveUnauthorized401() throws Exception {

        final FieldRequestDto requestDto = getFieldSuccessRequestDto();

        this.mockMvc
                .perform(post("/companies/1/fields")
                        .content(gson.toJson(requestDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    //company with id=1 belong to user with also id=1
    @Sql(value = {"/sql/field_controller/save_not_belong/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/save_not_belong/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void saveCompanyNotBelongException400() throws Exception {
        final FieldRequestDto requestDto = getFieldNotBelongRequestDto();
        final ErrorDto expectedResponseDto = getFieldErrorResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(post("/companies/7/fields")
                                .content(gson.toJson(requestDto))
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                                //this user doesn't have company with id=7
                                .with(initSecurityContext(getUser()))
                        //.header(API_TOKEN, BEARER_WITH_SPACE + TOKEN)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        final ErrorDto mcvResultResponseDto
                = gson.fromJson(mvcResult.getResponse().getContentAsString(), ErrorDto.class);

        Assert.assertEquals(expectedResponseDto, mcvResultResponseDto);
    }

    @Sql(value = {"/sql/field_controller/save_duplicated_field/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/save_duplicated_field/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void saveDuplicatedField400() {

    }


    private FieldRequestDto getFieldSuccessRequestDto() throws IOException {
        File file = new File("src/test/resources/controller-json/field_controller/save_success/save-success-request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

    private FieldResponseDto getFieldSuccessResponseDto() throws IOException {
        File file = new File("src/test/resources/controller-json/field_controller/save_success/save-success-response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldResponseDto.class);
    }

    private FieldRequestDto getFieldNotBelongRequestDto() throws IOException {
        File file = new File("src/test/resources/controller-json/field_controller/save_not_belong/save-not-belong-request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

    private ErrorDto getFieldErrorResponseDto() throws IOException {
        File file = new File("src/test/resources/controller-json/field_controller/save_not_belong/save-not-belong-response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }


}