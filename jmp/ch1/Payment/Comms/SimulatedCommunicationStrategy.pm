package Payment::Comms::SimulatedCommunicationStrategy;
use strict;
use warnings;

use Moose;
use JSON;
use Moose::Util::TypeConstraints;
use Data::Dumper;

with 'Payment::Comms::CommunicationStrategy';
with 'Payment::Loggable';

has 'json' => (
  is => 'ro',
  isa => duck_type( [qw(encode decode)]),
  default => sub {
    JSON->new->utf8->pretty;
  }
);

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
