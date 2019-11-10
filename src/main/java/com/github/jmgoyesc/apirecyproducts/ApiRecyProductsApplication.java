package com.github.jmgoyesc.apirecyproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class ApiRecyProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRecyProductsApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routes(BinTypeRepository br, ProductRepository pr) {
		return route()
				.GET("/bin-types", request -> ok().body(br.findAll(), BinType.class))
				.GET("/products", request -> ok().body(pr.findAll(), Product.class))
				.POST("/products", request -> request.bodyToMono(Product.class)
						.flatMap(pr::save)
						.map(Product::getId)
						.map(id -> UriComponentsBuilder.fromPath("/products/{id}").buildAndExpand(id).toUri())
						.flatMap(uri -> created(uri).build()))
				.build();
	}

}

interface ProductRepository extends ReactiveCrudRepository<Product, String> {}
interface BinTypeRepository extends ReactiveCrudRepository<BinType, String> {}