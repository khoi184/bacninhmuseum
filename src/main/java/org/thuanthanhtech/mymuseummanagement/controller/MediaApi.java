package org.thuanthanhtech.mymuseummanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thuanthanhtech.mymuseummanagement.dto.MediaDTO;
import org.thuanthanhtech.mymuseummanagement.entity.Media;
import org.thuanthanhtech.mymuseummanagement.service.MediaService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/media")
public class MediaApi {

    private final MediaService mediaService;

    @PostMapping("/create")
    public ResponseEntity<?> createMedia(@RequestBody Media media) {
        mediaService.createMedia(media);
        return new ResponseEntity<>(media, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMedia(@RequestBody Media media, @PathVariable("id") Long id) {
        mediaService.updateMedia(media, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedia(@PathVariable("id") Long id) {
        mediaService.deleteMedia(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @GetMapping("get-all-videos")
    public ResponseEntity<List<Media>> getAllMedia() {
        return new ResponseEntity<>(mediaService.getAllMedia(), HttpStatus.OK);
    }
}
