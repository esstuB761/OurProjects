package org.example;

public class Consumer {
    private String fio;

    private Integer age;
    private String adress;

    public Consumer(String fio, Integer age, String adress) {
        this.fio = fio;
        this.age = age;
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {this.age = age; }
}
