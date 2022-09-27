package org.thuanthanhtech.mymuseummanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thuanthanhtech.mymuseummanagement.dto.SyntheticDTO;
import org.thuanthanhtech.mymuseummanagement.entity.Synthetic;
import org.thuanthanhtech.mymuseummanagement.service.SyntheticService;

import java.util.List;

@RestController
@RequestMapping("/synthetic")
public class SyntheticApi { //tong hop


    @Autowired
    private SyntheticService syntheticService;

    @PostMapping("/create")
    public ResponseEntity<?> createSynthetic(@RequestBody Synthetic synthetic) {
        syntheticService.createSynthetic(synthetic);
        return new ResponseEntity<>(synthetic, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSynthetic(@RequestBody Synthetic synthetic, @PathVariable("id") Long id) {
        syntheticService.updateSynthetic(synthetic, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteSynthetic(@PathVariable("id") Long id) {
        syntheticService.deleteSynthetic(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<Synthetic>> getAllSynthetic(Pageable pageable, SyntheticDTO syntheticDTO) {
        return new ResponseEntity<>(syntheticService.getAllSynthetic(pageable,syntheticDTO),HttpStatus.OK);
    }
}
