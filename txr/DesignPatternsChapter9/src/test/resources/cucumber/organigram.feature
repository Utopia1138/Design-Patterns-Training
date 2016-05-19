Feature: Or
As a user, i want to be able to list employees hierarchy for a company

Scenario: Organigram of company
Given the Company "Foo"
Given the Ceo"Big Boss";
Given the Company has the Ceo as its ceo
Given that the ceo has managers Paul, Julia and Spencer
Given the employees Peter, Ivan,Nicolas,Brian,Kevin
Given the manager Randalph
Given that Paul has 4 employees Peter, Ivan, Randalph, Nicolas
Given that Randalph has suboordinate employee Brian
Given that Julia has suboordinate Kevin
When I retrieve the organigram of the company
Then the organigram has name of the first employe is "Big Boss"
Then the organigram shows Paul with 4 suboordinates
Then the organigram shows Paul has Randalph as subordinate
Then the organigram shows Randalph has Brian as subordinate
Then the organigram shows Julia has Kevin as subordinate
Then the organigram shows Kevin without subordinate

