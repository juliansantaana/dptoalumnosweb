package school;


import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juliansantaana
 */
public class HelpersValidacion {
    
    // ----- funciones de validacion -----
    public static boolean isNumeric(String str){
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    
    public static boolean isAlpha(String name) {
        //return name.matches("[a-zA-Z ]+");
        return name.matches("[a-zA-Zäáàëéèíìöóòúùñç  .]+");
    }
    
    public static boolean validateDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(date);
            return true;
        }
        catch(ParseException ex) {
            return false;
        }
    }
    
}
