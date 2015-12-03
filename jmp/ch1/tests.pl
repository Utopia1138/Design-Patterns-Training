#!perl
###########################
# Simple test harness. Looks for all *.t and *.pm
# files under t/ and adds them to the TAP::Harness
############################
use strict;
use warnings;

use TAP::Harness;
use File::Find;
use Data::Dumper;

use Log::Log4perl qw(:easy);
Log::Log4perl->easy_init( {
  level => $DEBUG,
} );
my $logger = get_logger();

my @tests = ();

find(
  sub {
    $logger->debug("finding: $File::Find::name");
    # skip directories
    return if -d;
    push(@tests, $File::Find::name) if( /\.(t|pm)$/ );
  },
  qw( t )
);

$logger->info("Test files found:\n" . Dumper( \@tests ) );

my $harness = TAP::Harness->new( {
 verbosity => 1,
 color => 1,
 show_count => 1,
 jobs => 1,
 merge => 1,
} );
my $aggregator = $harness->runtests( @tests );
$harness->summary( $aggregator );
