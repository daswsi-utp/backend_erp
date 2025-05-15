package com.microservice.rrhh.service;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private final String url = "files/";
	
	public String saveFile(MultipartFile file) throws IOException{
		if (!file.isEmpty()) {
			byte [] bytes = file.getBytes();
			String originalFileName = Objects.requireNonNull(file.getOriginalFilename());
			Path path = Paths.get(url + originalFileName); // ⬅️ Save with the original name
			Files.write(path, bytes);
			//Codificar el nombre
			String encodedFileName = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8)
			                                   .replace("+", "%20"); // ⬅️ Codificate the name for the bd
			return encodedFileName;
		}
		return null;
	}
	
	public void deleteFile(String nombre) throws IOException{
		String originalFileName = URLDecoder.decode(nombre, StandardCharsets.UTF_8);
		Path path = Paths.get("files/" + originalFileName);
		Files.deleteIfExists(path);
		//File file = new File(url + nombre);
		//file.delete();
	}
	
}
