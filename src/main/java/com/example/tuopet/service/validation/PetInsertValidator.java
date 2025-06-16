package com.example.tuopet.service.validation;

import com.example.tuopet.controller.exception.FieldMessage;
import com.example.tuopet.dto.PetInsertDto;
import com.example.tuopet.repository.PetRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PetInsertValidator implements ConstraintValidator<PetInsertValid, PetInsertDto> {

	private final PetRepository repository;
	
	@Override
	public void initialize(PetInsertValid ann) {
	}

	@Override
	public boolean isValid(PetInsertDto petInsertDto, ConstraintValidatorContext context) {
		var list = new ArrayList<FieldMessage>();

		repository.findByUsuarioIdAndNome(petInsertDto.getUsuarioId(), petInsertDto.getNome())
				.ifPresent(pet -> list.add(new FieldMessage("nome", "Já existe um pet com esse nome para esse usuário")));

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}
}
