package br.com.texoit.texoit.utils;

import lombok.Getter;

public class TexoitException extends Exception {

	private static final long serialVersionUID = 2642549586637782863L;

	@Getter
	private Integer code;

	public TexoitException(Integer code, String message) {
		super(message);
		this.code = code;
	}

}
