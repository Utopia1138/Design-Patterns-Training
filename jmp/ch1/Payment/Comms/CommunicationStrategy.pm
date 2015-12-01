package Payment::Comms::CommunicationStrategy;

use Moose::Role;

with 'Payment::Loggable';

requires 'send_request';

1;
