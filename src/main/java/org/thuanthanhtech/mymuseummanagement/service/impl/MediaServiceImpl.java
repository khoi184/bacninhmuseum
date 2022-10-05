package org.thuanthanhtech.mymuseummanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thuanthanhtech.mymuseummanagement.dto.MediaDTO;
import org.thuanthanhtech.mymuseummanagement.entity.Media;
import org.thuanthanhtech.mymuseummanagement.repository.MediaRepository;
import org.thuanthanhtech.mymuseummanagement.service.MediaService;
import org.thuanthanhtech.mymuseummanagement.utils.Constants;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @SneakyThrows
    @Override
    @Transactional
    public Media createMedia(Media media)  {
        Media medias = new Media();
        medias.setLink(media.getLink());
        medias.setObjectType(media.getObjectType());
        medias.setType(media.getType());
        medias.setStatus(Constants.STATUS_ACTIVE);
        mediaRepository.save(media);
        return media;
    }

    @SneakyThrows
    @Override
    @Transactional
    public Media updateMedia(Media media, Long id) {
        Optional<Media> medias = mediaRepository.findById(id);
        if (medias.isPresent()) {
            Media med = medias.get();

            med.setLink(media.getLink());
            med.setObjectType(media.getObjectType());
            med.setType(media.getType());
            med.setStatus(Constants.STATUS_ACTIVE);
            mediaRepository.save(med);
        } else {
            throw new MessageDescriptorFormatException("Can not found by id: " + id);
        }
        return media;
    }
    @SneakyThrows
    @Override
    public void deleteMedia(Long id) {
        List<Media> media = mediaRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(media)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (Media medias : media) {
            medias.setStatus(Constants.STATUS_INACTIVE);
            medias.setModifiedDate(LocalDate.now());
            mediaRepository.save(medias);
        }
    }

    @Override
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }
}
