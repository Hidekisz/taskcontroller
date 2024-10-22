package br.com.vini.taskcontroller.enums;

public enum DayType {
    TODAY(1),
    DATE(2),
    ALL(3);

    private Integer dayTipe;

    DayType(Integer dayTipe){
        this.dayTipe = dayTipe;
    }

    public Integer getRole(){
        return dayTipe;
    }


}
