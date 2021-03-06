package com.start.stockdata.api;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.rest.response.LongResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.List;


public interface StockGlobalController<
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto
        > {

    ResponseEntity<RS> save(
            @Valid @RequestBody RQ requestDto
    );

    ResponseEntity<RS> update(
             @PathVariable("id") Long id,
             @Valid @RequestBody RQ requestDto
    );

    ResponseEntity<RS> deleteById(
            @PathVariable("id") final Long id
    );

    ResponseEntity<List<RS>> findAll();

    ResponseEntity<RS> findById(
            @PathVariable("id") final Long id
    );

/*    List<RS> findAll(
            final int page,
            final int limit
    );

    List<RS> findAll(
            final String order, // todo: String -> Sort.RSirection
            final String attribute,
            final int page,
            final int limit
    );*/




    ResponseEntity<LongResponse> count(
            final boolean includeDeleted
    );

}
