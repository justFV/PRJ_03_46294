 package pt.ual.views;

 import javax.ws.rs.container.ContainerRequestContext;
 import javax.ws.rs.container.ContainerResponseContext;
 import javax.ws.rs.container.ContainerResponseFilter;
 import javax.ws.rs.ext.Provider;

 @Provider
 public class CORSFilter
   implements ContainerResponseFilter {
   public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
     responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
     responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
     responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
     responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
     responseContext.getHeaders().add("Access-Control-Expose-Headers", "Authorization");
   }
 }