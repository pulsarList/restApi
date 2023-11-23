package models;

import lombok.Data;

public @Data class ColorsData {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;
}
