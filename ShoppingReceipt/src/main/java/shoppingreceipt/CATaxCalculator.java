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
 * TaxCalculator for Location CA
 */
public class CATaxCalculator extends DefaultTaxCalculator{ 
    public CATaxCalculator() {
        super();
        defaultSalesTax = 0.0975;
    }   
    public void InitializeTaxRate(){
        taxCategoriesMappingDict = new Hashtable();
        taxCategoriesMappingDict.put(Category.FOOD, 0.0);
    }     
}
