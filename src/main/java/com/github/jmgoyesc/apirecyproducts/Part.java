package com.github.jmgoyesc.apirecyproducts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Part {
   private String name;
   private String binType;
   private List<String> imagePaths;
}
