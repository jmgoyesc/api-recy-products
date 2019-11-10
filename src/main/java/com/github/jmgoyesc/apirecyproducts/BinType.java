package com.github.jmgoyesc.apirecyproducts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
class BinType {
   @Id
   private String id;
   private String name;
   private Color color;
}

@AllArgsConstructor
enum Color {

   BLUE("Blue", "#0000FF"),
   BROWN("Brown", ""),
   GREEN("Green", ""),
   WHITE("White", ""),
   YELLOW("Yellow", ""),
   BLACK("Black", "");

   private String name;
   private String hex;
}
