package Payment::BankTerminal;

use strict;
use warnings;

use Moose::Role;

with 'Payment::Loggable';

has 'merchant' => (
  is => 'ro',
  isa => 'Payment::Merchant',
);

has 'message_strategy' => (
  is => 'ro',
  does => 'Payment::Message::Strategy',
);

has 'comms' => (
  is => 'rw',
  does => 'Payment::Comms::CommunicationStrategy',
);

sub authorisation {
  my ( $self, $payment ) = @_;

  my $message = $self->{message_strategy}->construct_message( $self, $self->{payment} );
  my $response = $self->{comms}->send_request( $message );
  $self->info( "Raw response received: $response" );
  return $self->{message_strategy}->parse_message( $response );
}

1;
