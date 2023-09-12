package es.alura.hotel.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.alura.hotel.model.Huesped;
import es.alura.hotel.util.ErrorUtil;

public class HuespedDao implements Dao<Huesped> {

  private EntityManager em;

  public HuespedDao(EntityManager em) {
    this.em = em;
  }

  @Override
  public void guardar(Huesped huesped) throws ErrorUtil {
    try {
      this.em.persist(huesped);
    } catch (EntityExistsException ex) {
      throw new ErrorUtil("El huesped ya existe");
    }
  }

  @Override
  public void actualizar(Huesped newHuesped) throws ErrorUtil {
    try {
      em.getTransaction().begin();
      em.merge(newHuesped);
      em.getTransaction().commit();
    } catch (Exception ex) {
      throw new ErrorUtil("Error al actualizar el huesped id(" + newHuesped.getId() + ")");
    } finally {
      em.close();
    }
  }

  @Override
  public Huesped buscarId(int id) throws ErrorUtil {
    try {
      Huesped huesped = this.em.find(Huesped.class, id);
      if (huesped == null)
        throw new Exception();
      return huesped;
    } catch (Exception ex) {
      throw new ErrorUtil("Error al buscar el huesped id(" + id + ")");
    }
  }

  @Override
  public void eliminar(int id) throws ErrorUtil {
    try {
      Huesped huesped = this.em.find(Huesped.class, id);
      em.getTransaction().begin();
      em.remove(huesped);
      em.getTransaction().commit();
    } catch (Exception ex) {
      throw new ErrorUtil("Error al intentar eliminar el huesped id(" + id + ")");
    }
  }

  @Override
  public List<Huesped> listar() throws ErrorUtil {
    try {
      TypedQuery<Huesped> query = em.createQuery("SELECT e FROM Huesped e", Huesped.class);
      return query.getResultList();
    } catch (Exception ex) {
      throw new ErrorUtil("Error al intentar listar los huespedes");
    }
  }

  @Override
  public List<Huesped> buscarQuery(String value) throws ErrorUtil {
    String jpqlQuery = "SELECT h FROM Huesped h " + "WHERE h.id LIKE :param OR " + "h.apellido LIKE :param OR "
        + "h.fechaDeNacimiento LIKE :param OR " + "h.nacionalidad LIKE :param OR " + "h.telefono LIKE :param OR "
        + "h.nombre LIKE :param OR " + "h.reserva.id LIKE :param";

    TypedQuery<Huesped> query = em.createQuery(jpqlQuery, Huesped.class);
    query.setParameter("param", "%" + value + "%");

    return query.getResultList();
  }

}
