package t::Payment::Terminal::authorisation;
use Test::Mock::Class ':all';
use Test::More;
use MooseX::Test::Role;

use Payment::Merchant;
use Payment::BankTerminal::FooBankTerminal;
use Payment::Transaction::CardData;
use Payment::Transaction;


my @requests;
my @responses;

my $merchant = Payment::Merchant->new();

my $comms = consumer_of('Payment::Comms::CommunicationStrategy',
  send_request => sub {
    my $self = shift;
    $self->info( "Sending request: " . $_[0] );
    push @requests, $_[1];
    return 'fake response';
  }
);


my $message_strategy = consumer_of('Payment::Message::Strategy',
  construct_message => sub {
    my $self = shift;
    $self->info( "Constructing message" );
    return 'fake message';
  },
  parse_message => sub {
    my $self = shift;
    $self->info( "Parsing message: $_[0]" );
    push @responses, $_[1];
    return {
      response => '00',
      message => 'Auth code: 123456',
    };
  }
);


my $terminal = Payment::BankTerminal::FooBankTerminal->new(
  merchant => $merchant,
  message_strategy => $message_strategy,
  comms => $comms,
);

my $transaction = Payment::Transaction->new(
  transaction_type => 'purchase',
  amount => '100.00',
  currency => 'GBP',
  card_data => Payment::Transaction::CardData->new(
    PAN => '4444333322221111',
    expiry => '01/38',
  ),
);

my $response = $terminal->authorisation( $transaction );
ok( defined($response), 'Response received' );
is( $response->{response}, '00', 'Correct response code' );
is( $response->{message}, 'Auth code: 123456', 'Correct message' );

done_testing();

1;
