package com.mongodb;

public class HelloWorldSparkStyle {

	public static void main(String[] args){
		spark.Spark.get("/hello", (req, res) -> "Hello World");
	}
}
