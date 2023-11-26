package com.example.viceversa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@Builder
@ToString
@AllArgsConstructor
public class GalleryItem {
    @Id
    private String galContentId;
    private String galContentTypeId;
    private String galPhotographyMonth;
    private String galPhotographyLocation;
    private String galWebImageUrl;
    private String galCreatedtime;
    private String galModifiedtime;
    private String galPhotographer;
    private String galSearchKeyword;
    private String galTitle;
}
