package es.alura.hotel.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.alura.hotel.model.Reserva;
import es.alura.hotel.util.ErrorUtil;

public class ReservaDao implements Dao<Reserva> {

  private EntityManager em;

  public ReservaDao(EntityManager em) {
    this.em = em;
  }

  @Override
  public void guardar(Reserva reserva) throws EntityExistsException {
    try {
      this.em.persist(reserva);
    } catch (Exception ex) {
      throw new EntityExistsException();
    }
  }

  @Override
  public void actualizar(Reserva newReserva) throws ErrorUtil {
    try {
      em.getTransaction().begin();
      em.merge(newReserva);
      em.getTransaction().commit();
    } catch (Exception ex) {
      throw new ErrorUtil("Error al actualizar la reserva id(" + newReserva.getId() + ")");
    } finally {
      em.close();
    }
  }

  @Override
  public Reserva buscarId(int id) throws ErrorUtil {
    try {
      Reserva reserva = this.em.find(Reserva.class, id);
      if (reserva == null)
        throw new Exception();
      return reserva;
    } catch (Exception ex) {
      throw new ErrorUtil("Error al buscar la reserva id(" + id + ")");
    }
  }

  @Override
  public void eliminar(int id) throws ErrorUtil {
    try {
      Reserva reserva = this.em.find(Reserva.class, id);
      em.getTransaction().begin();
      em.remove(reserva);
      em.getTransaction().commit();
    } catch (Exception ex) {
      System.out.println(ex);
      throw new ErrorUtil("Error al intentar eliminar la Reserva id(" + id + ")");
    }
  }

  @Override
  public List<Reserva> listar() throws ErrorUtil {
    try {
      TypedQuery<Reserva> query = em.createQuery("SELECT e FROM Reserva e", Reserva.class);
      return query.getResultList();
    } catch (Exception ex) {
      throw new ErrorUtil("Error al intentar listar las reservas");
    }
  }

  @Override
  public List<Reserva> buscarQuery(String value) throws ErrorUtil {
    try {
      String jpqlQuery = "SELECT r FROM Reserva r " + "WHERE r.id LIKE :param OR " + "r.fechaDeEntrada LIKE :param OR "
          + "r.fechaDeSalida LIKE :param OR " + "r.valor LIKE :param OR " + "r.formaDePago LIKE :param";

      TypedQuery<Reserva> query = em.createQuery(jpqlQuery, Reserva.class);
      query.setParameter("param", "%" + value + "%");
      return query.getResultList();

    } catch (Exception e) {
      throw new ErrorUtil();
    }

  }

}
