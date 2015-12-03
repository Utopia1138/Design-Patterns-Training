package Payment::Message::Strategy::JSONStrategy;
###########################################
# A simple JSON message strategy.
###############################################
use strict;
use warnings;
use Moose;
use Moose::Util::TypeConstraints;
use JSON;

with 'Payment::Message::Strategy';
with 'Payment::Loggable';

has 'transformer' => (
  is => 'ro',
  isa => duck_type( [qw(encode decode)]),
  default => sub {
    JSON->new->utf8->pretty;
  }
);

sub construct_message {
  my ( $self, $terminal, $transaction ) = @_;

  # build up the message and encode it
  return $self->{transformer}->encode(
    {
      transaction_type => $transaction->{transaction_type},
      card_details => {
        PAN => $transaction->{card_data}->{PAN},
        expiry => $transaction->{card_data}->{expiry},
      },
      amount => $transaction->{amount},
      currency => $transaction->{currency},
      # might as well include information from the terminal
      # just because we can. Otherwise how will the bank know
      # who sent the request?
      merchant => $terminal->{merchant}->{name},
    }
  );
}

sub parse_message {
  my ( $self, $message ) = @_;
  return $self->{transformer}->decode( $message );
}

no Moose;
__PACKAGE__->meta->make_immutable;
