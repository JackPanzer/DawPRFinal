/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.prfinal.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JuanManuel
 */
@Entity
@Table(name = "votacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Votacion.findAll", query = "SELECT v FROM Votacion v"),
    @NamedQuery(name = "Votacion.findByVotante", query = "SELECT v FROM Votacion v WHERE v.votacionPK.votante = :votante"),
    @NamedQuery(name = "Votacion.findByVotado", query = "SELECT v FROM Votacion v WHERE v.votacionPK.votado = :votado"),
    @NamedQuery(name = "Votacion.findByFechaVoto", query = "SELECT v FROM Votacion v WHERE v.fechaVoto = :fechaVoto"),
    @NamedQuery(name = "Votacion.findByPuntuacion", query = "SELECT v FROM Votacion v WHERE v.puntuacion = :puntuacion")})
public class Votacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VotacionPK votacionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_voto")
    @Temporal(TemporalType.DATE)
    private Date fechaVoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntuacion")
    private int puntuacion;
    @JoinColumn(name = "Votante", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "Votado", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public Votacion() {
    }

    public Votacion(VotacionPK votacionPK) {
        this.votacionPK = votacionPK;
    }

    public Votacion(VotacionPK votacionPK, Date fechaVoto, int puntuacion) {
        this.votacionPK = votacionPK;
        this.fechaVoto = fechaVoto;
        this.puntuacion = puntuacion;
    }

    public Votacion(long votante, long votado) {
        this.votacionPK = new VotacionPK(votante, votado);
    }

    public VotacionPK getVotacionPK() {
        return votacionPK;
    }

    public void setVotacionPK(VotacionPK votacionPK) {
        this.votacionPK = votacionPK;
    }

    public Date getFechaVoto() {
        return fechaVoto;
    }

    public void setFechaVoto(Date fechaVoto) {
        this.fechaVoto = fechaVoto;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (votacionPK != null ? votacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Votacion)) {
            return false;
        }
        Votacion other = (Votacion) object;
        if ((this.votacionPK == null && other.votacionPK != null) || (this.votacionPK != null && !this.votacionPK.equals(other.votacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "daw.prfinal.modelo.Votacion[ votacionPK=" + votacionPK + " ]";
    }
    
}
