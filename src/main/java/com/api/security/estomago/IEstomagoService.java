package com.api.security.estomago;

import com.api.security.comida.Comida;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;


public interface IEstomagoService{
    public void setTotalConsumido(int id);
    public Comida añadirComida(String nombreUsuario, int id, MultipartFile file,String nombreComida, int calorias, HttpServletRequest request);
    public void eliminarComida(int idEstomago, int idComida);
    public Comida editarComida(int idEstomago, int idComida, String nuevoNombreComida, int nuevoCalorias, String nombreUsuario, MultipartFile file, HttpServletRequest request);
}
