Feature: QA Challenge 

Background: User is Logged In 
	Given User navigate to the Login page 
	When User enters id as "fake23994@gmail.com" on LoginPage 
	And User enters password as "password" on LoginPage 
	Then User clicks on Login button on LoginPage 
	Then User should be able to verify Home page 
	
@s1 @all
Scenario Outline: LIKE AND UNLIKE FANPAGE 
	When User enters fanpage name as "<fanpage>" in Search Facebook on HomePage 
	Then User selects the fanpage "<fanpage>" on HomePage 
	Then User likes the fanpage from FanPage 
	And User navigates to Profile Page 
	And User clicks on Activity Log on Profile Page 
	Then User should be able to verify ActivityLog Page 
	And User verify liked "<fanpage>" on ActivityLog Page 
	And User navigates to Profile Page 
	And User navigates to "Likes" on Profile Page 
	Then User verify liked "<fanpage>" under Likes on Profile Page 
	And User unlikes the "<fanpage>" under Likes on Profile Page 
	And User clicks on "<fanpage>" under Likes on Profile Page 
	Then User should verify if fanpage is successfully unliked 
	
	Examples: 
		|fanpage|
		|Cristiano Ronaldo|
		
		
		@s2 @all
		Scenario Outline: CREATE A POST 
			When User clicks on create post on HomePage 
			And User enters facebook post as "<post>" on HomePage 
			And User clicks on Post button on HomePage 
			Then User verify if "<post>" was added successfuly on HomePage 
			And User navigates to Profile Page 
			Then User verify if "<post>" was added successfuly on Profile Page 
			And User remove post on Profile Page 
			Then User should verify "<post>" is not displayed on Profile Page 
			
			Examples: 
				|post|
				|https://share.getcloudapp.com/L1ugPr86|	