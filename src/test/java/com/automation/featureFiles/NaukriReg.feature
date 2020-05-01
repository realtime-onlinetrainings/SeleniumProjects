@testNaukri 
Feature: Register Naukri user 

@CreatePolicyName 
Scenario Outline: verify gmail accounts 
	Given I am at the naukri Login screen 
	And I click on I am a professional 
	And I enter Name as <Name> 
	And I enter EmailId as <email> 
	And I enter Password as <Password> 
	
	Examples: 
		| Name        |email                              | Password  |
		| chandrahasm |rama.mullamgdfdsffdfdsdf@gmail.com |Pass@1389  |
		
		