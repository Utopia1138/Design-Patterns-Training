#!perl

use strict;
use warnings;

use TAP::Harness;
use File::Find;
use Data::Dumper;

my @tests = ();

find(
  sub {
    print "finding: $File::Find::name\n";
    # skip directories
    return if -d;
    push(@tests, $File::Find::name) if( /\.(t|pm)$/ );
  },
  qw( t )
);

print "Test files found:\n" . Dumper( \@tests );

my $harness = TAP::Harness->new( {
 verbosity => 1,
 color => 1,
 show_count => 1,
} );
my $aggregator = $harness->runtests( @tests );
$harness->summary( $aggregator );
