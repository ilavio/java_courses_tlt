package com.potemkin.i;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.config.EntityManagerConfigLocal;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.repository.ProductRepositoryImpl;
import com.potemkin.i.repository.SupplierRepositoryImpl;
import com.potemkin.i.repository.hard.ProductRepositoryHard;
import com.potemkin.i.repository.hard.SupplierRepositoryHard;

/**
 * Класс тестирования CrudHandlerSupAndProductTest для репозиториев
 * SupplierRepository, ProductRepository
 * 
 * @author Илья Пот
 *
 */
public class CrudHandlerSupAndProductTest {
    private EntityManagerFactory entityManagerFactoryMock;
    private EntityManager entityManagerMock;
    private EntityTransaction entityTransactionMock;
    private EntityManagerConfigLocal springConfigLocal = new EntityManagerConfigLocal();
    private SupplierRepositoryImpl repoSup;
    private ProductRepositoryImpl repoProd;
    private SupplierRepositoryHard supHard = new SupplierRepositoryHard();
    private ProductRepositoryHard prodHard = new ProductRepositoryHard();

    @BeforeEach
    public void maskingObjects() {
        entityManagerFactoryMock = mock(EntityManagerFactory.class);
        entityManagerMock = mock(EntityManager.class);
        entityTransactionMock = mock(EntityTransaction.class);

        when(entityManagerFactoryMock.createEntityManager()).thenReturn(entityManagerMock);
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
        repoSup = new SupplierRepositoryImpl(entityManagerFactoryMock);
        repoProd = new ProductRepositoryImpl(entityManagerFactoryMock);
        supHard.addSupplier(new Supplier());
        prodHard.addProduct(new Product(), 0);
    }

    @Test
    public void addTest() {
        var sup = supHard.getSupplier(0);
        repoSup.addSupplier(supHard.getSupplier(0));
        repoProd.addProduct(prodHard.getProduct(0), 1);

        verify(entityManagerMock).persist(eq(sup));
    }

    @Test
    public void parseForProductTest() {
        var json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"CHUPAKA\",\r\n" + "    \"phone\" : \"954505\"\r\n" + "}");
        var sup = repoSup.parseForSupplier(json);
        assertNotNull(sup);
    }

    @Test
    public void parseForSupplierTets() {
        var json = new JSONObject("{\r\n" + "    \"productName\" : \"TRUSARDI\",\r\n" + "    \"supplierId\" : 8,\r\n"
                + "    \"unitPrice\" : 1000.12,\r\n" + "    \"isDiscontinued\" : true\r\n" + "}");

        var prod = repoProd.parseForProduct(json);
        assertNotNull(prod);
    }

    @Test
    public void getSupplierTest() {
        TypedQuery<Supplier> queryMock = (TypedQuery<Supplier>) mock(TypedQuery.class);
        when(entityManagerMock.createQuery("SELECT sup FROM Supplier sup WHERE sup.supplierId = ?1", Supplier.class))
                .thenReturn(queryMock);
        when(queryMock.setParameter(1, 1)).thenReturn(queryMock);
        var sup = supHard.getSupplier(0);
        when(queryMock.getSingleResult()).thenReturn(supHard.getSupplier(0));

        var supTest = repoSup.getSupplier(1);

        assertEquals(sup, supTest);
    }

    @Test
    public void getProductTest() {
        TypedQuery<Product> queryMock = (TypedQuery<Product>) mock(TypedQuery.class);
        when(entityManagerMock.createQuery("SELECT prod FROM Product prod WHERE prod.productId = ?1", Product.class))
                .thenReturn(queryMock);
        when(queryMock.setParameter(1, 1)).thenReturn(queryMock);
        var prod = new Product();
        when(queryMock.getSingleResult()).thenReturn(prod);

        var prodTest = repoProd.getProduct(1);

        assertEquals(prodTest, prod);
    }

    @Test
    public void getProductAllTest() {
        TypedQuery<Product> queryMock = (TypedQuery<Product>) mock(TypedQuery.class);
        when(entityManagerMock.createQuery("SELECT prod FROM Product prod", Product.class)).thenReturn(queryMock);
        when(queryMock.setParameter(1, 1)).thenReturn(queryMock);
        List<Product> prod = new ArrayList<Product>();
        when(queryMock.getResultList()).thenReturn(prod);

        var prodTest = repoProd.getProductAll();

        assertEquals(prodTest, prod);
    }

    @Test
    public void getSupplierAllTest() {
        TypedQuery<Supplier> queryMock = (TypedQuery<Supplier>) mock(TypedQuery.class);
        when(entityManagerMock.createQuery("SELECT sup FROM Supplier sup", Supplier.class)).thenReturn(queryMock);
        when(queryMock.setParameter(1, 1)).thenReturn(queryMock);
        var sup = new ArrayList<Supplier>();
        when(queryMock.getResultList()).thenReturn(sup);

        var supTest = repoSup.getSupplierAll();

        assertEquals(supTest, sup);
    }

    @Test
    public void changeEntityTest() {
        var json = new JSONObject(
                "{\r\n" + "        \"phone\": \"954505\",\r\n" + "        \"companyName\": \"ULIBAKA-LIBA\"\r\n" + "}");
        when(entityManagerMock.find(Supplier.class, 1)).thenReturn(new Supplier());

        var cust = repoSup.changeEntity(json, 1);

        assertEquals(cust.getCompanyName(), "ULIBAKA-LIBA");
        assertEquals(cust.getPhone(), "954505");
    }

    @Test
    public void changeProductTest() {
        var json = new JSONObject("{\r\n" + "    \"unitPrice\": 657.12,\r\n" + "    \"isDiscontinued\": true,\r\n"
                + "    \"productName\": \"SHOES\"\r\n" + "}");
        when(entityManagerMock.find(Product.class, 1)).thenReturn(new Product());

        var prod = repoProd.changeProduct(json, 1);

        assertEquals(prod.getProductName(), "SHOES");
        assertEquals(prod.getUnitPrice(), 657.12);
    }

    @Test
    public void deleteSupplierTest() {
        when(entityManagerMock.find(Supplier.class, 1)).thenReturn(new Supplier());
        repoSup.deleteSupplier(1);
        verify(entityManagerMock).persist(eq(new Supplier()));
        supHard.deleteSupplier(0);
    }

    @Test
    public void deleteProductTest() {
        var prod = new Product();
        when(entityManagerMock.find(Product.class, 1)).thenReturn(prod);
        repoProd.deleteProduct(1);
        verify(entityManagerMock).persist(eq(prod));
        prodHard.deleteProduct(0);
    }
}
