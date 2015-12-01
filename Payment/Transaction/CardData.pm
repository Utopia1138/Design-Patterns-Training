package Payment::Transaction::CardData;

use strict;
use warnings;

use Moose;
use Moose::Util::TypeConstraints;

has 'PAN' => (
  is =>  'rw',
  isa => subtype(
    as 'Str',
    where { /^\d{16,19}$/ },
    message { "PAN must be 16-19 digits" },
  ),
);

has 'expiry' => (
  is => 'rw',
  isa => subtype(
    as 'Str',
    where { /^\d\d\/\d\d$/ },
    message { "Expiry must be in the format MM/YY" },
  ),
);

no Moose;
__PACKAGE__->meta->make_immutable;
