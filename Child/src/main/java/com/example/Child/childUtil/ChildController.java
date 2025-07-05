package com.example.Child.childUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/child")
public class ChildController {
    
    @Autowired
    private ChildService childService;
    
    @PostMapping("/create")
    public ResponseEntity<Child> createChild(@RequestBody Child child)
    {
        Child savedChild = childService.saveChild(child);
        return ResponseEntity.ok(savedChild);
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<Child> findChildById(@PathVariable Long id)
    {
        Child child = childService.findChildById(id);
        return ResponseEntity.ok(child);
    }
    
    @GetMapping("/find/name/{fullName}")
    public ResponseEntity<Child> findChildByFullName(@PathVariable String fullName)
    {
        Child child = childService.findChildByFullName(fullName);
        return ResponseEntity.ok(child);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Child> deleteChild(@PathVariable Long id)
    {   
        Child deleted = childService.deleteChild(id);
        return ResponseEntity.ok(deleted);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Child> updateChild(@PathVariable Long id,
                                             @RequestBody Child updatedChild) 
    {
        Child existingChild = childService.findChildById(id);
        if (existingChild == null) {
            return ResponseEntity.notFound().build();
        }
        updatedChild.setId(id);
        Child updated = childService.saveChild(updatedChild);
        return ResponseEntity.ok(updated);
    }


}
