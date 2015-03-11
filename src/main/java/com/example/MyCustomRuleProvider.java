package com.example;

import org.jboss.windup.config.WindupRuleProvider;
import org.jboss.windup.graph.GraphContext;
import org.jboss.windup.reporting.config.Hint;
import org.jboss.windup.reporting.config.Link;
import org.jboss.windup.reporting.config.classification.Classification;
import org.jboss.windup.rules.apps.java.condition.JavaClass;
import org.jboss.windup.rules.apps.java.scan.ast.TypeReferenceLocation;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;

public class MyCustomRuleProvider extends WindupRuleProvider {

	public Configuration getConfiguration(GraphContext arg0) {
		return ConfigurationBuilder.begin()
		        .addRule()
		        .when(
		            JavaClass.references("weblogic.ejb.GenericMessageDrivenBean").at(TypeReferenceLocation.INHERITANCE)
		        )
		        .perform(
		            Hint.withText("WebLogic Server Generic Bean Template Inheritance")
		               .with(Link.to("Message Driven Bean", "https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/6.2/html/Development_Guide/Create_a_JMS-based_Message-Driven_Bean_in_JBoss_Developer_Studio.html"))
		               .withEffort(5)
		               .and(Classification.as("Remove the WebLogic generic Message Driven Bean template inheritance."))
		        )
		        .addRule()
		        .when(
		        	JavaClass.references("weblogic.ejb.GenericMessageDrivenBean").at(TypeReferenceLocation.IMPORT)
		        )
		        .perform(
		        		 Hint.withText("WebLogic Server Generic Bean Template Import")
			               .with(Link.to("Message Driven Bean", "https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/6.2/html/Development_Guide/Create_a_JMS-based_Message-Driven_Bean_in_JBoss_Developer_Studio.html"))
			               .withEffort(2)
			               .and(Classification.as("Remove the WebLogic generic Message Driven Bean template import.")));
		        
	}
	
}
