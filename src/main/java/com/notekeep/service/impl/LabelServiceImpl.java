package com.notekeep.service.impl;

import com.notekeep.exception.label.LabelAlreadyExistsException;
import com.notekeep.exception.label.LabelNotFoundException;
import com.notekeep.exception.user.UserNotFoundException;
import com.notekeep.model.Label;
import com.notekeep.model.User;
import com.notekeep.payload.request.label.CreateEditLabelRequest;
import com.notekeep.repository.LabelRepository;
import com.notekeep.repository.UserRepository;
import com.notekeep.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;
    private final UserRepository userRepository;

    /**
     * Creates label with name for user in system
     *
     * @param createEditLabelRequest contains information about label
     * @param authentication to get user in system
     */
    @Override
    public void create(CreateEditLabelRequest createEditLabelRequest, Authentication authentication) {
        if (labelRepository.existsByName(createEditLabelRequest.getName())){
            throw new LabelAlreadyExistsException(createEditLabelRequest.getName());
        }
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        Label label = new Label(createEditLabelRequest.getName(), user);
        labelRepository.save(label);
    }

    /**
     * Change label name
     *
     * @param createEditLabelRequest to get new label name
     * @param authentication to get user email
     */
    @Override
    public void edit(CreateEditLabelRequest createEditLabelRequest, Authentication authentication) {
        if (labelRepository.existsByName(createEditLabelRequest.getName())){
            throw new LabelAlreadyExistsException(createEditLabelRequest.getName());
        }
        Label label = labelRepository.findByNameAndUserEmail(createEditLabelRequest.getName(), authentication.getName())
                .orElseThrow(LabelNotFoundException::new);
        if (!StringUtils.equals(label.getName(), createEditLabelRequest.getName())) {
            label.setName(createEditLabelRequest.getName());
            labelRepository.save(label);
        }
    }
}
