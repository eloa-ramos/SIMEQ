package modelo;

import java.time.LocalDate;

public class Encontro {
    private int idEncontro;
    private LocalDate dataEncontro;
    private boolean realizado; //

    public Encontro() {
        this.realizado = true;
    }


    public Encontro(LocalDate dataEncontro) {
        this.dataEncontro = dataEncontro;
        this.realizado = true;
    }


    public int getIdEncontro() {
        return idEncontro;
    }

    public void setIdEncontro(int idEncontro) {
        this.idEncontro = idEncontro;
    }

    public LocalDate getDataEncontro() {
        return dataEncontro;
    }

    public void setDataEncontro(LocalDate dataEncontro) {
        this.dataEncontro = dataEncontro;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }
}