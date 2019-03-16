/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPackage;

/**
 *
 * @author irshed
 */
public class New {

    public static void main(String[] args) {
        int total = 40;
        float x = (float) (((float) total / 60.0) * 50.0);
        float y = (float) 1.5;
        x = x * y;
        total = (int) (Math.round(x));
        System.out.print(total);
    }
}
