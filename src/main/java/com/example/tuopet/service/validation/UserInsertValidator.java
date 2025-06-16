package com.example.tuopet.service.validation;

import com.example.tuopet.controller.exception.FieldMessage;
import com.example.tuopet.dto.UsuarioInsertDto;
import com.example.tuopet.repository.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UsuarioInsertDto> {

	private final UsuarioRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UsuarioInsertDto dto, ConstraintValidatorContext context) {
		
		var list = new ArrayList<FieldMessage>();
		
		var usuario = repository.findByEmailOrCpf(dto.getEmail(), dto.getCpf());
		if (usuario != null) {
			if (usuario.getEmail().equals(dto.getEmail()))
				list.add(new FieldMessage("email", "Email já existe"));

			if (usuario.getCpf().equals(dto.getCpf()))
				list.add(new FieldMessage("cpf", "Cpf já existe"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
