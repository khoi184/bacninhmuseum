package org.thuanthanhtech.mymuseummanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thuanthanhtech.mymuseummanagement.entity.Media;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "SELECT m.* FROM media m JOIN videos v WHERE v.id = m.video_id AND m.status = 1", nativeQuery = true)
    List<Media> findByVideoIdAndStatus();

    @Query(value = "SELECT m.* FROM media m JOIN album a WHERE a.id = m.album_id AND m.status = 1", nativeQuery = true)
    List<Media> findByAlbumIdAndStatus();
}
