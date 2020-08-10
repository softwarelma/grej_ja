package com.softwarelma.grej.rest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.softwarelma.grej.orm.GrejBo;
import com.softwarelma.grej.orm.GrejDao;

/**
 * see https://spring.io/guides/gs/rest-service-cors/
 */
@RestController
public class GrejController {

	private static final String CORS_ORIGIN = "http://localhost:4200";// http://localhost:4200,http://localhost:8080
	private final GrejDao dao = new GrejDao();

	public GrejController() {
	}

	@CrossOrigin(origins = CORS_ORIGIN)
	@GetMapping("/rest/getAll")
	public GrejListResponse getAll(HttpServletResponse httpServletResponse) {
		return this.dao.getAll();
	}

	@CrossOrigin(origins = CORS_ORIGIN)
	@GetMapping("/rest/get/{id}")
	public GrejResponse get(HttpServletResponse httpServletResponse, @PathVariable Integer id) {
		return this.dao.get(id);
	}

	@CrossOrigin(origins = CORS_ORIGIN, allowedHeaders = "*")
	@PostMapping(path = "/rest/postNew", consumes = "application/json", produces = "application/json")
	public GrejResponse postNew(HttpServletResponse httpServletResponse, @RequestBody GrejBo bo) {
		return this.dao.postNew(bo);
	}

	@CrossOrigin(origins = CORS_ORIGIN)
	@PutMapping("/rest/putExisting")
	public GrejResponse putExisting(HttpServletResponse httpServletResponse, @RequestBody GrejBo bo) {
		return this.dao.putExisting(bo);
	}

	@CrossOrigin(origins = CORS_ORIGIN)
	@PatchMapping("/rest/patchExisting")
	public GrejResponse patchExisting(HttpServletResponse httpServletResponse, @RequestBody GrejBo bo) {
		return this.dao.patchExisting(bo);
	}

}