package Pattern::Observer;

use strict;
use warnings;

use Exporter qw( import );

our @EXPORT = qw();
our @EXPORT_OK = qw( get_observer_methods );

sub get_observer_methods {
  my %observers = {};

  my $subscribe_ref = sub {
          my ( $observer ) = @_;
          if ( my $notify_ref = $observer->can( 'notify' ) ) {
            $observers{$observer} = { 'observer' => $observer, 'callback' => $notify_ref );
          }
          else {
            warn "This object [$observer] does not have a notify sub";
          }
  };

  my $unsubscribe_ref = sub {
          my ( $observer ) = @_;
          if ( $observers{$observer} ) {
            delete $observers{$observer};
          }
          else {
            warn "This object [$observer] is not subcribed to this subject";
          }
  };

  my $notify_ref = sub {
          my ( $data_burst ) = @_;
          foreach my $observer ( keys %observers ) {
            $observers{$observer}{'callback'}->( $observers{$observer}{'observer'}, $data_burst );
          }
  };

  return ( $subscribe_ref, $unsubscribe_ref, $notify_ref );
}
