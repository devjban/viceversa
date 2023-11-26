


## 구동방법

1. 소스코드 다운로드
2. Springboot 프로젝트 실행
  * 실행 시 Local에 테이블 자동 생성 
     * jdbc:h2:mem:test;MODE=MySQL
     * Table : GALLERY_ITEM
 * DB 생성 후 데이터 자동으로 다운로드
     * https://www.data.go.kr/data/15101914/openapi.do
3. Swagger 접속
   * http://localhost:8080/swagger-ui/index.html#/gallery-item-controller/getGalleryItems
4. 다운로드 받은 데이터로 검색 API 실행 가능
