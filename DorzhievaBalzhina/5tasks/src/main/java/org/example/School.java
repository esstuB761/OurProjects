package org.example;

enum Season {
    WINTER("Зима"), SPRING("Весна"), SUMMER("Лето"), FALL("Осень");

    private String name;

    Season(String name)
    {
        this.name=name;
    }
    public String GetName()
    {
        return name;
    }
}


public class School {
    private int schoolNumber;
    private int numberOfStudents;
    private Season currentSeason;


    public School(int schoolNumber, int numberOfStudents, Season currentSeason) {
        this.schoolNumber = schoolNumber;
        this.numberOfStudents = numberOfStudents;
        this.currentSeason = currentSeason;
    }

    public void schoolInformation() {
        if (currentSeason.GetName() == "Лето") {
            System.out.println("School #" + schoolNumber + ", students " + numberOfStudents + ", currently on vacation");
        } else {
            System.out.println("School #" + schoolNumber + ", students " + numberOfStudents + ", currently studying");
        }
    }

    public static void main(String[] args) {
        School school1 = new School(888, 666, Season.WINTER);
        School school2 = new School(999, 777, Season.FALL);
        School school3 = new School(777,333,Season.SUMMER);
        School school4 = new School(555,888,Season.SPRING);

        school1.schoolInformation();
        school2.schoolInformation();
        school3.schoolInformation();
        school4.schoolInformation();
    }
}
