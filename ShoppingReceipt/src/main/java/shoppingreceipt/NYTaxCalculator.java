/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingreceipt;

import java.util.Hashtable;

/**
 *
 * @author ChrisLam
 * TaxCalculator for Location NY
 */
public class NYTaxCalculator extends DefaultTaxCalculator{   
    public NYTaxCalculator() {
        super();
        defaultSalesTax = 0.08875;
    } 
    public void InitializeTaxRate(){
        taxCategoriesMappingDict = new Hashtable();
        taxCategoriesMappingDict.put(Category.FOOD, 0.0);
        taxCategoriesMappingDict.put(Category.CLOTHING, 0.0);
    }   
}
