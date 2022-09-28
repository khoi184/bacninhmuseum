package org.thuanthanhtech.mymuseummanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thuanthanhtech.mymuseummanagement.entity.Media;
import org.thuanthanhtech.mymuseummanagement.entity.Videos;
import org.thuanthanhtech.mymuseummanagement.repository.MediaRepository;
import org.thuanthanhtech.mymuseummanagement.repository.VideosRepository;
import org.thuanthanhtech.mymuseummanagement.service.VideosService;
import org.thuanthanhtech.mymuseummanagement.utils.Constants;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VideosServiceImpl implements VideosService {

    private final VideosRepository videosRepository;

    public static final String  VIDEO_MEDIA = "Video";

    private final MediaRepository mediaRepository;

    @SneakyThrows
    @Transactional
    @Override
    public Videos createVideo(Videos video) {
        Optional<Videos> optionalVideos = videosRepository.findByName(video.getName());
        Videos videos = new Videos();
        if (optionalVideos.isEmpty()) {
            videos.setName(video.getName());
            videos.setSlug(video.getSlug());
            videos.setStatus(Constants.STATUS_ACTIVE);
            videosRepository.save(videos);

            List<Media> mediaList = new ArrayList<>();
            for (Media mediaVideo : video.getMediaVideo()) {
                mediaVideo.setVideo(videos);
                mediaVideo.setType(Constants.MEDIA_VIDEOS);
                mediaList.add(mediaVideo);
            }
            mediaRepository.saveAll(mediaList);
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
                videos.setStatus(Constants.STATUS_ACTIVE);
                videosRepository.save(videos);

                List<Media> mediaList = new ArrayList<>();
                for (Media mediaVideo : video.getMediaVideo()) {
                    mediaVideo.setVideo(videos);
                    mediaVideo.setType(Constants.MEDIA_VIDEOS);
                    mediaList.add(mediaVideo);
                }
                mediaRepository.saveAll(mediaList);
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
    public Map<String, Object> deleteVideo(Long id) {
        List<Videos> videosList = videosRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(videosList)) {
            throw new Exception("Video not exsits");
        }
        for (Videos video : videosList) {
            video.setStatus(Constants.STATUS_INACTIVE);
            video.setCreatDate(new Date());
            videosRepository.save(video);
        }
        List<Media> mediaList = mediaRepository.findByVideoIdAndStatus();
        if (CollectionUtils.isEmpty(mediaList)) {
            throw new Exception("media not exists");
        }
        for (Media media : mediaList) {
            media.setStatus(Constants.STATUS_INACTIVE);
            mediaRepository.save(media);
        }

        Map<String, Object> result = new HashMap<>();
        result.put(VIDEO_MEDIA, videosList);
        return result;
    }

    @Override
    public List<Videos> getAllVideos() {
        return videosRepository.findAll();
    }


}
