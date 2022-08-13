package com.notekeep.controller;

import com.notekeep.dto.LabelDTO;
import com.notekeep.payload.request.label.CreateEditLabelRequest;
import com.notekeep.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/label")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<LabelDTO> getLabelsForUser(Authentication authentication) {
        return labelService.getLabelForUser(authentication);
    }

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

    @DeleteMapping("/delete/{name}")
    @PreAuthorize("isAuthenticated()")
    public void delete(@PathVariable("name") String name, Authentication authentication){
        labelService.delete(name, authentication);
    }

}
