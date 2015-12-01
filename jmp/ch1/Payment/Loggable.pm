package Payment::Loggable;
#######################################
# A simple role that allows easy integration
# of Log4Perl into any Moose object.
#######################################
use strict;
use warnings;

use Moose::Role;
use Log::Log4perl qw( :easy );

my @methods = qw(
    log trace debug info warn error fatal
    is_trace is_debug is_info is_warn is_error is_fatal
    logexit logwarn error_warn logdie error_die
    logcarp logcluck logcroak logconfess
);

has _logger => (
    is => 'ro',
    isa => 'Log::Log4perl::Logger',
    lazy_build => 1,
    handles => \@methods,
);

around $_ => sub {
    my $orig = shift;
    my $this = shift;

    # one level for this method itself
    # two levels for Class:;MOP::Method::Wrapped (the "around" wrapper)
    # one level for Moose::Meta::Method::Delegation (the "handles" wrapper)
    local $Log::Log4perl::caller_depth;
    $Log::Log4perl::caller_depth += 4;

    my $return = $this->$orig(@_);

    $Log::Log4perl::caller_depth -= 4;
    return $return;

} foreach @methods;

sub _build__logger {
    my $this = shift;

    my $loggerName = ref($this);
    Log::Log4perl->easy_init( {
      level =>  $DEBUG,
      layout => '[%l] %m%n',
    }) if not Log::Log4perl::initialized();
    return Log::Log4perl->get_logger($loggerName)
};

1;
