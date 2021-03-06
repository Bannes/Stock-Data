package com.start.stockdata.identity.converter.response;

import com.start.stockdata.identity.dto.response.CompanyFullResponseDto;
import com.start.stockdata.identity.model.Company;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFullConverter implements ResponseConverter<Company, CompanyFullResponseDto> {


    private final CompanyTypeResponseConverter companyTypeResponseConverter;
    private final FieldResponseConverter fieldResponseConverter;
    private final FactorResponseConverter factorResponseConverter;

    public CompanyFullConverter(CompanyTypeResponseConverter companyTypeResponseConverter,
                                FieldResponseConverter fieldResponseConverter,
                                FactorResponseConverter factorResponseConverter) {
        this.companyTypeResponseConverter = companyTypeResponseConverter;
        this.fieldResponseConverter = fieldResponseConverter;
        this.factorResponseConverter = factorResponseConverter;
    }


    @Override
    public CompanyFullResponseDto toDto(Company entity) {
        return ofNullable(entity)
                .map(item -> {
                    CompanyFullResponseDto companyFullResponseDto = new CompanyFullResponseDto();
                    companyFullResponseDto.setId(item.getId());
                    companyFullResponseDto.setName(item.getName());
                    companyFullResponseDto.setTypes(item
                            .getCompanyTypes()
                            .stream()
                            .map(companyTypeResponseConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyFullResponseDto.setFields(item
                            .getFields()
                            .stream()
                            .map(fieldResponseConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyFullResponseDto.setFactors(item
                            .getFactors()
                            .stream()
                            .map(factorResponseConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyFullResponseDto.setUserId(item.getUserId());
                    return companyFullResponseDto;
                })
                .orElse(null);
    }
    
}
