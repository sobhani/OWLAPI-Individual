/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package cz.makub;

public class RandomC {
 
      public static void anyRandomIntRange(Random random, int low, int high) {
        int randomInt = random.nextInt(high) + low;
        System.out.println(randomInt);
    }

    int nextInt(int high) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	}

