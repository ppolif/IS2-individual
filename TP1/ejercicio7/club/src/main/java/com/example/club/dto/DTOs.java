package com.example.club.dto;

import com.example.club.enums.FormaPago;
import com.example.club.enums.Rol;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

import java.time.LocalDate;

public class DTOs {

    @Data
    public static class RegistroSocioDTO {
        private String nombre;
        private String apellido;
        private Integer dni;
        private String correo;
        private String clave;
        private String repetirClave;
        private MultipartFile archivoFoto;
    }

    @Data
    public static class RegistroFamiliarDTO {
        private String nombre;
        private String apellido;
        private Integer dni;
        private String parentesco;
        private Long idFamilia;
        private MultipartFile archivoFoto;
    }

    @Data
    public static class AccesoDTO {
        private Integer dni;
        // Solo enviamos el DNI desde portería. El sistema calcula si es entrada o salida.
    }

    @Data
    public class UsuarioDTO {
        private String id;
        private String correo;
        private String clave;
        private Rol rol;
        private String nombreSocio;
        private String apellidoSocio;
        private Integer dniSocio;
    }

    @Data
    public class FamiliarDTO {
        private Long id;
        private String nombre;
        private String apellido;
        private Integer dni;
        private String parentesco;
        private Long idFamilia;
        private MultipartFile archivoFoto;
    }

    @Data
    public class PagoCuotaDTO {
        private Long id;
        private Long idSocio;
        private Double monto;
        private FormaPago formaPago;
        private String comprobante;
        private LocalDate fechaPago;
    }

    @Data
    public class FamiliaDTO {
        private Long id;
        private String apellidoFamilia;
    }
}
