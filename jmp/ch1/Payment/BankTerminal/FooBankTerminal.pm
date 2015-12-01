package Payment::BankTerminal::FooBankTerminal;

use Moose;
use Data::Dumper;

with 'Payment::BankTerminal';

before 'authorisation' => sub {
  my $self = shift;
  $self->info( "Authorising payment for amount $_[0]->{amount}" );
};


no Moose;
__PACKAGE__->meta->make_immutable;
