package org.thuanthanhtech.mymuseummanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thuanthanhtech.mymuseummanagement.entity.Videos;
import org.thuanthanhtech.mymuseummanagement.service.MediaService;
import org.thuanthanhtech.mymuseummanagement.service.VideosService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/videos")
public class VideosApi {

    private final VideosService videosService;

    private final MediaService mediaService;

    @PostMapping("/create")
    public ResponseEntity<?> createVideos(@RequestBody Videos video) {
        videosService.createVideo(video);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVideos(@RequestBody Videos video, @PathVariable("id") Long id) {
        videosService.updateVideo(video, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteVideos(@PathVariable("id") Long id) {
        videosService.deleteVideo(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @GetMapping("get-all-videos")
    public ResponseEntity<List<Videos>> getAllVideos() {
        return new ResponseEntity<>(videosService.getAllVideos(), HttpStatus.OK);
    }
}
