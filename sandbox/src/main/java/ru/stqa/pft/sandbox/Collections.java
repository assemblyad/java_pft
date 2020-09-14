package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main (String [] args) {
//    String[] langs = {"Java", "C#", "Python", "PHP"};
    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
/*
    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
    languages.add("PHP");
    String [] langs = new String[4];
    langs [0] = "Java";
    langs [1] = "C#";
    langs [2] = "Python";
    langs [3] = "PHP";

    for (int i=0;i<langs.length; i++){
      System.out.println("I wanna lean "+ langs[i]);
    }
//    https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html
      https://docs.oracle.com/javase/8/docs/api/index.html
   for (String l: langs) System.out.println("I wanna lean "+ l);
  }
     for (String l: languages) System.out.println("I wanna lean "+ l);
  }
     for (int i=0; i<languages.size();i++) System.out.println("I wanna lean "+ languages.get(i));
  }
 */
    for (String l : languages) {
      System.out.println("I wanna lean " + l);
    }
  }
}
