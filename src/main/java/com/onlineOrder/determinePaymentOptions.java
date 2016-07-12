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
    //THIS STUFF IS NOT CALLED ANYWAYS...
    /*protected Set<Expression> variablesInputExpressions = new HashSet<Expression>();
    protected String resultVariable;

    public void addRuleVariableInputIdExpression(Expression inputId) {
        this.variablesInputExpressions.add(inputId);
    }*/

    public void execute(DelegateExecution execution) throws Exception
    {
        Input input = new Input();

        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");


        Map<String,Object> variables = execution.getVariables();
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
                case "discountCode":
                    input.setDiscountCode(Boolean.parseBoolean((String) entry.getValue()));
            }
        }
        /*if (variablesInputExpressions != null) {
            Iterator<Expression> itVariable = variablesInputExpressions.iterator();
            while (itVariable.hasNext()) {
                Expression variable = itVariable.next();
                System.out.println("VAR: " + variable.toString());
                kSession.insert(variable.getValue(execution));
            }
        }*/

        // go !

        //Collection<? extends Object> inputObjects = kSession.getObjects();
        //mit objects den Input machen
        kSession.insert(input);
        /*System.out.println("BEFORE:");
        System.out.println(Integer.toString(inputObjects.size()));
        System.out.println(inputObjects.iterator().next().getClass().getSimpleName());*/

        kSession.fireAllRules();

        //System.out.println("PROCESS: " + Integer.toString(kSession.getProcessInstances().size()));

        Collection<? extends Object> outputObjects = kSession.getObjects();
        Collection<? extends FactHandle> facts = kSession.getFactHandles();

        System.out.println("hi." + Integer.toString(facts.size()));
        for (FactHandle f : facts)
            System.out.println(f.toExternalForm() + " OR " + f.toString());


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
    }

    /*public void setResultVariable(String resultVariableName) {
        this.resultVariable = resultVariableName;
    }*/

    public class Input
    {
        private String customerType;
        private BigDecimal amount;
        private String location;
        private Boolean discountCode;

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

        //asssd
    }

}
