package com.notekeep.service;

import com.notekeep.payload.request.label.CreateEditLabelRequest;
import org.springframework.security.core.Authentication;

public interface LabelService {

    void create(CreateEditLabelRequest createEditLabelRequest, Authentication authentication);

    void edit(CreateEditLabelRequest createEditLabelRequest, Authentication authentication);
}
