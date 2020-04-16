package com.start.stockdata.wrapper;

import com.start.stockdata.identity.converter.entity_to_dto.EntityDtoConverter;
import com.start.stockdata.identity.dto.response.CompanyTypeDto;
import com.start.stockdata.identity.model.CompanyType;
import com.start.stockdata.repository.CompanyTypeRepo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyTypeWrapper extends AbstractEntityDtoWrapper<CompanyType, CompanyTypeDto, CompanyTypeRepo> {

    public CompanyTypeWrapper(EntityDtoConverter<CompanyType, CompanyTypeDto> converter, CompanyTypeRepo repository) {
        super(converter, repository);
    }

    public boolean isSameCompanyTypeAlreadyExist(CompanyTypeDto companyTypeDto) {
        Optional<CompanyType> companyTypeOptional = repository.findByType(companyTypeDto.getType());
        return companyTypeOptional.isPresent();
    }


}