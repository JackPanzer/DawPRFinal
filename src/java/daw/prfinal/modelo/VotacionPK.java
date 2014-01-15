package daw.prfinal.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/******************************************
 * Fichero: VotacionPK.java
 *
 * Autor:             Juan Manuel Pedraza García <jackpanzer@github.com>
 * Fecha de creación: 11-Jan-2014
 * Descripcion:       Descripción
 ******************************************/

/*******************************************
* package daw.prfinal.modelo;
* import paquetes; 
*
* class VotacionPK
* {
*   public T nombreMetodo(params);
* }
*******************************************/

@Embeddable
public class VotacionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Votante")
    private long votante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Votado")
    private long votado;

    public VotacionPK() {
    }

    public VotacionPK(long votante, long votado) {
        this.votante = votante;
        this.votado = votado;
    }

    public long getVotante() {
        return votante;
    }

    public void setVotante(long votante) {
        this.votante = votante;
    }

    public long getVotado() {
        return votado;
    }

    public void setVotado(long votado) {
        this.votado = votado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) votante;
        hash += (int) votado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VotacionPK)) {
            return false;
        }
        VotacionPK other = (VotacionPK) object;
        if (this.votante != other.votante) {
            return false;
        }
        if (this.votado != other.votado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "daw.prfinal.modelo.VotacionPK[ votante=" + votante + ", votado=" + votado + " ]";
    }

}
/**#END CLASS COMMENT#**/