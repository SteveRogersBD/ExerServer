package com.example.Parent.parentUtil;

import com.example.Parent.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parent")
public class ParentController {


    @Autowired
    private ParentService parentService;

    @PostMapping("/reg")
    public ApiResponse<Parent> registerParent(@RequestBody Parent parent)
    {
        Parent savedParent = parentService.saveParent(parent);
        return ApiResponse.onSuccess(savedParent,"Registered Successfully!!!");
    }
}
