package com.example.Parent.parentUtil;

import com.example.Parent.utils.EmailService;
import com.example.Parent.utils.VerificationToken;
import com.example.Parent.utils.VerificationTokenRepo;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParentService {

    @Autowired
    private ParentRepo parentRepo;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerificationTokenRepo verificationTokenRepo;

    public List<Parent> getAllParents() {
        return parentRepo.findAll();
    }

    public Optional<Parent> getParentById(Long id) {
        return parentRepo.findById(id);
    }

    public Parent saveParent(Parent parent) {
        return parentRepo.save(parent);
    }

    public Parent updateParent(Long id, Parent updatedParent) {
        return parentRepo.findById(id).map(existingParent -> {
            existingParent.setFullName(updatedParent.getFullName());
            existingParent.setDp(updatedParent.getDp());
            existingParent.setChildren(updatedParent.getChildren());
            return parentRepo.save(existingParent);
        }).orElse(null);
    }

    public boolean deleteParent(Long id) {
        if (parentRepo.existsById(id)) {
            parentRepo.deleteById(id);
            return true;
        }
        return false;
    }
    public Parent registerParent(Parent parent)
    {
        parentRepo.findByEmail(parent.getEmail()).ifPresent(existingParent -> {
            new RuntimeException("Email has already been used!!!");
        });
        parent.setVerified(false);
        if(!verifyPassword(parent.getPassword())) return null;
        parentRepo.save(parent);
        String token = UUID.randomUUID().toString();
        emailService.sendEmail(parent.getEmail(),"Verification Token",token);
        VerificationToken vt = new VerificationToken();
        vt.setToken(token);
        vt.setParent(parent);
        verificationTokenRepo.save(vt);
        return parentRepo.save(parent);
    }

    public boolean verifyPassword(String password)
    {
        if(password.length()<8) return false;
        boolean containsUppercase = false;
        boolean containsLowercase = false;
        boolean containsDigit = false;
        boolean containsSpecialChar = false;
        for(int i=0;i<password.length();i++)
        {
            char c = password.charAt(i);
            if(Character.isUpperCase(c)) containsUppercase = true;
            if(Character.isLowerCase(c)) containsLowercase = true;
            if(Character.isDigit(c)) containsDigit = true;
            if(!Character.isLetterOrDigit(c)) containsSpecialChar = true;
        }
        if(containsUppercase && containsLowercase && containsDigit && containsSpecialChar)
            return true;
        return false;
    }

    public Parent verifyParentEmail(String email,String token)
    {
        VerificationToken vt = verificationTokenRepo.findByToken(token).orElse(null);
        if(vt==null) return null;
        if(vt.getParent().getEmail().equals(email))
        {
            Parent parent = vt.getParent();
            parent.setVerified(true);
            parentRepo.save(parent);
            verificationTokenRepo.delete(vt);
            return parent;
        }
        return null;
    }
    public Parent getParentByEmail(String email)
    {
        return parentRepo.findByEmail(email).orElse(null);
    }


}

