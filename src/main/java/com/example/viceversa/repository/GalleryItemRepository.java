package com.example.viceversa.repository;

import com.example.viceversa.entity.GalleryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryItemRepository extends JpaRepository<GalleryItem, String> {

    List<GalleryItem> findAllByGalSearchKeywordContains(String keyword);
    List<GalleryItem> findAllByGalTitleContains(String title);
    List<GalleryItem> findAllByGalSearchKeywordContainsOrGalTitleContains(String keyword, String title);
}
