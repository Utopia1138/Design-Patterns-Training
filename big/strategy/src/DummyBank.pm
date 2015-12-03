package DummyBank;

no autovivification;

use strict;
use warnings;
use bytes;

sub new {
  my ( $class_name ) = @_;

  my $self = bless {}, $class_name;

  return $self;
}

sub authorize {
  my ($self, $transaction) = @_;

  # Authorize whatever. @jmp's got it covered.
  $transaction->set_authcode(100000);
  $transaction->set_auth_timestamp(time());

  return $transaction;
}

1;
