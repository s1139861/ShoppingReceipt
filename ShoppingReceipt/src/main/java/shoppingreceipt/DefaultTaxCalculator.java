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
 */
public class DefaultTaxCalculator extends TaxCalculator{
    public void InitializeTaxRate(){
        taxCategoriesMappingDict = new Hashtable();
    }    
    public void InitializeCategoryList(){
        categoryList = new Hashtable();
        categoryList.put("potato chips", Category.FOOD);
        categoryList.put("shirt", Category.CLOTHING);
    }       
    public double CalTax(String itemName, double price, int quantity) {
       //if no category match, default tax rate is zero
       TaxCalculator.Category category = getItemCategory(itemName);
       double taxRate =  getCategoryTaxRate(category);
       
       return roundUpHelper(price * quantity * taxRate);
    }       
}
