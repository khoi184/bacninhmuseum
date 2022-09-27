package org.thuanthanhtech.mymuseummanagement.service;

import org.thuanthanhtech.mymuseummanagement.dto.MediaDTO;
import org.thuanthanhtech.mymuseummanagement.entity.Media;

import javax.transaction.Transactional;
import java.util.List;

public interface MediaService {

    @Transactional
    Media createMedia(Media media);

    @Transactional
    Media updateMedia(Media media, Long id)  ;

    void deleteMedia( Long id) ;

    List<Media> getAllMedia();
}
