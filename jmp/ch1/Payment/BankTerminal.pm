package Payment::BankTerminal;
##################################################
# A role for bank terminals. Merchants would use
# such a terminal to send messages to their acquirer.
#
# A terminal has two different strategies: the first
# tells them how to construct the message for the
# acquirer (e.g., APACS30 or ISO8583) and the other
# tells them how to send the message to the bank (HTTPS,
# TCP, x25, etc.).
##################################################
use strict;
use warnings;

use Moose::Role;

with 'Payment::Loggable';

# The merchant associated with this terminal
has 'merchant' => (
  is => 'ro',
  isa => 'Payment::Merchant',
);

# Controls how the terminal constructs messages
# for the acquirer and understands the response.
has 'message_strategy' => (
  is => 'ro',
  does => 'Payment::Message::Strategy',
);

# Controls how the message is sent to the acquirer.
has 'comms' => (
  is => 'rw',
  does => 'Payment::Comms::CommunicationStrategy',
);

# Perform an authorisation
sub authorisation {
  my ( $self, $payment ) = @_;

  my $message = $self->{message_strategy}->construct_message( $self, $self->{payment} );
  my $response = $self->{comms}->send_request( $message );
  $self->info( "Raw response received: $response" );
  return $self->{message_strategy}->parse_message( $response );
}

1;
