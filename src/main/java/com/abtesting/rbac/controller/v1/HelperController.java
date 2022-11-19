package com.abtesting.rbac.controller.v1;

import com.abtesting.rbac.model.constant.ConstantsResponse;
import com.abtesting.rbac.service.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelperController {

    @Autowired
    private HelperService helperService;

    @GetMapping("/v1/constants")
    public ConstantsResponse getConstants() {
        return this.helperService.getConstantsList();
    }
}