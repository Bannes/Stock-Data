package com.start.stockdata.rest.controller;

import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyResponseDto;
import com.start.stockdata.service.CompanyService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.start.stockdata.util.constants.UriPath.COMPANIES_PATH;

@RestController
@RequestMapping(COMPANIES_PATH)
@Api
/*@SwaggerDefinition(securityDefinition = @SecurityDefinition(
        apiKeyAuthDefinitions = {
                @ApiKeyAuthDefinition(key = "custom",
                        name = "Authorization",
                        in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER,
                        description = "Bearer Authentication")}))*/
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Get companies by user id. Id will be taken from token
    @GetMapping
    @ApiOperation(value = "Get all user's companies")
    public ResponseEntity<List<CompanyResponseDto>> getUserCompanies() {
        return new ResponseEntity<>(companyService.findAllByUserId(), HttpStatus.OK);
    }


    @GetMapping({"id"})
    public List<CompanyResponseDto> getAll(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping
    @ApiOperation("Group creation")
    public ResponseEntity<CompanyResponseDto> create(@Valid @RequestBody CompanyRequestDto companyCreationDto) {
        CompanyResponseDto result = companyService.save(companyCreationDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //@DeleteMapping("/c")

}
