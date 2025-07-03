package com.example.Parent.parentUtil;

import com.example.Parent.utils.ApiResponse;
import com.example.Parent.utils.VerificationToken;
import com.example.Parent.utils.VerificationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parent")
public class ParentController {


    @Autowired
    private ParentService parentService;
    @Autowired
    private VerificationTokenRepo verificationTokenRepo;

    @PostMapping("/reg")
    public ApiResponse<Parent> registerParent(@RequestBody Parent parent)
    {
        Parent savedParent = parentService.registerParent(parent);
        return ApiResponse.onSuccess(savedParent,"Registered Successfully!!!");
    }

    @GetMapping("/verify")
    public ApiResponse<Parent> verifyEmail(@RequestParam String token)
    {
        VerificationToken vt = verificationTokenRepo.findByToken(token).orElseThrow(()->
                new RuntimeException("Token ")
        );
        Parent verifiedParent = parentService.verifyParentEmail(vt.getParent().getEmail(),vt.getToken());
        if(verifiedParent!=null)
        {
            verificationTokenRepo.delete(vt);
            return ApiResponse.onSuccess(verifiedParent,"Email Verified Successfully!!!");
        }
        return ApiResponse.onError("Invalid Token");
    }

}
