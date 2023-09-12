package es.alura.hotel.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import es.alura.hotel.dao.ReservaDao;
import es.alura.hotel.model.Reserva;
import es.alura.hotel.util.ErrorUtil;
import es.alura.hotel.util.JPAUtil;
import es.alura.hotel.util.NotificationUtil;
import javafx.application.Platform;

public class ReservaService {

  static private List<Reserva> reservas;

  static private Reserva reserva;

  static private int reservaId;

  static public void RegistrarReserva(Runnable action) {
    NotificationUtil.showLoadingNotification();

    new Thread(() -> {
      EntityManager em = JPAUtil.getEntityManager();
      ReservaDao reservaDao = new ReservaDao(em);
      try {
        em.getTransaction().begin();
        reservaDao.guardar(reserva);
        em.getTransaction().commit();
        reservaId = reserva.getId();
        Platform.runLater(() -> {
          action.run();
          NotificationUtil.showSuccessNotification("Reserva '" + reservaId + "' guadada exitosamente");
        });
      } catch (RollbackException ex) {
        Platform.runLater(() -> {
          NotificationUtil.showErrorNotification("Error al guardar la reserva");
        });
      } finally {
        em.close();
      }
    }).start();
  }

  static public void searchReservas(String value, Runnable action) {
    Platform.runLater(() -> {
      NotificationUtil.showLoadingNotification();
    });

    new Thread(() -> {
      EntityManager em = JPAUtil.getEntityManager();

      ReservaDao reservaDao = new ReservaDao(em);
      try {
        List<Reserva> listReserva = reservaDao.buscarQuery(value);
        reservas = listReserva;
        Platform.runLater(() -> {
          NotificationUtil.showSynchronizationNotification();
          action.run();
        });
      } catch (ErrorUtil error) {
        Platform.runLater(() -> {
          NotificationUtil.showErrorNotification(error.getMessage());
        });
      } finally {
        em.close();
      }
    }).start();
  }

  public static void updateReserva(Reserva reserva) {
    Platform.runLater(() -> {
      NotificationUtil.showSynchronizationNotification();
    });

    new Thread(() -> {
      EntityManager em = JPAUtil.getEntityManager();

      ReservaDao reservaDao = new ReservaDao(em);
      try {
        reservaDao.actualizar(reserva);
        Platform.runLater(() -> {
          NotificationUtil.showSuccessNotification("Actualización exitosa");
        });
      } catch (ErrorUtil error) {
        Platform.runLater(() -> {
          NotificationUtil.showErrorNotification(error.getMessage());
        });
      } finally {
        em.close();
      }
    }).start();
  }

  public static void deleteReserva(Reserva reserva, Runnable action) {
    Platform.runLater(() -> {
      NotificationUtil.showSynchronizationNotification();
    });

    new Thread(() -> {
      EntityManager em = JPAUtil.getEntityManager();
      ReservaDao reservaDao = new ReservaDao(em);
      try {
        reservaDao.eliminar(reserva.getId());
        Platform.runLater(() -> {
          NotificationUtil.showSuccessNotification("Eliminado con éxito");
          action.run();
        });
      } catch (ErrorUtil error) {
        Platform.runLater(() -> {
          NotificationUtil.showErrorNotification(error.getMessage());
        });
      } finally {
        em.close();
      }
    }).start();
  }

  public static Reserva getReserva() {
    return reserva;
  }

  public static int getReservaId() {
    return reservaId;
  }

  public static List<Reserva> getReservas() {
    return reservas;
  }

  public static void setReserva(Reserva reserva) {
    ReservaService.reserva = reserva;
  }
}
