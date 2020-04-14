package com.start.stockdata.identity.dto;

import com.start.stockdata.util.constraints.Company;
import com.start.stockdata.util.enums.CompanyType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("Model, contains information about company")
public class CompanyDto extends AbstractEntityDto {

    private static final long serialVersionUID = 2435885082115582596L;

    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(value = "Company's name. Constraints: @NotBlank, @Size(max = 255)", required = true)
    private String name;

    @NotEmpty
    @ApiModelProperty(value = "Company's types. Constraints: @NotEmpty", required = true)
    private Set<CompanyTypeDto> types;

    @NotEmpty
    @ApiModelProperty(value = "Company's fields. Constraints: @NotEmpty", required = true)
    private Set<CompanyFieldDto> fields;

    @NotEmpty
    @ApiModelProperty(value = "Company's factors. Constraints: @NotEmpty", required = true)
    private Set<CompanyFactorDto> factors;

    @NotNull
    @ApiModelProperty(value = "User's id. Company belongs to this user. Constraints: @NotNull", required = true)
    private Long userId;

}
