package com.github.jmgoyesc.apirecyproducts;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

@Log4j2
@Service
@RequiredArgsConstructor
class DataInitializer {

   private final BinTypeRepository binTypeRepository;

   @EventListener(ApplicationReadyEvent.class)
   public void ready() {
      var saves = Flux.just(
            BinType.builder().name("Paper").color(Color.BLUE).build(),
            BinType.builder().name("Brown Glass").color(Color.BROWN).build(),
            BinType.builder().name("Green Glass").color(Color.GREEN).build(),
            BinType.builder().name("White Glass").color(Color.WHITE).build(),
            BinType.builder().name("Plastic").color(Color.YELLOW).build(), BinType.builder().name("Organic").color(Color.BROWN).build(),
            BinType.builder().name("Household Waste").color(Color.BLACK).build())
            .flatMap(this.binTypeRepository::save);

      this.binTypeRepository
            .deleteAll()
            .thenMany(saves)
            .thenMany(this.binTypeRepository.findAll())
            .subscriberContext(Context.of("a", "b"))
            .subscribe(log::info);
   }

}
