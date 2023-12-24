package org.example;
public class School {
    private int colstud;
    private int number;
    private Season season;

    public enum Season {
        ЗИМА("зимой"),
        ВЕСНА("весной"),
        ЛЕТО("летом"),
        ОСЕНЬ("осенью");

        private String Name;

        Season(String Name) {
            this.Name = Name;
        }

        public String getName() {
            return Name;
        }
    }

    public School(int colstud, int number, Season season) {
        this.colstud = colstud;
        this.number = number;
        this.season = season;
    }

    public void printSchoolInfo() {
        System.out.println("Школа №" + number + ", учащихся " + colstud +
                ", сейчас мы " + (season == Season.ЛЕТО ? "отдыхаем" : "учимся " + season.getName()));
    }
}