package t::Payment::BankTerminal::FooBankTerminal::construction;
use strict;
use warnings;
use Test::More;
use Payment::Merchant;
use Payment::BankTerminal::FooBankTerminal;
use Data::Dumper;

my $terminal = Payment::BankTerminal::FooBankTerminal->new(
  merchant => Payment::Merchant->new(),
);

ok( $terminal, "Terminal created" );
is( $terminal->{comms}->meta->name, 'Payment::Comms::SimulatedCommunicationStrategy', "Correct comms module" );
is( $terminal->{message_strategy}->meta->name, 'Payment::Message::Strategy::JSONStrategy', "Correct message strategy" );

done_testing();
1;
