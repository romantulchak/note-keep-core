package com.notekeep.controller;

import com.notekeep.payload.request.label.CreateEditLabelRequest;
import com.notekeep.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/label")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public void create(@Valid @RequestBody CreateEditLabelRequest createEditLabelRequest, Authentication authentication) {
        labelService.create(createEditLabelRequest, authentication);
    }

    @PutMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public void edit(@Valid @RequestBody CreateEditLabelRequest createEditLabelRequest, Authentication authentication) {
        labelService.edit(createEditLabelRequest, authentication);
    }
}
