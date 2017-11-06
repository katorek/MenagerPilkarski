package com.meneger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = DruzynaRestController.CONTROLLER_BASE)
public class DruzynaRestController extends AbstractRestController{
    public static final String CONTROLLER_BASE = "/druzyny";
}
