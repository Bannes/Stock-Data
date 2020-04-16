package com.start.stockdata.rest.controller.admin;

import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.dto.response.CompanyTypeDto;
import com.start.stockdata.rest.controller.AbstractController;
import com.start.stockdata.service.CompanyTypeService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.start.stockdata.util.constants.UriPath.COMPANY_TYPE_PATH;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(COMPANY_TYPE_PATH)
@Api(value = "CompanyTypeController")
public class CompanyTypeController extends AbstractController<CompanyTypeRequestDto, CompanyTypeDto, CompanyTypeService> {

    public CompanyTypeController(CompanyTypeService service) {
        super(service);
    }

    public void m() {
        //
    }

}