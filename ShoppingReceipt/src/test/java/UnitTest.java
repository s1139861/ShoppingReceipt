/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zwei
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zwei
 */
import junit.framework.TestCase;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;
import shoppingreceipt.CATaxCalculator;
import shoppingreceipt.DefaultTaxCalculator;
import shoppingreceipt.GenShoppingReceipt;
import shoppingreceipt.NYTaxCalculator;
import shoppingreceipt.TaxCalculator;
import shoppingreceipt.TaxCalculator.Category;

public class UnitTest extends TestCase{
    @Test
    public void testLocationMaping(){
        GenShoppingReceipt.initTaxCalculators();
        assertTrue("CA will map to CATaxCalculator", GenShoppingReceipt.taxCalculators.get("CA") instanceof CATaxCalculator );
        assertTrue("NY will map to NYTaxCalculator", GenShoppingReceipt.taxCalculators.get("NY") instanceof NYTaxCalculator );
    }
    
    @Test
    public void testCategoryMapping(){
        TaxCalculator taxCalculator = new DefaultTaxCalculator();
        assertTrue("book will map to default Category", taxCalculator.getItemCategory("book") == Category.DEFAULT);
        assertTrue("potato chips will map to Category.FOOD",  taxCalculator.getItemCategory("potato chips") == Category.FOOD);
        assertTrue("pencil will map to default Category",taxCalculator.getItemCategory("pencil") == Category.DEFAULT );
        assertTrue("shirt will map to Category.CLOTHING", taxCalculator.getItemCategory("shirt") == Category.CLOTHING );
    }
    
    @Test
    public void testTaxRateMapping(){
        CATaxCalculator caTaxCalculator = new CATaxCalculator();
        NYTaxCalculator nyTaxCalculator = new NYTaxCalculator();
        
        //Test CA TaxMapping
        assertEquals("Category.DEFAULT in CA will map to 0.0975", 0.0975, caTaxCalculator.getCategoryTaxRate(Category.DEFAULT));
        assertEquals("Category.FOOD in CA will map to 0.0", 0.0, caTaxCalculator.getCategoryTaxRate(Category.FOOD));
        assertEquals("Category.CLOTHING in CA will map to 0.0975", 0.0975, caTaxCalculator.getCategoryTaxRate(Category.CLOTHING));
        
        //Test NY TaxMapping
        assertEquals("Category.DEFAULT in NY will map to 0.0875", 0.08875, nyTaxCalculator.getCategoryTaxRate(Category.DEFAULT));
        assertEquals("Category.FOOD in NY will map to 0.0", 0.0, nyTaxCalculator.getCategoryTaxRate(Category.FOOD));
        assertEquals("Category.CLOTHING in NY will map to 0.0", 0.0, nyTaxCalculator.getCategoryTaxRate(Category.CLOTHING));
    }    
    
    @Test
    public void testCalTax(){
        CATaxCalculator caTaxCalculator = new CATaxCalculator();
        NYTaxCalculator nyTaxCalculator = new NYTaxCalculator();
        assertEquals("Test Case 1 : Location: CA, 1 book at 17.99, 1 potato chips at 3.99", "1.80" ,String.format( "%.2f",caTaxCalculator.CalTax("book", 17.99, 1) + caTaxCalculator.CalTax("potato chips", 3.99, 1)));
        assertEquals("Test Case 2 : Location: NY, 1 book at 17.99, 3 pencils at 2.99", "2.40" ,String.format( "%.2f", nyTaxCalculator.CalTax("book", 17.99, 1) + nyTaxCalculator.CalTax("pencils", 2.99, 3)));
        assertEquals("Test Case 3 : Location: NY, 2 pencils at 2.99, 1 shirt at 29.99", "0.55" ,String.format( "%.2f",nyTaxCalculator.CalTax("pencils", 2.99, 2) + nyTaxCalculator.CalTax("shirt", 29.99, 1)));
    }    
}
