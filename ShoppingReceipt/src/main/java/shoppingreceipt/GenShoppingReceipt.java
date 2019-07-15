/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingreceipt;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Zwei
 */
public class GenShoppingReceipt {
    public static  Dictionary taxCalculators;
    public static void initTaxCalculators(){
      //Initialize Calcautor Handler
      taxCalculators = new Hashtable();
      taxCalculators.put("CA", new CATaxCalculator());
      taxCalculators.put("NY", new NYTaxCalculator());    
    }
    
    public static void main(String[] args) {
      initTaxCalculators();
      
      System.out.println("Please Enter Input:");
      Scanner scanner = new Scanner(System. in);      
      String input = scanner. nextLine();
      
      String[] splitResult = input.split(",");
      
      //Get Location
      Pattern locationPattern = Pattern.compile("Location: (.*)");
      Matcher matcher = locationPattern.matcher(splitResult[0].trim());
      String location = "";
      if(matcher.matches())
        location = matcher.group(1);
      
      System.out.println(String.format("%-15s%-15s%5s" , "item", "price", "qty" ));
      
    //Get ItemList And Generate receipt
      double subtotal = 0;
      double subtotalTax = 0;
      Pattern itemPattern = Pattern.compile("(\\d+) (.*) at (.*)");
      for(int i = 1; i < splitResult.length; i++){
          //extract item properites from string
          matcher = itemPattern.matcher(splitResult[i].trim());
          matcher.matches();
          String itemName = matcher.group(2);
          int itemQuanity = Integer.parseInt(matcher.group(1));
          double itemPrice = Double.parseDouble(matcher.group(3)); 
          
          //Calculation Part
          subtotal += itemPrice * itemQuanity;
          TaxCalculator taxCalculator = (taxCalculators.get(location.toUpperCase()) == null) ? new DefaultTaxCalculator() : (TaxCalculator)taxCalculators.get(location.toUpperCase());
          subtotalTax += taxCalculator.CalTax(itemName, itemPrice, itemQuanity);
          
          //Output Item info summary
          System.out.println(String.format("%-15s%-15s%5s" , itemName, itemPrice , itemQuanity ));
      }
      
      double total = subtotal + subtotalTax;
      System.out.println(String.format("%-15s%20.2f" , "subtotal:", subtotal ));
      System.out.println(String.format("%-15s%20.2f" , "tax:", subtotalTax ));
      System.out.println(String.format("%-15s%20.2f" , "total:", total ));
          
    }    
}
