package org.thuanthanhtech.mymuseummanagement.service;

import org.springframework.stereotype.Service;
import org.thuanthanhtech.mymuseummanagement.entity.Videos;

import java.util.List;
import java.util.Map;

@Service
public interface VideosService {

    Videos createVideo(Videos video);

    Videos updateVideo(Videos video, Long id);

    Map<String, Object> deleteVideo(Long id);

    List<Videos> getAllVideos();
}
