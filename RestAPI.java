package com.mongodb;

import static spark.Spark.*;
 
import spark.*;
 
 
public class RestAPI {
      
       public static void main(String[] args) {
             
              // curl -i -X POST -H Content-Type='application/json' -d Message=Type=ExecutionReport#BeginString=FIX.4.4#TargetCompID=BLP#SourceCompID=WAGM#ClOrdID=1 http://ps-w7schuthari:4567/fix/event/insert
              
    	   get("/hello", (req, res) -> ""  +
                   "<DOCTYPE html>" +
                   "<html>" +
                   "  <head>" +
                   "  </head>" +
                   "  <body>" +
                   "    <h1>Hello " + ", Spark here.  Howrya?</h1>" +
                   " <form action=\"/post\" method=\"post\" target=\"_blank\"> " +
                   " First name: <input type=\"text\" name=\"fname\"><br> " +
                   " Last name: <input type=\"text\" name=\"lname\"><br> " +
                   " <input type=\"submit\" value=\"Submit\"> " +
                   "   </form> " +
                   " <p>Click on the submit button, and the input will be sent to a page on the server called \"demo_form_method_post.asp\".</p> " +
                   "  </body>" +
                   "</html>");


              

			post("/post", (request, response) -> {
				 System.out.print("Reqest is"  + request);
			     return response;
			});
	}
 
}