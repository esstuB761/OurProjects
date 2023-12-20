package org.example;

public class Consumer {
    private String fio;
    private String adress;

    public Consumer(String fio, String adress) {
        this.fio = fio;
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
}
