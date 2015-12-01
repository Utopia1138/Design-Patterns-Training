package Payment::Message::Strategy;
########################################
# The role for message strategies. These roles
# are responsible for determining how messages
# have to be formatted for the acquirer and how
# to understand the response.
#
# In the real world, you may have message strategies
# for things like APACS30 or ISO8583.
use strict;
use warnings;

use Moose::Role;

with 'Payment::Loggable';

# Implementors must know how to build the message
requires 'construct_message';

# ...and understand the response
requires 'parse_message';

1;
