package com.example.Child.childUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChildService {

    @Autowired
    private ChildRepo childRepo;

    public Child saveChild(Child child)
    {
        return childRepo.save(child);
    }

    public Child findChildById(Long id)
    {
        return childRepo.findById(id).orElse(null);
    }
    public Child findChildByFullName(String fullName)
    {
        return childRepo.findByFullName(fullName).orElse(null);
    }
    public Child deleteChild(Long id)
    {
        Child child = findChildById(id);
        if(child!=null)
        {
            childRepo.deleteById(id);
            return child;
        }
        return null;
    }



}
