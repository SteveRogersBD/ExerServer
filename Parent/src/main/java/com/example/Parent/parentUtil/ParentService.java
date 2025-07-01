package com.example.Parent.parentUtil;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ParentService {

    @Autowired
    private ParentRepo parentRepo;

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
}

