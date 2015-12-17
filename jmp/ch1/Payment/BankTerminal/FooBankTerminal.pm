package Payment::BankTerminal::FooBankTerminal;
#################################################
# An example implementation of BankTerminal.
# FooBank expects their merchants to send JSON messages.
#################################################
use Moose;
use Data::Dumper;
use Payment::Message::Strategy::JSONStrategy;
use Payment::Comms::SimulatedCommunicationStrategy;

with 'Payment::BankTerminal';

before 'authorisation' => sub {
  my $self = shift;
  $self->info( "Authorising payment for amount $_[0]->{amount}" );
};

# This terminal uses JSONStrategy to construct messages and
# parse responses.
has '+message_strategy' => (
  default => sub {
    Payment::Message::Strategy::JSONStrategy->new();
  }
);

# We'll talk to a simple simulation of a comms module
has '+comms' => (
  default => sub {
    Payment::Comms::SimulatedCommunicationStrategy->new();
  }
);

no Moose;
__PACKAGE__->meta->make_immutable;
