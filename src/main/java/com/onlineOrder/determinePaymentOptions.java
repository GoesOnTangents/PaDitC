package com.onlineOrder;

        import org.camunda.bpm.engine.delegate.DelegateExecution;
        import org.camunda.bpm.engine.delegate.Expression;
        import org.camunda.bpm.engine.delegate.JavaDelegate;
        import org.camunda.bpm.engine.delegate.VariableScope;
        import org.camunda.bpm.engine.impl.bpmn.behavior.TaskActivityBehavior;
        import org.kie.api.KieServices;
        import org.kie.api.runtime.KieContainer;
        import org.kie.api.runtime.KieSession;
        import org.kie.api.runtime.process.ProcessInstance;
        import org.kie.api.runtime.rule.FactHandle;
        import org.kie.internal.SystemEventListener;
        import java.math.BigDecimal;
        import java.util.*;


public class determinePaymentOptions implements JavaDelegate
{

    public void execute(DelegateExecution execution) throws Exception
    {
    }

    public static void main(String args[])
    {
        Input input = new Input();

        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        kSession.insert(input);
        kSession.fireAllRules();

        Collection<? extends FactHandle> facts = kSession.getFactHandles();
        Collection<? extends Object> objects = kSession.getObjects();

        for (Object o : objects)
        {
            System.out.println("object:" + o);
        }

        System.out.println("Size of facts after fireAllRules:" + Integer.toString(facts.size()));
    }

    public static class Input
    {
        private String customerType = "Gold";
        private BigDecimal amount = new BigDecimal(10);
        private String location = "EU";
        private Boolean discountCode = true;

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

        public Boolean getDiscountCode()
        {
            return discountCode;
        }

        public void setDiscountCode(Boolean discountCode)
        {
            this.discountCode = discountCode;
        }

    }

    /*Map<String,Object> variables = execution.getVariables();
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            switch (entry.getKey()) {
                case "customerType":
                    input.setCustomerType((String) entry.getValue());
                    break;
                case "amount":
                    input.setAmount(new BigDecimal(entry.getValue().toString()));
                    break;
                case "location":
                    input.setLocation((String) entry.getValue());
                    break;
                case "discountCode":
                    input.setDiscountCode((Boolean) entry.getValue());
                    break;
            }
        }*/


        /*if (variablesInputExpressions != null) {
            Iterator<Expression> itVariable = variablesInputExpressions.iterator();
            while (itVariable.hasNext()) {
                Expression variable = itVariable.next();
                System.out.println("VAR: " + variable.toString());
                kSession.insert(variable.getValue(execution));
            }
        }*/

    // go !
        /*Discount_Output discount = new Discount_Output();
        PaymentOptions_Output paymentOptions = new PaymentOptions_Output();
        PaymentOptions_Rule_Output paymentOptionsRule = new PaymentOptions_Rule_Output();*/

    //Collection<? extends Object> inputObjects = kSession.getObjects();
    //mit objects den Input machen
    /*for (FactHandle f : facts)
        {
            Object o = kSession.getObject(f);
            switch (o.getClass().getSimpleName()) {
                case "Discount_Output":
                    break;
                case "PaymentOptions_Rule_Output":
                    System.out.println("Payment: " + o.getClass().getMethod("getPaymentOptions").invoke(o));
                    break;
            }
        }*/

    //((PaymentOptions_Rule_Output) null).getPaymentOptions();

        /*System.out.println("VARIABLES:");
        System.out.println("Discount: " + discount.getDiscount().toString());
        System.out.println("Payment: " + paymentOptionsRule.getPaymentOptions());
        System.out.println("Payments Size: " + Integer.toString(paymentOptions.getPaymentOptions().size()));*/

    //System.out.println("AFTER:");
    //System.out.println(Integer.toString(outputObjects.size()));
    //for (Object o : outputObjects) {
    //    System.out.println(o.getClass().getSimpleName() + " -> " + o.toString());
    //}

        /*if (outputObjects != null && outputObjects.size() > 0) {
            Collection<Object> outputVariables = new ArrayList<Object>();
            for (Object object : outputObjects) {
                outputVariables.add(object);
            }
            System.out.println("RESULT: " + resultVariable);
            execution.setVariable(resultVariable, outputVariables);
        } else
            System.out.println("NOPE.");*/
    //kSession.dispose();
    //leave(execution);

    //werte rausziehen und in 'execution.setvar...' alle Werte setzen... => Zugriff auf die Werte später.

    /*kSession.insert(discount);
        kSession.insert(paymentOptions);
        kSession.insert(paymentOptionsRule);*/
        /*System.out.println("BEFORE:");
        System.out.println(Integer.toString(inputObjects.size()));
        System.out.println(inputObjects.iterator().next().getClass().getSimpleName());*/
    /*public void setResultVariable(String resultVariableName) {
        this.resultVariable = resultVariableName;
    }*/
/*
    public static class PaymentOptions_Rule_Output {
        private String paymentOptions = "";
        public String getPaymentOptions() { return paymentOptions; }
        public void setPaymentOptions(String p) { System.out.println("FUCKING SET IT!!"); paymentOptions = p; }
    }


    public static class PaymentOptions_Output {
        private List<String> paymentOptions = new ArrayList<String>();
        public List<String> getPaymentOptions() { return paymentOptions; }
        public void setPaymentOptions(List<String> p) { paymentOptions = p; }

    }

    public static class Discount_Output {
        private BigDecimal discount = new BigDecimal("7");
        public BigDecimal getDiscount() { return discount; }
        public void setDiscount(BigDecimal d) { discount = d; }
    }*/

}
