package org.thuanthanhtech.mymuseummanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thuanthanhtech.mymuseummanagement.dto.AlbumDTO;
import org.thuanthanhtech.mymuseummanagement.entity.Album;

import java.util.List;
import java.util.Map;

@Service
public interface AlbumService {
    @Transactional
    Album createAlbum(Album album);

    @Transactional
    Album updateAlbum(Album album, Long id);

    Map<String, Object> deleteAlbum(Long id);

    Page<Album> getAllAlbum(Pageable pageable, AlbumDTO albumDTO);

    List<Album> getAllAlbumByMedia(Long id);

}
