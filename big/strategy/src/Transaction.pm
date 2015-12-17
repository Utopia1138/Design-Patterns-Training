package Transaction;

no autovivification;

use strict;
use warnings;
use bytes;

sub new {
  my ( $class_name ) = @_;

  my $self = bless {}, $class_name;

  return $self;
}

sub set_authcode {
  my ($self, $scalar) = @_;
  $self->{authcode} = $scalar;
}

sub set_auth_timestamp {
  my ($self, $scalar) = @_;
  $self->{auth_timestamp} = $scalar;
}

sub set_amount {
  my ($self, $scalar) = @_;
  $self->{amount} = $scalar;
}

sub set_currency {
  my ($self, $scalar) = @_;
  $self->{currency} = $scalar;
}

sub get_authcode {
  my ($self) = @_;
  return $self->{authcode};
}

sub get_auth_timestamp {
  my ($self) = @_;
  return $self->{auth_timestamp};
}

sub get_amount {
  my ($self) = @_;
  return $self->{amount};
}

sub get_currency {
  my ($self) = @_;
  return $self->{currency};
}

1;
