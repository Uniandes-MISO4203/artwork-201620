/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.artwork.test.persistence;
import co.edu.uniandes.csw.artwork.entities.ClientEntity;
import co.edu.uniandes.csw.artwork.entities.PaymentEntity;
import co.edu.uniandes.csw.artwork.persistence.PaymentPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class PaymentPersistenceTest {

    /**
     * @generated
     */
    @Inject
    private PaymentPersistence paymentPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;
    
       private List<PaymentEntity> data = new ArrayList<>();
       
        private static final Logger LOGGER = Logger.getLogger("co.edu.uniandes.csw.artwork.test.persistence.PaymentPersistenceTest");

        private ClientEntity clientEntity;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaymentEntity.class.getPackage())
                .addPackage(PaymentPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */

    /**
     * @generated
     */
    

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
             LOGGER.log(Level.SEVERE, e.getMessage(), e);
            try {
                utx.rollback();
            } catch (Exception e1) {
                LOGGER.log(Level.SEVERE, e1.getMessage(), e1);
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from PaymentEntity").executeUpdate();
    }

    /**
     * @generated
     */
 

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        clientEntity = factory.manufacturePojo(ClientEntity.class);
        clientEntity.setId(1L);
        em.persist(clientEntity);
        
        for (int i = 0; i < 3; i++) {
            PaymentEntity entity = factory.manufacturePojo(PaymentEntity.class);
            entity.setClient(clientEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Payment.
     *
     * @generated
     */
    @Test
    public void createPaymentTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PaymentEntity newEntity = factory.manufacturePojo(PaymentEntity.class);
        PaymentEntity result = paymentPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PaymentEntity entity = em.find(PaymentEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Payments.
     *
     * @generated
     */
    @Test
    public void getPaymentsTest() {
        List<PaymentEntity> list = paymentPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PaymentEntity ent : list) {
            boolean found = false;
            for (PaymentEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Payment.
     *
     * @generated
     */
    @Test
    public void getPaymentTest() {
        PaymentEntity entity = data.get(0);
        PaymentEntity newEntity = paymentPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    @Test
    public void getPaymentByClientIdTest(){
    PaymentEntity entity = data.get(0);
        PaymentEntity newEntity = paymentPersistence.find(entity.getClient().getId(),entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    /**
     * Prueba para eliminar un Payment.
     *
     * @generated
     */
    @Test
    public void deletePaymentTest() {
        PaymentEntity entity = data.get(0);
        paymentPersistence.delete(entity.getId());
        PaymentEntity deleted = em.find(PaymentEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Payment.
     *
     * @generated
     */
    @Test
    public void updatePaymentTest() {
        PaymentEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PaymentEntity newEntity = factory.manufacturePojo(PaymentEntity.class);

        newEntity.setId(entity.getId());

        paymentPersistence.update(newEntity);

        PaymentEntity resp = em.find(PaymentEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
