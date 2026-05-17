package com.hasanz.BookVault.DTO;

public class BookReviewReportDto {
    private String title;
    private int reviewCount;

    public BookReviewReportDto(String title, int reviewCount) {
        this.title = title;
        this.reviewCount = reviewCount;
    }

    public String getTitle() {
        return title;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    @Override
    public String toString() {
        return "BookReviewReportDto{" +
                "title='" + title + '\'' +
                ", reviewCount=" + reviewCount +
                '}';
    }
}
