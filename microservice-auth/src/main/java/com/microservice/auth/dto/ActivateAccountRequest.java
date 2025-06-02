package com.microservice.auth.dto;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class ActivateAccountRequest {
	 @NotBlank(message = "El email no puede estar vacío")
	    @Email(message = "Debe ser un email válido")
	    private String email;
	    
	    @NotBlank(message = "El DNI no puede estar vacío")
	    @Size(min = 8, max = 20, message = "El DNI debe tener entre 8 y 20 caracteres")
	    private String dni;
	    
	    @NotBlank(message = "La contraseña no puede estar vacía")
	    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
	    private String password;
	    
	    @NotBlank(message = "La confirmación de contraseña no puede estar vacía")
	    @Size(min = 8, message = "La confirmación debe tener al menos 8 caracteres")
	    private String passwordConfirmation;
	    
	    private String roleName;  
	}	