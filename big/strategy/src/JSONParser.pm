package JSONParser;

no autovivification;

use strict;
use warnings;
use bytes;

use JSON;

use lib 'src';
require Transaction;

sub new {
  my ( $class_name ) = @_;

  my $self = bless {}, $class_name;

  return $self;
}

sub parse {
  my ($self, $raw_input) = @_;

  my $decoded_input = JSON->new->decode($raw_input);

  my $txn = Transaction->new();
  $txn->set_amount ($decoded_input->{amount});
  $txn->set_currency ($decoded_input->{currency});

  return $txn;
}

1;
