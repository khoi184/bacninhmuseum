package org.thuanthanhtech.mymuseummanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thuanthanhtech.mymuseummanagement.service.MediaService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/media")
public class MediaApi {

    private final MediaService mediaService;

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedia(@PathVariable("id") Long id) {
        mediaService.deleteMedia(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }
}
