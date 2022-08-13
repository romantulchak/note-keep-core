package com.notekeep.service;

import com.notekeep.dto.LabelDTO;
import com.notekeep.payload.request.label.CreateEditLabelRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface LabelService {

    List<LabelDTO> getLabelForUser(Authentication authentication);

    void create(CreateEditLabelRequest createEditLabelRequest, Authentication authentication);

    void edit(CreateEditLabelRequest createEditLabelRequest, Authentication authentication);

    void delete(String name, Authentication authentication);
}
