package es.alura.hotel.dao;

import java.util.List;

import es.alura.hotel.util.ErrorUtil;

public interface Dao<T> {

  void guardar(T entidad) throws ErrorUtil;

  void actualizar(T entidad) throws ErrorUtil;

  T buscarId(int id) throws ErrorUtil;

  void eliminar(int id) throws ErrorUtil;

  List<T> listar() throws ErrorUtil;

  List<T> buscarQuery(String value) throws ErrorUtil;

}
