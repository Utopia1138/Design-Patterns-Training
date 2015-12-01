#!perl

use strict;
use warnings;
use Payment::Merchant;
use Payment::BankTerminal::FooBankTerminal;
use Payment::Transaction;
use Payment::Transaction::CardData;
use Data::Dumper;

use Log::Log4perl qw(:easy);
Log::Log4perl->easy_init( {
  level => $DEBUG,
} );
my $logger = get_logger();

my $merchant = Payment::Merchant->new(
  name => 'Test Testerson',
);

my $terminal = Payment::BankTerminal::FooBankTerminal->new(
  merchant => $merchant,
);

my $response = $terminal->authorisation(
  Payment::Transaction->new(
    transaction_type => 'purchase',
    amount => '25.00',
    currency => 'GBP',
    card_data => Payment::Transaction::CardData->new(
      PAN => '4444333322221111',
      expiry => '01/38',
    ),
  )
);
$logger->info("Received response: " . Dumper( $response ) );
