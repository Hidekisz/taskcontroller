package br.com.vini.taskcontroller.enums;

public enum DayType {
    TODAY(1,"TODAY"),
    DATE(2,"DATE"),
    ALL(3,"ALL");

    private Integer dayTipe;
    private String name;


    DayType(int code,String nameC) {
        dayTipe =  code;
        name = nameC;
    }
}
