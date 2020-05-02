package com.start.stockdata.identity.converter.request;

import com.start.stockdata.identity.dto.request.CompanyFactorRequestDto;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.request.company.CompanyRequestDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.Factor;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.identity.model.CompanyType;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import static java.util.Optional.ofNullable;

@Component
public class CompanyRequestConverter implements RequestConverter<Company, CompanyRequestDto>  {

    private final RequestConverter<Factor, CompanyFactorRequestDto> factorConverter;
    private  final RequestConverter<Field, FieldRequestDto> fieldConverter;

    public CompanyRequestConverter(RequestConverter<Factor, CompanyFactorRequestDto> factorConverter,
                                   RequestConverter<Field, FieldRequestDto> fieldConverter) {
        this.factorConverter = factorConverter;
        this.fieldConverter = fieldConverter;
    }

    @Override
    public Company toEntity(CompanyRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    Company company = new Company();
                    company.setName(item.getName());
                    company.setCompanyTypes(item
                            .getTypes()
                            .stream()
                            .map(companyTypeIdDto -> {
                                CompanyType companyType = new CompanyType();
                                companyType.setId(companyTypeIdDto.getId());
                                return companyType;
                            })
                            .collect(Collectors.toSet()));
                    company.setFields(item
                            .getFields()
                            .stream()
                            .map(fieldConverter::toEntity)
                            .collect(Collectors.toSet()));
                    company.setFactors(item
                            .getFactors()
                            .stream()
                            .map(factorConverter::toEntity)
                            .collect(Collectors.toSet()));
                    return company;
                })
                .orElse(null);
    }

}
