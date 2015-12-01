package Payment::Message::Strategy;

use strict;
use warnings;

use Moose::Role;

with 'Payment::Loggable';

requires 'construct_message';

requires 'parse_message';

1;
