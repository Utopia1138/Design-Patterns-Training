package Payment::Transaction;

use strict;
use warnings;
use Moose;
use Moose::Util::TypeConstraints;

has 'transaction_type' => (
  is => 'rw',
  isa => enum([qw[ purchase refund ]]),
);

has 'amount' => (
  is => 'rw',
  isa => 'Num',
);

has 'currency' => (
  is => 'rw',
  isa => 'Str',
);

has 'card_data' => (
  is => 'rw',
  isa => 'Payment::Transaction::CardData',
);

no Moose;
__PACKAGE__->meta->make_immutable;
