package com.notekeep.payload.request.label;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateEditLabelRequest {

    @Size(min = 1, max = 50, message = "label.name.size")
    private String name;
}
