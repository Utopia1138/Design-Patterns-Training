#!/usr/bin/perl
# Try me with ./test.pl '{"amount":"ONE HUNDRED MILLION","currency":"DOLLARS"}'
use strict;
use warnings;
use bytes;

no autovivification;

use lib 'src';
require Dorson;
require JSONParser;
require JSONResponder;
require DummyBank;

my $request = shift;

die "I need some input" unless $request;

# Oh no! If only there was a pattern to do with instantiating things!
my $gateway = Dorson->new();
$gateway->set_input_parser( JSONParser->new() );
$gateway->set_authorizer( DummyBank->new() );
$gateway->set_response_generator( JSONResponder->new() );

my $response = $gateway->process( $request );

print "$response\n";

exit 0;
