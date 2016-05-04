Feature: MPFinder
As a user, i want to be able to find MP by their name via different sources (PublicWip, TheyWorkForyou)

Scenario: Find a MP by name with PublicWip
Given the PublicWip MPFinder
Given the resource "C:\\dev\\Design-Patterns-Training\\txr\\DesignPatternsChapter8\\src\\main\\resources\\publicwip.htm"
When I retrieve a MP with name "Diane Abbott"
Then the MP exists
And her name is "Diane Abbott"
And her constituency is "Hackney North and Stoke Newington"
And here party is "Labour"


Scenario: Find a MP by name with TheyWorkForYou
Given the TheyWorkForYou MPFinder
Given the resource "C:\\dev\\Design-Patterns-Training\\txr\\DesignPatternsChapter8\\src\\main\\resources\\twfymps.json"
When I retrieve a MP with name "Diane Abbott"
Then the MP exists
And her name is "Diane Abbott"
And her constituency is "Hackney North and Stoke Newington"
And here party is "Labour"
