package JSONResponder;

no autovivification;

use strict;
use warnings;
use bytes;

use JSON;

sub new {
  my ( $class_name ) = @_;

  my $self = bless {}, $class_name;

  return $self;
}

sub generate {
  my ($self, $transaction) = @_;

  my $raw_response = {currency => $transaction->get_currency(), amount => $transaction->get_amount()};

  if ($transaction->get_authcode()){
    $raw_response->{authorization_details} = {authcode=>$transaction->get_authcode(), timestamp => $transaction->get_auth_timestamp()};
  }

  my $json_response = JSON->new->encode($raw_response);

  return $json_response;
}

1;
