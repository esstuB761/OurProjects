import java.lang.invoke.SwitchPoint;

public class School {
    int numberOfStudents;
    String name;
    static WeatherRuEnum weatherRuEnum;
    public School(){
        this.name = "000";
        this.numberOfStudents = 0;
    }
    public School(String name_School,int numberOfStudents_School){
        this.name = name_School;
        this.numberOfStudents = numberOfStudents_School;
    }
    public void PrintStudyOrNotStudy(){
        int studyOrNotStudy = 0; // Если 0, то не учимся, иначе наоборот
        switch (weatherRuEnum){
            case Зима:
                studyOrNotStudy = 1;
                break;
            case Лето:
                studyOrNotStudy = 0;
                break;
            case Весна:
                studyOrNotStudy = 1;
                break;
            case Осень:
                studyOrNotStudy = 1;
                break;
        }
        if (studyOrNotStudy == 1){
            System.out.println(this.name + ", учащихся " + this.numberOfStudents +", сейчас мы учимся");
        }
        else{
            System.out.println(this.name + ", учащихся " + this.numberOfStudents +", сейчас мы отдыхаем");
        }
    }
}
