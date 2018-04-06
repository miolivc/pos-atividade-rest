
package br.edu.ifpb.configuracao;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class AppLogger {
    
    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        System.out.println("Metodo: " + context.getMethod().getName());
        return context.proceed();
    }
    
}
