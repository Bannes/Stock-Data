package com.start.stockdata.identity.dto;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("Model, contains information for creation company's fields")
public class CompanyFieldCreationDto extends AbstractSerializableDto {

    private static final long serialVersionUID = 7856897212698563490L;

    @Size(max = 255)
    @NotBlank
    String asset;

    @NotBlank
    @Size(max = 255)
    String displayName;

}
