/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingreceipt;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author ChrisLam
 * TaxCalculator with defaulr behaviour implemented
 */
public abstract class TaxCalculator{
    protected Dictionary taxCategoriesMappingDict;
    protected double defaultSalesTax = 0;
    protected Dictionary categoryList;
    public static enum Category {
      DEFAULT,
      FOOD,
      CLOTHING
    }    
    public TaxCalculator() {
        InitializeTaxRate();
        InitializeCategoryList();
    }

    public double getDefaultSalesTax() {
        return defaultSalesTax;
    }

    public void setDefaultSalesTax(double defaultSalesTax) {
        this.defaultSalesTax = defaultSalesTax;
    }

    public Dictionary getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(Dictionary categoryList) {
        this.categoryList = categoryList;
    }

    public Dictionary getTaxCategoriesMappingDict() {
        return taxCategoriesMappingDict;
    }

    public void setTaxCategoriesMappingDict(Dictionary taxCategoriesMappingDict) {
        this.taxCategoriesMappingDict = taxCategoriesMappingDict;
    }

    public Category getItemCategory(String itemName){     
        return (categoryList.get(itemName.toLowerCase()) == null) ? Category.DEFAULT : (Category)categoryList.get(itemName);
    }
    
    public double getCategoryTaxRate(Category category){     
        return (taxCategoriesMappingDict.get(category) == null) ? defaultSalesTax : (Double)taxCategoriesMappingDict.get(category);
    }        
    
    public abstract void InitializeTaxRate();
    
    public abstract void InitializeCategoryList();
    
    public abstract double CalTax(String itemName, double price, int quantity);
    
    public double roundUpHelper(double toRoundUp){
        //roundup to nearest 0.05
        return Math.ceil(toRoundUp * 20.0) / 20.0;
    }
}
