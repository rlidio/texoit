package br.com.texoit.texoit.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.com.texoit.texoit.utils.TexoitException;

public class ReadFileService {

	private static final Integer ERROR_READY_FILE_CODE = 1000;

	public List<String> read(String pathFile) throws TexoitException {
		try {
			File file = new File(pathFile);
			List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
			removeHead(lines);
			return lines;
		} catch (IOException ioException) {
			throw new TexoitException(ERROR_READY_FILE_CODE, ioException.getMessage());
		}

	}

	private void removeHead(List<String> lines) {
		if(!lines.isEmpty()) {
			lines.remove(0);
		}
	}

}
