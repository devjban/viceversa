package com.example.viceversa;

import com.example.viceversa.model.GalleryItemDTO;
import com.example.viceversa.sevice.GalleryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/gelleryItem")
@RequiredArgsConstructor
public class GalleryItemController {
    private final GalleryItemService service;

    @GetMapping
    public ResponseEntity<List<GalleryItemDTO>> getGalleryItems(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "title", required = false) String title
    ) {
        return ResponseEntity.ok(service.getList(keyword, title));
    }
}
