package com.example.viceversa.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class GalleryItemDTO {
    @Schema(description = "콘텐츠 아이디")
    private String galContentId;

    @Schema(description = "콘텐츠 타입 아이디")
    private String galContentTypeId;

    @Schema(description = "촬영월")
    private String galPhotographyMonth;

    @Schema(description = "촬영장소")
    private String galPhotographyLocation;

    @Schema(description = "웹용 이미지 경로")
    private String galWebImageUrl;

    @Schema(description = "등록일")
    private String galCreatedtime;

    @Schema(description = "수정일")
    private String galModifiedtime;

    @Schema(description = "촬영자")
    private String galPhotographer;

    @Schema(description = "검색 키워드")
    private String galSearchKeyword;

    @Schema(description = "제목")
    private String galTitle;
}
