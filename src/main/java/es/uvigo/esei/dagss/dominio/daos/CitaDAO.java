/*
 Proyecto Java EE, DAGSS-2014
 */

package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.EstadoCita;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;


@Stateless
@LocalBean
public class CitaDAO  extends GenericoDAO<Cita>{    

    //Función para recuperar las citas de un médico en la fecha actual
    public List<Cita> getCitasMedicoHoy(Medico medico){
        Date fecha_act = Calendar.getInstance().getTime();
        TypedQuery<Cita> q = em.createQuery("SELECT c FROM Cita AS c "
                + " WHERE c.medico = :medico"
                + " AND c.fecha = :fecha_act ", Cita.class);
        q.setParameter("medico", medico);
        q.setParameter("fecha_act", fecha_act);
        return q.getResultList();
    }
    
    public void cambiarEstadoCita(Cita c,String estadoCita){
        c.setEstado(EstadoCita.valueOf(estadoCita));      
        actualizar(c);
    };
}
