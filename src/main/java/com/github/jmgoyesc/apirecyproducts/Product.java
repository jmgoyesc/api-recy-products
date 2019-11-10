package com.github.jmgoyesc.apirecyproducts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
class Product {
   @Id
   private String id;
   private String barCode;
   private String qrCode;
   private String name;
   private List<String> labels;
   private String description;
   private List<String> imagePaths;
   private List<Part> parts;
}
