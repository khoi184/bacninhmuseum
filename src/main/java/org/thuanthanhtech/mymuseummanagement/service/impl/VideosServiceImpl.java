package org.thuanthanhtech.mymuseummanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thuanthanhtech.mymuseummanagement.entity.Videos;
import org.thuanthanhtech.mymuseummanagement.repository.VideosRepository;
import org.thuanthanhtech.mymuseummanagement.service.VideosService;
import org.thuanthanhtech.mymuseummanagement.utils.Constants;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VideosServiceImpl implements VideosService {

    private final VideosRepository videosRepository;

    @SneakyThrows
    @Transactional
    @Override
    public Videos createVideo(Videos video) {
        Optional<Videos> optionalVideos = videosRepository.findByName(video.getName());

        if (optionalVideos.isEmpty()) {
            video.setName(video.getName());
            video.setSlug(video.getSlug());
            video.setCode(video.getCode());
            video.setImage(video.getImage());
            video.setStatus(Constants.STATUS_ACTIVE);
            videosRepository.save(video);

        } else {
            throw new Exception("Name existed");
        }
        return video;
    }

    @SneakyThrows
    @Transactional
    @Override
    public Videos updateVideo(Videos video, Long id) {
        Optional<Videos> optionalVideos = videosRepository.findById(id);
        if (optionalVideos.isPresent()) {
            Videos videos = optionalVideos.get();

            Optional<Videos> optional = videosRepository.findByName(video.getName());
            if (optional.isEmpty() || videos.getId().equals(optional.get().getId())) {
                videos.setName(video.getName());
                videos.setSlug(video.getSlug());
                videos.setCode(video.getCode());
                videos.setImage(video.getImage());
                videos.setStatus(Constants.STATUS_ACTIVE);
                videosRepository.save(videos);

            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new NoSuchElementException();
        }
        return video;
    }

    @SneakyThrows
    @Override
    public void deleteVideo(Long id) {
        List<Videos> videosList = videosRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(videosList)) {
            throw new Exception("Video not exsits");
        }
        for (Videos video : videosList) {
            video.setStatus(Constants.STATUS_INACTIVE);
            video.setCreatDate(new Date());
            videosRepository.save(video);
        }
    }

    @Override
    public List<Videos> getAllVideos() {
        return videosRepository.getAllVideosByMedia();
    }


}
