package com.start.stockdata.identity.dto.different;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("Model, contains information about company")
@Valid
public class CompanyTypeIdRequestDto extends AbstractRequestDto {

    private static final long serialVersionUID = -4128328055692316346L;

    @ApiModelProperty("Unique identifier of the object")
    @NotNull
    private Long id;

}
