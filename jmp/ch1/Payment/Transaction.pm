package Payment::Transaction;
##########################################
# A representation of an authorisation message
# that would be sent to an acquirer.
############################################
use strict;
use warnings;
use Moose;
use Moose::Util::TypeConstraints;

# What operation is being performed by this transaction?
# Currently, we only support purchases and refunds.
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
