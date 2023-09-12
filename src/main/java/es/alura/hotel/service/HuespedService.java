package es.alura.hotel.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import es.alura.hotel.dao.HuespedDao;
import es.alura.hotel.model.Huesped;
import es.alura.hotel.util.ErrorUtil;
import es.alura.hotel.util.JPAUtil;
import es.alura.hotel.util.NotificationUtil;
import javafx.application.Platform;

public class HuespedService {

  static private List<Huesped> huespedes;

  static private Huesped huesped;

  static public void RegistrarHuesped(Runnable action) {
    NotificationUtil.showLoadingNotification();

    new Thread(() -> {
      EntityManager em = JPAUtil.getEntityManager();
      HuespedDao huespedDao = new HuespedDao(em);
      try {
        em.getTransaction().begin();
        huespedDao.guardar(huesped);
        em.getTransaction().commit();
        Platform.runLater(() -> {
          action.run();
          NotificationUtil.showSuccessNotification(
              "Registro del huésped '" + huesped.getNombre() + " " + huesped.getApellido() + "' guadado con éxito");
        });
      } catch (RollbackException ex) {
        Platform.runLater(() -> {
          NotificationUtil.showErrorNotification("Error al registrar el huésped");
        });
      } finally {
        em.close();
      }
    }).start();
  }

  static public void searchHuespedes(String value, Runnable action) {
    Platform.runLater(() -> {
      NotificationUtil.showLoadingNotification();
    });

    new Thread(() -> {
      EntityManager em = JPAUtil.getEntityManager();

      HuespedDao huespedDao = new HuespedDao(em);
      try {
        List<Huesped> listHuesped = huespedDao.buscarQuery(value);
        huespedes = listHuesped;
        Platform.runLater(() -> {
          NotificationUtil.showSuccessNotification("Datos cargados exitosamente");
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

  public static void updateHuesped(Huesped huesped) {
    Platform.runLater(() -> {
      NotificationUtil.showSynchronizationNotification();
    });

    new Thread(() -> {
      EntityManager em = JPAUtil.getEntityManager();

      HuespedDao huespedDao = new HuespedDao(em);
      try {
        huespedDao.actualizar(huesped);
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

  public static void deleteHuesped(Huesped huesped, Runnable action) {
    Platform.runLater(() -> {
      NotificationUtil.showSynchronizationNotification();
    });

    new Thread(() -> {
      EntityManager em = JPAUtil.getEntityManager();
      HuespedDao huespedDao = new HuespedDao(em);
      try {
        huespedDao.eliminar(huesped.getId());
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

  public static List<Huesped> getHuespedes() {
    return huespedes;
  }

  public static Huesped getHuesped() {
    return huesped;
  }

  public static void setHuesped(Huesped huesped) {
    HuespedService.huesped = huesped;
  }

}
