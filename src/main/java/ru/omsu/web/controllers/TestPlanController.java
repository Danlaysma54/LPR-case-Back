package ru.omsu.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.omsu.web.model.request.AddTestPlanRequest;

@RestController
@RequestMapping("{projectId}")
public class TestPlanController {
    @PutMapping("/addTestPlan")
    @ResponseBody
    public ResponseEntity<?> addTestPlan(AddTestPlanRequest testPlanRequest) {
        return null;
    }
}
