Feature:ViewingBehaviour
As a viewer, i can have a viewing behaviour.
This behaviour conditions how i evaluate the programs I am viewing

Scenario:
Given a TV 
Given a viewer 
Given a horrorFan Behaviour 
Given a horror program
When the viewer has the horror fan behaviour
And watches a horror program on the TV
Then the viewer current interest will be 5 
