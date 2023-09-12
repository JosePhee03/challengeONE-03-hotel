package es.alura.hotel.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

  private static EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("alura_hotel");

  public static EntityManager getEntityManager() {
    return emFactory.createEntityManager();

  }

}
