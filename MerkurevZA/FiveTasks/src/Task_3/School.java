package Task_3;

public class School {
    private Integer studentCount;
    private String schoolName;
    private SEASONS currSeason;

    public School(Integer studentCount, String schoolName, SEASONS currSeason){
        this.currSeason = currSeason;
        this.schoolName = schoolName;
        this.studentCount = studentCount;
    }


    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public SEASONS getCurrSeason() {
        return currSeason;
    }

    public void setCurrSeason(SEASONS currSeason) {
        this.currSeason = currSeason;
    }

    public void WriteInfo(){

        String areLearning = switch (currSeason){
            case Зима -> "не учимся";
            default -> "учимся";
        };

        System.out.printf("Школа %s, учащихся %d, сейчас мы &s", this.schoolName, this.studentCount, areLearning);
    }
}