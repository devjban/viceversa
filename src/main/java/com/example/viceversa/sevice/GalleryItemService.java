package com.example.viceversa.sevice;

import com.example.viceversa.entity.GalleryItem;
import com.example.viceversa.model.GalleryItemDTO;
import com.example.viceversa.repository.GalleryItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class GalleryItemService {
    private final GalleryItemRepository repository;

    public List<GalleryItemDTO> getList(String keyword, String title) {
        List<GalleryItem> galleryItems = new ArrayList<>();
        if (Objects.isNull(keyword) && Objects.isNull(title)) {
            galleryItems = repository.findAll();
        }

        if (!Objects.isNull(keyword) && Objects.isNull(title)) {
            galleryItems = repository.findAllByGalSearchKeywordContains(keyword);
        }

        if (Objects.isNull(keyword) && !Objects.isNull(title)) {
            galleryItems = repository.findAllByGalTitleContains(title);
        }

        if (!Objects.isNull(keyword) && !Objects.isNull(title)) {
            galleryItems = repository.findAllByGalSearchKeywordContainsOrGalTitleContains(keyword, title);
        }

        List<GalleryItemDTO> result = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for (GalleryItem galleryItem : galleryItems) {
            result.add(mapper.convertValue(galleryItem, GalleryItemDTO.class));
        }

        return result;
    }
}
