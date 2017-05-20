package com.example.ariso.planetas.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

/**
 * Created by ariso on 20/05/2017.
 */
@Entity
public class PLaneta {
    @Id
    private Long id;

    @NotNull
    private String texto;
    @NotNull private Date date;


    @Generated(hash = 542016605)
    public PLaneta(Long id, @NotNull String texto, @NotNull Date date) {
        this.id = id;
        this.texto = texto;
        this.date = date;
    }


    //USAR GENERATED PARA GENERAR EL CODIGO, THEN REBUILT
    @Generated(hash = 458247424)
    public PLaneta() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
