package com.cs.ajinkya.moviecatalogservice.resources;

import java.util.Collections;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.ajinkya.moviecatalogservice.models.CatalogItem;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathParam("userId") String userId) {
		
		return Collections.singletonList(new CatalogItem("Transformer", "Test", 4));
	}

}
