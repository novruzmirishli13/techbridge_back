package com.example.HZT.Dto;

public class CountsDto {
    private Long studentCount;
    private Long parentCount;
    private Long regionCount;

    public CountsDto(Long studentCount, Long parentCount, Long regionCount) {
        this.studentCount = studentCount;
        this.parentCount = parentCount;
        this.regionCount = regionCount;
    }

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }

    public Long getParentCount() {
        return parentCount;
    }

    public void setParentCount(Long parentCount) {
        this.parentCount = parentCount;
    }

    public Long getRegionCount() {
        return regionCount;
    }

    public void setRegionCount(Long regionCount) {
        this.regionCount = regionCount;
    }
}
