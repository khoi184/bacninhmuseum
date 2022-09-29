package org.thuanthanhtech.mymuseummanagement.service;

import org.thuanthanhtech.mymuseummanagement.entity.Media;

import java.util.List;

public interface MediaService {

    void deleteMedia( Long id) ;

    List<Media> getAllMedia();

}
