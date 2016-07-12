package com.onlineOrder;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.math.BigDecimal;
import java.util.Collection;

public class determinePickupDate implements JavaDelegate
{
    public static void main(String args[])
    {
        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        // go !

        Collection<? extends Object> sessionObjects = kSession.getObjects();
        //mit objects den Input machen
        kSession.insert(new Input());
        System.out.println(Integer.toString(sessionObjects.size()));
        System.out.println(sessionObjects.iterator().next().getClass().getSimpleName());

        kSession.fireAllRules();

        //werte rausziehen und in 'execution.setvar...' alle Werte setzen... => Zugriff auf die Werte später.
    }

    public static class Input
    {
        private BigDecimal orderSize = new BigDecimal(10);
        private String customerType = "Gold";
        private BigDecimal amount = new BigDecimal(501);
        private String location = "EU";
        private boolean discountCode = true;

        public BigDecimal getOrderSize()
        {
            return orderSize;
        }

        public void setOrderSize(BigDecimal orderSize)
        {
            this.orderSize = orderSize;
        }

        public String getCustomerType()
        {
            return customerType;
        }

        public void setCustomerType(String customerType)
        {
            this.customerType = customerType;
        }

        public BigDecimal getAmount()
        {
            return amount;
        }

        public void setAmount(BigDecimal amount)
        {
            this.amount = amount;
        }

        public String getLocation()
        {
            return location;
        }

        public void setLocation(String location)
        {
            this.location = location;
        }

        public boolean getDiscountCode()
        {
            return discountCode;
        }

        public void setDiscountCode(boolean discountCode)
        {
            this.discountCode = discountCode;
        }
    }

    public void execute(DelegateExecution execution) throws Exception
    {
        /*try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            kSession.insert(message);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }*/
    }
}


