package com.start.stockdata.rest.controller.attribute;

import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.service.attribute.DefaultFieldService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.start.stockdata.util.constants.UriPath.COMPANY_FIELD_PATH;

@RestController
@RequestMapping(COMPANY_FIELD_PATH)
@Api
public class FieldController extends AbstractAttributeController<
        FieldRequestDto,
        FieldResponseDto,
        DefaultFieldService
        > {


    public FieldController(DefaultFieldService service) {
        super(service);
    }
    
}
