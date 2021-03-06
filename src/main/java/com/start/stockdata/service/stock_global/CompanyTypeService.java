package com.start.stockdata.service.stock_global;

import com.start.stockdata.exception.exception.EntityByIdNotFoundException;
import com.start.stockdata.exception.exception.UnsupportedFieldException;
import com.start.stockdata.identity.converter.request.RequestConverter;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.dto.response.CompanyTypeResponseDto;
import com.start.stockdata.identity.model.CompanyType;
import com.start.stockdata.validations.FieldValueExists;
import com.start.stockdata.wrapper.global.CompanyTypeWrapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyTypeService extends AbstractGlobalService<
        CompanyType,
        CompanyTypeRequestDto,
        CompanyTypeResponseDto,
        CompanyTypeWrapper> implements FieldValueExists {

    public CompanyTypeService(CompanyTypeWrapper wrapper, ResponseConverter<CompanyType, CompanyTypeResponseDto> responseConverter, RequestConverter<CompanyType, CompanyTypeRequestDto> requestConverter) {
        super(wrapper, responseConverter, requestConverter);
    }

    @Override
    protected boolean entityAlreadyExistsSave(CompanyTypeRequestDto requestDto) {
        Optional<CompanyType> companyTypeOptional = wrapper.findByType(requestDto.getType());
        return companyTypeOptional.isPresent();
    }

    /*
        Specially has been done in this way. For test
        if current 'type=Tech' and user try update type to the same 'type=Tech'
        It will be update(request to Db) and no Exception will be thrown
     */

    @Override
    protected boolean entityAlreadyExistsUpdate(final Long id, CompanyTypeRequestDto requestDto) {
        Optional<CompanyType> companyTypeByIdOptional = wrapper.findById(id);
        if (!companyTypeByIdOptional.isPresent()) {
            throw new EntityByIdNotFoundException(id);
        }

        Optional<CompanyType> companyTypeOptional = wrapper.findByType(requestDto.getType());
        return companyTypeOptional.filter(companyType -> !id.equals(companyType.getId())).isPresent();
    }

    @Override
    public boolean isFieldValueExist(Object value, String fieldName) throws UnsupportedOperationException {

        if (!fieldName.equals("type")) {
            throw new UnsupportedFieldException(value.toString());
        }

        if (value == null) {
            return false;
        }

        return wrapper.findByType(value.toString()).isPresent();
    }

}
