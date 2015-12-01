package Payment::BankTerminal::FooBankTerminal;

use Moose;
use Data::Dumper;
use Payment::Message::Strategy::JSONStrategy;
use Payment::Comms::SimulatedCommunicationStrategy;

with 'Payment::BankTerminal';

before 'authorisation' => sub {
  my $self = shift;
  $self->info( "Authorising payment for amount $_[0]->{amount}" );
};

has '+message_strategy' => (
  default => sub {
    Payment::Message::Strategy::JSONStrategy->new();
  }
);

has '+comms' => (
  default => sub {
    Payment::Comms::SimulatedCommunicationStrategy->new();
  }
);

no Moose;
__PACKAGE__->meta->make_immutable;
