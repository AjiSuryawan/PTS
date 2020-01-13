package com.example.login;

public class model {
    private String nama;
    private String nism;
    private String noKK;

    public model(String nama, String nism, String noKK){
        this.nama = nama;
        this.nism = nism;
        this.noKK = noKK;
    }

    public String getName(){
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNisn(){
        return nism;
    }
    public void setNism(String nism) {
        this.nism = nism;
    }
    public String getNoKK(){

        return noKK;
    }
    public void setnokk(String noKK) {

        this.noKK = noKK;
    }
}
