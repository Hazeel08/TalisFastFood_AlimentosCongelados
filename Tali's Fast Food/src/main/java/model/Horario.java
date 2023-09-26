/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author steve
 */
public class Horario {

    private int cod_horario;
    private Usuario oUsuario;
    private LocalDateTime infoEntregas;
    private LocalDateTime minDateTime;
    private LocalDateTime maxDateTime;
    private Date fechaYHorario;

    public Horario() {
        minDateTime = LocalDateTime.now().minusWeeks(0);
    }

    public int getCod_horario() {
        return cod_horario;
    }

    public void setCod_horario(int cod_horario) {
        this.cod_horario = cod_horario;
    }

    public Usuario getoUsuario() {
        return oUsuario;
    }

    public void setUsuario(Usuario oUsuario) {
        this.oUsuario = oUsuario;
    }

    public Date getFechaYHorario() {
        return fechaYHorario;
    }

    public void setFechaYHorario(Date fechaYHorario) {
        this.fechaYHorario = fechaYHorario;
    }

    public LocalDateTime getInfoEntregas() {
        return infoEntregas;
    }

    public void setInfoEntregas(LocalDateTime infoEntregas) {
        this.infoEntregas = infoEntregas;
    }

    public LocalDateTime getMinDateTime() {
        return minDateTime;
    }

    public void setMinDateTime(LocalDateTime minDateTime) {
        this.minDateTime = minDateTime;
    }

    public LocalDateTime getMaxDateTime() {
        return maxDateTime;
    }

    public void setMaxDateTime(LocalDateTime maxDateTime) {
        this.maxDateTime = maxDateTime;
    }

    public String getFormatoFecha() {
        String formato = infoEntregas.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return formato;
    }

    public java.sql.Timestamp convertirLocalDateTimeADate() {
        Instant instant = infoEntregas.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return new java.sql.Timestamp(date.getTime());
    }

    public java.sql.Timestamp convertirFechaSQL() {
        return new java.sql.Timestamp(fechaYHorario.getTime());
    }

    public String getFormatoFechaYHora() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formato.format(convertirFechaSQL());
    }
}
