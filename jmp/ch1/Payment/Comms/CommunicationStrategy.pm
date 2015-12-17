package Payment::Comms::CommunicationStrategy;
############################################
# The role that controls how to communicate with
# the acquirer.
##############################################
use Moose::Role;

with 'Payment::Loggable';

# It's up to the implementor to know how to send the request
requires 'send_request';

1;
