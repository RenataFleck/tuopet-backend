package com.example.tuopet.controller.exception;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class StandardError {
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
