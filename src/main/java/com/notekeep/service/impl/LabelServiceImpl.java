package com.notekeep.service.impl;

import com.notekeep.dto.LabelDTO;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;
    private final UserRepository userRepository;

    /**
     * Gets available labels for user in system
     *
     * @param authentication to get user in system
     * @return list of {@link LabelDTO} available for user
     */
    @Override
    public List<LabelDTO> getLabelForUser(Authentication authentication) {
        String userEmail = authentication.getName();
        return labelRepository.findAllByUserEmail(userEmail)
                .stream()
                .map(label -> new LabelDTO(label.getId(), label.getName()))
                .toList();
    }

    /**
     * Creates label with name for user in system
     *
     * @param createEditLabelRequest contains information about label
     * @param authentication to get user in system
     */
    @Override
    public void create(CreateEditLabelRequest createEditLabelRequest, Authentication authentication) {
        if (labelRepository.existsByNameAndUserEmail(createEditLabelRequest.getName(), authentication.getName())){
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
        if (labelRepository.existsByNameAndUserEmail(createEditLabelRequest.getName(), authentication.getName())){
            throw new LabelAlreadyExistsException(createEditLabelRequest.getName());
        }
        Label label = labelRepository.findById(createEditLabelRequest.getId())
                .orElseThrow(LabelNotFoundException::new);
        if (!StringUtils.equals(label.getName(), createEditLabelRequest.getName())) {
            label.setName(createEditLabelRequest.getName());
            labelRepository.save(label);
        }
    }

    /**
     * Deletes label for user by its name
     *
     * @param name of label
     * @param authentication to get user in system
     */
    @Override
    public void delete(String name, Authentication authentication) {
        labelRepository.deleteByNameAndUserEmail(name, authentication.getName());
    }
}
