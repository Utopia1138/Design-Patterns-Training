package Payment::Comms::SimulatedCommunicationStrategy;
###########################################
# A kind of simulator that expects a JSON message
# and returns a JSON response
############################################
use strict;
use warnings;

use Moose;
use JSON;
use Moose::Util::TypeConstraints;
use Data::Dumper;

with 'Payment::Comms::CommunicationStrategy';
with 'Payment::Loggable';

# Use this to parse the incoming message and encode
# the response.
# If I'd put more time into the Payment::Message::Strategy
# role, this simulator could probably just use that to
# parse the incoming request and encode the response. This
# shows how useful the strategy pattern could be because
# the message strategy used on the client side could also be used
# by the server.
has 'json' => (
  is => 'ro',
  isa => duck_type( [qw(encode decode)]),
  default => sub {
    JSON->new->utf8->pretty;
  }
);

# Pretend to send the message, but just return a hard-coded response
sub send_request {
  my ( $self, $message ) = @_;
  $self->info( "Sending message: $message" );
  my $parsed = $self->{json}->decode( $message );

  my $response = {
    transaction_type => 'response',
    status => '00',
    message => 'Approved',
    merchant => $parsed->{merchant},
  };

  $self->info( "Returning data: " . Dumper( $response ) );

  return $self->{json}->encode( $response );
}

no Moose;
__PACKAGE__->meta->make_immutable;
