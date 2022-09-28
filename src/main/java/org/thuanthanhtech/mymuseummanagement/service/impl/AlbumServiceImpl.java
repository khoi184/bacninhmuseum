package org.thuanthanhtech.mymuseummanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.thuanthanhtech.mymuseummanagement.dto.AlbumDTO;
import org.thuanthanhtech.mymuseummanagement.entity.Album;
import org.thuanthanhtech.mymuseummanagement.entity.Media;
import org.thuanthanhtech.mymuseummanagement.repository.AlbumRepository;
import org.thuanthanhtech.mymuseummanagement.repository.MediaRepository;
import org.thuanthanhtech.mymuseummanagement.service.AlbumService;
import org.thuanthanhtech.mymuseummanagement.utils.Constants;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    private final MediaRepository mediaRepository;

    public static final String  ALBUM_MEDIA = "Album";

    @SneakyThrows
    @Override
    @Transactional
    public Album createAlbum(Album album) {
        Optional<Album> optional = albumRepository.findByName(album.getName());
        Album albums = new Album();
        if (optional.isEmpty()) {
            albums.setName(album.getName());
            albums.setSlug(album.getSlug());
            albums.setStatus(Constants.STATUS_ACTIVE);
            albumRepository.save(albums);

//            List<Media> mediaList = new ArrayList<>();
//            for (Media mediaImage : album.getMediaImage()) {
//                mediaImage.setAlbum(albums);
//                mediaList.add(mediaImage);
//            }
//            mediaRepository.saveAll(mediaList);
        } else {
            throw new Exception("Name existed!");
        }
        return album;
    }

    @SneakyThrows
    @Override
    @Transactional
    public Album updateAlbum(Album album, Long id) {
        Optional<Album> optional = albumRepository.findById(id);
        if (optional.isPresent()) {
            Album albums = optional.get();
            Optional<Album> optionalAlbum = albumRepository.findByName(album.getName());
            if (optionalAlbum.isEmpty() || albums.getId().equals(optionalAlbum.get().getId())) {
                albums.setName(album.getName());
                albums.setSlug(album.getSlug());
                albums.setStatus(Constants.STATUS_ACTIVE);
                albumRepository.save(albums);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found album with id: " + id);
        }
        return album;
    }

    @SneakyThrows
    @Override
    public Map<String, Object> deleteAlbum(Long id) {
        List<Album> albumList = albumRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if(CollectionUtils.isEmpty(albumList)) {
            throw new Exception("Can not found!");
        }
        for (Album album : albumList) {
            album.setStatus(Constants.STATUS_INACTIVE);
            album.setCreatDate(new Date());
            albumRepository.save(album);
        }

        List<Media> mediaList = mediaRepository.findByAlbumIdAndStatus();
        if (CollectionUtils.isEmpty(mediaList)) {
            throw new Exception("Media not exists");
        }
        for (Media media: mediaList) {
            media.setStatus(Constants.STATUS_INACTIVE);
            media.setCreatDate(new Date());
            mediaRepository.save(media);
        }

        Map<String, Object> result = new HashMap<>();
        result.put(ALBUM_MEDIA, albumList);
        return result;
    }

    @Override
    public Page<Album> getAllAlbum(Pageable pageable, AlbumDTO albumDTO) {
        String search;
        if (StringUtils.isEmpty(albumDTO.getSearch())) {
            search = "%%";
        } else {
            search = "%" + albumDTO.getSearch().toLowerCase() + "%";
        }
        return albumRepository.findBySearch(pageable, search);
    }

}
