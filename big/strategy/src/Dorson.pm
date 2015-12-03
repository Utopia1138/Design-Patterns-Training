package Dorson;

no autovivification;

use strict;
use warnings;
use bytes;

sub new {
  my ( $class_name ) = @_;

  my $self = bless {}, $class_name;

  return $self;
}

sub set_input_parser {
  my ($self, $ref) = @_;
  $self->{input_parser} = $ref;
}


sub set_authorizer {
  my ($self, $ref) = @_;
  $self->{authorizer} = $ref;
}

sub set_response_generator {
  my ($self, $ref) = @_;
  $self->{response_generator} = $ref;
}

sub process {
  my ($self, $request) = @_;
  
  my $transaction = $self->{input_parser}->parse($request);

  $self->{authorizer}->authorize($transaction);
  
  my $response = $self->{response_generator}->generate($transaction);

  return $response;
}

1;
